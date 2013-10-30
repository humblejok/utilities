/**
 * Title           : $Workfile: GeneralChecker.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 16.06.04 18:23 $
 * By              : $Author: Als $
 * Version number  : $Revision: 5 $
 */

package com.eim.util.check;

import java.util.Collection;


/**
 * JavaDoc class comment
 *
 * @author   als
 * @version  $Revision: 5 $
 */
public final class GeneralChecker {

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new GeneralChecker object.
	 */
	private GeneralChecker() {
		super();
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param   s  Add comments
	 *
	 * @return  Add comments
	 */
	public static boolean isEmpty( final String s ) {
		return ((s == null) || (s.length() == 0));
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   c  Add comments
	 *
	 * @return  Add comments
	 */
	@SuppressWarnings("unchecked")
	public static boolean isEmpty( final Collection c ) {
		return (c == null) || c.isEmpty();
	}
}
