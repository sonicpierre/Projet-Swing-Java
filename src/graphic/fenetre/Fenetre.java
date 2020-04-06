package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	
	private static Fenetre instance;
	
	private Fenetre() {
		
		setSize(new Dimension(1000, 800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public static Fenetre getInstance() {
		if (instance == null)
			instance = new Fenetre();
		return instance;
	}


}