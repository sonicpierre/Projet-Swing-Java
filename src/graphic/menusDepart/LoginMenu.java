package graphic.menusDepart;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.elementSauv.personnesDejaInscrite;
import graphic.fenetre.Fenetre;
import graphic.fenetre.FenetreLogin;


@SuppressWarnings("serial")
public class LoginMenu extends JPanel{
	
	private static LoginMenu instance;
	
	//On met ces variables en globale pour y avoir accés de partout et pouvoir valider la connexion
	JPanel menuLogin;
	JTextField login;
	JPasswordField passeword;
	

	private LoginMenu() {		
		menuLogin = new JPanel(new GridLayout(2,1, 0, 0));
		menuLogin.setBackground(new Color(200, 100, 100));
		menuLogin.add(InitialisationDuMenu());
		menuLogin.add(InitDesBouttons());
	}
	
	private JPanel InitialisationDuMenu() {
		JPanel mesEntre = new JPanel(new GridLayout(2,2,15,10));
		login = new JTextField("Login");
		passeword = new JPasswordField("Mot de Passe");

		JLabel loginTexte = new JLabel("Login :");
		loginTexte.setHorizontalAlignment(JLabel.CENTER);
		JLabel motDePasse = new JLabel("Mot de Passe :");
		motDePasse.setHorizontalAlignment(JLabel.CENTER);
		
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
		quitter.addActionListener((event)->quitter());
		valider.addActionListener((event)->valider());
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
	
	private void quitter() {
		FenetreLogin.getInstance().dispose();
		Fenetre.getInstance().dispose();
		System.exit(0);
	}
	
	private void valider() {
		System.out.println(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().size());
		String passewordTraduit =new String(passeword.getPassword());
		if(personnesDejaInscrite.getInstance().rechercher(login.getText(), passewordTraduit)) {
			FenetreLogin.getInstance().setVerouillage(false);
			FenetreLogin.getInstance().dispose();
		}
	}
	
}
