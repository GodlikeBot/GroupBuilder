package com.kn.groupBuilder.Gui.Popups;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.kn.groupBuilder.Gui.Popups.Listener.ConfirmationFrameListener;
import com.kn.groupBuilder.Storage.Pojo;

import dennis.markmann.MyLibraries.GuiJobs.DefaultFrames.Implementations.DefaultFrame;
import dennis.markmann.MyLibraries.GuiJobs.DefaultFrames.Implementations.MyWindowAdapter;

/**
 * Frame used to ask if a operation should really be done.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public final class ConfirmationFrame extends JFrame implements DefaultFrame {

    private static ConfirmationFrame instance = null;
    private static final long serialVersionUID = -6013891923961668832L;

    private ConfirmationFrame(final Pojo pojo, final String action, final Object object) {

        BUILDER.setDefaultFrameSettings(this, "GroupBuilder - Confirm");
        this.addWindowListener(new MyWindowAdapter(this));

        BUILDER.createLabel(this, "Are you sure you really want to " + action + " ?", 0, 1);
        final JButton confirmationButton = BUILDER.createButton(this, "confirmationButton", "Confirm", 0, 4);
        final JButton abortButton = BUILDER.createButton(this, "abortButton", "Abort", 3, 4);

        final ConfirmationFrameListener listener = new ConfirmationFrameListener(this, pojo, action, object);

        confirmationButton.addActionListener(listener);
        abortButton.addActionListener(listener);
    }

    public static ConfirmationFrame getInstance(final Pojo pojo, final String action, final Object object) {
        if (instance == null) {
            instance = new ConfirmationFrame(pojo, action, object);
        } else {
            instance.toFront();
        }
        return instance;
    }

    @Override
    public void openClosingDialog(final String text) {
        ConfirmationFrame.getInstance(null, text, this);
    }

    @Override
    public void closeWindow() {
        this.dispose();
        instance = null;
    }
}
