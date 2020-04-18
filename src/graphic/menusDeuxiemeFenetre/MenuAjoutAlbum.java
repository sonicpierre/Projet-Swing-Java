package graphic.menusDeuxiemeFenetre;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import control.elementSauv.personnesDejaInscrite;
import control.musique.Album;
import control.musique.Titre;

//JPANEL PERMETTANT D'AJOUTER LES ALBUMS
@SuppressWarnings("serial")
public class MenuAjoutAlbum extends JPanel{

	private static MenuAjoutAlbum instance;
	
	private final String login;//IDENTIFIER UTILISATEUR
	private JTextField nom;
	private List<Titre> titreAssocie;//LITE RITRE ASSO
	private String cheminVersImageAssocie;//CHEMIN DE L'IMAGE JPEG
	private JComboBox<String> style;//MENU DEROULANT
	private static final String[] listeStyle = {"Rock", "Classique", "Folk", "Electro", "Dance Hall"};//ONGLET TYPE DE ZIK
	Map<JCheckBox, Titre> mesAssociationsCheckTitre;
	//SINGLETON
	//AFFICHAGE
	private MenuAjoutAlbum(String login) {
		this.login = login;
		this.setLayout(new FlowLayout());//AFICHAE EN HORIZONTAL 
		this.add(choixLabels());
	}
	
	//CONSTRUCTION DU CONTENU
	private JPanel choixLabels() {
		mesAssociationsCheckTitre = new HashMap<JCheckBox, Titre>();
		JPanel choixLabels = new JPanel(new FlowLayout());
		JLabel nomLabel = new JLabel("Nom Album");
		style = new JComboBox<String>(listeStyle);//EN PARAM LA LISTE DE TYPE
		
		nom = new JTextField("Nom Album");
		JButton dossier = new JButton("Browse");
		JButton valider = new JButton("Valider");
		dossier.addActionListener((event)->choixDunAlbum());//FONCTION DE CHOIX ALBUM
		valider.addActionListener((event)->valider());//FONCTION VALIDATION ENSEMBLE CHOIX ALBUM
		
		choixLabels.add(nomLabel);
		choixLabels.add(nom);
		choixLabels.add(style);
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
	        titreAssocie = new ArrayList<Titre>();//LISTE DE CHEMIN VERS FICHIER MP3
	        for(File monFile : fichiersMP3) {
	        	titreAssocie.add(new Titre(monFile.getName(), monFile.getAbsolutePath()));//REMPLISSAGE AVEC LES CHEMINS ABS
	        }
        }
	}
	

	private void valider() {
		if(titreAssocie != null) {//SI LA LISTE DE TITE N'EST PAS NULLE => CREATION ALBUM DANSLES ENDROOITS DE SAUVEGARDE
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).ajouterAlbum(new Album(nom.getText(), style.getItemAt(style.getSelectedIndex()), titreAssocie, cheminVersImageAssocie));
			MenuChanteur.getInstance(login).update();//ON LA FONCTION QUE PV A CREER QUI S'APPELLE ET QUI PERMET L'AJOUT DE L'ALNUM ENTRE
			titreAssocie = null;//INITIALISATION DE LA LISTE
			nom.setText("Nom Album");//RESTTAURATION DE LA FENETRE 
			style.setSelectedItem(listeStyle[0]);
			JMenuItem contenant = new JMenuItem();
			JOptionPane.showMessageDialog(contenant,"Album ajouté");
			MenuAjoutMusique.getInstance(login).update();
		}
	}
	
	
}