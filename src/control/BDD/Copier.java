package control.BDD;

import java.sql.*;

public class Copier {
	String url1 = "jdbc:mysql://localhost/";
	String url2 = "/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String user = "MoneyMan";
	String passwd = "money";
	
	public void copier(String nomBDD) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url1+nomBDD+url2, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			ResultSet res = stat.executeQuery("SELECT * FROM Artiste");
			while (res.next()) {
				Modification.getInstance().insererArtiste(res.getInt("id"), res.getString("nom"), res.getString("bio"), res.getString("type"));
			}
			res = stat.executeQuery("SELECT * FROM Album");
			while (res.next()) {
				Modification.getInstance().insererAlbum(res.getInt("id"), res.getString("nom"), res.getString("date"), res.getInt("idArtiste"));
			}
			res = stat.executeQuery("SELECT * FROM Chanson");
			while (res.next()) {
				Modification.getInstance().insererChanson(res.getInt("id"), res.getString("titre"), res.getInt("duree"), res.getInt("idAlbum"));
			}
			res = stat.executeQuery("SELECT * FROM Film");
			while (res.next()) {
				Modification.getInstance().insererFilm(res.getInt("id"), res.getString("titre"), res.getInt("annee"));
			}
			res = stat.executeQuery("SELECT * FROM Spectacle");
			while (res.next()) {
				Modification.getInstance().insererSpectacle(res.getInt("id"), res.getString("titre"), res.getInt("annee"), res.getInt("spectateurs"));
			}
			res = stat.executeQuery("SELECT * FROM JouerFilm");
			while (res.next()) {
				Modification.getInstance().insererJouerFilm(res.getInt("idFilm"), res.getInt("idArtiste"));
			}
			res = stat.executeQuery("SELECT * FROM JouerSpectacle");
			while (res.next()) {
				Modification.getInstance().insererJouerSpectacle(res.getInt("idSpectacle"), res.getInt("idArtiste"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
