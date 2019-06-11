package tk.jasonho.shortcuts.everything.lang;

import lombok.Getter;
import lombok.Setter;
import tk.jasonho.shortcuts.everything.EverythingAPI;

import java.util.Locale;

public abstract class AbstractLocalizable {
    @Getter
    @Setter
    protected static Locale DEFAULT = Locale.ENGLISH;

    @Getter
    protected final EverythingAPI api;
    @Getter
    protected final String path;

    public AbstractLocalizable(EverythingAPI api, String path) {
        this.api = api;
        this.path = path;
    }

    public String resolveDefault() {
        return this.resolve(DEFAULT);
    }

    public abstract String resolve(Locale locale);

    private Locale resolveLocale(String locale) {
        return Locale.forLanguageTag(locale.replace("_", "-").replace("en-UK", "en-GB"));
    }
}
