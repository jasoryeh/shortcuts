package tk.jasonho.shortcuts.bukkit.log;

import org.bukkit.Bukkit;
import tk.jasonho.shortcuts.everything.log.LoggerManager;

public class BukkitLoggerManager extends LoggerManager {
    public static LoggerManager get() {
        return new BukkitLoggerManager();
    }

    @Override
    public void info(Object... o) {
        Bukkit.getLogger().info(concat(o));
    }

    @Override
    public void warn(Object... o) {
        Bukkit.getLogger().warning(concat(o));
    }

    @Override
    public void error(Object... o) {
        Bukkit.getLogger().severe(concat(o));
    }

    @Override
    public void debug(Object... o) {
        Bukkit.getLogger().fine(concat(o));
    }
}
