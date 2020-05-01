package graphic.menusDeuxiemeFenetre;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileSystemView;

import control.personne.Artiste;
import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;
import graphic.fenetre.FenetreParametre;
import graphic.fenetreEnvoieMail.FenetreMail;
import graphic.fenetreEnvoieMail.MenuDeMail;


//BARRE DE HAUT SELON LE TALENT DE L'UTILISATEUR
//CE N'EST PA PRATIK DAVOIU UN BARR POUR CHAQ PEROSN => COMBINAISON DES PREFERENCES
@SuppressWarnings("serial")
public class TopMenuDescriptif extends JMenuBar{
	
	
	private static TopMenuDescriptif instance;
	
	private String login;
	private Artiste artiste;
	
	private TopMenuDescriptif(String login) {
		this.login = login;
		this.artiste = null;//CAR ON A CLIQUER SUR AUCUN ARTISTE

		this.add(baseDeDonneMenu());
		this.add(contactMenu());
		
	}
	
	private JMenu contactMenu() {//RESERVER A L'ADMIN => IL CONTACTE TOUT LE MONDE
		JMenu contacter = new JMenu("Contact");
		contacter.add(MenuRaccourcis.getInstance(login).actContacter);
		
		return contacter;
	}
	
	/**
	 *On utilise tous les raccoursi effectue, en precisant sa localisation
	 **/
	
	private JMenu menuChanteur() {
		JMenu chanson = new JMenu("Musique");

		chanson.add(MenuRaccourcis.getInstance(login, artiste).actAjoutMus);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actSuppressionMusique);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actAjoutAlb);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actParametre);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actBack);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actDeco);
		return chanson;
	}
	
	
	private JMenu menuPlayer() {
		JMenu player = new JMenu("Player");

		player.add(MenuRaccourcis.getInstance(login, artiste).actPlay);
		player.add(MenuRaccourcis.getInstance(login, artiste).actStop);
		player.add(MenuRaccourcis.getInstance(login, artiste).actReset);
		return player;
	}
	
	/**
	 *Suppression du contenu de la barre de menu puis on remet en place les items propres à la brre chanteur
	 **/
	
	public void updateVersChanteur() {
		this.removeAll();
		
		this.add(menuChanteur());
		this.add(menuPlayer());
		this.add(contactMenu());
		this.validate();
	}
	
	public void updateVersActeurComedien() {
		this.removeAll();
		JMenu representation = new JMenu("Representation");
		representation.add(MenuRaccourcis.getInstance(login).actAjoutRepresentation);
		representation.add(MenuRaccourcis.getInstance(login, artiste).actBack);
		representation.add(MenuRaccourcis.getInstance(login, artiste).actDeco);
		this.add(representation);
		this.add(contactMenu());
	}
	
	/**
	 *Retour en arriève, sans suavegarder la pae d'avant
	 **/
	
	public void updateVersInitial() {
		this.removeAll();
		
		this.add(baseDeDonneMenu());
		this.add(contactMenu());
	}
	
	/**
	 *Menu poemettant de gerer la bdd, et qui est reserve à l'admin
	 **/
	
	private JMenu baseDeDonneMenu() {
		JMenu baseDeDonne = new JMenu("Base de données");
		
		
		/**
		 *Petits separateur entre items
		 **/
		
		baseDeDonne.add(MenuRaccourcis.getInstance(login).actAjoutArtiste);
		
		baseDeDonne.addSeparator();
		baseDeDonne.add(MenuRaccourcis.getInstance(login).actRemplirParUnCSV);
		baseDeDonne.addSeparator();
		baseDeDonne.add(MenuRaccourcis.getInstance(login).actRemplirParUneBDD);
		baseDeDonne.addSeparator();
		baseDeDonne.add(MenuRaccourcis.getInstance(login).actDeco);
		
		return baseDeDonne;
	}
	
	public void ouvertureFenetreMail() {
		FenetreMail.getInstance().setVisible(true);
		MenuDeMail.getInstance().getMessage().setText("");
		MenuDeMail.getInstance().getAdresseMailRentre().setText("");
	}
	
	/**
	 *Revenir au menu
	 **/
	
	public void deconnexion() {
		if(MenuMusique.getInstance(login).getTitreEnCoursDeLecture()!=null)
			MenuMusique.getInstance(login).getTitreEnCoursDeLecture().stop();
		/**
		 *Pas de barre de menu sur la fenetre de fond
		 **/
		
		FenetreFond.getInstance().remove(this);
		
		/**
		 *Supp menu prinip*/
		FenetreFond.getInstance().remove(MenuPrincipal.getInstance(login));
		FenetreFond.getInstance().changerFenetre(login);
		FenetreLogin.getInstance().setVisible(true);
	}
	
	
	public Artiste getArtiste() {
		return artiste;
	}


	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public void copieEtRemplissage() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		File selectedFile;
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    selectedFile = jfc.getSelectedFile();
		    copier(selectedFile, new File("DataCSV/" + selectedFile.getName()));
		}
	}

	private static boolean copier(File source, File dest) { 
	    try (InputStream sourceFile = new java.io.FileInputStream(source);  
	            OutputStream destinationFile = new FileOutputStream(dest)) { 
	        // Lecture par segment de 0.5Mo  
	        byte buffer[] = new byte[512 * 1024]; 
	        int nbLecture; 
	        while ((nbLecture = sourceFile.read(buffer)) != -1){ 
	            destinationFile.write(buffer, 0, nbLecture); 
	        } 
	    } catch (IOException e){ 
	        e.printStackTrace(); 
	        return false; // Erreur 
	    } 
	    return true; // Résultat OK   
	}


	public static TopMenuDescriptif getInstance(String login) {
		if (instance == null)
			instance = new TopMenuDescriptif(login);
		return instance;
	}
}