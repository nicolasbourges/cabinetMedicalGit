package com.iut.cabinet.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.iut.cabinet.presentation.GererPatientIHM;
import com.iut.cabinet.util.SimpleConnection;


/**	 
 *  Permet de gérer la relation entre le logiciel et la base de données pour <code>Adresse</code>
 * 
 * @author Nicolas Bourges
 * @version 1.1
 */

@SuppressWarnings("unused")
public class AdresseDAOJDBC {
	private static Logger logger = Logger.getLogger(GererPatientIHM.class.getName());

	/**
	 * Requête pour récupérer toutes les adresses
	 * 
	 */
	private static final String reqAllAdresses = "select * from Adresse;";
	
	/**
	 * Requête pour récupérer les adresses avec un id spécifique
	 * 
	 */
	 private static final String reqFindAdresseByPk = "select * from Adresse where idAdresse = ?;";
	 
	 /**
		 * Requête pour insérer une adresse
		 * 
		 */
	 private static final String reqInsertAdresse = "insert into Adresse (numero,rue,voie,batiment,codePostal,ville,pays,idPersonne) values (?,?,?,?,?,?,?,?);";
	 
	 /**
		 * Requête pour modifier une adresse
		 * 
		 */
	 private static final String reqUpdateAdresse = "update  Adresse set numero =?,rue=?,voie=?,batiment=?,codePostal=?,ville=?,pays=?,idPersonne=? where idPersonne= ? and idAdresse=?;";
	
	 /**
		 * Requête pour récupérer toutes les adresses possédant un id égal à celles
		 * de l'id d'une personne donnée
		 * 
		 */
	 private static final String reqFindAdresseByIdPersonne = "select * from Adresse where idPersonne = ?;";

	/** Stocke une <code>Adresse</code> dans la base de données
	 * @param <code>Adresse</code> a stocker
	 * @param <code>Int</code> l'id de la <code>Personne</code>
	 * @param <code>Connection</code> a la base de données
     * @return un <code>int</code>. */
	public static int storeAdresse(Adresse uneAdresse, int idPersonne, Connection c) throws CabinetTechniqueException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode storeAdresse de AdresseDAOJDBC");
		}
		
		PreparedStatement pst = null;
	    int rs = 0;


        try {
            pst = c.prepareStatement(reqInsertAdresse);    
            pst.setString(1, uneAdresse.getNumero());
            pst.setString(2, uneAdresse.getRue());
            pst.setString(3, uneAdresse.getVoie());
            pst.setString(4, uneAdresse.getBatiment());
            pst.setString(5, uneAdresse.getCodePostal());
            pst.setString(6, uneAdresse.getVille());
            pst.setString(7, uneAdresse.getPays());
            pst.setInt(8, idPersonne);

			rs = pst.executeUpdate();
        }catch(SQLException sqle){
	        System.out.println("sql");
	        sqle.printStackTrace();
	        throw new CabinetTechniqueException("Pb dans storeAdresse : "+ sqle.getMessage());
		}catch(Exception e){
	        System.out.println("e");
	        e.printStackTrace();
	        throw new CabinetTechniqueException("Pb dans storeAdresse : "+ e.getMessage());
	       }
	                    

		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode storeAdresse de AdresseDAOJDBC");
		}
	    
		
		return 0;
	}
	
	/** Retourne une <code>Adresse</code> d'une personne provenant de la base de données
	 * @param <code>Integer</code> l'id de la <code>Personne</code>
	 * @param <code>Connection</code> a la base de données
	 * @throws CabinetTechniqueException
     * @return un <code>int</code>. */
	public static Adresse findAdresseByIdPersonne(Integer idPersonne, Connection c) throws CabinetTechniqueException 
	{
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode findAdresseByIdPersonne de AdresseDAOJDBC");
		}
	    Adresse a = new Adresse();
	    PreparedStatement pst = null;
	    ResultSet rs = null;    
	    try{                
	        pst = c.prepareStatement(reqFindAdresseByIdPersonne);    
	        pst.setInt(1,idPersonne.intValue());
	        rs = pst.executeQuery();                
	        if (rs.next()){
	            a  = new Adresse(
	            rs.getString("numero"),
	            rs.getString("rue"),
	            rs.getString("voie"),
	            rs.getString("batiment"),
	            rs.getString("codePostal"),
	            rs.getString("ville"),
	            rs.getString("pays") );                    
	        a.setIdAdresse(new Integer(rs.getInt("idAdresse")));
	                
	        }
	    }catch(SQLException sqle){
	        System.out.println("sql");
	        sqle.printStackTrace();
	        throw new CabinetTechniqueException("Pb dans findAdresseByIdPersonne : "+ sqle.getMessage());
		}catch(Exception e){
	        System.out.println("e");
	        e.printStackTrace();
	        throw new CabinetTechniqueException("Pb dans findAdresseByIdPersonne : "+ e.getMessage());
	       }
	    
	    finally{
	        try {
	        if (rs!=null)   {   rs.close();}
	        if (pst!=null)    {  pst.close();}
	           }catch (Exception e){
	            e.printStackTrace();
	        }
	    }
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode findAdresseByIdPersonne de AdresseDAOJDBC");
		}
	    return a;
	}
	
	/** Supprime une <code>Adresse</code> de la base de données
	 * @param <code>int</code> l'id de l'<code>Adresse</code>
	 * @param <code>Connection</code> a la base de données
     */
	public static void delAdresse(int id, Connection c)
			throws CabinetMedicalException, SQLException {
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode delAdresse de AdresseDAOJDBC");
		}
		PreparedStatement ps = null;

		try {
			ps = c.prepareStatement("DELETE FROM adresse WHERE idPersonne = ?;");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode delAdresse de AdresseDAOJDBC");
		}	
	}

}
