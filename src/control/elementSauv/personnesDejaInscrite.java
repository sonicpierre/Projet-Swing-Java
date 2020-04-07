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


public class personnesDejaInscrite implements Serializable{
	
	private static final long serialVersionUID = 289235133718470195L;
	
	private HashMap<String, Compte> monHashMap;
	private static personnesDejaInscrite instance;
	
	
	private personnesDejaInscrite() {
		monHashMap = new HashMap<String, Compte>();
		monHashMap.put("Utilisateur", new Compte("123", Color.BLACK, Color.WHITE));
	}

	public HashMap<String, Compte> getMaListDePersonneInscrite() {
		return monHashMap;
	}
	
	public void sauvegarder() {
		ObjectOutputStream oos = null;
		
		//Va permettre de serializer un objet.
		
	    try {
	        final FileOutputStream fichier = new FileOutputStream("sauvegardeLogin.sauvFichier");
	        //On crée un flux de sortie 
	        oos = new ObjectOutputStream(fichier);
	        oos.writeObject(this);
	        //Va permettre de forcer l'écriture dans le fichier pour vider le tampon
	        oos.flush();
	        //Les problèmes d'écriture lié à l'écriture dans le fichier
	      } catch (final IOException e) {
	        e.printStackTrace();
	      } finally {
	        try {
	          if (oos != null) {
	            oos.flush();
	            oos.close();
	          }
	        } catch (final IOException ex) {
	          ex.printStackTrace();
	        }
	      }
	}
	
	//On utilise ici la serialisation pour charger cet objet et le mettre à jour
	
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
    
	
	public static personnesDejaInscrite getInstance() {
		File f = new File("sauvegardeLogin.sauvFichier");
		if ((instance == null) && (f.exists()))
			instance = chargerObjet();
		else if(instance == null)
			instance = new personnesDejaInscrite();
			
		return instance;
	}
	
	public boolean rechercher(String loginEntre, String passewordEntre) {
		if((monHashMap.get(loginEntre) != null)&&(monHashMap.get(loginEntre).getPasseword().equals(passewordEntre))) {
			return true;
		}
		return false;
	}

}
