package com.kn.groupBuilder.Gui.MainFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.kn.groupBuilder.Gui.HelperClasses.GuiTabBuilder;
import com.kn.groupBuilder.Gui.MainFrame.Listener.GroupTabListener;
import com.kn.groupBuilder.Storage.Pojo;

class GroupTab extends JPanel {

    private static final long serialVersionUID = 1673516265342795696L;
    private final GuiTabBuilder builder = new GuiTabBuilder();

    GroupTab(final Pojo pojo) {

        this.setLayout(new GridBagLayout());

        this.builder.createGroupTable(pojo.getGroupList(), this, 0, 0);
        final JButton addButton = this.builder.createButton(this, "addButton", "Add group", 0, 5);

        addButton.addActionListener(new GroupTabListener(pojo));

    }
}