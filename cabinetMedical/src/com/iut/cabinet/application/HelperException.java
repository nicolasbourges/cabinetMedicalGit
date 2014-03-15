package com.iut.cabinet.application;

public class HelperException extends Exception{
	

/**
 * Classe utilisée pour les exceptions du Cabinet Médical
 * @author Nicolas BOURGES
 */
	private static final long serialVersionUID = 1L;
		/** Constructeur par défaut */
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


