package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	
	//On ajoute la partie principale
	Panneau paneau;
	
	private static Fenetre instance;
	
	private Fenetre() {

		setSize(new Dimension(1000, 800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//On instencie notre panneau
		paneau = Panneau.getInstance();
		paneau.setFocusable(false);
		getContentPane().add(paneau);
		setUndecorated(true);
		setVisible(true);
		setResizable(false);
		
	}
	
	public static Fenetre getInstance() {
		if (instance == null)
			instance = new Fenetre();
		return instance;
	}


}