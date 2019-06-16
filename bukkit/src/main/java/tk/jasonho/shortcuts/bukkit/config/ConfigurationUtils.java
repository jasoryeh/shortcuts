package tk.jasonho.shortcuts.bukkit.config;

import org.bukkit.configuration.file.FileConfiguration;
import tk.jasonho.shortcuts.bukkit.BukkitEverythingAPI;

import java.io.File;

public class ConfigurationUtils {
    public static FileConfiguration setupConfiguration(BukkitEverythingAPI api) {
        File f = new File(api.getPlugin().getDataFolder(), "config.yml");
        if (!f.exists()) {
            api.getPlugin().getConfig().options().copyDefaults(true);
            api.getPlugin().saveConfig();
        }
        api.getPlugin().saveConfig();

        return api.getPlugin().getConfig();
    }
}
