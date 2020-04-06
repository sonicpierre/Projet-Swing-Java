package graphic.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class LoginMenu extends JPanel{
	
	private static LoginMenu instance;
	JPanel menuLogin;
	JTabbedPane mesOnglets;
	JTextField login;
	JLabel motDePasse;

	private LoginMenu() {
		mesOnglets = new JTabbedPane(JTabbedPane.LEFT);
		
		menuLogin = new JPanel(new GridLayout(2,1, 0, 0));
		menuLogin.setBackground(new Color(200, 100, 100));
		menuLogin.add(InitialisationDuMenu());
		menuLogin.add(InitDesBouttons());
		mesOnglets.add("Membre", menuLogin);
	}
	
	private JPanel InitialisationDuMenu() {
		JPanel mesEntre = new JPanel(new GridLayout(2,2,15,10));
		login = new JTextField("Login");

		JLabel loginTexte = new JLabel("Login :");
		loginTexte.setHorizontalAlignment(JLabel.CENTER);
		motDePasse = new JLabel("Mot de Passe :");
		motDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		JPasswordField passeword = new JPasswordField("Mot de Passe");
		passeword.setPreferredSize(new Dimension(50,100));
		//On ajoute au Panel les éléments pour saisir mot de pass et login.
		mesEntre.add(loginTexte);
		mesEntre.add(login);
		mesEntre.add(motDePasse);
		mesEntre.add(passeword);
		return mesEntre;
	}
	
	private JPanel InitDesBouttons() {
		JPanel mesBouttons = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 25));
		JButton valider = new JButton("Valider");
		JButton quitter = new JButton("Quitter");
		mesBouttons.add(valider);
		mesBouttons.add(quitter);
		return mesBouttons;
	}
	
	public static LoginMenu getInstance() {
		if (instance == null)
			instance = new LoginMenu();
		return instance;
	}

	public JPanel getMenuLogin() {
		return menuLogin;
	}

	public void setMenuLogin(JPanel menuLogin) {
		this.menuLogin = menuLogin;
	}

	public JTabbedPane getMesOnglets() {
		return mesOnglets;
	}

	public void setMesOnglets(JTabbedPane mesOnglets) {
		this.mesOnglets = mesOnglets;
	}
}
