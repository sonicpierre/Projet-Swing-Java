package graphic.fenetreEnvoieMail;


import java.awt.Dimension;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class FenetreMail extends JFrame {
	
	
	private static FenetreMail instance;

	
	private FenetreMail() {
		//Fenêtre de démarrage 
		setSize(new Dimension(800, 500));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().add(MenuDeMail.getInstance().getMenuMail());
		setResizable(false);//NON POSSIBILITÉ DE REDIMENSIONNER LA FENETRE
		setVisible(true);
	}
	
	public static FenetreMail getInstance() {
		if (instance == null)
			instance = new FenetreMail();
		return instance;
	}

}