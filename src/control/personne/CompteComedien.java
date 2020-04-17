package control.personne;


/**
 *<b>CompteComedien</b> est la classe qui donne les caractéristiques de la fenetre d'un utilisateur comédien.
 *@see Compte
 *@author VIRGAUX Pierre
 **/

public class CompteComedien extends Compte{

	private static final long serialVersionUID = -5068429557852875543L;

	/**
	 *Permet de récupérer les éléments de la classe dont CompteComédien hérite
	 *@param passeword
	 *	Mot de passe
	 *@param talent
	 *	Talent de l'utilisateur
	 *@param CheminVersImage
	 *	Chemin vers l'image
	 *@param addresseMail
	 *	E-mail utilisateur
	 **/
	
	public CompteComedien(String passeword, String talent, String CheminVersImage, String addresseMail) {
		
		/**
		 *Récupère les définitions de compte car parent hérité
		 **/
		
		super(passeword, talent, CheminVersImage, addresseMail);
		// TODO Auto-generated constructor stub
	}
}
