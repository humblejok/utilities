/**
 * Title           : $Workfile: StatisticsMissingLastestValueException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: StatisticsMissingLastestValueException.java $
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/statistic
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/statistic
 * Refactoring
 */
package com.eim.util.exceptions.statistic;

/**
 * Exception levée lorsque la dernière time series d'un track est manquante en fonction de la fréquence du track
 *
 * @author  jpf
 */
public class StatisticsMissingLastestValueException
	extends StatisticsException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 6683683271957495548L;

	/**
	 * Creates a new StatisticsException object.
	 */
	public StatisticsMissingLastestValueException() {
		super();
	}

	/**
	 * Creates a new StatisticsException object.
	 *
	 * @param  message
	 */
	public StatisticsMissingLastestValueException(String message) {
		super( message );
	}

	/**
	 * Creates a new StatisticsException object.
	 *
	 * @param  message
	 * @param  cause
	 */
	public StatisticsMissingLastestValueException(String message, Throwable cause) {
		super( message );
	}
} // end class StatisticsMissingLastestValueException
