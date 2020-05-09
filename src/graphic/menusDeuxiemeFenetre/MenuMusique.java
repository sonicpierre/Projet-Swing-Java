package graphic.menusDeuxiemeFenetre;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileSystemView;

import control.BDD.Modification;
import control.activite.Album;
import control.activite.Titre;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetre.FenetreParametre;

/**
 * <b>MenuMusique</b> est la classe qui permet de générer l'ensemble du menu concernant les musiques.
 * @author Lucille Beziat
 * @version 2.0
 */
@SuppressWarnings("serial")
public class MenuMusique extends JPanel{

	private static MenuMusique instance;
	
	private JPanel menuFinal;
	private String login;
	private Artiste artiste;
	//Permet d'avoir un panel de taille adaptable
	private int nombreDeMusique;
	//A chaque titre on associe une cheque box pour pouvoir faire des opérations dessus, lire, mettre en pause, supprimer
	Map<JCheckBox, Titre> mesAssociationsCheckTitre;
	//Va permettre ici de gérer les lectures et ne pas jouer 2 musiques en même temps.
	private Titre titreEnCoursDeLecture;

	/**
	 * Ici on construit la première instance du MenuMusique
	 * @param login
	 */
	
	private MenuMusique(String login) {
		this.login = login;
		this.artiste = null;
		this.titreEnCoursDeLecture = null;
		if(artiste == null)
			constructionDuPanelGlobal();
		this.add(menuFinal);
	}
	
	/**
	 * Permet de construire le panel principal du menu en prenant en compte le fait qu'on est choisi ou non un artiste.
	 * Chaque musique sera affiché avec son nom et un player associé permettant de se donner une idée de la musique.
	 * De plus on ajoute à chaque photo d'album un popupMenu permettant la Mise à jour de celui-ci.
	 */

