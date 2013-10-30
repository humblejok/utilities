/**
 * Title           : $Workfile: NodeWrapper.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 11:38 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 *
 * $History: NodeWrapper.java $
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 10/08/07   Time: 11:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring for Java 5
 * 
 * *****************  Version 1  *****************
 * User: Als          Date: 22.02.05   Time: 10:07
 * Created in $/Current/Projects/utilities/src/com/eim/util
 * Gestion d'arbre
 */
package com.eim.util;

/**
 * 
 */
interface NodeWrapper<E> {

	//~ Methods ----------------------------------------------------------------

	E getNodeContent();
}