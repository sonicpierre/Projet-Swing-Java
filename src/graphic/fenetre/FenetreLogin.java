package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

import graphic.menus.LoginMenu;

@SuppressWarnings("serial")
public class FenetreLogin extends JFrame {
	
	//On ajoute la partie principale
	
	private static FenetreLogin instance;
	
	private FenetreLogin() {
		Fenetre.getInstance();
		this.setTitle("Connexion");
		setSize(new Dimension(400, 200));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().add(LoginMenu.getInstance().getMesOnglets());
		setVisible(true);
		setResizable(false);
		
	}
	
	public static FenetreLogin getInstance() {
		if (instance == null)
			instance = new FenetreLogin();
		return instance;
	}


}