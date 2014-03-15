package com.iut.cabinet.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Classe proposant des fonctions utiles pour le traitement des dates
 *
 * @author Jimmy Jouannaud
 */
@SuppressWarnings("serial")
public class DateUtil extends SimpleDateFormat {

//////////////////////////////////////////////////////////////////////////////// attributs

	/**
	* Format par défaut
	*/
	public static final String FORMAT_DEFAUT = "yyyy-MM-dd";

	/**
	* Format par défaut inverse
	*/
	public static final String FORMAT_DEFAUT_REVERSE = "dd-MM-yyyy";
  
	/**
	* Format français par défaut
	*/
	public static final String FRENCH_DEFAUT = "dd/MM/yyyy";

	/**
	* Format français par défaut avec heure et minute
	*/
	public static final String FRENCH_DEFAUT_HH_MM = "dd/MM/yyyy HH:mm";

	/**
	* Format français par défaut avec heure et minute et seconde
	*/
	public static final String FRENCH_DEFAUT_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
  
	/**
	* Format français jour/mois
	*/
	public static final String FRENCH2_DEFAUT = "dd/MM";

	/**
	* Format du type 7 janvier 2005
	*/
	public static final String FORMAT_ENTIER = "d MMMM yyyy";

	/**
	* Format du type janvier 2005
	*/
	public static final String FORMAT_ENTIER_SANS_JOUR = "MMMM yyyy";

	/**
	* Format complet du type vendredi 7 janvier 2005
	*/
	public static final String FORMAT_COMPLET = "EEEE d MMMM yyyy";
  
  /**
  * Format français sans /
	*/
	public static final String FRENCH_COMPACT = "ddMMyyyy";
  

//////////////////////////////////////////////////////////////////////////////// toString

	/**
	 * Retourne la date (java.sql.Date) en chaine de caractères
	 * sous la forme : "dd/mm/yyyy"
	 *
	 * @param date
	 * @return String
	 */
	public static String toString(java.sql.Date date) {
		String s = null;
		if (date != null) {
			String tab[] = date.toString().split("-");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(tab[2]);
			stringBuilder.append("/");
			stringBuilder.append(tab[1]);
			stringBuilder.append("/");
			stringBuilder.append(tab[0]);
			s= stringBuilder.toString();
		}
		return s;
	}

//////////////////////////////////////////////////////////////////////////////// toStr

  /**
   * Retourne une date sous la forme d'une chaine de caractères
   * avec un format personnalisé
	*
   * @param format format à appliquer
   * @param dt date à transformer
   * @return String date formatée ou null si erreur
   * @throws NullPointerException si dt ou format est null
   */
	public static String toStr(java.util.Date dt, String format) {
		String ret = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		ret = sdf.format(dt);
		if (DateUtil.toDate(ret,format) == null) {
			ret = null;
		}
		return ret;
	}

//////////////////////////////////////////////////////////////////////////////// toStr

	/**
	* Retourne une date sous la forme d'une chaine de caractères
	* avec le format par defaut
	*
	* @param dt date à transformer
	* @return String date au format par défaut ou null si erreur
	* @throws NullPointerException si dt est null
	*/
	public static String toStr(java.util.Date dt) {
		return DateUtil.toStr(dt, FORMAT_DEFAUT);
	}

//////////////////////////////////////////////////////////////////////////////// toStr

  /**
   * Permet de formater une date sous forme chaine de caractères vers une autre
   * date sous forme chaine de carcatères avec un format personnalisé
	*
   * @param dt 				date au format String à transformer
   * @param formatDepart 	format de départ
   * @param formatArrivee 	format d'arrivée
   * @return String 		date au format personnalisé ou null si erreur
   * @throws NullPointerException si dt ou formatDepart ou formatArrivee est null
   */
	public static String toStr(String dt, String formatDepart ,String formatArrivee) {
		return DateUtil.toStr(DateUtil.toDate(dt,formatDepart), formatArrivee);
	}

//////////////////////////////////////////////////////////////////////////////// toDate

  /**
   * Permet de transformer une date chaine de caractères avec une certain format
   * en un objet de type java.sql.Date
	*
   * @param format format de la date à transformer
   * @param sdt date sous forme chaine de caractères
   * @return java.sql.Date date transformée ou null si erreur
   * @throws NullPointerException si sdt ou format est null
   */
	public static java.sql.Date toDate(String sdt, String format) {
		java.sql.Date dt = null;
		if (!sdt.equals(format)) {
			SimpleDateFormat sdf = new SimpleDateFormat (format);
			sdf.setLenient(false);
			ParsePosition pp = new ParsePosition(0);
			java.util.Date d = sdf.parse(sdt, pp);
			if (d != null) {
				dt = new java.sql.Date(d.getTime());
			}
		}
		return dt;
	}

//////////////////////////////////////////////////////////////////////////////// toDate

  /**
   * Permet de transformer une date chaine de caractères ayant le format par défaut
   * en un objet de type java.sql.Date
	*
   * @return java.sql.Date date transformée ou null si erreur
   * @param sdt date sous forme chaine de caractères
   * @throws NullPointerException si sdt est null
   */
	public static java.sql.Date toDate(String sdt) {
		return DateUtil.toDate(sdt, FORMAT_DEFAUT);
	}

//////////////////////////////////////////////////////////////////////////////// currentDate

