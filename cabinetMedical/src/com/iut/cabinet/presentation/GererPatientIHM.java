package com.iut.cabinet.presentation;


import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.iut.cabinet.application.GererPatientCtrl;
import com.iut.cabinet.application.HelperException;
import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.CabinetTechniqueException;
import com.iut.cabinet.user.AdresseDTO;
import com.iut.cabinet.user.PatientDTO;
import com.iut.cabinet.util.DateUtil;

/**
 * Classe correspondant à l'IHM (Interface Homme-Machine) dans le MVC du cabinet médical
 * @author Nicolas BOURGES
 */
public class GererPatientIHM {
	private static Logger logger = Logger.getLogger(GererPatientIHM.class.getName());
	
	GererPatientCtrl ctrlUseCase= new GererPatientCtrl();
	
	public GererPatientIHM() throws CabinetTechniqueException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans le constructeur de GererPatientIHM");
		}
		
		System.out.println("\t\t --- CABINET MEDICAL ---");
		
		int monChoix;
		
		do {
			menu();
			try {
				System.out.println(" ---> Votre Choix : ");
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				monChoix = sc.nextInt();
			} catch (Exception e) {
				monChoix = -1;
			} 

			switch (monChoix) {
			case 1:
				System.out.println("-- Creer un patient ---\n");
				creerPatient();
				System.out.println("--------------------------------------");
				break;

			case 2:
				System.out.println("-- Modifier un patient ---\n");
				modifierPatient();
				System.out.println("--------------------------------------");
				break;

			case 3:
				System.out.println("--- Supprimer un patient ---\n");
				supprimerPatient();
				System.out.println("--------------------------------------");
				break;

			case 4:
				System.out.println("--- Liste des patients  --- \n");
				listerPatients();
				System.out.println("--------------------------------------");
				break;

			case 0:
				System.out.println("\n\n--- Au revoir ... ");
				break;
			default:
				System.out.println("--- Veuillez saisir un entier entre 0 et 4 ---");
				System.out.println("--------------------------------------");
			}
		} while (monChoix != 0);
		if(logger.isDebugEnabled()){
			logger.debug("Sortie du constructeur de GererPatientIHM");
		}
	}

	/**
	 * Méthode pour afficher le menu dans la console
	 */
	private static void menu() {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode menu");
		}
		// Affichage du menu
		
		System.out.println("\n\n------- Gestion des Patients --------");
		System.out.println("-- 1. Creer un patient");
		System.out.println("-- 2. Modifier un patient");
		System.out.println("-- 3. Supprimer un patient");
		System.out.println("-- 4. Lister tous les patients");
		System.out.println("-- 0. Quitter");
		System.out.println("-------------------------------------");

		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode menu");
		}
	}

	/**
	 * Méthode pour créer un PatientDTO puis le stocker en mémoire à l'aide du contrôleur
	 */
	@SuppressWarnings("resource")
	public void creerPatient() {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode creerPatient de GererPatientIHM");
		}
	
		System.out
				.println(" \n ---------------------------------------------------------------");
		System.out
				.println(" \n ------ CREATION D'UN NOUVEAU PATIENT (sans ascendant) ---------");
		System.out
				.println(" \n ---------------------------------------------------------------");

		Scanner sc;
		String nir="";
		String nom="";
		String prenom="";
		String s_dateNaissance="";
		String telephone="";
		String portable="";
		String email="";
		String medecinTraitant="";
		String numero="";
		String codePostal="";
		String ville="";
		String pays="";
		Integer idPersonne=0;

		boolean isMale = false;
		String voie="";
		String rue="";
		String batiment="";
		Date dateNaissance=DateUtil.sysdate();
		
		Matcher maNom;
		Matcher maPrenom;
		Matcher maTelNum;
		Matcher maTelNumPoints;
		Matcher maTelNumTirets;
		Matcher maMail;
		Matcher maMailPlusRare;
		Matcher maCodePostal;
		
		
		// nir
		System.out.println(" \t Saisir le NIR du Patient : ");
		sc = new Scanner(System.in);
		nir = sc.next();

		// nom
		do{
		System.out.println(" \t Saisir le nom du Patient : ");
		sc = new Scanner(System.in);
		nom = sc.next();
		Pattern paNom = Pattern.compile("[A-Z]+");
		maNom = paNom.matcher(nom);
		}while(!maNom.matches());

		// prenom
		do{
		System.out.println(" \t Saisir le prénom du Patient : ");
		sc = new Scanner(System.in);
		prenom = sc.next();
		Pattern paPrenom = Pattern.compile("[A-Z][a-z]+");
		maPrenom = paPrenom.matcher(prenom);
		}while(!maPrenom.matches());

		// date de Naissance
		System.out.println(" \t Saisir la date de naissance (au format : jj/mm/aaaa): ");
		sc = new Scanner(System.in);
		s_dateNaissance = sc.next();
		dateNaissance = DateUtil.toDate(s_dateNaissance, DateUtil.FRENCH_DEFAUT);

		// sexe
		System.out.println(" \t Saisir le sexe (m ou f) du patient : ");
		sc = new Scanner(System.in);
		char c_isMale = sc.next().charAt(0);
		isMale = (c_isMale == 'm');
		
		// téléphone
		do{
		System.out.println(" \t Saisir le téléphone du patient : ");
		sc = new Scanner(System.in);
		telephone = sc.next();
		Pattern paTelNum = Pattern.compile("[0-9]{10}");
		maTelNum = paTelNum.matcher(telephone);
		Pattern paTelNumPoints = Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}");
		maTelNumPoints = paTelNumPoints.matcher(telephone);
		Pattern paTelNumTirets = Pattern.compile("[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}");
		maTelNumTirets = paTelNumTirets.matcher(telephone);
		}while(!maTelNum.matches() && !maTelNumPoints.matches() && !maTelNumTirets.matches());

		// portable
		do{
		System.out.println(" \t Saisir le portable du patient : ");
		sc = new Scanner(System.in);
		portable = sc.next();
		Pattern paTelNum = Pattern.compile("[0-9]{10}");
		maTelNum = paTelNum.matcher(portable);
		Pattern paTelNumPoints = Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}");
		maTelNumPoints = paTelNumPoints.matcher(portable);
		Pattern paTelNumTirets = Pattern.compile("[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}");
		maTelNumTirets = paTelNumTirets.matcher(portable);
		}while(!maTelNum.matches() && !maTelNumPoints.matches() && !maTelNumTirets.matches());

		// email
		do{
		System.out.println(" \t Saisir l'email du patient : ");
		sc = new Scanner(System.in);
		email = sc.next();
		Pattern paMail = Pattern.compile("[a-zA-Z\\.]+@[a-z]+\\.[a-z]{2,4}");
		maMail = paMail.matcher(email);
		Pattern paMailPlusRare = Pattern.compile("[a-zA-Z\\.]+@[a-z]+\\.[a-z]+\\.[a-z]{2,4}");
		maMailPlusRare = paMailPlusRare.matcher(email);
		}while(!maMail.matches() && !maMailPlusRare.matches());

		// adresse
		System.out.println(" \t Saisir l'adresse du patient : ");

		// numero
		System.out.println(" \t \t numero : ");
		sc = new Scanner(System.in);
		numero = sc.next();

		// rue
		System.out.println(" \t \t rue : ");
		sc = new Scanner(System.in);
		rue = sc.nextLine(); 
		
		// voie
		System.out.println(" \t \t voie : ");
		sc = new Scanner(System.in);
		voie = sc.nextLine();

		// batiment
		System.out.println(" \t \t batiment : ");
		sc = new Scanner(System.in);
		batiment = sc.nextLine();

		// codePostal
		do{
		System.out.println(" \t \t codePostal : ");
		sc = new Scanner(System.in);
		codePostal = sc.next();
		Pattern paCodePostal = Pattern.compile("[0-9]{5}");
		maCodePostal = paCodePostal.matcher(codePostal);
		}while(!maCodePostal.matches());

		// ville
		System.out.println(" \t \t ville : ");
		sc = new Scanner(System.in);
		ville = sc.nextLine();

		// pays
		System.out.println(" \t \t pays : ");
		sc = new Scanner(System.in);
		pays = sc.nextLine();

		// Medecin Traitant
		System.out.println(" \t Saisir le nom et prénom du médecin Traitant : ");
		sc = new Scanner(System.in);
		medecinTraitant = sc.nextLine();

		
		// numéro du Patient
		AdresseDTO adrDTO = new AdresseDTO(batiment, codePostal, numero, pays, rue, ville, voie);
		PatientDTO patDTO = new PatientDTO(idPersonne, nom, prenom, dateNaissance, isMale, telephone, portable, email, adrDTO, null, nir, medecinTraitant);
		
		// appel au controleur de cas d'utilisation
		try {
			ctrlUseCase.creerPatient(patDTO);
			System.out.println("Le nouveau patient a bien été ajouté!");
		} catch (CabinetMedicalException e) {
			e.printStackTrace();
			logger.error("Déclenchement d'une exception métier dans la méthode creerPatient de GererPatientIHM");
		} catch (HelperException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (CabinetTechniqueException e) {
			e.printStackTrace();
			logger.fatal("Déclenchement d'une exception technique dans la méthode creerPatient de GererPatientIHM");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode creerPatient de GererPatientIHM");
		}
	}

	/**
	 * Méthode pour modifier un PatientDTO puis répercuter la modification en mémoire à l'aide du contrôleur
	 */	
	public void modifierPatient() {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode modifierPatient de GererPatientIHM");
		}
		
		String nom;
		String prenom;
		String nir;
		String champAModifier="";
		String modif;

		//Déclaration d'une collection de PatientDTO
		Collection<PatientDTO> maListe;
		
		//affichage de la collection pour choisir le client
		try {
			maListe = ctrlUseCase.listerPatients();
			for (PatientDTO unPatientDTO: maListe){
				System.out.println("----------------------------");
				System.out.println("NIR: "+unPatientDTO.getNir());
				System.out.println("Nom: "+unPatientDTO.getNom());
				System.out.println("Prénom: "+unPatientDTO.getPrenom());
				System.out.println("Date de naissance: "+DateUtil.toString(unPatientDTO.getDateNaissance()));
				System.out.println("----------------------------");
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (CabinetTechniqueException e1) {
			e1.printStackTrace();
			logger.fatal("Déclenchement d'une exception technique dans la méthode modifierPatient de GererPatientIHM");
		} catch (HelperException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		// nom
		System.out.println(" \t Saisir le nom du Patient à modifier: ");
		sc = new Scanner(System.in);
		nom = sc.next();

		// prenom
		System.out.println(" \t Saisir le prénom du Patient à modifier: ");
		sc = new Scanner(System.in);
		prenom = sc.next();
		
		// nir
		System.out.println(" \t Saisir le NIR du Patient à modifier: ");
		sc = new Scanner(System.in);
		nir = sc.next();
		
		//champ à modifier
		do{
		System.out.println(" \t Saisir le champ à modifier en respectant la casse: ");
		System.out.println("nir ou nom ou prenom ou s_dateNaissance ou telephone ou portable ou email ou medecinTraitant ou numero ou codePostal ou ville ou pays ou isMale ou voie ou rue ou batiment");
		sc = new Scanner(System.in);
		champAModifier = sc.next();
		}while (!champAModifier.equals("nir") && !champAModifier.equals("nom") && !champAModifier.equals("prenom") && 
				!champAModifier.equals("s_dateNaissance") && !champAModifier.equals("telephone") && !champAModifier.equals("portable") &&
				!champAModifier.equals("email") && !champAModifier.equals("medecinTraitant") && !champAModifier.equals("numero") &&
				!champAModifier.equals("codePostal") && !champAModifier.equals("ville") && !champAModifier.equals("pays") && 
				!champAModifier.equals("isMale") && !champAModifier.equals("voie") && !champAModifier.equals("rue") &&
				!champAModifier.equals("batiment"));
			
		//modification
		System.out.println(" \t Saisir la nouvelle valeur: ");
		sc = new Scanner(System.in);
		modif = sc.next();
		
		try {
			ctrlUseCase.modifierPatient(nom, prenom, nir, champAModifier, modif);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (CabinetTechniqueException e) {
			e.printStackTrace();
			logger.fatal("Déclenchement d'une exception technique dans la méthode modifierPatient de GererPatientIHM");
		} catch (CabinetMedicalException e) {
			e.printStackTrace();
			logger.error("Déclenchement d'une exception métier dans la méthode modifierPatient de GererPatientIHM");
		} catch (HelperException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode modifierPatient de GererPatientIHM");
		}
	}

	/**
	 * Méthode pour supprimer un PatientDTO puis répercuter la modification en mémoire à l'aide du contrôleur
	 */	
	public void supprimerPatient()  {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode supprimerPatient de GererPatientIHM");
		}
		
		String nom;
		String prenom;
		String nir;

		//Déclaration d'une collection de PatientDTO
		Collection<PatientDTO> maListe;
		
		//affichage de la collection pour choisir le client
		try {
			maListe = ctrlUseCase.listerPatients();
			for (PatientDTO unPatientDTO: maListe){
				System.out.println("----------------------------");
				System.out.println("NIR: "+unPatientDTO.getNir());
				System.out.println("Nom: "+unPatientDTO.getNom());
				System.out.println("Prénom: "+unPatientDTO.getPrenom());
				System.out.println("Date de naissance: "+DateUtil.toString(unPatientDTO.getDateNaissance()));
				System.out.println("----------------------------");
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (CabinetTechniqueException e1) {
			e1.printStackTrace();
			logger.fatal("Déclenchement d'une exception technique dans la méthode supprimerPatient de GererPatientIHM");
		} catch (HelperException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		// nom
		System.out.println(" \t Saisir le nom du Patient à supprimer: ");
		sc = new Scanner(System.in);
		nom = sc.next();

		// prenom
		System.out.println(" \t Saisir le prénom du Patient à supprimer: ");
		sc = new Scanner(System.in);
		prenom = sc.next();
		
		// nir
		System.out.println(" \t Saisir le NIR du Patient à supprimer: ");
		sc = new Scanner(System.in);
		nir = sc.next();
		
		try {
			ctrlUseCase.supprimerPatient(nom, prenom, nir);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (CabinetTechniqueException e) {
			e.printStackTrace();
			logger.fatal("Déclenchement d'une exception technique dans la méthode supprimerPatient de GererPatientIHM");
		} catch (HelperException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode supprimerPatient de GererPatientIHM");
		}
	}

	/**
	 * Méthode pour lister tous les PatientDTO stockés en mémoire à l'aide du contrôleur
	 */	
	public void listerPatients() {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode listerPatients de GererPatientIHM");
		}
		//Déclaration d'une collection de PatientDTO
		Collection<PatientDTO> maListe;
		try {
			maListe = ctrlUseCase.listerPatients();
			for (PatientDTO unPatientDTO: maListe){
				System.out.println("----------------------------");
				System.out.println("NIR: "+unPatientDTO.getNir());
				System.out.println("Nom: "+unPatientDTO.getNom());
				System.out.println("Prénom: "+unPatientDTO.getPrenom());
				System.out.println("Date de naissance: "+DateUtil.toString(unPatientDTO.getDateNaissance()));
				System.out.println("----------------------------");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (CabinetTechniqueException e) {
			e.printStackTrace();
			logger.fatal("Déclenchement d'une exception technique dans la méthode listerPatients de GererPatientIHM");
		} catch (HelperException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode listerPatients de GererPatientIHM");
		}
	}
}
