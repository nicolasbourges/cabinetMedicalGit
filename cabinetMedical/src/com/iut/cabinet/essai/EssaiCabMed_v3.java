package com.iut.cabinet.essai;

import com.iut.cabinet.metier.Adresse;
import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.util.DateUtil;
import com.iut.cabinet.metier.CabinetTechniqueException;
import com.iut.cabinet.metier.Personne;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.metier.PersonneDAOFichier;
import com.iut.cabinet.metier.Professionnel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Essai de la version 3 de l'application pour le cabinet médical
 * @author Nicolas BOURGES
*/
public class EssaiCabMed_v3 {

	/**
	 * Cette classe permet de comparer les noms de deux personnes en vue d'un tri
	 * @param p1 la personne à comparer à
	 * @param p2 celle-ci
	 * @return 0 si le même nom, -1 si le nom de p2 est avant dans l'alphabet, 1 sinon
	 */
	class ComparateurNom implements Comparator<Personne>{
		private Logger logger = Logger.getLogger(ComparateurNom.class.getName());
		public int compare(Personne p1, Personne p2){
			if(logger.isDebugEnabled()){
				logger.debug("Entrée dans la méthode compare de ComparateurNom");
			}
			if(logger.isDebugEnabled()){
				logger.debug("Sortie de la méthode compare de ComparateurNom");
			}
			return p1.getNom().compareTo(p2.getNom());
		}
	}
	
	/**
	 * Cette classe permet de comparer les codes postaux de deux personnes en vue d'un tri
	 * @param p1 la personne à comparer à
	 * @param p2 celle-ci
	 * @return 0 si le même code postal, -1 si le code postal de p2 est plus petit que celui de p1, 1 sinon
	 */
	class ComparateurCodePostal implements Comparator<Personne>{
		private Logger logger = Logger.getLogger(ComparateurCodePostal.class.getName());
		public int compare(Personne p1, Personne p2){
			if(logger.isDebugEnabled()){
				logger.debug("Entrée dans la méthode compare de ComparateurCodePostal");
			}
			if(logger.isDebugEnabled()){
				logger.debug("Sortie de la méthode compare de ComparateurCodePostal");
			}
			return p1.getAdresse().getCodePostal().compareTo(p2.getAdresse().getCodePostal());
		}
	}
	
	/**
	 * Cette classe permet de comparer les dates de naissance de deux personnes en vue d'un tri
	 * @param p1 la personne à comparer à
	 * @param p2 celle-ci
	 * @return 0 si le même âge, -1 si p2 est né avant p1, 1 sinon
	 */
	class ComparateurAge implements Comparator<Personne>{
		private Logger logger = Logger.getLogger(ComparateurAge.class.getName());
		public int compare(Personne p1, Personne p2){
			if(logger.isDebugEnabled()){
				logger.debug("Entrée dans la méthode compare de ComparateurAge");
			}
			if(logger.isDebugEnabled()){
				logger.debug("Sortie de la méthode compare de ComparateurAge");
			}
			return DateUtil.toDate(p2.getDateNaissance().toString()).compareTo(DateUtil.toDate(p1.getDateNaissance().toString()));
		}
	}
	
	/**
	 * Cette classe permet de comparer les noms puis prénoms de deux personnes en vue d'un tri
	 * @param p1 la personne à comparer à
	 * @param p2 celle-ci
	 * @return 0 si le même nom et prénom, -1 si le nom (ou le prénom si mêmes noms) de p2 est avant dans l'alphabet, 1 sinon
	 */
	class ComparateurOrdreAlpha implements Comparator<Personne>{
		private Logger logger = Logger.getLogger(ComparateurOrdreAlpha.class.getName());
		public int compare(Personne p1, Personne p2){
			if(logger.isDebugEnabled()){
				logger.debug("Entrée dans la méthode compare de ComparateurOrdreAlpha");
			}
			if(p1.getNom().compareTo(p2.getNom())==0){
				if(logger.isDebugEnabled()){
					logger.debug("Sortie de la méthode compare de ComparateurOrdreAlpha");
				}
				return p1.getPrenom().compareTo(p2.getPrenom());
			}
			else{
				if(logger.isDebugEnabled()){
					logger.debug("Sortie de la méthode compare de ComparateurOrdreAlpha");
				}
				return p1.getNom().compareTo(p2.getNom());
			}
		}
	}
	
