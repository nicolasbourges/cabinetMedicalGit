package com.iut.cabinet.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.iut.cabinet.application.GererPatientCtrl;
import com.iut.cabinet.util.SimpleConnection;
/**
 * Classe permettant la gestion des personnes avec la bases de donn�es mySQL
 * @author Nicolas BOURGES
 */
public class PersonneDAOJDBC {
	private static Logger logger = Logger.getLogger(GererPatientCtrl.class.getName());
	
	/**
	 * M�thode permettant de r�cup�rer la bonne personne c-�-d un Patient ou un Professionnel
	 * @param rs l'objet ResultSet contenant la personne � tester
	 * @param c la connexion � la BD
	 * @return pat (si la personne est un Patient) ou pro (si la personne est un Professionnel) ou null en cas de probl�me
	 * @throws CabinetTechniqueException
	 * @throws CabinetMedicalException
	 */
	private static Personne getBonnePersonne(ResultSet rs, Connection c) throws CabinetTechniqueException, CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode getBonnePersonne de PersonneDAOJDBC");
		}
		try {
			if(rs.getInt("idTypePersonne")==0){
				Patient pat = new Patient(rs.getInt("idPersonne"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("datenaissance"), rs.getBoolean("male"), rs.getString("telephone"), rs.getString("portable"), rs.getString("email"), AdresseDAOJDBC.findAdresseByIdPersonne(rs.getInt("idPersonne"), c), null, rs.getString("nir"), rs.getString("medecinTraitant"));
				return pat;
			}
			else{
				if(rs.getInt("idTypePersonne")==1){
					Professionnel pro = new Professionnel(rs.getInt("idPersonne"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("datenaissance"), rs.getBoolean("male"), rs.getString("telephone"), rs.getString("portable"), rs.getString("email"), AdresseDAOJDBC.findAdresseByIdPersonne(rs.getInt("idPersonne"), c), null, rs.getString("immatriculation"), rs.getString("specialite"));
					return pro;
				}
				else{
					return null;
				}
			}
		} catch (SQLException e) {
			throw new CabinetTechniqueException("Erreur lors du parcours du resulSet de getBonnePersonne"+e.getMessage());
		}
		finally{
			if(logger.isDebugEnabled()){
				logger.debug("Sortie de la m�thode getBonnePersonne de PersonneDAOJDBC");
			}
		}
	}
	
	/**
	 * M�thode permettant de r�cup�rer toutes les personnes stock�es dans la BD mySQL
	 * @param c la connexion � la BD
	 * @return listPers, la liste de Personne stock�es en BD mySQL
	 * @throws CabinetTechniqueException
	 */
	public static Collection<Personne> findAllPersonnes(Connection c) throws CabinetTechniqueException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode findAllPersonnes de PersonneDAOJDBC");
		}
		Collection <Personne> listPers = new ArrayList<Personne>();
		
		//R�cup�ration du ResulSet par requ�te pr�-compil�e
		ResultSet rs = null;
		String requete = "select * from personne";
		PreparedStatement instruction = null;
		try{
			instruction=c.prepareStatement(requete);
			rs = instruction.executeQuery();
			
			while(rs.next()){
				Personne unePersonne = getBonnePersonne(rs, c);
				listPers.add(unePersonne);
			}
		}
		catch(SQLException sqle){
			throw new CabinetTechniqueException("Erreur lors du parcours du resulSet de findAllPersonnes"+sqle.getMessage());
		} catch (CabinetMedicalException e) {
			logger.error("D�clenchement d'une exception m�tier dans la m�thode findAllPersonnes de PersonneDAOJDBC");
			throw new CabinetTechniqueException("Erreur lors du parcours du resulSet de findAllPersonnes"+e.getMessage());
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode findAllPersonnes de PersonneDAOJDBC");
		}
		return listPers;
	}
	
	/**
	 * M�thode permettant de stocker une Personne dans la base de donn�es mySQL
	 * @param unePers la personne � stocker
	 * @param c la connexion � la BD
	 * @throws CabinetTechniqueException
	 * @throws SQLException
	 */
	public static void storePersonne(Personne unePers, Connection c) throws CabinetTechniqueException, SQLException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode storePersonne de PersonneDAOJDBC");
		}
		PreparedStatement pst = null;
		String requete = "insert into Personne (nom, prenom, datenaissance, male, telephone, portable, email, idAscendant, idTypePersonne, nir, medecinTraitant)"
							+"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			
			//////////////////////////////////////////////////////////////////////////////////////
			//										/!\										 	//
			//	ATTENTION! Prise en compte du fait que l'on n'ajoute � la BD que des Patients! 	//
			//								(pour cette version)							 	//
			//////////////////////////////////////////////////////////////////////////////////////
			
			pst = c.prepareStatement(requete);
			int male;
			if(unePers.isMale()){
				male=1;
			}
			else{
				male=0;
			}
			//r�cup�ration des diff�rents champs du patient avant de stocker
			pst.setString(1, unePers.getNom());
			pst.setString(2, unePers.getPrenom());
			pst.setDate(3, unePers.getDateNaissance());
			pst.setInt(4, male);
			pst.setString(5, unePers.getTelephone());
			pst.setString(6, unePers.getPortable());
			pst.setString(7, unePers.getEmail());
			pst.setString(8, null);
			Patient unPat = (Patient)unePers;
			pst.setInt(9, 0);
			pst.setString(10, unPat.getNir());
			pst.setString(11, unPat.getMedecinTraitant());
			
			@SuppressWarnings("unused")
			int nbLignes = pst.executeUpdate();
			
			c.setAutoCommit(false);
			c.commit();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	 	Collection<Personne> listPers= null;
	 	int id = 0;
		
		listPers= findAllPersonnes(c);
		//Recherche de l'id de la personne juste ins�r�e pour ins�rer son adresse
	    for(Personne unPatient: listPers){
			if(unPatient instanceof Patient && unPatient.getNom().equals(unePers.getNom()) &&  unPatient.getPrenom().equals(unePers.getPrenom()) &&  ((Patient) unPatient).getNir().equals(((Patient) unePers).getNir())){
				id=unPatient.getIdPersonne();
			}
		}

		AdresseDAOJDBC.storeAdresse(unePers.getAdresse(), id, c);
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode storePersonne de PersonneDAOJDBC");
		}
	}
	
	/**
	 * M�thode permettant de supprimer une Personne de la base de donn�es mySQL
	 * @param unePers la personne � supprimer
	 * @param c la connexion � la BD
	 * @throws CabinetTechniqueException
	 * @throws SQLException
	 */
	public static void deletePersonne(Personne unePers, Connection c) throws CabinetTechniqueException, SQLException{
		if(logger.isDebugEnabled()){
			logger.debug("Entr�e dans la m�thode deletePersonne de PersonneDAOJDBC");
		}
		//Pour l'adresse
		PreparedStatement pst = null;
		String requete = "delete from adresse where idPersonne = (select idPersonne from personne where nom = ? && prenom = ? && nir=?)";
		
		try{
			pst = c.prepareStatement(requete);
			pst.setString(1, unePers.getNom());
			pst.setString(2, unePers.getPrenom());
			Patient unPat = (Patient)unePers;
			pst.setString(3, unPat.getNir());
			
			@SuppressWarnings("unused")
			int nbLignes = pst.executeUpdate();
			
			c.setAutoCommit(false);
			c.commit();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		//Pour la personne
		requete = "delete from Personne where nom = ? && prenom = ? && nir=?";
		
		try{
			pst = c.prepareStatement(requete);
			pst.setString(1, unePers.getNom());
			pst.setString(2, unePers.getPrenom());
			Patient unPat = (Patient)unePers;
			pst.setString(3, unPat.getNir());
			
			@SuppressWarnings("unused")
			int nbLignes = pst.executeUpdate();
			
			c.setAutoCommit(false);
			c.commit();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la m�thode deletePersonne de PersonneDAOJDBC");
		}
	}
	
	public static void main (String[] args){
		Connection conn = null;
		Collection<Personne> maListe = null;
		try{
			conn = SimpleConnection.getInstance().getconnection();
			maListe = findAllPersonnes(conn);
		}
		catch (CabinetTechniqueException e){
			e.printStackTrace();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		System.out.println("---------------------------");
		System.out.println("La collection charg�e donne: ");
		System.out.println(maListe);
		System.out.println("---------------------------");
		
		try{
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
