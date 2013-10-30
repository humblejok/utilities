/**
 * Title           : $Workfile: DateUtil.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 3/17/09 6:39p $
 * By              : $Author: Als $
 * Version number  : $Revision: 25 $
 *
 * $History: DateUtil.java $
 * 
 * *****************  Version 25  *****************
 * User: Als          Date: 3/17/09    Time: 6:39p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/date
 * Retro-Propagation
 * 
 * *****************  Version 24  *****************
 * User: Sdj          Date: 3/17/09    Time: 3:36p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/date
 * Next Week Date
 * 
 * *****************  Version 23  *****************
 * User: Als          Date: 2/25/09    Time: 3:48p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/date
 * Retro-Propagation
 * 
 * *****************  Version 22  *****************
 * User: Sdj          Date: 2/13/09    Time: 1:57p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/date
 * Track TreeTable
 * 
 * *****************  Version 21  *****************
 * User: Als          Date: 10/08/07   Time: 9:25
 * Updated in $/Current/Projects/utilities/src/com/eim/util/date
 * Refactoring for Java 5
 * 
 * *****************  Version 20  *****************
 * User: Sdj          Date: 7.08.06    Time: 15:27
 * Updated in $/Current/Projects/utilities/src/com/eim/util/date
 * Bad audit comparison juste after a validation
 * 
 * *****************  Version 19  *****************
 * User: Als          Date: 24.07.06   Time: 14:15
 * Updated in $/Current/Projects/utilities/src/com/eim/util/date
 */
/**
 * Title           : $Workfile: DateUtil.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 3/17/09 6:39p $
 * By              : $Author: Als $
 * Version number  : $Revision: 25 $
 */
package com.eim.util.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Fonctions outils pour la manipulation des dates
 *
 * @author   jpf
 * @version  $Revision: 25 $
 */
public final class DateUtil {

	//~ Static fields/initializers ---------------------------------------------

	/** DOCUMENT ME! */
	public static final int DAYS_IN_YEAR = 365;