	public static void main(String[] args) throws CabinetMedicalException, ClassNotFoundException, CabinetTechniqueException {
		new EssaiCabMed_v3();
	}
EssaiCabMed_v3() throws CabinetMedicalException, ClassNotFoundException, CabinetTechniqueException{
	//les patients
	Patient personne1 = new Patient (1, "DUPONT", "Julie", DateUtil.toDate("1960-05-21"), false, "05.55.43.43.55", "0606060606", "julie.dupont@tralala.fr", new Adresse(null, "15", null, "avenue Jean Jaurès", "87000", "Limoges", "France"), null, "260058700112367", "LEDOC Paul");
	Patient personne2 = new Patient (2, "DUPONT", "Toto", DateUtil.toDate("1991-12-25"), true, "05-55-43-43-55", "0605040302", "toto.dupont@etu.unilim.fr", new Adresse("Résidence La Borie", "185", null, "avenue Albert Thomas", "87065", "Limoges", "France"), personne1, "260058700112367", "LEDOC Teur");
			
	//les pros
	Professionnel  personne3 = new Professionnel (3, "LEDOC", "Paul", DateUtil.toDate("1976-07-10"), true, "0555444343", "0612345678", "paul.ledoc@lesmedecins.fr", new Adresse(null, "3", null, "rue de Limoges", "87170", "Isle", "France"), null, "871255358", "Médecine générale");
	Professionnel  personne4 = new Professionnel (4, "CHILDREN", "Rose", DateUtil.toDate("1970-02-16"), false, "0555434343", "0607656321", "rose.chlidren@lesmedecins.fr", new Adresse(null, "10", null, "avenue de la Gare", "87000", "Limoges", "France"), null, "3124445555", "Pédiatre");
	
	
	List<Personne> maListe = new ArrayList <Personne>();
	
	maListe.add(personne1);
	maListe.add(personne2);
	maListe.add(personne3);
	maListe.add(personne4);
	
	System.out.println("--- Contenu de maListe de Personnes ---");
	
	for (Personne p : maListe){
		System.out.println(p.affichageSimplifie(p));
	}
	
	ComparateurNom cn = new ComparateurNom();
	Collections.sort(maListe, cn);
	
	System.out.println("--- Contenu de maListe de Personnes triée par nom ---");
	
	for (Personne p : maListe){
		System.out.println(p.affichageSimplifie(p));
	}
	
	ComparateurCodePostal cccp = new ComparateurCodePostal();
	Collections.sort(maListe, cccp);
	
	System.out.println("--- Contenu de maListe de Personnes triée par code postal ---");
	
	for (Personne p : maListe){
		System.out.println(p.affichageSimplifie(p));
	}
	
	ComparateurAge ca = new ComparateurAge();
	Collections.sort(maListe, ca);
	
	System.out.println("--- Contenu de maListe de Personnes triée par date de naissance ---");
	
	for (Personne p : maListe){
		System.out.println(p.affichageSimplifie(p));
	}
	
	ComparateurOrdreAlpha coa = new ComparateurOrdreAlpha();
	Collections.sort(maListe, coa);
	
	System.out.println("--- Contenu de maListe de Personnes triée par nom puis prénom ---");
	
	for (Personne p : maListe){
		System.out.println(p.affichageSimplifie(p));
	}
	
	//--------------------- TP 5 ---------------------
	System.out.println("--- La liste de personnes désérialisée AVANT TOUT... ---");
	Collection<Personne> maListDésérialiséeAvantTout = PersonneDAOFichier.findAllPersonnes();
	if(maListDésérialiséeAvantTout.isEmpty()){System.out.println("Liste vide!");}
	for (Personne p : maListDésérialiséeAvantTout){
		System.out.println(p.affichageSimplifie(p));
	}
	PersonneDAOFichier.storeAllPersonnes(maListe);
	
	System.out.println("--- La liste de personnes désérialisée... ---");
	Collection<Personne> maListDésérialisée = PersonneDAOFichier.findAllPersonnes();
	for (Personne p : maListDésérialisée){
		System.out.println(p.affichageSimplifie(p));
	}
}
}
