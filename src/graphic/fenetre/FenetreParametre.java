package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

import graphic.menusDeuxiemeFenetre.MenuAjoutAlbum;
import graphic.menusParametreFenetre.MenuFinalParametre;


//
@SuppressWarnings("serial")
public class FenetreParametre extends JFrame{
	//On ajoute la partie principale
	//DEFINITION D'UNE FENETRE PREMETTANT 
	//FENETRE LOGIN MDP
	//FEN FOND
	//FN D'ORPERATIONS
	
	private static FenetreParametre instance;
	private final static Dimension dimChoixAlbum = new Dimension(600,100);//DIMENSION FEN CHOIX ALBUM
	private final static Dimension dimParametre = new Dimension(600,500);//DIM FEN PARAM
	
	String login;//CLÉ VERS UTILISATEUR
	
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
	
	//IDEM EN BAS
	public void ajoutParametre() {
		dispose();
		this.getContentPane().removeAll();
		setSize(dimParametre);
		this.getContentPane().add(MenuFinalParametre.getInstance(login));
		setVisible(true);
		
	}
	
	//
	public void ajoutAlbumFenetre() {
		dispose();//ON REND LA FEN INVISIBLE
		this.getContentPane().removeAll();//SUPPREMSSION CONTENU
		setSize(dimChoixAlbum);//REDIMENSION
		this.getContentPane().add(MenuAjoutAlbum.getInstance(login));//LECTURE CONTENU + AJOUT DU MENU CORRESPONDZNT À L'AJOUT ALBUM
		setVisible(true);//VISIBLITE DELA FEN
	}
	
	public static FenetreParametre getInstance(String login) {
		if (instance == null)
			instance = new FenetreParametre(login);
		return instance;
	}
}
