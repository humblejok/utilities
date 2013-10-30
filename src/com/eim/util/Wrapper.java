/*
 * =====================================================
 * Title           : $Workfile: Wrapper.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 11:38 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 * =====================================================
 * $History: Wrapper.java $
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 10/08/07   Time: 11:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring for Java 5
 * 
 * *****************  Version 1  *****************
 * User: Als          Date: 24.02.05   Time: 17:53
 * Created in $/Current/Projects/utilities/src/com/eim/util
 * Improvement of the Tree utility
 */
package com.eim.util;

/**
 * 
 */
public interface Wrapper<K,V> {

	//~ Methods ----------------------------------------------------------------

	/**
	 * transforms an object in another object or creates a new object following the object in parameter.
	 *
	 * @param   o  original object
	 *
	 * @return  the new object
	 */
	V wrap(K o);
} // end interface Wrapper
