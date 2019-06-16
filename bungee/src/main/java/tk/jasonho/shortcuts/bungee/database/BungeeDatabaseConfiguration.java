package tk.jasonho.shortcuts.bungee.database;

import net.md_5.bungee.config.Configuration;
import tk.jasonho.shortcuts.everything.database.DatabaseConfiguration;

public class BungeeDatabaseConfiguration extends DatabaseConfiguration {
    public BungeeDatabaseConfiguration(Configuration config) {
        super(
                config.getString("connections.database.host"),
                config.getString("connections.database.database"),
                config.getString("connections.database.username"),
                config.getString("connections.database.password")
        );
    }
}
