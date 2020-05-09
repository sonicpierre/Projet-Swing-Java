package graphic.menusParametreFenetre;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.personne.Artiste;


/**
 *<b>MenuReparametrageDuCompte</b> est la classe qui permet de modifier les parameètres du compte
 *@author ATANGANA Daniel 
 *@version 2.0
 **/

@SuppressWarnings("serial")
public class MenuReparametrageDuCompte extends JPanel{
	
	/**
	 *Déclaration du login de l'artiste
	 **/
	
	private String login;
	
	/**
	 *Déclaration de l'artiste
	 **/
	
	private Artiste artiste;
	
	/**
	 *Décalaration d'une fenetre de transition
	 **/
	
	private JPanel tempon1;
	
	/**
	 *Permet de générer le menu de reparamètrage du compte utilisateur
	 *@param login	
	 *	Login utilisateur
	 **/
	
	public MenuReparametrageDuCompte(String login){
		this.login = login;
		this.setArtiste(null);
		this.setLayout(new GridLayout(3, 1));
		tempon1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		
		//Le code HTML fonctionne bien dans les JLabels.
		JLabel monUtilisateurActuel = new JLabel("<html><FONT color=\"#ff0000\" size = \"6\" face=\"Times New Roman\">Utilisateur actuel</FONT></html>");
		JLabel UtilisateurActuel = new JLabel("<html><FONT color=\"#5a98f7\" size = \"4\" face=\"Times New Roman\">" + login + "</FONT></html>");
		

		
		this.add(monUtilisateurActuel);
		tempon1.add(UtilisateurActuel);
		this.add(tempon1);
		
	}
	
	/**
	 *Récupère l'artiste 
	 *@return Artiste
	 **/
	
	public Artiste getArtiste() {
		return artiste;
	}
	
	/**
	 *Initialisation de l'artiste
	 *@param artiste
	 *	Artiste
	 **/
	
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	
}
