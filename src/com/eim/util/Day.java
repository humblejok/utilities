/**
 * Title           : $Workfile: Day.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10.11.04 17:53 $
 * By              : $Author: Als $
 * Version number  : $Revision: 4 $
 *
 * $History: Day.java $
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 10.11.04   Time: 17:53
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 */
package com.eim.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Represents a date without any time information. This class is intended to represent a usefull day representation without the time information presents in the
 * Date object. A gregorian calendar is used for day to day computing. This object can be safely used as key in collections.
 *
 * @author   $Author: Als $
 * @version  $Revision: 4 $, $Date: 10.11.04 17:53 $
 */
@SuppressWarnings("unchecked")
public class Day implements Comparable {

	//~ Instance fields --------------------------------------------------------

	private Calendar calendar;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new Day object representing the current day.
	 */
	public Day() {
		super();
		calendar = getCalendar();
	}

	/**
	 * Creates a new Day object representing the specified date
	 *
	 * @param  date  the date to set the day
	 */
	public Day(Date date) {
		super();
		calendar = getCalendar( date );
	}

	/**
	 * Creates a new Day object representing an explicit day.
	 *
	 * @param  year   The year of the day
	 * @param  month  The month of the day
	 * @param  day    The day of the month of the day
	 */
	public Day(int year, int month, int day) {
		super();
		calendar = getCalendar();
		calendar.set( year, month, day );
	}

	/**
	 * Creates a new Day object using the information stores in a calendar
	 *
	 * @param  calendar  the calendar containing the date/time information
	 */
	private Day(Calendar calendar) {
		super();
		this.calendar = calendar;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * the test program
	 *
	 * @param  args  the arguments of the test program (not used)
	 */
	public static final void main(String[] args) {
		final String tab  = "\t";
		Day			 d    = new Day( 2004, 0, 1 );
		StringBuffer line = new StringBuffer();
		line.append( "Day" ).append( tab );
		line.append( "WeekDayName" ).append( tab );
		line.append( "FirstOpenDayOfTheWeek" ).append( tab );
		line.append( "LastOpenDayOfTheWeek" ).append( tab );
		line.append( "FirstOpenDayOfThisMonth" ).append( tab );
		line.append( "LastOpenDayOfThisMonth" ).append( tab );
		line.append( "previous" ).append( tab );
		line.append( "next" ).append( tab );
		line.append( "previousWeek" ).append( tab );
		line.append( "nextWeek" ).append( tab );
		line.append( "previousMonth" ).append( tab );
		line.append( "nextMonth" ).append( tab );
		line.append( "previousYear" ).append( tab );
		line.append( "nextYear" ).append( tab );
		System.out.println( line.toString() );
		for(int i = 0; i<400; i++) {
			line = new StringBuffer();
			line.append( d.toString() ).append( tab );
			line.append( d.getWeekDayName() ).append( tab );
			line.append( d.getFirstOpenDayOfTheWeek().toString() ).append( tab );
			line.append( d.getLastOpenDayOfTheWeek().toString() ).append( tab );
			line.append( d.getFirstOpenDayOfThisMonth().toString() ).append( tab );
			line.append( d.getLastOpenDayOfThisMonth().toString() ).append( tab );
			line.append( d.previous().toString() ).append( tab );
			line.append( d.next().toString() ).append( tab );
			line.append( d.previousWeek().toString() ).append( tab );
			line.append( d.nextWeek().toString() ).append( tab );
			line.append( d.previousMonth().toString() ).append( tab );
			line.append( d.nextMonth().toString() ).append( tab );
			line.append( d.previousYear().toString() ).append( tab );
			line.append( d.nextYear().toString() ).append( tab );
			System.out.println( line.toString() );
			d = d.next();
		} // end for
	} // end method main

	/**
	 * returns a clone of this day
	 *
	 * @return  another equivalent representation of this day
	 *
	 * @throws  CloneNotSupportedException  should not be thrown;
	 */
	public Object clone()
				 throws CloneNotSupportedException
	{
		return new Day( toDate() );
	}

	/**
	 * simple comparator for Days
	 *
	 * @param   o  the object to compare
	 *
	 * @return  the value 0 if the argument o is equal to this Day; a value less than 0 if this Day is before the Day argument; and a value greater than 0 if
	 *          this Day is after the Day argument.
	 *
	 * @throws  IllegalArgumentException  if the object is null or not a Day object
	 */
	public int compareTo(Object o) {
		if((o==null) || !(o instanceof Day)) {
			throw new IllegalArgumentException();
		}
		return toDate().compareTo( ((Day)o).toDate() );
	}

	/**
	 * Compares this calendar to the specified object. The result is true if and only if the argument is not null and is a Day object that represents the same
	 * day as this object.
	 *
	 * @param   obj  the object to compare with.
	 *
	 * @return  true if the objects are the same; false otherwise.
	 */
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if(!(obj instanceof Day)) {
			return false;
		}
		Day day = (Day)obj;
		return calendar.equals( day.calendar );
	} // end method equals

