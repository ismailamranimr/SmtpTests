package tests;

import javax.mail.MessagingException;

import utils.DeleteInbox;
import utils.EmailUtils;

public class testing {

	public static void main(String[] args) throws MessagingException {
		// TODO Auto-generated method stub

		EmailUtils mail = new EmailUtils("test4dsmtp@gmail.com", "4d11ismail", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
		
		DeleteInbox delete = new DeleteInbox();
		delete.deleteAllMessages(mail);
		
		
	}

}
