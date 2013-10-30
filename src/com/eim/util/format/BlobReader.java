/**
 * Title           : $Workfile: BlobReader.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 25.05.05 16:30 $
 * By              : $Author: Als $
 * Version number  : $Revision: 1 $
 *
 * $History: BlobReader.java $
 * 
 * *****************  Version 1  *****************
 * User: Als          Date: 25.05.05   Time: 16:30
 * Created in $/Current/Projects/utilities/src/com/eim/util/format
 */
package com.eim.util.format;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;


/**
 * 
 */
public final class BlobReader {

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new BlobReader object.
	 */
	private BlobReader() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param   blob
	 *
	 * @return
	 *
	 * @throws  SQLException
	 * @throws  IOException
	 * @throws  ClassNotFoundException
	 */
	public static byte[] readBlob(Blob blob) throws SQLException {
		return blob.getBytes(1, (int)blob.length());
	} // end method readBlob
} // end class BlobReader