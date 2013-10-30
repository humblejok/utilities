/**
 * Title           : $Workfile: StatisticsNotEnoughDataException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 6 $
 *
 * $History: StatisticsNotEnoughDataException.java $
 * 
 * *****************  Version 6  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/statistic
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/statistic
 * Refactoring
 */
package com.eim.util.exceptions.statistic;

import java.util.Date;


/**
 * Exception levée lorsque le track est incohérent par rapport à sa fréquence Il faut renseigner les dates avant et après le trou détecté
 */
public class StatisticsNotEnoughDataException
	extends StatisticsException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 3622816377470439046L;
	private final Date afterDate;
	private final Date beforeDate;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new StatisticsNotEnoughDataException object.
	 *
	 * @param  beforeDate  DOCUMENT ME!
	 * @param  afterDate   DOCUMENT ME!
	 */
	public StatisticsNotEnoughDataException(final Date beforeDate, final Date afterDate) {
		super();
		this.afterDate  = afterDate;
		this.beforeDate = beforeDate;
	}

	/**
	 * Creates a new StatisticsNotEnoughDataException object.
	 *
	 * @param  beforeDate  DOCUMENT ME!
	 * @param  afterDate   DOCUMENT ME!
	 * @param  message     DOCUMENT ME!
	 */
	public StatisticsNotEnoughDataException(final Date beforeDate, final Date afterDate, final String message) {
		super( message );
		this.afterDate  = afterDate;
		this.beforeDate = beforeDate;
	}

	/**
	 * Creates a new StatisticsNotEnoughDataException object.
	 *
	 * @param  beforeDate  DOCUMENT ME!
	 * @param  afterDate   DOCUMENT ME!
	 * @param  cause       DOCUMENT ME!
	 */
	public StatisticsNotEnoughDataException(final Date beforeDate, final Date afterDate, final Throwable cause) {
		super( cause.getMessage() );
		this.afterDate  = afterDate;
		this.beforeDate = beforeDate;
	}

	/**
	 * Creates a new StatisticsNotEnoughDataException object.
	 *
	 * @param  beforeDate  DOCUMENT ME!
	 * @param  afterDate   DOCUMENT ME!
	 * @param  message     DOCUMENT ME!
	 * @param  cause       DOCUMENT ME!
	 */
	public StatisticsNotEnoughDataException(final Date beforeDate, final Date afterDate, final String message, final Throwable cause) {
		super( message, cause );
		this.afterDate  = afterDate;
		this.beforeDate = beforeDate;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final Date getAfterDate() {
		return afterDate;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final Date getBeforeDate() {
		return beforeDate;
	}
} // end class StatisticsNotEnoughDataException
