package graphic.fenetreEnvoieMail;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SenderMail {
	public static void sendMail(String recepient, String sujet, String messageAEnvoyer) throws MessagingException {
		System.out.println("Preparation de l'envoie du message");
		//REFERENCES AUX PROPIETES PERMETTANT DE NOUS CONNECTER A GMAIL => UTILISATION DE LA LIBRAIRIE JAVAS.MAIL.JAR
		//SMTP est l'acronyme de Simple Mail Transport Protocol. Ce protocole défini par la recommandation RFC 821 permet l'envoi de mails vers un serveur de mails qui supporte ce protocole.
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");//SMTP PROTOCOLE EN RESEAU
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	
		String myAccountEmail = "artistaketexe@gmail.com";
		String password = "artistak";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		Message message = prepareMessage(session, myAccountEmail, recepient, sujet, messageAEnvoyer);
		Transport.send(message);//ENVOIE MESSAGE 
		System.out.println("Message envoyé !!");
	}
	
	private static Message prepareMessage(Session session, String MyAcount, String recepient, String sujet, String messageAEnvoyer) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MyAcount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject(sujet);
			message.setText(messageAEnvoyer);
			return message;
		} catch (Exception ex) {
			Logger.getLogger(SenderMail.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
		
	}
}
