package graphic.fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import graphic.menusDeuxiemeFenetre.EspaceDeconnexion;
import graphic.menusDeuxiemeFenetre.TopMenuDescriptif;


@SuppressWarnings("serial")
public class FenetreFond extends JFrame {
	
	//On ajoute la partie principale
	//Panneau paneau;
	
	private static FenetreFond instance;
	private JLabel ImageFond;
	JMenuBar maMenuBarre;
	EspaceDeconnexion monEspaceDeconnexion;
	
	boolean FenetreFondDepartActive = true;
	
	private FenetreFond() {
		//Fenêtre de démarrage 
		setSize(new Dimension(1200, 800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		ImageFond = new JLabel(new ImageIcon("ImageDeFond/ImageAnime.gif"));
		this.add(ImageFond);
		setUndecorated(true);//ENLEVE LA BARRE DU DESSUS POUR REDIMENTIONNER ET FERMER LA FENETRE
		setVisible(true);
		setResizable(false);//NON POSSIBILITÉ DE REDIMENSIONNER LA FENETRE
		setFocusable(true);
	}
	
	public static FenetreFond getInstance() {
		if (instance == null)
			instance = new FenetreFond();
		return instance;
	}

	public void changerFenetre(String login) {
		if(FenetreFondDepartActive) {
			dispose();
			setUndecorated(false);
			this.remove(ImageFond);
			getContentPane().setBackground(new Color(100,100,100));
			maMenuBarre = TopMenuDescriptif.getInstance().getMenuFinal();
			this.add(maMenuBarre, BorderLayout.NORTH);
			monEspaceDeconnexion = new EspaceDeconnexion(login);
			this.add(monEspaceDeconnexion.getMonPannelDeconexion(), BorderLayout.EAST);
			setResizable(true);
			setVisible(true);
			setFocusable(true);
			FenetreFondDepartActive = false;
		}
		else {
			dispose();
			ImageFond = new JLabel(new ImageIcon("ImageDeFond/ImageAnime.gif"));
			this.add(ImageFond);
			this.remove(maMenuBarre);
			this.remove(monEspaceDeconnexion.getMonPannelDeconexion());
			setUndecorated(true);
			setResizable(false);
			setFocusable(true);
			FenetreFondDepartActive = true;
			setVisible(true);
			FenetreLogin.getInstance().setVisible(true);
		}
	}

}