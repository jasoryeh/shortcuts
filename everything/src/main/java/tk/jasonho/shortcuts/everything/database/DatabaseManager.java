package tk.jasonho.shortcuts.everything.database;

import lombok.Getter;
import lombok.Setter;
import tk.jasonho.shortcuts.everything.EverythingAPI;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager implements DatabaseConnectionManager {

    private static Map<EverythingAPI, DatabaseManager> managers = new HashMap<>();

    @Getter
    @Setter
    private DatabaseConfiguration configuration;
    private final boolean shouldUseHikariPooling;

    @Getter
    private DatabaseConnectionManager connectionManager;

    @Getter
    @Setter
    private EverythingAPI api;

    public DatabaseManager(EverythingAPI api, DatabaseConfiguration configuration) {
        this(api, configuration, true);
    }

    public DatabaseManager(EverythingAPI api, DatabaseConfiguration configuration, boolean shouldUseHikariPooling) {
        this.api = api;
        this.configuration = configuration;
        this.shouldUseHikariPooling = shouldUseHikariPooling;

        if(this.shouldUseHikariPooling) {
            this.connectionManager = new HikariDatabaseConnectionManager(this);
        } else {
            this.connectionManager = new JDBCDatabaseConnectionManager(this);
        }

        this.managers.put(api, this);
    }


    @Override
    public void closeConnection() {
        this.connectionManager.closeConnection();
    }

    @Override
    public Connection getNewConnection(String database) {
        return this.connectionManager.getNewConnection(database);
    }

    @Override
    public Connection getConnection(boolean newcon, String database) {
        return this.connectionManager.getConnection(newcon, database);
    }

    @Override
    public Connection getConnection() {
        return this.connectionManager.getConnection();
    }
}
