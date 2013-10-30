/**
 * Title           : $Workfile: VEvent.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 10/08/07 9:45 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 */

package com.eim.util.pim;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TimeZone;


/**
 * JavaDoc class comment
 *
 * @author als
 */
public class VEvent {
	private static final String NEW_LINE = System.getProperty( "line.separator" );
	public static final String STATUS_TENTATIVE = "Tentative";
	public static final String STATUS_BUSY = "Busy";
	public static final String STATUS_OUT_OF_OFFICE = "Out of Office";
	protected String summary;
	protected String location;
	protected String description;
	protected String status;
	protected Date startDate;
	protected Date endDate;
	protected boolean publicFlag;
	protected ArrayList<String> categories;

	/**
	 * Creates a new VEvent object.
	 */
	public VEvent(  ) {
		super(  );
		summary = "";
		location = "";
		description = "";
		status = STATUS_TENTATIVE;
		startDate = new Date(  );
		endDate = new Date(  );
		publicFlag = true;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param year Add comments
	 * @param month Add comments
	 * @param day Add comments
	 */
	public void setFullDayEvent( int year,
								 int month,
								 int day ) {
		GregorianCalendar calendar = ( GregorianCalendar ) GregorianCalendar.getInstance(  );
		calendar.setLenient( false );
		calendar.set( year, month - 1, day, 0, 0, 0 );
		startDate = calendar.getTime(  );
		calendar.add( Calendar.DATE, +1 );
		endDate = calendar.getTime(  );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param category Add comments
	 */
	public void addCategory( String category ) {
		if( categories == null ) {
			categories = new ArrayList<String>(  );
		}
		categories.add( category );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public ArrayList<String> getCategories(  ) {
		return categories;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public String getDescription(  ) {
		return description;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public Date getEndDate(  ) {
		return endDate;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public String getLocation(  ) {
		return location;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public boolean isPublicFlag(  ) {
		return publicFlag;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public Date getStartDate(  ) {
		return startDate;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public String getSummary(  ) {
		return summary;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param list Add comments
	 */
	public void setCategories( ArrayList<String> list ) {
		categories = list;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param string Add comments
	 */
	public void setDescription( String string ) {
		description = string;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param date Add comments
	 */
	public void setEndDate( Date date ) {
		endDate = date;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param string Add comments
	 */
	public void setLocation( String string ) {
		location = string;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param b Add comments
	 */
	public void setPublicFlag( boolean b ) {
		publicFlag = b;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param date Add comments
	 */
	public void setStartDate( Date date ) {
		startDate = date;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param string Add comments
	 */
	public void setSummary( String string ) {
		summary = string;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public String toString(  ) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyyMMdd'T'HHmmss'Z'" );
		simpleDateFormat.setTimeZone( TimeZone.getTimeZone( "GMT" ) );

		StringBuffer output = new StringBuffer(  );

		output.append( "BEGIN:VEVENT" ).append( NEW_LINE );
		output.append( "DTSTART:" ).append( simpleDateFormat.format( startDate ) ).append( NEW_LINE );
		output.append( "DTEND:" ).append( simpleDateFormat.format( endDate ) ).append( NEW_LINE );
		output.append( "SUMMARY;ENCODING=QUOTED-PRINTABLE:" ).append( PimEncoder.encode( summary ) ).append( NEW_LINE );
		output.append( "LOCATION;ENCODING=QUOTED-PRINTABLE:" ).append( PimEncoder.encode( location ) ).append( NEW_LINE );
		output.append( "DESCRIPTION;ENCODING=QUOTED-PRINTABLE:" ).append( PimEncoder.encode( description ) ).append( NEW_LINE );
		output.append( "PUBLIC:" ).append( publicFlag ? "PUBLIC" : "PRIVATE" ).append( NEW_LINE );

		//output.append( "STATUS:" ).append( status ).append( NEW_LINE );
		if( ( categories != null ) && !categories.isEmpty(  ) ) {
			output.append( "CATEGORIES:" );

			Iterator<String> categoryIt = categories.iterator(  );
			while( categoryIt.hasNext(  ) ) {
				String category = categoryIt.next(  );
				output.append( category );
				if( categoryIt.hasNext(  ) ) {
					output.append( ";" );
				}
			}
			output.append( NEW_LINE );
		}
		output.append( "END:VEVENT" ).append( NEW_LINE );

		return output.toString(  );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public String getStatus(  ) {
		return status;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param string Add comments
	 */
	public void setStatus( String string ) {
		status = string;
	}
}
