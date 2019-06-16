package tk.jasonho.shortcuts.bungee.chat;

import net.md_5.bungee.api.chat.TextComponent;
import tk.jasonho.shortcuts.everything.chat.Colors;

public class BungeeColors {
    public static TextComponent translate(String txt) {
        return new TextComponent(Colors.translate(txt));
    }
}
