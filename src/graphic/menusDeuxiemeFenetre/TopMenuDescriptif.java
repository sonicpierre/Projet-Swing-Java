package graphic.menusDeuxiemeFenetre;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;
import graphic.fenetre.FenetreParametre;
import graphic.fenetreEnvoieMail.FenetreMail;
import graphic.fenetreEnvoieMail.MenuDeMail;

@SuppressWarnings("serial")
public class TopMenuDescriptif extends JMenuBar{
	
	
	private static TopMenuDescriptif instance;
	
	private String login;
	
	private TopMenuDescriptif(String login, String typeArtiste) {
		if(typeArtiste == null) {
			this.add(baseDeDonneMenu());
			this.add(contactMenu());
		} else if(typeArtiste.equals("Chanteur")) {
			this.add(menuChanteur());
			this.add(menuPlayer());
		} else {
			this.add(menuActeurComedien());
		}
		
		this.login = login;
	}
	
	
	private JMenu contactMenu() {
		JMenu contacter = new JMenu("Contact");
		JMenuItem autreUtilisateur = new JMenuItem("Autre utilisateur");
		autreUtilisateur.addActionListener((event)->ouvertureFenetreMail());
		
		contacter.add(autreUtilisateur);
		
		return contacter;
	}
	
	private JMenu menuChanteur() {
		JMenu chanson = new JMenu("Musique");
		JMenuItem ajouterAlbums = new JMenuItem("Albums");
		JMenuItem ajouterSaMusique = new JMenuItem("Ajouter musique");
		JMenuItem supprimerSaMusique = new JMenuItem("Supprimer musique");
		JMenuItem param = new JMenuItem("Paramètres");
		JMenuItem deconnexion = new JMenuItem("Déconnexion");
		deconnexion.addActionListener((event)->deconnexion());
		ajouterAlbums.addActionListener((event)->FenetreParametre.getInstance(login).ajoutAlbumFenetre());
		param.addActionListener((event)->FenetreParametre.getInstance(login).ajoutParametre());
		chanson.add(ajouterSaMusique);
		chanson.add(supprimerSaMusique);
		chanson.add(ajouterAlbums);
		chanson.add(param);
		chanson.add(deconnexion);
		return chanson;
	}
	
	private JMenu menuPlayer() {
		JMenu player = new JMenu("Player");
		JMenuItem jouer = new JMenuItem("Jouer");
		JMenuItem arreter = new JMenuItem("Stop");
		JMenuItem reprendreAuDebut = new JMenuItem("Reprendre au début");
		player.add(jouer);
		player.add(arreter);
		player.add(reprendreAuDebut);
		return player;
	}
	
	private JMenu menuActeurComedien() {
		JMenu spectacles = new JMenu("Représentation");
		JMenuItem ajouterUnSpectacle = new JMenuItem("Ajouter représentation");
		JMenuItem ajouterUneVille = new JMenuItem("Ajouter une ville");
		JMenuItem deconnexion = new JMenuItem("Déconnexion");
		deconnexion.addActionListener((event)->deconnexion());
		spectacles.add(ajouterUnSpectacle);
		spectacles.add(ajouterUneVille);
		spectacles.add(deconnexion);
		return spectacles;
	}
	
	private JMenu baseDeDonneMenu() {
		JMenu baseDeDonne = new JMenu("Donnée");
		JMenuItem creer = new JMenuItem("CréerBase");
		JMenuItem modifierLaBDD = new JMenuItem("Modifier");
		JMenuItem paramBDD = new JMenuItem("Paramètres");
		JMenuItem deconnexion = new JMenuItem("Déconnexion");
		deconnexion.addActionListener((event)->deconnexion());
		paramBDD.addActionListener((event)->FenetreParametre.getInstance(login).ajoutParametre());
		
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
		FenetreFond.getInstance().remove(this);
		FenetreFond.getInstance().remove(MenuChanteur.getInstance(login));
		FenetreFond.getInstance().changerFenetre(login);
		FenetreLogin.getInstance().setVisible(true);
	}
	
	
	public static TopMenuDescriptif getInstance(String login, String typeArtiste) {
		if (instance == null)
			instance = new TopMenuDescriptif(login, typeArtiste);
		return instance;
	}
}