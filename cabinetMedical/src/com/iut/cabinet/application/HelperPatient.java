package com.iut.cabinet.application;

import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.user.PatientDTO;


/**
 * Classe contenant les m�thodes pour transformer un PatientDTO en Patient et inversement
 * @author Nicolas BOURGES
 */
public class HelperPatient {
	
	// toPatientDTO permet de recopier les valeurs 
	// d'un objet m�tier Patient existant  (c-a-d ayant un �tat interne OK) 
	// vers dans un objet de type PatientDTO
	// --> Comme l'objet m�tier est d�j� OK, 
	// cette m�thode ne d�clenchera jamais d'exception de type CabinetMedicalException ...
	// --> seule une HelperException est envisageable si le param�tre d'entr�e 
	// d'entr�e unPatient est une r�f�rence nulle.
	// ...En effet, si on continuait le programme dans ce cas, 
	// unPatient.getNir() provoquerait une erreur 
	// et l'arr�t imm�diat du programme : 
	// ... pas de getNir() sur null !!! 
	// getNir() s'applique uniquement sur une instance de Patient 
	// (c-a-d sur une r�f�rence non nulle)
	// Ainsi afin de ne pas rencontrer ce genre de probl�me � l'ex�cution
	// on pr�f�re d�clencher une HelperException en testant la valeur
	// en d�but de m�thode de la r�f�rence d'entr�e unPatient
	
	/**
	 * M�thode pour transformer un Patient en PatientDTO
	 * @param unPatient le Patient � transformer
	 * @throws HelperException
	 */
	public static PatientDTO toPatientDTO (Patient unPatient) throws HelperException
	{
		if (unPatient==null)throw new HelperException("HelperPatient : Impossible de traiter dans un patient null");
		
		PatientDTO patDTO = new PatientDTO ();
		patDTO.setNir(unPatient.getNir());
		patDTO.setMedecinTraitant(unPatient.getMedecinTraitant());
		
		patDTO.setIdPersonne(unPatient.getIdPersonne());
		patDTO.setNom (unPatient.getNom ());
		patDTO.setPrenom (unPatient.getPrenom ());
		patDTO.setDateNaissance (unPatient.getDateNaissance ());
		patDTO.setMale (unPatient.isMale());
		patDTO.setTelephone (unPatient.getTelephone ());
		patDTO.setPortable (unPatient.getPortable() );
		patDTO.setEmail (unPatient.getEmail ());
		
		// N'oubliez pas ce test � null � cause du cast ...
		// ces deux lignes de code sont expliqu�es dans le tutoriel
		if (unPatient.getUnAscendant()==null) patDTO.setUnAscendant(null);
		else patDTO.setUnAscendant(HelperPatient.toPatientDTO((Patient) unPatient.getUnAscendant()));
		
	
		patDTO.setAdresse(HelperAdresse.toAdresseDTO(unPatient.getAdresse()));
		
		return patDTO;
	}
	
	
	/**
	 * M�thode pour transformer un PatientDTO en Patient
	 * @param unPatientDTO le PatientDTO � transformer
	 * @throws CabinetMedicalException
	 * @throws HelperException
	 */
	public static Patient toPatient (PatientDTO unPatientDTO) throws CabinetMedicalException, HelperException 
	{
		if (unPatientDTO==null)throw new HelperException("HelperPatient : Impossible de traiter dans un patientDTO null");
		
		Patient pat = new Patient();
		pat.setNir(unPatientDTO.getNir());
		pat.setMedecinTraitant(unPatientDTO.getMedecinTraitant());
		
		pat.setIdPersonne(unPatientDTO.getIdPersonne());
		pat.setNom (unPatientDTO.getNom ());
		pat.setPrenom (unPatientDTO.getPrenom ());
		pat.setDateNaissance (unPatientDTO.getDateNaissance ());
		pat.setMale(unPatientDTO.isMale());
		pat.setTelephone (unPatientDTO.getTelephone ());
		pat.setPortable(unPatientDTO.getPortable());
		pat.setEmail (unPatientDTO.getEmail ());
		
		// cette ligne de code est expliqu�e dans le tutoriel
		pat.setAdresse(HelperAdresse.toAdresse(unPatientDTO.getAdresse()));
		
		if (unPatientDTO.getUnAscendant()==null) pat.setUnAscendant(null);
		else pat.setUnAscendant(HelperPatient.toPatient((PatientDTO) unPatientDTO.getUnAscendant()));
	
		return pat;
	}
}

