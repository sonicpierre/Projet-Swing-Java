package control.csvBDD;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FichierCsv c = new FichierCsv("Lucille");
		List<String> resultat=c.fichierCsvList((c.getFichier("Bibliothèque/DataTest Oeuvres/data.csv")));
		c.enregistrment(resultat);
	}

}
