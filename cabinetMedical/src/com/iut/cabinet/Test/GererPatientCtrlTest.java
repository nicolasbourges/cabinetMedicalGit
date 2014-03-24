package com.iut.cabinet.Test;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


import org.mockito.Mock;
import org.mockito.Mockito;

import com.iut.cabinet.application.GererPatientCtrl;
import com.iut.cabinet.application.HelperException;
import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.CabinetTechniqueException;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.metier.Personne;
import com.iut.cabinet.user.AdresseDTO;
import com.iut.cabinet.user.PatientDTO;

public class GererPatientCtrlTest {

	@Mock
	PatientDTO patientDTO;
	
	@Mock
	AdresseDTO adresse;
	
	@Test
    public void creerPatientTest1() {
    	
    	GererPatientCtrl gererPatientCtrl = new GererPatientCtrl();
    	
    	try {
			Assert.assertNull(gererPatientCtrl.trouverPatient("nom", "prenom", "nir"));
		} catch (ClassNotFoundException | CabinetTechniqueException
				| SQLException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void trouverPatientTest2(){
    	Personne personne = null;
    	GererPatientCtrl gererPatientCtrl = new GererPatientCtrl();
    	PatientDTO patientDTO = Mockito.mock(PatientDTO.class);
    	
    	Mockito.when(patientDTO.getNir()).thenReturn("194123366300270");
    	Mockito.when(patientDTO.getNom()).thenReturn("NOM");
    	Mockito.when(patientDTO.getPrenom()).thenReturn("Prenom");
    	Mockito.when(patientDTO.getDateNaissance()).thenReturn(new Date(0));
    	Mockito.when(patientDTO.getEmail()).thenReturn("Prenom.NOM@mail.fr");
    	Mockito.when(patientDTO.getIdPersonne()).thenReturn(1000);
    	Mockito.when(patientDTO.getMedecinTraitant()).thenReturn("Medecin");
    	Mockito.when(patientDTO.getPortable()).thenReturn("0332907415");
    	Mockito.when(patientDTO.getTelephone()).thenReturn("0556063209");    	
    	AdresseDTO adresse = Mockito.mock(AdresseDTO.class);
		Mockito.when(patientDTO.getAdresse()).thenReturn(adresse);
    	
    	   	
    	try { 
			gererPatientCtrl.creerPatient(patientDTO);
		} catch (ClassNotFoundException | CabinetMedicalException
				| HelperException | CabinetTechniqueException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    	try {
			personne = gererPatientCtrl.trouverPatient("NOM", "Prenom", "194123366300270");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Patient pers = (Patient)personne;
    	Assert.assertEquals("194123366300270", pers.getNir());
    }
	
    @Test
    public void suprimerPatientTest1(){
    	Personne personne = null;
    	GererPatientCtrl gererPatientCtrl = new GererPatientCtrl();
    	PatientDTO patientDTO = Mockito.mock(PatientDTO.class);
    	
    	Mockito.when(patientDTO.getNir()).thenReturn("194123366300270");
    	Mockito.when(patientDTO.getNom()).thenReturn("NOMS");
    	Mockito.when(patientDTO.getPrenom()).thenReturn("Prenom");
    	Mockito.when(patientDTO.getDateNaissance()).thenReturn(new Date(0));
    	Mockito.when(patientDTO.getEmail()).thenReturn("Prenom.NOM@mail.fr");
    	Mockito.when(patientDTO.getIdPersonne()).thenReturn(1000);
    	Mockito.when(patientDTO.getMedecinTraitant()).thenReturn("Medecin");
    	Mockito.when(patientDTO.getPortable()).thenReturn("0332907415");
    	Mockito.when(patientDTO.getTelephone()).thenReturn("0556063209");    	
    	AdresseDTO adresse = Mockito.mock(AdresseDTO.class);
		Mockito.when(patientDTO.getAdresse()).thenReturn(adresse);
    	
    	   	
    	try { 
			gererPatientCtrl.creerPatient(patientDTO);
		} catch (ClassNotFoundException | CabinetMedicalException
				| HelperException | CabinetTechniqueException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			gererPatientCtrl.supprimerPatient("NOMS", "prenom", "194123366300270");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| HelperException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	try {
			personne = gererPatientCtrl.trouverPatient("NOMS", "Prenom", "194123366300270");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

			Assert.assertNull(personne);

    	
    }
    
    @Ignore
    @Test
    public void modifierPatientTest2(){
    	Personne personne = null;
    	GererPatientCtrl gererPatientCtrl = new GererPatientCtrl();
    	PatientDTO patientDTO = Mockito.mock(PatientDTO.class);
    	
    	Mockito.when(patientDTO.getNir()).thenReturn("194123366300270");
    	Mockito.when(patientDTO.getNom()).thenReturn("NOMM");
    	Mockito.when(patientDTO.getPrenom()).thenReturn("Prenom");
    	Mockito.when(patientDTO.getDateNaissance()).thenReturn(new Date(0));
    	Mockito.when(patientDTO.getEmail()).thenReturn("Prenom.NOM@mail.fr");
    	Mockito.when(patientDTO.getIdPersonne()).thenReturn(1000);
    	Mockito.when(patientDTO.getMedecinTraitant()).thenReturn("Medecin");
    	Mockito.when(patientDTO.getPortable()).thenReturn("0332907415");
    	Mockito.when(patientDTO.getTelephone()).thenReturn("0556063209");    	
    	AdresseDTO adresse = Mockito.mock(AdresseDTO.class);
		Mockito.when(patientDTO.getAdresse()).thenReturn(adresse);
    	
    	   	
    	try { 
			gererPatientCtrl.creerPatient(patientDTO);
		} catch (ClassNotFoundException | CabinetMedicalException
				| HelperException | CabinetTechniqueException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    	
    	try {
			gererPatientCtrl.modifierPatient("NOMM", "prenom", "194123366300270", "nom", "NOUVEAUNOM");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| CabinetMedicalException | HelperException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	try {
			personne = gererPatientCtrl.trouverPatient("NOMM", "Prenom", "194123366300270");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Patient pers = (Patient)personne;
    	Assert.assertEquals("NOUVEAUNOM", pers.getNom());
    }
	
}
