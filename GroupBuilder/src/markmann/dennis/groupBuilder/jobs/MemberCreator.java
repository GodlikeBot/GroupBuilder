package markmann.dennis.groupBuilder.jobs;

import markmann.dennis.groupBuilder.exceptions.DuplicateEntryException;
import markmann.dennis.groupBuilder.logging.LogHandler;
import markmann.dennis.groupBuilder.storage.Group;
import markmann.dennis.groupBuilder.storage.Member;
import markmann.dennis.groupBuilder.storage.Pojo;

import org.apache.log4j.Logger;

/**
 * Used to create new member objects.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class MemberCreator {

    private static final Logger logger = LogHandler.getLogger("./logs/GroupBuilder.log");

    private final Pojo pojo;

    public MemberCreator(final Pojo pojo) {
        this.pojo = pojo;
    }

    public final void createMember(final String firstName, final String lastName) {
        logger.info("Creating member " + firstName + " " + lastName);
        this.addMemberToList(new Member(this.correctFormat(firstName), this.correctFormat(lastName)));
    }

    public final void createMember(final String firstName, final String lastName, final String eMailAdress) {
        logger.info("Creating member " + firstName + " " + lastName);
        this.addMemberToList(new Member(this.correctFormat(firstName), this.correctFormat(lastName), eMailAdress));
    }

    public final void createMember(final String firstName, final String lastName, final String eMailAdress, final Group group) {
        logger.info("Creating member " + firstName + " " + lastName);
        this.addMemberToList(new Member(this.correctFormat(firstName), this.correctFormat(lastName), eMailAdress, group));
    }

    public final void correctMemberFormat(final Member member) {
        logger.info("Correcting member format.");

        final String firstName = member.getFirstName();
        final String lastName = member.getLastName();
        final String eMailAdress = member.getEMailAdress();
        final Group group = member.getGroup();

        this.addMemberToList(new Member(this.correctFormat(firstName), this.correctFormat(lastName), eMailAdress, group));
    }

    private void addMemberToList(final Member member) {
        logger.info("Adding member to list.");

        try {
            this.checkDuplicates(member.getFirstName(), member.getLastName());
        } catch (final DuplicateEntryException e) {
            e.showDialog();
            return;
        }
        this.pojo.getMemberList().add(member);
    }

    public final void removeMember(final Member member) {
        logger.info("Removing member " + member);

        this.pojo.getMemberList().remove(this.pojo.getMemberByName(member.getFirstName(), member.getLastName()));
    }

    public final void editMember(final Member oldMember, final Member newMember) {
        logger.info("Editing member " + oldMember);

        final Member member = this.pojo.getMemberByName(oldMember.getFirstName(), oldMember.getLastName());
        member.setFirstName(newMember.getFirstName());
        member.setLastName(newMember.getLastName());
        member.setEMailAdress(newMember.getEMailAdress());
        member.setGroup(newMember.getGroup());
    }

    private void checkDuplicates(final String firstName, final String lastName) throws DuplicateEntryException {
        if (this.pojo.getMemberByName(firstName, lastName) != null) {
            throw new DuplicateEntryException(firstName + "." + lastName);
        }
    }

    private String correctFormat(String string) {
        string = string.trim();
        string = string.toLowerCase();
        string = string.substring(0, 1).toUpperCase() + string.substring(1);
        return string;
    }
}
