package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import graphic.menusDepart.CreerCompte;

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
		

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(url, "root", passwrdRoot);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("CREATE USER IF NOT EXISTS '"+ userACreer +"'@'localhost' IDENTIFIED BY '" + passwrdAssocie + "'");
			stat.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO '" + userACreer + "'@'localhost'");
			stat.executeUpdate("CREATE DATABASE IF NOT EXISTS Artistak");
			
			}catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showInternalMessageDialog(CreerCompte.getInstance(), "Mauvais mot de passe root", "Erreur",
						JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
		
	}

	public boolean creerBDD(String user, String passwd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement stat = conn.createStatement();
			stat.executeUpdate("CREATE DATABASE IF NOT EXISTS Artistak");
			SQLScript.remplissageDeBDD(user, passwd);
			return true;
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
