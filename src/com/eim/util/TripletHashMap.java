/**
 * Title           : $Workfile: TripletHashMap.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 10:02 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 */
package com.eim.util;

import java.util.HashMap;
import java.util.Set;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision: 2 $, $Date: 10/08/07 10:02 $
 */
public class TripletHashMap<K1,K2,V> {

	//~ Instance fields ------------------------------------------------------------------------------

	private HashMap<K1,HashMap<K2, V>> primaryMap;

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new TripletHashMap object.
	 */
	public TripletHashMap() {
		super();
		primaryMap = new HashMap<K1, HashMap<K2,V>>();
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 */
	public void clear() {
		primaryMap.clear();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   key  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public boolean containsPrimaryKey( K1 key ) {
		return primaryMap.containsKey( key );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   primaryKey    DOCUMENT ME!
	 * @param   secondaryKey  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public V get(
	  K1 primaryKey,
	  K2 secondaryKey ) {
		if( primaryMap.containsKey( primaryKey ) ) {
			return primaryMap.get( primaryKey ).get( secondaryKey );
		}
		return null;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public Set<K1> primaryKeySet() {
		return primaryMap.keySet();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public int primarySize() {
		return primaryMap.size();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   primaryKey    DOCUMENT ME!
	 * @param   secondaryKey  DOCUMENT ME!
	 * @param   value         DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public V put(
	  K1 primaryKey,
	  K2 secondaryKey,
	  V value ) {
		if( primaryMap.containsKey( primaryKey ) ) {
			HashMap<K2, V> secondaryMap = primaryMap.get( primaryKey );
			return secondaryMap.put( secondaryKey, value );
		} else {
			HashMap<K2,V> secondaryMap = new HashMap<K2, V>();
			primaryMap.put( primaryKey, secondaryMap );
			return secondaryMap.put( secondaryKey, value );
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param  primaryKey  DOCUMENT ME!
	 */
	public void remove( K1 primaryKey ) {
		primaryMap.remove( primaryKey );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param  primaryKey    DOCUMENT ME!
	 * @param  secondaryKey  DOCUMENT ME!
	 */
	public void remove(
	  K1 primaryKey,
	  K2 secondaryKey ) {
		if( primaryMap.containsKey( primaryKey ) ) {
			primaryMap.get( primaryKey ).remove( secondaryKey );
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   primaryKey  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public Set<K2> secondaryKeySet( K1 primaryKey ) {
		if( primaryMap.containsKey( primaryKey ) ) {
			return primaryMap.get( primaryKey ).keySet();
		}
		return null;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param   primaryKey  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public int secondarySize( K1 primaryKey ) {
		if( primaryMap.containsKey( primaryKey ) ) {
			return primaryMap.get( primaryKey ).size();
		}
		return 0;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return
	 *
	 * @author
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append( "TripletHashMap[" );
		buffer.append( primaryMap );
		buffer.append( "]" );
		return buffer.toString();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public boolean isEmpty() {
		return primaryMap.isEmpty();
	}
}
