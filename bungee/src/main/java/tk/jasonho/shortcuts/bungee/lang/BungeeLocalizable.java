package tk.jasonho.shortcuts.bungee.lang;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import tk.jasonho.shortcuts.bungee.BungeeEverythingAPI;
import tk.jasonho.shortcuts.everything.lang.DefaultLocalizable;

import java.util.Locale;

public class BungeeLocalizable extends DefaultLocalizable {
    public BungeeLocalizable(BungeeEverythingAPI api, String path) {
        super(api, path);
    }

    public String resolve(ProxiedPlayer player) {
        try {
            Locale locale = player.getLocale();
            if (locale != null) {
                return this.resolve(locale);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return this.resolve(DEFAULT);
    }

    @Override
    public String resolve(Locale locale) {
        return ChatColor.translateAlternateColorCodes('&', this.resolve(locale));
    }
}
