package com.iut.cabinet.application;

public class HelperException extends Exception{
	

/**
 * Classe utilis�e pour les exceptions du Cabinet M�dical
 * @author Nicolas BOURGES
 */
	private static final long serialVersionUID = 1L;
		/** Constructeur par d�faut */
		public HelperException(){
			super();
		}
		
		/** Constructeur avec le message d'erreur
		 * @param msg le message d'erreur
		 */
		public HelperException(String msg){
			super(msg);
		}

	}


