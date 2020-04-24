package control.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import graphic.fenetre.FenetreLogin;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Voilà la fenètre graphique sans rien dedans. Voilà voilà voilà
		FenetreLogin.getInstance();
		//Ceci est un test
	}

}
