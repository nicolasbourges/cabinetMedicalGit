package com.iut.cabinet.metier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Classe contenant les r�gles � respecter pour une Personne
 * @author Nicolas BOURGES
 */
public class PersonneRegle {
	private static Logger logger = Logger.getLogger(PersonneRegle.class.getName());
	/**
	 * M�thode permettant de v�rifier la syntaxe d'un nom
	 * @param nom le nom � v�rifier
	 * @throws CabinetMedicalException
	 */
	public static void verifierNom(String nom) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode verifierNom");
		}
		Pattern paNom = Pattern.compile("[A-Z]+");
		Matcher maNom = paNom.matcher(nom);
		try{
		if(!maNom.matches()){
			throw new CabinetMedicalException("Caract�re incoh�rent dans le nom!");
		}
		}
		catch(CabinetMedicalException eNom){
			logger.error("D�clenchement d'une exception m�tier dans la m�thode verifierNom de PersonneRegle");
			throw new CabinetMedicalException("Un nom ne contient que des lettres majuscules!");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode verifierNom");
		}
	}
	
	/**
	 * M�thode permettant de v�rifier la syntaxe d'un pr�nom
	 * @param prenom le pr�nom � v�rifier
	 * @throws CabinetMedicalException
	 */
	public static void verifierPrenom(String prenom) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode verifierPrenom");
		}
		Pattern paPrenom = Pattern.compile("[A-Z][a-z]+");
		Matcher maPrenom = paPrenom.matcher(prenom);
		try{
			if(!maPrenom.matches()){
				throw new CabinetMedicalException("Caract�re incoh�rent dans le pr�nom!");
			}
		}
		catch(CabinetMedicalException ePrenom){
			logger.error("D�clenchement d'une exception m�tier dans la m�thode verifierPrenom de PersonneRegle");
			throw new CabinetMedicalException("Un pr�nom ne contient que des lettres minuscules et commence par une majuscule!");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode verifierPrenom");
		}
	}
	
	/**
	 * M�thode permettant de v�rifier qu'un num�ro de t�l�phone est valide (fixe comme portable)
	 * @param telnum le num�ro � v�rifier
	 * @throws CabinetMedicalException
	 */
	public static void verifierTel(String telnum) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode verifierTel");
		}
		Pattern paTelNum = Pattern.compile("[0-9]{10}");
		Matcher maTelNum = paTelNum.matcher(telnum);
		Pattern paTelNumPoints = Pattern.compile("[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}");
		Matcher maTelNumPoints = paTelNumPoints.matcher(telnum);
		Pattern paTelNumTirets = Pattern.compile("[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}");
		Matcher maTelNumTirets = paTelNumTirets.matcher(telnum);
		try{
			if(!maTelNum.matches() && !maTelNumPoints.matches() && !maTelNumTirets.matches()){
				throw new CabinetMedicalException("Un t�l�phone est un num�ro!");
			}
		}
		catch(CabinetMedicalException eTelNum){
			logger.error("D�clenchement d'une exception m�tier dans la m�thode verifierTel de PersonneRegle");
			throw new CabinetMedicalException("Un t�l�phone ne contient que des num�ros; peuvent �tre s�par�s, par paire, par des . ou - !");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode verifierTel");
		}
	}
	
	/**
	 * M�thode permettant de v�rifier qu'un email est valide
	 * @param email l'email � v�rifier
	 * @throws CabinetMedicalException
	 */
	public static void verifierMail(String email) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode verifierMail");
		}
		Pattern paMail = Pattern.compile("[a-zA-Z\\.]+@[a-z]+\\.[a-z]{2,4}");
		/*Un mail peut contenir des lettres majuscules comme minuscules comme des points
		 * � gauche du @; � droite du @ ce sera des lettres suivies d'un point suivies de l'id
		 * exemples:
		 * coucou@orange.fr		cubitus.le.chat@miaou.com		titi.contact@grosminet.info
		 */
		Matcher maMail = paMail.matcher(email);
		Pattern paMailPlusRare = Pattern.compile("[a-zA-Z\\.]+@[a-z]+\\.[a-z]+\\.[a-z]{2,4}");
		//Un mail peut aussi �tre de la forme xxx@xxx.xxx.xx --> exemple: toto@etu.unilim.fr
		Matcher maMailPlusRare = paMailPlusRare.matcher(email);
		try{
			if(!maMail.matches() && !maMailPlusRare.matches()){
				throw new CabinetMedicalException("Mail invalide!");
		}
		}
		catch(CabinetMedicalException eMail){
			logger.error("D�clenchement d'une exception m�tier dans la m�thode verifierMail de PersonneRegle");
			throw new CabinetMedicalException("Un mail est de la forme xxx@xxx.xx");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode verifierMail");
		}
	}
}
