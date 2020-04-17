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

//
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
		this.mesOnglets = new JTabbedPane();	//CREATION PANEL ONGLETS
		for(String monNomOnglet : listeStyle) {//POUR TOUS LES STYLES DE ZIK, ON PASSE TOUS LES ALBUMS YANT UN CERTAIN STYLE, ON COMPTE LEUR NOMBRE DE TITRE DANS L'LABUM => NBR DE LIGNE DU TABLEAU QUON VEUR AFFICHER
			int nombreTotalDeTitre = 0;
			boolean premierPassage = true;//PREMIER PASS NOM ET IMG ALBUM
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums())	//POUR TOUS LES ALBUM DAN SLA LISTE D'ALBUM,SAUVEGARDER
				if(monAlbum.getType().equals(monNomOnglet))//TEST DU TYPE EN LECTURE
					nombreTotalDeTitre+= monAlbum.getChansonsDelAlbum().size() + 1;//COMPTE DU NBR DE TITRE + 1 CAR RAJOUT D'UNE LIGNE NOM + PHOTO
			
			//CREATION COLONNES: NOM DE TITRE + GESTION LECTURE
			menuFinal = new JPanel(new GridLayout(nombreTotalDeTitre, 2, 20, 10));
			
			menuFinal.setBorder(BorderFactory.createLineBorder(new Color(100,100,100), 10));//BORDURE
			
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums()) {//POUR TOUS LES ALBUM INSCRIS
				premierPassage = true;
				if(monAlbum.getType().equals(monNomOnglet))
					for(Titre monTitre : monAlbum.getChansonsDelAlbum()) {//ON PASSSE TOUTES LES MUSIQUES
						if(premierPassage) {
							JLabel album = new JLabel("<html><FONT color=\"#ff0000\" size = \"6\" face=\"Times New Roman\">"+ monAlbum.getTitre() +"</FONT></html>");//ECRITURE EN HTML PERMETTAT DES PREZ FOLIFOLI
							menuFinal.add(album);//AJOUT TITRE 
							ImageIcon monImage = new ImageIcon(new ImageIcon(monAlbum.getCheminVersImageAssocie()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
							JLabel imageCorespondante = new JLabel(monImage);
							menuFinal.add(imageCorespondante);//BOUTON CORRESPONDANT AU CONTROL DE LA ZIK
							premierPassage = false;
						}
							
						JPanel temponConstruction = constructionEtiquette(monTitre.getTitre());//TITRE ZIK
						menuFinal.add(temponConstruction);
						JPanel bouttons = lesBouttonsDeControle(monTitre);//CONTROLE LECTURE ZIK
						menuFinal.add(bouttons);
						
						titreAssociePlayer.put(monTitre, bouttons);
					}
			}
			mesOnglets.add(menuFinal, monNomOnglet);//AJOUT DE TOUS LES ONGLETS A UN SEUL ONGLET 
		}
	}
	
	
	private JPanel constructionEtiquette(String titreMusique) {//CONSTRUCTION PANEL ETIQUETTE (TITRE) DE LA CHANSON
		JPanel description = new JPanel(new FlowLayout());
		JLabel titre = new JLabel(titreMusique);
		description.add(titre);
		return description;
	}
	
	
	private JPanel lesBouttonsDeControle(Titre titreAssocie) {//BOUTON DE COTROLE => CONTROLE LECTEUR DOU LE TRIRE ASSOCIÉ
		
		JPanel menuInter = new JPanel(new FlowLayout());
		JButton play = new JButton("Play");
		JButton stop = new JButton("Stop");
		JButton reset = new JButton("Reset");
		play.addActionListener((event)-> titreAssocie.play());//DEFINITION DE L'ACTION DU BOUTON CORRESPONDANT AU BON TITRE @SEE TITRE
		stop.addActionListener((event)-> titreAssocie.stop());
		reset.addActionListener((event)-> titreAssocie.reset());
		menuInter.add(play);
		menuInter.add(stop);
		menuInter.add(reset);
		return menuInter;
	}
	
	public void update() {//
		this.setViewportView(null);//INIT A NULL
		constructionDuPanel();//RECLACULE INTERIEUR PANEL
		this.setViewportView(mesOnglets);//REAJOUTE AUX ONGLETS
		this.validate();//RECALCUL DE L'EMPLACMENT DES LAYOUT 
	}
	
	public static MenuChanteur getInstance(String login) {
		if (instance == null)
			instance = new MenuChanteur(login);
		return instance;
	}

}