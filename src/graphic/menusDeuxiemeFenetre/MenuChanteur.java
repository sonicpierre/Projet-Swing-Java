package graphic.menusDeuxiemeFenetre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.elementSauv.personnesDejaInscrite;
import control.musique.Album;
import control.musique.Lecteur;
import control.musique.PisteAudio;
import control.musique.Titre;

@SuppressWarnings("serial")
public class MenuChanteur extends JScrollPane{

	private static MenuChanteur instance;
	private JPanel menuFinal;
	private String login;
	private Map<Titre, JPanel> titreAssociePlayer;
	//A chaque musique sont associé des bouttons :) 
	
	private MenuChanteur(String login) {
		this.login = login;
		this.titreAssociePlayer = new HashMap<Titre, JPanel>();
		constructionDuPanel();
		
		//On utilise ça pour les JScrollPan pour ajouter à la place de add un JScrollPane ne peut gérer qu'un seul élément à la foi
		this.setViewportView(menuFinal);
		
	}
	
	private void constructionDuPanel() {
		int nombreTotalDeTitre = 0;
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums())
			nombreTotalDeTitre+= monAlbum.getChansonsDelAlbum().size();
		menuFinal = new JPanel(new GridLayout(nombreTotalDeTitre, 2));
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums()) {
			for(Titre monTitre : monAlbum.getChansonsDelAlbum()) {
				JPanel temponConstruction = constructionEtiquette(monTitre.getTitre(), monAlbum.getTitre());
				menuFinal.add(temponConstruction);
				JPanel bouttons = lesBouttonsDeControle(monTitre);
				menuFinal.add(bouttons);
				
				ImageIcon monImage = new ImageIcon(new ImageIcon(monAlbum.getCheminVersImageAssocie()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				JLabel imageCorespondante = new JLabel(monImage);
				menuFinal.add(imageCorespondante);
				
				titreAssociePlayer.put(monTitre, bouttons);
			}
		}
	}
	
	
	private JPanel constructionEtiquette(String titreMusique, String titreAlbum) {
		JPanel description = new JPanel(new FlowLayout());
		JLabel titre = new JLabel(titreMusique);
		JLabel album = new JLabel(titreAlbum);
		description.add(album);
		description.add(titre);
		return description;
	}
	
	
	private JPanel lesBouttonsDeControle(Titre titreAssocie) {
		JPanel menuInter = new JPanel(new FlowLayout());
		JButton play = new JButton("Play");
		
		play.addActionListener((event)->new Lecteur(new PisteAudio(new File(titreAssocie.getCheminVersLaMusique()), titreAssocie.getTitre())));
		JButton stop = new JButton("Stop");
		JButton reset = new JButton("Reset");
		menuInter.add(play);
		menuInter.add(stop);
		menuInter.add(reset);
		return menuInter;
	}
	
	public void update() {
		this.remove(menuFinal);
		menuFinal.removeAll();
		constructionDuPanel();
		this.add(menuFinal);
		this.validate();
	}
	
	public static MenuChanteur getInstance(String login) {
		if (instance == null)
			instance = new MenuChanteur(login);
		return instance;
	}

}