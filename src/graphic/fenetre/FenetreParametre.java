package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

import graphic.menusParametreFenetre.MenuFinalParametre;

@SuppressWarnings("serial")
public class FenetreParametre extends JFrame{
	//On ajoute la partie principale
	
	private static FenetreParametre instance;
	
	String login;
	
	private FenetreParametre(String login) {
		this.login = login;
		FenetreFond.getInstance();
		this.setTitle("Paramètre");
		setSize(new Dimension(600,500));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().add(new MenuFinalParametre(login));
		this.setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	
	
	public static FenetreParametre getInstance(String login) {
		if (instance == null)
			instance = new FenetreParametre(login);
		return instance;
	}
}
