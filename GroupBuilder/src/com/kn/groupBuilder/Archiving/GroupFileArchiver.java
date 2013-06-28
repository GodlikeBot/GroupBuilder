package com.kn.groupBuilder.Archiving;

import java.io.File;

import com.kn.groupBuilder.HelperClasses.DateHelper;
import com.kn.groupBuilder.Storage.Pojo;

/**
 * Used to create archive files (copys) of the current group files.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class GroupFileArchiver {

    public final void archivGroupFiles(final Pojo pojo) {

        final String archivPath = this.createArchivFolder(pojo.getSettings().getPath());

        final File[] files = new File(pojo.getSettings().getPath() + "Groups//").listFiles();

        for (final File file : files) {
            new FileCopy().copy(file.getAbsolutePath(), archivPath + file.getName());
        }
    }

    private String createArchivFolder(final String path) {
        final String archivPath = path + "Archive//" + new DateHelper().getFullDate(0) + "//";
        new File(archivPath).mkdirs();
        return archivPath;

    }

}
