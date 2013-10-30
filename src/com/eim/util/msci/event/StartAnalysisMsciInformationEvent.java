/**
 * Title           : $Workfile: StartAnalysisMsciInformationEvent.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 16.12.03 15:06 $
 * By              : $Author: Als $
 * Version number  : $Revision: 3 $
 */

package com.eim.util.msci.event;

/**
 * JavaDoc class comment
 *
 * @author als
 */
public class StartAnalysisMsciInformationEvent
	extends MsciImporterEvent {
	protected int fundNb;

	/**
	 * Creates a new StartAnalysisMsciInformationEvent object.
	 *
	 * @param fundNb DOCUMENT ME!
	 */
	public StartAnalysisMsciInformationEvent( int fundNb ) {
		this.fundNb = fundNb;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public int getFundNb(  ) {
		return fundNb;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return 		Add comments
	 */
	public String toString(  ) {
		return super.toString(  ) + "(" + fundNb + ")";
	}
}
