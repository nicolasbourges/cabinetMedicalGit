package com.iut.cabinet.metier;

/**
 * Classe utilisée pour les exceptions du Cabinet Médical
 * @author Nicolas BOURGES
 */
@SuppressWarnings("serial")
public class CabinetMedicalException extends Exception {
	/** Constructeur par défaut */
	public CabinetMedicalException(){super();}
	/** Constructeur avec le message d'erreur
	 * @param messAff le message d'erreur
	 */
	public CabinetMedicalException(String messAff){
		super(messAff);
	}
}
