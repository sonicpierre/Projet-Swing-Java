package graphic.menusParametreFenetre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import control.elementSauv.personnesDejaInscrite;

@SuppressWarnings("serial")
public class MenuReparametrageDuCompte extends JPanel{
	
	private String login;
	private JPasswordField motPasse;
	private JPanel tempon1;
	private JPanel tempon2;
	
	public MenuReparametrageDuCompte(String login){
		this.login = login;
		String passewordTraduit = null;
		try {
			passewordTraduit = (String) personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getPasseword().getObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new GridLayout(6, 1));
		tempon1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		tempon2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		
		//Le code HTML fonctionne bien dans les JLabels.
		JLabel monUtilisateurActuel = new JLabel("<html><FONT color=\"#ff0000\" size = \"6\" face=\"Times New Roman\">Utilisateur actuel</FONT></html>");
		JLabel UtilisateurActuel = new JLabel("<html><FONT color=\"#5a98f7\" size = \"4\" face=\"Times New Roman\">" + login + "</FONT></html>");
		
		JLabel monMotDePasseAct = new JLabel("<html><FONT color=\"#ff0000\" size = \"6\" face=\"Times New Roman\">Mot de passe actuel</FONT>");
		JLabel MotPasseActuel = new JLabel("<html><FONT color=\"#5a98f7\" size = \"4\" face=\"Times New Roman\">" +passewordTraduit + "</FONT></html>");
		JButton changerMotDePasse = new JButton("Modifier");
		changerMotDePasse.addActionListener((event)->changerMotDePasse());
		

		
		this.add(monUtilisateurActuel);
		tempon1.add(UtilisateurActuel);
		this.add(tempon1);
		this.add(monMotDePasseAct);
		tempon2.add(MotPasseActuel);
		tempon2.add(changerMotDePasse);
		this.add(tempon2);
		
	}
	
	private void changerMotDePasse() {
		motPasse = new JPasswordField("MotDePasse");
		tempon2.removeAll();
		tempon2.add(new JLabel("Nouveau mot de passe :"));
		tempon2.add(motPasse);
		JButton validerMotDePasse = new JButton("valider");
		tempon2.add(validerMotDePasse);
		validerMotDePasse.addActionListener((event)->validerMotDePasse());
		tempon2.validate();
	}
	

	
	private void validerMotDePasse() {
		String passewordTraduit = new String(motPasse.getPassword());
		personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).setPasseword(passewordTraduit);
		personnesDejaInscrite.getInstance().sauvegarder();
		tempon2.removeAll();
		JLabel MotPasseActuel = new JLabel("<html><FONT color=\"#5a98f7\" size = \"4\" face=\"Times New Roman\">" +passewordTraduit + "</FONT></html>");
		JButton changerMotDePasse = new JButton("Modifier");
		changerMotDePasse.addActionListener((event)->changerMotDePasse());
		tempon2.add(MotPasseActuel);
		tempon2.add(changerMotDePasse);
		MenuFinalParametre.getInstance(login).setSelectedComponent(MenuProfilDescription.getInstance(login));
		MenuFinalParametre.getInstance(login).validate();
	}
	
}
