package graphic.menusParametreFenetre;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.activite.Album;
import control.activite.Titre;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuAlbum;
import graphic.menusDeuxiemeFenetre.MenuMusique;


/**
 *<b>MenuRenommer</b> est la classe qui permet à l'utilisateur de modifier les informations saisies précédemment
 *@author ATANGANA Daniel
 *@version 2.0
 **/

@SuppressWarnings("serial")
public class MenuRenommer extends JPanel{
	
	/**
	 *Déclaration de l'instance du menu de modification d'informations
	 **/
	
	private static MenuRenommer instance;
	
	/**
	 *Déclaration du menu utilisateur
	 **/
	
	private String login;
	
	/**
	 *Déclaration du nom modifié
	 **/
	
	private JTextField nouveauNom;
	
	/**
	 *Déclaration de l'album
	 **/
	
	private Album album;
	
	/**
	 *Déclaration du titre
	 **/
	
	private Titre titre;
	
	/**
	 *Déclaration de l'artiste
	 **/
	
	private Artiste artiste;
	
	/**
	 *Déclaration du texte de modification
	 **/
	
	private String textLabel;
	
	/**
	 *Permet de rendre effectives les modifications des informations de l'artiste
	 *@param login
	 *	Login utilisateur
	 *@param textLabel
	 *	Contenu zone de texte
	 *@param textField
	 *	Interfac de saisie texte
	 *@param album
	 *	Album artiste
	 *@param artiste
	 *	Artiste
	 *@param titre
	 *	Titre oeuvre artiste
	 **/
	private MenuRenommer(String login, String textLabel, String textField, Album album, Artiste artiste, Titre titre) {
		this.login = login;
		this.textLabel = textLabel;
		this.artiste = artiste;
		this.album = album;
		this.titre = titre;
		this.setLayout(new BorderLayout());
		JPanel etiquette = new JPanel(new GridLayout(1, 2, 30, 0));
		nouveauNom = new JTextField(textField);
		JLabel monTitre = new JLabel(textLabel);
		
		JButton monBoutton = new JButton("Valider");
		monBoutton.addActionListener(e -> valider());
		
		etiquette.add(monTitre);
		etiquette.add(nouveauNom);
		
		this.add(etiquette, BorderLayout.WEST);
		this.add(monBoutton, BorderLayout.EAST);
	}
	
	/**
	 *Permet de valider la modification de l'album
	 *@see ALbum
	 *@see personnesDejaInscrite
	 *@see MenuMusique
	 *@see MenuAlbum
	 **/
	
	private void valider() {
		if(textLabel.equals("Renommer Album")) {
			album.setTitre(nouveauNom.getText());
			personnesDejaInscrite.getInstance().sauvegarder();
			MenuMusique.getInstance(login).update();
			MenuAlbum.getInstance(login).update();
		}
	}
	
	
	/**
	 *Instance qui va permettre d'effectuer des modifications sur les contenus
	 *@param login
	 *	Login utilisateur
	 *@param textLabel
	 *	Contenu zone de texte
	 *@param textField
	 *	Interface de saisie texte
	 *@param album
	 *	Album artiste
	 *@param artiste
	 *	Artiste
	 *@param titre
	 *	Titre oeuvre artiste
	 *@return Menu d'informations modifiées
	 **/
	
	public static MenuRenommer getInstance(String login, String textLabel, String textField, Album album, Artiste artiste, Titre titre) {
		if (instance == null)
			instance = new MenuRenommer(login, textLabel, textField, album, artiste, titre);
		return instance;
	}
}
