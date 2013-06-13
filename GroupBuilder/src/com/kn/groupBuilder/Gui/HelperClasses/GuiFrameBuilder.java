package com.kn.groupBuilder.Gui.HelperClasses;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuiFrameBuilder {

    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public GuiFrameBuilder() {

        this.gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.gridBagConstraints.weightx = 2;
    }

    public final void setDefaultFrameSettings(final JFrame frame) {

        frame.setTitle("GroupBuilder");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(400, 200));
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public final JButton createButton(
            final JFrame frame,
            final String buttonName,
            final String buttonText,
            final int gridxValue,
            final int gridyValue) {

        final JButton button = new JButton(buttonText);
        this.setName(button, buttonName);
        this.setPosition(frame, this.gridBagConstraints, gridxValue, gridyValue, button);

        return button;
    }

    public final JLabel createLabel(final JFrame frame, final String labelText, final int gridxValue, final int gridyValue) {
        final JLabel label = new JLabel(labelText);
        this.setPosition(frame, this.gridBagConstraints, gridxValue, gridyValue, label);

        return label;
    }

    public final JTextField createTextField(
            final JFrame frame,
            final int textFieldSize,
            final int gridxValue,
            final int gridyValue) {
        final JTextField textField = new JTextField(textFieldSize);
        this.setPosition(frame, this.gridBagConstraints, gridxValue, gridyValue, textField);

        return textField;
    }

    private void setName(final Component object, final String objectName) {
        object.setName(objectName);
    }

    private void setPosition(
            final JFrame frame,
            final GridBagConstraints c,
            final int gridxValue,
            final int gridyValue,
            final Component object) {

        c.gridx = gridxValue;
        c.gridy = gridyValue;
        frame.add(object, c);
    }

    public final GridBagConstraints getGridBagConstraints() {
        return this.gridBagConstraints;
    }
}