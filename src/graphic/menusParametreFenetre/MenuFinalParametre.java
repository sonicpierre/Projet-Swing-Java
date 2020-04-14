package graphic.menusParametreFenetre;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 *<p><b>MenuFinalParametre</b> est la classe qui permet d'afficher les options
 *de navigation des onglets de la fenêtre.
 *@author VIRGAUX Pierre
 *</p>
 **/
@SuppressWarnings("serial")
public class MenuFinalParametre extends JTabbedPane{

	String login;
	
	/**
	 *Permet la représentation des options du menu
	 *@param login
	 *	Identifiant utilisateur
	 **/
	
	public MenuFinalParametre(String login) {
		this.login = login;
		this.add("Mon profil", MenuProfilDescription.getInstance(login));
		this.add("Mes Préférences", new JPanel());
		this.add("Mon Compte", new JPanel());
	}	
	
}
