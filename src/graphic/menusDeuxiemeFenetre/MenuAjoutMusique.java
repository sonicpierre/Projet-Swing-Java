package graphic.menusDeuxiemeFenetre;

import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import control.elementSauv.personnesDejaInscrite;
import control.musique.Album;
import control.musique.Titre;
import graphic.fenetre.FenetreParametre;

@SuppressWarnings("serial")
public class MenuAjoutMusique extends JPanel{
	
	private static MenuAjoutMusique instance;
	
	private String login;
	private JTextField nouveauNom;
	private String cheminVersMusique;
	private JComboBox<String> listeAlbum;
	private JComboBox<String> libelleAssocie;
	
	private MenuAjoutMusique(String login) {
		this.login = login;
		this.setLayout(new FlowLayout());
		this.add(panelChoixMusique());
	}
	
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
	
	
	
	private void constructionListeAlbum() {
		List<String> listeTitreAlbum = new ArrayList<String>();
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums())
			listeTitreAlbum.add(monAlbum.getTitre());
		int index = 0;
		String[] listeConstruction = new String[listeTitreAlbum.size()];
		for(String monTitre : listeTitreAlbum) {
			listeConstruction[index] = monTitre;
			index++;
		}
		this.listeAlbum = new JComboBox<String>(listeConstruction);
	}
	
	public void update() {
		constructionListeAlbum();
	}
	
	private void valider() {
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeDeAlbums()) {
			if(monAlbum.getTitre().equals(listeAlbum.getSelectedItem())) {
				monAlbum.getChansonsDelAlbum().add(new Titre(nouveauNom.getText(), cheminVersMusique));
				break;
			}
		}
		MenuChanteur.getInstance(login).update();
	}
	
	private void choixFichier() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    cheminVersMusique = selectedFile.getAbsolutePath();
		}
	}
	
	public static MenuAjoutMusique getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutMusique(login);
		return instance;
	}
	
}
