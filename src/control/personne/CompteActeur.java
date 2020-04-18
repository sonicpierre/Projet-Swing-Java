package control.personne;

/**
 *<b>CompteActeur</b> est la classe qui donne les caractéristiques de la fenetre d'un utilisateur Acteur.
 *@see Compte
 *@author VIRGAUX Pierre
 **/

public class CompteActeur extends Compte{


	private static final long serialVersionUID = -852346060469113803L;

	/**
	 *Permet de récupérer les éléments de la classe dont CompteActeur hérite
	 *@param passeword
	 *	Mot de passe
	 *@param talent
	 *	Talent de l'utilisateur
	 *@param CheminVersImage
	 *	Chemin vers l'image
	 *@param addresseMail
	 *	E-mail utilisateur
	 **/
	
	public CompteActeur(String passeword, String talent, String CheminVersImage, String addresseMail) {
		
		/**
		 *Récupère les définitions de compte car parent hérité
		 **/
		
		super(passeword, talent, CheminVersImage, addresseMail);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
