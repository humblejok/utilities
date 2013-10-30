/**
 * Title           : $Workfile: CollectionsUtil.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 2/17/09 3:47p $
 * By              : $Author: Als $
 * Version number  : $Revision: 3 $
 *
 * $History: CollectionsUtil.java $
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 2/17/09    Time: 3:47p
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Retro-Propagation
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 10/08/07   Time: 9:46
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring for Java 5
 * 
 * *****************  Version 1  *****************
 * User: Als          Date: 30.12.05   Time: 16:00
 * Created in $/Current/Projects/utilities/src/com/eim/util
 * Ajout du group de classification
 */
package com.eim.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 */
public abstract class CollectionsUtil {

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new CollectionsUtil object.
	 */
	private CollectionsUtil() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * implements an equals method that ignore the ordering of the collection.
	 *
	 * @param   c1
	 * @param   c2
	 *
	 * @return  true if c2 contains exactly all the elements of c1.
	 */
	public static final <K> boolean unorderedEquals(Collection<K> c1, Collection<K> c2) {
		if(c1==c2) {
			return true;
		}
		if((c1==null) || (c2==null)) {
			return false;
		}
		return new HashSet<K>( c1 ).equals( new HashSet<K>( c2 ) );
	} // end method unorderedEquals
	
	
	public static final <K> List<K> removeDuplicates(List<K> list){
		List<K> result = new ArrayList<K>();
		for (K k : list) {
	        if(!result.contains(k)){
	        	result.add(k);
	        }
        }
		return result;
	}
	
	public static <K> List<K> createSingletonList(K k){
		List<K> result = new ArrayList<K>();
		result.add(k);
		return result;
	}
} // end class CollectionsUtil
