package tk.jasonho.shortcuts.bukkit.log;

import org.bukkit.Bukkit;
import tk.jasonho.shortcuts.everything.log.LoggerManager;

public class BukkitLoggerManager extends LoggerManager {
    public static LoggerManager get() {
        return new BukkitLoggerManager();
    }

    @Override
    public void info(String m) {
        Bukkit.getLogger().info(m);
    }

    @Override
    public void warn(String m) {
        Bukkit.getLogger().warning(m);
    }

    @Override
    public void error(String m) {
        Bukkit.getLogger().severe(m);
    }

    @Override
    public void debug(String m) {
        Bukkit.getLogger().fine(m);
    }
}
