package com.iut.cabinet.user;

import java.io.Serializable;
import java.sql.Date;
import com.iut.cabinet.metier.CabinetMedicalException;

/** Un <code> PatientDTO </code> est la classe DTO 
 * correspondant aux copies des objets métiers de type Patient
 * est une <code>Personne</code>.
 *  Un <code> PatientDTO </code> est une <code> PersonneDTO </code>caractÃ©risÃ©e par :
 * <ul>
 * <li>un <code>nir</code> (numÃ©ro de securite sociale) </li>
 * <li>un <code>medecinTraitant</code> (le medecin traitant du patient)</li>
 * </ul>
 * @author Nicolas BOURGES
 * @version 1.1
 */
public class PatientDTO extends PersonneDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nir;
	
	private String medecinTraitant;
	
	
	/**
	 * Constructeur de PatientDTO par défaut
	 */
	public PatientDTO(){}
	
	/**
	 * Constructeur de PatientDTO avec un médecin traitant et un nir
	 * @param medecinTraitant
	 * @param nir
	 */
	public PatientDTO(String medecinTraitant, String nir) {
		this.medecinTraitant = medecinTraitant;
		this.nir = nir;
	}
	
	/**
     * Construit un nouveau <code> Patient </code>
     * en passant une valeur spécifique pour tous les attributs
     * 
     * @param idPersonne		l'identifiant
     * @param nom   	 		le nom
   	 * @param prenom 			le prenom 
   	 * @param dateNaissance  	la date de naissance
   	 * @param isMale 			si la personne est de sexe masculin	 
   	 * @param telephone	 		le numero de telephone
   	 * @param portable		 	le numero de portable
     * @param email	 			l'adresse email
     * @param adresse		 	l'adresse physique
     * @param unAscendant		l'ascendant de la personne
     * @param nir				le nir
     * @param medecinTraitant	le medecin traitant
     * @throws CabinetMedicalException
     */
	public PatientDTO(Integer idPersonne, String nom, String prenom,
			Date dateNaissance, boolean isMale, String telephone,
			String portable, String email, AdresseDTO adresse,
			PersonneDTO unAscendant, String nir, String medecinTraitant) {
			
		super(idPersonne, nom, prenom, dateNaissance, isMale, telephone,
				portable, email, adresse, unAscendant);
		setNir(nir);
		setMedecinTraitant(medecinTraitant);
	}

	/**
	 * @return the nir
	 */
	public String getNir() {
		return nir;
	}

	/**
	 * @param nir the nir to set
	 */
	public void setNir(String nir) {
		this.nir = nir;
	}

	/**
	 * @return the medecinTraitant
	 */
	public String getMedecinTraitant() {
		return medecinTraitant;
	}

	/**
	 * @param medecinTraitant the medecinTraitant to set
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
		PatientDTO other = (PatientDTO) obj;
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
	
	@Override
	 public String toString()  {
	     StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
		stringBuilder.append("\n");
		stringBuilder.append("Numéro sécu: ");
		stringBuilder.append(nir);
		stringBuilder.append("\n");
		stringBuilder.append("Medecin Traitant: ");
		stringBuilder.append(medecinTraitant);
		return stringBuilder.toString();
	     
}
}

