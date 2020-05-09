package control.csvBDD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import control.BDD.Modification;
import control.activite.Album;
import control.activite.Representation;
import control.activite.Titre;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetre.FenetreFond;;

/**
 * <b>FichierCsv</b> est la classe qui permet d'importer un fichier CSV dans
 * l'application
 * 
 * @author BEZIAT Lucille
 * @version 2.0
 **/

public class FichierCsv {

	/**
	 * Login utilisateur
	 **/

	private String login;

	/**
	 * Déclaration du fichier CSV
	 * 
	 * @param login Login utilisateur
	 **/

	public FichierCsv(String login) {
		super();
		this.login = login;
	}

	/**
	 * Récupère le login
	 * 
	 * @return Login utilisateur
	 **/

	public String getLogin() {
		return login;
	}

	/**
	 * Initialisation du login utilisateur
	 * 
	 * @param login LOgin utilisateur
	 **/

	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Permet d'obtenir le chemin absolu du fichier fileName
	 * 
	 * @param fileName Chelin relatif du fichier .java
	 * @return Chemin absolu
	 **/

	public String getChemin(String fileName) {
		final File f = new File(""); // Fichier null pour avoir le chemin relatif de fichier.java en execution
		final String cheminDossier = f.getAbsolutePath() + File.separator + fileName;
		return cheminDossier;
	}

	/**
	 * Permet d'obtenir le fichier fileName
	 * 
	 * @param fileName Chemin relatif du fichier java
	 * @return Fichier CSV sous le format File
	 **/

	public File getFichier(String fileName) {
		final String cheminComplet = getChemin(fileName);
		File fichier = new File(cheminComplet);
		return fichier;
	}

	/*
	 * la fonction transforme un fichier.csv en List<String> avec impossibilité de
	 * séparer les collones entre elles puis en String[nb d'artiste][4]:
	 * 
	 * De fichier.csv en List<String>:
	 * 
	 * On ouvre le fichier et on le lit et testant l'ouverture (try, catch) Puis on
	 * ajoute chaque ligne au fichier List<String> Et on referme les lecteurs
	 * 
	 * 
	 * De List<String> en String[nb d'artiste][4]:
	 * 
	 * Pour chaque ligne de la liste, on récupère l'indice des "," (id1,id2,...)
	 * Puis on découpe chaque ligne entre les "," (substring) et on ajoute chaque
	 * partie au tableau
	 * 
	 */

	/**
	 * Permet de transformer un fichier.csv en List<String> avec impossibilité de
	 * séparer les collones entre elles puis en String[nb d'artiste][4]: Ainsi, le
	 * passage de fichier.csv en List<String> se fait comme suit :
	 * <p>
	 * On ouvre le fichier et on le lit et testant l'ouverture (try, catch) Puis on
	 * ajoute chaque ligne au fichier List<String> Et on referme les lecteurs
	 * </p>
	 *
	 * Celui de De List<String> en String[nb d'artiste][4] :
	 * <p>
	 * Pour chaque ligne de la liste, on récupère l'indice des "," (id1,id2,...)
	 * Puis on découpe chaque ligne entre les "," (substring) et on ajoute chaque
	 * partie au tableau
	 * </p>
	 * 
	 * @return Lignes du fichier
	 **/

