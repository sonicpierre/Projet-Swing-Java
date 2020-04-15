package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

import graphic.menusDeuxiemeFenetre.MenuAjoutAlbum;
import graphic.menusParametreFenetre.MenuFinalParametre;

@SuppressWarnings("serial")
public class FenetreParametre extends JFrame{
	//On ajoute la partie principale
	
	private static FenetreParametre instance;
	private final static Dimension dimChoixAlbum = new Dimension(600,100);
	private final static Dimension dimParametre = new Dimension(600,500);
	
	String login;
	
	private FenetreParametre(String login){
		this.login = login;
		FenetreFond.getInstance();
		this.setTitle("Paramètre");
		setSize(new Dimension(600,500));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void ajoutParametre() {
		dispose();
		setSize(dimParametre);
		this.getContentPane().add(MenuFinalParametre.getInstance(login));
		setVisible(true);
	}
	
	public void ajoutAlbumFenetre() {
		dispose();
		setSize(dimChoixAlbum);
		this.getContentPane().add(MenuAjoutAlbum.getInstance(login));
		setVisible(true);
	}
	
	public static FenetreParametre getInstance(String login) {
		if (instance == null)
			instance = new FenetreParametre(login);
		return instance;
	}
}
