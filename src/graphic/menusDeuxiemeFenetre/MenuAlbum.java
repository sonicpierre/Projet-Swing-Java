package graphic.menusDeuxiemeFenetre;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.elementSauv.personnesDejaInscrite;
import control.musique.Album;
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
				ImageIcon monImage = new ImageIcon(new ImageIcon(monAlbum.getCheminVersImageAssocie()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
				JLabel maPhoto = new JLabel(monImage);
				this.add(maPhoto);
			}
				
	
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
