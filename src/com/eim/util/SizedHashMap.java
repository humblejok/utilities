/**
 * Title           : $Workfile: SizedHashMap.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:59 $
 * By              : $Author: Als $
 * Version number  : $Revision: 6 $
 *
 * $History: SizedHashMap.java $
 * 
 * *****************  Version 6  *****************
 * User: Als          Date: 10/08/07   Time: 9:59
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring for Java 5
 * 
 * *****************  Version 5  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:37
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring
 */
/**
 * Title           : $Workfile: SizedHashMap.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:59 $
 * By              : $Author: Als $
 * Version number  : $Revision: 6 $
 */
package com.eim.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
public final class SizedHashMap<K,V>
	extends HashMap<K,V>
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -1298992190157190811L;
	private int     maxSize;
	private HashMap<K, Long> timestamps;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new SizedHashMap object.
	 *
	 * @param  maxSize  DOCUMENT ME!
	 */
	public SizedHashMap(final int maxSize) {
		super( maxSize );
		this.maxSize = maxSize;
		timestamps   = new HashMap<K, Long>( maxSize );
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 */
	public void clear() {
		synchronized(timestamps) {
			super.clear();
			timestamps.clear();
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	@SuppressWarnings("unchecked")
	public Object clone() {
		synchronized(timestamps) {
			SizedHashMap<K,V> result = (SizedHashMap<K,V>)super.clone();
			result.maxSize    = this.maxSize;
			result.timestamps = (HashMap<K, Long>)this.timestamps.clone();
			return result;
		}
	} // end method clone

	/**
	 * JavaDoc method comments
	 *
	 * @param   key  Add comments
	 *
	 * @return  Add comments
	 */
	public boolean containsKey(final Object key) {
		synchronized(timestamps) {
			return super.containsKey( key );
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   value  Add comments
	 *
	 * @return  Add comments
	 */
	public boolean containsValue(final Object value) {
		synchronized(timestamps) {
			return super.containsValue( value );
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public Set<Entry<K,V>> entrySet() {
		synchronized(timestamps) {
			return super.entrySet();
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   key  Add comments
	 *
	 * @return  Add comments
	 */
	public V get(final Object key) {
		synchronized(timestamps) {
			return super.get( key );
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public Set<K> keySet() {
		synchronized(timestamps) {
			return super.keySet();
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   key    Add comments
	 * @param   value  Add comments
	 *
	 * @return  Add comments
	 */
	public V put(final K key, final V value) {
		synchronized(timestamps) {
			if(this.size()<maxSize) {
				timestamps.put( key, new Long( System.currentTimeMillis() ) );
				return super.put( key, value );
			} else {
				K oldestKey = getOldestKey();
				timestamps.remove( oldestKey );
				super.remove( oldestKey );
				timestamps.put( key, new Long( System.currentTimeMillis() ) );
				return super.put( key, value );
			} // end if
		} // end synchronized
	} // end method put

	/**
	 * JavaDoc method comments
	 *
	 * @param  m  Add comments
	 */
	public void putAll(final Map<? extends K, ? extends V> m) {
		synchronized(timestamps) {
			for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
				this.put(entry.getKey(), entry.getValue());
			}
		} // end synchronized
	} // end method putAll

	/**
	 * JavaDoc method comments
	 *
	 * @param   key  Add comments
	 *
	 * @return  Add comments
	 */
	public V remove(final Object key) {
		synchronized(timestamps) {
			timestamps.remove( key );
			return super.remove( key );
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public int size() {
		synchronized(timestamps) {
			return super.size();
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public Collection<V> values() {
		synchronized(timestamps) {
			return super.values();
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	private K getOldestKey() {
		K   oldestKey   = null;
		Long     oldestStamp = new Long( Long.MAX_VALUE );
		for (Entry<K,Long> entry : timestamps.entrySet()) {
			if(oldestStamp.longValue()>entry.getValue().longValue()) {
				oldestStamp = entry.getValue();
				oldestKey   = entry.getKey();
			}
		}
		return oldestKey;
	} // end method getOldestKey

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public boolean isEmpty() {
		synchronized(timestamps) {
			return super.isEmpty();
		}
	}
} // end class SizedHashMap
