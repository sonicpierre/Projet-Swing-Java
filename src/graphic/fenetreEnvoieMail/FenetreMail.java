package graphic.fenetreEnvoieMail;


import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")

/**
 *<p><b>FenetreMail</b> est la classe qui permet de générer la fenetre
 *d'envoie du message.
 *</p>
 *@author VIRGAUX Pierre
 **/

public class FenetreMail extends JFrame {
	
	
	private static FenetreMail instance;

	/**
	 *Permet le lancement de la fenetre d'envoie
	 **/
	
	private FenetreMail() {
		
		/**
		 *Initialisation des dimensions de la fenetre
		 **/
		
		setSize(new Dimension(800, 500));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().add(MenuDeMail.getInstance().getMenuMail());
		
		/**
		 *On annule la possibilité de redimenssioner la fenetre
		 **/
		
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 *Création d'une fenetre
	 *@return Fenetre d'envoie d'e-mail
	 **/
	
	public static FenetreMail getInstance() {
		if (instance == null)
			instance = new FenetreMail();
		return instance;
	}

}