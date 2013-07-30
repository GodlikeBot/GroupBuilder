package com.kn.groupBuilder.Exceptions;

import javax.swing.JOptionPane;

import com.kn.groupBuilder.Storage.Pojo;

/**
 * Exception thrown if a file can't be written sucessfully.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class WriteOperationException extends Exception implements ExceptionDialogInterface {

    private static final long serialVersionUID = 6498733673905740756L;

    public WriteOperationException(final String path, final StackTraceElement[] stackTraceElements) {
        super(Pojo.getPojo().getMessages("WriteOperationText") + path);
    }

    @Override
    public final void showDialog() {
        JOptionPane.showMessageDialog(null, this.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        Pojo.getPojo().setError(true);
    }
}
