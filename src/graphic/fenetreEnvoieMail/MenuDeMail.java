package graphic.fenetreEnvoieMail;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MenuDeMail extends JPanel{
	
	private static MenuDeMail instance;
	
	private JPanel menuMail;
	private JTextField adresseMailRentre;
	private JTextField objetEntre;
	private JTextArea message;
	
	private MenuDeMail() {
		menuMail = new JPanel(new BorderLayout());
		menuMail.add(parametre(), BorderLayout.NORTH);
		menuMail.add(zoneMessage(), BorderLayout.CENTER);
		menuMail.add(bouttonEnvoi(), BorderLayout.SOUTH);
	}
	
	private JPanel parametre() {
		JPanel parametre = new JPanel(new GridLayout(2,1));
		JPanel parametreTampon1 = new JPanel(new BorderLayout());
		JPanel parametreTampon2 = new JPanel(new BorderLayout());

		adresseMailRentre = new JTextField();
		JLabel destinataire = new JLabel("Destinataire :", JLabel.LEFT);
		
		parametreTampon1.add(destinataire, BorderLayout.WEST);
		parametreTampon1.add(adresseMailRentre, BorderLayout.CENTER);
		parametre.add(parametreTampon1);
		
		objetEntre = new JTextField();
		JLabel objet = new JLabel("Objet :", JLabel.LEFT);
		
		parametreTampon2.add(objet, BorderLayout.WEST);
		parametreTampon2.add(objetEntre, BorderLayout.CENTER);
		parametre.add(parametreTampon2);
		
		return parametre;
	}
	
	private JTextArea zoneMessage() {
		message = new JTextArea(100, 200);
		//Choix de couleur et de police à terminer...
		return message;
	}

	private JPanel bouttonEnvoi() {
		JPanel panelBoutton = new JPanel(new FlowLayout());
		JButton envoyer = new JButton("Envoyer");
		envoyer.addActionListener((event)->envoyerMail());//PENSER AU @SENDERMAIL
		panelBoutton.add(envoyer);
		return panelBoutton;
	}

	public JPanel getMenuMail() {
		return menuMail;
	}

	public void setMenuMail(JPanel menuMail) {
		this.menuMail = menuMail;
	}
	
	public void envoyerMail() {
		try {
			SenderMail.sendMail(adresseMailRentre.getText(), objetEntre.getText(), message.getText());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MenuDeMail getInstance() {
		if (instance == null)
			instance = new MenuDeMail();
		return instance;
	}

	public JTextField getAdresseMailRentre() {
		return adresseMailRentre;
	}

	public void setAdresseMailRentre(JTextField adresseMailRentre) {
		this.adresseMailRentre = adresseMailRentre;
	}

	public JTextArea getMessage() {
		return message;
	}

	public void setMessage(JTextArea message) {
		this.message = message;
	}

	
}
