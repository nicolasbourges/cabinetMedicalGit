package com.iut.cabinet.metier;

/**
 * Classe utilis�e pour les exceptions du Cabinet M�dical
 * @author Nicolas BOURGES
 */
@SuppressWarnings("serial")
public class CabinetMedicalException extends Exception {
	/** Constructeur par d�faut */
	public CabinetMedicalException(){super();}
	/** Constructeur avec le message d'erreur
	 * @param messAff le message d'erreur
	 */
	public CabinetMedicalException(String messAff){
		super(messAff);
	}
}
