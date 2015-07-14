package com.liqiang.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {

	
	public void send(String to,String content,String title)
	{
				// of course you would use DI in any real-world cases
				JavaMailSenderImpl sender = new JavaMailSenderImpl();
				sender.setHost("smtp.163.com");
				sender.setUsername("liqiang0315");
				sender.setPassword("liqiang!@#");
				MimeMessage message = sender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);
				try {
					helper.setTo(to);
					helper.setText(content);
					helper.setSubject(title);
					helper.setFrom("liqiang0315@163.com");
					helper.setCc("17701300200@189.cn");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sender.send(message);
	}
	
	
	
	public static void main(String[] args) throws Exception {

		System.setProperty("java.net.preferIPv4Stack", "true");
		while(true)
		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					new MailUtil().send("liq4i@icloud.com","程序停止了！","程序停止了！");
					System.out.println("send ....");
				}
			}).start();;
			
			Thread.sleep(3000);
		}
		
		
		
		
		
		
		
	}

}
