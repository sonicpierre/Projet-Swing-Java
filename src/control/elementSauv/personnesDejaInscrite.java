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


public class personnesDejaInscrite implements Serializable{//SINGLETON AUQUEL ON A ACCES DEPUIS LA FENETRE
	
	private static final long serialVersionUID = 289235133718470195L;//DETERMINE UN CHIFFRE OU UNE ADRESSE QUI REPRESENTE L'OBJET A N'IMPORTE QUEL TEMPS. ON L'ECRIT DANS UN FICHIER
	
	private HashMap<String, Compte> monHashMap;
	private static personnesDejaInscrite instance;
	
	
	private personnesDejaInscrite() {
		monHashMap = new HashMap<String, Compte>();//INSTANCIATION DE L'OBJET
		monHashMap.put("Utilisateur", new Compte("123", Color.BLACK, Color.WHITE));//AJOUT D'UN "ADMINISTRATEUR"
	}

	public HashMap<String, Compte> getMaListDePersonneInscrite() {
		return monHashMap;
	}
	
	public void sauvegarder() {
		ObjectOutputStream oos = null;//FLUX DE SORTIE
		
		//Va permettre de serializer un objet.
		
	    try {//TRY CATCH POUR EVITER LES ERREURS
	        final FileOutputStream fichier = new FileOutputStream("sauvegardeLogin.sauvFichier");//DECLARATION DU FICHIER D'ECRITURE
	        //On crée un flux de sortie 
	        oos = new ObjectOutputStream(fichier);
	        oos.writeObject(this);//ECRITURE DE L'OBJET A SERIALIZER
	        //Va permettre de forcer l'écriture dans le fichier pour vider le tampon
	        oos.flush();//LIBERATION DU CONTENANT
	        //Les problèmes d'écriture lié à l'écriture dans le fichier
	      } catch (final IOException e) {
	        e.printStackTrace();
	      } finally {
	        try {
	          if (oos != null) {
	            oos.flush();
	            oos.close();//FERMETURE DU FLUX S'IL N'EST PAS NUL
	          }
	        } catch (final IOException ex) {
	          ex.printStackTrace();
	        }
	      }
	}
	
	//On utilise ici la serialisation pour charger cet objet et le mettre à jour
	
	private static personnesDejaInscrite chargerObjet() {
	    ObjectInputStream ois = null;
	    personnesDejaInscrite personneInscrite = null;//INITIALISATION DE LA LECTURE D'UNE PERSONNE DEJA INSCRITE
	    
	    try {
	        final FileInputStream fichier = new FileInputStream("sauvegardeLogin.sauvFichier");//FICHIER A LIRE 
	        ois = new ObjectInputStream(fichier);
	        personneInscrite = (personnesDejaInscrite) ois.readObject();//ON DIRIGE LE FLUX VERS NOUS ET ON LE LIT
	        System.out.println("Fichier Charge");//CONFIRMATION DE CHARGEMENT
	      } catch (final java.io.IOException e) {
	        e.printStackTrace();
	      } catch (final ClassNotFoundException e) {
	        e.printStackTrace();
	      } finally {
	        try {
	          if (ois != null) {
	            ois.close();//FERMETURE DU FLUX
	          }
	        } catch (final IOException ex) {
	        	
	          ex.printStackTrace();
	        }
	      }
	    
	    return personneInscrite;
	}
    
	
	public static personnesDejaInscrite getInstance() {
		File f = new File("sauvegardeLogin.sauvFichier");//VERIFICATION D'EXITENCE ET CREATION LE CAS ECHEANT
		if ((instance == null) && (f.exists()))//LECTURE EN CAS D'EXISTENCE
			instance = chargerObjet();
		else if(instance == null)//CREATION D'UNE NOUVELLE PERSONNE INSCRITE
			instance = new personnesDejaInscrite();
			
		return instance;
	}
	
	public boolean rechercher(String loginEntre, String passewordEntre) {//VERIFICATION D'EXISTENCE
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
