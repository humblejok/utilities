/**
 * Title           : $Workfile: StartBuildEventMsciTracksEvent.java $
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
public class StartBuildEventMsciTracksEvent
	extends MsciImporterEvent {
	protected int eventNb;

	/**
	 * Creates a new StartBuildEventMsciTracksEvent object.
	 *
	 * @param eventNb DOCUMENT ME!
	 */
	public StartBuildEventMsciTracksEvent( int eventNb ) {
		this.eventNb = eventNb;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return 		Add comments
	 */
	public int getEventNb(  ) {
		return eventNb;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param i 	Add comments
	 */
	public void setEventNb( int i ) {
		eventNb = i;
	}
	public String toString() {
		return super.toString() + "(" + eventNb + ")";
	}

}
