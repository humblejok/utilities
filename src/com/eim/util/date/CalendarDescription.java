package com.eim.util.date;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class CalendarDescription implements Serializable {
	
	
	private static Logger logger = Logger.getLogger(CalendarDescription.class);
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 7211356757570058985L;
	
	protected String name;
	protected ArrayList<Integer> weekEndDays = new ArrayList<Integer>();
	protected ArrayList<Date> offDays = new ArrayList<Date>();
	
	public CalendarDescription(String name) {
		this.name = name;
	}
	
	public boolean isDayOff(Date date) {
		Date wrkDate = DateUtil.roundToDay(date);
		Calendar wrkCalendar = Calendar.getInstance();
		wrkCalendar.setTime(wrkDate);
		return weekEndDays.contains(wrkCalendar.get(Calendar.DAY_OF_WEEK)) || offDays.contains(wrkDate);
	}
	
	public Date getPreviousOpenDayWithXDaysShift(Date date,int xDays) {
		Date result = DateUtil.addOrRemoveDays(date,-xDays);
		while (isDayOff(result)) {
			result = DateUtil.removeOneDay(result);
		}
		return result;
	}
	
	public Date getNextOpenDayWithXDaysShift(Date date,int xDays) {
		Date result = DateUtil.addOrRemoveDays(date,xDays);
		while (isDayOff(result)) {
			result = DateUtil.addOneDay(result);
		}
		return result;
	}
	
	public ArrayList<Integer> addWeekEndDay(int calendarDay) {
		if (!weekEndDays.contains(calendarDay)) {
			weekEndDays.add(calendarDay);
		}
		return weekEndDays;
	}
	
	public ArrayList<Date> addOffDay(String format,String input, int year) {
		logger.debug("Received date to parse: " + format + "," + input + "," + year);
		Calendar wrkCalendar = Calendar.getInstance();
		wrkCalendar.setTime(DateUtil.roundToDay(new Date()));
		
		if (year!=0) {
			wrkCalendar.set(Calendar.YEAR, year);
		}
		
		if (format.equals("yyyy-MM-dd")) {
			if (input.startsWith("****")) {
				return this.addOffDay(format.substring(5), input.substring(5),0);
			} else {
				return this.addOffDay(format.substring(5), input.substring(5),Integer.parseInt(input.substring(0,4)));
			}
		} else if (format.equals("MM-dd")){
			String month = input.substring(0,2);
			String day = input.substring(3,5);
			if (month.startsWith("**")) {
				wrkCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
				for (int i=Calendar.JANUARY;i<=Calendar.DECEMBER;i++) {
					wrkCalendar.set(Calendar.MONTH, i);
					logger.info("\tAdding date:" + wrkCalendar.getTime());
					offDays.add(wrkCalendar.getTime());
				}
			} else if (day.endsWith("**")) {
				wrkCalendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
				for (int i=wrkCalendar.getActualMinimum(Calendar.MONTH);i<=wrkCalendar.getActualMaximum(Calendar.MONTH);i++) {
					logger.info("\tAdding date:" + wrkCalendar.getTime());
					offDays.add(wrkCalendar.getTime());
				}
			} else {
				wrkCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
				wrkCalendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
				logger.info("\tAdding date:" + wrkCalendar.getTime());
				offDays.add(wrkCalendar.getTime());
			}
		} else {
			return offDays;
		}
		
		return offDays;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getWeekEndDays() {
		return weekEndDays;
	}

	public void setWeekEndDays(ArrayList<Integer> weekEndDays) {
		this.weekEndDays = weekEndDays;
	}

	public ArrayList<Date> getOffDays() {
		return offDays;
	}

	public void setOffDays(ArrayList<Date> offDays) {
		this.offDays = offDays;
	}
	
	public String toString() {
		return "[" + name + "] = [Week-end days: " + weekEndDays.toString() + " - Days off:" + offDays.toString() + "]";
	}
}