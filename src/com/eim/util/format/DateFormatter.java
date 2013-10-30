/**
 * Title           : $Workfile: DateFormatter.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 11/27/07 3:01p $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 25 $
 *
 * $History: DateFormatter.java $
 * 
 * *****************  Version 25  *****************
 * User: Jpf          Date: 11/27/07   Time: 3:01p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/format
 * Ajout Monthly Report
 * 
 * *****************  Version 24  *****************
 * User: Sdj          Date: 12/09/07   Time: 16:51
 * Updated in $/Current/Projects/utilities/src/com/eim/util/format
 * Publish functionnality
 * 
 * *****************  Version 23  *****************
 * User: Sdj          Date: 1.11.05    Time: 17:13
 * Updated in $/Current/Projects/utilities/src/com/eim/util/format
 * 
 * *****************  Version 22  *****************
 * User: Sdj          Date: 31.10.05   Time: 13:41
 * Updated in $/Current/Projects/utilities/src/com/eim/util/format
 * Date conversion problem
 * 
 * *****************  Version 21  *****************
 * User: Ac           Date: 13.05.05   Time: 10:59
 * Updated in $/Current/Projects/utilities/src/com/eim/util/format
 * 
 * *****************  Version 20  *****************
 * User: Ac           Date: 10.05.05   Time: 16:28
 * Updated in $/Current/Projects/utilities/src/com/eim/util/format
 */
/**
 * Title           : $Workfile: DateFormatter.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 11/27/07 3:01p $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 25 $
 */
package com.eim.util.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Description of the Class
 *
 * @author  als
 */
public final class DateFormatter {

	//~ Static fields/initializers ---------------------------------------------

