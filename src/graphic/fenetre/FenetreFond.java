package graphic.fenetre;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class FenetreFond extends JFrame {
	
	//On ajoute la partie principale
	//Panneau paneau;
	
	private static FenetreFond instance;
	private JLabel ImageFond;
	
	private FenetreFond() {
		//Fenêtre de démarrage 
		setSize(new Dimension(1200, 800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		ImageFond = new JLabel(new ImageIcon("ImageDeFond/ImageAnime.gif"));
		this.add(ImageFond);
		setUndecorated(true);
		setVisible(true);
		setResizable(false);
	}
	
	public static FenetreFond getInstance() {
		if (instance == null)
			instance = new FenetreFond();
		return instance;
	}

	public void changerFenetre() {
		dispose();
		setUndecorated(false);
		this.remove(ImageFond);
		getContentPane().setBackground(new Color(100,100,100));
		setResizable(true);
		setVisible(true);
	}

}