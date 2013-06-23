package com.kn.groupBuilder.Storage;

import java.util.ArrayList;

public class Pojo {

    private ArrayList<Member> memberList = new ArrayList<Member>();;
    private ArrayList<Group> groupList = new ArrayList<Group>();
    private final String[] languageList = { "German", "English" };
    private final String[] formatList = { "XML", "TXT" };
    private final Settings settings = new Settings();

    public final ArrayList<Member> getMemberList() {
        return this.memberList;
    }

    public final void setMemberList(final ArrayList<Member> memberList) {
        this.memberList = memberList;
    }

    public final void setGroupList(final ArrayList<Group> groupList) {
        this.groupList = groupList;

    }

    public final ArrayList<Group> getGroupList() {
        return this.groupList;
    }

    public final String[] getLanguageList() {
        return this.languageList;
    }

    public final String[] getFormatList() {
        return this.formatList;
    }

    public final Settings getSettings() {
        return this.settings;
    }

    public Group getGroupByName(final String name) {
        for (final Group group : this.groupList) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    public Member getMemberByName(final String firstName, final String lastName) {
        for (final Member member : this.memberList) {
            if (member.getFirstName().equals(firstName) && member.getLastName().equals(lastName)) {
                return member;
            }
        }
        return null;
    }

    public String[] getGroupListAsArray() {

        final String[] groupListArray = new String[this.groupList.size()];
        for (int i = 0; i < groupListArray.length; i++) {
            groupListArray[i] = this.groupList.get(i).getName();
        }
        return groupListArray;
    }
    // public String[] getGroupListForComboboxes() {
    // final StringBuilder builder = new StringBuilder();
    //
    // for (final Group group : this.groupList) {
    // if (builder.length() != 0) {
    // builder.append(", ");
    // } else {
    // builder.append("{");
    // }
    // builder.append("\"").append(group.getName()).append("\"");
    // }
    // builder.append("}");
    // // System.out.println(builder.toString());
    // final String[] groupListArray = new String[] { builder.toString() };
    //
    // return groupListArray;
    // }
}
