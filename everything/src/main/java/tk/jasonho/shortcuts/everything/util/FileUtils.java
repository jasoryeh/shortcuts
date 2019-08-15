package tk.jasonho.shortcuts.everything.util;

import java.io.File;
import java.nio.file.Paths;

public class FileUtils {

    /**
     * Returns a File object
     * that is set to the current
     * program's working directory
     * or the directory the program
     * was running from.
     */
    public static File getCurrentWorkingDirectory() {
        return Paths.get(".").toFile();
    }

}
