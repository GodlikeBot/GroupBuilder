package com.kn.groupBuilder.Gui.Popups.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.kn.groupBuilder.Exceptions.DataNotFoundException;
import com.kn.groupBuilder.Exceptions.EmptyValueException;
import com.kn.groupBuilder.Gui.Popups.ConfirmationFrame;
import com.kn.groupBuilder.Gui.Popups.EditMemberFrame;
import com.kn.groupBuilder.Storage.Group;
import com.kn.groupBuilder.Storage.Member;
import com.kn.groupBuilder.Storage.Pojo;

/**
 * Listener for the createMemberFrame. Allows to start edit member and close the window.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class EditMemberFrameListener implements ActionListener {

    private final EditMemberFrame editMemberFrame;
    private final Pojo pojo;
    private final int rowID;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField eMailField;
    final JTextField groupField;

    public EditMemberFrameListener(
            final EditMemberFrame editMemberFrame,
            final Pojo pojo,
            final int rowID,
            final JTextField firstNameField,
            final JTextField lastNameField,
            final JTextField eMailField,
            final JTextField groupField) {

        this.editMemberFrame = editMemberFrame;
        this.pojo = pojo;
        this.rowID = rowID;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.eMailField = eMailField;
        this.groupField = groupField;
    }

    @Override
    public final void actionPerformed(final ActionEvent event) {

        final JButton buttonClicked = (JButton) event.getSource();
        if (buttonClicked.getName().compareTo("confirmationButton") == 0) {

            final ArrayList<Member> memberList = new ArrayList<Member>();
            final String firstName = this.firstNameField.getText();
            final String lastName = this.lastNameField.getText();
            final String email = this.eMailField.getText();
            final String groupName = this.groupField.getText();
            final Group group = this.pojo.getGroupByName(groupName);

            if (firstName.equals("")) {
                new EmptyValueException("firstName").showDialog();
                return;
            }
            if (lastName.equals("")) {
                new EmptyValueException("lastName").showDialog();
                return;
            }
            if (!groupName.equals("") && group == null) {
                new DataNotFoundException("Group: \"" + groupName + "\"").showDialog();
                return;
            }
            memberList.add(this.pojo.getMemberList().get(this.rowID));
            memberList.add(new Member(firstName, lastName, email, group));

            ConfirmationFrame.getInstance(this.pojo, "editMember", memberList);
        }
        this.editMemberFrame.closeWindow();

    }
}