	/**
	 * 
	 */
	public static final String EF_DATE_FORMAT = "/";

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new DateFormatter object.
	 */
	private DateFormatter() {
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param   date  01/06
	 *
	 * @return  01 June
	 */
	public static String convertDateToEqualizationFactorDateFormat(Date date) {
		int		 day    = -1;
		int		 month  = -1;
		String   sDay   = null;
		String   sMonth = null;
		Calendar cal    = Calendar.getInstance();
		cal.setTime( date );
		day   = cal.get( Calendar.DAY_OF_MONTH );
		month = cal.get( Calendar.MONTH ) + 1;
		// On rajoute le 0 si plus petit que 10
		if((day>-1) && (day<10)) {
			sDay = "0" + day;
		} else {
			sDay = Integer.toString( day );
		}
		// On rajoute le 0 si plus petit que 10
		if((month>-1) && (month<10)) {
			sMonth = "0" + month;
		} else {
			sMonth = Integer.toString( month );
		}
		// on renvoi l'EqualizationFactorDateFormat
		if((sDay!=null) && (sDay.length()==2) && (sMonth!=null) && (sMonth.length()==2)) {
			return convertDayMonthToEqualizationFactorDateFormat( sDay, sMonth );
		}
		return null;
	} // end method convertDateToEqualizationFactorDateFormat

	/**
	 * @param   day    01
	 * @param   month  06
	 *
	 * @return  objet Date
	 */
	public static Date convertDayMonthToDate(String day, String month) {
		int dayOfMonth  = -1;
		int monthOfYear = -1;
		// On va mettre la date
		Calendar cal = Calendar.getInstance( Locale.US );
		try {
			dayOfMonth  = new Integer( day ).intValue();
			monthOfYear = new Integer( month ).intValue();
			cal.set( Calendar.DAY_OF_MONTH, dayOfMonth );
			cal.set( Calendar.MONTH, monthOfYear - 1 );
			return cal.getTime();
		} catch(Exception e) {
			return null;
		}
	} // end method convertDayMonthToDate

	/**
	 * @param   day    : 01/06
	 * @param   month
	 *
	 * @return  01 June
	 */
	public static String convertDayMonthToDisplayDate(String day, String month) {
		Date date = convertDayMonthToDate( day, month );
		if(date==null) {
			return "";
		}
		return new SimpleDateFormat( "dd MMM" ).format( date );
	} // end method convertDayMonthToDisplayDate

	
	public static String convertToLivelinkDate(Date date) {
		return new SimpleDateFormat("MMM-yy").format(date);
	}
	/**
	 * @param   day    : 01/06
	 * @param   month
	 *
	 * @return  01 June
	 */
	public static String convertDayMonthToEqualizationFactorDateFormat(String day, String month) {
		return month + EF_DATE_FORMAT + day;
	}

	/**
	 * @param   displayDate
	 *
	 * @return
	 */
	public static String convertDisplayDateToEqualizationFactorDateFormat(String displayDate) {
		return convertDateToEqualizationFactorDateFormat( stringToDateTime( displayDate ) );
	}

	/**
	 * @param   equalizationFactorDateFormat  01/06
	 *
	 * @return  01 June
	 */
	public static Date convertEqualizationFactorDateFormatToDate(String equalizationFactorDateFormat) {
		String day   = null;
		String month = null;
		if((equalizationFactorDateFormat!=null) && (equalizationFactorDateFormat.length()>0)) {
			month = equalizationFactorDateFormat.substring( 0, 2 );
			day   = equalizationFactorDateFormat.substring( 3, equalizationFactorDateFormat.length() );
			return convertDayMonthToDate( day, month );
		}
		return null;
	} // end method convertEqualizationFactorDateFormatToDate

	/**
	 * @param   equalizationFactorDateFormat  01/06
	 *
	 * @return  01 June
	 */
	public static String convertEqualizationFactorDateFormatToDisplayDate(String equalizationFactorDateFormat) {
		String day   = null;
		String month = null;
		if((equalizationFactorDateFormat!=null) && (equalizationFactorDateFormat.length()>0)) {
			month = equalizationFactorDateFormat.substring( 0, 2 );
			day   = equalizationFactorDateFormat.substring( 3, equalizationFactorDateFormat.length() );
			return convertDayMonthToDisplayDate( day, month );
		}
		return null;
	} // end method convertEqualizationFactorDateFormatToDisplayDate

	/**
	 * JavaDoc method comments
	 *
	 * @param  args  Add comments
	 */
	public static void main(String[] args) {
		System.out.println( "Locale: " + Locale.getDefault().getDisplayName() );
		Date now = GregorianCalendar.getInstance().getTime();
		System.out.println( "Test for : " + now.toString() );
		System.out.println( "toSting: " + DateFormatter.toString( now ) );
		System.out.println( "toUsSting: " + DateFormatter.toUsString( now ) );
		System.out.println( "toInputSting: " + DateFormatter.toInputString( now ) );
		System.out.println( "*toEuropeSting: " + DateFormatter.toEuropeString( now ) );
		System.out.println( "*toDateTimeSting: " + DateFormatter.toDateTimeString( now ) );
		System.out.println( "toGMTSting: " + DateFormatter.toGMTString( now ) );
		System.out.println( "*toSmallGMTSting: " + DateFormatter.toSmallGMTString( now ) );
		System.out.println( "getYearAsString: " + DateFormatter.getYearAsString( now ) );
		System.out.println( "*toLiteralSting: " + DateFormatter.toLiteralString( now ) );
		System.out.println( "toSmallSting: " + DateFormatter.toSmallString( now ) );
		System.out.println( "toMediumSting: " + DateFormatter.toMediumString( now ) );
		/*
		 *Locale: English (United States) Test for : Mon Dec 01 15:16:55 CET 2003 toSting: Dec 1, 2003 toUsSting: Dec 1, 2003 toInputSting: 12.01.2003
		 *toEuropeSting: 1 déc. 2003 *toDateTimeSting: Dec 1, 2003 3:16:55 PM toGMTSting: Dec 01, 2003 03:16:55 PM CET *toSmallGMTSting: Dec 01, 2003 03:16:55
		 * getYearAsString: 2003 *toLiteralSting: Dec 01 2003 toSmallSting: Dec-03 toMediumSting: Dec-2003
		 */
	} // end method main

	/**
	 * Description of the Method
	 *
	 * @param   sDate  Description of the Parameter
	 *
	 * @return  Description of the Return Value
	 */
	public static Date stringToDateTime(String sDate) {
		if(sDate==null) {
			return null;
		}
		try {
			return new SimpleDateFormat( "MMM dd, yyyy hh:mm:ss a z" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		try {
			return new SimpleDateFormat( "MMM dd, yyyy hh:mm:ss a" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		try {
			return new SimpleDateFormat( "MMM dd, yyyy" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		try {
			return new SimpleDateFormat( "MM.dd.yyyy" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		try {
			return new SimpleDateFormat( "MM/dd/yyyy" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		try {
			return new SimpleDateFormat( "MM-dd-yyyy" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		try {
			return new SimpleDateFormat( "yyyy-MM-dd" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		try {
			return new SimpleDateFormat( "mm.dd" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		// A weird case introduced by Aymeric De Tappie, his dates are in a different format
		try {
			return new SimpleDateFormat( "dd MMM", Locale.US ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		try {
			return new SimpleDateFormat( "dd MMM" ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		// On parse l'heure locale US
		try {
			return DateFormat.getDateInstance( DateFormat.MEDIUM, Locale.US ).parse( sDate );
		} catch(ParseException e) {
			// we by-pass this exception to try another format
		}
		return null;
	} // end method stringToDateTime

	/**
	 * converts a date to a string with the month, day, year, and time. exemple: Mar 22, 2002 9:58:01 AM
	 *
	 * @param       date  The date you want to format
	 *
	 * @return      The string representation of the param date.
	 *
	 * @deprecated
	 */
	public static String toDateTimeString(Date date) {
		if(date==null) {
			return "";
		}
		return DateFormat.getDateTimeInstance( DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.US ).format( date );
	}

	/**
	 * Description of the Method
	 *
	 * @param       date  Description of the Parameter
	 *
	 * @return      Description of the Return Value
	 *
	 * @deprecated
	 */
	public static String toEuropeString(Date date) {
		if(date==null) {
			return "";
		}
		return DateFormat.getDateInstance( DateFormat.MEDIUM, Locale.FRANCE ).format( date );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   date  Add comments
	 *
	 * @return  Add 
		comments
	 */
	public static String toGMTString(Date date) {
		if(date==null) {
			return "";
		} else {
			// SimpleDateFormat dateFormatter = new SimpleDateFormat( "MMMM dd yyyy hh:mm:ss aaa z",
			// Locale.US );            SimpleDateFormat dateFormatter = new SimpleDateFormat( "MMM dd,
			// yyyy hh:mm:ss a z", Locale.US );            dateFormatter.setTimeZone(
			// TimeZone.getTimeZone( "GMT+1" ) );
			//
			// return dateFormatter.format( date );
			return toString( "MMM dd, yyyy hh:mm:ss a z", date );
		} // end if
	} // end method toGMTString

	/**
	 * Description of the Method
	 *
	 * @param   date  Description of the Parameter
	 *
	 * @return  Description of the Return Value
	 */
	public static String toInputString(Date date) {
		if(date==null) {
			return "";
		}
		return new SimpleDateFormat( "MM.dd.yyyy" ).format( date );
	}
	
	public static String toDisplayString(Date date) {
		if(date==null) {
			return "";
		}
		return new SimpleDateFormat( "MMM dd, yyyy" ).format( date );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param       date  Add comments
	 *
	 * @return      Add comments
	 *
	 * @deprecated
	 */
	public static String toLiteralString(Date date) {
		if(date==null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance( TimeZone.getTimeZone( "GMT+1" ) );
			cal.setTime( date );
			SimpleDateFormat dateFormatter = new SimpleDateFormat( "MMM dd yyyy", Locale.US );
			return dateFormatter.format( date );
		}
	} // end method toLiteralString

	/**
	 * DOCUMENT ME!
	 *
	 * @param   date
	 *
	 * @return  Une date au format "MMM-yyyy"
	 */
	public static String toMediumString(Date date) {
		if(date==null) {
			return "";
		} else {
			return new SimpleDateFormat( "MMM-yyyy" ).format( date );
		}
	} // end method toMediumString

	/**
	 * JavaDoc method comments
	 *
	 * @param       date  Add comments
	 *
	 * @return      Add comments
	 *
	 * @deprecated
	 */
	public static String toSmallGMTString(Date date) {
		if(date==null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance( TimeZone.getTimeZone( "GMT+1" ) );
			cal.setTime( date );
			return new SimpleDateFormat( "MMM dd, yyyy hh:mm:ss", Locale.US ).format( cal.getTime() );
		}
	} // end method toSmallGMTString

	/**
	 * DOCUMENT ME!
	 *
	 * @param   date
	 *
	 * @return  Une date au format "MMM-yy"
	 */
	public static String toSmallString(Date date) {
		if(date==null) {
			return "";
		} else {
			return new SimpleDateFormat( "MMM-yy" ).format( date );
		}
	} // end method toSmallString

	/**
	 * @param   date
	 *
	 * @return  Une date au format "dd-MMM-yy"
	 */
	public static String toSmallStringFull(Date date) {
		if(date==null) {
			return "";
		} else {
			return new SimpleDateFormat( "dd-MMM-yy" ).format( date );
		}
	} // end method toSmallStringFull

	/**
	 * Description of the Method
	 *
	 * @param   date  Description of the Parameter
	 *
	 * @return  Description of the Return Value
	 */
	public static String toString(Date date) {
		return toUsString( date );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   format  Add comments
	 * @param   date    Add comments
	 *
	 * @return  Add comments
	 */
	public static String toString(String format, Date date) {
		if(date==null) {
			return "";
		} else {
			SimpleDateFormat dateFormatter = new SimpleDateFormat( format, Locale.US );
			return dateFormatter.format( date );
		}
	} // end method toString

	/**
	 * Description of the Method
	 *
	 * @param   date  Description of the Parameter
	 *
	 * @return  Description of the Return Value
	 */
	public static String toUsString(Date date) {
		if(date==null) {
			return "";
		}
		return DateFormat.getDateInstance( DateFormat.MEDIUM, Locale.US ).format( date );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   date
	 *
	 * @return  Une date au format "yyyy"
	 */
	public static String toYearAsString(Date date) {
		if(date==null) {
			return "";
		} else {
			return new SimpleDateFormat( "yyyy" ).format( date );
		}
	} // end method toYearAsString

	/**
	 * JavaDoc method comments
	 *
	 * @param   date  Add comments
	 *
	 * @return  Add comments
	 */
	public static String getMonthAsString(Date date) {
		if(date==null) {
			return "";
		} else {
			return new SimpleDateFormat( "MMM" ).format( date );
		}
	} // end method getMonthAsString

	
	/**
	 * @param   date 
	 *
	 * @return  Une date au format MMM YYYY exemple November 2007
	 */
	public static String toMonthYearAsString(Date date) {
		if(date==null) {
			return "";
		} else {
			return new SimpleDateFormat( "MMMMM yyyy" ).format( date );
		}
	} // end method getMonthAsString	
	
	/**
	 * JavaDoc method comments
	 *
	 * @param   date  Add comments
	 *
	 * @return  Add comments
	 */
	public static String getYearAsString(Date date) {
		if(date==null) {
			return "";
		} else {
			return new SimpleDateFormat( "yyyy" ).format( date );
		}
	} // end method getYearAsString
} // end class DateFormatter
