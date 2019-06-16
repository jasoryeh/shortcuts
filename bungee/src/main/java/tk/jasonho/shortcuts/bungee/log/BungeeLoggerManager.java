package tk.jasonho.shortcuts.bungee.log;

import net.md_5.bungee.api.ProxyServer;
import tk.jasonho.shortcuts.everything.log.LoggerManager;

public class BungeeLoggerManager extends LoggerManager {
    public static LoggerManager get() {
        return new BungeeLoggerManager();
    }

    @Override
    public void info(String m) {
        ProxyServer.getInstance().getLogger().info(m);
    }

    @Override
    public void warn(String m) {
        ProxyServer.getInstance().getLogger().warning(m);
    }

    @Override
    public void error(String m) {
        ProxyServer.getInstance().getLogger().severe(m);
    }

    @Override
    public void debug(String m) {
        ProxyServer.getInstance().getLogger().fine(m);
    }
}
