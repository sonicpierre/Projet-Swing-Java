package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	
	//On ajoute la partie principale
	//Panneau paneau;
	
	private static Fenetre instance;
	
	private Fenetre() {

		setSize(new Dimension(1200, 800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JLabel ImageFond = new JLabel(new ImageIcon("ImageDeFond/ImageAnime.gif"));
		this.add(ImageFond);
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