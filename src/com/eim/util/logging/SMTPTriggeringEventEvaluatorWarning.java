/**
 * Title           : $Workfile: SMTPTriggeringEventEvaluatorWarning.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 22.06.04 18:26 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 */

package com.eim.util.logging;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
public class SMTPTriggeringEventEvaluatorWarning
implements TriggeringEventEvaluator {

	//~ Static fields/initializers -------------------------------------------------------------------

	protected static final long PERIODICITY = 5 * 60 * 1000;
	protected static long       timestamp;

	static {
		timestamp = System.currentTimeMillis();
	}

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new SMTPTriggeringEventEvaluatorWarning object.
	 */
	public SMTPTriggeringEventEvaluatorWarning() {
		super();
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param   event  Add comments
	 *
	 * @return  Add comments
	 */
	public boolean isTriggeringEvent( LoggingEvent event ) {
		if( (System.currentTimeMillis() - timestamp) >= PERIODICITY ) {
			timestamp = System.currentTimeMillis();

			return true;
		} else {
			return false;
		}
	}
}