	/**
	 * Returns a hash code for this day.
	 *
	 * @return  a hash code value for this object.
	 */
	public int hashCode() {
		int result = 17;
		result = (37 * result) + calendar.hashCode();
		return result;
	}

	/**
	 * evaluates the following day
	 *
	 * @return  a Day object representing the next day of this
	 */
	public Day next() {
		Calendar cal = getCalendar( calendar.getTime() );
		cal.add( Calendar.DATE, 1 );
		return new Day( cal );
	}

	/**
	 * evaluates the day, one month in the future
	 *
	 * @return  a Day object representing this day forward one month in the future
	 */
	public Day nextMonth() {
		Calendar cal = getCalendar( calendar.getTime() );
		cal.add( Calendar.MONTH, 1 );
		return new Day( cal );
	}

	/**
	 * evaluates the day, one week in the future
	 *
	 * @return  a Day object representing this day forward one week in the future
	 */
	public Day nextWeek() {
		Calendar cal = getCalendar( calendar.getTime() );
		cal.add( Calendar.WEEK_OF_YEAR, 1 );
		return new Day( cal );
	}

	/**
	 * evaluates the day, one year in the future
	 *
	 * @return  a Day object representing this day forward one year in the future
	 */
	public Day nextYear() {
		Calendar cal = getCalendar( calendar.getTime() );
		cal.add( Calendar.YEAR, 1 );
		return new Day( cal );
	}

	/**
	 * evaluates the previous day of this day
	 *
	 * @return  a Day object representing the previous day of this day
	 */
	public Day previous() {
		Calendar cal = getCalendar( calendar.getTime() );
		cal.add( Calendar.DATE, -1 );
		return new Day( cal );
	}

	/**
	 * evaluates the day, one month in the past
	 *
	 * @return  a Day object representing this day back one month in the past
	 */
	public Day previousMonth() {
		Calendar cal = getCalendar( calendar.getTime() );
		cal.add( Calendar.MONTH, -1 );
		return new Day( cal );
	}

	/**
	 * evaluates the day, one week in the past
	 *
	 * @return  a Day object representing this day back one week in the past
	 */
	public Day previousWeek() {
		Calendar cal = getCalendar( calendar.getTime() );
		cal.add( Calendar.WEEK_OF_YEAR, -1 );
		return new Day( cal );
	}

	/**
	 * evaluates the day, one year in the past
	 *
	 * @return  a Day object representing this day back one year in the past
	 */
	public Day previousYear() {
		Calendar cal = getCalendar( calendar.getTime() );
		cal.add( Calendar.YEAR, -1 );
		return new Day( cal );
	}

	/**
	 * gives a Date representation of this day
	 *
	 * @return  a Date object representing the same day as this Day
	 */
	public Date toDate() {
		return calendar.getTime();
	}

	/**
	 * Converts to a string representing the data in this object
	 *
	 * @return  a String representing the data in this object
	 */
	public String toString() {
		return DateFormat.getDateInstance( DateFormat.MEDIUM ).format( toDate() );
	}

	/**
	 * test if this day is after the other day
	 *
	 * @param   other  the day to test
	 *
	 * @return  true if this day is after the other day.
	 */
	public boolean isAfter(Day other) {
		return compareTo( other )>0;
	}

	/**
	 * test if this day is before the other day
	 *
	 * @param   other  the day to test
	 *
	 * @return  true if this day is before the other day.
	 */
	public boolean isBefore(Day other) {
		return compareTo( other )<0;
	}

	/**
	 * get the day of the month
	 *
	 * @return  the day of the month
	 */
	public int getDay() {
		return calendar.get( Calendar.DAY_OF_MONTH );
	}

