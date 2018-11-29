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

public class Test_EndtoEnd {
	
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
	 public void verifyReceivedEmail() {
		    try {
		      //TODO: Execute actions to send verification code to email
		    	   Message[] messages = receiverEmail.getAllMessages();
		  
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getFrom()), "sender4dsmtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.TO)), "test4dsmtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.CC)), null);
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.BCC)),null);
		      Assert.assertEquals(messages[messages.length-1].getSubject(), "Test EndToEnd");
		      //Assert.assertEquals(messages[messages.length-1].getContent().toString(), "This the first test for an authenticated mode");
		      
		      Date expectedDate=java.util.Calendar.getInstance().getTime(); 
		   
		      LocalDate localexpectedDate = expectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      
		      LocalDate localSentDate = messages[messages.length-1].getSentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      LocalDate localReceivedDate = messages[messages.length-1].getReceivedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      
		      Period period1 = Period.between(localexpectedDate,localSentDate);
		      Period period2 = Period.between(localexpectedDate,localReceivedDate);
		      Assert.assertEquals(period1.getDays(),0);
		      Assert.assertEquals(period2.getDays(),0);
		      
		     /* System.out.println("********************************************** Current date *******************************************************");
		      System.out.println(expectedDate);
		      System.out.println("Sent: " + messages[messages.length-1].getSentDate());
		      System.out.println("Received: " + messages[messages.length-1].getReceivedDate());
		      System.out.println(period1.getDays());
		      System.out.println(period2.getDays());*/
		      
		      
		      
		   /*     System.out.println("Hello");
		 	  int i = receiverEmail.getNumberOfMessages();
		      System.out.println(String.valueOf(i));
		      Message m =  receiverEmail.getMessageByIndex(i);
		      System.out.println("Content type: "+m.getContentType());
		      Address [] addresses = m.getAllRecipients();
		      System.out.println("Recipients: "+addresses.toString());
		      System.out.println("From: "+m.getFrom());
		      System.out.println("Subject: "+m.getSubject());
		      System.out.println("****************Boucle de messages:****************\n ");
		      for (i = 0; i < messages.length; i++) {
		        System.out.println("Message " + (i + 1));

		        System.out.println("From: " + InternetAddress.toString(messages[i].getFrom()));
		        System.out.println("Reply-to: " + InternetAddress.toString(messages[i].getReplyTo()));
		        String to = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO));
		        System.out.println("To: " + to);
		        String cc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.CC));
		        System.out.println("Cc: " + cc);
		        String bcc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.BCC));
		        System.out.println("Bcc: " + bcc);
		        System.out.println("Subject: " + messages[i].getSubject());
		        System.out.println("Sent: " + messages[i].getSentDate());
		        System.out.println("Received: " + messages[i].getReceivedDate());
		        System.out.println("Content: " + messages[i].getContent().toString());
		      }
		      */
		   /*   InternetAddress.toString(messages[messages.length-1].getFrom())
		      InternetAddress.toString(messages[messages.length-1].getReplyTo()
		    		  InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.TO))
		    		  InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.CC))
		    		  InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.BCC))
		    		  messages[messages.length-1].getSubject()
		      */
		      
		  
		 
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
		  
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getFrom()), "sender4dsmtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.TO)), "test4dsmtp@gmail.com");
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.CC)), null);
		      Assert.assertEquals(InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.BCC)),null);
		      Assert.assertEquals(messages[messages.length-1].getSubject(), "Test EndToEnd");
		    //  Assert.assertEquals(messages[messages.length-1].getContent().toString(), "This the first test for an authenticated mode");
		      Date expectedDate=java.util.Calendar.getInstance().getTime(); 
			   
		      LocalDate localexpectedDate = expectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      
		      LocalDate localSentDate = messages[messages.length-1].getSentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      LocalDate localReceivedDate = messages[messages.length-1].getReceivedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		      
		      Period period1 = Period.between(localexpectedDate,localSentDate);
		      Period period2 = Period.between(localexpectedDate,localReceivedDate);
		      Assert.assertEquals(period1.getDays(),0);
		      Assert.assertEquals(period2.getDays(),0);
		   
		      
		      
		   /*    System.out.println("Hello");
		 	  int i = senderEmail.getNumberOfMessages();
		      System.out.println(String.valueOf(i));
		      Message m =  senderEmail.getMessageByIndex(i);
		      System.out.println("Content type: "+m.getContentType());
		      Address [] addresses = m.getAllRecipients();
		      System.out.println("Recipients: "+addresses.toString());
		      System.out.println("From: "+m.getFrom());
		      System.out.println("Subject: "+m.getSubject());
		      System.out.println("****************Boucle de messages:****************\n ");
		      for (i = 0; i < messages.length; i++) {
		        System.out.println("Message " + (i + 1));

		        System.out.println("From: " + InternetAddress.toString(messages[i].getFrom()));
		        System.out.println("Reply-to: " + InternetAddress.toString(messages[i].getReplyTo()));
		        String to = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO));
		        System.out.println("To: " + to);
		        String cc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.CC));
		        System.out.println("Cc: " + cc);
		        String bcc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.BCC));
		        System.out.println("Bcc: " + bcc);
		        System.out.println("Subject: " + messages[i].getSubject());
		        System.out.println("Sent: " + messages[i].getSentDate());
		        System.out.println("Received: " + messages[i].getReceivedDate());
		        System.out.println("Content: " + messages[i].getContent().toString());
		      }*/
		      
		   /*   InternetAddress.toString(messages[messages.length-1].getFrom())
		      InternetAddress.toString(messages[messages.length-1].getReplyTo()
		    		  InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.TO))
		    		  InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.CC))
		    		  InternetAddress.toString(messages[messages.length-1].getRecipients(Message.RecipientType.BCC))
		    		  messages[messages.length-1].getSubject()
		      */
		      
		  
		 
		    } catch (Exception e) {
		      e.printStackTrace();
		      Assert.fail(e.getMessage());
		    }
		  }
}
