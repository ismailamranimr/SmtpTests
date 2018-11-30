package utils;

import java.io.File;
import java.io.IOException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;

public class HandleAttachments {
	
	
	 public void downloadAttachments(EmailUtils email, String typeofMail) throws MessagingException, IOException {
			String saveDirectory;
			email.setSaveDirectory("src/test/resources/attachments/"+typeofMail);
			saveDirectory= email.getSaveDirectory();
			 
		   
		            // fetches new messages from server
		            
		            Message[] messages = email.getAllMessages();
		            Message message = messages[messages.length-1];
		    
		  
		   
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
		              // System.out.println("Message # :");
		              /*  System.out.println("\t From: " + from);
		                System.out.println("\t Subject: " + subject);
		                System.out.println("\t Sent Date: " + sentDate);
		                System.out.println("\t Message: " + messageContent);
		                System.out.println("\t Attachments: " + attachFiles);*/
		          
			 
			 
		 }
		 
	
	

}
