package graphic.menusParametreFenetre;

import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import control.BDD.Modification;
import control.activite.Album;
import control.activite.Titre;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuMusique;
import graphic.menusDeuxiemeFenetre.MenuPrincipal;

@SuppressWarnings("serial")

/**
 *<b>MenuAjoutMusique</b> est la classe qui permet à l'utilisateur d'ajouter un nouvel abum de musique à son répertoire
 *@author BUISSON-CHAVOT Julien
 *@version 2.0
 **/

public class MenuAjoutMusique extends JPanel{
	
	/**
	 *Déclaration de l'instance du menu d'ajout musique
	 **/
	
	private static MenuAjoutMusique instance;
	
	/**
	 *Déclaration du login utilisateur
	 **/
	
	private String login;
	
	/**
	 *Déclaration de l'artiste
	 **/

	private Artiste artiste;
	
	/**
	 *Déclaration du nouveau nom de l'album
	 **/
	
	private JTextField nouveauNom;
	
	/**
	 *Déclaration de la localisation de la musique
	 **/
	
	private String cheminVersMusique;
	
	/**
	 *Déclaration de la lisye d'album
	 **/
	
	private JComboBox<String> listeAlbum;
	
	/**
	 *Permet de générer le menu d'ajout musique
	 *@param login
	 *	Login utilisateur
	 **/
	
	private MenuAjoutMusique(String login) {
		this.login = login;
		this.artiste = null;
		this.setLayout(new FlowLayout());
		this.add(panelChoixMusique());
	}
	
	/**
	 *Permet de construire la fenetre de choix musique avec ses caractéristiques
	 *@return Fenetre de choix musique
	 **/
	
	private JPanel panelChoixMusique() {
		JPanel monPanel = new JPanel();
		JLabel etiquette = new JLabel("Nom musique");
		JButton choixFichier = new JButton("Browse");
		JButton valider = new JButton("Valider");
		nouveauNom = new JTextField("Titre musique");
		choixFichier.addActionListener((event)->choixFichier());
		valider.addActionListener((event)->valider());
		constructionListeAlbum();
		monPanel.add(etiquette);
		monPanel.add(nouveauNom);
		monPanel.add(listeAlbum);
		monPanel.add(choixFichier);
		monPanel.add(valider);
		
		return monPanel;
	}
	
	
	/**
	 *Permet de construire une liste d'album et de les afficher à la suite sur la fenetre
	 *@see Album
	 *@see personnesDejaInscrite
	 **/
	
	private void constructionListeAlbum() {
		List<String> listeTitreAlbum = new ArrayList<String>();
		if(artiste != null) {
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums()) {
				listeTitreAlbum.add(monAlbum.getTitre());
			}
			int index = 0;
			String[] listeConstruction = new String[listeTitreAlbum.size()];
			for(String monTitre : listeTitreAlbum) {
				listeConstruction[index] = monTitre;
				index++;
			}
			this.listeAlbum = new JComboBox<String>(listeConstruction);
		} else {
			this.listeAlbum = new JComboBox<String>();
		}
	}
	
	/**
	 *Permet de mettre à jour la fenetre avec les musiques selectionnées
	 *@see MenuPrincipal
	 **/
	
	public void update() {
		artiste = MenuPrincipal.getInstance(login).getArtisteSelectionne();
		System.out.println(artiste);
		this.removeAll();
		this.add(panelChoixMusique());
		this.validate();
	}
	
	/**
	 *Permet de valider le choix de l'utilisateur
	 *@see personnesDejaInscrite
	 *@see Titre
	 *@see Modification
	 *@see MenuMusique
	 **/
	
	private void valider() {
		boolean passage = false;
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums()) {
			if(monAlbum.getTitre().equals(listeAlbum.getSelectedItem())) {
				Titre maChanson = new Titre(nouveauNom.getText(), cheminVersMusique);
				monAlbum.getChansonsDelAlbum().add(maChanson);
				Modification.getInstance().insererChanson(maChanson.hashCode(), maChanson.getTitre(), 0, monAlbum.hashCode());
				passage = true;
				break;
			}
		}
		if (passage)
			MenuMusique.getInstance(login).update();
		else {
			MenuItem contenant = new MenuItem();
			JOptionPane.showInputDialog(contenant,"Champs incorrect !");
		}
	}
	
	/**
	 *Permet de choisir le fichier dans lequel se trouve la musique
	 **/
	
	private void choixFichier() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    cheminVersMusique = selectedFile.getAbsolutePath();
		}
	}
	
	/**
	 *Instanciation du menu d'ajout musique
	 *@param login
	 *	Login utilisateur
	 *@return Menu d'ajout musique
	 **/
	
	public static MenuAjoutMusique getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutMusique(login);
		return instance;
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
