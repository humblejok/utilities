/**
 * Title           : $Workfile: IterationStatistics.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 29/06/07 11:34 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 *
 * $History: IterationStatistics.java $
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 29/06/07   Time: 11:34
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Correction des calculs de sats et des rkt
 * 
 * *****************  Version 1  *****************
 * User: Als          Date: 7.08.06    Time: 10:45
 * Created in $/Current/Projects/utilities/src/com/eim/util
 */
package com.eim.util;

/**
 */
public class IterationStatistics {

	//~ Instance fields --------------------------------------------------------

	private long    elapsedTime		   = 0;
	private boolean inIteration		   = false;
	private int     iterationNb		   = 0;
	private long    iterationStartTime;
	private long    maxTime			   = Long.MIN_VALUE;
	private long    minTime			   = Long.MAX_VALUE;
	private long    lastRunDuration    = 0;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new IterationStatistics object.
	 */
	public IterationStatistics() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 */
	public void beginIteration() {
		if(!inIteration) {
			inIteration		   = true;
			iterationStartTime = System.currentTimeMillis();
		}
	}

	/**
	 */
	public void endIteration() {
		if(inIteration) {
			long iterationEndTime  = System.currentTimeMillis();
			long iterationDuration = iterationEndTime - iterationStartTime;
			lastRunDuration = iterationDuration;
			elapsedTime += iterationDuration;
			iterationNb++;
			minTime     = Math.min( minTime, iterationDuration );
			maxTime     = Math.max( maxTime, iterationDuration );
			inIteration = false;
		} // end if
	} // end method endIteration

	/**
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append( "Iteration stats: " );
		buffer.append( " elapsed time = " ).append( elapsedTime ).append( "ms. in " );
		buffer.append( iterationNb ).append( " iteration." );
		buffer.append( " average time = " ).append( elapsedTime / iterationNb ).append( "ms. " );
		buffer.append( " min time = " ).append( minTime ).append( "ms. " );
		buffer.append( " max time = " ).append( maxTime ).append( "ms. " );
		buffer.append( " last run = " ).append( lastRunDuration ).append( "ms. " );
		return buffer.toString();
	} // end method toString
} // end class IterationStatistics
