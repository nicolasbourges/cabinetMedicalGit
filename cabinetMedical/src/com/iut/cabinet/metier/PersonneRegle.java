package com.iut.cabinet.metier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Classe contenant les règles à respecter pour une Personne
 * @author Nicolas BOURGES
 */
public class PersonneRegle {
	private static Logger logger = Logger.getLogger(PersonneRegle.class.getName());
	/**
	 * Méthode permettant de vérifier la syntaxe d'un nom
	 * @param nom le nom à vérifier
	 * @throws CabinetMedicalException
	 */
	public static void verifierNom(String nom) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode verifierNom");
		}
		Pattern paNom = Pattern.compile("[A-Z]+");
		Matcher maNom = paNom.matcher(nom);
		try{
		if(!maNom.matches()){
			throw new CabinetMedicalException("Caractère incohérent dans le nom!");
		}
		}
		catch(CabinetMedicalException eNom){
			logger.error("Déclenchement d'une exception métier dans la méthode verifierNom de PersonneRegle");
			throw new CabinetMedicalException("Un nom ne contient que des lettres majuscules!");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode verifierNom");
		}
	}
	
	/**
	 * Méthode permettant de vérifier la syntaxe d'un prénom
	 * @param prenom le prénom à vérifier
	 * @throws CabinetMedicalException
	 */
	public static void verifierPrenom(String prenom) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode verifierPrenom");
		}
		Pattern paPrenom = Pattern.compile("[A-Z][a-z]+");
		Matcher maPrenom = paPrenom.matcher(prenom);
		try{
			if(!maPrenom.matches()){
				throw new CabinetMedicalException("Caractère incohérent dans le prénom!");
			}
		}
		catch(CabinetMedicalException ePrenom){
			logger.error("Déclenchement d'une exception métier dans la méthode verifierPrenom de PersonneRegle");
			throw new CabinetMedicalException("Un prénom ne contient que des lettres minuscules et commence par une majuscule!");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode verifierPrenom");
		}
	}
	
	/**
	 * Méthode permettant de vérifier qu'un numéro de téléphone est valide (fixe comme portable)
	 * @param telnum le numéro à vérifier
	 * @throws CabinetMedicalException
	 */
	public static void verifierTel(String telnum) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode verifierTel");
		}
		Pattern paTelNum = Pattern.compile("[0-9]{10}");
		Matcher maTelNum = paTelNum.matcher(telnum);
		Pattern paTelNumPoints = Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}");
		Matcher maTelNumPoints = paTelNumPoints.matcher(telnum);
		Pattern paTelNumTirets = Pattern.compile("[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}");
		Matcher maTelNumTirets = paTelNumTirets.matcher(telnum);
		try{
			if(!maTelNum.matches() && !maTelNumPoints.matches() && !maTelNumTirets.matches()){
				throw new CabinetMedicalException("Un téléphone est un numéro!");
			}
		}
		catch(CabinetMedicalException eTelNum){
			logger.error("Déclenchement d'une exception métier dans la méthode verifierTel de PersonneRegle");
			throw new CabinetMedicalException("Un téléphone ne contient que des numéros; peuvent être séparés, par paire, par des . ou - !");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode verifierTel");
		}
	}
	
	/**
	 * Méthode permettant de vérifier qu'un email est valide
	 * @param email l'email à vérifier
	 * @throws CabinetMedicalException
	 */
	public static void verifierMail(String email) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode verifierMail");
		}
		Pattern paMail = Pattern.compile("[a-zA-Z\\.]+@[a-z]+\\.[a-z]{2,4}");
		/*Un mail peut contenir des lettres majuscules comme minuscules comme des points
		 * à gauche du @; à droite du @ ce sera des lettres suivies d'un point suivies de l'id
		 * exemples:
		 * coucou@orange.fr		cubitus.le.chat@miaou.com		titi.contact@grosminet.info
		 */
		Matcher maMail = paMail.matcher(email);
		Pattern paMailPlusRare = Pattern.compile("[a-zA-Z\\.]+@[a-z]+\\.[a-z]+\\.[a-z]{2,4}");
		//Un mail peut aussi être de la forme xxx@xxx.xxx.xx --> exemple: toto@etu.unilim.fr
		Matcher maMailPlusRare = paMailPlusRare.matcher(email);
		try{
			if(!maMail.matches() && !maMailPlusRare.matches()){
				throw new CabinetMedicalException("Mail invalide!");
		}
		}
		catch(CabinetMedicalException eMail){
			logger.error("Déclenchement d'une exception métier dans la méthode verifierMail de PersonneRegle");
			throw new CabinetMedicalException("Un mail est de la forme xxx@xxx.xx");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode verifierMail");
		}
	}
}
