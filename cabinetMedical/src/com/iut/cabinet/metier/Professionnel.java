package com.iut.cabinet.metier;

import java.sql.Date;

import org.apache.log4j.Logger;

/**
 * Classe étandant Personne caractérisant les professionnels du cabinet 
 * @author Nicolas BOURGES
 */
@SuppressWarnings("serial")
public class Professionnel extends Personne{
	private static Logger logger = Logger.getLogger(Professionnel.class.getName());
	private String immatriculation;
	private String specialite;
	/**
	 * Retourne le matricule du professionnel
	 * @return l' immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}
	/**
	 * Permet de modifier le matricule d'un professionnel
	 * @param immatriculation la nouvelle immatriculation
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	/**
	 * Retourne la spécialité du professionnel
	 * @return la specialite
	 */
	public String getSpecialite() {
		return specialite;
	}
	/**
	 * Permet de modifier la spécialité du professionnel
	 * @param specialite la nouvelle specialite
	 */
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	/**
	 * Constructeur de profesionnel avec tous les arguments
	 * @param idPersonne l'identifiant du professionnel
	 * @param nom le nom du professionnel
	 * @param prenom le prénom du professionnel
	 * @param dateNaissance la date de naissance du professionnel
	 * @param isMale si le professionnel est un homme, vrai; sinon c'est une femme
	 * @param telephone le numéro de fixe du professionnel
	 * @param portable le numéro de portable du professionnel
	 * @param email l'email du professionnel
	 * @param adresse l'adresse du professionnel
	 * @param unAscendant l'ascendant éventuel du professionnel (pour les mineurs)
	 * @param immatriculation l'immatriculation du professionnel
	 * @param specialite la spécialité du professionnel
	 * @throws CabinetMedicalException 
	 */
	public Professionnel(int idPersonne, String nom, String prenom,
			Date dateNaissance, boolean isMale, String telephone,
			String portable, String email, Adresse adresse,
			Personne unAscendant, String immatriculation, String specialite) throws CabinetMedicalException {
		super(idPersonne, nom, prenom, dateNaissance, isMale, telephone,
				portable, email, adresse, unAscendant);
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans le constructeur de Professionnel avec tous les paramètres");
		}
		setImmatriculation(immatriculation);
		setSpecialite(specialite);
		if(logger.isDebugEnabled()){
			logger.debug("Sortie du constructeur de Professionnel avec tous les paramètres");
		}
	}
	
	/**
	 * Le constructeur par défaut de professionnel
	 */
	public Professionnel(){}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((immatriculation == null) ? 0 : immatriculation.hashCode());
		result = prime * result
				+ ((specialite == null) ? 0 : specialite.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professionnel other = (Professionnel) obj;
		if (immatriculation == null) {
			if (other.immatriculation != null)
				return false;
		} else if (!immatriculation.equals(other.immatriculation))
			return false;
		if (specialite == null) {
			if (other.specialite != null)
				return false;
		} else if (!specialite.equals(other.specialite))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
		stringBuilder.append("\n immatriculation = ");
		stringBuilder.append(this.getImmatriculation());
		stringBuilder.append("\n specialite = ");
		stringBuilder.append(this.getSpecialite());
		return stringBuilder.toString();
	}
	
}
