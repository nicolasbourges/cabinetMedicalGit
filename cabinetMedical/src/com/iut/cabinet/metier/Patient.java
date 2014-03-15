package com.iut.cabinet.metier;

import java.sql.Date;

import org.apache.log4j.Logger;

import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.Personne;

/**
 * Classe étandant Personne caractérisant les patients du cabinet 
 * @author Nicolas BOURGES
 */
@SuppressWarnings("serial")
public class Patient extends Personne {
	private static Logger logger = Logger.getLogger(Patient.class.getName());
	private String nir; /** Le NIR de la personne */
	private String medecinTraitant; /** Le médecin traitant de la personne */
	
	/** Le constructeur par défaut de patient */
	public Patient (){}
	
	/**
	 * Le constructeur de patient avec tous les attributs
	 * @param idPersonne l'identifiant du patient
	 * @param nom le nom du patient
	 * @param prenom le prénom du patient
	 * @param dateNaissance la date de naissance du patient
	 * @param isMale si le patient est un homme, vrai; sinon c'est une femme
	 * @param telephone le numéro de fixe du patient
	 * @param portable le numéro de portable du patient
	 * @param email l'email du patient
	 * @param adresse l'adresse du patient
	 * @param unAscendant l'ascendant éventuel du patient (pour les mineurs)
	 * @param nir le NIR du patient
	 * @param medecinTraitant le médecin traitant du patient
	 * @throws CabinetMedicalException 
	 */
	public Patient(int idPersonne, String nom, String prenom,
			Date dateNaissance, boolean isMale, String telephone,
			String portable, String email, Adresse adresse,
			Personne unAscendant, String nir, String medecinTraitant) throws CabinetMedicalException {
			super(idPersonne, nom, prenom, dateNaissance, isMale, telephone,
				portable, email, adresse, unAscendant);
			if(logger.isDebugEnabled()){
				logger.debug("Entrée dans la constructeur de Patient avec tous les paramètres");
			}
			setNir(nir);
			setMedecinTraitant(medecinTraitant);
			if(logger.isDebugEnabled()){
				logger.debug("Sortie du constructeur de Patient avec tous les paramètres");
			}
	}
	
	/**
	 * Constructeur de patient à six paramètres
	 * @param nom le nom du patient
	 * @param prenom le prénom du patient
	 * @param dateNaissance la date de naissance du patient
	 * @param isMale si le patient est un homme, vrai; sinon c'est une femme
	 * @param adresse l'adresse du patient
	 * @param nir le NIR du patient
	 */
	public Patient(String nom, String prenom, Date dateNaissance,
			boolean isMale, Adresse adresse, String nir) {
		super(nom, prenom, dateNaissance, isMale, adresse);
		this.nir = nir;
	}
	
	/**
	 * Constructeur de patient à sept paramètres
	 * @param nom le nom du patient
	 * @param prenom le prénom du patient
	 * @param dateNaissance la date de naissance du patient
	 * @param isMale si le patient est un homme, vrai; sinon c'est une femme
	 * @param adresse l'adresse du patient
	 * @param unAscendant l'ascendant éventuel du patient (pour les mineurs)
	 * @param nir le NIR du patient
	 */
	public Patient(String nom, String prenom, Date dateNaissance,
			boolean isMale, Adresse adresse, Personne unAscendant, String nir) {
		super(nom, prenom, dateNaissance, isMale, adresse, unAscendant);
		this.nir = nir;
	}
	/**
	 * Retourne le NIR du patient
	 * @return le nir
	 */
	public String getNir() {
		return nir;
	}
	/**
	 * Modifie le NIR du patient
	 * @param nir le nouveau NIR
	 * @throws CabinetMedicalException 
	 */
	public void setNir(String nir) throws CabinetMedicalException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans le setteur de nir de Patient");
		}
		PatientRegle.verifierNir(nir);
		this.nir = nir;
		if(logger.isDebugEnabled()){
			logger.debug("Sortie du setteur de nir de Patient");
		}
	}
	/**
	 * Retourne le nom du médecin traitant du patient
	 * @return le medecinTraitant
	 */
	public String getMedecinTraitant() {
		return medecinTraitant;
	}
	/**
	 * Modifie le nom du médecin traitant du patient
	 * @param medecinTraitant le nouveau médecin traitant
	 */
	public void setMedecinTraitant(String medecinTraitant) {
		this.medecinTraitant = medecinTraitant;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((medecinTraitant == null) ? 0 : medecinTraitant.hashCode());
		result = prime * result + ((nir == null) ? 0 : nir.hashCode());
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
		Patient other = (Patient) obj;
		if (medecinTraitant == null) {
			if (other.medecinTraitant != null)
				return false;
		} else if (!medecinTraitant.equals(other.medecinTraitant))
			return false;
		if (nir == null) {
			if (other.nir != null)
				return false;
		} else if (!nir.equals(other.nir))
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
		stringBuilder.append("\n nir = ");
		stringBuilder.append(this.getNir());
		stringBuilder.append("\n medecinTraitant = ");
		stringBuilder.append(this.getMedecinTraitant());
		return stringBuilder.toString();
	}

}
