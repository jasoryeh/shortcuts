package tk.jasonho.shortcuts.everything.lang;

import lombok.Getter;
import lombok.Setter;
import tk.jasonho.shortcuts.everything.EverythingAPI;
import tk.jasonho.shortcuts.everything.yaml.YamlConfigurationObject;

import java.util.Locale;

public class DefaultLocalizable extends AbstractLocalizable {

    @Getter
    @Setter
    private final String CONFIG_PREPEND = "lang";

    public DefaultLocalizable(EverythingAPI api, String path) {
        super(api, path);
    }

    public String resolveDefault() {
        return this.resolve(DEFAULT);
    }

    public String resolve(Locale locale) {
        YamlConfigurationObject lang = this.api.getLangConfig().getSection(CONFIG_PREPEND);

        YamlConfigurationObject specific = lang.getSection(locale.getLanguage().toLowerCase() + "_"
                + locale.getCountry().toLowerCase());

        if (specific == null) {
            specific = lang.getSection(locale.getLanguage().toLowerCase());
            if (specific == null) {
                specific = lang.getSection(DEFAULT.getLanguage().toLowerCase());
                if (specific == null) {
                    return this.path;
                }
            }
        }

        return specific.getString(this.path, this.path);
    }

    protected Locale resolveLocale(String locale) {
        return Locale.forLanguageTag(locale
                .replace("_", "-")
                .replace("en-UK", "en-GB"));
    }

    @Override
    public String toString() {
        return this.resolveDefault();
    }
}

