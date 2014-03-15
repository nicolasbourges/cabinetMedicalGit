package com.iut.cabinet.metier;

import org.apache.log4j.Logger;

import com.iut.cabinet.metier.CabinetMedicalException;

/**
 * Classe contenant les r�gles � respecter pour un Patient
 * @author Nicolas BOURGES
 */
public class PatientRegle {
	/**
	 * M�thode permettant de v�rifier la validit� d'un NIR par le calcul de la cl� de contr�le
	 * @param nirATester le NIR � v�rifier
	 * @throws CabinetMedicalException
	 */
	private static Logger logger = Logger.getLogger(PatientRegle.class.getName());
	public static void verifierNir(String nirATester) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode verifierNIR");
		}
		
		try{
			String ctrlCorseA;
			String ctrlCorseB;
			Long cleCtrl;
			Long cle;
			if(nirATester.substring(6,7).equals("A")){
				ctrlCorseA = nirATester.substring(0,6)+'0'+nirATester.substring(7,15);
				cleCtrl = Long.parseLong(ctrlCorseA.substring(0,13));
				cleCtrl = cleCtrl - 1000000;
				cle = Long.parseLong(ctrlCorseA.substring(13,15));
			}
			else{
				if(nirATester.substring(6,7).equals("B")){
					ctrlCorseB = nirATester.substring(0,6)+'0'+nirATester.substring(7,15);
					cleCtrl = Long.parseLong(ctrlCorseB.substring(0,13));
					cleCtrl = cleCtrl - 2000000;
					cle = Long.parseLong(ctrlCorseB.substring(13,15));
				}
				else{
					cleCtrl = Long.parseLong(nirATester.substring(0,13));
					cle = Long.parseLong(nirATester.substring(13,15));
				}
			}
			Long cleCtrlCheck = (cleCtrl % 97);
			cleCtrlCheck = 97-cleCtrlCheck;
			if (!cle.equals(cleCtrlCheck)){
				throw new CabinetMedicalException("Le NIR propos� est incorrect: " + nirATester);
			}
		}
		catch(NumberFormatException eNb){
			throw new CabinetMedicalException("Le NIR propos� est incorrect: " + nirATester);
		}
		catch(StringIndexOutOfBoundsException e){
			throw new CabinetMedicalException("Le NIR propos� est incorrect: " + nirATester);
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode verifierNIR");
		}
	}
	
	/**
	 * M�thode pour v�rifier qu'un Patient a bien saisi une adresse
	 * @param adr l'adresse (null si pas d'adresse saisie)
	 * @throws CabinetMedicalException
	 */
	public static void verifierAdresse(Adresse adr) throws CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode verifierAdresse");
		}
		try{
			if(adr == null){
				throw new CabinetMedicalException("Pas d'adresse!");
			}
		}
		catch(CabinetMedicalException eAdr){
			System.out.println("Un patient doit obligatoirement avoir une adresse!");
			logger.error("D�clenchement d'une exception m�tier dans la m�thode verifierAdresse de PatientRegle");
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode verifierAdresse");
		}
	}
}
