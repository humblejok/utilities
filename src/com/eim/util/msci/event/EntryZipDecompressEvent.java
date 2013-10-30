/**
 * Title           : $Workfile: EntryZipDecompressEvent.java $
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
public class EntryZipDecompressEvent
	extends ZipEvent {
	protected String entryName;

	/**
	 * Creates a new EntryZipDecompressEvent object.
	 *
	 * @param entryName DOCUMENT ME!
	 */
	public EntryZipDecompressEvent( String entryName ) {
		this.entryName = entryName;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public String getEntryName(  ) {
		return entryName;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param string Add comments
	 */
	public void setEntryName( String string ) {
		entryName = string;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return 		Add comments
	 */
	public String toString(  ) {
		return super.toString(  ) + " (" + entryName + ")";
	}
}
