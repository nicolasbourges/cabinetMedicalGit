package com.iut.cabinet.application;

import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.user.PatientDTO;


/**
 * Classe contenant les méthodes pour transformer un PatientDTO en Patient et inversement
 * @author Nicolas BOURGES
 */
public class HelperPatient {
	
	// toPatientDTO permet de recopier les valeurs 
	// d'un objet métier Patient existant  (c-a-d ayant un état interne OK) 
	// vers dans un objet de type PatientDTO
	// --> Comme l'objet métier est déjà OK, 
	// cette méthode ne déclenchera jamais d'exception de type CabinetMedicalException ...
	// --> seule une HelperException est envisageable si le paramètre d'entrée 
	// d'entrée unPatient est une référence nulle.
	// ...En effet, si on continuait le programme dans ce cas, 
	// unPatient.getNir() provoquerait une erreur 
	// et l'arrêt immédiat du programme : 
	// ... pas de getNir() sur null !!! 
	// getNir() s'applique uniquement sur une instance de Patient 
	// (c-a-d sur une référence non nulle)
	// Ainsi afin de ne pas rencontrer ce genre de problème à l'exécution
	// on préfère déclencher une HelperException en testant la valeur
	// en début de méthode de la référence d'entrée unPatient
	
	/**
	 * Méthode pour transformer un Patient en PatientDTO
	 * @param unPatient le Patient à transformer
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
		
		// N'oubliez pas ce test à null à cause du cast ...
		// ces deux lignes de code sont expliquées dans le tutoriel
		if (unPatient.getUnAscendant()==null) patDTO.setUnAscendant(null);
		else patDTO.setUnAscendant(HelperPatient.toPatientDTO((Patient) unPatient.getUnAscendant()));
		
	
		patDTO.setAdresse(HelperAdresse.toAdresseDTO(unPatient.getAdresse()));
		
		return patDTO;
	}
	
	
	/**
	 * Méthode pour transformer un PatientDTO en Patient
	 * @param unPatientDTO le PatientDTO à transformer
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
		
		// cette ligne de code est expliquée dans le tutoriel
		pat.setAdresse(HelperAdresse.toAdresse(unPatientDTO.getAdresse()));
		
		if (unPatientDTO.getUnAscendant()==null) pat.setUnAscendant(null);
		else pat.setUnAscendant(HelperPatient.toPatient((PatientDTO) unPatientDTO.getUnAscendant()));
	
		return pat;
	}
}

