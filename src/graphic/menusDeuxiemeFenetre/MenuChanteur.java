package graphic.menusDeuxiemeFenetre;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileSystemView;

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
	Map<JCheckBox, Titre> mesAssociationsCheckTitre;
	private Titre titreEnCoursDeLecture;
	//A chaque musique sont associé des bouttons :) 
	
	private MenuChanteur(String login) {
		this.login = login;
		this.titreEnCoursDeLecture = null;
		constructionDuPanel();
		//On utilise ça pour les JScrollPan pour ajouter à la place de add un JScrollPane ne peut gérer qu'un seul élément à la foi
		this.setViewportView(mesOnglets);
	}
	

	private void constructionDuPanel() {
		mesAssociationsCheckTitre = new HashMap<JCheckBox, Titre>();
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
							
							imageCorespondante.addMouseListener(new MouseAdapter() {

								@Override
								public void mousePressed(MouseEvent event) {
									monAlbum.setSelected(true);
									if(event.isPopupTrigger()) {
										createPopupMenu(monAlbum).show(event.getComponent(), event.getX(), event.getY());
									}
								}

							});
							
							menuFinal.add(imageCorespondante);//BOUTON CORRESPONDANT AU CONTROL DE LA ZIK
							premierPassage = false;
						}
							
						JPanel temponConstruction = constructionEtiquette(monTitre);//TITRE ZIK
						menuFinal.add(temponConstruction);
						JPanel bouttons = lesBouttonsDeControle(monTitre);//CONTROLE LECTURE ZIK
						menuFinal.add(bouttons);
					}
			}
			mesOnglets.add(menuFinal, monNomOnglet);//AJOUT DE TOUS LES ONGLETS A UN SEUL ONGLET 
		}
	}
	
	
	private JPanel constructionEtiquette(Titre titre) {//CONSTRUCTION PANEL ETIQUETTE (TITRE) DE LA CHANSON
		JPanel description = new JPanel(new FlowLayout());
		JCheckBox maCheckBox = new JCheckBox(titre.getTitre());
		mesAssociationsCheckTitre.put(maCheckBox, titre);
		description.add(maCheckBox);
		return description;
	}
	
	
	private JPanel lesBouttonsDeControle(Titre titreAssocie) {//BOUTON DE COTROLE => CONTROLE LECTEUR DOU LE TRIRE ASSOCIÉ
		
		JPanel menuInter = new JPanel(new FlowLayout());
		JButton play = new JButton("Play");
		JButton stop = new JButton("Stop");
		JButton reset = new JButton("Reset");
		play.addActionListener((event)-> play(titreAssocie));//DEFINITION DE L'ACTION DU BOUTON CORRESPONDANT AU BON TITRE @SEE TITRE
		stop.addActionListener((event)-> stop());
		reset.addActionListener((event)-> reset());
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
	
	public void checkOperation(String operation) {
	      Set<Entry<JCheckBox, Titre>> set = mesAssociationsCheckTitre.entrySet();
	      Iterator<Entry<JCheckBox, Titre>> monIterateur = set.iterator();
	      boolean passage = false;
	      boolean passagePlayer = false;
	      while(monIterateur.hasNext()){
	         Entry<JCheckBox, Titre> e = monIterateur.next();
	         if(e.getKey().isSelected() && operation.equals("Supprimer")) {
	        	 e.getValue().supprimerMusique();
	        	 passage = true;
	        	 //On supprime un album qui est vide
	        	 if(e.getValue().getAlbumAssocie().getChansonsDelAlbum().isEmpty()) {
	        		 personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums().remove(e.getValue().getAlbumAssocie());
	        		 personnesDejaInscrite.getInstance().sauvegarder();
	        	 }
	         } else if(e.getKey().isSelected() && operation.equals("Play")) {
	        	 this.play(e.getValue());
	        	 passagePlayer = true;
	         } else if(e.getKey().isSelected() && operation.equals("Stop")) {
	        	 this.stop();
	        	 passagePlayer = true;
	         } else if(e.getKey().isSelected() && operation.equals("Reset")) {
	        	 this.reset();
	        	 passagePlayer = true;
	         }
	      }
	      if(passage && !passagePlayer)
	    	  this.update();
	      else if(!passage && !passagePlayer){
	    	  JMenuItem indication = new JMenuItem();
	    	  JOptionPane.showMessageDialog(indication,"Aucune musique sélectionnée");
	      }
	}
	
	
	
	
	private JPopupMenu createPopupMenu(Album albumAssocie) {
		JPopupMenu monPopUp = new JPopupMenu();
		monPopUp.add(MenuRaccourcis.getInstance(login).actSuppressionAlbum);
		monPopUp.add(MenuRaccourcis.getInstance(login).actRenommer);
		monPopUp.add(MenuRaccourcis.getInstance(login).actChangerImage);
		return monPopUp;
	}

	public void supprimerAlbum() {
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums())
			if(monAlbum.isSelected()) {
				personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums().remove(monAlbum);
				personnesDejaInscrite.getInstance().sauvegarder();
				break;
			}
				
	}
	
	public void renommerAlbum() {
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums())
			if(monAlbum.isSelected()) {
				monAlbum.setTitre("Momo va aux moules");
				personnesDejaInscrite.getInstance().sauvegarder();
				break;
			}
				
	}
	
	public void changerImage() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums())
				if(monAlbum.isSelected()) {
					monAlbum.setCheminVersImageAssocie(selectedFile.getAbsolutePath());
					personnesDejaInscrite.getInstance().sauvegarder();
					break;
				}
		}
		
	}
	
	
	private void play(Titre titreAssocie) {
		if(titreEnCoursDeLecture==null) {
			this.titreEnCoursDeLecture = titreAssocie;
			this.titreEnCoursDeLecture.play();
		} else if(titreEnCoursDeLecture.isPlaying() && !titreEnCoursDeLecture.isEnPause()) {
			this.titreEnCoursDeLecture.stop();
			this.titreEnCoursDeLecture = titreAssocie;
			this.titreEnCoursDeLecture.play();
		} else {
			this.titreEnCoursDeLecture = titreAssocie;
			this.titreEnCoursDeLecture.play();
		}
			
	}
	
	
	private void stop() {
		if(titreEnCoursDeLecture!=null)
			titreEnCoursDeLecture.stop();
		else {
    	  JMenuItem indication = new JMenuItem();
    	  JOptionPane.showMessageDialog(indication,"Aucun Titre est joué");
		}
	}
	
	private void reset() {
		if(titreEnCoursDeLecture!=null)
			titreEnCoursDeLecture.reset();
		else {
	    	  JMenuItem indication = new JMenuItem();
	    	  JOptionPane.showMessageDialog(indication,"Aucun Titre est joué");
			}
	}


	public Titre getTitreEnCoursDeLecture() {
		return titreEnCoursDeLecture;
	}


	public void setTitreEnCoursDeLecture(Titre titreEnCoursDeLecture) {
		this.titreEnCoursDeLecture = titreEnCoursDeLecture;
	}
	
	
}