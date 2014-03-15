/**
 * 
 */
package com.iut.cabinet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.iut.cabinet.application.GererPatientCtrl;
import com.iut.cabinet.metier.CabinetTechniqueException;

/**
 * Classe permettant de se connecter à la base de données
 * @author Nicolas Bourges
 *
 */
public class SimpleConnection {
	private static Logger logger = Logger.getLogger(GererPatientCtrl.class.getName());
 
 public static synchronized SimpleConnection getInstance() throws CabinetTechniqueException{
	if(logger.isDebugEnabled()){
		logger.debug("Entrée dans la méthode getInstance de SimpleConnection");
	}
	if(instance==null){
	  instance = new SimpleConnection();
  	}
	if(logger.isDebugEnabled()){
		logger.debug("Sortie de la méthode getInstance de SimpleConnection");
	}
	return instance;
 }
 
 private static SimpleConnection instance;
 
 private SimpleConnection() throws CabinetTechniqueException{
	if(logger.isDebugEnabled()){
		logger.debug("Entrée dans le constructeur de SimpleConnection");
	}
	String nomdriver = "sun.jdbc.odbc.JdbcOdbcDriver";
	try{
	   Class.forName(nomdriver);
	}catch(ClassNotFoundException e){
	   throw new CabinetTechniqueException("Erreur de Connection"+e.getMessage());
	}
	if(logger.isDebugEnabled()){
		logger.debug("Sortie du constructeur de SimpleConnection");
	}
 }
 
 @SuppressWarnings("finally")
public Connection getconnection()throws CabinetTechniqueException, SQLException{
  if(logger.isDebugEnabled()){
	logger.debug("Entrée dans la méthode getconnection de SimpleConnection");
  }
  String url = "jdbc:mysql://localhost/cabinetmedical";
  String login = "root";
  String password = "";
  Connection connexion = null;
  try{
   connexion = DriverManager.getConnection(url,login,password);
  }catch(SQLException e){
   throw new CabinetTechniqueException("Erreur de Connection à SQL"+e.getMessage());
  }finally{
	  if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode getconnection de SimpleConnection");
		  }
	  return connexion;
  }
  }
 
 public static void main(String args[]) {
	Connection co1 = null;
	try{
		co1 = SimpleConnection.getInstance().getconnection();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (CabinetTechniqueException e){
		logger.fatal("Déclenchement d'une exception technique dans le main de SimpleConnection");
		e.printStackTrace();
	}
	 System.out.println("-->Simple Connection: "+co1.toString());
 }
 
 }
