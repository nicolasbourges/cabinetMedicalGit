package com.iut.cabinet.user;

import java.io.Serializable;
import java.sql.Date;
import com.iut.cabinet.util.DateUtil;


/** La classe <code>PersonneDTO</code> représente 
 *  un objet DTO de type Personne caractérisé par
 *  <ul>
 *  <li>son identifiant <code>idPersonne</code></li>
 *  <li>son <code>nom</code>
 *  <li>son <code>prenom</code></li>
 *  <li>sa <code>dateNaissance</code></li>
 *  <li>son sexe <code>isMale</code></li>
 *  <li>son numero de <code>telephone</code></li>
 *  <li>son numero de <code>portable</code></li>
 *  <li>son adresse <code>email</code></li>
 *  <li>son <code>adresse</code></li>
 *  <li>son ascendant (<code>unAscendant</code>)</li>
 *  </ul>
 * @author Nicolas BOURGES
 * @version 1.1
 */
public abstract class PersonneDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	protected Integer idPersonne;
	protected String nom;
	protected String prenom;
	protected Date dateNaissance;
	protected boolean isMale;
	protected String telephone;
	protected String portable;
	protected String email;
	protected AdresseDTO adresse;
	protected PersonneDTO unAscendant;

	
	/** Constructeur par défaut de PersonneDTO
	 * 
	 */
	public PersonneDTO(){}
	
	/** Constructeur de PersonneDTO avec tous les paramètres
	 * @param adresse
	 * @param dateNaissance
	 * @param email
	 * @param idPersonne
	 * @param isMale
	 * @param nom
	 * @param portable
	 * @param prenom
	 * @param telephone
	 * @param unAscendant
	 */
	public PersonneDTO(Integer idPersonne, String nom, String prenom,
				Date dateNaissance, boolean isMale, String telephone,
				String portable, String email, AdresseDTO adresse, PersonneDTO unAscendant)  {
			
		
		super();
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.idPersonne = idPersonne;
		this.isMale = isMale;
		this.nom = nom;
		this.portable = portable;
		this.prenom = prenom;
		this.telephone = telephone;
		this.unAscendant = unAscendant;
	}
	
	/** Methode retournant l'id de la personne
	 * @return the idPersonne
	 */
	public Integer getIdPersonne() {
		return idPersonne;
	}
	
	/** Methode modifiant l'id de la personne
	 * @param idPersonne the idPersonne to set
	 */
	public void setIdPersonne(Integer idPersonne) {
		this.idPersonne = idPersonne;
	}

	/** Methode retournant le nom de la personne
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Methode modifiant le nom de la personne
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Methode retournant le prenom de la personne
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/** Methode modifiant le prenom de la personne
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/** Methode retournant la date de naissance de la personne
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}

	/** Methode modifiant la date de naissance de la personne
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/** Methode retournant le sexe de la personne sous forme de booléen (true = homme, false = femme)
	 * @return isMale
	 */
	public boolean isMale() {
		return isMale;
	}

	/** Methode modifiant le sexe de la personne
	 * @param isMale the isMale boolean to set
	 */
	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	/** Methode retournant le téléphone de la personne
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/** Methode modifiant le téléphone de la personne
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** Methode retournant le téléphone portable de la personne
	 * @return the portable
	 */
	public String getPortable() {
		return portable;
	}

	/** Methode modifiant le téléphone portable de la personne
	 * @param portable the portable to set
	 */
	public void setPortable(String portable) {
		this.portable = portable;
	}

	/** Methode retournant l'email de la personne
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/** Methode modifiant l'email de la personne
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Methode retournant l'AdresseDTO de la personne
	 * @return the adresse
	 */
	public AdresseDTO getAdresse() {
		return adresse;
	}

	/** Methode modifiant l'AdresseDTO de la personne
	 * @param adresse the adresse to set
	 */
	public void setAdresse(AdresseDTO adresse) {
		this.adresse = adresse;
	}

	/** Methode retournant l'ascendant de la personne
	 * @return unAscendant
	 */
	public PersonneDTO getUnAscendant() {
		return unAscendant;
	}

	/** Methode modifiant l'ascendant de la personne
	 * @param unAscendant the unAscendant to set
	 */
	public void setUnAscendant(PersonneDTO unAscendant) {
		this.unAscendant = unAscendant;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result
				+ ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((idPersonne == null) ? 0 : idPersonne.hashCode());
		result = prime * result + (isMale ? 1231 : 1237);
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result
				+ ((portable == null) ? 0 : portable.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result
				+ ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result
				+ ((unAscendant == null) ? 0 : unAscendant.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonneDTO other = (PersonneDTO) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idPersonne == null) {
			if (other.idPersonne != null)
				return false;
		} else if (!idPersonne.equals(other.idPersonne))
			return false;
		if (isMale != other.isMale)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (portable == null) {
			if (other.portable != null)
				return false;
		} else if (!portable.equals(other.portable))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (unAscendant == null) {
			if (other.unAscendant != null)
				return false;
		} else if (!unAscendant.equals(other.unAscendant))
			return false;
		return true;
	}
	
	@Override
	public String toString()  {
	    String s= "";
	    StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Numéro: ");
		stringBuilder.append(idPersonne);
		stringBuilder.append("\n");
		stringBuilder.append("Nom : ");
		stringBuilder.append(nom);
		stringBuilder.append("\n");
		stringBuilder.append("Prenom : ");
		stringBuilder.append(prenom);
		stringBuilder.append("\n");
		stringBuilder.append("DateNaissance : ");
		stringBuilder.append(DateUtil.toString(dateNaissance));
		stringBuilder.append("\n");
		stringBuilder.append("isMale : ");
		stringBuilder.append(isMale);
		stringBuilder.append("\n");
		stringBuilder.append("Telephone :  ");
		stringBuilder.append(telephone);
		stringBuilder.append("\n");
		stringBuilder.append("Portable : ");
		stringBuilder.append(portable);
		stringBuilder.append("\n");
		stringBuilder.append("Email : ");
		stringBuilder.append(email);
		stringBuilder.append("\n");
		stringBuilder.append("Adresse : ");
		stringBuilder.append(adresse);
		stringBuilder.append("\n");
		stringBuilder.append("Ascendant : ");
		stringBuilder.append(unAscendant);
		s=stringBuilder.toString() ;
	    return s;
	  }

}
