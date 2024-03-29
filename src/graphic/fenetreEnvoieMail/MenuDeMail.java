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

import control.personne.Artiste;


/**
 *<b>MenuDeMail</b> est la classe qui permet de gérer les espaces
 *dédiés à chaque champ de saisie dans la fenetre d'envoie.
 *@author VIRGAUX Pierre
 *@version 2.0
 **/


@SuppressWarnings("serial")
public class MenuDeMail extends JPanel{
	
	/**
	 *Déclaration de l'instacnce du menu e-mail
	 **/
	
	private static MenuDeMail instance;
	
	/**
	 *Décalaration de l'instance du menu e-mail
	 **/
	
	private JPanel menuMail;
	
	/**
	 *Déclaration des données d'envoie
	 **/
	
	private JTextField adresseMailRentre, objetEntre;
	
	/**
	 *Déclaration de la zone de saisie message
	 **/
	
	private JTextArea message;
	
	/**
	 *Déclaration de l'artiste
	 **/
	
	private Artiste artiste;
	
	/**
	 *Espace de menu e-mail
	 **/
	
	private MenuDeMail() {
		menuMail = new JPanel(new BorderLayout());
		menuMail.add(parametre(), BorderLayout.NORTH);
		menuMail.add(zoneMessage(), BorderLayout.CENTER);
		menuMail.add(bouttonEnvoi(), BorderLayout.SOUTH);
	}
	
	/**
	 *Espace de choix de paramètrage
	 **/
	
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
	
	/**
	 *Création de l'espace de zone de texte
	 *@return Message saisie
	 **/
	
	private JTextArea zoneMessage() {
		message = new JTextArea(100, 200);
		//Choix de couleur et de police à terminer...
		return message;
	}
	
	/**
	 *Permet de définir l'action du bouton d'envoie d'e-mail
	 *@see SenderMail
	 *@return Bouton d'envoie
	 **/
	
	private JPanel bouttonEnvoi() {
		JPanel panelBoutton = new JPanel(new FlowLayout());
		JButton envoyer = new JButton("Envoyer");
		envoyer.addActionListener((event)->envoyerMail());//PENSER AU @SENDERMAIL
		panelBoutton.add(envoyer);
		return panelBoutton;
	}
	
	/**
	 *Récupère le menu d'e-mail
	 *@return Le menu e-mail
	 **/
	
	public JPanel getMenuMail() {
		return menuMail;
	}
	
	/**
	 *Initialise le menu e-mail
	 *@param menuMail
	 *	Un panel de menu e-mail
	 **/
	
	public void setMenuMail(JPanel menuMail) {
		this.menuMail = menuMail;
	}
	
	/**
	 *Fonction d'nevoie e-mail avec les zones de champs de saisie qui sont des textes
	 *@see SenderMail
	 **/
	
	public void envoyerMail() {
		try {
			SenderMail.sendMail(adresseMailRentre.getText(), objetEntre.getText(), message.getText());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *Instanciation de la fenetre de menu e-mail
	 *@return Fenetre de menu e-mail
	 **/
	
	public static MenuDeMail getInstance() {
		if (instance == null)
			instance = new MenuDeMail();
		return instance;
	}
	
	/**
	 *Récupère l'adresse e-mail saisie
	 *@return
	 *	E-mail saisie
	 **/
	
	public JTextField getAdresseMailRentre() {
		return adresseMailRentre;
	}
	
	/**
	 *Initialisation de l'e-mail
	 *@param adresseMailRentre
	 *	E-mail saisie
	 **/
	
	public void setAdresseMailRentre(JTextField adresseMailRentre) {
		this.adresseMailRentre = adresseMailRentre;
	}
	
	/**
	 *Récupération du message e-mail
	 *@return E-mail
	 **/
	
	public JTextArea getMessage() {
		return message;
	}
	
	/**
	 *Initialise l'espace e-mail
	 *@return Saisie utilisateur
	 **/
	
	public JTextField getObjetEntre() {
		return objetEntre;
	}
	
	/**
	 *Initialise l'objet entré
	 *@param objetEntre
	 *	Objet saisi
	 **/
	
	public void setObjetEntre(JTextField objetEntre) {
		this.objetEntre = objetEntre;
	}

	/**
	 *Définition du message de l'e-mail
	 *@param message dans une zone de texte
	 **/
	
	public void setMessage(JTextArea message) {
		this.message = message;
	}
	
	/**
	 *Récupère l'artiste
	 *@return Artiste
	 **/
	
	public Artiste getArtiste() {
		return artiste;
	}
	
	/**
	 *Initialise l'artiste
	 *@param artiste
	 *	Artiste
	 **/
	
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	
	
}
