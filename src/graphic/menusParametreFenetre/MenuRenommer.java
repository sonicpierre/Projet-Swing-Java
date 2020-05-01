package graphic.menusParametreFenetre;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.activite.Album;
import control.activite.Titre;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuAlbum;
import graphic.menusDeuxiemeFenetre.MenuMusique;

@SuppressWarnings("serial")
public class MenuRenommer extends JPanel{

	private static MenuRenommer instance;
	
	private String login;
	private JTextField nouveauNom;
	private Album album;
	private Titre titre;
	private Artiste artiste;
	private String textLabel;
	
	private MenuRenommer(String login, String textLabel, String textField, Album album, Artiste artiste, Titre titre) {
		this.login = login;
		this.textLabel = textLabel;
		this.artiste = artiste;
		this.album = album;
		this.titre = titre;
		this.setLayout(new BorderLayout());
		JPanel etiquette = new JPanel(new GridLayout(1, 2, 30, 0));
		nouveauNom = new JTextField(textField);
		JLabel monTitre = new JLabel(textLabel);
		
		JButton monBoutton = new JButton("Valider");
		monBoutton.addActionListener(e -> valider());
		
		etiquette.add(monTitre);
		etiquette.add(nouveauNom);
		
		this.add(etiquette, BorderLayout.WEST);
		this.add(monBoutton, BorderLayout.EAST);
	}
	
	
	private void valider() {
		if(textLabel.equals("Renommer Album")) {
			album.setTitre(nouveauNom.getText());
			personnesDejaInscrite.getInstance().sauvegarder();
			MenuMusique.getInstance(login).update();
			MenuAlbum.getInstance(login).update();
		}
	}
	
	
	
	public static MenuRenommer getInstance(String login, String textLabel, String textField, Album album, Artiste artiste, Titre titre) {
		if (instance == null)
			instance = new MenuRenommer(login, textLabel, textField, album, artiste, titre);
		return instance;
	}
}
