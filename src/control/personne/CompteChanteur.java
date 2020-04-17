package control.personne;

import java.util.ArrayList;

import control.musique.Album;
import control.musique.Titre;

/**
 *<b>CompteChanteur</b> est la classe qui donne les caractéristiques de la fenetre d'un utilisateur chanteur.
 *@see Compte
 *@author VIRGAUX Pierre
 **/

public class CompteChanteur extends Compte{

	private static final long serialVersionUID = 3745382508866010183L;

	/**
	 *Permet de récupérer les éléments de la classe dont CompteChanteur hérite
	 *@param passeword
	 *	Mot de passe
	 *@param talent
	 *	Talent de l'utilisateur
	 *@param CheminVersImage
	 *	Chemin vers l'image
	 *@param addresseMail
	 *	E-mail utilisateur
	 **/
	
	public CompteChanteur(String passeword, String talent, String CheminVersImage, String addresseMail) {
		
		/**
		 *Récupère les définitions de compte car parent hérité
		 **/
		
		super(passeword, talent, CheminVersImage, addresseMail);
		
		/**
		 *Tableau d'albums
		 *@see Album
		 **/
		
		maListeDeAlbums = new ArrayList<Album>();
	}

	
}
