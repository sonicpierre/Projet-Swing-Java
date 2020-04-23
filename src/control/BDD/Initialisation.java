package control.BDD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Initialisation {
	private static final String url = "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String urlApresConnexion = "jdbc:mysql://localhost/Artistak?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String user = "MoneyMan";
	String passwd = "money";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void creerBDD(String user, String passwd) {
		File sourceBDD = new File("iniBDD.sql");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlApresConnexion, user, passwd);
			System.out.println("Connexion effective !");
			SQLScript.remplissageDeBDD(user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
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
