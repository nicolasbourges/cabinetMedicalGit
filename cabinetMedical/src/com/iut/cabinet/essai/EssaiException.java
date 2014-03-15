package com.iut.cabinet.essai;

import com.iut.cabinet.metier.Adresse;
import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.util.DateUtil;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.metier.Professionnel;
import com.iut.cabinet.essai.ListePersonneCabinetTab;
import com.iut.cabinet.metier.PatientRegle;

/**
 * Essai concernant les exceptions et le contrôle du NIR de l'application pour le cabinet médical
 * @author Nicolas BOURGES
*/
public class EssaiException {
	public static void main (String[] args) throws CabinetMedicalException{
		//appel au constructeur
		new EssaiException();
}
	EssaiException() throws CabinetMedicalException{
		//les patients
		Patient personne1 = new Patient (1, "DUPONT", "Julie", DateUtil.toDate("1960-05-21"), false, "0555434355", "0606060606", "julie.dupont@tralala.fr", new Adresse(null, "15", null, "avenue Jean Jaurès", "87000", "Limoges", "France"), null, "260058700112367", "LEDOC Paul");
		Patient personne2 = new Patient (2, "DUPONT", "Toto", DateUtil.toDate("1991-12-25"), true, "0555434355", "0605040302", "toto.dupont@etu.unilim.fr", new Adresse("Résidence La Borie", "185", null, "avenue Albert Thomas", "87065", "Limoges", "France"), personne1, "260058700112367", "LEDOC Teur");
		
		//les pros
		Professionnel  personne3 = new Professionnel (3, "LEDOC", "Paul", DateUtil.toDate("1976-07-10"), true, "0555444343", "0612345678", "paul.ledoc@lesmedecins.fr", new Adresse(null, "3", null, "rue de Limoges", "87170", "Isle", "France"), null, "871255358", "Médecine générale");
		Professionnel  personne4 = new Professionnel (4, "CHILDREN", "Rose", DateUtil.toDate("1970-02-16"), false, "0555434343", "0607656321", "rose.chlidren@lesmedecins.fr", new Adresse(null, "10", null, "avenue de la Gare", "87000", "Limoges", "France"), null, "3124445555", "Pédiatre");
		
		//Ajout dans le tableau
		int nb = 0;
		ListePersonneCabinetTab liste = new ListePersonneCabinetTab();
		liste.ajouterPersonne(personne1, nb);
		liste.ajouterPersonne(personne2, nb);
		liste.ajouterPersonne(personne3, nb);
		liste.ajouterPersonne(personne4, nb);
		
		//affichage
		liste.afficheListe();
		System.out.println("\n Les patients: \n");
		liste.afficherPatient();
		System.out.println("\n Les pros: \n");
		liste.afficheProfessionnel();
		
		//test des NIRS
		try{
		System.out.println("\n 260058700112367 est-il valide: ");
		PatientRegle.verifierNir("260058700112367");
		
		System.out.println("\n 191128708545628 est-il valide: ");
		PatientRegle.verifierNir("191128708545628");

		System.out.println("\n 194018708520553 est-il valide: ");
		PatientRegle.verifierNir("194018708520553");

		System.out.println("\n 297112A10102401 est-il valide: ");
		PatientRegle.verifierNir("297112A10102401");

		System.out.println("\n 168072B12345652 est-il valide: ");
		PatientRegle.verifierNir("168072B12345652");
		
		//System.out.println("\n 168072B12345653 est-il valide: ");
		//PatientRegle.verifierNir("168072012345653");
		
		System.out.println("\n 1Z807B12345651 est-il valide: ");
		PatientRegle.verifierNir("1Z807B12345651");
		}
		catch(CabinetMedicalException e){
			System.out.println("NIR invalide!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
