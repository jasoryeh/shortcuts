package tk.jasonho.shortcuts.everything;

import tk.jasonho.shortcuts.everything.log.LoggerManager;
import tk.jasonho.shortcuts.everything.yaml.YamlConfigurationObject;

import java.io.File;

/**
 * Not important, but it's just
 * a helper API to help some other
 * modules in the shortcuts project
 * to find some resources
 *
 * If used in a regular project, you
 * may need to manually implement and
 * store information for all of these
 * methods.
 */
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
