package com.iut.cabinet.metier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;
/**
 * Classe permettant la sérialisation/désérialisation d'une liste de personnes
 * @author Nicolas BOURGES
 */
public class PersonneDAOFichier implements Serializable {
	private static final long serialVersionUID=1L; /** le sérial version UID */
	private static Logger logger = Logger.getLogger(PersonneDAOFichier.class.getName());
	
	/**
	 * Méthode permettant la sérialisation d'une liste de personnes dans le fichier cabMedPersonne.dat
	 * @param uneListe la liste à sérialiser
	 */
	public static void storeAllPersonnes(Collection <Personne> uneListe){
		
		/*//Choix du Layout: format d'affichage
		PatternLayout layout = new PatternLayout("%d %-5p %c - %F:%L - %m%n");
		//Choix de l'Appender: sortie
		//Association du ConsoleAppender au logger de la classe
		ConsoleAppender stdout = new ConsoleAppender(layout);
		logger.addAppender(stdout);
		
		//Journalisation pour marquer l'entrée dans la méthode
		logger.debug("storeAllPersonnes : Entree");*/
		
		//Journalisation pour marquer l'entrée dans la méthode
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode storeAllPersonnes");
		}
		
		/*logger.trace("Test d'un affichage de log de niveau TRACE");
		logger.debug("Test d'un message de log de niveau DEBUG");
		logger.info("Test d'un message de log de niveau INFO");
		logger.warn("Test d'un message de log de niveau WARN");
		logger.error("Test d'un message de log de niveau ERROR");
		logger.fatal("Test d'un message de log de niveau FATAL");*/
		
		try{
			FileOutputStream fichier = new FileOutputStream("cabMedPersonne.data");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(uneListe);
			oos.close();
			fichier.close();
		}
		catch(IOException e){
			logger.fatal("storeAllPersonnes: erreur d'écriture dans le fichier" + e.getMessage());
			System.out.println("Erreur d'output!");
			System.out.println(e.getMessage());
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode storeAllPersonnes");
		}
		//Journalisation pour marquer la sortie de la méthode
	}
	
	/**
	 * Méthode permettant la désérialisation d'une liste de personnes depuis le fichier cabMedPersonne.dat
	 * @return la liste de personnes ou une liste vide si le fichier est vide ou introuvable
	 * @throws ClassNotFoundException
	 * @throws CabinetTechniqueException 
	 */

	@SuppressWarnings("unchecked")
	public static Collection<Personne> findAllPersonnes() throws ClassNotFoundException, CabinetTechniqueException{
		//Journalisation pour marquer l'entrée dans la méthode
		if(logger.isDebugEnabled()){
			logger.debug("Entrée dans la méthode findAllPersonnes");
		}
		
		FileInputStream fichier = null;
		ObjectInputStream ois = null;
		Collection<Personne> maListe = null;
		try{
			fichier = new FileInputStream("cabMedPersonne.data");
			ois = new ObjectInputStream(fichier);
			maListe = (Collection<Personne>) ois.readObject();
			if(maListe==null){
				maListe = Collections.emptyList();
			}
			ois.close();
			fichier.close();
		}
		catch(IOException e){
			File fic = new File("cabMedPersonne.data");
			if(fic.exists()){
				logger.fatal("findAllPersonnes : erreur de lecture dans le fichier"+ e.getMessage());
				throw new CabinetTechniqueException("Système en Erreur - Veuillez contacter l'administrateur");
			}
			else{
				try{
					creatFic();
				}catch(IOException e1){
					logger.fatal("findAllPersonnes : Erreur de lecture dans le fichier"+ e1.getMessage());
					throw new CabinetTechniqueException("Système en Erreur - Veuillez contacter l'administrateur");
				}
			}
		}
		catch(ClassNotFoundException e){
			logger.fatal("findAllPersonnes: erreur de classe " + e.getMessage());
			throw new CabinetTechniqueException("Système en erreur - Veuillez contacter l'administrateur");
		}
		finally{
			if(maListe==null){
				maListe = Collections.emptyList();
			}
		}
		//Journalisation pour marquer la sortie de la méthode
		if(logger.isDebugEnabled()){
			logger.debug("Sortie de la méthode findAllPersonnes");
		}
		return maListe;
	}
	
	/**
	 * Méthode qui crée le fichier cabMedPersonne.data si il n'existe pas
	 * @throws IOException
	 */
	private static void creatFic() throws IOException{
		  //Création du fichier
		  File fic = new File("cabMedPersonne.data");
		  fic.createNewFile();
		  
		  //Initialisation
		  Collection<Personne> c = new ArrayList<Personne>();
		  
		  FileOutputStream fos = new FileOutputStream("cabMedPersonne.data");
		  ObjectOutputStream ois = new ObjectOutputStream(fos);
		  ois.writeObject(c);
		  ois.close();
		  fos.close();
	}
}
