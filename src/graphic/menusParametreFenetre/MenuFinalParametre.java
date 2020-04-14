package graphic.menusParametreFenetre;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MenuFinalParametre extends JTabbedPane{

	private static MenuFinalParametre instance;
	
	String login;
	
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
