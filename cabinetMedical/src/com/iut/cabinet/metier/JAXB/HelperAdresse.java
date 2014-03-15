package com.iut.cabinet.metier.JAXB;

import com.iut.cabinet.metier.JAXB.HelperException;
import com.iut.cabinet.metier.Adresse;

//////////////////////////////////////////////////////////////////////
/// HELPER pour la conversion des classes JAXB / classes métier JAVA
/// c-a-d   :  AdresseType / Adresse
/////////////////////////////////////////////////////////////////////

public class HelperAdresse {
	
	public static AdresseType toAdresseType (Adresse uneAdresse) throws 

HelperException
	{
		if (uneAdresse==null)throw new HelperException("HelperAdresse : Impossible de traiter dans une adresse null");
		
		//AdresseType adTypeJAXB = new AdresseType ();
		ObjectFactory fabrique = new ObjectFactory();
		AdresseType adTypeJAXB =fabrique.createAdresseType();
		
		
		adTypeJAXB.setNumero ( uneAdresse.getNumero ());
		adTypeJAXB.setRue ( uneAdresse.getRue ());
		adTypeJAXB.setVoie ( uneAdresse.getVoie ());
		adTypeJAXB.setBatiment ( uneAdresse.getBatiment ());
		adTypeJAXB.setCodePostal ( uneAdresse.getCodePostal ());
		adTypeJAXB.setVille ( uneAdresse.getVille());
		adTypeJAXB.setPays ( uneAdresse.getPays ());
		
		return  adTypeJAXB;
	}
	
	
	public static Adresse toAdresse (AdresseType uneAdresseTypeJAXB) throws HelperException 
	{
		if (uneAdresseTypeJAXB==null)throw new HelperException("HelperAdresse : Impossible de traiter dans une adresseType null");
		
		Adresse ad = new Adresse ();
		
		ad.setNumero ( uneAdresseTypeJAXB.getNumero ());
		ad.setRue ( uneAdresseTypeJAXB.getRue ());
		ad.setVoie ( uneAdresseTypeJAXB.getVoie ());
		ad.setBatiment ( uneAdresseTypeJAXB.getBatiment ());
		ad.setCodePostal (uneAdresseTypeJAXB.getCodePostal ());
		ad.setVille ( uneAdresseTypeJAXB.getVille ());
		ad.setPays ( uneAdresseTypeJAXB.getPays ());
		
		return  ad;
	}


}