	/** DOCUMENT ME! */
	public static final int DAYS_IN_LEAP_YEAR = 366;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new DateUtil object.
	 */
	private DateUtil() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Ajoute x jours à une date
	 *
	 * @param   date  DOCUMENT ME!
	 *
	 * @return  la date plus un jour
	 */
	public static Date addOrRemoveDays(final Date date,final int days) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( date );
		gc.add( GregorianCalendar.DAY_OF_YEAR, days );
		return gc.getTime();
	}	
	
	/**
	 * Ajoute un jour à une date
	 *
	 * @param   date  DOCUMENT ME!
	 *
	 * @return  la date plus un jour
	 */
	public static Date addOneDay(final Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( date );
		gc.add( GregorianCalendar.DAY_OF_YEAR, 1 );
		return gc.getTime();
	}
	
	/**
	 * Ajoute un an à une date
	 *
	 * @param   date  DOCUMENT ME!
	 *
	 * @return  la date plus un jour
	 */
	public static Date addOneYear(final Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( date );
		gc.add( GregorianCalendar.YEAR, 1 );
		return gc.getTime();
	}

	/**
	 * supprime un an à une date
	 *
	 * @param   date  DOCUMENT ME!
	 *
	 * @return  la date plus un jour
	 */
	public static Date removeYears(final Date date, int nbYears) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( date );
		gc.add( GregorianCalendar.YEAR, -nbYears );
		return gc.getTime();
	}
	
	/**
	 * supprime un jour à une date
	 *
	 * @param   date  DOCUMENT ME!
	 *
	 * @return  la date moins un jour
	 */
	public static Date removeOneDay(final Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( date );
		gc.add( GregorianCalendar.DAY_OF_YEAR, -1 );
		return gc.getTime();
	}
	
	public static Date addOrRemoveMonths(final Date date,int months) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( date );
		gc.add( GregorianCalendar.MONTH, months );
		return gc.getTime();
	}
	
	public static Date addOrRemoveMonthsEOM(final Date date,int months) {
		int absMonth = Math.abs(months);
		Date dt = new Date();
		dt.setTime(date.getTime());
		for (int i=0;i<absMonth;i++) {
			if (months<0) {
				dt = DateUtil.getEndOfPreviousMonth(dt);
			} else {
				dt = DateUtil.getEndOfNextMonth(dt);
			}
		}
		return dt;
	}
	
	public static Date getBeginOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		while ((calendar.get(Calendar.MONTH)+1)%3!=1) {
			calendar.add(Calendar.MONTH, -1);
		}
		return DateUtil.getBeginOfMonth(calendar.getTime());
	}
	
	public static Date getEndOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		while ((calendar.get(Calendar.MONTH)+1)%3!=0) {
			calendar.add(Calendar.MONTH, +1);
		}
		return DateUtil.getEndOfMonth(calendar.getTime());
	}
	
	/**
	 * Creates a date with no time set, only the date is set.
	 *
	 * @param   year
	 * @param   month
	 * @param   day
	 *
	 * @return
	 */
	public static Date createDate(int year, int month, int day) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set( Calendar.YEAR, year );
		calendar.set( Calendar.MONTH, month );
		calendar.set( Calendar.DAY_OF_MONTH, day );
		return calendar.getTime();
	} // end method createDate

	/**
	 * Retourne la date avec un changement d'année d'une valeur de Offset
	 *
	 * @param   dtpDate  Description of the Parameter
	 * @param   offset   DOCUMENT ME!
	 *
	 * @return  Date date correspondant à la date moins "offset" année
	 */
	public static Date dateAtYearOffset(final Date dtpDate, final int offset) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.setLenient( false );
		cal.add( Calendar.YEAR, offset );
		return cal.getTime();
	} // end method dateAtYearOffset

	/**
	 * DOCUMENT ME!
	 *
	 * @param   date  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public static Date roundToDay(final Date date) {
		final Calendar c = Calendar.getInstance();
		c.setTime( date );
		c.set( Calendar.MILLISECOND, 0 );
		c.set( Calendar.SECOND, 0 );
		c.set( Calendar.MINUTE, 0 );
		c.set( Calendar.HOUR_OF_DAY, 0 );
		return c.getTime();
	} // end method roundToDay

	/**
	 * 
	 * Given a date as long value, it rounds it to the lowest closest second
	 * 
	 * @param date Date in long format
	 * @return
	 */
	public static long roundToSecond(final long date) {
		return date - (date % 1000);
	}
	
	
	/**
	 * @return
	 */
	public static Date today() {
		return roundToDay( new Date() );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param  args  Add comments
	 */
	public void main(final String[] args) {
		final int year = 2004;
		try {
			GregorianCalendar gc	  = new GregorianCalendar( year, Calendar.JANUARY, 11 );
			Date			  dMaDate = gc.getTime();
			System.out.println( dMaDate + " " + DateUtil.getEndOfWeek( dMaDate ) );
			GregorianCalendar gc2	   = new GregorianCalendar( year, Calendar.JANUARY, 8 );
			Date			  dMaDate2 = gc2.getTime();
			System.out.println( dMaDate2 + " " + DateUtil.getEndOfWeek( dMaDate2 ) );
			//
			System.out.println( DateUtil.getDifferenceWeeksNumber( dMaDate, dMaDate2 ) );
			//
			// dMaDate= new Date(2003-1900,04-1,8); System.out.println( dMaDate + " " + DateUtil.getEndOfWeek( dMaDate ));
			//
			// dMaDate= new Date(2002-1900,12-1,30); System.out.println( dMaDate + " " + DateUtil.getEndOfWeek( dMaDate ));
		} catch(Exception e) {
			e.printStackTrace();
			System.exit( 1 );
		}
		System.exit( 0 );
	} // end method main

	public static Date getFirstDate(final Date d1, final Date d2){
		if(d1==null) return d2;
		if(d2==null) return d1;
		if (d1.before(d2)) {
			return d1;
		} else {
			return d2;
		}
	}
	
	public static Date getLastDate(final Date d1, final Date d2){
		if(d1==null) return d2;
		if(d2==null) return d1;
		if (d1.before(d2)) {
			return d2;
		} else {
			return d1;
		}
	}
	
	/**
	 * Retourne la différence en nombre de jours entre deux dates
	 *
	 * @param   d1  DOCUMENT ME!
	 * @param   d2  DOCUMENT ME!
	 *
	 * @return  0 si les dates sont du même jour n>0 si D2>D1, n nb de jours de différence n <0 si D2<D1, n nb de jours de différence
	 */
	public static long getDifferenceDaysNumber(final Date d1, final Date d2) {
		GregorianCalendar c1original = new GregorianCalendar();
		c1original.setTime( d1 );
		GregorianCalendar c2original = new GregorianCalendar();
		c2original.setTime( d2 );
		GregorianCalendar c2;
		GregorianCalendar c1;
		if(c2original.before( c1original )) {
			// On inverse les dates
			c2 = c1original;
			c1 = c2original;
		} else {
			c2 = c2original;
			c1 = c1original;
		}
		int nYearD1     = c1.get( Calendar.YEAR );
		int nYearD2     = c2.get( Calendar.YEAR );
		int nNBDaysD1   = c1.get( Calendar.DAY_OF_YEAR );
		int nNBDaysD2   = c2.get( Calendar.DAY_OF_YEAR );
		int nNbDiffDays = 0;
		if(nYearD2>nYearD1) {
			// Gestion des années bissextiles
			if(c1.isLeapYear( nYearD1 )) {
				nNbDiffDays = DAYS_IN_LEAP_YEAR - nNBDaysD1;
			} else {
				nNbDiffDays = DAYS_IN_YEAR - nNBDaysD1;
			}
			nYearD1++;
			while((nYearD2 - nYearD1)!=0) {
				// parcours des années intervalles
				if(c1.isLeapYear( nYearD1 )) {
					nNbDiffDays += DAYS_IN_LEAP_YEAR;
				} else {
					nNbDiffDays += DAYS_IN_YEAR;
				}
				nYearD1++;
			} // end while
			nNbDiffDays += nNBDaysD2;
		} else {
			// Les deux années sont égales c'est plus simple
			nNbDiffDays = nNBDaysD2 - nNBDaysD1;
		}
		if(c2original.before( c1original )) {
			return -nNbDiffDays;
		} else {
			return nNbDiffDays;
		}
	} // end method getDifferenceDaysNumber

	/**
	 * Retourne la différence en nombre de mois entre deux dates
	 *
	 * @param   d1  Date 1
	 * @param   d2  Date 2
	 *
	 * @return  0 si les dates sont du même mois n>0 si D2>D1, n nb de mois de différence n <0 si D2<D1, n nb de mois de différence
	 */
	public static int getDifferenceMonthsNumber(final Date d1, final Date d2) {
		final Calendar c1 = Calendar.getInstance();
		c1.setTime( d1 );
		final Calendar c2 = Calendar.getInstance();
		c2.setTime( d2 );
		final int nNBMonthsD1 = (c1.get( Calendar.YEAR ) * 12) + c1.get( Calendar.MONTH );
		final int nNBMonthsD2 = (c2.get( Calendar.YEAR ) * 12) + c2.get( Calendar.MONTH );
		return nNBMonthsD2 - nNBMonthsD1;
	} // end method getDifferenceMonthsNumber

	/**
	 * Retourne l'écart en nombre de semaines entre deux dates
	 *
	 * @param   d1  DOCUMENT ME!
	 * @param   d2  DOCUMENT ME!
	 *
	 * @return  0 si les dates sont de la même semaine n>0 si D2>D1, n nb de semaines de différence n<0 si D2 <D1, n nb de semaines de différence
	 */
	public static long getDifferenceWeeksNumber(final Date d1, final Date d2) {
		final GregorianCalendar c1original = new GregorianCalendar();
		c1original.setTime( d1 );
		final GregorianCalendar c2original = new GregorianCalendar();
		c2original.setTime( d2 );
		final GregorianCalendar c2;
		final GregorianCalendar c1;
		if(c2original.before( c1original )) {
			// On inverse les dates
			c2 = c1original;
			c1 = c2original;
		} else {
			c2 = c2original;
			c1 = c1original;
		}
		int nYearD1		 = c1.get( Calendar.YEAR );
		int nYearD2		 = c2.get( Calendar.YEAR );
		int nNbDiffWeeks = 0;
		if((c1.get( Calendar.WEEK_OF_YEAR )==1) && (c1.get( Calendar.MONTH )==Calendar.DECEMBER)) {
			// on est en décembre mais il s'agit de la première semaine de l'année suivante
			nYearD1++;
		}
		if(nYearD2>nYearD1) {
			nNbDiffWeeks = c1.getActualMaximum( Calendar.WEEK_OF_YEAR ) - c1.get( Calendar.WEEK_OF_YEAR );
			nYearD1++;
			while((nYearD2 - nYearD1)!=0) {
				// parcours des années intervalles
				nNbDiffWeeks = nNbDiffWeeks + c1.getActualMaximum( Calendar.WEEK_OF_YEAR );
				nYearD1++;
			}
			nNbDiffWeeks = nNbDiffWeeks + c2.get( Calendar.WEEK_OF_YEAR );
		} else {
			// Les deux années sont égales c'est plus simple
			nNbDiffWeeks = c2.get( Calendar.WEEK_OF_YEAR ) - c1.get( Calendar.WEEK_OF_YEAR );
		}
		if(c2original.before( c1original )) {
			return -nNbDiffWeeks;
		} else {
			return nNbDiffWeeks;
		}
	} // end method getDifferenceWeeksNumber

	/**
	 * Retourne le debut du mois par rapport a une date courante
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date de fin du mois
	 */
	public static Date getBeginOfMonth(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.set( cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ), cal.getActualMinimum( Calendar.DAY_OF_MONTH ), cal.get( Calendar.HOUR ),
				 cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		return cal.getTime();
	} // end method getEndOfMonth
	
	
	/**
	 * Retourne la fin du mois par rapport a une date courante
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date de fin du mois
	 */
	public static Date getEndOfMonth(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.set( cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ), cal.getActualMaximum( Calendar.DAY_OF_MONTH ), cal.get( Calendar.HOUR ),
				 cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		return cal.getTime();
	} // end method getEndOfMonth

	/**
	 * Retourne la fin du mois suivant par rapport a une date
	 *
	 * @param   dtpDate
	 *
	 * @return  Date date de fin du mois suivant
	 */
	public static Date getEndOfNextMonth(final Date dtpDate) {
		return getEndOfMonth( addOneDay( getEndOfMonth( dtpDate ) ) );
	}
	
	/**
	 * Retourne la fin de la semaine suivante par rapport a une date
	 *
	 * @param   dtpDate
	 *
	 * @return  Date date de fin du mois suivant
	 */
	public static Date getEndOfNextWeek(final Date dtpDate) {
		return getEndOfWeek( addOneDay( getEndOfWeek( dtpDate ) ) );
	}

	/**
	 * Retourne la fin du mois par rapport a une date courante
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date de fin du mois
	 */
	public static Date getEndOfPreviousMonth(final Date dtpDate) {
		return getPreviousDay( getStartOfMonth( dtpDate ) );
	}

	/**
	 * Retourne la fin de la semaine par rapport a une date courante
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date de fin du mois
	 */
	public static Date getEndOfPreviousWeek(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		while(cal.get( Calendar.DAY_OF_WEEK )!=Calendar.FRIDAY) {
			cal.add( Calendar.DATE, 1 );
		}
		cal.add( Calendar.DATE, -7 );
		return cal.getTime();
	} // end method getEndOfPreviousWeek

	/**
	 * Retourne la debut de la semaine par rapport a une date courante
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date de fin du mois
	 */
	public static Date getStartOfPreviousWeek(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		while(cal.get( Calendar.DAY_OF_WEEK )!=Calendar.MONDAY) {
			cal.add( Calendar.DATE, -1 );
		}
		cal.add( Calendar.DATE, -7 );
		return cal.getTime();
	} // end method getStartOfPreviousWeek
	
	/**
	 * Retourne la fin de la semaine par rapport a une date courante
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date de fin du mois
	 */
	public static Date getEndOfWeek(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		while(cal.get( Calendar.DAY_OF_WEEK )!=Calendar.FRIDAY) {
			cal.add( Calendar.DATE, 1 );
		}
		return cal.getTime();
	} // end method getEndOfWeek

	/**
	 * DOCUMENT ME!
	 *
	 * @param   dtpDate  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public static Date getBeginOfYear(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.set( cal.get( Calendar.YEAR ), cal.getActualMinimum( Calendar.MONTH ), cal.get( Calendar.DAY_OF_MONTH ), cal.get( Calendar.HOUR ),
				 cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		cal.set( cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ), cal.getActualMinimum( Calendar.DAY_OF_MONTH ), cal.get( Calendar.HOUR ),
				 cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		return cal.getTime();
	} // end method getEndOfYear
	
	/**
	 * Get start of day for given date
	 * 
	 * @param dtpDate the date from which to compute
	 * @return a date instance with time 0h00.00
	 */
	public static Date getBeginOfDay(final Date dtpDate) {
	    	final Calendar cal = Calendar.getInstance();
	    	cal.setTime(dtpDate);
	    	cal.set(Calendar.HOUR, 0);
	    	cal.set(Calendar.MINUTE, 0);
	    	cal.set(Calendar.SECOND, 0);
	    	
	    	return cal.getTime();
	}
	
	/**
	 * DOCUMENT ME!
	 *
	 * @param   dtpDate  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public static Date getEndOfYear(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.set( cal.get( Calendar.YEAR ), cal.getActualMaximum( Calendar.MONTH ), cal.get( Calendar.DAY_OF_MONTH ), cal.get( Calendar.HOUR ),
				 cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		cal.set( cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ), cal.getActualMaximum( Calendar.DAY_OF_MONTH ), cal.get( Calendar.HOUR ),
				 cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		return cal.getTime();
	} // end method getEndOfYear
	
	/**
	 * Get end of day for given date
	 * 
	 * @param dtpDate the date from which to compute
	 * @return a date instance with time 23h59.59
	 */
	public static Date getEndOfDay(final Date dtpDate) {
	    	final Calendar cal = Calendar.getInstance();
	    	cal.setTime(dtpDate);
	    	cal.set(Calendar.HOUR, 23);
	    	cal.set(Calendar.MINUTE, 59);
	    	cal.set(Calendar.SECOND, 59);
	    	
	    	return cal.getTime();
	}

	/**
	 * Retourne le numéro du mois par rapport a une date courante
	 *
	 * @param   dtpDate  Une date
	 *
	 * @return  Le numéro du mois JANUARY = 1
	 */
	public static int getMonth(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		return cal.get( Calendar.MONTH ) + 1;
	}

	/**
	 * Retourne le jour précédent
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date correspondant au jour précédent
	 */
	public static Date getPreviousDay(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.setLenient( false );
		cal.add( Calendar.DAY_OF_MONTH, -1 );
		return cal.getTime();
	} // end method getPreviousDay

	/**
	 * Retourne la date moins 1 mois
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date correspondant à la date moins 1 mois
	 */
	public static Date getPreviousMonth(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.setLenient( false );
		cal.add( Calendar.MONTH, -1 );
		return cal.getTime();
	} // end method getPreviousMonth

	/**
	 * Builds a collection of dates starting from the from date and ending at the to date.
	 *
	 * @param   from          the date where to start
	 * @param   to            the date where to end
	 * @param   moveFromDate  if true, the start date is moved to the end of the month.
	 * @param   moveToDate    if true, the end date is moved to the end of the month.
	 *
	 * @return
	 */
	public static Collection<Date> getRangeOfDatesMonthly(Date from, Date to, boolean moveFromDate, boolean moveToDate) {
		Date	   startDate   = moveFromDate?getEndOfMonth( from ):from;
		Date	   endDate     = moveToDate?getEndOfMonth( to ):to;
		Collection<Date> dates	   = new ArrayList<Date>();
		Date	   currentDate = startDate;
		while(!currentDate.after( endDate )) {
			dates.add( currentDate );
			if((currentDate==startDate) && !currentDate.equals( getEndOfMonth( currentDate ) )) {
				currentDate = getEndOfMonth( currentDate );
			} else {
				currentDate = getEndOfNextMonth( currentDate );
			}
		} // end while
		return dates;
	} // end method getRangeOfDatesMonthly

	/**
	 * Retourne le début du mois par rapport a une date courante
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date de début du mois
	 */
	public static Date getStartOfMonth(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.set( cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ), cal.getActualMinimum( Calendar.DAY_OF_MONTH ), cal.get( Calendar.HOUR ),
				 cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		return cal.getTime();
	} // end method getStartOfMonth
	
	/**
	 * Retourne le début du mois par rapport a une date courante
	 *
	 * @param   dtpDate  Description of the Parameter
	 *
	 * @return  Date date de début du mois
	 */
	public static Date getMiddleOfMonth(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		cal.set( cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ), 15, cal.get( Calendar.HOUR ),
				 cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		return cal.getTime();
	} // end method getStartOfMonth

	/**
	 * DOCUMENT ME!
	 *
	 * @param   dtpDate  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public static int getYear(final Date dtpDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime( dtpDate );
		return cal.get( Calendar.YEAR );
	}

	public static boolean isAfterLastDayOfMonth(String newDay, String newMonth) {
		int day   = -1;
		int month = -1;
		try {
			day   = Integer.parseInt( newDay );
			month = Integer.parseInt( newMonth );
			// mois de fevrier
			if((month==2) && (day>29)) {
				return true;
			}
			// mois a 30 jours
			if(((month==4) || (month==6) || (month==9) || (month==11)) && (day>30)) {
				return true;
			}
		} catch(Exception e) {
			return false;
		}
		return false;
	} // end method isAfterLastDayOfMonth

	public static boolean isDateInRange(Date date, Date startDate, Date endDate, boolean inclusive) {
		if(inclusive) {
			return (date.after( startDate ) && date.before( endDate )) || date.equals( startDate ) || date.equals( endDate );
		} else {
			return date.after( startDate ) && date.before( endDate );
		}
	} // end method isDateInRange
	
	public static boolean isEOM(Date date){
		return roundToDay(date).equals(getEndOfMonth(date));
	}

	public static boolean isEOMBusinessDay(Date date) {
		Date eomBD = DateUtil.roundToDay(getEndOfMonth(date));
		if (DateUtil.isSundayDate(eomBD)) {
			eomBD = DateUtil.removeOneDay(eomBD);
		}
		if (DateUtil.isSaturdayDate(eomBD)) {
			eomBD = DateUtil.removeOneDay(eomBD);
		}
		
		return roundToDay(date).equals(eomBD);
	}
	
	public static boolean isMondayDate(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY;
	}
	
	public static boolean isTuesdayDate(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY;
	}
	
	public static boolean isWednesdayDate(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY;
	}
	
	public static boolean isThursdayDate(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY;
	}
	
	public static boolean isFridayDate(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY;
	}
	
	public static boolean isSaturdayDate(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY;
	}
	
	public static boolean isSundayDate(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY;
	}
	
	private static Pattern PERIOD_PATTERN = Pattern.compile("(Q[1-4]|H[1-2])");
	
	/**
	 * Compute dates from a given period (e.g Q1 2012 or H2 2015)
	 * @param period the given period matching Q[1-4]|H[1-2]
	 * @param year the given year
	 * @return Data[] with 2 dates: the start date and end date of the period
	 */
	public static Date[] computePeriod(String period, int year) {
		Date[] result = new Date[2];
		
		Matcher m = PERIOD_PATTERN.matcher(period);
		if (m.matches() == false)
			throw new IllegalArgumentException("Invalid period " + period);
		
		String periodType = m.group(1).substring(0, 1);
		int periodNum = Integer.valueOf(m.group(1).substring(1));
		
		int startMonth = 0, endDay = 0, endMonth = 0;
		if (periodType.equals("Q")) {
			switch (periodNum) {
				case 1:
					startMonth = 0; // 0 based
					endDay = 31;
					endMonth = 2; // 0 based
					break;
				case 2:
					startMonth = 3; // 0 based
					endDay = 30; 
					endMonth = 5; // 0 based
					break;
				case 3:
					startMonth = 6; // 0 based
					endDay = 30;
					endMonth = 8; // 0 based
					break;
				case 4:
					startMonth = 9; // 0 based
					endDay = 31;
					endMonth = 11; // 0 based
					break;
			}
		} else { // = "H"
			switch (periodNum) {
				case 1:
					startMonth = 0; // 0 based
					endDay = 30; 
					endMonth = 5; // 0 based
					break;
				case 2:
					startMonth = 6; // 0 based
					endDay = 31;
					endMonth = 11; // 0 based
					break;
			}
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, startMonth, 1, 0, 0, 0);
		result[0] = cal.getTime();
		cal.set(year, endMonth, endDay, 23, 59, 59);
		result[1] = cal.getTime();
		return result;
	}
	
	/**
	 * Get start and end dates for current quarter based on given date 
	 * 
	 * @param date the date from which to compute the quarter
	 * @return an array with 2 date instances: the first date and last date of the quarter
	 */
	public static Date[] getCurrentQuarterForDate(Date date) {
	    Date[] result = new Date[2];
	    
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    
	    int month = cal.get(Calendar.MONTH);
	    month = month / 3 * 3; // start month for date
	    
	    Calendar temp = Calendar.getInstance();
	    temp.set(cal.get(Calendar.YEAR), month, 1, 0, 0, 0);
	    temp.set(Calendar.MILLISECOND, 0);
	    result[0] = temp.getTime();
	    
	    Calendar temp2 = Calendar.getInstance();
	    temp2.set(Calendar.YEAR, temp.get(Calendar.YEAR));
	    temp2.set(Calendar.MONTH, temp.get(Calendar.MONTH) + 4); // get first day of next period
	    temp2.add(Calendar.MILLISECOND, -1); // then 1 second before (= last of current period)
	    result[1] = temp2.getTime();
	    
	    return result;
	}
	
} // end class DateUtil
