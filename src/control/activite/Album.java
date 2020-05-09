package control.activite;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import control.elementSauv.personnesDejaInscrite;

/**
 * La classe <b>Album</b> permet de définir les caractèristiques de l'album :
 * <lu>
 * <li>Titre</li>
 * <li>Type</li>
 * <li>Chemin vers le dossier</li>
 * <li>Chemin vers l'image associée au dossier</li> </lu> Elle est Serializable
 * car on y sauvegarde des informations
 * <p>
 * <b>NB : </b> On utilisera le chemin absolu puisque les fichiers sont
 * volumineux.Ainsi on stocke le chemin du dossier plutot que le dossier
 * lui-même
 * </p>
 * @author VIRGAUX Pierre
 * @version 2.0
 **/

public class Album implements Serializable {

	private static final long serialVersionUID = -7477910052318976290L;

	/**
	 * Nom du titre
	 **/

	private String titre;

	/**
	 * Type du titre
	 **/

	private String cheminVerDossier;

	/**
	 * Chemin vers l'image associée au titre
	 **/

	private String cheminVersImageAssocie;

	/**
	 * Set de titres, afin d'éviter les doublons dans la liste pour ne pas avoir deux le même titre
	 * @see Titre
	 **/

	private Set<Titre> chansonsDelAlbum;

	private boolean selected;

	/**
	 * Initialisation des composentes de l'album
	 * 
	 *@param titre               
	 *    Titre
	 *@param titreDeLalbum         
	 *  Titre album
	 *@param cheminVersImageAssocie 
	 *	Localisation du chemin associé
	 **/

	public Album(String titre, Set<Titre> titreDeLalbum, String cheminVersImageAssocie) {
		
		
		
		this.setTitre(titre);
		this.chansonsDelAlbum = new HashSet<Titre>();
		this.setChansonsDelAlbum(titreDeLalbum);
		this.setCheminVersImageAssocie(cheminVersImageAssocie);
		this.selected = false;

		for (Titre montTitre : titreDeLalbum)
			montTitre.setAlbumAssocie(this);
	}

	
	/**
	 *Permet d'ajouter une musique à un album
	 *@param titreAAjouter
	 *	Titre de la musique à ajouter
	 *@see personnesDejaInscrite
	 **/
	
	
	public void ajouterMusique(Titre titreAAjouter) {
		this.getChansonsDelAlbum().add(titreAAjouter);
		personnesDejaInscrite.getInstance().sauvegarder();
	}

	/**Permet de chercher une musique pour sa suppression
	 *@param musiqueATrouver
	 *	Musique à supprimer
	 **/
	
	public void rechercheSuppressionMusique(Titre musiqueATrouver) {
		for (Titre monTitre : this.getChansonsDelAlbum())
			if (monTitre.equals(musiqueATrouver)) {
				this.getChansonsDelAlbum().remove(monTitre);
				personnesDejaInscrite.getInstance().sauvegarder();
				break;
			}
	}
	

	/**
	 * Récupère le titre
	 * @return
	 * Titre
	 **/

	public String getTitre() {
		return titre;
	}

	/**
	 * Initialisation titre
	 * 
	 * @param titre
	 **/

	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Récupère le chemin vers le dossier
	 * 
	 * @return cheminVerDossier
	 **/

	public String getCheminVerDossier() {
		return cheminVerDossier;
	}

	/**
	 * Initialisation de la localisation du dossier
	 * 
	 * @param cheminVerDossier
	 **/

	public void setCheminVerDossier(String cheminVerDossier) {
		this.cheminVerDossier = cheminVerDossier;
	}

	/**
	 * Récupère les chansons de l'album
	 * 
	 * @return chanson de l'album
	 **/

	public Set<Titre> getChansonsDelAlbum() {
		return chansonsDelAlbum;
	}

	/**
	 * Initialisation des chansons de l'album
	 * @param chansonsDelAlbum
	 * 	Chanson de l'album
	 **/

	public void setChansonsDelAlbum(Set<Titre> chansonsDelAlbum) {
		this.chansonsDelAlbum = chansonsDelAlbum;
	}

	/**
	 * Récupération de la localisation du chemin de l'image associée
	 * @return Localisation image associée
	 **/

	public String getCheminVersImageAssocie() {
		return cheminVersImageAssocie;
	}

	/**
	 * Initialisation du chemin de l'image album
	 * @param cheminVersImageAssocie
	 * 	Localisation de la musique associée
	 **/

	public void setCheminVersImageAssocie(String cheminVersImageAssocie) {
		this.cheminVersImageAssocie = cheminVersImageAssocie;
	}
	
	/**
	 *Récupère la musique selectionnée
	 *@return Musique selectionnée
	 **/
	
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 *Initialise la musique selectionnée
	 *@param selected
	 *	Musique selectionnée
	 **/
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}