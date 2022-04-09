package br.com.inventory.web;

import java.util.Properties;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.inventory.util.FacesUtil;

@RequestScoped
@Named("emailBean")
public class EmailBean {

	final String username = "adrianos@officer.com.br";
	final String password = "officer";

	public void enviar() {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.host", "EXCH01.officer.com.br");
		props.put("mail.smtp.port", "25");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("adrianos@officer.com.br"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("adrianos@officer.com.br"));
			/*message.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse("lcarlos@officer.com.br"));*/
			message.setSubject("Batimento de Saldo - Divergências");
			String msg = "Batimento de Saldo Officer <br><br>"
						 + "Testando envio de e-mail automático. <br><br>";
			message.setContent(msg, "text/html; charset=utf-8");
			
			Transport.send(message);
			
			FacesUtil.addSuccessMessage("Mensagem enviada com sucesso!");
			
		} catch (MessagingException e) {
			FacesUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
}
