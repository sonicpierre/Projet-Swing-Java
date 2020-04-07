package graphic.menusDepart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import graphic.fenetre.Fenetre;
import graphic.fenetre.FenetreLogin;

@SuppressWarnings("serial")
public class CreerCompte extends JPanel{

	private static CreerCompte instance;
	
	//Elements dont on a besoin de partout... Voilà voilà
	JPanel menuCreation;
	JTextField login;
	JPasswordField passeword;
	JPasswordField confirmedPasseword;
	JTextField adresseMail;
	
	private CreerCompte() {
		menuCreation = new JPanel(new BorderLayout());
		menuCreation.setBackground(new Color(200, 100, 100));
		menuCreation.add(InitialisationDuMenu(), BorderLayout.CENTER);
		menuCreation.add(InitDesBouttons(), BorderLayout.SOUTH);
	}
	
	
	private JPanel InitialisationDuMenu() {
		JPanel mesEntre = new JPanel(new GridLayout(9,2,25,10));
		login = new JTextField("Login");
		confirmedPasseword = new JPasswordField("Mot de Passe");
		adresseMail = new JTextField("...@gmail.com");
		passeword = new JPasswordField("Mot de Passe");
		
		JLabel loginTexte = new JLabel("Login :");
		loginTexte.setHorizontalAlignment(JLabel.CENTER);
		JLabel motDePasse = new JLabel("Mot de Passe :");
		motDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel addresseMail = new JLabel("Adresse mail :");
		addresseMail.setHorizontalAlignment(JLabel.CENTER);
		JLabel confirmedMotDePasse = new JLabel("Confirmer Mot de Passe :");
		confirmedMotDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel choixCategorie = new JPanel(new GridLayout(1,3));
		JCheckBox checkArtiste = new JCheckBox("Artiste");
		JCheckBox checkChanteur = new JCheckBox("Chanteur");
		JCheckBox checkDanseur = new JCheckBox("Danseur");
		choixCategorie.add(checkArtiste);
		choixCategorie.add(checkChanteur);
		choixCategorie.add(checkDanseur);
		
		//On ajoute au Panel les éléments pour saisir mot de pass et login.
		mesEntre.add(loginTexte);
		mesEntre.add(login);
		mesEntre.add(motDePasse);
		mesEntre.add(passeword);
		mesEntre.add(confirmedMotDePasse);
		mesEntre.add(confirmedPasseword);
		mesEntre.add(addresseMail);
		mesEntre.add(adresseMail);
		mesEntre.add(choixCategorie);
		
		return mesEntre;
	}
	
	private JPanel InitDesBouttons() {
		JPanel mesBouttons = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 25));
		JButton valider = new JButton("Valider");
		JButton quitter = new JButton("Quitter");
		//On ajoute un listener sur le boutton
		quitter.addActionListener((event)->quitter());
		mesBouttons.add(valider);
		mesBouttons.add(quitter);
		return mesBouttons;
	}
	
	public static CreerCompte getInstance() {
		if (instance == null)
			instance = new CreerCompte();
		return instance;
	}


	public JPanel getMenuCreation() {
		return menuCreation;
	}


	public void setMenuCreation(JPanel menuCreation) {
		this.menuCreation = menuCreation;
	}
	
	//Permet de quitter quand on appuie sur le boutton
	private void quitter() {
		FenetreLogin.getInstance().dispose();
		Fenetre.getInstance().dispose();
		System.exit(0);
	}
	
}
