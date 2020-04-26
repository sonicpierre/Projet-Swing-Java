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
import javax.swing.JOptionPane;

/**
 *<p>
 *<b>SenderMail</b> est la classe qui permet à l'utilisateur d'envoyer des e-mails
 *à des contacts depuis une fenetre d'envoie. Son fonctionne se fait comme suit : 
 *<ul>
 *<li>Définition du protocole permettant la connexion aux serveurs de Google Mail (Gmail)</li>
 *<li>Définition des identifiants du compte d'envoie d'artistak</li>
 *<li>Préparation et envoie du message</li>
 *</ul>
 *</p>
 *@author VIRGAUX Pierre 
 **/

public class SenderMail {
	public static void sendMail(String recepient, String sujet, String messageAEnvoyer) throws MessagingException {
		System.out.println("Preparation de l'envoie du message");
		
		/**<p>Définition des propriétés SMTP. SMTP étant l'acronyme de Simple Mail Transport Protocol. Ce protocole défini par la recommandation RFC 821 permet
		 *l'envoi de mails vers un serveur de mails qui supporte ce protocole.</p>
		 *<b>NB : </b> Ce protocole nécessite l'utilisation de la librairie Java.mail.jar
		 **/
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	
		String myAccountEmail = "artistaketexe@gmail.com";
		String password = "artistak";
		
		/**
		 *Authentification de la session de connexion
		 *@param properties
		 *	Propriétés du protocole
		 *@param Autheticator
		 *	Fonction de vérification
		 *@return Authetification de la personne via son e-mail et mot de passe
		 **/
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		/**
		 *Permet d'envoyer le message
		 *@param session
		 *	Session de connexion
		 *@param myAccountEmail
		 *	Adresse e-mail
		 *@param recepient
		 *	Destinataire
		 *@param sujet
		 *	Objet de l'e-mail
		 *@param messageAEnvoyer
		 *	Contenu du message
		 **/
		
		Message message = prepareMessage(session, myAccountEmail, recepient, sujet, messageAEnvoyer);
		Transport.send(message);//ENVOIE MESSAGE 
		System.out.println("Message envoyé !!");
		MenuDeMail.getInstance().getMessage().setText("");
		MenuDeMail.getInstance().getAdresseMailRentre().setText("");
		MenuDeMail.getInstance().getObjetEntre().setText("");
		FenetreMail.getInstance().dispose();
	}
	
	/**
	 *Définit les paramètres d'envoie du message
	 *@param session
	 *	Session de connexion
	 *@param MyAccount
	 *	L'e-mail d'envoie
	 *@param recepient
	 *	Destinataire
	 *@param sujet
	 *	Objet du message
	 *@param messageAEnvoyer
	 *	Contenu du message
	 **/
	
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
