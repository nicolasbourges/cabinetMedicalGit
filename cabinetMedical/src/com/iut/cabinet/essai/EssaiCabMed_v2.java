package com.iut.cabinet.essai;

import com.iut.cabinet.metier.Adresse;
import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.util.DateUtil;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.metier.Professionnel;

/**
 * Essai de la version 2 de l'application pour le cabinet médical
 * @author Nicolas BOURGES
*/
public class EssaiCabMed_v2 {
	public static void main (String[] args) throws CabinetMedicalException{
		//appel au constructeur
		new EssaiCabMed_v2();
	}
	
	EssaiCabMed_v2() throws CabinetMedicalException{
		Patient personne1 = new Patient (1, "DUPONT", "Julie", DateUtil.toDate("1960-05-21"), false, "0555434355", "0606060606", "julie.dupont@tralala.fr", new Adresse(null, "15", null, "avenue Jean Jaurès", "87000", "Limoges", "France"), null, "260058700112367", "LEDOC Paul");
		Patient personne2 = new Patient (2, "DUPONT", "Toto", DateUtil.toDate("1991-12-25"), true, "0555434355", "0605040302", "toto.dupont@etu.unilim.fr", new Adresse("Résidence La Borie", "185", null, "avenue Albert Thomas", "87065", "Limoges", "France"), personne1, "260058700112368", "LEDOC Teur");
		System.out.println(personne1.toString());
		System.out.println(personne2.toString());
		
		Professionnel  personne3 = new Professionnel (3, "LEDOC", "Paul", DateUtil.toDate("1976-07-10"), true, "0555444343", "0612345678", "paul.ledoc@lesmedecins.fr", new Adresse(null, "3", null, "rue de Limoges", "87170", "Isle", "France"), null, "871255358", "Médecine générale");
		Professionnel  personne4 = new Professionnel (4, "CHILDREN", "Rose", DateUtil.toDate("1970-02-16"), false, "0555434343", "0607656321", "rose.chlidren@lesmedecins.fr", new Adresse(null, "10", null, "avenue de la Gare", "87000", "Limoges", "France"), null, "3124445555", "Pédiatre");
		System.out.println(personne3.toString());
		System.out.println(personne4.toString());
	}
}
