package tk.jasonho.shortcuts.everything.database;

import lombok.Getter;
import lombok.Setter;
import tk.jasonho.shortcuts.everything.EverythingAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {

    private static Map<EverythingAPI, DatabaseManager> managers = new HashMap<>();

    private Connection connection;

    @Getter
    @Setter
    private DatabaseConfiguration configuration;

    @Getter
    @Setter
    private EverythingAPI api;

    public DatabaseManager(EverythingAPI api, DatabaseConfiguration configuration) {
        this.api = api;
        this.configuration = configuration;

        this.managers.put(api, this);
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

    private synchronized Connection getNewConnection(String database) {
        try {
            if(!this.getConfiguration().getDatabases().contains(database)) {
                this.api.getLogger().warn("The database configuration does not have a '" + database + "' configured! Connection may not work.");
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://" + this.configuration.getHost() + ":" + this.configuration.getPort()+ "/" + database,
                    this.configuration.getUsername(),
                    this.configuration.getPassword()
            );
            return c;
        } catch(Exception e) {
            this.api.getLogger().error("Could not get new connection.");
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
            this.getApi().getLogger().warn("An error occurred whilst getting a connection.");
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
        String db = this.getConfiguration().getDatabases().get(0);
        this.getApi().getLogger().info("Spawning default connection to '" + db + "'.");
        return getConnection(true, db);
    }
}
