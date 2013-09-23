package markmann.dennis.groupBuilder.jobs;

import java.util.ArrayList;

import markmann.dennis.groupBuilder.exceptions.DuplicateEntryException;
import markmann.dennis.groupBuilder.storage.Group;
import markmann.dennis.groupBuilder.storage.Pojo;

/**
 * Used to create new group objects.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class GroupCreator {

    private final Pojo pojo;

    public GroupCreator(final Pojo pojo) {
        this.pojo = pojo;
    }

    public final void createGroup(final String name, final String description) {
        this.addGroupToList(new Group(this.correctFormat(name), description));
    }

    public final void createGroup(final String name, final String description, final int fixSize) {
        this.addGroupToList(new Group(this.correctFormat(name), description, fixSize));
    }

    private void addGroupToList(final Group group) {
        try {
            this.checkDuplicates(group.getName());
        } catch (final DuplicateEntryException e) {
            e.showDialog();
            return;
        }
        this.pojo.getGroupList().add(group);
    }

    public final void createGroupsAutmatically(final int memberPerGroup) {

        // parsing to double to avoid problems with odd numbers
        final double numberOfGroups = (double) this.pojo.getMemberList().size() / (double) memberPerGroup;

        this.cleanGroupList();

        for (int i = 0; i < numberOfGroups; i++) {
            this.pojo.getGroupList().add(new Group("Group" + i));
        }

    }

    public final void removeGroup(final Group group) {
        this.pojo.getGroupList().remove(this.pojo.getGroupByName(group.getName()));
    }

    public final void editGroup(final Group oldGroup, final Group newGroup) {
        final Group group = this.pojo.getGroupByName(oldGroup.getName());
        group.setName(newGroup.getName());
        group.setDescription(newGroup.getDescription());
        group.setFixSize(newGroup.getFixSize());
        // group.setMemberList(newGroup.getMemberList());

    }

    private void cleanGroupList() {
        final ArrayList<Group> tempList = new ArrayList<Group>();

        for (final Group group : this.pojo.getGroupList()) {
            tempList.add(group);
        }
        for (final Group group : tempList) {
            this.pojo.getGroupList().remove(group);
        }
    }

    private void checkDuplicates(final String name) throws DuplicateEntryException {
        if (this.pojo.getGroupByName(name) != null) {
            throw new DuplicateEntryException(name);
        }
    }

    private String correctFormat(String string) {
        string = string.trim();
        string = string.toLowerCase();
        string = string.substring(0, 1).toUpperCase() + string.substring(1);
        return string;
    }

}