	public List<String> fichierCsvList(File fichier) {

		/**
		 * Liste de lignes comme tableau de liste
		 **/

		List<String> lignes = new ArrayList<String>();

		/**
		 * Initialisation des reader NB : Initialisée à null car on ne sait pas si le
		 * fichier existe
		 **/

		FileReader fr = null;

		/**
		 * Initialisation des reader NB : Initialisée à null car on ne sait pas si le
		 * fichier existe
		 **/

		BufferedReader br = null;

		/**
		 * Test de lecture du fichier
		 **/

		try {
			fr = new FileReader(fichier);
			br = new BufferedReader(fr);

			for (String ligne = br.readLine(); ligne != null; ligne = br.readLine()) {
				lignes.add(ligne);
			}
			br.close();
			fr.close();

			/**
			 * Retourne une erreur sinon
			 **/

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lignes;
	}

	/**
	 * Permet d'effectuer les enregistrements de lignes
	 * 
	 * @param lignes Lignes du fichier
	 * @return Etat de validation de l'enregistrement
	 * @see Artiste
	 **/

	public String enregistrment(List<String> lignes) {

		/**
		 * On ne récupère pas le nom des colonnes (1ere ligne)
		 **/
		for (int i = 1; i < lignes.size(); i++) {

			/**
			 * On récupère l'indice de toutes les virgules qui représentent le changement de
			 * cases pour couper la chaine de caractère par catégorie
			 **/
			List<Integer> idVirgule = new ArrayList<Integer>();
			int id = lignes.get(i).indexOf(",");

			/**
			 * Tant qu'il reste des virgules
			 **/

			while (id != -1) {
				idVirgule.add(id);
				id = lignes.get(i).indexOf(",", id + 1);
			}

			/**
			 * Création d'un intermédiaire pour la lisibilité
			 **/

			String[] Artistes = new String[4];

			/**
			 * Création de l'artiste
			 **/

			Artistes[0] = lignes.get(i).substring(0, idVirgule.get(0));
			Artistes[1] = lignes.get(i).substring(idVirgule.get(0) + 1, idVirgule.get(1));
			Artistes[2] = lignes.get(i).substring(idVirgule.get(1) + 1, idVirgule.get(2));
			Artistes[3] = lignes.get(i).substring(idVirgule.get(2) + 1, idVirgule.get(3));

			/**
			 * Bibliothèque, chemin vers l'image, Informations sur l'artiste et e-mail
			 **/

			Artiste artiste = new Artiste(Artistes[2], "Bibliothèque/DataTestOeuvres/Artistes/" + Artistes[1] + ".jpeg",
					Artistes[1], Artistes[0],
					Artistes[0].substring(0, 1) + "." + Artistes[1].toLowerCase() + "@gmail.com", Artistes[3]);

			/**
			 * Création des oeuvres de l'artiste
			 **/

			int nbOeuvres = (idVirgule.size() - 3) / 6;

			/**
			 * Idem que pour Artistes et pour différencier les albums
			 **/

			String[][] Oeuvres = new String[nbOeuvres][6];

			for (int j = 0; j < nbOeuvres; j++) {
				for (int k = 0; k < 6; k++) {
					if (6 * j + 4 + k != 21) {
						Oeuvres[j][k] = lignes.get(i).substring(idVirgule.get(6 * j + 3 + k) + 1,
								idVirgule.get(6 * j + 4 + k));
					} else {
						/**
						 * Dernière case, elle ne se termine pas par une virgule donc on ne donne que le
						 * début de la coupure
						 **/

						Oeuvres[j][k] = lignes.get(i).substring(idVirgule.get(6 * j + 3 + k) + 1);
					}
				}
			}

			/**
			 * Répartiton des oeuvre en fonction de la profession de l'artiste
			 **/
			if (Artistes[3].equals("Chanteur")) {
				/**
				 * On crée un set des différentes chansons
				 */

				/**
				 * Set de tous les albums de l'artiste
				 **/

				Set<Album> albums = new HashSet<Album>();

				/**
				 * Set des chansons d'un album
				 **/

				Set<Titre> chansons = new HashSet<Titre>();
				int k = 0;

				/**
				 * length !=0 est la condition d'arret en cas de case vide
				 **/

				while (k != nbOeuvres && Oeuvres[k][4].length() != 0) {

					/**
					 * On supposera que les chansons d'un même album sont à la suite
					 */
					String titre = Oeuvres[k][3];

					/**
					 * On ajoute k!= nbOeuvres car k est incrémenter à chaque fois
					 */
					while (k != nbOeuvres && Oeuvres[k][3].equals(titre)) {
						double duree = (double) Float.valueOf(Oeuvres[k][4]);
						Titre chanson = new Titre(Oeuvres[k][1], duree,
								"Bibliothèque/DataTestOeuvres/Musiques/" + Oeuvres[k][1] + ".mp3");
						/**
						 * On regroupe ainsi toutes les chansons d'un même album
						 **/

						chansons.add(chanson);
						k = k + 1;

					}
					/**
					 * Création de l'album
					 **/

					Album album = new Album(titre, chansons, "Bibliothèque/DataTestOeuvres/Album/" + titre + ".jpeg");
					System.out.println("Album: " + album.getCheminVersImageAssocie());

					/**
					 * Ajout de l'album à la liste des albums avec la liste des chansons du bon
					 * album
					 **/

					albums.add(album);
				}
				artiste.setMaListeDeAlbums(albums);

				/**
				 * Cas où l'artiste est un acteur ou un comédien
				 **/

			} else {
				Set<Representation> representations = new HashSet<Representation>();
				for (int k = 0; k < nbOeuvres; k++) {
					Representation rep = new Representation(Oeuvres[k][1], Oeuvres[k][4],
							"Bibliothèque/DataTestOeuvres/representations/" + Oeuvres[k][1] + ".jpeg", Oeuvres[k][2],
							Oeuvres[k][0]);
					representations.add(rep);
				}
				artiste.setMaListeDeRepresentations(representations);

			}
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste()
					.add(artiste);
			Modification.getInstance().insererArtiste(artiste.hashCode(), artiste.getNom(), artiste.getDescription(),
					artiste.getType());

			if (artiste.getType().equals("Chanteur")) {
				for (Album monAlbum : artiste.getMaListeDeAlbums()) {
					Modification.getInstance().insererAlbum(monAlbum.hashCode(), monAlbum.getTitre(), "2020",
							artiste.hashCode());
					for (Titre monTitre : monAlbum.getChansonsDelAlbum())
						Modification.getInstance().insererChanson(monTitre.hashCode(), monTitre.getTitre(),
								(int) monTitre.getDuree(), monAlbum.hashCode());
				}

			} else {
				for (Representation maRepres : artiste.getMaListeDeRepresentations()) {
					if (artiste.getType().equals("Acteur")) {
						Modification.getInstance().insererFilm(maRepres.hashCode(), maRepres.getTitre(), 2020);
						Modification.getInstance().insererJouerFilm(maRepres.hashCode(), artiste.hashCode());
					} else {
						Modification.getInstance().insererSpectacle(maRepres.hashCode(), maRepres.getTitre(), 2020,
								100);
						Modification.getInstance().insererJouerFilm(maRepres.hashCode(), artiste.hashCode());
					}
				}
			}
			personnesDejaInscrite.getInstance().sauvegarder();
		}

		FenetreFond.getInstance().retourEtatInitial(login);
		return "C'est bon";
	}

}