package graphic.menusDeuxiemeFenetre;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.activite.Representation;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;

@SuppressWarnings("serial")
public class MenuRepresentation extends JPanel{
	
	private static MenuRepresentation instance;
	private Artiste artiste;
	private String login;
	
	public MenuRepresentation(String login) {
		this.login = login;
		constructionPanel();
	}
	
	private void constructionPanel() {
		int compteur = 0;
		if(artiste == null) {
			for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
				if(monArtiste.getType().equals("Acteur") || monArtiste.getType().equals("Comedien"))
					compteur += monArtiste.getMaListeDeRepresentations().size() + 1;
			
			this.setLayout(new GridLayout(compteur, 1));
			
			for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
				if(monArtiste.getType().equals("Acteur") || monArtiste.getType().equals("Comedien"))
					for(Representation maRepresentation : monArtiste.getMaListeDeRepresentations())
						this.add(new JLabel(maRepresentation.getTitre()));
		} else {
			compteur = artiste.getMaListeDeRepresentations().size() + 1;
			this.setLayout(new GridLayout(compteur, 1));

			if(artiste.getType().equals("Acteur") || artiste.getType().equals("Comedien"))
				for(Representation maRepresentation : artiste.getMaListeDeRepresentations())
					this.add(new JLabel(maRepresentation.getTitre()));
		}
	}
	
	public void update() {
		this.removeAll();
		constructionPanel();
		this.validate();
	}
	
	
	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public static MenuRepresentation getInstance(String login) {
		if (instance == null)
			instance = new MenuRepresentation(login);
		return instance;
	}
	
}
