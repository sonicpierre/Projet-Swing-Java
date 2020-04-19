package graphic.menusParametreFenetre;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.personne.Artiste;

@SuppressWarnings("serial")
public class MenuReparametrageDuCompte extends JPanel{
	
	private String login;
	private Artiste artiste;
	private JPanel tempon1;
	
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

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	
}
