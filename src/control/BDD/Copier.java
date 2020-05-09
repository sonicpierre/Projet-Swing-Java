package control.BDD;

import java.sql.*;

/**
 *<b>Copier</b> est la classe permettant de saisir les champs d'informations des artistes, album, chanson, film, spectacle
 *@author VIRGAUX Pierre
 *@version 2.0
 **/

public class Copier {
	
	/**
	 *URL du protocole de connexion à la base de données
	 **/
	
	String url1 = "jdbc:mysql://localhost/";
	
	/**
	 *Localisation de la bas de données
	 **/
	
	String url2 = "/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	/**
	 *Nom de l'utilisateur
	 **/
	
	String user = "MoneyMan";
	
	/**
	 *Mot de passe utilisateur
	 **/
	
	String passwd = "money";
	
	/**
	 *Copie d'informations
	 *@param nomBDD
	 *	Nom de la base de données
	 **/
	
	public void copier(String nomBDD) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			/**
			 *Connexion à la base de données
			 **/
			
			Connection conn = DriverManager.getConnection(url1+nomBDD+url2, user, passwd);
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			
			/**
			 *Initialisation de la requête asscociée au résultat
			 **/
			
			ResultSet res = stat.executeQuery("SELECT * FROM Artiste");
			
			/**
			 *Modication des artistes
			 *@see Modification
			 **/
			
			while (res.next()) {
				Modification.getInstance().insererArtiste(res.getInt("id"), res.getString("nom"), res.getString("bio"), res.getString("type"));
			}
			res = stat.executeQuery("SELECT * FROM Album");
			
			/**
			 *Modification des albums
			 *@see Modification
			 **/
			
			while (res.next()) {
				Modification.getInstance().insererAlbum(res.getInt("id"), res.getString("nom"), res.getString("date"), res.getInt("idArtiste"));
			}
			res = stat.executeQuery("SELECT * FROM Chanson");
			
			/**
			 *Modification des chansons
			 *@see Modification
			 **/
			
			while (res.next()) {
				Modification.getInstance().insererChanson(res.getInt("id"), res.getString("titre"), res.getInt("duree"), res.getInt("idAlbum"));
			}
			res = stat.executeQuery("SELECT * FROM Film");
			
			/**
			 *Modification des films 
			 *@see Modification
			 **/
			
			while (res.next()) {
				Modification.getInstance().insererFilm(res.getInt("id"), res.getString("titre"), res.getInt("annee"));
			}
			res = stat.executeQuery("SELECT * FROM Spectacle");
			
			/**
			 *Modification des spectacles
			 *@see Modification
			 **/
			
			while (res.next()) {
				Modification.getInstance().insererSpectacle(res.getInt("id"), res.getString("titre"), res.getInt("annee"), res.getInt("spectateurs"));
			}
			res = stat.executeQuery("SELECT * FROM JouerFilm");
			
			/**
			 *Modification des films
			 *@see Modification
			 **/
			
			while (res.next()) {
				Modification.getInstance().insererJouerFilm(res.getInt("idFilm"), res.getInt("idArtiste"));
			}
			res = stat.executeQuery("SELECT * FROM JouerSpectacle");
			
			/**
			 *Modification des spectacles
			 *@see Modification
			 **/
			while (res.next()) {
				Modification.getInstance().insererJouerSpectacle(res.getInt("idSpectacle"), res.getInt("idArtiste"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