	/**
	 * get a calendar set at the current day but with the time information cleared
	 *
	 * @return  a Calendar without the time information set at the current day
	 */
	private static Calendar getCalendar() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setLenient( false );
		calendar.clear( Calendar.HOUR );
		calendar.clear( Calendar.MINUTE );
		calendar.clear( Calendar.SECOND );
		calendar.clear( Calendar.MILLISECOND );
		return calendar;
	} // end method getCalendar

	/**
	 * get a calendar set at the specified day but with the time information cleared
	 *
	 * @param   date  the date at which to set the calendar
	 *
	 * @return  a Calendar without the time information set at the specified date
	 */
	private static Calendar getCalendar(Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime( date );
		return calendar;
	}

	/**
	 * test if this day is the last day of the month
	 *
	 * @return  true if this day is the last day of the month
	 */
	public boolean isEndOfMonth() {
		return calendar.getActualMaximum( Calendar.DATE )==calendar.get( Calendar.DATE );
	}

	/**
	 * test if this day is the last day of the year
	 *
	 * @return  true if this day is the last day of the year
	 */
	public boolean isEndOfYear() {
		return isEndOfMonth() && (calendar.get( Calendar.MONTH )==Calendar.DECEMBER);
	}

	/**
	 * test if this day is equal to the other day
	 *
	 * @param   other  the day to test
	 *
	 * @return  true if this day is equal to the other day.
	 */
	public boolean isEqual(Day other) {
		return compareTo( other )==0;
	}

	/**
	 * test if this day is the first day of the month
	 *
	 * @return  true if this day is the first day of the month
	 */
	public boolean isFirstOfMonth() {
		return calendar.getActualMinimum( Calendar.DATE )==calendar.get( Calendar.DATE );
	}

	/**
	 * test if this day is the first day of the year
	 *
	 * @return  true if this day is the first day of the year
	 */
	public boolean isFirstOfYear() {
		return isFirstOfMonth() && (calendar.get( Calendar.MONTH )==Calendar.JANUARY);
	}

	/**
	 * get the first monday in this week considering this day. If the monday of this week is not in the same month, the first monday of the month is returned
	 *
	 * @return  a "monday" Day
	 */
	public Day getFirstOpenDayOfTheWeek() {
		Day d = this;
		while(d.calendar.get( Calendar.DAY_OF_WEEK )!=Calendar.MONDAY) {
			if(d.previous().getMonth()!=d.getMonth()) {
				d = d.nextWeek();
			} else {
				d = d.previous();
			}
		} // end while
		return d;
	} // end method getFirstOpenDayOfTheWeek

	/**
	 * get the first monday in this month considering this day.
	 *
	 * @return  a "monday" Day
	 */
	public Day getFirstOpenDayOfThisMonth() {
		if(isFirstOfMonth()) {
			if(isWeekDay()) {
				return this;
			} else {
				Day d = next();
				while(d.isWeekEndDay()) {
					d = d.next();
				}
				return d;
			} // end if
		} else {
			return new Day( getYear(), getMonth(), 1 ).getFirstOpenDayOfThisMonth();
		}
	} // end method getFirstOpenDayOfThisMonth

	/**
	 * check the leap year aspect of the current year
	 *
	 * @return  true if the current year is leap
	 */
	public boolean isInLeapYear() {
		return ((GregorianCalendar)calendar).isLeapYear( calendar.get( Calendar.YEAR ) );
	}

	/**
	 * get the friday in this week considering this day. If the friday of this week is not in the same month, the last friday of the month is returned
	 *
	 * @return  a "friday" day
	 */
	public Day getLastOpenDayOfTheWeek() {
		Day d = this;
		while(d.calendar.get( Calendar.DAY_OF_WEEK )!=Calendar.FRIDAY) {
			if(d.previous().getMonth()!=d.getMonth()) {
				d = d.previousWeek();
			} else {
				d = d.next();
			}
		} // end while
		return d;
	} // end method getLastOpenDayOfTheWeek

	/**
	 * get the last friday in this month considering this day.
	 *
	 * @return  a "friday" day
	 */
	public Day getLastOpenDayOfThisMonth() {
		if(isEndOfMonth()) {
			if(isWeekDay()) {
				return this;
			} else {
				Day d = previous();
				while(d.isWeekEndDay()) {
					d = d.previous();
				}
				return d;
			} // end if
		} else {
			Day d = new Day( getYear(), getMonth(), calendar.getActualMaximum( Calendar.DAY_OF_MONTH ) );
			return d.getLastOpenDayOfThisMonth();
		}
	} // end method getLastOpenDayOfThisMonth

	/**
	 * get the month of this day
	 *
	 * @return  the month in zero base.
	 */
	public int getMonth() {
		return calendar.get( Calendar.MONTH );
	}

	/**
	 * tests if the day is between monday and friday (included)
	 *
	 * @return  true if this day is a monday, tuesday, ... or friday
	 */
	public boolean isWeekDay() {
		return !isWeekEndDay();
	}

	/**
	 * returns a string representation (using the current Locale) of the day name
	 *
	 * @return  the name of the day
	 */
	public String getWeekDayName() {
		return new SimpleDateFormat( "EEEE" ).format( toDate() );
	}

	/**
	 * test if this day is in a week-end
	 *
	 * @return  true if this day is a saturday or a sunday.
	 */
	public boolean isWeekEndDay() {
		return (calendar.get( Calendar.DAY_OF_WEEK )==Calendar.SATURDAY) || (calendar.get( Calendar.DAY_OF_WEEK )==Calendar.SUNDAY);
	}

	/**
	 * get the year of this day
	 *
	 * @return  the year
	 */
	public int getYear() {
		return calendar.get( Calendar.YEAR );
	}
} // end class Day