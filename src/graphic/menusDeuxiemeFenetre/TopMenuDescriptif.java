package graphic.menusDeuxiemeFenetre;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;
import graphic.fenetre.FenetreParametre;
import graphic.fenetreEnvoieMail.FenetreMail;
import graphic.fenetreEnvoieMail.MenuDeMail;


//BARRE DE HAUT SELON LE TALENT DE L'UTILISATEUR
//CE N'EST PA PRATIK DAVOIU UN BARR POUR CHAQ PEROSN => COMBINAISON DES PREFERENCES
@SuppressWarnings("serial")
public class TopMenuDescriptif extends JMenuBar{
	
	
	private static TopMenuDescriptif instance;
	
	private String login;
	private String typeArtiste;
	
	private TopMenuDescriptif(String login, String typeArtiste) {
		this.login = login;
		this.typeArtiste = typeArtiste;
		if(typeArtiste == null) {//ADMINISTRATEUR IL POURRA AVOIR ACCES A CERTAINES INFO DES PERSONNES
			this.add(baseDeDonneMenu());
			this.add(contactMenu());
		} else if(typeArtiste.equals("Chanteur")) {
			this.add(menuChanteur());
			this.add(menuPlayer());
		} else {
			this.add(menuActeurComedien());
		}
		
		
	}
	
	
	private JMenu contactMenu() {//RESERVER A L'ADMIN => IL CONTACTE TOUT LE MONDE
		JMenu contacter = new JMenu("Contact");
		JMenuItem autreUtilisateur = new JMenuItem("Autre utilisateur");
		autreUtilisateur.addActionListener((event)->ouvertureFenetreMail());
		
		contacter.add(autreUtilisateur);
		
		return contacter;
	}
	
	private JMenu menuChanteur() {
		JMenu chanson = new JMenu("Musique");

		chanson.add(MenuRaccourcis.getInstance(login).actAjoutMus);
		chanson.add(MenuRaccourcis.getInstance(login, typeArtiste).actSuppressionMusique);
		chanson.add(MenuRaccourcis.getInstance(login, typeArtiste).actAjoutAlb);
		chanson.add(MenuRaccourcis.getInstance(login, typeArtiste).actParametre);
		chanson.add(MenuRaccourcis.getInstance(login, typeArtiste).actDeco);
		return chanson;
	}
	
	private JMenu menuPlayer() {
		JMenu player = new JMenu("Player");

		player.add(MenuRaccourcis.getInstance(login).actPlay);
		player.add(MenuRaccourcis.getInstance(login).actStop);
		player.add(MenuRaccourcis.getInstance(login).actReset);
		return player;
	}
	
	private JMenu menuActeurComedien() {
		JMenu spectacles = new JMenu("Représentation");
		JMenuItem ajouterUnSpectacle = new JMenuItem("Ajouter représentation");
		JMenuItem ajouterUneVille = new JMenuItem("Ajouter une ville");

		spectacles.add(ajouterUnSpectacle);
		spectacles.add(ajouterUneVille);
		spectacles.add(MenuRaccourcis.getInstance(login, typeArtiste).actDeco);
		return spectacles;
	}
	
	private JMenu baseDeDonneMenu() {
		JMenu baseDeDonne = new JMenu("Donnée");
		JMenuItem creer = new JMenuItem("CréerBase");
		JMenuItem modifierLaBDD = new JMenuItem("Modifier");
		JMenuItem paramBDD = new JMenuItem("Paramètres");
		paramBDD.addActionListener((event)->FenetreParametre.getInstance(login).ajoutParametre());
		
		baseDeDonne.add(creer);
		baseDeDonne.addSeparator();
		baseDeDonne.add(modifierLaBDD);
		baseDeDonne.addSeparator();
		baseDeDonne.add(paramBDD);
		baseDeDonne.addSeparator();
		baseDeDonne.add(MenuRaccourcis.getInstance(login, typeArtiste).actDeco);
		
		return baseDeDonne;
	}
	
	
	private void ouvertureFenetreMail() {
		FenetreMail.getInstance().setVisible(true);
		MenuDeMail.getInstance().getMessage().setText("");
		MenuDeMail.getInstance().getAdresseMailRentre().setText("");
	}
	
	
	public void deconnexion() {
		if(typeArtiste.equals("Chanteur"))
			MenuChanteur.getInstance(login).getTitreEnCoursDeLecture().stop();
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