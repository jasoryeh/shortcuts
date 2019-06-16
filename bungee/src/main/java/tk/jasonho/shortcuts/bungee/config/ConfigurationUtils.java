package tk.jasonho.shortcuts.bungee.config;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import tk.jasonho.shortcuts.bungee.BungeeEverythingAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigurationUtils {

    public static Configuration setupConfiguration(BungeeEverythingAPI api) {
        if(!api.getDataFolder().exists()) {
            if(!api.getDataFolder().mkdirs()) {
                api.getLogger().error("Cannot make plugin data folder!");
            }
        }

        Configuration config = null;

        File f = new File(api.getDataFolder(), "config.yml");
        if (!f.exists()) {
            try {
                if (!f.createNewFile()) {
                    api.getLogger().error("Unable to create config " + f.getAbsolutePath());
                }
                InputStream in = api.getPlugin().getClass().getClassLoader().getResourceAsStream("config.yml");
                FileOutputStream ou = new FileOutputStream(f);

                ByteStreams.copy(in, ou);
                ou.close();
                in.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        try {
            ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);
            config = provider.load(new File(api.getDataFolder(), "config.yml"));
            provider.save(config, new File(api.getDataFolder(), "config.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return config;
    }
}
