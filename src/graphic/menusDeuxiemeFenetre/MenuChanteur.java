package graphic.menusDeuxiemeFenetre;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import control.elementSauv.personnesDejaInscrite;
import control.musique.Album;
import control.musique.Titre;

@SuppressWarnings("serial")
public class MenuChanteur extends JScrollPane{

	private static MenuChanteur instance;
	private JPanel menuFinal;
	private static final String[] listeStyle = {"Rock", "Classique", "Folk", "Electro", "Dance Hall"};
	private JTabbedPane mesOnglets;
	private String login;
	private Map<Titre, JPanel> titreAssociePlayer;

	//A chaque musique sont associé des bouttons :) 
	
	private MenuChanteur(String login) {
		this.login = login;
		this.titreAssociePlayer = new HashMap<Titre, JPanel>();
		constructionDuPanel();
		//On utilise ça pour les JScrollPan pour ajouter à la place de add un JScrollPane ne peut gérer qu'un seul élément à la foi
		this.setViewportView(mesOnglets);
		
	}
	
	private void constructionDuPanel() {
		this.mesOnglets = new JTabbedPane();
		for(String monNomOnglet : listeStyle) {
			int nombreTotalDeTitre = 0;
			boolean premierPassage = true;
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums())	
				if(monAlbum.getType().equals(monNomOnglet))
					nombreTotalDeTitre+= monAlbum.getChansonsDelAlbum().size() + 1;
			
			menuFinal = new JPanel(new GridLayout(nombreTotalDeTitre, 2, 20, 10));
			
			menuFinal.setBorder(BorderFactory.createLineBorder(new Color(100,100,100), 10));
			
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums()) {
				premierPassage = true;
				if(monAlbum.getType().equals(monNomOnglet))
					for(Titre monTitre : monAlbum.getChansonsDelAlbum()) {
						if(premierPassage) {
							JLabel album = new JLabel(monAlbum.getTitre());
							menuFinal.add(album);
							ImageIcon monImage = new ImageIcon(new ImageIcon(monAlbum.getCheminVersImageAssocie()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
							JLabel imageCorespondante = new JLabel(monImage);
							menuFinal.add(imageCorespondante);
							premierPassage = false;
						}
							
						JPanel temponConstruction = constructionEtiquette(monTitre.getTitre());
						menuFinal.add(temponConstruction);
						JPanel bouttons = lesBouttonsDeControle(monTitre);
						menuFinal.add(bouttons);
						
						titreAssociePlayer.put(monTitre, bouttons);
					}
			}
			mesOnglets.add(menuFinal, monNomOnglet);
		}
	}
	
	
	private JPanel constructionEtiquette(String titreMusique) {
		JPanel description = new JPanel(new FlowLayout());
		JLabel titre = new JLabel(titreMusique);
		description.add(titre);
		return description;
	}
	
	
	private JPanel lesBouttonsDeControle(Titre titreAssocie) {
		
		JPanel menuInter = new JPanel(new FlowLayout());
		JButton play = new JButton("Play");
		JButton stop = new JButton("Stop");
		JButton reset = new JButton("Reset");
		play.addActionListener((event)-> titreAssocie.play());
		stop.addActionListener((event)-> titreAssocie.stop());
		reset.addActionListener((event)-> titreAssocie.reset());
		menuInter.add(play);
		menuInter.add(stop);
		menuInter.add(reset);
		return menuInter;
	}
	
	public void update() {
		this.setViewportView(null);
		constructionDuPanel();
		this.setViewportView(mesOnglets);
		this.validate();
	}
	
	public static MenuChanteur getInstance(String login) {
		if (instance == null)
			instance = new MenuChanteur(login);
		return instance;
	}

}