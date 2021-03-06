package tk.jasonho.shortcuts.bungee;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;
import tk.jasonho.shortcuts.bungee.log.BungeeLoggerManager;
import tk.jasonho.shortcuts.everything.EverythingAPI;
import tk.jasonho.shortcuts.everything.log.LoggerManager;
import tk.jasonho.shortcuts.everything.yaml.YamlConfiguration;
import tk.jasonho.shortcuts.everything.yaml.YamlConfigurationObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BungeeEverythingAPI extends EverythingAPI {

    /**
     * Store every copy of this instance here.
     */
    @Getter
    private static Map<Plugin, BungeeEverythingAPI> instances = new HashMap<Plugin, BungeeEverythingAPI>();

    @Getter
    private Plugin plugin;

    public BungeeEverythingAPI(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void enable() { }

    @Override
    public void disable() { }

    @Override
    public LoggerManager getLogger() {
        return BungeeLoggerManager.get();
    }

    @Override
    public YamlConfigurationObject getConfig() {
        return YamlConfiguration.load(new File(this.plugin.getDataFolder() + File.separator + "config.yml"));
    }

    @Setter
    private YamlConfigurationObject langConfig;

    @Override
    public YamlConfigurationObject getLangConfig() {
        return this.langConfig;
    }

    @Override
    public File getDataFolder() {
        return this.plugin.getDataFolder();
    }
}
