package control.elementSauv;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import control.personne.Compte;

/**
 * <b>personneDejaInscrite</b> est la classe qui permet de vérifier l'inscription d'une personne.
 * <p>Cette vérification suit le processus suivant : 
 * <ul>
 * <li>Détermination d'un format de représentation de l'objet à n'importe quel moment</li>
 * <li>Initialisation de l'objet</li>
 * <li>Déclaration d'un fichier de sauvegarde d'information</li>
 * <li>Vérification d'existence ou création d'un nouvel inscrit</li>
 * </ul>
 * NB : Il s'agit d'un singleton auquel on a acces depuis la fenêtre.
 * </p>
 * @author VIRGAUX Pierre
 **/

public class personnesDejaInscrite implements Serializable{
	
	/**
	 *Détermination du format de représentation à écrire dans le fichier.
	 **/
	
	private static final long serialVersionUID = 289235133718470195L;
	
	/**
	 *Initialisation d'une table de hashage permettant d'associer une clé à une valeur.
	 **/
	
	private HashMap<String, Compte> monHashMap;
	
	private static personnesDejaInscrite instance;
	
	/**
	 *Dans un premier temps, on instancie l'objet
	 *puis on crée un utilisateur de type administrateur. 
	 **/
	
	private personnesDejaInscrite() {
		
		/**
		 *Instanciation de l'objet
		 **/
		
		monHashMap = new HashMap<String, Compte>();
		
		/**
		 *Ajout d'un profil administrateur
		 **/
		
		monHashMap.put("Utilisateur", new Compte("123", Color.BLACK, Color.WHITE,"ImageProfil/inconnu.jpg"));
	}
	
	/**
	 * Retourne une correspondance clé-valeur.
	 *@return Une instance de personneDejaInscrite, qui lie une clé à une valeur.
	 **/
	
	public HashMap<String, Compte> getMaListDePersonneInscrite() {
		return monHashMap;
	}
	
	/**
	 *La méthode permet de serializer un objet via un flux de sortie initialisé.
	 **/
	
	public void sauvegarder() {
		
		/**
		 *Flux de sortie
		 **/
		
		ObjectOutputStream oos = null;
		
		/**
		 *Sérialization en utilisant un "try catch" afin d'éviter les erreurs.
		 **/
	    try {
	    	/**
	    	 *Déclaration du fichier d'écriture
	    	 **/
	    	
	        final FileOutputStream fichier = new FileOutputStream("sauvegardeLogin.sauvFichier");
	        
	        
	        oos = new ObjectOutputStream(fichier);
	        
	        /**
	         *Ecriture de l'objet à serializer
	         **/
	        
	        oos.writeObject(this);
	        
	        /**Force l'écriture dans le fichier pour libérer le contenant
	         **/
	        
	        oos.flush();
	        
	        /**Gestion des problèmes liés à l'écriture dans le fichier
	         **/
	        
	      } catch (final IOException e) {
	        e.printStackTrace();
	      } finally {
	        try {
	          if (oos != null) {
	            oos.flush();
	            
	            /**Fermeture du flux s'il n'est pas vide
	             **/
	            
	            oos.close();
	          }
	        } catch (final IOException ex) {
	          ex.printStackTrace();
	        }
	      }
	}
	
	/**
	 *Utilisation de la serialization pour charger l'objet et le mettre à jour selon le procédé suivant :
	 *<ul>
	 *<li>Initialisation de la lecture d'une personne déjà inscrite</li>
	 *<li>Indexation du fichier de lecture</li>
	 *<li>Lecture du flux et affichage de la confirmation du chargement</li>
	 *<li>Fermeture du flux</li>
	 *@return Personne inscrite dans le fichier
	 *</ul>
	 **/
	
	private static personnesDejaInscrite chargerObjet() {
	    ObjectInputStream ois = null;
	    personnesDejaInscrite personneInscrite = null;
	    
	    try {
	        final FileInputStream fichier = new FileInputStream("sauvegardeLogin.sauvFichier");
	        ois = new ObjectInputStream(fichier);
	        personneInscrite = (personnesDejaInscrite) ois.readObject();
	        System.out.println("Fichier Charge");
	      } catch (final java.io.IOException e) {
	        e.printStackTrace();
	      } catch (final ClassNotFoundException e) {
	        e.printStackTrace();
	      } finally {
	        try {
	          if (ois != null) {
	            ois.close();
	          }
	        } catch (final IOException ex) {
	        	
	          ex.printStackTrace();
	        }
	      }
	    
	    return personneInscrite;
	}
    
	/**
	 *Traitement du fichier d'enregstrement selon le processus suivant : 
	 *<ul>
	 *<li>Vérification de l'existence du fichier et sa crétion le cas écheant</li>
	 *<li>Lecture du fichier en cas d'existence</li>
	 *<li>Création d'une nouvelle personne inscrite</li>
	 *</ul>
	 *@return Fichier crée avec personne inscrite.
	 **/
	
	public static personnesDejaInscrite getInstance() {
		File f = new File("sauvegardeLogin.sauvFichier");
		if ((instance == null) && (f.exists()))
			instance = chargerObjet();
		else if(instance == null)
			instance = new personnesDejaInscrite();
			
		return instance;
	}
	
	/**
	 *Vérification de l'existence de la personne.
	 *@return True si la personne existe.
	 **/
	
	public boolean rechercher(String loginEntre, String passewordEntre) {
		try {
			if((monHashMap.get(loginEntre) != null)&&(monHashMap.get(loginEntre).getPasseword().getObject().equals(passewordEntre))) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
