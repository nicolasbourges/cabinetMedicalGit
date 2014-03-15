package com.iut.cabinet.essai;

import org.apache.log4j.Logger;

import com.iut.cabinet.metier.Personne;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.metier.Professionnel;

/**
 * Classe pour stocker des personnes caractérisée par:
 *  - le tableau correspondant à la liste
 *  - le nombre de personnes dans le tableau 
 * @author Nicolas BOURGES
 */

public class ListePersonneCabinetTab {
	private static Logger logger = Logger.getLogger(ListePersonneCabinetTab.class.getName());
	//attributs
	private Personne tabPers[] = new Personne[5];	/** le tableau de personnes pour la liste */
	private int nb = 0; /** le nombre de personnes */
	
	/**
	 * retourne le nombre de personnes dans la liste
	 * @return nb le nombre de personnes dans la liste
	 */
	public int getNb() {
		return nb;
	}

	/**
	 * modifie le nombre de personnes dans la liste, en prenant en paramètre le nouveau nombre de personnes
	 * @param nb le nouveau nombre de personnes
	 */
	public void setNb(int nb) {
		this.nb = nb;
	}

	/**
	 * Constructeur d'une liste de personnes à deux paramètres
	 */
	public ListePersonneCabinetTab(Personne tabPers[], int nb) {
		this.tabPers = tabPers;
		this.nb = nb;
	}
	
	/**
	 * constructeur par défaut d'une liste de personnes
	 */
	public ListePersonneCabinetTab(){}

	/**
	 * méthode permettant d'ajouter une personne dans le tableau
	 * @param maPers la personne à ajouter
	 * @param nb le nombre de personnes dans le tableau, à incrémenter
	 */
	public void ajouterPersonne (Personne maPers, int nb){
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode ajouterPersonne de ListePersonneCabinetTab");
		}
		if (nb<this.tabPers.length){
		this.tabPers[this.nb]=maPers;
		this.nb = this.nb+1;
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode ajouterPersonne de ListePersonneCabinetTab");
		}
	}
	
	/**
	 * méthode permettant d'afficher toutes les personnes de la liste
	 */
	public void afficheListe(){
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode afficheListe de ListePersonneCabinetTab");
		}
		int i = 0;
		for(i=0; i< this.tabPers.length; i++){
			System.out.println(this.tabPers[i]);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode afficheListe de ListePersonneCabinetTab");
		}
	}
	
	/**
	 * méthode permettant d'afficher tous les patients de la liste
	 */
	public void afficherPatient(){
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode afficherPatient de ListePersonneCabinetTab");
		}
		int i = 0;
		for(i=0; i< this.tabPers.length; i++){
			if (this.tabPers[i] instanceof Patient){
				System.out.println(this.tabPers[i]);
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode afficherPatient de ListePersonneCabinetTab");
		}
	}
	
	/**
	 * méthode permettant d'afficher tous les professionnels de la liste
	 */
	public void afficheProfessionnel(){
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode afficheProfessionnel de ListePersonneCabinetTab");
		}
		int i = 0;
		for(i=0; i< this.tabPers.length; i++){
			if (this.tabPers[i] instanceof Professionnel){
				System.out.println(this.tabPers[i]);
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode afficheProfessionnel de ListePersonneCabinetTab");
		}
	}
}
