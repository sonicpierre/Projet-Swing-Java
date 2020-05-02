package graphic.menusDeuxiemeFenetre;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import control.BDD.Modification;
import control.activite.Representation;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetre.FenetreFond;

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
						this.add(constructionCase(artiste, maRepresentation));
		} else {
			compteur = artiste.getMaListeDeRepresentations().size() + 1;
			this.setLayout(new GridLayout(compteur, 1));

			if(artiste.getType().equals("Acteur") || artiste.getType().equals("Comedien"))
				for(Representation maRepresentation : artiste.getMaListeDeRepresentations())
					this.add(constructionCase(artiste, maRepresentation));
		}
	}
	
	private JPanel constructionCase(Artiste artiste, Representation marepres) {
		JPanel constructionCase = new JPanel(new FlowLayout());
		ImageIcon monImage = new ImageIcon(new ImageIcon(marepres.getCheminVersImage()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
		JLabel maPhoto = new JLabel(monImage);
		
		if(artiste != null) {
			maPhoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			JPopupMenu maPopup = new JPopupMenu();
			JMenuItem supprimer = new JMenuItem("Supprimer");
			JMenuItem changerImage = new JMenuItem("Changer imager");
			
			supprimer.addActionListener(e -> supprimerRepres(artiste, marepres));
			maPopup.add(supprimer);
			maPopup.add(changerImage);
			maPhoto.setComponentPopupMenu(maPopup);
		}
		constructionCase.add(maPhoto);
		constructionCase.add(constructionLabel(marepres));
		
		return constructionCase;
	}
	
	private JPanel constructionLabel(Representation marepres) {
		JPanel lesLabels = new JPanel(new GridLayout(3,2));
		JLabel labelTitre = new JLabel(marepres.getTitre());
		JLabel labelDuree = new JLabel(marepres.getDuree());
		JLabel labelDate = new JLabel(marepres.getDate().toString());
		
		lesLabels.add(labelTitre);
		lesLabels.add(labelDuree);
		lesLabels.add(labelDate);
		
		return lesLabels;
	}
	
	private void supprimerRepres(Artiste monArtiste, Representation maRepres) {
		personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(monArtiste).getMaListeDeRepresentations().remove(maRepres);
		personnesDejaInscrite.getInstance().sauvegarder();
		if(maRepres.getType().equals("Film"))
			Modification.getInstance().supprimerFilm(maRepres.hashCode());
		if(maRepres.getType().equals("Comedie"))
			Modification.getInstance().supprimerSpectacle(maRepres.hashCode());
		FenetreFond.getInstance().retourEtatInitial(login);
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
