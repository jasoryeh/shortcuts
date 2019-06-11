package tk.jasonho.everything.bukkit.database;

import org.bukkit.configuration.file.FileConfiguration;
import tk.jasonho.shortcuts.everything.database.DatabaseConfiguration;

public class BukkitDatabaseConfiguration extends DatabaseConfiguration {
    public BukkitDatabaseConfiguration(FileConfiguration config) {
        super
                (
                        config.getString("connections.database.host"),
                        config.getString("connections.database.database"),
                        config.getString("connections.database.username"),
                        config.getString("connections.database.password")
                )
        ;
    }
}
