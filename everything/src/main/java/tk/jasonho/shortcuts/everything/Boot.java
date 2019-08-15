package tk.jasonho.shortcuts.everything;

import tk.jasonho.shortcuts.everything.util.FileUtils;

import java.io.File;

/**
 * Represents a Bootable object.
 *
 * Mainly used for applications that
 * are made from scratch like Discord
 * so that we know for sure that we can
 * just do something like:
 *   new BootClass().main();
 */
public abstract class Boot implements Toggleable {

    protected File workspace;


    public Boot() {
        this.workspace = FileUtils.getCurrentWorkingDirectory();
    }

}
