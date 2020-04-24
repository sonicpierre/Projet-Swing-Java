package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Modification {
	private static final String url = "jdbc:mysql://localhost/artistak?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user;
	private String passwd;
	boolean userCree = false;
	private static Modification instance;

	private Modification() {

	}

	public static Modification getInstance() {
		if (instance == null)
			instance = new Modification();
		return instance;
	}
	
	public void insererArtiste(int id, String nom, String bio, String type) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("INSERT INTO Artiste VALUES(" + id + "," + nom + "," + bio + "," + type + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insererAlbum(int id, String nom, String date, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("INSERT INTO Album VALUES(" + id + "," + nom + "," + date + "," + idArtiste + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insererChanson(int id, String titre, int duree, int idAlbum) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("INSERT INTO Chanson VALUES(" + id + "," + titre + "," + duree + "," + idAlbum + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insererFilm(int id, String titre, String annee, int idFilm, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("INSERT INTO Film VALUES(" + id + "," + titre + "," + annee + ")");
			stat.executeUpdate("INSERT INTO JouerFilm VALUES(" + idFilm + "," + idArtiste + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insererSpectacle(int id, String titre, String annee, int spectateurs, int idSpectacle, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate(
					"INSERT INTO Spectacle VALUES(" + id + "," + titre + "," + annee + "," + spectateurs + ")");
			stat.executeUpdate("INSERT INTO JouerSpectacle VALUES(" + idSpectacle + "," + idArtiste + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerArtiste(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM Chanson WHERE idAlbum IN(SELECT id FROM Album WHERE idArtiste=" + id + ")");
			stat.executeUpdate("DELETE FROM Album WHERE idArtiste=" + id);
			stat.executeUpdate("DELETE FROM JouerFilm WHERE idArtiste=" + id);
			stat.executeUpdate("DELETE FROM JouerSpectacle WHERE idArtiste=" + id);
			stat.executeUpdate("DELETE FROM Artiste WHERE id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerAlbum(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM Album WHERE id=" + id);
			stat.executeUpdate("DELETE FROM Chanson WHERE idAlbum=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerChanson(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM Chanson WHERE id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerFilm(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM Film WHERE id=" + id);
			stat.executeUpdate("DELETE FROM JouerFilm WHERE idFilm=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerSpectacle(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM Spectacle WHERE id=" + id);
			stat.executeUpdate("DELETE FROM JouerSpectacle WHERE idSpectacle=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierArtiste(int id, String nom, String bio, String type) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("UPDATE Artiste SET nom=" + nom + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Artiste SET bio=" + bio + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Artiste SET type=" + type + " WHERE id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierAlbum(int id, String nom, String date, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("UPDATE Album SET nom=" + nom + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Album SET date=" + date + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Album SET idArtiste=" + idArtiste + " WHERE id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierChanson(int id, String titre, int duree, int idAlbum) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("UPDATE Chanson SET titre=" + titre + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Chanson SET duree=" + duree + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Chanson SET idAlbum=" + idAlbum + " WHERE id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierFilm(int id, String titre, String annee, int idFilm, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("UPDATE Film SET titre=" + titre + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Film SET duree=" + annee + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Film SET idAlbum=" + idFilm + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Film SET idAlbum=" + idArtiste + " WHERE id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierSpectacle(int id, String titre, String annee, int spectateurs, int idSpectacle, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("UPDATE Spectacle SET titre=" + titre + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Spectacle SET duree=" + annee + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Spectacle SET spectateurs=" + spectateurs + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Spectacle SET idAlbum=" + idSpectacle + " WHERE id=" + id);
			stat.executeUpdate("UPDATE Spectacle SET idAlbum=" + idArtiste + " WHERE id=" + id);
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
