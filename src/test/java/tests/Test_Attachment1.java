package tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.EmailUtils;
import utils.HandleAttachments;

public class Test_Attachment1 {
	
	private static EmailUtils receiverEmail;
	private static EmailUtils senderEmail;
	 @BeforeMethod
	  public static void connectToEmail() {
	    try {
	     receiverEmail = new EmailUtils("test4dsmtp@gmail.com", "4d11ismail", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
	    senderEmail = new EmailUtils("sender4dsmtp@gmail.com", "4d011ismail", "smtp.gmail.com", EmailUtils.EmailFolder.SENT);
	    } catch (Exception e) {
	      e.printStackTrace();
	      Assert.fail(e.getMessage());
	    }
	    System.out.println("Connection to mail succeded");
	 }
	 @Test
	 public void verifyAttachmentReceivedMail() {
	HandleAttachments attach = new HandleAttachments();
	 try {
		attach.downloadAttachments(receiverEmail,"receiver");
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }
	 @Test
	 public void verifyAttachmentSentMail() {
			HandleAttachments attach = new HandleAttachments();
			 try {
				attach.downloadAttachments(senderEmail,"sender");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }

}
