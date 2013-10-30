/**
 * Title           : $Workfile: StartZipDecompressEvent.java $
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
public class StartZipDecompressEvent
	extends ZipEvent {
	protected int entriesNb;

	/**
	 * Creates a new StartZipDecompressEvent object.
	 *
	 * @param entriesNb DOCUMENT ME!
	 */
	public StartZipDecompressEvent( int entriesNb ) {
		this.entriesNb = entriesNb;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public int getEntriesNb(  ) {
		return entriesNb;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param i Add comments
	 */
	public void setEntriesNb( int i ) {
		entriesNb = i;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return 		Add comments
	 */
	public String toString(  ) {
		return super.toString(  ) + "(" + entriesNb + ")";
	}
}
