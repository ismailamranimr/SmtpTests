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
	 public void downloadAttachmentsReceiver() {
		String saveDirectory;
		receiverEmail.setSaveDirectory("/src/test/resources/attachments/receiver");
		saveDirectory= receiverEmail.getSaveDirectory();
		 
	   
	            // fetches new messages from server
	            
	            Message[] messages = receiverEmail.getAllMessages();
	            Message message = messages[messages.length-1];
	    
	  
	           Address[] fromAddress = message.getFrom();
	           String from = fromAddress[0].toString();
	           String subject = message.getSubject();
	           String sentDate = message.getSentDate().toString();
	           String contentType = message.getContentType();
	           String messageContent = "";
	 
	                // store attachment file name, separated by comma
	                String attachFiles = "";
	 
	                if (contentType.contains("multipart")) {
	                    // content may contain attachments
	                    Multipart multiPart = (Multipart) message.getContent();
	                    int numberOfParts = multiPart.getCount();
	                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
	                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
	                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	                            // this part is attachment
	                            String fileName = part.getFileName();
	                            attachFiles += fileName + ", ";
	                            part.saveFile(saveDirectory + File.separator + fileName);
	                        } else {
	                            // this part may be the message content
	                            messageContent = part.getContent().toString();
	                        }
	                    }
	 
	                    if (attachFiles.length() > 1) {
	                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
	                    }
	                } else if (contentType.contains("text/plain")
	                        || contentType.contains("text/html")) {
	                    Object content = message.getContent();
	                    if (content != null) {
	                        messageContent = content.toString();
	                    }
	                }
	 
	                // print out details of each message
	                System.out.println("Message #" + (i + 1) + ":");
	              /*  System.out.println("\t From: " + from);
	                System.out.println("\t Subject: " + subject);
	                System.out.println("\t Sent Date: " + sentDate);
	                System.out.println("\t Message: " + messageContent);
	                System.out.println("\t Attachments: " + attachFiles);*/
	          
		 
		 
	 }
	 
	
	
	

}
