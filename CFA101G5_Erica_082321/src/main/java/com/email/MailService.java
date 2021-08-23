package com.email;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   // �]�w�ϥ�SSL�s�u�� Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

	     final String myGmail = "misstest654@gmail.com";
	     final String myGmail_password = "987misstest";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //�]�w�H�����D��  
		   message.setSubject(subject);
		   //�]�w�H�������e 
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("成功");
     }catch (MessagingException e){
	     System.out.println("失敗");
	     e.printStackTrace();
     }
   }
	
	 public static void main (String args[]){

      String to = "rely2021@gmail.com";
      
      String subject = "測試測試";
      
      String ch_name = "peter1";
      String passRandom = "111";
      String messageText = "Hello! " + ch_name + " ���԰O���K�X: " + passRandom + "\n"; 
       
      MailService mailService = new MailService();
      mailService.sendMail(to, subject, messageText);

   }


}
