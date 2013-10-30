/**
 * Title           : $Workfile: MSCIImportedData.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 15.12.03 17:14 $
 * By              : $Author: Als $
 * Version number  : $Revision: 1 $
 */

package com.eim.util.msci;

import java.util.Date;

import org.w3c.dom.Document;


/**
 * JavaDoc class comment
 *
 * @author als
 */
public class MSCIImportedData {
	protected Document data;
	protected Date extractionDate;

	/**
	 * Creates a new MSCIImportedData object.
	 *
	 * @param data DOCUMENT ME!
	 * @param extractionDate DOCUMENT ME!
	 */
	public MSCIImportedData( Document data,
							 Date extractionDate ) {
		this.data = data;
		this.extractionDate = extractionDate;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public Document getData(  ) {
		return data;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public Date getExtractionDate(  ) {
		return extractionDate;
	}
}
