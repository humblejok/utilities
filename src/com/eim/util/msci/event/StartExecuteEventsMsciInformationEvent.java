/**
 * Title           : $Workfile: StartExecuteEventsMsciInformationEvent.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 16.12.03 15:06 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 */

package com.eim.util.msci.event;

/**
 * JavaDoc class comment
 *
 * @author als
 */
public class StartExecuteEventsMsciInformationEvent
	extends MsciImporterEvent {
	protected int eventNb;

	/**
	 * Creates a new StartExecuteEventsMsciInformationEvent object.
	 *
	 * @param eventNb DOCUMENT ME!
	 */
	public StartExecuteEventsMsciInformationEvent( int eventNb ) {
		this.eventNb = eventNb;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public int getEventNb(  ) {
		return eventNb;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param i Add comments
	 */
	public void setEventNb( int i ) {
		eventNb = i;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return 		Add comments
	 */
	public String toString(  ) {
		return super.toString(  ) + "(" + eventNb + ")";
	}
}
