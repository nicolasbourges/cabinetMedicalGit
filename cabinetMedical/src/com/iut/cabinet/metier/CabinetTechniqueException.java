package com.iut.cabinet.metier;
/**
 * Classe utilis�e pour les exceptions techniques du Cabinet M�dical
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
