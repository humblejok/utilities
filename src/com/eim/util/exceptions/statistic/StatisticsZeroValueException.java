/**
 * Title           : $Workfile: StatisticsZeroValueException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: StatisticsZeroValueException.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/statistic
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/statistic
 * Refactoring
 */
package com.eim.util.exceptions.statistic;

import java.util.Date;


/**
 * Exception levée lorsqu'une valeur du track est égale à zéro Il faut renseigner la date
 */
public class StatisticsZeroValueException
	extends StatisticsException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -8839648253261135722L;
	private final Date datPiDate;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new StatisticsNotEnoughDataException object.
	 *
	 * @param  datpDate  Date permettant d'identifier dans le track la time series posant problème.
	 */
	public StatisticsZeroValueException(final Date datpDate) {
		super();
		this.datPiDate = datpDate;
	}

	/**
	 * Creates a new StatisticsNotEnoughDataException object.
	 *
	 * @param  datpDate  Date permettant d'identifier dans le track la time series posant problème.
	 * @param  message   Message d'erreur
	 */
	public StatisticsZeroValueException(final Date datpDate, final String message) {
		super( message );
		this.datPiDate = datpDate;
	}

	/**
	 * Creates a new StatisticsNotEnoughDataException object.
	 *
	 * @param  datpDate  Date permettant d'identifier dans le track la time series posant problème.
	 * @param  cause     Exception sous jacente
	 */
	public StatisticsZeroValueException(final Date datpDate, final Throwable cause) {
		super( cause.getMessage() );
		this.datPiDate = datpDate;
	}

	/**
	 * Creates a new StatisticsNotEnoughDataException object.
	 *
	 * @param  datpDate  Date permettant d'identifier dans le track la time series posant problème.
	 * @param  message   Message d'erreur
	 * @param  cause     Exception sous jacente
	 */
	public StatisticsZeroValueException(final Date datpDate, final String message, final Throwable cause) {
		super( message, cause );
		this.datPiDate = datpDate;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @return  Date permettant d'identifier dans le track la time series posant problème.
	 */
	public final Date getDate() {
		return this.datPiDate;
	}
} // end class StatisticsZeroValueException
