package com.iut.cabinet.metier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.iut.cabinet.metier.JAXB.AdresseType;
import com.iut.cabinet.metier.JAXB.HelperException;
import com.iut.cabinet.metier.JAXB.HelperPatient;
import com.iut.cabinet.metier.JAXB.ListePatientsType;
import com.iut.cabinet.metier.JAXB.ObjectFactory;
import com.iut.cabinet.metier.JAXB.PatientType;
import com.iut.cabinet.util.DateUtil;

public class PersonneDAOXML {

	
	public static Collection<Personne> findAllPersonne() throws CabinetTechniqueException 
	{
		Collection<Personne> listPers = new ArrayList<Personne>();

		///////////////////////////////////////////////////////////////////////////////////////////
		/// 1. Mise en place d'une opération de unmarshalling ( Désérialisation XML vers JAVA)
		///    en vue de la récupération d'un graphe d'objets JAVA instanciés à partir de classes annotées JAXB
		///////////////////////////////////////////////////////////////////////////////////////////
		ListePatientsType listePatientsJAXB=null;
		try {
			// 1. Création d'un JAXBContext
			JAXBContext jc = JAXBContext.newInstance("com.iut.cabinet.metier.JAXB");
						
			// 2. Creation d'un Unmarshaller
		     Unmarshaller unm = jc.createUnmarshaller();
		        
		     // 3. Lecture du flux XML 
		     // et construction du graphe d'objets Java annotées JAXB de type ListePatientsType
		    listePatientsJAXB = (ListePatientsType) unm.unmarshal(new FileInputStream("listePatients.xml"));
			}
		 catch (JAXBException e) {
			throw new CabinetTechniqueException("Probleme lors d'une operation de unmarshalling "+e.getMessage());
			}
		catch (IOException e) {// Exception nécessaire en raison du FileInpuStream
			throw new CabinetTechniqueException("Probleme fichier lors d'une operation de unmarshalling  : "+e.getMessage());
			}
		
		 
		///////////////////////////////////////////////////////////////////////////////////////////
		/// 2. Conversion du graphe d'objets JAVA instanciés à partir de classes annotées JAXB
		//   			  en graphe d'objets JAVA instanciés à partir de classes metier
		///////////////////////////////////////////////////////////////////////////////////////////
		if (listePatientsJAXB!=null) 
			{
			 Collection<PatientType> liste= listePatientsJAXB.getPatient();
			    for(PatientType unPatJAXB : liste){
			    	try {
						listPers.add(HelperPatient.toPatient(unPatJAXB));
					} catch (CabinetMedicalException e) {
						throw new CabinetTechniqueException("findAllPersonne de PersonneDAoFichierXML : conversion Collection PatientType (JAXB) en Collection Patient  : "+e.getMessage());
						
					} catch (HelperException e) {
						throw new CabinetTechniqueException("findAllPersonne de PersonneDAoFichierXML : conversion Collection PatientType (JAXB) en Collection Patient  : "+e.getMessage());
						
					}
			      }
			}
		
		////////////////////////////////
		/// renvoi de la Collection 
		////////////////////////////////
		return listPers;
	}// fin findAllPersonne

	
	
	
	
