package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import graphic.menusDepart.CreerCompte;

/**
 *<b>Initialisation</b> est la classe permettant d'initialiser la base de données
 *@author Julien Buisson Chabot
 *@version 2.0
 **/

public class Initialisation {
	
	/**
	 *URL de connexion à la base de données
	 **/
	
	private static final String url = "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	/**
	 *Initialisation de l'utilisateur
	 **/
	
	private String user = null;
	
	/**
	 *Initialisation du mot de passe
	 **/
	
	private String passwd = null;
	
	/**
	 *Déclaration de l'instance
	 **/
	
	private static Initialisation instance;
	
	/**
	 *Déclaration de l'initialisation
	 **/
	
	private Initialisation() {
	}
	
	/**
	 *Instanciation de l'initialisation
	 *@return Initialisation
	 **/
	
	public static Initialisation getInstance() {
		if (instance == null)
			instance = new Initialisation();
		return instance;
	}
	
	/**
	 *Permet de créer un utilisateur
	 *@param passwrdRoot
	 *	Mot de passe root
	 *@param userACreer
	 *	Utilisateur à créer
	 *@param passwrdAssocie
	 *	Mot de passe associé
	 **/
	
	public void creerUser(String passwrdRoot, String userACreer, String passwrdAssocie) {
		
			/**
			 *Utilisation du try catch car il permet de recupere l'erreur lors de la connexion avec un mauvaus mdp root
			 **/
		
			try {
				/**
				 *Driver permettantla connexion entre mysql er java
				 **/
				Class.forName("com.mysql.cj.jdbc.Driver");
			
				/**
				 *Connexion avec root car c'est qu'avec lui qu'on peut creer un utilisateur
				 *l'utilisation du try comme ça permet de fermer la connection automatiquement
				 **/
				
			try(Connection conn = DriverManager.getConnection(url, "root", passwrdRoot)){
				System.out.println("Connexion effective !");
			
				/**
				 *Permet de faire des requetes qui changent la bdd, mais ne donne pas de resultat.
				 **/
				
				Statement stat = conn.createStatement();
				
				/**
				 *Création de l'utilisateur s'il n'existe pas, identifié par son mot de passe et nom d'utilisateur
				 **/
				
				stat.executeUpdate("CREATE USER IF NOT EXISTS '"+ userACreer +"'@'localhost' IDENTIFIED BY '" + passwrdAssocie + "'");
				
				/**
				 *Accorde tous les privilèges d'actions à l'utilisateur nouvellement crée
				 **/
				
				stat.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO '" + userACreer + "'@'localhost'");
				
				/**
				 *Création de la base de données si elle n'existe pas
				 **/
				
				stat.executeUpdate("CREATE DATABASE IF NOT EXISTS Artistak");
				
				/**
				 *En cas d'erreur, on fzaitr apparaitre la fenetre, mais on garde l'erreur en console avecle e.trace
				 **/
			}
			
			}catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showInternalMessageDialog(CreerCompte.getInstance(), "Mauvais mot de passe root", "Erreur",
						JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
		
	}

	/**
	 *Permet de créer la base de données et est appelée lors de la connexion
	 *@param user
	 *	Utilisateur
	 *@param passwd
	 *	Mot de passe
	 *@see SQLScript
	 *<b>NB : </b> Dans le cas où un utilisateur n'a pas la base de données, il aura besoin de la créer. Un try catch est utilisé pour vérifier la présence d'un mauvais mot de passe ou nom d'utilisateur
	 *@return True si la base de données a été créee
	 **/
	
	public boolean creerBDD(String user, String passwd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(Connection conn = DriverManager.getConnection(url, user, passwd)){
				Statement stat = conn.createStatement();
				stat.executeUpdate("CREATE DATABASE IF NOT EXISTS Artistak");
				SQLScript.remplissageDeBDD(user, passwd);
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(CreerCompte.getInstance(), "Mauvais mot de passe ou utilisateur SQL", "Erreur",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *Récupère l'utilisateur
	 *@return Utilisateur
	 **/
	
	public String getUser() {
		return user;
	}
	
	/**
	 *Initialisation de l'utilisateur
	 *@param user
	 *	Utilisateur
	 **/
	
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 *Récupère le mot de passe
	 *@return Mot de passe
	 **/
	
	public String getPasswd() {
		return passwd;
	}
	
	/**
	 *Initialisation du mot de passe
	 *@param passwd
	 *	Mot de passe
	 **/
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
