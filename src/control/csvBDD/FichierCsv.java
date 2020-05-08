package control.csvBDD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import control.activite.*;
import graphic.fenetre.FenetreFond;
import graphic.menusDeuxiemeFenetre.TopMenuDescriptif;;

public class FichierCsv {

	private String login;
	
	public FichierCsv(String login) { 
		super();
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	/*  la fonction permet d'obetnir le chemin absolue du fichier fileName:
	 * 
	 * En entrée, on met le chemin relatif du fichier.java
	 * 
	 * En sortie, on a le chemin absolue
	 * */

	public String getChemin(String fileName) {
		final File f = new File(""); //Fichier null pour avoir le chemin relatif de fichier.java en execution
		final String cheminDossier = f.getAbsolutePath() + File.separator + fileName;
		return cheminDossier;
	}

	/* la fonction permet d'obtenir le fichier fileName:
	 * 
	 * En entrée, on met le chemin relatif du fichier.java
	 * 
	 * On retrouve le chemin absolue avec getChemin()
	 * 
	 * En sortie, on a le fichier.csv sous le format File
	 * 
	 * */
	public File getFichier(String fileName) {
		final String cheminComplet = getChemin(fileName);
		File fichier = new File (cheminComplet);
		return fichier;
	}

	/* la fonction transforme un fichier.csv en List<String> avec impossibilité de séparer les collones entre elles puis en String[nb d'artiste][4]:
	 * 
	 * De fichier.csv en List<String>:
	 * 
	 * On ouvre le fichier et on le lit et testant l'ouverture (try, catch)
	 * Puis on ajoute chaque ligne au fichier List<String>
	 * Et on referme les lecteurs
	 * 
	 * 
	 * De List<String> en String[nb d'artiste][4]:
	 * 
	 * Pour chaque ligne de la liste, on récupère l'indice des "," (id1,id2,...)
	 * Puis on découpe chaque ligne entre les "," (substring)
	 * et on ajoute chaque partie au tableau 
	 * 
	 * */
	
	
	public List<String> fichierCsvList(File fichier){
		List<String> lignes = new ArrayList<String>();
		
		//Sont égales à null car on ne sait pas si le fichier existe
		FileReader fr = null;
		BufferedReader br = null;
		
		try { // La boucle teste si on arrive à lire le fichier en question
			fr = new FileReader(fichier);
			br = new BufferedReader(fr);

			
			for(String ligne = br.readLine(); ligne != null; ligne=br.readLine()) {
				lignes.add(ligne);
			}
			br.close();
			fr.close();
			
		} catch (IOException e) { //Sinon, elle retourne une erreur
			e.printStackTrace();
		}
		return lignes;
	}
	
	
	
	
	public String enregistrment(List<String> lignes){
		
		for (int i = 1; i<lignes.size();i++) { //On ne récupère pas le nom des colonnes (1ere ligne)
	
			//On récupère l'indice de toutes les virgules qui représentent le changement de cases pour couper la chaine de caractère par catégorie
			List<Integer> idVirgule = new ArrayList<Integer>();
			int id = lignes.get(i).indexOf(","); 
			while (id != -1) { // Tant qu'il reste des virgules
				idVirgule.add(id);
				id = lignes.get(i).indexOf(",", id+1);
			}
			
			
			String[] Artistes = new String[4]; // On crée cet intermédiaire pour plus de lisibilité
			
			//On crée l'artiste
			Artistes[0]=lignes.get(i).substring(0, idVirgule.get(0));
			Artistes[1]=lignes.get(i).substring(idVirgule.get(0)+1, idVirgule.get(1));
			Artistes[2]=lignes.get(i).substring(idVirgule.get(1)+1, idVirgule.get(2));
			Artistes[3]=lignes.get(i).substring(idVirgule.get(2)+1,idVirgule.get(3));
			Artiste artiste = new Artiste(Artistes[2], //Biblio
					"Bibliothèque/DataTestOeuvres/Artistes/"+Artistes[1]+".jpeg",//CheminversImage
					Artistes[1], // Nom
					Artistes[0], // Prenom
					Artistes[0].substring(0, 1) + "." + Artistes[1].toLowerCase() + "@gmail.com",//adresse mail=p.nom@gmail.com
					Artistes[3]); // Profession

			
			
			//On crée les oeuvres de l'artiste
			
			 int nbOeuvres = (idVirgule.size()-3)/6;
			String[][] Oeuvres = new String[nbOeuvres][6]; // Idem que pour Artistes et pour différencier les albums
			
			for (int j = 0; j<nbOeuvres;j++) {
				for (int k = 0; k<6 ;k++) {
					if ( 6*j+4+k != 21) {
						Oeuvres[j][k]=lignes.get(i).substring(idVirgule.get(6*j+3+k)+1,idVirgule.get(6*j+4+k));
					} else {
						Oeuvres[j][k]=lignes.get(i).substring(idVirgule.get(6*j+3+k)+1); //Dernière case, elle ne se termine pas par une virgule donc on ne donne que le début de la coupure
					}
				}
			}
			
			// Répartitons des oeuvre en fonction de la profession de l'artiste
			if (Artistes[3].equals("Chanteur")) {
				//On crée un set des différentes chansons
				Set<Album> albums = new HashSet<Album>(); // Tous les albums de l'artiste
				Set<Titre> chansons = new HashSet<Titre>(); // Toutes les chansons d'un album
				int k = 0;
				while (k!= nbOeuvres && Oeuvres[k][4].length()!=0) { //.length !=0 est s'arreter en cas de case vide
					String titre = Oeuvres[k][3]; //On supposera que les chansons d'un même album sont à la suite
					while (k!= nbOeuvres &&  Oeuvres[k][3].equals(titre)) { // on ajoute k!= nbOeuvres car k est incrémenter à chaque fois
						double duree = (double) Float.valueOf(Oeuvres[k][4]);
						Titre chanson = new Titre(Oeuvres[k][1], // Titre
												duree, // Durée
												"Bibliothèque/DataTestOeuvres/Musiques/"+Oeuvres[k][1]+".mp3"); // Emplacement du fichier mp3 = nom de la chanson.mp3
						// On regroupe ainsi toutes les chansons d'un même album
						chansons.add(chanson);
						k=k+1;
						
					}
					// On crée cet album
					Album album = new Album(titre,chansons,"Bibliothèque/DataTestOeuvres/Album/"+titre+".jpeg");
					System.out.println("Album: "+album.getCheminVersImageAssocie());
					albums.add(album); // et on l'ajoute à la liste des albums aves la liste des chansons du bon album
				}
				artiste.setMaListeDeAlbums(albums);
				
			} else { // l'artiste est un acteur ou un commédien
				Set<Representation> representations = new HashSet<Representation>(); // Toutes les chansons d'un album
				for (int k =0; k< nbOeuvres; k++) {
					Representation rep = new Representation(Oeuvres[k][1], // Titre
															Oeuvres[k][4], // Durée ?
															"Bibliothèque/DataTestOeuvres/representations/"+Oeuvres[k][1]+".jpeg", //Chemin vers image = titre.jpeg
															Oeuvres[k][2], //Date
															Oeuvres[k][0]); // Type -film ou comédie/spectacle
				representations.add(rep);
				}
				artiste.setMaListeDeRepresentations(representations);
				
				
			}
		personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().add(artiste); 
		personnesDejaInscrite.getInstance().sauvegarder();
		}
		FenetreFond.getInstance().retourEtatInitial(login);
		return "C'est bon";
	}
	
}