	//////////////////////////////////////////////
	// Méthode permettant la SERIALISATION
	public static void storeAllPersonne (Collection<Personne> uneListe) throws CabinetTechniqueException
	{

		///////////////////////////////////////////////////////////////////////////////////////////
		/// 1. Conversion du graphe d'objets JAVA instanciés à partir de classes métier
		//   			  en graphe d'objets JAVA instanciés à partir de classes annotées JAXB
		///////////////////////////////////////////////////////////////////////////////////////////
		
		ObjectFactory fabrique = new ObjectFactory();
		ListePatientsType listePatientsJAXB = fabrique.createListePatientsType();
		for(Personne pers : uneListe){
			Patient perso = (Patient) pers;
			PatientType pat = fabrique.createPatientType();
			AdresseType adrt = fabrique.createAdresseType();
			adrt.setBatiment(perso.getAdresse().getBatiment());
			adrt.setCodePostal(perso.getAdresse().getCodePostal());
			adrt.setNumero(perso.getAdresse().getNumero());
			adrt.setPays(perso.getAdresse().getPays());
			adrt.setRue(perso.getAdresse().getRue());
			adrt.setVille(perso.getAdresse().getVille());
			adrt.setVoie(perso.getAdresse().getVoie());
			pat.setAdresse(adrt);
			//date
			GregorianCalendar gCalendar = new GregorianCalendar();
			gCalendar.setTime(perso.getDateNaissance());
			try {
				pat.setDateNaissance(DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar));
			} catch (DatatypeConfigurationException e) {
				e.getMessage();
			}
			//fin date
			pat.setEmail(perso.getEmail());
			pat.setIdPersonne(perso.getIdPersonne());
			pat.setMedecinTraitant(perso.getMedecinTraitant());
			pat.setNir("151024610204325");
			pat.setNom(perso.getNom());
			pat.setPortable(perso.getPortable());
			pat.setPrenom(perso.getPrenom());
			if(perso.isMale()){
				pat.setSexe("Homme");
			}
			else{
				pat.setSexe("Femme");
			}
			pat.setTelephone(perso.getTelephone());
			listePatientsJAXB.getPatient().add(pat);
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////
		/// 2. Mise en place d'une opération de marshalling ( Sérialisation JAVA vers XML )
		///////////////////////////////////////////////////////////////////////////////////////////
		
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance("com.iut.cabinet.metier.JAXB");
			Marshaller marshaller = jc.createMarshaller();
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    marshaller.marshal(listePatientsJAXB, new FileOutputStream("listePatients.xml"));
		} catch (JAXBException e) {
			e.getMessage();
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		 
		} // fin storeAllPersonnes
	
	
	
	
	public static void main(String args[])
	{

		//////////////////////////
		/// Test storeAllPersonne
		//////////////////////////
		Collection<Personne> uneListe=new ArrayList<Personne>();
		
		Patient patient1=null;
		try {
			patient1=new Patient(1,"DUPONT","Julie",DateUtil.toDate("21/05/1960",DateUtil.FRENCH_DEFAUT),
			   false,"0555434355","0606060606","julie.dupont@tralala.fr",
			   new Adresse("15","avenue Jean Jaurès",null,null,"87000","Limoges","France"),
			   null,
			   "260058700112367", "LEDOC Paul");
		} catch (CabinetMedicalException e) {
			System.out.println(" PB création Patient 1... "+e.getMessage());
		}	
		
		Patient patient2=null;
		try {
			patient2=new Patient(2,"DURAND","Toto",DateUtil.toDate("25/12/1990",DateUtil.FRENCH_DEFAUT),
					true,"0555430000","0605040302","toto.durand@etu.unilim.fr",
			        new Adresse("185","avenue Albert Thomas",null,"Résidence La Borie - Batiment A","87085","Limoges","France"),
			        null,
			        "297112A10102401", "Children Rose");
		} catch (CabinetMedicalException e) {
			System.out.println(" PB création Patient 1... "+e.getMessage());
		}
	
		if (patient1!=null) uneListe.add(patient1);
		if (patient2!=null) uneListe.add(patient2);
			
		try {
			PersonneDAOXML.storeAllPersonne (uneListe);
		} catch (CabinetTechniqueException e) {
			System.out.println(" PB storeAllPersonne "+e.getMessage());
			e.printStackTrace();
			
		}
		System.out.println(" FIN Test storeAllPersonne ");
		System.out.println("------------------------");
		
		
		
		//////////////////////////
		/// Test Lister
		//////////////////////////
		try {
			Collection<Personne> maListe = PersonneDAOXML.findAllPersonne();
			
			for (Personne unePers : maListe)
				{
				System.out.println(unePers);
			    System.out.println("------------------------");
				}
		} catch (CabinetTechniqueException e) {
			System.out.println(" Erreur Test  findAllPersonne"+e.getMessage());
			
			e.printStackTrace();
		}
		System.out.println(" FIN Test findAllPersonne ");
		System.out.println("------------------------");
		
		System.out.println(" -- Fin Test PersonneDAOXML ");
		
	}
	
	
}// fin classe PersonneDAOXML
