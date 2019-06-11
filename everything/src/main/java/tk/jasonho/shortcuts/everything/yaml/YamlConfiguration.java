package tk.jasonho.shortcuts.everything.yaml;

import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;

public class YamlConfiguration {

    @SneakyThrows
    /**
     *
     * @throws java.io.FileNotFoundException
     */
    public static YamlConfigurationObject load(File f) {
        FileInputStream stream = new FileInputStream(f);

        return YamlConfigurationObject.get(
                new Yaml().load(stream),
                "" // root.
        );
    }
}
