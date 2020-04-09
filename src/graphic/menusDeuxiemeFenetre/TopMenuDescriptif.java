package graphic.menusDeuxiemeFenetre;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopMenuDescriptif{
	
	JMenuBar menuFinal;
	
	private static TopMenuDescriptif instance;
	
	private TopMenuDescriptif() {
		menuFinal = new JMenuBar();
		menuFinal.add(baseDeDonneMenu());
		menuFinal.add(contactMenu());
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
		JMenuItem createur = new JMenuItem("Administrateur");
		
		contacter.add(autreUtilisateur);
		contacter.addSeparator();
		contacter.add(createur);
		
		return contacter;
	}
	
	private JMenu baseDeDonneMenu() {
		JMenu baseDeDonne = new JMenu("Donnée");
		JMenuItem creer = new JMenuItem("CréerBase");
		JMenuItem modifierLaBDD = new JMenuItem("Modifier");
		JMenuItem paramBDD = new JMenuItem("Paramètres");
		
		baseDeDonne.add(creer);
		baseDeDonne.addSeparator();
		baseDeDonne.add(modifierLaBDD);
		baseDeDonne.addSeparator();
		baseDeDonne.add(paramBDD);
		
		return baseDeDonne;
	}
	
	public static TopMenuDescriptif getInstance() {
		if (instance == null)
			instance = new TopMenuDescriptif();
		return instance;
	}
}