  /**
   * Retourner la date du jour sous forme de chaine de caractères
   * avec un format personnalisé
	*
   * @param format format à appliquer
   * @return String date du jour ou null si erreur
   * @throws NullPointerException si format est null
   */
	public static String currentDate(String format) {
		java.util.GregorianCalendar unGregorianCalendar = null;
		unGregorianCalendar = new java.util.GregorianCalendar();
		return DateUtil.toStr(unGregorianCalendar.getTime(), format);
	}

//////////////////////////////////////////////////////////////////////////////// sysdate

  /**
   * Retourne un objet java.sql.Date contenant la date du jour
	*
   * @return java.sql.Date date du jour
   */
	public static java.sql.Date sysdate() {
		return new java.sql.Date(new java.util.Date().getTime());
	}

//////////////////////////////////////////////////////////////////////////////// currentDate

  /**
   * Retourne la date du jour sous forme de chaine de caractères
   * avec le format par défaut
	 *
   * @return String date du jour au format chaine de caractères
   */
	public static String currentDate() {
		return DateUtil.currentDate(FORMAT_DEFAUT);
	}

//////////////////////////////////////////////////////////////////////////////// isDate

  /**
   * Permet de savoir si la chaine de caractères passée en paramètre est bien
   * une date en regard d'un certain format
	*
   * @param format format personnalisé d'une date
   * @param sdt chaine de caractères à analyser
   * @return boolean vrai si la chaine est une date conforme au format date spécifié
   * @throws NullPointerException si sdt ou format est null
   */
	public static boolean isDate(String sdt, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat (format);
		sdf.setLenient(false);
		ParsePosition pp = new ParsePosition(0);
		java.util.Date d = sdf.parse(sdt,pp);
		return (d != null) && (!format.equals(sdt));
	}

//////////////////////////////////////////////////////////////////////////////// isDate

  /**
   * Permet de savoir si la chaine de caractères passée en paramètre est bien
   * une date au format par défaut
	*
   * @param sdt chaine de carctère à analyser
   * @return boolean vrai si la chaine est une date conforme au format par défaut
   * @throws NullPointerException si sdt est null
   */
	public static boolean isDate(String sdt) {
		return DateUtil.isDate(sdt, FORMAT_DEFAUT);
	}

//////////////////////////////////////////////////////////////////////////////// add

  /**
   * Ajoute un nombre n de jour/mois/année à une date
	*
   * @param dt date de base
   * @param field champ (jour/mois/année) à ajouter
   * @param value nombre à ajouter
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	private static java.sql.Date add(java.sql.Date dt, int field, int value) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dt);
		calendar.add(field, value);
		return new java.sql.Date ((calendar.getTime()).getTime());
	}

//////////////////////////////////////////////////////////////////////////////// addMonth

  /**
   * Ajoute un nombre n de mois à une date
	*
   * @param value nombre de mois à ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addMonth(java.sql.Date dt, int value) {
		return add(dt,Calendar.MONTH, value);
	}

//////////////////////////////////////////////////////////////////////////////// addYear

  /**
   * Ajoute un nombre n d'années à une date
	*
   * @param value nombre d'années à ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addYear(java.sql.Date dt, int value) {
		return add(dt, Calendar.YEAR, value);
	}

//////////////////////////////////////////////////////////////////////////////// addDay

  /**
   * Ajoute un nombre n de jours à une date
	*
   * @param value nombre de jours à ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addDay(java.sql.Date dt, int value) {
		return add(dt, Calendar.DATE, value);
	}

//////////////////////////////////////////////////////////////////////////////// addMinute

  /**
   * Ajoute un nombre n de minutes à une date (BF).
	*
   * @param value nombre de minutes à ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addMinute(java.sql.Date dt, int value) {
		return add(dt,Calendar.MINUTE, value);
	}

//////////////////////////////////////////////////////////////////////////////// addSeconde

  /**
   * Ajoute un nombre n de secondes à une date 
	*
   * @param value nombre de secondes à ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addSeconde(java.sql.Date dt, int value) {
		return add(dt,Calendar.SECOND, value);
	}
  
//////////////////////////////////////////////////////////////////////////////// getDayOfWeek

  /**
   * Retourne le jour de la semaine pour une date donnée
 	 *
   * @param dt date donnée
   * @return int jour de la semaine (lundi, mardi,...)
   * @throws NullPointerException si dt est null
   */
	public static int getDayOfWeek(java.sql.Date dt) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dt);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
  
//////////////////////////////////////////////////////////////////////////////// getDayOfWeek

  /**
   * Renvoit true si les dates sont égales, les heures, minutes et secondes sont ignorées
   * 
   * @return boolean true si égalité, false sinon
   * @param dt  date comparer avec dt2
   * @param dt2 date à comparer avec dt
   */
	public static boolean equalsDate(java.sql.Date dt, java.sql.Date dt2) {
    String sdt = DateUtil.toStr( dt, DateUtil.FRENCH_DEFAUT);
    String sdt2 = DateUtil.toStr( dt2, DateUtil.FRENCH_DEFAUT);  
            
		return sdt.equalsIgnoreCase(sdt2);
	}
  
  /**
   * Evaluation du décalage horaire entre France et GMT
   */
   public static long getFrenchTimeOffset(){
     return TimeZone.getTimeZone("MET").getOffset(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime().getTime());
   }
        
        
        

}
