package com.iut.cabinet.application;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.CabinetTechniqueException;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.metier.Personne;
import com.iut.cabinet.user.AdresseDTO;
import com.iut.cabinet.user.PatientDTO;

@SuppressWarnings("deprecation")
public class GererPatientCtrlIntegrationTest {
	private GererPatientCtrl controleur;
	
	@Before
	public void setUp() throws Exception {
		controleur = new GererPatientCtrl();
	}
	
	@After
	public void tearDown() throws Exception {
		controleur = null;
	}

	@Test
	public void testCreerPatient() {
		PatientDTO pat = new PatientDTO(32, "DURAND", "Jean", new java.sql.Date(0), true, "0555055555", "0666066666", "jeandu@jaune.fr", new AdresseDTO("","","","","","",""), null, "151024610204325", "LEDOC Teur");
		try {
			controleur.creerPatient(pat);
		} catch (ClassNotFoundException | CabinetMedicalException
				| HelperException | CabinetTechniqueException | SQLException e) {
			e.printStackTrace();
		}
		Personne persTrouv� = null;
		try {
			persTrouv� = controleur.trouverPatient("DURAND", "Jean", "151024610204325");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| SQLException e) {
			e.printStackTrace();
		}
		Patient patTrouv� = (Patient)persTrouv�;
		Patient patient = null;
		try {
			patient = com.iut.cabinet.application.HelperPatient.toPatient(pat);
		} catch (CabinetMedicalException | HelperException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(patient.getNir(), patTrouv�.getNir());
		Assert.assertEquals(patient.getNom(), patTrouv�.getNom());
		Assert.assertEquals(patient.getPrenom(), patTrouv�.getPrenom());
	}
	
	@Test
	public void testSupprimerPatientExistant() {
		PatientDTO pat = new PatientDTO(1, "FANTOMAS", "Louis", new java.sql.Date(0), true, "0555055555", "0666066666", "fant@jaune.fr", new AdresseDTO("","","","","","",""), null, "111111111111120", "LEDOC Teur");
		try {
			controleur.creerPatient(pat);
		} catch (ClassNotFoundException | CabinetMedicalException
				| HelperException | CabinetTechniqueException | SQLException e) {
			e.printStackTrace();
		}
		try {
			controleur.supprimerPatient("FANTOMAS", "Louis", "111111111111120");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| HelperException | SQLException e) {
			e.printStackTrace();
		}
		Personne fant�me = null;
		try {
			fant�me = controleur.trouverPatient("FANTOMES", "Louis", "111111111111120");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| SQLException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(fant�me, null);
	}
	
	@Test
	public void testSupprimerPatientInexistant(){
		try {
			controleur.supprimerPatient("BHCBFEHC", "Nrjezk", "111111111111120");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| HelperException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTrouverPatientExistant(){
		Personne fant�me = null;
		try {
			fant�me = controleur.trouverPatient("DOLERON", "Giles", "151024610204325");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| SQLException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(fant�me.getNom(), "DOLERON");
	}
	
	@Test
	public void testTrouverPatientInexistant(){
		Personne fant�me = null;
		try {
			fant�me = controleur.trouverPatient("BFERHJ", "Fyifzg", "111111111111120");
		} catch (ClassNotFoundException | CabinetTechniqueException
				| SQLException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(fant�me, null);
	}
}
