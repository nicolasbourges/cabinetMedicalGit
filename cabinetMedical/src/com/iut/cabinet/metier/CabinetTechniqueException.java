package com.iut.cabinet.metier;
/**
 * Classe utilisée pour les exceptions techniques du Cabinet Médical
 * @author Nicolas BOURGES
 */
public class CabinetTechniqueException extends Exception{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	/** Constructeur avec le message d'erreur
	 * @param message le message d'erreur
	 */
	public CabinetTechniqueException(String message) {
		this.message=message;
	}

	public String getMessage(){
		return message;
	}
	
	
	
}
