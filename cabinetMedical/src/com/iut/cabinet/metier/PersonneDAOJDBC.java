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
 * Classe permettant la gestion des personnes avec la bases de données mySQL
 * @author Nicolas BOURGES
 */
public class PersonneDAOJDBC {
	private static Logger logger = Logger.getLogger(GererPatientCtrl.class.getName());
	
	/**
	 * Méthode permettant de récupérer la bonne personne c-à-d un Patient ou un Professionnel
	 * @param rs l'objet ResultSet contenant la personne à tester
	 * @param c la connexion à la BD
	 * @return pat (si la personne est un Patient) ou pro (si la personne est un Professionnel) ou null en cas de problème
	 * @throws CabinetTechniqueException
	 * @throws CabinetMedicalException
	 */
	private static Personne getBonnePersonne(ResultSet rs, Connection c) throws CabinetTechniqueException, CabinetMedicalException{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode getBonnePersonne de PersonneDAOJDBC");
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
				logger.debug("Sortie de la méthode getBonnePersonne de PersonneDAOJDBC");
			}
		}
	}
	
	/**
	 * Méthode permettant de récupérer toutes les personnes stockées dans la BD mySQL
	 * @param c la connexion à la BD
	 * @return listPers, la liste de Personne stockées en BD mySQL
	 * @throws CabinetTechniqueException
	 */
	public static Collection<Personne> findAllPersonnes(Connection c) throws CabinetTechniqueException{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode findAllPersonnes de PersonneDAOJDBC");
		}
		Collection <Personne> listPers = new ArrayList<Personne>();
		
		//Récupération du ResulSet par requête pré-compilée
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
			logger.error("Déclenchement d'une exception métier dans la méthode findAllPersonnes de PersonneDAOJDBC");
			throw new CabinetTechniqueException("Erreur lors du parcours du resulSet de findAllPersonnes"+e.getMessage());
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode findAllPersonnes de PersonneDAOJDBC");
		}
		return listPers;
	}
	
	/**
	 * Méthode permettant de stocker une Personne dans la base de données mySQL
	 * @param unePers la personne à stocker
	 * @param c la connexion à la BD
	 * @throws CabinetTechniqueException
	 * @throws SQLException
	 */
	public static void storePersonne(Personne unePers, Connection c) throws CabinetTechniqueException, SQLException{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode storePersonne de PersonneDAOJDBC");
		}
		PreparedStatement pst = null;
		String requete = "insert into Personne (nom, prenom, datenaissance, male, telephone, portable, email, idAscendant, idTypePersonne, nir, medecinTraitant)"
							+"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			
			//////////////////////////////////////////////////////////////////////////////////////
			//										/!\										 	//
			//	ATTENTION! Prise en compte du fait que l'on n'ajoute à la BD que des Patients! 	//
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
			//récupération des différents champs du patient avant de stocker
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
		//Recherche de l'id de la personne juste insérée pour insérer son adresse
	    for(Personne unPatient: listPers){
			if(unPatient instanceof Patient && unPatient.getNom().equals(unePers.getNom()) &&  unPatient.getPrenom().equals(unePers.getPrenom()) &&  ((Patient) unPatient).getNir().equals(((Patient) unePers).getNir())){
				id=unPatient.getIdPersonne();
			}
		}

		AdresseDAOJDBC.storeAdresse(unePers.getAdresse(), id, c);
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode storePersonne de PersonneDAOJDBC");
		}
	}
	
	/**
	 * Méthode permettant de supprimer une Personne de la base de données mySQL
	 * @param unePers la personne à supprimer
	 * @param c la connexion à la BD
	 * @throws CabinetTechniqueException
	 * @throws SQLException
	 */
	public static void deletePersonne(Personne unePers, Connection c) throws CabinetTechniqueException, SQLException{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode deletePersonne de PersonneDAOJDBC");
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
			logger.debug("Sortie de la méthode deletePersonne de PersonneDAOJDBC");
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
		System.out.println("La collection chargée donne: ");
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
