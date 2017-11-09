/*package com.bridgelabz.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailServiceUtil {
	
	@Autowired
	static SimpleMailMessage mailMessage;
	
	@Autowired
	static MailSender mailSender;
	
	public static void sendMail(String from,String to,String msg,String subject) {
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(msg);
		mailSender.send(mailMessage);
	}

}
*/