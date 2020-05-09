package graphic.menusParametreFenetre;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 *<p><b>MenuFinalParametre</b> est la classe qui permet d'afficher les options
 *de navigation des onglets de la fenêtre.
 *@author VIRGAUX Pierre
 *</p>
 *@version 2.0
 **/

@SuppressWarnings("serial")
public class MenuFinalParametre extends JTabbedPane{
	
	/**
	 *Déclaration de l'instance du menu de paramètres
	 **/
	
	private static MenuFinalParametre instance;
	
	/**
	 *Déclaration du login utilisateur
	 **/
	
	private final String login;
	
	/**
	 *Permet la représentation des options du menu
	 *@param login
	 *	Identifiant utilisateur
	 **/

	private MenuFinalParametre(String login) {

		this.login = login;
		this.add("Mon profil", MenuProfilDescription.getInstance(login));
		this.add("Mes Préférences", new JPanel());
		this.add("Mon Compte", new MenuReparametrageDuCompte(login));
	}	
	
	/**
	 *Instance du menu final de paramètres
	 *@param login
	 *	Login utilisateur
	 *@return Menu de paramètres
	 **/
	
	public static MenuFinalParametre getInstance(String login) {
		if (instance == null)
			instance = new MenuFinalParametre(login);
		return instance;
	}
}
