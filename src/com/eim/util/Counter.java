/**
 * Title           : $Workfile: Counter.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:59 $
 * By              : $Author: Als $
 * Version number  : $Revision: 5 $
 *
 * $History: Counter.java $
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 10/08/07   Time: 9:59
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring for Java 5
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:33
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring
 */
/*
 * Created on Apr 8, 2005
 *
 */
package com.eim.util;

import java.text.NumberFormat;
import java.text.ParseException;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Allow to count object
 *
 * @author  sdj
 */
public class Counter<E>
	extends Hashtable<E,Integer>
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -3331379584392348577L;

	/**
	 * Creates a new Counter object.
	 */
	public Counter() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Create a new entry for <code>countObject</code> with starting value set to <code>startingNumber</code> .
	 *
	 * @param  countObject
	 * @param  startingNumber
	 */
	public void addCountObject(E countObject, Integer startingNumber) {
		if(countObject==null) {
			return;
		}
		this.remove( countObject );
		this.put( countObject, startingNumber );
	} // end method addCountObject

	/**
	 * Decrease the counter of the <code>countObject</code> object, if this object do not exist, it is created and valued to 1.
	 *
	 * @param  countObject
	 */
	public void doDecrement(E countObject) {
		this.doChange( countObject, new Integer( -1 ) );
	} // end method doDecrement

	/**
	 * Increase the counter of the <code>countObject</code> object, if this object do not exist, it is created and valued to 1.
	 *
	 * @param  countObject
	 */
	public void doIncrement(E countObject) {
		this.doChange( countObject, new Integer( 1 ) );
	} // end method doIncrement

	/**
	 * @param  mergedCounter
	 */
	public void doMerge(Counter<E> mergedCounter) {
		for(Enumeration<E> keys = mergedCounter.keys(); keys.hasMoreElements();) {
			E key = keys.nextElement();
			this.doChange( key, (Integer)mergedCounter.get( key ) );
		}
	}

	/**
	 * @param  countObject
	 * @param  value
	 */
	protected void doChange(E countObject, Integer value) {
		if(countObject==null) {
			return;
		}
		Integer counter = this.get( countObject );
		if(counter==null) {
			this.put( countObject, value );
		} else {
			this.remove( countObject );
			this.put( countObject, new Integer( counter.intValue() + value.intValue() ) );
		}
	} // end method doChange

	public String getCounterStatistics(Counter<E> globalCounter) {
		StringBuffer resultString   = new StringBuffer();
		NumberFormat percentDisplay = NumberFormat.getPercentInstance();
		for(Enumeration<E> keys = this.keys(); keys.hasMoreElements();) {
			E key = keys.nextElement();
			resultString.append( key.toString() + " : " + this.get( key ) + "/" + globalCounter.get( key ) );
			try {
				resultString.append( " (" + percentDisplay.parse( percentDisplay.format( this.getItemStatistics( key, globalCounter ) * 100.0d ) ) + "%) " );
			} catch(ParseException e) {
				resultString.append( " (N/A) " );
			}
		} // end for
		return resultString.toString();
	} // end method getCounterStatistics

	public double getItemStatistics(Object item, Counter<E> globalCounter) {
		return (this.get( item ).doubleValue() / globalCounter.get( item ).doubleValue());
	}
} // end class Counter
