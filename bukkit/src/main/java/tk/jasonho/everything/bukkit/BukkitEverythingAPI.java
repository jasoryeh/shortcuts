package tk.jasonho.everything.bukkit;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.Plugin;
import tk.jasonho.everything.bukkit.log.BukkitLoggerManager;
import tk.jasonho.shortcuts.everything.EverythingAPI;
import tk.jasonho.shortcuts.everything.log.LoggerManager;
import tk.jasonho.shortcuts.everything.yaml.YamlConfiguration;
import tk.jasonho.shortcuts.everything.yaml.YamlConfigurationObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BukkitEverythingAPI extends EverythingAPI {

    /**
     * Store every copy of this instance here.
     */
    @Getter
    private static Map<Plugin, BukkitEverythingAPI> instances = new HashMap<Plugin, BukkitEverythingAPI>();

    /**
     * Bukkit plugin using us.
     */
    @Getter
    private Plugin plugin;


    public BukkitEverythingAPI(Plugin plugin) {
        super();

        this.plugin = plugin;

        instances.put(plugin, this);
    }

    public void enable() { }

    public void disable() { }

    public LoggerManager getLogger() {
        return BukkitLoggerManager.get();
    }

    @Override
    public YamlConfigurationObject getConfig() {
        return YamlConfiguration.load(new File(this.plugin.getDataFolder() + File.separator + "config.yml"));
    }

    @Getter
    @Setter
    /**
     * Must be manually set.
     */
    private YamlConfigurationObject langConfig;

    @Override
    /**
     * Returns whatever langConfig is set as. We don't automatically set it
     * so you can choose the files.
     */
    public YamlConfigurationObject getLangConfig() {
        return this.langConfig;
    }

    @Override
    public File getDataFolder() {
        return this.plugin.getDataFolder();
    }
}
