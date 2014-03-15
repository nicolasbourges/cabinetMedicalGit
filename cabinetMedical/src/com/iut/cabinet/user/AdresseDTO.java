package com.iut.cabinet.user;

import java.io.Serializable;

/** La classe AdresseDTO est la classe DTO correspondant aux 
 * copies des objets métiers de type Adresse.
 * 
 *  Elle est caractérisée par :
 * <ul>
 * <li> un <code>numéro</code> </li>
 * <li> un libellé de <code>rue</code> </li>
 * <li> un libellé de <code>voie</code> </li>
 * <li> un complément d'identification du point géographique comme un <code>batiment</code> </li>
 * <li> un <code>code postal</code> </li>
 * <li> une localité de destination (<code>ville </code>) </li>
 * <li> un <code> pays </code> </li>       
 * </ul>
 * @author Nicolas BOURGES
 * @version 1.1
 */
public class AdresseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String numero; // String car il peut y avoir 21 bis
	  private String rue;
	  private String voie;
	  private String batiment;
	  private String codePostal;
	  private String ville;
	  private String pays;

	  
	/**Constructeur d'AdresseDTO par défaut
	 * 
	 */
	public AdresseDTO() {
		super();
	}
		
	/**
	 * Constructeur d'AdresseDTO avec tous les paramètres
	 * @param batiment
	 * @param codePostal
	 * @param numero
	 * @param pays
	 * @param rue
	 * @param ville
	 * @param voie
	 */
	public AdresseDTO(String batiment, String codePostal, String numero,
			String pays, String rue, String ville, String voie) {
		super();
		this.batiment = batiment;
		this.codePostal = codePostal;
		this.numero = numero;
		this.pays = pays;
		this.rue = rue;
		this.ville = ville;
		this.voie = voie;
	}
	
	

	/** Methode retournant le numero de la rue
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/** Methode permettant de modifier le numero de la rue
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/** Methode retournant le nom de la rue
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/** Methode permettant de modifier le nom de la rue
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/** Methode retournant le nom de la voie
	 * @return the voie
	 */
	public String getVoie() {
		return voie;
	}

	/** Methode permettant de modifier le nom de la voie
	 * @param voie the voie to set
	 */
	public void setVoie(String voie) {
		this.voie = voie;
	}

	/** Methode retournant le nom du bâtiment
	 * @return the batiment
	 */
	public String getBatiment() {
		return batiment;
	}

	/** Methode permettant de modifier le nom du bâtiment
	 * @param batiment the batiment to set
	 */
	public void setBatiment(String batiment) {
		this.batiment = batiment;
	}

	/** Methode retournant le code postal
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**Methode permettant de modifier le code postal
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**Methode retournant la ville
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**Methode permettant de modifier la ville
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**Methode retournant le pays
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}

	/**Methode permettant de modifier le pays
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((batiment == null) ? 0 : batiment.hashCode());
		result = prime * result
				+ ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		result = prime * result + ((voie == null) ? 0 : voie.hashCode());
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
		AdresseDTO other = (AdresseDTO) obj;
		if (batiment == null) {
			if (other.batiment != null)
				return false;
		} else if (!batiment.equals(other.batiment))
			return false;
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
		if (voie == null) {
			if (other.voie != null)
				return false;
		} else if (!voie.equals(other.voie))
			return false;
		return true;
	}
	@Override 
	 public String toString()  {  
		    String s= "\n";
		    if (numero!=null) s=s+numero+" ";
		    if (rue!=null) s=s+rue+" ";
		    if (voie!=null) s=s+voie+" ";
		    if (batiment!=null) s=s+batiment+" ";
		    if (codePostal!=null) s=s+"\n"+codePostal+" ";
		    if (ville!=null) s=s+ville;
		    if (pays!=null) s=s+"\n"+pays;
		    return s;
		  }
  }
