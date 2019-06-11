package tk.jasonho.everything.bukkit;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Manager implements Listener {

    @Getter
    protected final BukkitEverythingAPI api;

    public Manager(BukkitEverythingAPI api) {
        this.api = api;
    }

    public abstract void enable();

    public abstract void disable();

    /**
     * Given a list of managers to enable, it will try to load all of them
     * and return a list that was successful.
     * @param api Everything API
     * @param managers List of managers
     * @return
     */
    public static List<Manager> enableManagers(BukkitEverythingAPI api, List<Manager> managers) {
        List<Manager> enabled = new ArrayList<>(managers);

        for (Manager manager : managers) {
            api.getLogger().info("Enabling manager class " + manager.getClass().getName());
            try {
                manager.enable();
            } catch (Exception e) {
                api.getLogger().warn("Unable to setup manager " + manager.getClass().getName());
                e.printStackTrace();
                enabled.remove(manager);
            }
        }

        return enabled;
    }

    /**
     * Self-registering command on constructor call.
     * <p>
     * This is simply an extension of BukkitCommand
     * <p>
     * see https://www.spigotmc.org/threads/small-easy-register-command-without-plugin-yml.38036/
     */
    protected abstract class Command extends BukkitCommand {
        private final BukkitEverythingAPI api;

        public Command(BukkitEverythingAPI api, String command) {
            this(api, command, null);
        }

        public Command(BukkitEverythingAPI api, String command, String permission) {
            this(api, command, permission, new String[0]);
        }

        public Command(BukkitEverythingAPI api, String command, String permission, String... alias) {
            super(command, "(empty)", "/" + command.toLowerCase(), Arrays.asList(alias));
            this.setPermission(permission);

            this.api = api;

            this.registerCommand();
        }

        private void registerCommand() {
            try {
                final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                bukkitCommandMap.setAccessible(true);

                CommandMap map = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

                map.register(this.getName(), this);
                this.api.getLogger().info("Registered command \"" + this.getName() + "\"");
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();

                this.api.getLogger().info("An error occurred while registering commands.");
                Bukkit.shutdown();
            }
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            this.execute(sender, args);
            return true;
        }

        public abstract void execute(CommandSender sender, String[] args);
    }
}

