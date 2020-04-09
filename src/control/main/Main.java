package control.main;

import graphic.fenetre.FenetreLogin;

public class Main {

	public static void main(String[] args) {
		//Voilà la fenètre graphique sans rien dedans. Changements...
		FenetreLogin.getInstance();
		MoteurGraphique.getInstance();
	}

}
