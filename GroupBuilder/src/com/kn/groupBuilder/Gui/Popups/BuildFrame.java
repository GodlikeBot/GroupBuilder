package com.kn.groupBuilder.Gui.Popups;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import com.kn.groupBuilder.Gui.HelperClasses.CheckBoxHelper;
import com.kn.groupBuilder.Gui.HelperClasses.GuiFrameBuilder;
import com.kn.groupBuilder.Gui.Popups.Listener.BuildFrameListener;
import com.kn.groupBuilder.Storage.Pojo;

public class BuildFrame extends JFrame {

    private static final long serialVersionUID = -6911722669720979718L;
    private final GuiFrameBuilder builder = new GuiFrameBuilder();
    private final CheckBoxHelper checkBoxHelper = new CheckBoxHelper();

    public BuildFrame(final Pojo pojo) {

        this.builder.setDefaultFrameSettings(this, "Build Groups");

        final JCheckBox buildCompleteCheckBox = this.checkBoxHelper.createSingleSelectionCheckBox(
                this,
                "buildCompleteCheckBox",
                "(Re)Build Everything",
                0,
                0);
        final JCheckBox buildSelectedCheckBox = this.checkBoxHelper.createSingleSelectionCheckBox(
                this,
                "buildSelectedCheckBox",
                "Build Selected",
                0,
                1);
        final JCheckBox buildUnassignedCheckBox = this.checkBoxHelper.createSingleSelectionCheckBox(
                this,
                "buildUnassignedCheckBox",
                "Build Unassigned",
                0,
                2);
        final JCheckBox buildSingleCheckBox = this.checkBoxHelper.createSingleSelectionCheckBox(
                this,
                "buildSingleCheckBox",
                "Build Single",
                0,
                3);
        final JButton buildButton = this.builder.createButton(this, "buildButton", "Build", 0, 4);
        this.pack();

        final BuildFrameListener listener = new BuildFrameListener(
                this,
                pojo,
                buildCompleteCheckBox,
                buildSelectedCheckBox,
                buildUnassignedCheckBox,
                buildSingleCheckBox);

        buildButton.addActionListener(listener);

    }
}
