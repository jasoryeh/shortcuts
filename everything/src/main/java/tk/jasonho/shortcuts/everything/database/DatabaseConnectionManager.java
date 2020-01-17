package tk.jasonho.shortcuts.everything.database;

import java.sql.Connection;

public interface DatabaseConnectionManager {
    /**
     * Closes whatever connection we last touched.
     */
    public void closeConnection();

    Connection getNewConnection(String database);

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
    public Connection getConnection(boolean newcon, String database);

    /**
     * Spawns a connection the first supplied database on the list of databases
     * in the configuration.
     *
     * @return Connection to list.get(0);
     */
    public Connection getConnection();
}
