package tk.jasonho.shortcuts.everything.log;

import tk.jasonho.shortcuts.everything.util.ConsoleColors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DefaultLogger extends LoggerManager {

    /**
     * Date format for console messages
     */
    private final static DateFormat CONSOLE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.ENGLISH);

    @Override
    public void info(Object... o) {
        System.out.println(toLine(ConsoleColors.GREEN_BRIGHT + "INFO", o));
    }

    @Override
    public void warn(Object... o) {
        System.out.println(toLine(ConsoleColors.YELLOW_BRIGHT + "WARN", o));
    }

    @Override
    public void error(Object... o) {
        System.err.println(toLine(ConsoleColors.RED_BRIGHT + "ERROR", o));
    }

    @Override
    public void debug(Object... o) {
        System.out.println(toLine(ConsoleColors.BLUE_BRIGHT + "DEBUG", o));
    }

    /**
     * Builds a string that includes the log
     * level, time, and log message
     * @param prefix The very first part of the line
     * @param messages The message(s) to log
     * @return String that can be logged to the console
     */
    private static String toLine(String prefix, Object... messages) {
        StringBuilder builder = new StringBuilder();

        builder.append(" [").append(prefix).append("] ")
                .append(" [").append(CONSOLE_FORMAT.format(new Date())).append("] ").append(concat(messages));

        return builder.toString();
    }
}
