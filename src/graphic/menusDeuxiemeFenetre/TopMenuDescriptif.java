package graphic.menusDeuxiemeFenetre;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.filechooser.FileSystemView;

import control.csvBDD.FichierCsv;
import control.personne.Artiste;
import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;
import graphic.fenetreEnvoieMail.FenetreMail;
import graphic.fenetreEnvoieMail.MenuDeMail;


@SuppressWarnings("serial")

/**
 *<b>TopMenuDescriptif</b> est la classe qui permet de gérer la barre de dessus des fenetres artistes
 *@author BEZIAT Lucille
 *@version 2.0
 **/

public class TopMenuDescriptif extends JMenuBar{
	
	/**
	 *Déclaration de l'instance du menu descriptif
	 **/
	
	private static TopMenuDescriptif instance;
	
	/**
	 *Déclaration du login utilisateur
	 **/
	
	private String login;
	
	/**
	 *Déclaration de l'artiste
	 **/
	
	private Artiste artiste;
	
	/**
	 *Permet de générer le menu descriptif utilisateur
	 *@param login
	 *	Login utilisateur
	 **/
	
	private TopMenuDescriptif(String login) {
		this.login = login;
		this.artiste = null;

		this.add(baseDeDonneMenu());
		this.add(contactMenu());
		
	}
	
	/**
	 *Permet de définir les actions du menu de contact réservé à l'administrateur
	 *@see MenuRaccourcis
	 *@return Contact des utilisateur
	 **/
	
	private JMenu contactMenu() {
		JMenu contacter = new JMenu("Contact");
		contacter.add(MenuRaccourcis.getInstance(login).actContacter);
		
		return contacter;
	}
	
	/**
	 *Permet de gérer le menu chanteur en utilisant les raccourcis et en précisant sa localisation
	 *@see MenuRaccourcis
	 *@return Chanson
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
	
	/**
	 *Permet de gérer les actions du menu de lecture musique
	 *@see MenuRaccourcis
	 **/
	
	private JMenu menuPlayer() {
		JMenu player = new JMenu("Player");

		player.add(MenuRaccourcis.getInstance(login, artiste).actPlay);
		player.add(MenuRaccourcis.getInstance(login, artiste).actStop);
		player.add(MenuRaccourcis.getInstance(login, artiste).actReset);
		return player;
	}
	
	/**
	 *Suppression du contenu de la barre de menu puis on remet en place les items propres à la barre chanteur
	 **/
	
	public void updateVersChanteur() {
		this.removeAll();
		this.add(menuChanteur());
		this.add(menuPlayer());
		this.add(contactMenu());
		this.validate();
	}
	
	/**
	 *Permet la mise à jour de la fenetre pour un acteur ou un comédien
	 **/
	
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
	 *Retour en arriève, sans sauvegarder la page antérieure
	 **/
	
	public void updateVersInitial() {
		this.removeAll();
		
		this.add(baseDeDonneMenu());
		this.add(contactMenu());
	}
	
	/**
	 *Permet de gérer la base de données et est reservé à l'administrateur de l'application
	 *@return Base de données
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
	
	/**
	 *Permet l'ouverture de la fenetre d'envpoie e-mail
	 *@see FenetreMail
	 *@see MenuDeMail
	 **/
	
	public void ouvertureFenetreMail() {
		FenetreMail.getInstance().setVisible(true);
		MenuDeMail.getInstance().getMessage().setText("");
		MenuDeMail.getInstance().getAdresseMailRentre().setText("");
	}
	
	/**
	 *Permet la déconnexion et le retour au menu principal
	 *@see MenuMusique
	 *@see FenetreFond
	 *@see FenetreLogin
	 **/
	
	public void deconnexion() {
		if(MenuMusique.getInstance(login).getTitreEnCoursDeLecture()!=null)
			MenuMusique.getInstance(login).getTitreEnCoursDeLecture().stop();
		/**
		 *Pas de barre de menu sur la fenetre de fond
		 **/
		
		FenetreFond.getInstance().remove(this);
		
		/**
		 *Suppression du menu prinipal
		 **/
		
		FenetreFond.getInstance().remove(MenuPrincipal.getInstance(login));
		FenetreFond.getInstance().changerFenetre(login);
		FenetreLogin.getInstance().setVisible(true);
	}
	
	/**
	 *Récupère l'artiste
	 *@return Artiste
	 **/
	
	public Artiste getArtiste() {
		return artiste;
	}

	/**Initialisation de l'artiste
	 *@param artiste
	 * 	Artiste
	 **/
	
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	/**
	 *Permet de copier les informations et de remplir le fichier CSV
	 *@see FichierCsv
	 **/
	
	public void copieEtRemplissage() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = jfc.showOpenDialog(null);
		File selectedFile;
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    selectedFile = jfc.getSelectedFile();

		    File f = new File("Bibliothèque/" + selectedFile.getName());
		    try {
				copy(selectedFile, f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    FichierCsv c = new FichierCsv(login);
			List<String> resultat=c.fichierCsvList(c.getFichier("Bibliothèque/" +f.getName()+"/data.csv"));
			c.enregistrment(resultat);
		}
	}
	
	/**
	 *Permet de copier le fichier dans un repertoire
	 *@param src
	 *	Dossier source
	 *@param dest
	 *	Destination de copie
	 **/
	
	public static void copy(File src, File dest) throws IOException{
	    
	      if(src.isDirectory()){
	      /**
	       *Si le répertoire n'existe pas, créez-le
	       */
	        if(!dest.exists()){
	           dest.mkdir();
	           System.out.println("Dossier "+ src + "  > " + dest);
	        }
	        /**
	         *Liste le contenu du répertoire
	         */
	        String files[] = src.list();
	        
	        for (String f : files) {
	           /**
	            *Construire la structure des fichiers src et dest
	            */
	           File srcF = new File(src, f);
	           File destF = new File(dest, f);
	           /**
	            *Copie récursive
	            */
	           copy(srcF, destF);
	        }
	      }else{
	          /**
	           *si src est un fichier, copiez-le.
	           */
	          InputStream in = new FileInputStream(src);
	          OutputStream out = new FileOutputStream(dest); 
	                           
	          byte[] buffer = new byte[1024];
	          int length;
	          /**
	           *Copie le contenu du fichier
	           */
	          while ((length = in.read(buffer)) > 0){
	            out.write(buffer, 0, length);
	          }
	 
	          in.close();
	          out.close();
	          System.out.println("Fichier " + src + " > " + dest);
	      }
	  }
	
	/**
	 *Instanciation du menu descriptif
	 *@param login
	 *	Login utilisateur
	 *@return Menu descriptif
	 **/
	
	public static TopMenuDescriptif getInstance(String login) {
		if (instance == null)
			instance = new TopMenuDescriptif(login);
		return instance;
	}
}