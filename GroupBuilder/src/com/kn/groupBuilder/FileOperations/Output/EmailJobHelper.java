package com.kn.groupBuilder.FileOperations.Output;

import java.io.File;
import java.util.ArrayList;

import com.kn.groupBuilder.Storage.Group;
import com.kn.groupBuilder.Storage.Member;
import com.kn.groupBuilder.Storage.Pojo;

import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailContentCreator;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailJob;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailObject;
import dennis.markmann.MyLibraries.DefaultJobs.Email.EmailSettings;

/**
 * Helper class used to initialize the emailSending and to create the emails to send.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_21
 * @version 1.0
 */

public class EmailJobHelper {

    private EmailSettings setEmailSettings() {
        return new EmailSettings("GroupBuilder@gmx.de", "buildGroups", "GroupBuilder@gmx.de", "GroupBuilder", "smtp.gmx.net");
    }

    public final void sendMailsToAll(final Pojo pojo) {

        final ArrayList<EmailObject> emailList = this.createEmailObjects(pojo);
        new EmailJob().sendMail(this.setEmailSettings(), emailList);
    }

    public final void sendSingleMail(final Pojo pojo, final Member member) {
        final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();
        emailList.add(this.createSingleEmailObject(pojo, member));
        new EmailJob().sendMail(this.setEmailSettings(), emailList);
    }

    private String generateMailText(final Group group, final String path) {

        final StringBuilder sb = new StringBuilder();

        sb.append("Hello! ");
        sb.append(System.lineSeparator());
        sb.append("This is an autmatic generated mail by the groupBuilder of Dennis Markmann.");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append(new TextCreator().createText(group));

        return sb.toString();
    }

    private ArrayList<EmailObject> createEmailObjects(final Pojo pojo) {

        final ArrayList<EmailObject> emailList = new ArrayList<EmailObject>();
        final String path = pojo.getSettings().getPath();

        for (final Group group : pojo.getGroupList()) {

            final EmailObject emailObject = new EmailObject();
            emailList.add(emailObject);

            final ArrayList<String> emailAddresList = emailObject.getEmailAddressList();
            final String emailText = this.generateMailText(group, path);
            final File file = new File(path + "Groups//" + group.getName() + ".xml");

            new EmailContentCreator().createMailContent(emailText, file, emailObject);

            for (final Member member : group.getMemberList()) {
                emailAddresList.add(member.getEMailAdress());
            }
        }
        return emailList;
    }

    private EmailObject createSingleEmailObject(final Pojo pojo, final Member member) {

        final EmailObject emailObject = new EmailObject();

        final ArrayList<String> emailAddresList = emailObject.getEmailAddressList();
        emailAddresList.add(member.getEMailAdress());

        final Group group = member.getGroup();
        final String path = pojo.getSettings().getPath();

        final String emailText = this.generateMailText(group, path);
        final File file = new File(path + "Groups//" + group.getName() + ".xml");

        new EmailContentCreator().createMailContent(emailText, file, emailObject);

        return emailObject;
    }
}
