package graphic.menusDeuxiemeFenetre;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.personne.Artiste;
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
	private Artiste artiste;
	
	private TopMenuDescriptif(String login) {
		this.login = login;
		this.artiste = null;

		this.add(baseDeDonneMenu());
		this.add(contactMenu());
		
	}
		
		
	
	
	private JMenu contactMenu() {//RESERVER A L'ADMIN => IL CONTACTE TOUT LE MONDE
		JMenu contacter = new JMenu("Contact");
		contacter.add(MenuRaccourcis.getInstance(login).actContacter);
		
		return contacter;
	}
	
	private JMenu menuChanteur() {
		JMenu chanson = new JMenu("Musique");

		chanson.add(MenuRaccourcis.getInstance(login, artiste).actAjoutMus);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actSuppressionMusique);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actAjoutAlb);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actParametre);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actBack);
		chanson.add(MenuRaccourcis.getInstance(login, artiste).actDeco);
		return chanson;
	}
	
	private JMenu menuPlayer() {
		JMenu player = new JMenu("Player");

		player.add(MenuRaccourcis.getInstance(login, artiste).actPlay);
		player.add(MenuRaccourcis.getInstance(login, artiste).actStop);
		player.add(MenuRaccourcis.getInstance(login, artiste).actReset);
		return player;
	}
	
	public void updateVersChanteur() {
		this.removeAll();
		
		this.add(menuChanteur());
		this.add(menuPlayer());
		this.add(contactMenu());
		this.validate();
	}
	
	public void updateVersInitial() {
		this.removeAll();
		
		this.add(baseDeDonneMenu());
		this.add(contactMenu());
	}
	
	private JMenu baseDeDonneMenu() {
		JMenu baseDeDonne = new JMenu("Base de données");
		JMenuItem modifierLaBDD = new JMenuItem("Modifier");
		JMenuItem paramBDD = new JMenuItem("Paramètres");
		
		baseDeDonne.add(MenuRaccourcis.getInstance(login).actAjoutArtiste);
		
		baseDeDonne.addSeparator();
		baseDeDonne.add(modifierLaBDD);
		baseDeDonne.addSeparator();
		baseDeDonne.add(paramBDD);
		baseDeDonne.addSeparator();
		baseDeDonne.add(MenuRaccourcis.getInstance(login).actDeco);
		
		return baseDeDonne;
	}
	
	
	public void ouvertureFenetreMail() {
		FenetreMail.getInstance().setVisible(true);
		MenuDeMail.getInstance().getMessage().setText("");
		MenuDeMail.getInstance().getAdresseMailRentre().setText("");
	}
	
	
	public void deconnexion() {
		if(MenuMusique.getInstance(login).getTitreEnCoursDeLecture()!=null)
			MenuMusique.getInstance(login).getTitreEnCoursDeLecture().stop();
		FenetreFond.getInstance().remove(this);
		FenetreFond.getInstance().remove(MenuPrincipal.getInstance(login));
		FenetreFond.getInstance().changerFenetre(login);
		FenetreLogin.getInstance().setVisible(true);
	}
	
	
	public Artiste getArtiste() {
		return artiste;
	}


	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}




	public static TopMenuDescriptif getInstance(String login) {
		if (instance == null)
			instance = new TopMenuDescriptif(login);
		return instance;
	}
}