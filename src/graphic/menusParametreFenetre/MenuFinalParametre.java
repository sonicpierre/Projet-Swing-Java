package graphic.menusParametreFenetre;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MenuFinalParametre extends JTabbedPane{

	String login;
	
	public MenuFinalParametre(String login) {
		this.login = login;
		this.add("Mon profil", MenuProfilDescription.getInstance(login));
		this.add("Mes Préférences", new JPanel());
		this.add("Mon Compte", new JPanel());
	}	
	
}
