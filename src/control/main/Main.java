package control.main;

import graphic.fenetre.FenetreLogin;

public class Main {

	public static void main(String[] args) {
		//Voilà la fenètre graphique sans rien dedans.
		FenetreLogin.getInstance();
		MoteurGraphique.getInstance();
	}

}
