package graphic.menusDeuxiemeFenetre;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.elementSauv.personnesDejaInscrite;
import control.musique.Album;
import control.musique.Titre;
import graphic.fenetre.FenetreParametre;

@SuppressWarnings("serial")
public class MenuAjoutAlbum extends JPanel{

	private static MenuAjoutAlbum instance;
	
	private final String login;
	private JTextField nom;
	private List<Titre> titreAssocie;
	private String cheminVersImageAssocie;
	JComboBox<String> style;
	private static final String[] listeStyle = {"Rock", "Classique", "Folk", "Electro", "Dance Hall"};
	
	private MenuAjoutAlbum(String login) {
		this.login = login;
		this.setLayout(new FlowLayout());
		this.add(choixLabels());
	}
	
	private JPanel choixLabels() {
		JPanel choixLabels = new JPanel(new FlowLayout());
		JLabel nomLabel = new JLabel("Nom Album");
		style = new JComboBox<String>(listeStyle);
		
		JLabel dossierLabel = new JLabel("Dossier");
		nom = new JTextField("Nom Album");
		JButton dossier = new JButton("Browse");
		JButton valider = new JButton("Valider");
		dossier.addActionListener((event)->choixDunAlbum());
		valider.addActionListener((event)->valider());
		
		choixLabels.add(nomLabel);
		choixLabels.add(nom);
		choixLabels.add(style);
		choixLabels.add(dossierLabel);
		choixLabels.add(dossier);
		choixLabels.add(valider);
		
		return choixLabels;
	}
	
	public static MenuAjoutAlbum getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutAlbum(login);
		return instance;
	}
	
	private void choixDunAlbum() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = jfc.showOpenDialog(null); // ne te rend la main que si tu ferme
        
        if(ret == JFileChooser.APPROVE_OPTION) { // validation
        	File repSon = jfc.getSelectedFile();
        	File repImage = repSon;
	        File[] fichiersMP3 = repSon.listFiles(new FilenameFilter(){
	          public boolean accept(File dir, String name) {
	            return name.endsWith(".mp3");
	          }
	        });
	        File[] fichiersImage = repImage.listFiles(new FilenameFilter(){
		          public boolean accept(File dir, String name) {
		            return name.endsWith(".jpg");
		          }
		        });
	        cheminVersImageAssocie = fichiersImage[0].getAbsolutePath();
	        titreAssocie = new ArrayList<Titre>();
	        for(File monFile : fichiersMP3) {
	        	titreAssocie.add(new Titre(monFile.getName(), monFile.getAbsolutePath()));
	        }
        }
	}
	
	private void valider() {
		if(titreAssocie != null) {
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums().add(new Album(nom.getText(), style.getItemAt(style.getSelectedIndex()), titreAssocie, cheminVersImageAssocie));
			personnesDejaInscrite.getInstance().sauvegarder();
			MenuChanteur.getInstance(login).update();
			titreAssocie = null;
			nom.setText("Nom Album");
			style.setSelectedItem(listeStyle[0]);
			FenetreParametre.getInstance(login).dispose();
		}
	}
}