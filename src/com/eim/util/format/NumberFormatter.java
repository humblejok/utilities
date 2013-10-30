/**
 * Title           : $Workfile: NumberFormatter.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 4/29/05 9:38a $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 9 $
 */

package com.eim.util.format;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import java.util.Locale;


/**
 * Classe utilitaire permettant de formatter un montant
 *
 * @author  jpf
 */
public final class NumberFormatter {

	//~ Static fields/initializers -------------------------------------------------------------------

	/** DOCUMENT ME! */
	public static final int FRACTION_DIGIT = 6;
	/** DOCUMENT ME! */
	public static final String FORMAT_AMOUNT = "Amount";
	/** DOCUMENT ME! */
	public static final String FORMAT_PERCENTAGE = "Percentage";
	/** DOCUMENT ME! */
	public static final String FORMAT_NUMBER = "Number";

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new NumberFormatter object.
	 */
	private NumberFormatter() {
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param   d               amount
	 * @param   fractionDigits  number of digits
	 *
	 * @return  an formated amount
	 */
	public static final String amountToString(
	  double d,
	  int    fractionDigits ) {
		NumberFormat df = DecimalFormat.getNumberInstance( Locale.US );
		df.setMaximumFractionDigits( fractionDigits );

		return df.format( d );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   d  amount
	 *
	 * @return  an formated amount
	 */
	public static final String amountToWeb( double d ) {
		return DecimalFormat.getNumberInstance( Locale.US ).format( d );
	}

	/**
	 * 
	 *
	 * @param   d  amount
	 *
	 * @return  an formated amount
	 */
	public static final String amountToWeb( Double d ) {
		if( d != null ) {
			return amountToWeb( d.doubleValue() );
		} else {
			return Double.toString( Double.NaN );
		}
	}

	/**
	 * 
	 *
	 * @param   d  amount
	 *
	 * @return  an formated amount
	 */
	public static final String amountToWeb( Number d ) {
		if( d != null ) {
			return amountToWeb( d.doubleValue() );
		} else {
			return Double.toString( Double.NaN );
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   value           Add comments
	 * @param   fractionDigits  DOCUMENT ME!
	 *
	 * @return  Add comments
	 *
	 * @throws  ParseException  DOCUMENT ME!
	 */
	public static final Number parse(
	  String value,
	  int    fractionDigits )
	        throws ParseException {
		NumberFormat df = DecimalFormat.getNumberInstance( Locale.US );
		df.setMaximumFractionDigits( fractionDigits );

		return df.parse( value );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   value           Add comments
	 * @param   fractionDigits  
	 * @param   trackType       
	 *
	 * @return  Add comments
	 *
	 * @throws  ParseException  DOCUMENT ME!
	 */
	public static final Number parse(
	  String value,
	  int    fractionDigits,
	  String trackType )
	        throws ParseException {


		NumberFormat df = null;

		if( FORMAT_AMOUNT.equals( trackType ) || FORMAT_NUMBER.equals( trackType ) ) {
			df = DecimalFormat.getNumberInstance( Locale.US );
		} else {
			df = DecimalFormat.getPercentInstance( Locale.US );
		}

		df.setMaximumFractionDigits( fractionDigits );

		return df.parse( value );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   d               amount
	 * @param   fractionDigits  number of digits
	 *
	 * @return  an formated amount
	 */
	public static final String percentToString(
	  double d,
	  int    fractionDigits ) {
		NumberFormat df = getPercentFormat(fractionDigits);

		return df.format( d );
	}
	
	/**
	 * @param fractionDigits number of digits
	 * @return A percentage formatter
	 */
	public static final NumberFormat getPercentFormat(int    fractionDigits){
		NumberFormat df = DecimalFormat.getPercentInstance( Locale.US );
		df.setMaximumFractionDigits( fractionDigits );
		return df;
	}
}
