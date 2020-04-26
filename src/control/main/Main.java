package control.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import graphic.fenetre.FenetreLogin;

public class Main {
	
	
	public static void main(String[] args) {
		
		/**
		 *Le try catch permet d'ajouter le style numbus, permettant de rajouter un aspect comptémporain à l'application
		 **/

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 *Lancement fenêtre graphique
		 **/
		
		FenetreLogin.getInstance();
	}

}
