package tk.jasonho.shortcuts.bungee;

import lombok.Getter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class Manager implements Listener {

    @Getter
    protected final BungeeEverythingAPI api;

    public Manager(BungeeEverythingAPI api) {
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
    public static List<Manager> enableManagers(BungeeEverythingAPI api, List<Manager> managers) {
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

    public void enableListeners() {
        this.api.getPlugin().getProxy().getPluginManager().registerListener(this.api.getPlugin(), this);
    }

    /**
     * Self-registering command on constructor call.
     * <p>
     * This is simply an extension of BukkitCommand
     * <p>
     * see https://www.spigotmc.org/threads/small-easy-register-command-without-plugin-yml.38036/
     */
    protected abstract class Command extends net.md_5.bungee.api.plugin.Command {
        private final BungeeEverythingAPI api;

        public Command(BungeeEverythingAPI api, String command) {
            this(api, command, null);
        }

        public Command(BungeeEverythingAPI api, String command, String permission) {
            this(api, command, permission, new String[0]);
        }

        public Command(BungeeEverythingAPI api, String command, String permission, String... alias) {
            super(command, permission, alias);

            this.api = api;

            this.registerCommand();
        }

        private void registerCommand() {
            this.api.getPlugin().getProxy().getPluginManager().registerCommand(this.api.getPlugin(), this);

            this.api.getPlugin().getLogger().info("Registered command \"" + this.getName() + "\"");
        }

        public abstract void execute(CommandSender sender, String[] args);
    }
}

