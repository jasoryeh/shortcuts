package tk.jasonho.shortcuts.bukkit.util.universal.entity;

import org.bukkit.entity.Player;

public class EntityOperations {

    public static boolean isLookingAt(Player p1, Player p2) {
        return p2.getEyeLocation().getDirection()
                .subtract(p1.getEyeLocation().getDirection())
                .normalize()
                .dot(p1.getEyeLocation().getDirection()) > 0.99D;
    }

}
