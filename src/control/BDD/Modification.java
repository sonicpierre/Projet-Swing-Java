package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *<b>Modification</b> est la classe qui permet de modifier le répertoire de chaque artiste en y ajoutant ou supprimant des éléments.
 *@author Julien Buisson Chabot
 *@version 2.0
 **/

public class Modification {
	
	/**
	 *URL de connexion à la base de données
	 **/
	
	private static final String url = "jdbc:mysql://localhost/Artistak?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	/**
	 *Utilisateur
	 **/
	
	private String user;
	
	/**
	 *Mot de pase crypté
	 **/
	
	private String passwd;
	
	/**
	 *Etat de création d'un utilisateur
	 **/
	
	boolean userCree = false;
	
	/**
	 *Déclaration de l'instance de modification
	 **/
	
	private static Modification instance;

	private Modification() {

	}
	//R
	/**
	 *Instanciation de la modification
	 *@return Etat modifié de la fenêtre
	 **/
	
	public static Modification getInstance() {
		if (instance == null)
			instance = new Modification();
		return instance;
	}
	
	/**
	 *Permet d'enregistrer le répertoire d'artistes dans la base de données
	 *@param id
	 *	ID 
	 *@param nom
	 *	Nom de l'artiste
	 *@param bio
	 *	Biographie de l'artiste
	 *@param type
	 *	Talent de l'artiste
	 **/

	public void insererArtiste(int id, String nom, String bio, String type) {
		try {
			/**
			 * importation du driver
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			/**
			 * Connexion à la BDD
			 */
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion artiste");
				String requeteSQL = "REPLACE INTO Artiste SET `id`=" + id + ",`nom`= ?, `biographie` = ?, `type`='" + type +"'";
				/**
				 * Permet d'éviter les attaques par injections et en d'autre termes d'éviter que
				 * ça plante quand y a des '.
				 */
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
	
	/**
	 *Permet d'enregistrer le répertoire d'album dans la base de données
	 *@param id
	 *	ID 
	 *@param nom
	 *	Nom de l'album
	 *@param date
	 *	Date de l'album
	 *@param idArtiste
	 *	ID de l'artiste
	 **/
	
	public void insererAlbum(int id, String nom, String date, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion album");
				String requeteSQL = "REPLACE INTO Album SET `id`=" + id + ",`nom`=?,`date`='" + date + "',`idArtiste`=" + idArtiste;
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
	
	/**
	 *Permet d'enregistrer le répertoire de chansons dans la base de données
	 *@param id
	 *	ID 
	 *@param titre
	 *	Titre de la chanson
	 *@param duree
	 *	Durée du spectacle
	 *@param idAlbum
	 *	ID de l'album
	 **/
	
	public void insererChanson(int id, String titre, int duree, int idAlbum) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion chanson");
				String requeteSQL = "REPLACE INTO Chanson SET `id`=" + id + ",`titre`=?,`duree`=" + duree + ",`idAlbum`=" + idAlbum;
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
	
	/**
	 *Permet d'ajouter un bon film à la base de données
	 *@param id
	 *	ID
	 *@param titre
	 *	Titre du film
	 *@param annee
	 *	Année du film
	 **/
	
	public void insererFilm(int id, String titre, int annee) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion film");
				String requeteSQL = "REPLACE INTO Film SET `id`=" + id + ",`titre`=?,`annee`=" + annee;
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
	
	/**
	 *Permet d'ajouter un spectacle dans la base de données
	 *@param id 
	 *	ID spectacle
	 *@param titre
	 *	Titre spectacle
	 *@param annee
	 *	Année spectacle
	 *@param spectateurs
	 *	Nombre de spectateur
	 **/
	
	public void insererSpectacle(int id, String titre, int annee, int spectateurs) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion spectacle");
				String requeteSQL = "REPLACE INTO Spectacle SET `id`=" + id + ",`titre`=?,`annee`=" + annee + ",`spectateurs`=" + spectateurs;
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
	
	/**
	 *Permet de modifier le répertoire de films dans la base de données
	 *@param idFilm
	 *	ID du film
	 *@param idArtiste
	 *	ID de l'artiste concerné
	 **/
	
	public void insererJouerFilm(int idFilm, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion lien film acteur");
				System.out.println(idFilm +" " + idArtiste);
				Statement stat = conn.createStatement();
				stat.executeUpdate("REPLACE INTO JouerFilm SET `idFilm`=" + idFilm + ",`idArtiste`=" + idArtiste);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *Permet d'enregistrer un spectacle de la base de données via son id
	 *@param idSpectacle
	 *	ID du spectacle
	 *@param idArtiste
	 *	ID de l'artiste concerné
	 **/
	
	public void insererJouerSpectacle(int idSpectacle, int idArtiste) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, user, passwd)) {
				System.out.println("Insertion lien spectacle acteur");
				Statement stat = conn.createStatement();
				stat.executeUpdate("REPLACE INTO JouerSpectacle SET `idSpectacle`=" + idSpectacle + ",`idArtiste`=" + idArtiste);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *Permet de supprimer un artiste de la base de données via son id
	 *@param id
	 *	ID de l'artiste
	 **/
	
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
	
	/**
	 *Permet de supprimer un album de la base de données via son id
	 *@param id
	 *	ID de l'album
	 **/
	
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
	
	/**
	 *Permet de supprimer une chanson de la base de données via son id
	 *@param titre
	 *	Titre de la chanson
	 **/
	
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
	
	/**
	 *Permet de supprimer un film de la base de données via son id
	 *@param id
	 *	ID du film
	 **/
	
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
	
	/**
	 *Permet de supprimer un spectacle de la base de données via son id
	 *@param id
	 *	ID du spectacle
	 **/
	
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
	
	/**
	 *Permet de modifier le répertoire d'artistes dans la base de données
	 *@param id
	 *	ID 
	 *@param nom
	 *	Nom de l'artiste
	 *@param bio
	 *	Biographie de l'artiste
	 *@param type
	 *	Talent de l'artiste
	 **/
	
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
	
	/**
	 *Permet de modifier le répertoire d'album dans la base de données
	 *@param id
	 *	ID 
	 *@param nom
	 *	Nom de l'album
	 *@param date
	 *	Date de l'album
	 *@param idArtiste
	 *	ID de l'artiste
	 **/
	
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
	
	/**
	 *Permet de modifier le répertoire de chansons dans la base de données
	 *@param id
	 *	ID 
	 *@param titre
	 *	Titre de la chanson
	 *@param duree
	 *	Durée de la chanson
	 *@param idAlbum
	 *	ID de l'album
	 **/
	
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
	
	/**
	 *Permet de modifier le répertoire de chansons par son ID 
	 *@param id
	 *	ID 
	 *@param idAlbum
	 *	ID de l'album
	 **/
	
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
	
	/**
	 *Permet de modifier le répertoire de films dans la base de données
	 *@param id
	 *	ID 
	 *@param titre
	 *	Titre du spectacle
	 *@param annee
	 *	Année du film
	 *@param idFilm
	 *	ID du film
	 *@param idArtiste
	 *	ID de l'artiste concerné
	 **/
	
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
	
	/**
	 *Permet de modifier le répertoire de spectacles dans la base de données
	 *@param id
	 *	ID 
	 *@param titre
	 *	Titre du spectacle
	 *@param annee
	 *	Année du spectacle
	 *@param spectateurs
	 *	Nombre de spectateurs
	 *@param idSpectacle
	 *	ID du spectacle
	 *@param idArtiste
	 *	ID de l'artiste concerné
	 **/
	
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
