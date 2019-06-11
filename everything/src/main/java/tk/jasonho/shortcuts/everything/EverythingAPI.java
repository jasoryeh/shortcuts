package tk.jasonho.shortcuts.everything;

import tk.jasonho.shortcuts.everything.log.LoggerManager;
import tk.jasonho.shortcuts.everything.yaml.YamlConfigurationObject;

import java.io.File;

public abstract class EverythingAPI {

    public EverythingAPI() {

    }

    public abstract void enable();

    public abstract void disable();

    public abstract LoggerManager getLogger();

    public abstract YamlConfigurationObject getConfig();

    public abstract YamlConfigurationObject getLangConfig();

    public abstract File getDataFolder();
}
