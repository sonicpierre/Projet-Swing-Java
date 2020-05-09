package graphic.menusDepart;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graphic.fenetre.FenetreLogin;


/**
 *<p> <b>MenuDemmarrage</b> est la classe qui construit la fenêtre 
 *du menu de démarrage. Elle permettra la navigation de l'utilisateur dans deux onglets : 
 *<lu>
 *<li>Membre : servant à la connexion via identifiants</li>
 *<li>Nouveau : servant à la saisie d'informations de création de compte</li>
 *</lu>
 *@author VIRAGAUX Pierre
 *</p>
 *@version 2.0
 **/

@SuppressWarnings("serial")
public class MenuDemmarrage extends JPanel{
	
	/**
	 *Panel d'onglets
	 **/
	
	JTabbedPane mesOnglets;
	
	/**
	 *Permet initialisation de la fenêtre puis l'ajout de ses composantes
	 **/
	
	public MenuDemmarrage() {
		
		/**
		 *Initialisation du JPanel en le plaçant en haut
		 **/
		
		mesOnglets = new JTabbedPane(JTabbedPane.TOP);
		
		/**
		 *Ajout du menu login
		 **/
		
		mesOnglets.add("Utilisateur", LoginMenu.getInstance().getMenuLogin());
		
		/**
		 *Ajout de la fenête de création de compte
		 **/
		
		mesOnglets.add("Nouvel utilisateur", CreerCompte.getInstance().getMenuCreation());
		
		/**
		 *Listener qui ecoute les actions sur la fenêtre
		 **/
		
		mesOnglets.addChangeListener(new ChangeListener() {
			
			@Override
			
			/**
			 *<p> Fonction permettant le changement de dimension de 
			 *la fenêtre lorsqu'on passe de l'onglet Nouveau à Membre (vice-versa)
			 *</p>
			 **/
			
			public void stateChanged(ChangeEvent e) {
				
				/**
				 *Appel du sigleton
				 **/
				
				FenetreLogin.getInstance().changerLadim();
				
			}
		});
	}
	
	/**
	 *Retourne les onglets de la fenêtre
	 *@return Onglet fenêtre
	 **/
	
	public JTabbedPane getMesOnglets() {
		return mesOnglets;
	}
	
	/**
	 *Création des onglets de navigation
	 *@param mesOnglets
	 **/
	
	public void setMesOnglets(JTabbedPane mesOnglets) {
		this.mesOnglets = mesOnglets;
	}
	
	
}
