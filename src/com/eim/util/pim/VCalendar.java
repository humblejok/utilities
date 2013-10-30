/**
 * Title           : $Workfile: VCalendar.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:45 $
 * By              : $Author: Als $
 * Version number  : $Revision: 3 $
 *
 * $History: VCalendar.java $
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 10/08/07   Time: 9:45
 * Updated in $/Current/Projects/utilities/src/com/eim/util/pim
 * Refactoring for Java 5
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 10.11.04   Time: 17:47
 * Updated in $/Current/Projects/utilities/src/com/eim/util/pim
 */
package com.eim.util.pim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
public class VCalendar {

	//~ Static fields/initializers ---------------------------------------------

	private static final String NEW_LINE = System.getProperty( "line.separator" );
	private static final String VERSION  = "1.0";

	//~ Instance fields --------------------------------------------------------

	protected ArrayList<VEvent> events;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new VCalendar object.
	 */
	public VCalendar() {
		super();
		events = new ArrayList<VEvent>();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param  args
	 */
	public static final void main(String[] args) {
		VCalendar calendar = new VCalendar();
		VEvent    event    = new VEvent();
		event.setFullDayEvent( 2003, 11, 30 );
		event.addCategory( "Fund Visit" );
		event.setSummary( "Visit of the fund \"the יהטהיא+*ח%û fund\"" );
		event.setLocation( "EIM SA" );
		event.setDescription( "Welcome to the party!!!" );
		event.setPublicFlag( true );
		event.setStatus( VEvent.STATUS_BUSY );
		calendar.addEvent( event );
		System.out.println( calendar.toString() );
	} // end method main

	/**
	 * JavaDoc method comments
	 *
	 * @param  event  Add comments
	 */
	public void addEvent(VEvent event) {
		events.add( event );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append( "BEGIN:VCALENDAR" ).append( NEW_LINE );
		output.append( "VERSION:" ).append( VERSION ).append( NEW_LINE );
		Iterator<VEvent> eventIt = events.iterator();
		while(eventIt.hasNext()) {
			VEvent event = eventIt.next();
			output.append( event.toString() );
		}
		output.append( "END:VCALENDAR" ).append( NEW_LINE );
		return output.toString();
	} // end method toString

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public Collection<VEvent> getEvents() {
		return events;
	}
} // end class VCalendar