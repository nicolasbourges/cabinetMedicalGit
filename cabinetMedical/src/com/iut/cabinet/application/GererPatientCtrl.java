package com.iut.cabinet.application;


import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.iut.cabinet.metier.Adresse;
import com.iut.cabinet.metier.CabinetMedicalException;
import com.iut.cabinet.metier.CabinetTechniqueException;
import com.iut.cabinet.metier.Patient;
import com.iut.cabinet.metier.PatientRegle;
import com.iut.cabinet.metier.Personne;
import com.iut.cabinet.metier.PersonneDAOFichier;
import com.iut.cabinet.metier.PersonneDAOJDBC;
import com.iut.cabinet.user.PatientDTO;
import com.iut.cabinet.util.DateUtil;
import com.iut.cabinet.util.SimpleConnection;


/**
 * Classe correspondant au contr�leur dans le MVC du cabinet m�dical
 * @author Nicolas BOURGES
 * @version 1.1
 */
public class GererPatientCtrl {
	private static Logger logger = Logger.getLogger(GererPatientCtrl.class.getName());

	/**
	 * M�thode pour cr�er un PatientDTO puis le stocker dans la BD mySQL
	 * @param unPatientDTO le PatientDTO � cr�er puis stocker
	 * @throws HelperException
	 * @throws CabinetMedicalException
	 * @throws ClassNotFoundException
	 * @throws CabinetTechniqueException
	 * @throws SQLException 
	 */
	public void creerPatient(PatientDTO unPatientDTO) throws CabinetMedicalException, HelperException, ClassNotFoundException, CabinetTechniqueException, SQLException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode creerPatient de GererPatientCtrl");
		}
		Connection conn = null;
		conn = SimpleConnection.getInstance().getconnection();
		Patient unPat;
		unPat = HelperPatient.toPatient(unPatientDTO);
		unPat.affichageSimplifie(unPat);
		
		//ajout d'une Personne dans la BD
		PersonneDAOJDBC.storePersonne(unPat, conn);
		try{
			conn.setAutoCommit(false);	//sinon le commit de l'ennonc� du TP ne servirait � rien
			conn.commit();
		} catch(SQLException e){
			e.printStackTrace();
			throw new CabinetTechniqueException("Probl�me lors de la validation de la trasnsaction"+e.getMessage());
		}
		
		try{
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new CabinetTechniqueException("Probl�me lors de la fermeture de la connexion"+e.getMessage());
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode creerPatient de GererPatientCtrl");
		}
	}

	/**
	 * M�thode pour lister tous les PatientsDTO stock�s en BD mySQL
	 * @return maListePat la liste des PatientsDTO stock�s
	 * @throws HelperException
	 * @throws ClassNotFoundException
	 * @throws CabinetTechniqueException
	 * @throws SQLException 
	 */
	public Collection<PatientDTO> listerPatients() throws ClassNotFoundException, CabinetTechniqueException, HelperException, SQLException {
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode listerPatients de GererPatientCtrl");
		}
		Collection <Personne> maListe = null;
		Connection conn = null;
		conn = SimpleConnection.getInstance().getconnection();
		maListe = PersonneDAOJDBC.findAllPersonnes(conn);
		
		try{
			conn.setAutoCommit(false);
			conn.commit();
		} catch(SQLException e){
			throw new CabinetTechniqueException("Probl�me lors de la validation de la trasnsaction"+e.getMessage());
		}
		
		try{
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch(SQLException e){
			throw new CabinetTechniqueException("Probl�me lors de la fermeture de la connexion"+e.getMessage());
		}
		
		Collection <PatientDTO> maListePat = new ArrayList<PatientDTO>();
		for (Personne unPatientDTO: maListe){
			if(unPatientDTO instanceof Patient){
				PatientDTO patDTO = new PatientDTO();
				patDTO=HelperPatient.toPatientDTO((Patient)unPatientDTO);
				maListePat.add(patDTO);
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode listerPatients de GererPatientCtrl");
		}
		return maListePat;
	}
	
	/**
	 * M�thode pour trouver un PatientDTO et le renvoyer
	 * @param nom le nom du PatientDTO � trouver
	 * @param prenom le prenom du PatientDTO � trouver
	 * @return unPatientDTO
	 * @throws ClassNotFoundException
	 * @throws CabinetTechniqueException
	 * @throws SQLException 
	 */
	public Personne trouverPatient(String nom, String prenom, String nir) throws ClassNotFoundException, CabinetTechniqueException, SQLException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode trouverPatient de GererPatientCtrl");
		}
		Connection conn = null;
		conn = SimpleConnection.getInstance().getconnection();
		conn.setAutoCommit(false);
		Collection <Personne> maListe = PersonneDAOJDBC.findAllPersonnes(conn);
		for (Personne unPatientDTO: maListe){
			if(unPatientDTO instanceof Patient && unPatientDTO.getNom().equals(nom) && unPatientDTO.getPrenom().equals(prenom) &&((Patient) unPatientDTO).getNir().equals(nir)){
				if(logger.isDebugEnabled()){
					logger.debug("Sortie de la m�thode trouverPatient de GererPatientCtrl");
				}
				return unPatientDTO;
			}
		}
		conn.commit();	//penser au rolllback au cas o�
		conn.close();
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode trouverPatient de GererPatientCtrl");
		}
		//si on a pas trouv� le patient
		return null;
	}
	
	/**
	 * M�thode pour supprimer un PatientDTO de la liste stock�e en m�moire
	 * @param nom le nom du PatientDTO � supprimer
	 * @param prenom le prenom du PatientDTO � supprimer
	 * @throws ClassNotFoundException
	 * @throws CabinetTechniqueException
	 * @throws HelperException
	 * @throws SQLException 
	 */
	public void supprimerPatient(String nom, String prenom, String nir) throws ClassNotFoundException, CabinetTechniqueException, HelperException, SQLException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode supprimerPatient de GererPatientCtrl");
		}
		Connection conn = null;
		conn = SimpleConnection.getInstance().getconnection();
		Patient unPat = new Patient(nom, prenom, nir);
		
		//ajout d'une Personne dans la BD
		PersonneDAOJDBC.deletePersonne(unPat, conn);
		try{
			conn.setAutoCommit(false);	//sinon le commit de l'ennonc� du TP ne servirait � rien
			conn.commit();
		} catch(SQLException e){
			e.printStackTrace();
			throw new CabinetTechniqueException("Probl�me lors de la validation de la trasnsaction"+e.getMessage());
		}
		
		try{
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new CabinetTechniqueException("Probl�me lors de la fermeture de la connexion"+e.getMessage());
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode supprimerPatient de GererPatientCtrl");
		}
	}
	
	/**
	 * M�thode pour modifier un PatientDTO de la liste stock�e en m�moire
	 * @param nom le nom du PatientDTO � supprimer
	 * @param prenom le prenom du PatientDTO � supprimer
	 * @param champAModifier l'attribut du PatientDTO qui doit �tre modifi�
	 * @param modif la modification dudit attribut
	 * @throws ClassNotFoundException
	 * @throws CabinetTechniqueException
	 * @throws CabinetMedicalException
	 * @throws HelperException 
	 * @throws SQLException 
	 */
	public void modifierPatient(String nom, String prenom, String nir, String champAModifier, String modif) throws ClassNotFoundException, CabinetTechniqueException, CabinetMedicalException, HelperException, SQLException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode modifierPatient de GererPatientCtrl");
		}
		Connection conn = null;
		conn = SimpleConnection.getInstance().getconnection();
		Collection <Personne> maListe = PersonneDAOJDBC.findAllPersonnes(conn);
		Personne patientAModifier;
		patientAModifier=trouverPatient(nom, prenom, nir);
		supprimerPatient(nom, prenom, nir);
		switch (champAModifier) {
		case "nir":
			PatientRegle.verifierNir(modif);
			Patient patientModifNir = new Patient();
			patientModifNir=(Patient)patientAModifier;
			patientModifNir.setNir(modif);
			//On remplace le patient avec l'ancien NIR...
			maListe.remove(patientAModifier);
			//... par un patient identique, mais avec le nouveau NIR
			maListe.add(patientModifNir);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "nom":
			maListe.remove(patientAModifier);
			patientAModifier.setNom(modif);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "prenom":
			maListe.remove(patientAModifier);
			patientAModifier.setPrenom(modif);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "s_dateNaissance":
			maListe.remove(patientAModifier);
			Date dateNaissance = DateUtil.toDate(modif, DateUtil.FRENCH_DEFAUT);
			patientAModifier.setDateNaissance(dateNaissance);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "telephone":
			maListe.remove(patientAModifier);
			patientAModifier.setTelephone(modif);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "portable":
			maListe.remove(patientAModifier);
			patientAModifier.setPortable(modif);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "email":
			maListe.remove(patientAModifier);
			patientAModifier.setEmail(modif);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "medecinTraitant":
			Patient patientModifMed = new Patient();
			patientModifMed=(Patient)patientAModifier;
			patientModifMed.setMedecinTraitant(modif);
			//On remplace le patient avec l'ancien NIR...
			maListe.remove(patientAModifier);
			//... par un patient identique, mais avec le nouveau NIR
			maListe.add(patientModifMed);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "numero":
			maListe.remove(patientAModifier);
			Adresse adresseAModifierNum = new Adresse();
			adresseAModifierNum=patientAModifier.getAdresse();
			adresseAModifierNum.setNumero(modif);
			patientAModifier.setAdresse(adresseAModifierNum);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "codePostal":
			maListe.remove(patientAModifier);
			Adresse adresseAModifierCp = new Adresse();
			adresseAModifierCp=patientAModifier.getAdresse();
			adresseAModifierCp.setCodePostal(modif);
			patientAModifier.setAdresse(adresseAModifierCp);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "ville":
			maListe.remove(patientAModifier);
			Adresse adresseAModifierVille = new Adresse();
			adresseAModifierVille=patientAModifier.getAdresse();
			adresseAModifierVille.setVille(modif);
			patientAModifier.setAdresse(adresseAModifierVille);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "pays":
			maListe.remove(patientAModifier);
			Adresse adresseAModifierPays = new Adresse();
			adresseAModifierPays=patientAModifier.getAdresse();
			adresseAModifierPays.setPays(modif);
			patientAModifier.setAdresse(adresseAModifierPays);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "isMale":
			if(modif.equals("m")){
				maListe.remove(patientAModifier);
				patientAModifier.setMale(true);
				maListe.add(patientAModifier);
				PersonneDAOFichier.storeAllPersonnes(maListe);
			}
			else{
				if(modif.equals("f")){
					maListe.remove(patientAModifier);
					patientAModifier.setMale(false);
					maListe.add(patientAModifier);
					PersonneDAOFichier.storeAllPersonnes(maListe);
				}
				else{
					System.out.println("Vous devez saisir m pour homme et f pour femme!");
				}
			}
			break;
		case "rue":
			maListe.remove(patientAModifier);
			Adresse adresseAModifierRue = new Adresse();
			adresseAModifierRue=patientAModifier.getAdresse();
			adresseAModifierRue.setRue(modif);
			patientAModifier.setAdresse(adresseAModifierRue);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "voie":
			maListe.remove(patientAModifier);
			Adresse adresseAModifierVoie = new Adresse();
			adresseAModifierVoie=patientAModifier.getAdresse();
			adresseAModifierVoie.setVoie(modif);
			patientAModifier.setAdresse(adresseAModifierVoie);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		case "batiment":
			maListe.remove(patientAModifier);
			Adresse adresseAModifierBat = new Adresse();
			adresseAModifierBat=patientAModifier.getAdresse();
			adresseAModifierBat.setBatiment(modif);
			patientAModifier.setAdresse(adresseAModifierBat);
			maListe.add(patientAModifier);
			PersonneDAOFichier.storeAllPersonnes(maListe);
			break;
		default:
			break;
		}
		conn.commit();	//penser au rolllback au cas o�
		conn.close();
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode modifierPatient de GererPatientCtrl");
		}
	}
	
}
