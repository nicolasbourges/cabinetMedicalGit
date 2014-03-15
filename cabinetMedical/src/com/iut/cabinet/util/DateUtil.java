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
	* Format par d�faut
	*/
	public static final String FORMAT_DEFAUT = "yyyy-MM-dd";

	/**
	* Format par d�faut inverse
	*/
	public static final String FORMAT_DEFAUT_REVERSE = "dd-MM-yyyy";
  
	/**
	* Format fran�ais par d�faut
	*/
	public static final String FRENCH_DEFAUT = "dd/MM/yyyy";

	/**
	* Format fran�ais par d�faut avec heure et minute
	*/
	public static final String FRENCH_DEFAUT_HH_MM = "dd/MM/yyyy HH:mm";

	/**
	* Format fran�ais par d�faut avec heure et minute et seconde
	*/
	public static final String FRENCH_DEFAUT_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
  
	/**
	* Format fran�ais jour/mois
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
  * Format fran�ais sans /
	*/
	public static final String FRENCH_COMPACT = "ddMMyyyy";
  

//////////////////////////////////////////////////////////////////////////////// toString

	/**
	 * Retourne la date (java.sql.Date) en chaine de caract�res
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
   * Retourne une date sous la forme d'une chaine de caract�res
   * avec un format personnalis�
	*
   * @param format format � appliquer
   * @param dt date � transformer
   * @return String date format�e ou null si erreur
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
	* Retourne une date sous la forme d'une chaine de caract�res
	* avec le format par defaut
	*
	* @param dt date � transformer
	* @return String date au format par d�faut ou null si erreur
	* @throws NullPointerException si dt est null
	*/
	public static String toStr(java.util.Date dt) {
		return DateUtil.toStr(dt, FORMAT_DEFAUT);
	}

//////////////////////////////////////////////////////////////////////////////// toStr

  /**
   * Permet de formater une date sous forme chaine de caract�res vers une autre
   * date sous forme chaine de carcat�res avec un format personnalis�
	*
   * @param dt 				date au format String � transformer
   * @param formatDepart 	format de d�part
   * @param formatArrivee 	format d'arriv�e
   * @return String 		date au format personnalis� ou null si erreur
   * @throws NullPointerException si dt ou formatDepart ou formatArrivee est null
   */
	public static String toStr(String dt, String formatDepart ,String formatArrivee) {
		return DateUtil.toStr(DateUtil.toDate(dt,formatDepart), formatArrivee);
	}

//////////////////////////////////////////////////////////////////////////////// toDate

  /**
   * Permet de transformer une date chaine de caract�res avec une certain format
   * en un objet de type java.sql.Date
	*
   * @param format format de la date � transformer
   * @param sdt date sous forme chaine de caract�res
   * @return java.sql.Date date transform�e ou null si erreur
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
   * Permet de transformer une date chaine de caract�res ayant le format par d�faut
   * en un objet de type java.sql.Date
	*
   * @return java.sql.Date date transform�e ou null si erreur
   * @param sdt date sous forme chaine de caract�res
   * @throws NullPointerException si sdt est null
   */
	public static java.sql.Date toDate(String sdt) {
		return DateUtil.toDate(sdt, FORMAT_DEFAUT);
	}

//////////////////////////////////////////////////////////////////////////////// currentDate

  /**
   * Retourner la date du jour sous forme de chaine de caract�res
   * avec un format personnalis�
	*
   * @param format format � appliquer
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
   * Retourne la date du jour sous forme de chaine de caract�res
   * avec le format par d�faut
	 *
   * @return String date du jour au format chaine de caract�res
   */
	public static String currentDate() {
		return DateUtil.currentDate(FORMAT_DEFAUT);
	}

//////////////////////////////////////////////////////////////////////////////// isDate

  /**
   * Permet de savoir si la chaine de caract�res pass�e en param�tre est bien
   * une date en regard d'un certain format
	*
   * @param format format personnalis� d'une date
   * @param sdt chaine de caract�res � analyser
   * @return boolean vrai si la chaine est une date conforme au format date sp�cifi�
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
   * Permet de savoir si la chaine de caract�res pass�e en param�tre est bien
   * une date au format par d�faut
	*
   * @param sdt chaine de carct�re � analyser
   * @return boolean vrai si la chaine est une date conforme au format par d�faut
   * @throws NullPointerException si sdt est null
   */
	public static boolean isDate(String sdt) {
		return DateUtil.isDate(sdt, FORMAT_DEFAUT);
	}

//////////////////////////////////////////////////////////////////////////////// add

  /**
   * Ajoute un nombre n de jour/mois/ann�e � une date
	*
   * @param dt date de base
   * @param field champ (jour/mois/ann�e) � ajouter
   * @param value nombre � ajouter
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
   * Ajoute un nombre n de mois � une date
	*
   * @param value nombre de mois � ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addMonth(java.sql.Date dt, int value) {
		return add(dt,Calendar.MONTH, value);
	}

//////////////////////////////////////////////////////////////////////////////// addYear

  /**
   * Ajoute un nombre n d'ann�es � une date
	*
   * @param value nombre d'ann�es � ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addYear(java.sql.Date dt, int value) {
		return add(dt, Calendar.YEAR, value);
	}

//////////////////////////////////////////////////////////////////////////////// addDay

  /**
   * Ajoute un nombre n de jours � une date
	*
   * @param value nombre de jours � ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addDay(java.sql.Date dt, int value) {
		return add(dt, Calendar.DATE, value);
	}

//////////////////////////////////////////////////////////////////////////////// addMinute

  /**
   * Ajoute un nombre n de minutes � une date (BF).
	*
   * @param value nombre de minutes � ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addMinute(java.sql.Date dt, int value) {
		return add(dt,Calendar.MINUTE, value);
	}

//////////////////////////////////////////////////////////////////////////////// addSeconde

  /**
   * Ajoute un nombre n de secondes � une date 
	*
   * @param value nombre de secondes � ajouter
   * @param dt date de base
   * @return java.sql.Date nouvelle date
   * @throws NullPointerException si dt est null
   */
	public static java.sql.Date addSeconde(java.sql.Date dt, int value) {
		return add(dt,Calendar.SECOND, value);
	}
  
//////////////////////////////////////////////////////////////////////////////// getDayOfWeek

  /**
   * Retourne le jour de la semaine pour une date donn�e
 	 *
   * @param dt date donn�e
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
   * Renvoit true si les dates sont �gales, les heures, minutes et secondes sont ignor�es
   * 
   * @return boolean true si �galit�, false sinon
   * @param dt  date comparer avec dt2
   * @param dt2 date � comparer avec dt
   */
	public static boolean equalsDate(java.sql.Date dt, java.sql.Date dt2) {
    String sdt = DateUtil.toStr( dt, DateUtil.FRENCH_DEFAUT);
    String sdt2 = DateUtil.toStr( dt2, DateUtil.FRENCH_DEFAUT);  
            
		return sdt.equalsIgnoreCase(sdt2);
	}
  
  /**
   * Evaluation du d�calage horaire entre France et GMT
   */
   public static long getFrenchTimeOffset(){
     return TimeZone.getTimeZone("MET").getOffset(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime().getTime());
   }
        
        
        

}
