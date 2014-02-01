package markmann.dennis.groupBuilder.fileOperations.output;

import java.io.File;

import markmann.dennis.groupBuilder.exceptions.CopyException;
import markmann.dennis.groupBuilder.logging.LogHandler;
import markmann.dennis.groupBuilder.storage.Pojo;

import org.apache.log4j.Logger;

import dennis.markmann.MyLibraries.DefaultJobs.DateHelper;
import dennis.markmann.MyLibraries.DefaultJobs.FileCopy.CopyOperationException;
import dennis.markmann.MyLibraries.DefaultJobs.FileCopy.FileCopy;

/**
 * Used to create archive files (copys) of the current group files.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class GroupFileArchiver {

    private static final Logger logger = LogHandler.getLogger("./logs/Output.log");

    public final void archivGroupFiles(final Pojo pojo) {

        logger.info("Archiving group files.");

        final String archivPath = this.createArchivFolder(pojo.getSettings().getPath());

        final File[] files = new File(pojo.getSettings().getPath() + "Groups//").listFiles();

        for (final File file : files) {
            try {
                new FileCopy().copy(file.getAbsolutePath(), archivPath + file.getName());
            } catch (final CopyOperationException e) {
                new CopyException(e).showDialog();
            }
        }
    }

    private String createArchivFolder(final String path) {
        final String archivPath = path + "Archive//" + new DateHelper().getFullDate() + "//";
        new File(archivPath).mkdirs();
        return archivPath;

    }

}
