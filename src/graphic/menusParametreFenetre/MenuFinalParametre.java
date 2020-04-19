package graphic.menusParametreFenetre;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import control.personne.Artiste;


/**
 *<p><b>MenuFinalParametre</b> est la classe qui permet d'afficher les options
 *de navigation des onglets de la fenêtre.
 *@author VIRGAUX Pierre
 *</p>
 **/
@SuppressWarnings("serial")
public class MenuFinalParametre extends JTabbedPane{

	private static MenuFinalParametre instance;
	
	private String login;
	
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
	
	public static MenuFinalParametre getInstance(String login) {
		if (instance == null)
			instance = new MenuFinalParametre(login);
		return instance;
	}
}
