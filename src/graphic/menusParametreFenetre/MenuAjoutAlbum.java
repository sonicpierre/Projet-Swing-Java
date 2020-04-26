package graphic.menusParametreFenetre;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FilenameFilter;
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

import control.activite.Album;
import control.activite.Titre;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuAlbum;
import graphic.menusDeuxiemeFenetre.MenuMusique;
import graphic.menusDeuxiemeFenetre.MenuPrincipal;

//JPANEL PERMETTANT D'AJOUTER LES ALBUMS
@SuppressWarnings("serial")
public class MenuAjoutAlbum extends JPanel{

	private static MenuAjoutAlbum instance;
	
	private final String login;//IDENTIFIER UTILISATEUR
	private Artiste artiste;
	private JTextField nom;
	private Set<Titre> titreAssocie;//LITE RITRE ASSO
	private String cheminVersImageAssocie;//CHEMIN DE L'IMAGE JPEG

	Map<JCheckBox, Titre> mesAssociationsCheckTitre;
	//SINGLETON
	//AFFICHAGE
	private MenuAjoutAlbum(String login) {
		this.login = login;
		this.artiste = null;
		this.setLayout(new FlowLayout());//AFICHAE EN HORIZONTAL 
		this.add(choixLabels());
	}
	
	//CONSTRUCTION DU CONTENU
	private JPanel choixLabels() {
		mesAssociationsCheckTitre = new HashMap<JCheckBox, Titre>();
		JPanel choixLabels = new JPanel(new FlowLayout());
		JLabel nomLabel = new JLabel("Nom Album");
		
		nom = new JTextField("Nom Album");
		JButton dossier = new JButton("Browse");
		JButton valider = new JButton("Valider");
		dossier.addActionListener((event)->choixDunAlbum());//FONCTION DE CHOIX ALBUM
		valider.addActionListener((event)->valider());//FONCTION VALIDATION ENSEMBLE CHOIX ALBUM
		
		choixLabels.add(nomLabel);
		choixLabels.add(nom);
		choixLabels.add(dossier);
		choixLabels.add(valider);
		
		return choixLabels;
	}
	
	public static MenuAjoutAlbum getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutAlbum(login);
		return instance;
	}
	
	//
	private void choixDunAlbum() {
        JFileChooser jfc = new JFileChooser();//FONCTION CHOIX 
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//ARRET QUAND POLUS DE DOSS A OUVRIR
        int ret = jfc.showOpenDialog(null); // ne te rend la main que si tu ferme
        
        if(ret == JFileChooser.APPROVE_OPTION) { // validation
        	File repSon = jfc.getSelectedFile();//RECUPERE LE DOSSIER SELECTIONNÉ
        	File repImage = repSon;//COPIE CAR SLECTION MP3 DAN SUN PREMOIER TEMPS, PUISJPEG ENSUITE
	        File[] fichiersMP3 = repSon.listFiles(new FilenameFilter(){//TABLEAU DE FICHIER MP3
	          public boolean accept(File dir, String name) {
	            return name.endsWith(".mp3");//TEST DE TERMINAISON
	          }
	        });
	        File[] fichiersImage = repImage.listFiles(new FilenameFilter(){//IDEM POUR IMAGE
		          public boolean accept(File dir, String name) {
		            return name.endsWith(".jpg");
		          }
		        });
	        cheminVersImageAssocie = fichiersImage[0].getAbsolutePath();//ON GARDE QUE LA PREMIERE IMAGE SELECTIONNÉE
	        titreAssocie = new HashSet<Titre>();//LISTE DE CHEMIN VERS FICHIER MP3
	        for(File monFile : fichiersMP3) {
	        	titreAssocie.add(new Titre(monFile.getName(), monFile.getAbsolutePath()));//REMPLISSAGE AVEC LES CHEMINS ABS
	        }
        }
	}
	

	private void valider() {
		if(titreAssocie != null) {//SI LA LISTE DE TITE N'EST PAS NULLE => CREATION ALBUM DANSLES ENDROOITS DE SAUVEGARDE
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).ajouterAlbum(new Album(nom.getText(), titreAssocie, cheminVersImageAssocie));
			MenuMusique.getInstance(login).update();//ON LA FONCTION QUE PV A CREER QUI S'APPELLE ET QUI PERMET L'AJOUT DE L'ALNUM ENTRE
			MenuPrincipal.getInstance(login).validate();
			titreAssocie = null;//INITIALISATION DE LA LISTE
			nom.setText("Nom Album");//RESTTAURATION DE LA FENETRE 

			JMenuItem contenant = new JMenuItem();
			JOptionPane.showMessageDialog(contenant,"Album ajouté");
			MenuAjoutMusique.getInstance(login).setArtiste(artiste);
			MenuAjoutMusique.getInstance(login).update();
			MenuAlbum.getInstance(login).update();
		}
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	
}