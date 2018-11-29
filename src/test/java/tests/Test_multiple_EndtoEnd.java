package tests;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.EmailUtils;

public class Test_multiple_EndtoEnd {
	
	private static EmailUtils receiverEmail1;
/*	private static EmailUtils receiverEmail2;
	private static EmailUtils receiverEmail3;
	private static EmailUtils receiverEmail4;*/
	private static EmailUtils senderEmail;
	 @BeforeMethod
	  public static void connectToEmail() {
	    try {
	     receiverEmail1 = new EmailUtils("test4dsmtp@gmail.com", "4d11ismail", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
	     //receiverEmail2 = new EmailUtils("test4d2smtp@gmail.com", "4d211ismail", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
	     //receiverEmail3 = new EmailUtils("test4d3smtp@gmail.com", "4d311ismail", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
	     //receiverEmail4 = new EmailUtils("test4d4smtp@gmail.com", "4d411ismail", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
	    senderEmail = new EmailUtils("sender4dsmtp@gmail.com", "4d011ismail", "smtp.gmail.com", EmailUtils.EmailFolder.SENT);
	    } catch (Exception e) {
	      e.printStackTrace();
	      Assert.fail(e.getMessage());
	    }
	    System.out.println("Connection to mail succeded");
	 }
	 @Test
	 public void verifyReceivedEmail() {
		    try {
		      //TODO: Execute actions to send verification code to email
		    	   Message[] messages = receiverEmail1.getAllMessages();
		  
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getFrom()), "sender4dsmtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.TO)), "test4dsmtp@gmail.com, test4d2smtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.CC)), "test4d3smtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.BCC)),null);
		      Assert.assertEquals(messages[messages.length-1].getSubject(), "Sending emails to multiple users");
		      //Assert.assertEquals(messages[messages.length-1].getContent().toString(), "This the first test for an authenticated mode");
		      
		      Date expectedDate=java.util.Calendar.getInstance().getTime(); 
		   
		      LocalDate localexpectedDate = expectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      
		      LocalDate localSentDate = messages[messages.length-1].getSentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      LocalDate localReceivedDate = messages[messages.length-1].getReceivedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      
		      Period period1 = Period.between(localexpectedDate,localSentDate);
		      Period period2 = Period.between(localexpectedDate,localReceivedDate);
		      Assert.assertEquals(period1.getDays(),0);
		      Assert.assertEquals(period2.getDays(),0);
		      
		   
		    } catch (Exception e) {
		      e.printStackTrace();
		      Assert.fail(e.getMessage());
		    }
		  }
	 
	 @Test
	 public void verifySentEmail() {
		    try {
		      //TODO: Execute actions to send verification code to email
		    	   Message[] messages = senderEmail.getAllMessages();
		    	   
		    	   System.out.println(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.TO)));
		  
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getFrom()), "sender4dsmtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.TO)), "test4dsmtp@gmail.com, test4d2smtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.CC)), "test4d3smtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.BCC)),"test4d4smtp@gmail.com");
		      Assert.assertEquals(messages[messages.length-1].getSubject(), "Sending emails to multiple users");
		    //  Assert.assertEquals(messages[messages.length-1].getContent().toString(), "This the first test for an authenticated mode");
		      Date expectedDate=java.util.Calendar.getInstance().getTime(); 
			   
		      LocalDate localexpectedDate = expectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      
		      LocalDate localSentDate = messages[messages.length-1].getSentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      LocalDate localReceivedDate = messages[messages.length-1].getReceivedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      
		      Period period1 = Period.between(localexpectedDate,localSentDate);
		      Period period2 = Period.between(localexpectedDate,localReceivedDate);
		      Assert.assertEquals(period1.getDays(),0);
		      Assert.assertEquals(period2.getDays(),0);

		 
		    } catch (Exception e) {
		      e.printStackTrace();
		      Assert.fail(e.getMessage());
		    }
		  }
	

}
