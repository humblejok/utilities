/**
 * Title           : $Workfile: InformationProvider.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 22.06.04 18:26 $
 * By              : $Author: Als $
 * Version number  : $Revision: 3 $
 */

package com.eim.util.exceptions;

/**
 * Description of the Class
 *
 * @author  als
 */
public final class InformationProvider {

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new InformationProvider object.
	 */
	private InformationProvider() {
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * Description of the Method
	 *
	 * @return  Description of the Return Value
	 */
	public static String giveMethodName() {
		try {
			return new Throwable().getStackTrace()[2].toString();
		} catch( ArrayIndexOutOfBoundsException e ) {
			return new Throwable().getStackTrace()[1].toString();
		}
	}
}
