package com.bridgelabz.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public class MailUtil {
	
	@Autowired
	 private SimpleMailMessage mailMessage;
	
	@Autowired
	private	MailSender mailSender;
	
	public  void sendMail(String from,String to,String msg,String subject) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(msg);
		try {
			mailSender.send(mailMessage);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
