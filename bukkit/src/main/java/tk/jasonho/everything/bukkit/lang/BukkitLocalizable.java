package tk.jasonho.everything.bukkit.lang;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import tk.jasonho.everything.bukkit.BukkitEverythingAPI;
import tk.jasonho.shortcuts.everything.lang.DefaultLocalizable;

import java.util.Locale;

public class BukkitLocalizable extends DefaultLocalizable {
    public BukkitLocalizable(BukkitEverythingAPI api, String path) {
        super(api, path);
    }

    public String resolve(Player player) {
        try {
            Locale locale = this.resolveLocale(player.spigot().getLocale());
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

