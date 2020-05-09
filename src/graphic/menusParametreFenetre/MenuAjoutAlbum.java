package graphic.menusParametreFenetre;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.BDD.Modification;
import control.activite.Album;
import control.activite.Titre;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuAlbum;
import graphic.menusDeuxiemeFenetre.MenuMusique;
import graphic.menusDeuxiemeFenetre.MenuPrincipal;

@SuppressWarnings("serial")

/**
 *<b>MenuAjoutAlbum</b> est la classe qui permet l'ajout d'album dans le menu album
 *@author VIRGAUX Pierre
 *@version 2.0
 **/

public class MenuAjoutAlbum extends JPanel{
	
	/**
	 *Déclaration de l'instance du menu ajout album
	 **/
	
	private static MenuAjoutAlbum instance;
	
	/**
	 *Déclaration du login utilisateur permettant de l'identifier
	 **/
	
	private final String login;
	
	/**
	 *Déclaration de l'artiste
	 *@see Artiste
	 **/
	
	private Artiste artiste;
	
	/**
	 *Déclaration du nom de l'artiste
	 **/
	
	private JTextField nom;
	/**
	 *Set des titres associés aux musiques
	 **/
	
	private Set<Titre> titreAssocie;
	
	/**
	 *Localisation de l'image associée à la musique
	 **/
	
	private String cheminVersImageAssocie;
	
	/**
	 *Vérification des titres
	 *@see Titre
	 **/
	
	Map<JCheckBox, Titre> mesAssociationsCheckTitre;

	/**
	 *Affichage de l'interface d'ajout album
	 *@param login
	 *	Login utilisateur
	 **/
	
	private MenuAjoutAlbum(String login) {
		this.login = login;
		this.artiste = null;
		this.setLayout(new FlowLayout()); 
		this.add(choixLabels());
	}
	
	/**
	 *Construction du contenu du menu
	 *@return Action choisie
	 **/
	
	private JPanel choixLabels() {
		mesAssociationsCheckTitre = new HashMap<JCheckBox, Titre>();
		JPanel choixLabels = new JPanel(new FlowLayout());
		JLabel nomLabel = new JLabel("Nom Album");
		
		nom = new JTextField("Nom Album");
		JButton dossier = new JButton("Browse");
		JButton valider = new JButton("Valider");
		
		/**
		 *Fonction de choix album
		 **/
		
		dossier.addActionListener((event)->choixDunAlbum());
		
		/**
		 *Fonction de validation du choix de l'album
		 **/
		
		valider.addActionListener((event)->valider());
		
		choixLabels.add(nomLabel);
		choixLabels.add(nom);
		choixLabels.add(dossier);
		choixLabels.add(valider);
		
		return choixLabels;
	}
	
	/**
	 *Instanciation du menu d'ajout album
	 *@param login
	 *	LOgin utilisateur
	 *@return Menu d'ajout album
	 **/
	
	public static MenuAjoutAlbum getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutAlbum(login);
		return instance;
	}
	
	/**
	 *Permet de faire le choix d'un album à ajouter
	 **/
	private void choixDunAlbum() {
		
		/**
		 *Fonction de choix
		 **/
		
        JFileChooser jfc = new JFileChooser();
        
        /**
         *Condition d'arrêt lorsqu'il n'y a plus de dossier à ouvrir
         **/
        
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        /**
         *Ne rend la main à l'utilisateur que s'il ferme la fenetre de navigation dossier
         **/
        
        int ret = jfc.showOpenDialog(null);
        
        /**
         *Permet la validation du choix
         **/
        
        if(ret == JFileChooser.APPROVE_OPTION) {
        	
        	/**
        	 *Récupère le dossier selectionné
        	 **/
        	
        	File repSon = jfc.getSelectedFile();
        	
        	/**
        	 *Copie de la selection du MP3 dans un premier temps, puis de l'image associée
        	 **/
        	
        	File repImage = repSon;
        	
        	/**
        	 *Tableau de fichier MP3
        	 **/
        	
	        File[] fichiersMP3 = repSon.listFiles(new FilenameFilter(){
	          public boolean accept(File dir, String name) {
	        	  
	        	  /**
	        	   *Test de terminaison
	        	   **/
	        	  
	            return name.endsWith(".mp3");
	          }
	        });
	        
	        /**
	         *Même procédé que précédemment pour l'image
	         **/
	        
	        File[] fichiersImage = repImage.listFiles(new FilenameFilter(){
		          public boolean accept(File dir, String name) {
		            return name.endsWith(".jpg");
		          }
		        });
	        
	        /**
	         *Garde la première image selectionnée
	         **/
	        
	        cheminVersImageAssocie = fichiersImage[0].getAbsolutePath();
	        
	        /**
	         *Liste de chemin fichier MP3
	         **/
	        
	        titreAssocie = new HashSet<Titre>();
	        for(File monFile : fichiersMP3) {
	        	Titre monTitre = new Titre(monFile.getName(), monFile.getAbsolutePath());
	        	
	        	/**
	        	 *Remplissage avec les chemins absolus
	        	 **/
	        	
	        	titreAssocie.add(monTitre);
	        }
        }
	}
	
	/**
	 *Permet la validation du choix album
	 *@see Album
	 *@see personnesDejaInscrite
	 *@see Modification
	 *@see MenuMusique
	 *@see MenuPrincipal
	 **/
	
	private void valider() {
		
		/**
		 *Test du contenu de la liste et création d'album dans la localisation de sauvegarder
		 **/
		
		if(titreAssocie != null) {
			Album monAlbum = new Album(nom.getText(), titreAssocie, cheminVersImageAssocie);
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).ajouterAlbum(monAlbum);
			
			//System.out.println(monAlbum.hashCode() + " " + monAlbum.getTitre() + " " + artiste.hashCode());
			
			
			Modification.getInstance().insererAlbum(monAlbum.hashCode(), monAlbum.getTitre(), "10-12-1990", artiste.hashCode());
			
			
			for(Titre maChanson : titreAssocie)
				Modification.getInstance().insererChanson(maChanson.hashCode(), maChanson.getTitre(), 0, monAlbum.hashCode());
			
			/**
			 *Ajout de la musique entrée
			 **/
			
			MenuMusique.getInstance(login).update();
			
			/**
			 *Validation de l'ajout
			 **/
			
			MenuPrincipal.getInstance(login).validate();
			
			/**
			 *Initialisation de la liste
			 **/
			
			titreAssocie = null;
			
			/**
			 *Restaurationnde la fenetre
			 **/
			
			nom.setText("Nom Album"); 

			JMenuItem contenant = new JMenuItem();
			JOptionPane.showMessageDialog(contenant,"Album ajouté");
			MenuAjoutMusique.getInstance(login).setArtiste(artiste);
			MenuAjoutMusique.getInstance(login).update();
			MenuAlbum.getInstance(login).update();
		}
	}
	
	/**
	 *Récupère l'artiste
	 *@return Artiste
	 **/
	
	public Artiste getArtiste() {
		return artiste;
	}
	
	/**
	 *Initialisation de l'artiste
	 *@param artiste
	 *	Artiste
	 **/
	
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	
}