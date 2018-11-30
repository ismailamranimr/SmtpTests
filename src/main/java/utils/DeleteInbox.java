package utils;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

public class DeleteInbox {
	
	
	public void deleteAllMessages(EmailUtils email) throws MessagingException
	{
		
		
		
	  Message[] message = email.getAllMessages();
	  
	  for (int i = 0; i < message.length; i++) 
		  message[i].setFlag(Flags.Flag.DELETED, true);
	  
	  
	  
	Folder folderInbox = email.getFolder();
	  
	  boolean expunge = true;
      folderInbox.close(expunge);
}
	
}
