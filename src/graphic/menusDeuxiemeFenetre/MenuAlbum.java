package graphic.menusDeuxiemeFenetre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.activite.Album;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;

@SuppressWarnings("serial")
public class MenuAlbum extends JPanel{
	
	private static MenuAlbum instance;

	private String login;

	private MenuAlbum(String login) {
		this.login = login;
		constructionPanel();
	}
	
	private void constructionPanel() {
		int compteur = 0;
		for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
			for(Album monAlbum : monArtiste.getMaListeDeAlbums())
				compteur++;
		this.setLayout(new GridLayout(compteur%4 + 1, 4));
		for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
			for(Album monAlbum : monArtiste.getMaListeDeAlbums()) {
				this.add(constructionCase(monAlbum, monArtiste));
			}
				
	}
	
	private JPanel constructionCase(Album album, Artiste artiste) {
		JPanel maCase = new JPanel(new FlowLayout());
		ImageIcon monImage = new ImageIcon(new ImageIcon(album.getCheminVersImageAssocie()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
		JLabel maPhoto = new JLabel(monImage);
		
		JPanel lesLabels = new JPanel(new GridLayout(2,1));
		JLabel monTitre = new JLabel(album.getTitre());
		JLabel monAuteur = new JLabel(artiste.getNom() + " " + artiste.getPrenom());
		
		lesLabels.add(monTitre);
		lesLabels.add(monAuteur);
		
		maCase.add(maPhoto);
		maCase.add(lesLabels);
		
		return maCase;
	}
	
	public void update() {
		this.removeAll();
		this.constructionPanel();
		this.validate();
	}
	
	
	public static MenuAlbum getInstance(String login) {
		if (instance == null)
			instance = new MenuAlbum(login);
		return instance;
	}

}