	private void constructionDuPanelGlobal() {
		//On a ici pour chacun des titres musicaux une case qu'on peut cocher.
		mesAssociationsCheckTitre = new HashMap<JCheckBox, Titre>();
		//On va compter le nombre de musique pour bien les afficher.
		nombreDeMusique = 0;
		//Va permettre d'afficher l'album avant d'afficher les musiques
		boolean premierPassage = false;
		//Représente le cas où on n'a pas encore seletionné d'artiste 
		if(artiste == null) {
			nombreDeMusique = 0;
			for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste()) {//POUR TOUS LES STYLES DE ZIK, ON PASSE TOUS LES ALBUMS YANT UN CERTAIN STYLE, ON COMPTE LEUR NOMBRE DE TITRE DANS L'LABUM => NBR DE LIGNE DU TABLEAU QUON VEUR AFFICHER
				for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(monArtiste).getMaListeDeAlbums())	//POUR TOUS LES ALBUM DAN SLA LISTE D'ALBUM,SAUVEGARDER
					nombreDeMusique+= monAlbum.getChansonsDelAlbum().size() + 1;//COMPTE DU NBR DE TITRE + 1 CAR RAJOUT D'UNE LIGNE NOM + PHOTO
			}
		} else {
			nombreDeMusique = 0;
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums())	//POUR TOUS LES ALBUM DAN SLA LISTE D'ALBUM,SAUVEGARDER
				nombreDeMusique+= monAlbum.getChansonsDelAlbum().size() + 1;//COMPTE DU NBR DE TITRE + 1 CAR RAJOUT D'UNE LIGNE NOM + PHOTO
		}
		
			//CREATION COLONNES: NOM DE TITRE + GESTION LECTURE
			menuFinal = new JPanel(new GridLayout(nombreDeMusique, 2, 20, 10));
			
			if(artiste == null) {
				for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste()) {
					for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(monArtiste).getMaListeDeAlbums()) {//POUR TOUS LES ALBUM INSCRIS
						premierPassage = true;
						//On passe toutes les musiques de l'Album
						for(Titre monTitre : monAlbum.getChansonsDelAlbum()) {
							if(premierPassage) {
								//On peut remarquer qu'il est possible de mettre du HTML dans les JLabels.
								JLabel album = new JLabel("<html><FONT color=\"#ff0000\" size = \"6\" face=\"Times New Roman\">"+ monAlbum.getTitre() +"</FONT></html>");//ECRITURE EN HTML PERMETTAT DES PREZ FOLIFOLI
								menuFinal.add(album);//AJOUT TITRE 
								ImageIcon monImage = new ImageIcon(new ImageIcon(monAlbum.getCheminVersImageAssocie()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
								JLabel imageCorespondante = new JLabel(monImage);
								
								imageCorespondante.addMouseListener(new MouseAdapter() {
									//On définit l'évènement dans le cas d'un mouseEvent particulier
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
				} 
			}else {
					
					for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums()) {//POUR TOUS LES ALBUM INSCRIS
						premierPassage = true;
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
						
				}
		}
	
	/**
	 * Cette méthode permet de constuire pour chacune des musiques un petit panel avec son titre et sa check box.
	 * @see Titre
	 * @param titre
	 * @return La description de la musique
	 */
	
	private JPanel constructionEtiquette(Titre titre) {//CONSTRUCTION PANEL ETIQUETTE (TITRE) DE LA CHANSON
		JPanel description = new JPanel(new FlowLayout());
		if(artiste==null) {
			JLabel monTitre = new JLabel(titre.getTitre());
			description.add(monTitre);
		} else {
			JCheckBox maCheckBox = new JCheckBox(titre.getTitre());
			mesAssociationsCheckTitre.put(maCheckBox, titre);
			description.add(maCheckBox);
		}
		return description;
	}
	
	/**
	 * Cette fonction permet de construire un layout contenant les différents bouttons pour contrôler la musique.
	 * L'attribut nous donne donc la possibilité d'associer à chacuns des bouttons un titre, pour qu'on puisse jouer les bons titre correspondants.
	 * 
	 * @param titreAssocie
	 * @return un panel contenant les 3 bouttons de contrôle
	 */
	
	private JPanel lesBouttonsDeControle(Titre titreAssocie) {
		
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
	
	/**
	 * Cette méthode sert à recalculer l'ensemble du panel elle va être utile au moment du changement d'artiste mais aussi
	 * quand on ajoute une musique dés qu'il y a des changements cette méthode sera appelée.
	 * 
	 * @see MenuPrincipal
	 */
	
	public void update() {
		this.removeAll();
		this.artiste = MenuPrincipal.getInstance(login).getArtisteSelectionne();
		//Recalcule l'intérieur du panel
		constructionDuPanelGlobal();
		//Ajoute les musiques au menu final
		this.add(menuFinal);
		//Permet de recalculer l'emplacement des layouts.
		this.validate();
	}
	
	/**
	 * Cette méthode permet de regarder toutes les checkBox et d'appliquer des opérations sur les titres associés à 
	 * la checkBox correspondante. Ici on peut supprimer, Lire, Stoper ou Reset une musique.
	 * @param operation
	 */
	
	public void checkOperation(String operation) {
	      Set<Entry<JCheckBox, Titre>> set = mesAssociationsCheckTitre.entrySet();
	      Iterator<Entry<JCheckBox, Titre>> monIterateur = set.iterator();
	      //Va permettre de savoir si on est passer dans les conditions.
	      boolean passage = false;
	      boolean passagePlayer = false;
	      while(monIterateur.hasNext()){
	         Entry<JCheckBox, Titre> e = monIterateur.next();
	         if(e.getKey().isSelected() && operation.equals("Supprimer")) {
	        	 e.getValue().supprimerMusique();
	        	 Modification.getInstance().supprimerChanson(e.getValue().getTitre());
	        	 passage = true;
	        	 //On supprime un album qui est vide
	        	 if(e.getValue().getAlbumAssocie().getChansonsDelAlbum().isEmpty()) {
	        		 Modification.getInstance().supprimerAlbum(e.getValue().getAlbumAssocie().hashCode());
	        		 personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums().remove(e.getValue().getAlbumAssocie());
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
	
	/**
	 * Il faut ici créer la popup avec toutes les opération qu'on veut faire.
	 * Permet ici de supprimer, changer l'image de l'album et enfin renommer l'album.
	 * 
	 * @param albumAssocie
	 * @see MenuRaccourcis
	 * @return La popup menu correspondante
	 */
	
	private JPopupMenu createPopupMenu(Album albumAssocie) {
		JPopupMenu monPopUp = new JPopupMenu();
		if(artiste!=null) {
			JMenuItem renommer = new JMenuItem("Renommer");
			renommer.addActionListener(e -> renommerAlbum());
			monPopUp.add(MenuRaccourcis.getInstance(login, artiste).actSuppressionAlbum);
			monPopUp.add(renommer);
			monPopUp.add(MenuRaccourcis.getInstance(login, artiste).actChangerImage);
		}
		return monPopUp;
	}

	/**
	 * Permet de supprimer un Album dans la BDD et dans le fichier et donc qu'il apparaisse plus dans l'affichage graphique.
	 * Ici l'utilisation du hashCode comme id rend la suppression dans la BDD facile et rapide.
	 * 
	 * @see Modification
	 * @see personnesDejaInscrite
	 * @see MenuAlbum
	 */
	
	public void supprimerAlbum() {
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums())
			if(monAlbum.isSelected()) {
				Modification.getInstance().supprimerAlbum(monAlbum.hashCode());
				personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums().remove(monAlbum);
				personnesDejaInscrite.getInstance().sauvegarder();
				this.update();
				MenuAlbum.getInstance(login).update();
				break;
			}
				
	}
	
	/**
	 * Cette méthode permet de donner un nouveau nom à l'album.
	 * @see FenetreParametre
	 */
	
	public void renommerAlbum() {
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums())
			if(monAlbum.isSelected()) {
				FenetreParametre.getInstance(login).ajoutRenommageFenetre("Renommer Album", "Renommer Album", "Titre Album", monAlbum, artiste, null);
				break;
			}
				
	}
	
	/**
	 * Cette méthode va permettre par le biais d'une popup menu de donner la possibilité de changer l'image correspondant à un Album.
	 * Une fois changé l'image changera certes dans le MenuMusique mais aussi dans le menu Album.
	 * 
	 * @see MenuAlbum
	 */
	
	public void changerImage() {
		//Ce composant est particulièrement util pour choisir un fichier et c'est pourquoi on l'utilise ici pour choisir la nouvelle photo
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums())
				if(monAlbum.isSelected()) {
					//On prend le chemin absolu car il est nécessaire lorsqu'on change d'ordinateur l'architecture n'est pas forcément la même
					monAlbum.setCheminVersImageAssocie(selectedFile.getAbsolutePath());
					personnesDejaInscrite.getInstance().sauvegarder();
					this.update();
					MenuAlbum.getInstance(login).update();
					break;
				}
		}
		
	}
	
	/**
	 * Il est plus difficile de lire le titre en cours. En effet il faut d'abord vérifier qu'il n'est pas déjà entrain d'être lu, vérifier si 
	 * il est en pause ou si le titre est encore à null car il n'a jamais été utilisé ou qu'il avait fini de lire le titre.
	 * 
	 * @see Titre
	 * @param titreAssocie
	 * @deprecated On utilise ici une méthode dépréciée qui est play mais qui nous sert à lancer le player facilement, elle est dépréciée car elle ne permet pas de refermer les fluxs correctement
	 */
	
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
	
	/**
	 * Permet ici d'arrêter le titre qui entrain d'être joué.
	 * Si jamais aucun titre n'est sélectionné une fenêtre s'ouvre avec un Warning
	 * @see Titre
	 */
	
	private void stop() {
		if(titreEnCoursDeLecture!=null)
			titreEnCoursDeLecture.stop();
		else {
    	  JMenuItem indication = new JMenuItem();
    	  //Fenêtre préexistence dans Swing
    	  JOptionPane.showMessageDialog(indication,"Aucun Titre est joué");
		}
	}
	
	/**
	 * Comme pour stop(), ici il faut qu'un titre soit joué pour être rejoué du début
	 * @see Titre
	 */
	
	private void reset() {
		if(titreEnCoursDeLecture!=null)
			titreEnCoursDeLecture.reset();
		else {
	    	  JMenuItem indication = new JMenuItem();
	    	  JOptionPane.showMessageDialog(indication,"Aucun Titre est joué");
			}
	}

	/**
	 * Getter du titre entrain d'être lu possibilité d'avoir un retour null
	 * @return titre en cour de lecture
	 */

	public Titre getTitreEnCoursDeLecture() {
		return titreEnCoursDeLecture;
	}
	
	/**
	 * Permet de changer le titre en cours de lecture
	 * @param titreEnCoursDeLecture
	 */

	public void setTitreEnCoursDeLecture(Titre titreEnCoursDeLecture) {
		this.titreEnCoursDeLecture = titreEnCoursDeLecture;
	}
	
	/**
	 * Permet de récupérer l'artiste qui avait été sélectionné
	 * @return Artiste sélectionné
	 */

	public Artiste getArtiste() {
		return artiste;
	}

	/**
	 * Permet de remettre l'artiste à null quand c'est fini et de l'initialiser quand on rentre sur le menu
	 * @param artiste
	 */

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	/**
	 * Permet de récupérer le nombre de musique nécessaire pour faire un bon affichage dans le MenuPrincipal
	 * @return Le nombre de musique de l'artiste
	 */

	public int getNombreDeMusique() {
		return nombreDeMusique;
	}

	/**
	 * Permet de changer le nombre de musique comme tous les artistes n'ont pas le même nombre de musiques
	 * @param nombreDeMusique
	 */

	public void setNombreDeMusique(int nombreDeMusique) {
		this.nombreDeMusique = nombreDeMusique;
	}
	
	
	/**
	 * Cette fonction permet d'accéder à l'objet MenuMusique
	 * @param login Login utilisateur
	 * @return L'objet singleton
	 */
	
	public static MenuMusique getInstance(String login) {
		if (instance == null)
			instance = new MenuMusique(login);
		return instance;
	}
}