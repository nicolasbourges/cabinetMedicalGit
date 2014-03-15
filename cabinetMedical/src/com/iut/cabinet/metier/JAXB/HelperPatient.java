package com.iut.cabinet.metier.JAXB;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


import com.iut.cabinet.metier.JAXB.HelperException;
import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.Patient;

//////////////////////////////////////////////////////////////////////
/// HELPER pour la conversion des classes JAXB / classes métier JAVA
/// c-a-d   :  PatientType / Patient
/////////////////////////////////////////////////////////////////////
public class HelperPatient {
	
    
		// patientType récupère les valeurs,
		// il ne déclenchera pas les exceptions ...
		public static PatientType toPatientType (Patient unPatient) throws HelperException
		{
			if (unPatient==null)throw new HelperException("HelperPatient - toPatientType : Impossible de traiter dans un patient null");
			
			//PatientType patTypeJAXB = new PatientType ();
			ObjectFactory fabrique = new ObjectFactory();
			PatientType patTypeJAXB =fabrique.createPatientType();
			
			patTypeJAXB.setNir(unPatient.getNir());
			patTypeJAXB.setMedecinTraitant(unPatient.getMedecinTraitant());
			
			patTypeJAXB.setIdPersonne(unPatient.getIdPersonne());
			patTypeJAXB.setNom (unPatient.getNom ());
			patTypeJAXB.setPrenom (unPatient.getPrenom ());
			
						
			// Conversion java.SQL.Date to XMLGregorianCalendar 
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(unPatient.getDateNaissance ());
			 XMLGregorianCalendar gc;
			try {
				gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			} catch (DatatypeConfigurationException e) {
				throw new HelperException("HelperPatient - toPatientType: Pb de conversion de date ... ");
			}
			 patTypeJAXB.setDateNaissance (gc);
		
			if (unPatient.isMale()==true) patTypeJAXB.setSexe ("Masculin");
			else patTypeJAXB.setSexe ("Feminin");
			patTypeJAXB.setTelephone (unPatient.getTelephone ());
			patTypeJAXB.setPortable (unPatient.getPortable() );
			patTypeJAXB.setEmail (unPatient.getEmail ());
			
			// pour l'instant on ne traite pas le cas de l'ascendant
			// ... à voir par la suite ...
			/*if (unPatient.getUnAscendant()==null) patTypeJAXB.setUnAscendant(null);
			else patTypeJAXB.setUnAscendant(...à vous de compléter plus tard lorsque vous gérerez l'ascendant....);
			*/
		
			patTypeJAXB.setAdresse(HelperAdresse.toAdresseType(unPatient.getAdresse()));
			
			return patTypeJAXB;
		}
		
		
		public static Patient toPatient (PatientType unPatientTypeJAXB) throws CabinetMedicalException, HelperException 
		{
			if (unPatientTypeJAXB==null)throw new HelperException("HelperPatient : Impossible de traiter dans un patientType null");
			
			Patient pat = new Patient();
			pat.setNir(unPatientTypeJAXB.getNir());
			pat.setMedecinTraitant(unPatientTypeJAXB.getMedecinTraitant());
			
			pat.setIdPersonne(unPatientTypeJAXB.getIdPersonne());
			pat.setNom (unPatientTypeJAXB.getNom ());
			pat.setPrenom (unPatientTypeJAXB.getPrenom ());
			
			// Conversion XMLGregorianCalendar to java.SQL.Date
			XMLGregorianCalendar xmlCal=unPatientTypeJAXB.getDateNaissance();
			java.util.Date dt = xmlCal.toGregorianCalendar().getTime();
			java.sql.Date sqlDt = new java.sql.Date(dt.getTime());
			pat.setDateNaissance (sqlDt);
			
			if (unPatientTypeJAXB.getSexe().equals("Masculin")) pat.setMale(true);
			else pat.setMale(false);
			
			pat.setTelephone (unPatientTypeJAXB.getTelephone ());
			pat.setPortable(unPatientTypeJAXB.getPortable());
			pat.setEmail (unPatientTypeJAXB.getEmail ());
			
			pat.setAdresse(HelperAdresse.toAdresse(unPatientTypeJAXB.getAdresse()));
			
			// pour l'instant on ne traite pas le cas de l'ascendant
			// ... à voir par la suite ...
			/*if (unPatientTypeJAXB.getUnAscendant()==null) pat.setUnAscendant(null);
			else pat.setUnAscendant(...à vous de compléter plus tard lorsque vous gérerez l'ascendant....);
			*/
			
			return pat;
		}
	

}
