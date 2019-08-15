package tk.jasonho.shortcuts.bungee.log;

import net.md_5.bungee.api.ProxyServer;
import tk.jasonho.shortcuts.everything.log.LoggerManager;

public class BungeeLoggerManager extends LoggerManager {
    public static LoggerManager get() {
        return new BungeeLoggerManager();
    }

    @Override
    public void info(Object... o) {
        ProxyServer.getInstance().getLogger().info(concat(o));
    }

    @Override
    public void warn(Object... o) {
        ProxyServer.getInstance().getLogger().warning(concat(o));
    }

    @Override
    public void error(Object... o) {
        ProxyServer.getInstance().getLogger().severe(concat(o));
    }

    @Override
    public void debug(Object... o) {
        ProxyServer.getInstance().getLogger().fine(concat(o));
    }
}
