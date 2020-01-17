package tk.jasonho.shortcuts.everything.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;

/**
 * Supports single databases
 */
public class HikariDatabaseConnectionManager implements DatabaseConnectionManager {

    @Getter
    private final DatabaseManager parent;

    private HikariDataSource hikariDataSource;

    public HikariDatabaseConnectionManager(DatabaseManager parent) {
        this.parent = parent;

        DatabaseConfiguration configuration = this.parent.getConfiguration();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + configuration.getHost() + ":" + configuration.getPort() + "/" + configuration.getDatabases().get(0));
        config.setUsername(config.getUsername());
        config.setPassword(config.getPassword());

        this.hikariDataSource = new HikariDataSource(config);
    }

    @Deprecated
    public void closeConnection() { }

    public synchronized Connection getNewConnection(String database) {
        try {
            if(this.parent.getConfiguration().getDatabases().size() > 1) {
                this.getParent().getApi().getLogger().warn("Hikari doesn't support multi-database connections!");
            }
            Connection c = this.hikariDataSource.getConnection();
            return c;
        } catch(Exception e) {
            this.parent.getApi().getLogger().error("Could not get new connection.");
            e.printStackTrace();
            return null;
        }
    }

    public Connection getConnection(boolean newcon, String database) {
        return getNewConnection(database);
    }

    public Connection getConnection() {
        return getConnection(true, this.parent.getConfiguration().getDatabases().get(0));
    }

}
