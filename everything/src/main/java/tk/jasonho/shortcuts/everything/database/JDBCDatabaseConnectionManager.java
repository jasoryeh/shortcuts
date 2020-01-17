package tk.jasonho.shortcuts.everything.database;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Supports single connections only
 */
public class JDBCDatabaseConnectionManager implements DatabaseConnectionManager {

    private Connection connection;
    @Getter
    private final DatabaseManager parent;

    public JDBCDatabaseConnectionManager(DatabaseManager parent) {
        this.parent = parent;
    }

    /**
     * Closes whatever connection we last touched.
     */
    public void closeConnection() {
        Connection old = connection;
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public synchronized Connection getNewConnection(String database) {
        try {
            DatabaseConfiguration configuration = this.parent.getConfiguration();
            if(!configuration.getDatabases().contains(database)) {
                this.parent.getApi().getLogger().warn("The database configuration does not have a '"
                        + database + "' configured! Connection may not work.");
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://" + configuration.getHost() + ":" + configuration.getPort()+ "/" + database,
                    configuration.getUsername(),
                    configuration.getPassword()
            );
            return c;
        } catch(Exception e) {
            this.parent.getApi().getLogger().error("Could not get new connection.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gives connection to the specified database, warns if you select something
     * that isn't on the list.
     *
     * Connects with credentials supplied earlier.
     *
     * @param newcon New connection (if one has been made before)?
     * @param database Which database?
     * @return
     */
    public Connection getConnection(boolean newcon, String database) {
        try {
            if(newcon) {
                closeConnection();
                return getNewConnection(database);
            } else if((connection != null && !connection.isClosed()) || connection.getMetaData().getURL().contains("/" + database)) {
                return connection;
            } else if(connection == null || connection.isClosed()) {
                return getNewConnection(database);
            }
            return getNewConnection(database);
        } catch(Exception e) {
            this.parent.getApi().getLogger().warn("An error occurred whilst getting a connection.");
            e.printStackTrace();
        }
        return getNewConnection(database);
    }

    /**
     * Spawns a connection the first supplied database on the list of databases
     * in the configuration.
     *
     * @return Connection to list.get(0);
     */
    public Connection getConnection() {
        String db = this.parent.getConfiguration().getDatabases().get(0);
        this.parent.getApi().getLogger().info("Spawning default connection to '" + db + "'.");
        return getConnection(true, db);
    }
}
