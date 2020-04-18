package graphic.menusDeuxiemeFenetre;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;
import graphic.fenetre.FenetreParametre;
import graphic.fenetreEnvoieMail.FenetreMail;
import graphic.fenetreEnvoieMail.MenuDeMail;

public class TopMenuDescriptif{
	
	JMenuBar menuFinal;//BARRE DU DESSUS
	
	private static TopMenuDescriptif instance;
	
	private String login;
	
	private TopMenuDescriptif(String login) {
		menuFinal = new JMenuBar();
		menuFinal.add(baseDeDonneMenu());
		menuFinal.add(contactMenu());
		this.login = login;
	}
	
	public JMenuBar getMenuFinal() {
		return menuFinal;
	}
	
	public void setMenuFinal(JMenuBar menuFinal) {
		this.menuFinal = menuFinal;
	}
	
	private JMenu contactMenu() {
		JMenu contacter = new JMenu("Contact");
		
		JMenuItem autreUtilisateur = new JMenuItem("Autre utilisateur");
		autreUtilisateur.addActionListener((event)->ouvertureFenetreMail());
		
		contacter.add(autreUtilisateur);
		
		return contacter;
	}
	
	private JMenu baseDeDonneMenu() {
		JMenu baseDeDonne = new JMenu("Donnée");
		JMenuItem creer = new JMenuItem("CréerBase");
		JMenuItem modifierLaBDD = new JMenuItem("Modifier");
		JMenuItem paramBDD = new JMenuItem("Paramètres");
		JMenuItem deconnexion = new JMenuItem("Déconnexion");
		deconnexion.addActionListener((event)->deconnexion());
		paramBDD.addActionListener((event)->FenetreParametre.getInstance(login).setVisible(true));
		
		baseDeDonne.add(creer);
		baseDeDonne.addSeparator();
		baseDeDonne.add(modifierLaBDD);
		baseDeDonne.addSeparator();
		baseDeDonne.add(paramBDD);
		baseDeDonne.addSeparator();
		baseDeDonne.add(deconnexion);
		
		return baseDeDonne;
	}
	
	
	private void ouvertureFenetreMail() {
		FenetreMail.getInstance().setVisible(true);
		MenuDeMail.getInstance().getMessage().setText("");
		MenuDeMail.getInstance().getAdresseMailRentre().setText("");
	}
	
	
	public void deconnexion() {
		FenetreFond.getInstance().changerFenetre(null);
		FenetreLogin.getInstance().setVisible(true);
	}
	
	public static TopMenuDescriptif getInstance(String login) {
		if (instance == null)
			instance = new TopMenuDescriptif(login);
		return instance;
	}
}