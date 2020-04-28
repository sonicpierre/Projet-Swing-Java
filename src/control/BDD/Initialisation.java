package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import graphic.menusDepart.CreerCompte;

/**
 *url chemin vers la bb
 **/
public class Initialisation {
	private static final String url = "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	private String user = null;
	private String passwd = null;
	
	private static Initialisation instance;

	private Initialisation() {
	}

	public static Initialisation getInstance() {
		if (instance == null)
			instance = new Initialisation();
		return instance;
	}
	
	
	public void creerUser(String passwrdRoot, String userACreer, String passwrdAssocie) {
		
			/**
			 *Utilisation du try catch car il permet de recupere l'erreur lors de la connexion avec un mauvaus mdp root
			 **/
		
			try {
				/*
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
				stat.executeUpdate("CREATE USER IF NOT EXISTS '"+ userACreer +"'@'localhost' IDENTIFIED BY '" + passwrdAssocie + "'");
				stat.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO '" + userACreer + "'@'localhost'");
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
	 *Permet de creer la bdd
	 *Elle est appler lors de la connexion
	 *Penser au cas d'un utilisateur qui n'a pas la bdd et qui aurea besoin de creer la bdd
	 *@see SQLScript
	 *on utilise le try catch pour verifier la presnce d'un mauvais mdp ou nom d'utilisateur
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
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
