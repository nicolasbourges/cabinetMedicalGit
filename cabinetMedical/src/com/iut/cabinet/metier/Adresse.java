/**
 * 
 */
package com.iut.cabinet.metier;

import java.io.Serializable;

/**
 * Une adresse repr�sente une adresse postale. Elle est caract�ris�e par
 * <ul>
 * <li>un num�ro</li>
 * <li>un libell� de rue</li>
 * <li>un libell� de voie</li>
 * <li>un compl�ment d'identification du point g�ographique</li>
 * <li>un code postal</li>
 * <li>une localit� de destination(ville)</li>
 * <li>un pays</li>
 * </ul>
 * @author Nicolas BOURGES
 * @version 1.1
 */
@SuppressWarnings("serial")
public class Adresse implements Serializable {
	/////////////////////////////////////
	//
	//			Attributs
	//
	/////////////////////////////////////
	
	/** Le compl�ment d'identification du point g�ographique */
	private String batiment;
	/** Le code postal */
	private String codePostal;
	/** Le num�ro */
	private String numero;
	/** Le pays */
	private String pays;
	/** Le libell� de la rue */
	private String rue;
	/** La localit� de destination */
	private String ville;
	/** Le libell� de la voie */
	private String voie;
	/** L'identifiant technique de la table Adresse */
	private Integer idAdresse;
	
	/////////////////////////////////////
	//
	//			Constructeurs
	//
	/////////////////////////////////////


	/**
	 * Construit une nouvelle Adresse en passant des valeurs par d�faut pour tous les attributs
	 */
	public Adresse() {}

	
	/**
	 * Construit une nouvelle Adresse en passant une valeur sp�cifique pour tous les attributs
	 * @param batiment
	 * @param numero
	 * @param voie
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param pays
	 */
	public Adresse(String batiment, String numero, String voie, String rue,
			String codePostal, String ville, String pays) {
		this.batiment = batiment;
		this.numero = numero;
		this.voie = voie;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}




	/////////////////////////////////////
	//
	//			Getteurs
	//
	/////////////////////////////////////
	
	/**
	 * @return l'idAdresse
	 */
	public Integer getIdAdresse() {
		return idAdresse;
	}
	
	/**
	 * Retourne le complement d'identification du point g�ographique de cette Adresse sous forme de String
	 * @return Le batiment
	 */
	public String getBatiment() {
		return batiment;
	}

	/**
	 * Retourne le code postal de cette Adresse sous forme de String
	 * @return Le codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * Retourne le num�ro de cette Adresse sous forme de String
	 * @return Le numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Retourne le pays de cette Adresse sous forme de String
	 * @return Le pays
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * Retourne le libell� de la rue de cette Adresse sous forme de String
	 * @return La rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * Retourne la localit� de destination de cette Adresse sous forme de String
	 * @return La ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Retourne le libell� de la voie de cette Adresse sous forme de String 
	 * @return La voie
	 */
	public String getVoie() {
		return voie;
	}
	
	/////////////////////////////////////
	//
	//			Setteurs
	//
	/////////////////////////////////////
	
	/**
	 * @param idAdresse l'idAdresse � setter
	 */
	public void setIdAdresse(Integer idAdresse) {
		this.idAdresse = idAdresse;
	}

	/**
	 * Mise � jour du compl�ment d'identification du point g�ographique de cette Adresse
	 * @param batiment the batiment to set
	 */
	public void setBatiment(String batiment) {
		this.batiment = batiment;
	}

	/**
	 * Mise � jour du code postal de cette Adresse
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Mise � jour du num�ro de cette Adresse
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Mise � jour du pays de cette Adresse
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	/**
	 * Mise � jour de la rue de cette Adresse
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * Mise � jour de la locatit� de destination de cette Adresse
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Mise � jour de la voie de cette Adresse
	 * @param voie the voie to set
	 */
	public void setVoie(String voie) {
		this.voie = voie;
	}
	
	
	/////////////////////////////////////
	//
	//			Autres m�thodes
	//
	/////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	/**
	 * Teste si l'Objet sp�cifi� est bien une Adresse et si cette derni�re est �gale 
	 * � cette Adresse en comparant les valeurs des attributs suivants : numero, rue,
	 * codePostal, ville et pays
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pays == null) {
			if (other.pays != null)
				return false;
		} else if (!pays.equals(other.pays))
			return false;
		if (rue == null) {
			if (other.rue != null)
				return false;
		} else if (!rue.equals(other.rue))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}
	
	/**
	 * Retourne un String qui contient les caract�istiques de cette Adresse
	 */
	public String toString(){
		String tmp = new String();
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\tNum�ro : ");
		stringBuilder.append(getNumero());
		stringBuilder.append("\n");
		tmp = stringBuilder.toString();
		StringBuilder stringBuilder2 = new StringBuilder();
		stringBuilder2.append("\tRue : ");
		stringBuilder2.append(getRue());
		stringBuilder2.append("\n");
		tmp += stringBuilder2.toString();
		StringBuilder stringBuilder3 = new StringBuilder();
		stringBuilder3.append("\tVoie : ");
		stringBuilder3.append(getVoie());
		stringBuilder3.append("\n");
		tmp +=  stringBuilder3.toString();
		StringBuilder stringBuilder4 = new StringBuilder();
		stringBuilder4.append("\tB�timent : ");
		stringBuilder4.append(getBatiment());
		stringBuilder4.append("\n");
		tmp += stringBuilder4.toString();
		StringBuilder stringBuilder5 = new StringBuilder();
		stringBuilder5.append("\tCode postal : ");
		stringBuilder5.append(getCodePostal());
		stringBuilder5.append("\n");
		tmp += stringBuilder5.toString();
		StringBuilder stringBuilder6 = new StringBuilder();
		stringBuilder6.append("\tVille : ");
		stringBuilder6.append(getVille());
		stringBuilder6.append("\n");
		tmp += stringBuilder6.toString();
		StringBuilder stringBuilder7 = new StringBuilder();
		stringBuilder7.append("\tPays : ");
		stringBuilder7.append(getPays());
		tmp += stringBuilder7.toString();
		
		return tmp;
	}
}
