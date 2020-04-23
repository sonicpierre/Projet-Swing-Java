package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLScript {

	
	public static void remplissageDeBDD(String user, String passwrd) {
		String urlApresConnexion = "jdbc:mysql://localhost/Artistak?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlApresConnexion, user, passwrd);
			
			System.out.println("Connexion effective !");
			Statement stat = conn.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Artiste(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	nom VARCHAR(30),\n" + 
					"	biographie VARCHAR(1000),\n" + 
					"	type VARCHAR(10));");
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Album(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	nom VARCHAR(30),\n" + 
					"	date DATE,\n" + 
					"	idArtiste INT,\n" + 
					"	FOREIGN KEY fk_artiste(idArtiste) REFERENCES Artiste(id));");
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Chanson(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	titre VARCHAR(30),\n" + 
					"	duree INT,\n" + 
					"	idAlbum INT,\n" + 
					"	FOREIGN KEY fk_artiste(idAlbum) REFERENCES Album(id));");
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Film(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	titre VARCHAR(30),\n" + 
					"	annee INT);");
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Spectacle(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	titre VARCHAR(30),\n" + 
					"	annee INT,\n" + 
					"	spectateurs INT);");
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS JouerFilm(\n" + 
					"	idFilm INT,\n" + 
					"	idArtiste INT,\n" + 
					"	CONSTRAINT pk_JouerFilm PRIMARY KEY (idFilm, idArtiste),\n" + 
					"	FOREIGN KEY fk_film(idFilm) REFERENCES Film(id),\n" + 
					"	FOREIGN KEY fk_artiste(idArtiste) REFERENCES Artiste(id));");
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS JouerSpectacle(\n" + 
					"	idSpectacle INT,\n" + 
					"	idArtiste INT,\n" + 
					"	CONSTRAINT pk_JouerFilm PRIMARY KEY (idSpectacle, idArtiste),\n" + 
					"	FOREIGN KEY fk_spectacle(idSpectacle) REFERENCES Spectacle(id),\n" + 
					"	FOREIGN KEY fk_artiste(idArtiste) REFERENCES Artiste(id));");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
