package com.kn.groupBuilder.Exceptions;

import javax.swing.JOptionPane;

import com.kn.groupBuilder.Storage.Pojo;

/**
 * Exception thrown if no file can't be found at the chosen location.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class NoFilesFoundException extends Exception implements ExceptionDialogInterface {

    private static final long serialVersionUID = -4565962119370664301L;

    public NoFilesFoundException(final String path, final StackTraceElement[] stackTraceElements) {
        super(Pojo.getPojo().getMessages("NoFilesFoundText") + path);
    }

    @Override
    public final void showDialog() {
        JOptionPane.showMessageDialog(null, this.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        Pojo.getPojo().setError(true);
    }
}
