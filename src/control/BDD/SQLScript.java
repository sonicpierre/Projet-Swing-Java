package control.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *La classe <b>SQLScript</b> est la classe permettant de crée le fichier sql ayant les tables de la base de données.
 *NB : Nous avons été obligé de passer par des requêtes pour créer l'architecture la base de données. En effet JBDC ne reconnait pas la commande sourcesuivie du chemin absolue.
 *@author BUISSON-CHAVOT Julien
 *@version 2.0
 **/


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
			
			/**
			 *Création de la table d'album si elle n'existe pas :
			 *<ul>
			 *<li>Définition d'une clé primaire</li>
			 *<li>Définition du nom de l'utilisateur</li>
			 *<li>Définition de la date de l'album</li>
			 *<li>Définition d'un ID artiste auquel correspond l'album</li>
			 *<li>Définition d'une clé étrangère correspondant à un artiste</li>
			 *</ul>
			 **/
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Album(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	nom VARCHAR(30),\n" + 
					"	date VARCHAR(10),\n" + 
					"	idArtiste INT,\n" + 
					"	FOREIGN KEY fk_artiste(idArtiste) REFERENCES Artiste(id));");
			
			/**
			 *Même processus que précédemment
			 **/
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Chanson(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	titre VARCHAR(30),\n" + 
					"	duree INT,\n" + 
					"	idAlbum INT,\n" + 
					"	FOREIGN KEY fk_artiste(idAlbum) REFERENCES Album(id));");
			
			/**
			 *Même processus que précédemment
			 **/
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Film(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	titre VARCHAR(30),\n" + 
					"	annee INT);");
			
			/**
			 *Même processus que précédemment
			 **/
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Spectacle(\n" + 
					"	id INT PRIMARY KEY,\n" + 
					"	titre VARCHAR(30),\n" + 
					"	annee INT,\n" + 
					"	spectateurs INT);");
			
			/**
			 *Même processus que précédemment
			 **/
			
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS JouerFilm(\n" + 
					"	idFilm INT,\n" + 
					"	idArtiste INT,\n" + 
					"	CONSTRAINT pk_JouerFilm PRIMARY KEY (idFilm, idArtiste),\n" + 
					"	FOREIGN KEY fk_film(idFilm) REFERENCES Film(id) on update cascade,\n" + 
					"	FOREIGN KEY fk_artiste(idArtiste) REFERENCES Artiste(id) on update cascade );");
			
			/**
			 *Même processus que précédemment
			 **/
			
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
