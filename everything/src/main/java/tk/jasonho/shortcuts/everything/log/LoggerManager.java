package tk.jasonho.shortcuts.everything.log;

import java.util.StringJoiner;

/**
 * Default logger methods to be implemented
 * wherever else this is used to simplify
 * logging levels to 4 simple levels
 */
public abstract class LoggerManager {

    /**
     * Log out to console general information
     * @param o
     */
    public abstract void info(Object... o);

    /**
     * Log out to console warnings that are not
     * severely affecting the app
     * @param o
     */
    public abstract void warn(Object... o);

    /**
     * Log errors out to console that are for
     * things that are either not recoverable
     * or for things that shouldn't have happened.
     * @param o
     */
    public abstract void error(Object... o);

    /**
     * Log out information that is useful for debugging
     * but not so much for the general user or info
     * that's not very important or that may be useful
     * when looking back at what happened.
     * @param o
     */
    public abstract void debug(Object... o);

    /**
     * Join Object[] with a delimiter of a single space (" ")
     *
     * This converts the object to string via Object.toString(),
     * adds them to a StringJoiner and returns the result.
     *
     * @param objects
     * @return
     */
    protected static String concat(Object[] objects) {
        StringJoiner joiner = new StringJoiner(" ");
        for (Object o : objects) {
            joiner.add(o.toString());
        }
        return joiner.toString();
    }
}
