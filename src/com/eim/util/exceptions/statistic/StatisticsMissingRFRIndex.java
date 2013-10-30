/**
 * Title           : $Workfile: StatisticsMissingRFRIndex.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: StatisticsMissingRFRIndex.java $
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
 * Exception levée lorsque l'indice RFR est manquant pour le calcul des ratio (Exemple Sharpe Ratio RFR).
 *
 * @author  jpf
 */
public class StatisticsMissingRFRIndex
	extends StatisticsException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -6316215192898871268L;

	/**
	 */
	public StatisticsMissingRFRIndex() {
		super();
	}

	/**
	 * @param  message
	 */
	public StatisticsMissingRFRIndex(String message) {
		super( message );
	}

	/**
	 * @param  message
	 * @param  cause
	 */
	public StatisticsMissingRFRIndex(String message, Throwable cause) {
		super( message, cause );
	}
} // end class StatisticsMissingRFRIndex
