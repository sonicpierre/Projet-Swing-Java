package graphic.menusDeuxiemeFenetre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.activite.Album;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;

/**
 * La classe <b>MenuAlbum</b> permet d'avoir une vision des Albums qui ont été ajouté :
 **/

@SuppressWarnings("serial")
public class MenuAlbum extends JPanel{
	
	private static MenuAlbum instance;

	
	//Ici on a toujours le nom de l'utilisateur qui est une clef pour lire le fichier de sauvegarde
	
	private String login;

	/**
	 * Ici il s'agit de la méthode qui permet de construire le panel
	 * @param login
	 */

	private MenuAlbum(String login) {
		this.login = login;
		//Par soucis de propreté on appelle une fonction histoire de pas tout mettre dans le constructeur.
		constructionPanel();
	}
	
	/**
	 * Cette fonction va construire grace au fichier sauvegarder le panel
	 * @see personnesDejaInscrite
	 */
	
	private void constructionPanel() {
		//Ici on a un compteur qui permet de savoir combien on a d'albums à afficher.
		int compteur = 0;
		//On parcoure une première foi les listes pour compter les Albums.
		for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
			for(Album monAlbum : monArtiste.getMaListeDeAlbums())
				compteur++;
		//On adapte le layout en fonction du nombre d'albums à afficher, on voudrait 4 albums par ligne
		this.setLayout(new GridLayout(compteur%4 + 1, 4));
		//On passe tous les artistes.
		for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
			//On passe chaque album de chaque artiste
			for(Album monAlbum : monArtiste.getMaListeDeAlbums()) {
				//On ajoute au panel chacune des cases construites.
				this.add(constructionCase(monAlbum, monArtiste));
			}
	}
	
	/**
	 * Cette fonction permet de construire chacune des cases
	 * Une case est photo de l'album suivi de son nom et auteur
	 * 
	 * @param album
	 * @param artiste
	 * @return La case construite
	 */
	
	private JPanel constructionCase(Album album, Artiste artiste) {
		//Le JPanel est un FlowLayout on affiche tout les éléments les uns à côté des autres.
		JPanel maCase = new JPanel(new FlowLayout());
		//On récupère l'image et on la redimentionne.
		ImageIcon monImage = new ImageIcon(new ImageIcon(album.getCheminVersImageAssocie()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
		JLabel maPhoto = new JLabel(monImage);
		//On sépare les Labels du JLabel pour la photo
		JPanel lesLabels = new JPanel(new GridLayout(2,1));
		JLabel monTitre = new JLabel(album.getTitre());
		JLabel monAuteur = new JLabel(artiste.getNom() + " " + artiste.getPrenom());
		
		lesLabels.add(monTitre);
		lesLabels.add(monAuteur);
		
		maCase.add(maPhoto);
		maCase.add(lesLabels);
		
		return maCase;
	}
	
	/**
	 * Cette fonction permet de mettre à jour le panel en le reconstruisant.
	 * Elle est utilisée à chaque fois qu'on ajoutera un nouvel album par exemple.
	 */
	
	public void update() {
		this.removeAll();
		this.constructionPanel();
		this.validate();
	}
	

	/**
	 * Cette fonction permet d'accéder à l'objet MenuAlbum
	 * 
	 * @param login
	 * @return L'objet singleton
	 */

	
	public static MenuAlbum getInstance(String login) {
		if (instance == null)
			instance = new MenuAlbum(login);
		return instance;
	}

}
