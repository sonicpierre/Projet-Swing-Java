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
	 *@param type                 
	 *   Type du titre
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

	public void ajouterMusique(Titre titreAAjouter) {
		this.getChansonsDelAlbum().add(titreAAjouter);
		personnesDejaInscrite.getInstance().sauvegarder();
	}

	//Permet de chercher pour suppression
	
	public void rechercheSuppressionMusique(Titre musiqueATrouver) {
		for (Titre monTitre : this.getChansonsDelAlbum())
			if (monTitre.equals(musiqueATrouver)) {
				this.getChansonsDelAlbum().remove(monTitre);
				personnesDejaInscrite.getInstance().sauvegarder();
				break;
			}
	}
	
	/**
	 *Comparaison album afin de ne pas prendre en compte le chemin vers l'image.
	 *Ainsi un album sera def par son titre et son chemin
	 **/
	
	@Override
	public boolean equals(Object autreAlbum) {
		Album artistePourComparaison = (Album) autreAlbum;
		if(this.titre.equals(artistePourComparaison.getTitre()) && this.cheminVerDossier.equals(artistePourComparaison.getCheminVerDossier()))
			return true;
		
		return false;
	}
	
	/**
	 *On associe un chiffree à chaqie album afin de le retrouver plus rapidemment
	 **/
	
	@Override
	public int hashCode() {
		int compteurFinal = 0;
		
		/**
		 *NB : On prend chaque lettre du titre (tab de carac) en demandant son equivalent ascii.
		 *On somme ensuite le tout afin de nous donner le chiffre
		 **/
		
		char[] monTitre = this.getTitre().toCharArray();
		
		for(char titre : monTitre)
			compteurFinal+= (int) titre;

		
		return compteurFinal;
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
	 * 
	 * @param chansonsDelAlbum
	 **/

	public void setChansonsDelAlbum(Set<Titre> chansonsDelAlbum) {
		this.chansonsDelAlbum = chansonsDelAlbum;
	}

	/**
	 * Récupération de la localisation du chemin de l'image associée
	 * 
	 * @return Localisation image associée
	 **/

	public String getCheminVersImageAssocie() {
		return cheminVersImageAssocie;
	}

	/**
	 * Initialisation du chemin de l'image album
	 * 
	 * @param cheminVersImageAssocie
	 **/

	public void setCheminVersImageAssocie(String cheminVersImageAssocie) {
		this.cheminVersImageAssocie = cheminVersImageAssocie;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
