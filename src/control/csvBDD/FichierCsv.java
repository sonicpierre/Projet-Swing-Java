package control.csvBDD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FichierCsv {

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
	
	
	public String[][] lireFichier(File fichier){
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
			String[][] er ={{"Erreur de lecture"}};
			return er;
		}
		
		String[][] data = new String[lignes.size()-1][4];
		
		for (int i = 1; i<lignes.size();i++) { //On ne récupère pas le nom des colonnes
			
			int id1 = lignes.get(i).indexOf(",");
			int id2 = lignes.get(i).indexOf(",", id1+1);
			int id3 = lignes.get(i).indexOf(",", id2+1);
			
			data[i-1][0]=lignes.get(i).substring(0, id1);
			data[i-1][1]=lignes.get(i).substring(id1+1, id2);
			data[i-1][2]=lignes.get(i).substring(id2+1, id3);
			data[i-1][3]=lignes.get(i).substring(id3+1,lignes.get(i).length() );
		}
		return data;
		
	}
	
	/* Exemple de main
	 public static void main(String[] args) throws IOException { //IOException sera demandé pour fonctionner
		// TODO Auto-generated method stub

		FichierCsv c = new FichierCsv();
		String[][] resultat;
		resultat = c.lireFichier(c.getFichier("Bibliothèque/DataTestCSV/data.csv")); //Chemin fictif pour expliquer ou est le csv utilisé
		System.out.println(resultat[11][1]);
	}
	 *
	 * Doit retourner "Céline Dion"
	 * */
}
