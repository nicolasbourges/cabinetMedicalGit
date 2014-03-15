package com.iut.cabinet.metier;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.iut.cabinet.util.DateUtil;

/**
 * Classe abstraite permettant de donner des attributs de base communs à toutes les personnes
 * @author Nicolas BOURGES
 */
public abstract class Personne implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Personne.class.getName());
	protected int idPersonne; /** l'identifiant unique de la personne */
	protected String nom, prenom, telephone, portable, email; /** le nom, prénom, téléphone fixe, portable et l'email de la personne */
	protected java.sql.Date dateNaissance; /** la date de naissance de la personne */
	protected boolean isMale; /** si le patient est un homme, vrai; sinon c'est une femme */
	protected Adresse adresse; /** l'adresse du patient */
	protected Personne unAscendant; /** l'ascendant éventuel du patient (pour les mineurs) */
	
	/**
	 * La méthode d'affichage simplifié d'une Personne
	 * @param unePersonne la personne à afficher
	 * @return un String contenant l'affichage simplifié de la personne
	 */
	public String affichageSimplifie(Personne unePersonne){
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode affichageSimplifie de Personne");
		}
		String nom = new String("Nom: " + unePersonne.getNom() + "\n");
		String pnom = new String("Prénom: " + unePersonne.getPrenom() + "\n");
		String dn = new String("DateNaisssance: " + DateUtil.toDate(unePersonne.getDateNaissance().toString()) + "\n");
		String cp = new String("Code postal: " + unePersonne.getAdresse().getCodePostal() + "\n -----------------------------");
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode affichageSimplifie de Personne");
		}
		return nom + pnom + dn + cp;
	}
	
	/**
	 * Retourne l'identifiant de la Personne
	 * @return l'idPersonne
	 */
	public int getIdPersonne() {
		return idPersonne;
	}
	/**
	 * Permet de modifier l'identifiant de la Personne
	 * @param idPersonne le nouvel idPersonne
	 */
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	/**
	 * Retourne le nom de la personne
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Permet de modifier le nom de la personne
	 * @param nom le nouveau nom
	 * @throws CabinetMedicalException 
	 */
	public void setNom(String nom) throws CabinetMedicalException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode setNom de Personne");
		}
		PersonneRegle.verifierNom(nom);
		this.nom = nom;
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode setNom de Personne");
		}
	}
	/**
	 * Retourne le prénom de la personne
	 * @return le prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Permet de modifier le prénom de la personne
	 * @param prenom le nouveau prenom
	 * @throws CabinetMedicalException 
	 */
	public void setPrenom(String prenom) throws CabinetMedicalException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode setPrenom de Personne");
		}
		PersonneRegle.verifierPrenom(prenom);
		this.prenom = prenom;
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode setPrenom de Personne");
		}
	}
	/**
	 * Retourne le téléphone fixe de la personne
	 * @return le telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * Permet de modifier le téléphone fixe de la personne
	 * @param telephone le nouveau numéro de telephone
	 * @throws CabinetMedicalException 
	 */
	public void setTelephone(String telephone) throws CabinetMedicalException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode setTelephone de Personne");
		}
		PersonneRegle.verifierTel(telephone);
		this.telephone = telephone;
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode setTelephone de Personne");
		}
	}
	/**
	 * Retourne le téléphone portable de la personne
	 * @return le portable
	 */
	public String getPortable() {
		return portable;
	}
	/**
	 * Permet de modifier le téléphone portable de la personne
	 * @param portable le nouveau numéro de portable
	 * @throws CabinetMedicalException 
	 */
	public void setPortable(String portable) throws CabinetMedicalException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode setPortable de Personne");
		}
		PersonneRegle.verifierTel(portable);
		this.portable = portable;
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode setPortable de Personne");
		}
	}
	/**
	 * Retourne l'email
	 * @return l' email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Permet de modifier l'email de la personne
	 * @param email le nouvel email
	 * @throws CabinetMedicalException 
	 */
	public void setEmail(String email) throws CabinetMedicalException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode setEmail de Personne");
		}
		PersonneRegle.verifierMail(email);
		this.email = email;
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode setEmail de Personne");
		}
	}
	/**
	 * Retourne la date de naissance de la personne
	 * @return the dateNaissance
	 */
	public java.sql.Date getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * Permet de modifier la date de naissance de la personne
	 * @param dateNaissance la nouvelle dateNaissance
	 */
	public void setDateNaissance(java.sql.Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	/**
	 * Retourne true (vrai) si homme, false (faux) si femme
	 * @return isMale
	 */
	public boolean isMale() {
		return isMale;
	}
	/**
	 * Permet de modifier le sexe d'une personne
	 * @param isMale le nouveau sexe de la personne sous forme booléenne (true si homme, false si femme)
	 */
	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}
	/**
	 * Retourne l'adresse de la personne
	 * @return l' adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}
	/**
	 * Permet de modifier l'adresse de la personne
	 * @param adresse la nouvelle adresse
	 * @throws CabinetMedicalException 
	 */
	public void setAdresse(Adresse adresse) throws CabinetMedicalException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode setAdresse de Personne");
		}
		if(this instanceof Patient){
			PatientRegle.verifierAdresse(adresse);
		}
		this.adresse = adresse;
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode setAdresse de Personne");
		}
	}
	/**
	 * Retourne l'ascendant de la personne
	 * @return unAscendant
	 */
	public Personne getUnAscendant() {
		return unAscendant;
	}
	/**
	 * Permet de modifier l'ascendant
	 * @param unAscendant le nouvel ascendant
	 */
	public void setUnAscendant(Personne unAscendant) {
		this.unAscendant = unAscendant;
	}
	
	/**
	 * Constructeur de personne avec tous les paramètres
	 * @param idPersonne l'identifiant de la personne
	 * @param nom le nom de la personne
	 * @param prenom le prénom de la personne
	 * @param dateNaissance la date de naissance de la personne
	 * @param isMale si la personne est un homme, vrai; sinon c'est une femme
	 * @param telephone le numéro de fixe de la personne
	 * @param portable le numéro de portable de la personne
	 * @param email l'email de la personne
	 * @param adresse l'adresse de la personne
	 * @param unAscendant l'ascendant éventuel de la personne (pour les mineurs)
	 * @throws CabinetMedicalException 
	 */
	public Personne(int idPersonne, String nom, String prenom,
			Date dateNaissance, boolean isMale, String telephone,
			String portable, String email, Adresse adresse, Personne unAscendant) throws CabinetMedicalException {
		super();
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans le constructeur de Personne avec tous les paramètres");
		}
		//this.idPersonne = idPersonne;
		setIdPersonne(idPersonne);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setMale(isMale);
		setTelephone(telephone);
		setPortable(portable);
		setEmail(email);
		setAdresse(adresse);
		setUnAscendant(unAscendant);
		if(logger.isDebugEnabled()){
			logger.debug("Sortie du constructeur de Personne avec tous les paramètres");
		}
	}
	
	/**
	 * Constructeur de personne par défaut
	 */
	public Personne() {}
	
	/**
	 * Constructeur de personne à cinq paramètres
	 * @param nom le nom de la personne
	 * @param prenom le prénom de la personne
	 * @param dateNaissance la date de naissance de la personne
	 * @param isMale si la personne est un homme, vrai; sinon c'est une femme
	 * @param adresse l'adresse de la personne
	 */
	public Personne(String nom, String prenom, Date dateNaissance,
			boolean isMale, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.isMale = isMale;
		this.adresse = adresse;
	}
	
	/**
	 * Constructeur de personne à six paramètres
	 * @param nom le nom de la personne
	 * @param prenom le prénom de la personne
	 * @param dateNaissance la date de naissance de la personne
	 * @param isMale si la personne est un homme, vrai; sinon c'est une femme
	 * @param adresse l'adresse de la personne
	 * @param unAscendant l'ascendant éventuel de la personne (pour les mineurs)
	 */
	public Personne(String nom, String prenom, Date dateNaissance,
			boolean isMale, Adresse adresse, Personne unAscendant) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.isMale = isMale;
		this.adresse = adresse;
		this.unAscendant = unAscendant;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idPersonne;
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
		Personne other = (Personne) obj;
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
		if (idPersonne != other.idPersonne)
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
	
	public String toString(){StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Numéro: ");
		stringBuilder.append(this.getIdPersonne());
		stringBuilder.append("\n Nom: ");
		stringBuilder.append(this.getNom());
		stringBuilder.append("\n Prénom: ");
		stringBuilder.append(this.getPrenom());
		stringBuilder.append("\n Date de naissance: ");
		stringBuilder.append(DateUtil.toString(this.getDateNaissance()));
		stringBuilder.append("\n Age: ");
		stringBuilder.append(this.getAge());
		stringBuilder.append(" ans");
		stringBuilder.append("\n isMale: ");
		stringBuilder.append(this.isMale());
		stringBuilder.append("\n Téléphone: ");
		stringBuilder.append(this.getTelephone());
		stringBuilder.append("\n Portable: ");
		stringBuilder.append(this.getPortable());
		stringBuilder.append("\n Email: ");
		stringBuilder.append(this.getEmail());
		stringBuilder.append("\n Adresse: ");
		stringBuilder.append(this.getAdresse());
		stringBuilder.append("\n Ascendant: ");
		stringBuilder.append(this.unAscendant);
	return stringBuilder.toString();}
	
	public int getAge(){
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode getAge de Personne");
		}
		
		Calendar cal = Calendar.getInstance();
		int actualYear = cal.get(Calendar.YEAR);	//récupère l'année
		int actualMonth = cal.get(Calendar.MONTH); // récupère le mois
		int actualDay = cal.get(Calendar.DAY_OF_MONTH); //récupère le jour 
		
		Calendar calendrierNaiss = Calendar.getInstance();
		calendrierNaiss.setTime(this.dateNaissance);
		
		int anNaiss = calendrierNaiss.get(Calendar.YEAR);
		int moisNaiss = calendrierNaiss.get(Calendar.MONTH);
		int dateNaiss = calendrierNaiss.get(Calendar.DAY_OF_MONTH);
		if (actualMonth<moisNaiss){
			if(logger.isDebugEnabled()){
				logger.debug("Sortie de la méthode getAge de Personne");
			}
			return actualYear - anNaiss - 1;
		}
		else{
			if(actualMonth==moisNaiss){
				if(actualDay<dateNaiss){
					if(logger.isDebugEnabled()){
						logger.debug("Sortie de la méthode getAge de Personne");
					}
					return actualYear - anNaiss - 1;
				}
				else {
					if(logger.isDebugEnabled()){
						logger.debug("Sortie de la méthode getAge de Personne");
					}
					return actualYear - anNaiss;
				}
			}
			else {
				if(logger.isDebugEnabled()){
					logger.debug("Sortie de la méthode getAge de Personne");
				}
				return actualYear - anNaiss;
			}
		}
	}
}
