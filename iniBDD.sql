CREATE DATABASE IF NOT EXISTS Artistak;

CREATE TABLE IF NOT EXISTS Artiste(
	id INT PRIMARY KEY,
	nom VARCHAR(30),
	biographie VARCHAR(1000),
	type VARCHAR(10));
CREATE TABLE IF NOT EXISTS Album(
	id INT PRIMARY KEY,
	nom VARCHAR(30),
	date DATE,
	idArtiste INT,
	FOREIGN KEY fk_artiste(idArtiste) REFERENCES Artiste(id));
CREATE TABLE IF NOT EXISTS Chanson(
	id INT PRIMARY KEY,
	titre VARCHAR(30),
	duree INT,
	idAlbum INT,
	FOREIGN KEY fk_artiste(idAlbum) REFERENCES Album(id));
CREATE TABLE IF NOT EXISTS Film(
	id INT PRIMARY KEY,
	titre VARCHAR(30),
	annee INT);
CREATE TABLE IF NOT EXISTS Spectacle(
	id INT PRIMARY KEY,
	titre VARCHAR(30),
	annee INT,
	spectateurs INT);
CREATE TABLE IF NOT EXISTS JouerFilm(
	idFilm INT,
	idArtiste INT,
	CONSTRAINT pk_JouerFilm PRIMARY KEY (idFilm, idArtiste),
	FOREIGN KEY fk_film(idFilm) REFERENCES Film(id),
	FOREIGN KEY fk_artiste(idArtiste) REFERENCES Artiste(id));
CREATE TABLE IF NOT EXISTS JouerSpectacle(
	idSpectacle INT,
	idArtiste INT,
	CONSTRAINT pk_JouerFilm PRIMARY KEY (idSpectacle, idArtiste),
	FOREIGN KEY fk_spectacle(idSpectacle) REFERENCES Spectacle(id),
	FOREIGN KEY fk_artiste(idArtiste) REFERENCES Artiste(id));