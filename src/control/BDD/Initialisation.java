package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Initialisation {
	String url = "jdbc:mysql://localhost/artistak?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String user = "MoneyMan";
	String passwd = "money";
	boolean userCree = false;
	private static Initialisation instance;

	private Initialisation() {
	}

	public static Initialisation getInstance() {
		if (instance == null)
			instance = new Initialisation();
		return instance;
	}

	public void creerUser() {
		try {
			if (!userCree) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, user, passwd);
				System.out.println("Connexion effective !");
				Statement stat = conn.createStatement();
				stat.executeUpdate("CREATE USER IF NOT EXISTS 'user'@'localhost' IDENTIFIED BY '123'");
				stat.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO 'user'@'localhost'");
				userCree = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void creerBDD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("source iniBDD.sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
