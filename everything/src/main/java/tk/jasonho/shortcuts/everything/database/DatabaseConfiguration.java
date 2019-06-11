package tk.jasonho.shortcuts.everything.database;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class DatabaseConfiguration {
    @Getter
    @Setter
    private final String host;
    @Getter
    @Setter
    private final List<String> databases;
    @Getter
    @Setter
    private final String username;
    @Getter
    @Setter
    private final String password;
    @Getter
    @Setter
    private final int port;

    public DatabaseConfiguration(String host, String database, String username, String password) {
        this(hostAddress(host), hostPort(host), Arrays.asList(database), username, password);
    }
    public DatabaseConfiguration(String host, int port, String database, String username, String password) {
        this(host, port, Arrays.asList(database), username, password);
    }

    public DatabaseConfiguration(String host, List<String> databases, String username, String password) {
        this(hostAddress(host), hostPort(host), databases, username, password);
    }

    private static String hostAddress(String host) {
        return host.split(":")[0];
    }

    private static int hostPort(String host) {
        return host.split(":").length > 1 ? Integer.valueOf(host.split(":")[1]) : 3306;
    }

    public DatabaseConfiguration(String host, int port, List<String> databases, String username, String password) {
        this.host = host;
        this.databases = databases;
        this.username = username;
        this.password = password;
        this.port = port;
    }
}
