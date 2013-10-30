/**
 * Title           : $Workfile: StringFormatter.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 22.06.04 18:26 $
 * By              : $Author: Als $
 * Version number  : $Revision: 8 $
 */

package com.eim.util.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.text.DecimalFormat;


/**
 * Description of the Class
 *
 * @author  als
 */
public final class StringFormatter {

	//~ Static fields/initializers -------------------------------------------------------------------

	/** Description of the Field */
	public static final String EMPTY_STRING = "";

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new StringFormatter object.
	 */
	private StringFormatter() {
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param  args  DOCUMENT ME!
	 */
	public static void main( String[] args ) {
		System.out.println( StringFormatter.ratioToString( 0.255, 2 ) );
	}

	/**
	 * Description of the Method
	 *
	 * @param   s  Description of the Parameter
	 *
	 * @return  Description of the Return Value
	 */
	public static String nullToEmpty( String s ) {
		if( s == null ) {
			return EMPTY_STRING;
		} else {
			// s = s.trim();
			return s;
		}
	}

	/**
	 * Description of the Method
	 *
	 * @param   l  Description of the Parameter
	 *
	 * @return  Description of the Return Value
	 */
	public static String nullToEmpty( Long l ) {
		if( l == null ) {
			return EMPTY_STRING;
		} else {
			return l.toString();
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   d  percentage -1&lt;=d&lt;=1
	 *
	 * @return  an formated percentage, with 4 maximum fraction digits
	 */
	public static String percentageToString( double d ) {
		return percentageToString( d, 4 );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   d  percentage -1&lt;=d&lt;=1
	 *
	 * @return  an formated percentage, with 4 maximum fraction digits
	 */
	public static String percentageToString( Double d ) {
		return percentageToString( d.doubleValue() );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   d                       percentage -1&lt;=d&lt;=1
	 * @param   npMaximumFractionDigit  Maximum fraction digits
	 *
	 * @return  an formated percentage
	 */
	public static String percentageToString(
	  double d,
	  int    npMaximumFractionDigit ) {
		DecimalFormat decFormat = new DecimalFormat( "0.00" );
		decFormat.setMaximumFractionDigits( npMaximumFractionDigit );

		return decFormat.format( d * 100 );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   d                       percentage -1&lt;=d&lt;=1
	 * @param   npMaximumFractionDigit  Maximum fraction digits
	 *
	 * @return  an formated percentage
	 */
	public static String percentageToString(
	  Double d,
	  int    npMaximumFractionDigit ) {
		return percentageToString( d.doubleValue(), npMaximumFractionDigit );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   d  a ratio -1&lt;=d&lt;=1
	 *
	 * @return  an formated ratio with 4 maximum fraction digits
	 */
	public static String ratioToString( double d ) {
		return ratioToString( d, 4 );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   d                       a ratio -1&lt;=d&lt;=1
	 * @param   npMaximumFractionDigit  Maximum fraction digits
	 *
	 * @return  an formated ratio
	 */
	public static String ratioToString(
	  double d,
	  int    npMaximumFractionDigit ) {
		if( Double.isNaN( d ) ) {
			return "";
		}
		DecimalFormat decFormat = new DecimalFormat( "0.00" );
		decFormat.setMaximumFractionDigits( npMaximumFractionDigit );

		return decFormat.format( d );
	}

	/**
	 * Méthode qui prend une string en entrée et qui lui enlève les retours chariots
	 *
	 * @param   string  Add comments
	 *
	 * @return  Add comments
	 */
	public static String removeLines( String string ) {
		if( (string != null) && (string.length() > 0) ) {
			StringBuffer   block       = new StringBuffer();
			BufferedReader blockReader = new BufferedReader( new StringReader( string ) );

			String         line        = null;
			try {
				while( (line = blockReader.readLine()) != null ) {
					block.append( line.trim() );
					block.append( " " );
				}
				blockReader.close();
			} catch( IOException ioe ) {
				ioe.printStackTrace();

				return "";
			}

			return block.toString();
		} else {
			return "";
		}
	}
}
