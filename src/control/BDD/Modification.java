package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 **/
public class Modification {
	private static final String url = "jdbc:mysql://localhost/Artistak?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
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

	// Permet d'éviter les attaques par injections et en d'autre termes d'éviter que
	// ça plante quand y a des '.

	public void insererArtiste(int id, String nom, String bio, String type) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion artiste");
				String requeteSQL = "INSERT INTO Artiste VALUES(" + id + ",?,?,'" + type + "')";
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, nom); // On remplace le premier ? par le nom ici ça commence à 1 et pas 0
					stat.setString(2, bio);
					stat.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void insererAlbum(int id, String nom, String date, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion album");
				String requeteSQL = "INSERT INTO Album VALUES(" + id + ",?,'" + date + "'," + idArtiste + ")";
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, nom);
					stat.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insererChanson(int id, String titre, int duree, int idAlbum) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion chanson");
				String requeteSQL = "INSERT INTO Chanson VALUES(" + id + ",?," + duree + "," + idAlbum + ")";
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, titre);
					stat.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
	}

	public void insererFilm(int id, String titre, int annee) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion film");
				String requeteSQL = "INSERT INTO Film VALUES(" + id + ",?," + annee + ")";
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, titre);
					stat.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insererSpectacle(int id, String titre, int annee, int spectateurs) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion spectacle");
				String requeteSQL = "INSERT INTO Spectacle VALUES(" + id + ",?," + annee + "," + spectateurs + ")";
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, titre);
					stat.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insererJouerFilm(int idFilm, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion lien film acteur");
				Statement stat = conn.createStatement();
				stat.executeUpdate("INSERT INTO JouerFilm VALUES(" + idFilm + "," + idArtiste + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insererJouerSpectacle(int idSpectacle, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion lien spectacle acteur");
				Statement stat = conn.createStatement();
				stat.executeUpdate("INSERT INTO JouerSpectacle VALUES(" + idSpectacle + "," + idArtiste + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerArtiste(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Suppression artiste");
				Statement stat = conn.createStatement();
				stat.executeUpdate(
						"DELETE FROM Chanson WHERE idAlbum IN(SELECT id FROM Album WHERE idArtiste=" + id + ")");
				stat.executeUpdate("DELETE FROM Album WHERE idArtiste=" + id);
				stat.executeUpdate("DELETE FROM JouerFilm WHERE idArtiste=" + id);
				stat.executeUpdate("DELETE FROM JouerSpectacle WHERE idArtiste=" + id);
				stat.executeUpdate("DELETE FROM Artiste WHERE id=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerAlbum(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Suppression album");
				Statement stat = conn.createStatement();
				stat.executeUpdate("DELETE FROM Chanson WHERE idAlbum=" + id);
				stat.executeUpdate("DELETE FROM Album WHERE id=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerChanson(String titre) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Suppression chanson");
				Statement stat = conn.createStatement();
				stat.executeUpdate("DELETE FROM Chanson WHERE titre=" + titre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerFilm(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Suppression film");
				Statement stat = conn.createStatement();
				stat.executeUpdate("DELETE FROM Film WHERE id=" + id);
				stat.executeUpdate("DELETE FROM JouerFilm WHERE idFilm=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void supprimerSpectacle(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Suppression spectacle");
				Statement stat = conn.createStatement();
				stat.executeUpdate("DELETE FROM Spectacle WHERE id=" + id);
				stat.executeUpdate("DELETE FROM JouerSpectacle WHERE idSpectacle=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierArtiste(int id, String nom, String bio, String type) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Modification artiste");
				String requeteSQL = "UPDATE Artiste SET nom=? WHERE id=" + id;
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, nom);
					stat.executeUpdate();
				}
				requeteSQL = "UPDATE Artiste SET bio=? WHERE id=" + id;
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, bio);
					stat.executeUpdate();
				}
				Statement stat = conn.createStatement();
				stat.executeUpdate("UPDATE Artiste SET type='" + type + "' WHERE id=" + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void modifierAlbum(int id, String nom, String date, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Modification album");
				String requeteSQL = "UPDATE Album SET nom=? WHERE id=" + id;
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, nom);
					stat.executeUpdate();
				}
				Statement stat = conn.createStatement();
				stat.executeUpdate("UPDATE Album SET date='" + date + "' WHERE id=" + id);
				stat.executeUpdate("UPDATE Album SET idArtiste=" + idArtiste + " WHERE id=" + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void modifierChanson(int id, String titre, int duree, int idAlbum) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Modification chanson");
				String requeteSQL = "UPDATE Chanson SET titre=? WHERE id=" + id;
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, titre);
					stat.executeUpdate();
				}
				Statement stat = conn.createStatement();
				stat.executeUpdate("UPDATE Chanson SET duree=" + duree + " WHERE id=" + id);
				stat.executeUpdate("UPDATE Chanson SET idAlbum=" + idAlbum + " WHERE id=" + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void modifierChansonID(int id, int idAlbum) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Modification chanson");
				Statement stat = conn.createStatement();
				stat.executeUpdate("UPDATE Chanson SET idAlbum=" + idAlbum + " WHERE id=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierFilm(int id, String titre, int annee, int idFilm, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Modification film");
				String requeteSQL = "UPDATE Film SET titre=? WHERE id=" + id;
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, titre);
					stat.executeUpdate();
				}
				Statement stat = conn.createStatement();
				stat.executeUpdate("UPDATE Film SET annee=" + annee + " WHERE id=" + id);
				stat.executeUpdate("UPDATE Film SET idFilm=" + idFilm + " WHERE id=" + id);
				stat.executeUpdate("UPDATE Film SET idArtiste=" + idArtiste + " WHERE id=" + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void modifierSpectacle(int id, String titre, int annee, int spectateurs, int idSpectacle, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Modification spectacle");
				String requeteSQL = "UPDATE Spectacle SET titre=? WHERE id=" + id;
				try (PreparedStatement stat = conn.prepareStatement(requeteSQL)) {
					stat.setString(1, titre);
					stat.executeUpdate();
				}
				Statement stat = conn.createStatement();
				stat.executeUpdate("UPDATE Spectacle SET annee=" + annee + " WHERE id=" + id);
				stat.executeUpdate("UPDATE Spectacle SET spectateurs=" + spectateurs + " WHERE id=" + id);
				stat.executeUpdate("UPDATE Spectacle SET idSpectacle=" + idSpectacle + " WHERE id=" + id);
				stat.executeUpdate("UPDATE Spectacle SET idArtiste=" + idArtiste + " WHERE id=" + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
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
