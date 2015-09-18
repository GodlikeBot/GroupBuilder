package markmann.dennis.groupBuilder.exceptions;

import markmann.dennis.groupBuilder.storage.Pojo;
import dennis.markmann.MyLibraries.DefaultJobs.FileCopy.CopyOperationException;

/**
 * Exception thrown if a file can't be written sucessfully.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class CopyException extends SuperException implements ExceptionDialogInterface {

    private static final String errorTitel = "CopyException";
    private static final String errorMessage = "An error appeared while trying to copy a file.";
    private final String message;

    private static final long serialVersionUID = 6498733673905740756L;

    public CopyException(CopyOperationException e) {
        // TODO deutsche Übersetzung
        super(errorTitel, errorMessage, true, e);
        this.message = (Pojo.getPojo().getTranslation("CopyExceptionMessage"));
    }

    @Override
    public final void showDialog() {
        super.showDialog(this.message);
    }
}
