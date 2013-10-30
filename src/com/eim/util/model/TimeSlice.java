/**
 * Title           : $Workfile: TimeSlice.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 7/09/07 17:29 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 37 $
 *
 * $History: TimeSlice.java $
 * 
 * *****************  Version 37  *****************
 * User: Sdj          Date: 7/09/07    Time: 17:29
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * Implementation of Bypass status
 * 
 * *****************  Version 36  *****************
 * User: Sdj          Date: 1.06.06    Time: 10:54
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * Auto-generation of serial UID.
 * 
 * *****************  Version 35  *****************
 * User: Sdj          Date: 9.05.06    Time: 16:33
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * Code clean
 * 
 * *****************  Version 34  *****************
 * User: Sdj          Date: 30.01.06   Time: 16:10
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * V2.9.0: Bloomberg improvement
 * 
 * *****************  Version 33  *****************
 * User: Sdj          Date: 1/09/06    Time: 10:47a
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * V2.9.0: No penalty bug
 * 
 * *****************  Version 32  *****************
 * User: Sdj          Date: 6.01.06    Time: 14:44
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * V2.90: Classification search, Initialize timeslices start date
 * 
 * *****************  Version 31  *****************
 * User: Jpf          Date: 12/29/05   Time: 11:47a
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * Ajout extraction par rapport à une date donnée
 * 
 * *****************  Version 30  *****************
 * User: Sdj          Date: 22.12.05   Time: 15:49
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * 
 * *****************  Version 29  *****************
 * User: Sdj          Date: 9.12.05    Time: 11:50
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * V2.9.0: TimeSlice init
 * 
 * *****************  Version 28  *****************
 * User: Sdj          Date: 1.12.05    Time: 8:47
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * Timeslice correction
 * 
 * *****************  Version 27  *****************
 * User: Sdj          Date: 9.11.05    Time: 9:44
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * V2.9.0: Init of the timeslices
 * 
 * *****************  Version 26  *****************
 * User: Als          Date: 7.11.05    Time: 16:09
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * Add a current field
 * 
 * *****************  Version 25  *****************
 * User: Sdj          Date: 26.08.05   Time: 15:47
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * No penalties is allowing duplicate period
 */
/**
 * Title           : $Workfile: TimeSlice.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 7/09/07 17:29 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 37 $
 */
package com.eim.util.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.eim.util.date.DateUtil;
import com.eim.util.exceptions.CodingFailureException;
import com.eim.util.exceptions.validation.InvalidDateRangeException;
import com.eim.util.format.DateFormatter;
import com.eim.util.xml.TimeSliceTags;


/**
 * Model modelizing the TimeSlice entity
 */
@SuppressWarnings("unchecked")
public class TimeSlice extends IdentifiableObject implements Comparable {

	//~ Static fields/initializers ---------------------------------------------

	//~ Instance fields --------------------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 4308522690040211170L;
	/** property representing the end date value of this entity */
	public Date endDate;
	/** property representing the start date value of this entity */
	public Date startDate;
	
	public boolean current;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Simple constructor
	 */
	public TimeSlice() {
	}

	/**
	 * Clone constructor the values are only copied from the clone. ie: No deep copy is done!!
	 *
	 * @param  clone  the object to copy the value from
	 */
	public TimeSlice(TimeSlice clone) {
		super( clone );
		this.startDate = clone.startDate;
		this.endDate   = clone.endDate;
		internalSynchro();
	}

	/**
	 * Constructor that extracts all the data from the provided JDom Element.
	 *
	 * @param  elt     the JDOM Element containing the object data
	 * @param  father  true if the provided element is the parent node of the node containing the desired data. False if the provided node is the node
	 *                 containing the Data
	 */
	public TimeSlice(Element elt, boolean father) {
		super( elt, father );
		if(father) {
			// the control of the father parameter is done in the parent class.
			elt = elt.getChild( getXmlTag() );
		}
		// Unserializing the class property: start date
		this.setStartDate( DateFormatter.stringToDateTime( elt.getChild( TimeSliceTags.START_DATE_TAG ).getText() ) );
		// Unserializing the class property: end date
		this.setEndDate( DateFormatter.stringToDateTime( elt.getChild( TimeSliceTags.END_DATE_TAG ).getText() ) );
		internalSynchro();
	} // end ctor TimeSlice

	/**
	 * Constructor that fill every property
	 *
	 * @param  id         value of the id property
	 * @param  startDate  value of the start date property
	 * @param  endDate    value of the end date property
	 */
	public TimeSlice(Long id, Date startDate, Date endDate) {
		super( id );
		this.startDate = startDate;
		this.endDate   = endDate;
		internalSynchro();
	}

	//~ Methods ----------------------------------------------------------------
	
	private void internalSynchro(){
		Date now = new Date();
		if(startDate==null){
			if(endDate==null){
				current=true;
			}else{
				current = now.compareTo(endDate)<0;
			}
		}else{
			if(endDate==null){
				current=now.compareTo(startDate)>0;
			}else{
				current = now.compareTo(endDate)<0 && now.compareTo(startDate)>0;
			}
		}
	}
	
	public static final Date getMaxEndDateOfTimeSlices(Collection<TimeSlice> timeSlices) {
		Date maxEndDate = null;
		for (TimeSlice ts : timeSlices) {
			if (ts.getEndDate()!=null) {
				if (maxEndDate==null) {
					maxEndDate = ts.getEndDate();
				} else if (maxEndDate.before(ts.getEndDate())) {
					maxEndDate = ts.getEndDate();
				}
			}
		}
		return maxEndDate;
	}

	/**
	 * Given a timeslice and its associated method, it computes a string identifier that define the group associated to the given TimeSlice
	 * 
	 * @param timeSlice The timeslice to analyze
	 * @param groupCheck The list of methods that helps to determine the group of this timeslice
	 * @return A string identifier that define the group associated to the given TimeSlice
	 */
	public static final String computeTimeSliceGroupId(TimeSlice timeSlice,Method[] groupCheck) {
		StringBuffer groupName = new StringBuffer();
		try {
			for(int i = 0; i<groupCheck.length; i++) {
				Object result = groupCheck[i].invoke( timeSlice, new Object[0] );
				if (result==null) {
					result = "";
				}
				groupName.append(result);
			}
		} catch(IllegalArgumentException e) {
			throw new CodingFailureException( "Incorrect method name for object " + timeSlice.getClass().getName() );
		} catch(IllegalAccessException e) {
			throw new CodingFailureException( "Incorrect method name for object " + timeSlice.getClass().getName() );
		} catch(InvocationTargetException e) {
			throw new CodingFailureException( "Incorrect method name for object " + timeSlice.getClass().getName() );
		}
		if (groupName.length()==0) {
			return "none";
		}
		return groupName.toString();
	}
	
	/**
	 * Add an object in a list of timeslice.  -> If list is empty, add the new object.  -> If list is not empty: -> add the new object when: -> The last
	 * TimeSlice object has no end date specified and the start date of the new one is after the start date of the old one. -> The last TimeSlice object has an
	 * end date specified and it is the same as the start date of the new one. -> The first TimeSlice object has the start date that is the same as the end date
	 * of the new one, means that the new TimeSlice come before the current first one.  In the other cases, throw an exception!!!
	 *
	 * @param   list          The original list where the new timeslice will be added
	 * @param   newTimeSlice  The new timeslice
	 * @param   groupCheck	  The list of methods that helps to determine the group of this timeslice
	 *
	 * @return  Return the timeslices list with the new timeslice appended
	 *
	 * @throws  InvalidDateRangeException  If the timeslice to add doesn't have valid start or/and end date
	 */
	public static final ArrayList addToList(ArrayList list, TimeSlice newTimeSlice, Method[] groupCheck) throws InvalidDateRangeException {
		ArrayList currentGroup = null;
		HashMap groupsMap = null;
		// If there is no group, the full list is used
		if (groupCheck.length==0) {
			currentGroup = list;
			groupsMap = new HashMap();
			groupsMap.put("no-type",currentGroup);
		} else {
			// otherwise we just extract the timeslices list that correspond to same groupCheck criteria
			groupsMap = getAllTimeSlicesGroups(list,groupCheck);
			String currentType = computeTimeSliceGroupId(newTimeSlice,groupCheck);
			currentGroup = (ArrayList)groupsMap.get(currentType);
		}
		// if we create a new group when adding that timeslice, we just add the timeslice and return
		if (currentGroup==null) {
			if(list==null) {
				list = new ArrayList();
			}
			list.add( newTimeSlice );
			return list;
		}
		if(currentGroup.size()>0) {
			Iterator it = currentGroup.iterator();
			Date firstDate = getBoundingDates( currentGroup, newTimeSlice, groupCheck );
			while(it.hasNext()) {
				TimeSlice current = (TimeSlice)it.next();
				setEndDateToTimeSlice( newTimeSlice, current, firstDate );
			} // end while
		} // end if
		currentGroup.add( newTimeSlice );
		if (groupCheck.length!=0) {
			list.add( newTimeSlice );
		}
		checkAllTimeSlicesGroupValidity(groupsMap);
		return list;
	} // end method addToList

	/**
	 * Given a timeslices array, it returns a hashmap containing a list of next default dates depending on the timeslices groups. It's used to automatically set the new timeslice start
	 * date in the wizards. At least, the hashmap contains the [code]default[/code] key, which is valued with [code]defaultDate[/code] if not null
	 *  or current date otherwise.
	 * @param timeSlices A list of timeslices
	 * @param defaultDate A default date
	 * @param groupCheck The list of methods that helps to determine the group of this timeslice
	 * @return The hashmap that contains the list of possible dates
	 */
	public static HashMap getTimeSlicesArraysNextDate(Collection timeSlices,Date defaultDate,Method[] groupCheck) {
		HashMap groups = getAllTimeSlicesGroups(timeSlices,groupCheck);
		HashMap results = new HashMap();
		// Set the default item
		if (defaultDate!=null) {
			results.put("default", DateFormatter.toInputString((Date)defaultDate.clone()));
		} else {
			results.put("default", DateFormatter.toInputString(Calendar.getInstance().getTime()));
		}
		// For each group
		for (Iterator it = groups.keySet().iterator();it.hasNext();) {
			String currentItem = (String)it.next();
			Object [] timeslicesToSort = ((Collection)groups.get(currentItem)).toArray();
			Arrays.sort(timeslicesToSort);
			// If there is no end date to the current timeslice, set the item in the hashmap to current date
			if (((TimeSlice)timeslicesToSort[timeslicesToSort.length-1]).getEndDate()==null) {
				Calendar cal = Calendar.getInstance();
				cal.setLenient( true );
				results.put(currentItem,DateFormatter.toInputString(cal.getTime()));
			// otherwise set it to the end date + 1 day
			} else {
				Calendar cal = Calendar.getInstance();
				cal.setLenient( true );
				cal.setTime( ((TimeSlice)timeslicesToSort[timeslicesToSort.length-1]).getEndDate() );
				cal.add( Calendar.DAY_OF_YEAR, 1 );
				results.put(currentItem,DateFormatter.toInputString(cal.getTime()));
			}
		}
		return results;
	}
	
	/**
	 * Give the list of groups that exists in this timeslices list
	 * 
	 * @param list The timeslices list
	 * @param groupCheck The list of methods that helps to determine the group of this timeslice
	 * @return Thelist as a hashmap
	 */
	public static HashMap getAllTimeSlicesGroups(Collection list,Method[] groupCheck) {
		if ((list==null) || (list.size()<1)) {
			return new HashMap();
		}
		HashMap storageMap = new HashMap();
		// Creating groups of timeslices
		Iterator  it	    = list.iterator();
		while(it.hasNext()) {
			String groupName;
			TimeSlice current     = (TimeSlice)it.next();
			groupName = computeTimeSliceGroupId(current,groupCheck);
			if (!storageMap.containsKey(groupName.toString())) {
				ArrayList tmpArray = new ArrayList();
				tmpArray.add(current);
				storageMap.put(groupName.toString(),tmpArray);
			} else {
				((ArrayList)storageMap.get(groupName.toString())).add(current);
			}
		}
		return storageMap;
	}
	
	/**
	 * Checking if all the groups of timeslices are correct (due to edition of typed timeslices like status, ...)
	 * @param list The list of timeslices to check
	 * @param groupCheck The list of methods that helps to determine the group of this timeslice
	 * @return Nothing
	 * 
	 * @throws InvalidDateRangeException If the timeslices list is not valid 
	 */
	public static void checkAllTimeSlicesGroupValidity(HashMap storageMap) 
		throws InvalidDateRangeException {
		// Checking groups
		for (Iterator it = storageMap.keySet().iterator();it.hasNext();) {
			Object[] objList = ((Collection)storageMap.get(it.next())).toArray();
			Arrays.sort( objList );
			for(int i = 0; i<(objList.length - 1); i++) {
				if(((TimeSlice)objList[i]).getEndDate()!=null) {
					Calendar cal = Calendar.getInstance();
					cal.setLenient( true );
					cal.setTime( ((TimeSlice)objList[i]).getEndDate() );
					cal.add( Calendar.DAY_OF_YEAR, 1 );
					if(!cal.getTime().equals( ((TimeSlice)objList[i + 1]).getStartDate() )) {
						throw new InvalidDateRangeException( "Unable to add the element, invalid date range!" );
					}
				} else {
					throw new InvalidDateRangeException( "Unable to add the element, invalid date range!" );
				} // end if
			} // end for
		}
	}
	
	/**
	 * adds to the given node a collection of sub-element corresponding to the xml-serialization of each object (of this class) from the given List.
	 *
	 * @param  collection  the collection containing the objects to xml-serialize
	 * @param  node        the node to add the new serialized objects
	 */
	public static <T extends TimeSlice> void collectionToXml(Collection<T> collection, Element node) {
		Iterator<T> it = collection.iterator();
		while(it.hasNext()) {
			node.addContent( it.next().toXml() );
		}
	}

	/**
	 * Generates a new List containing allocated and set Objects of this Class according with the content of the provided Element
	 *
	 * @param   elt  the JDOM node containg a collection of sub-element corresponding to the xml-serialization of this Class of Object
	 *
	 * @return  a new List of allocated objects of this Class
	 *
	 * @throws  IllegalArgumentException  DOCUMENT ME!
	 */
	public static List extractListFromXml(Element elt) {
		if(elt==null) {
			throw new IllegalArgumentException( "The XML node cannot be null!" );
		}
		List     result = new ArrayList();
		Iterator eltIt  = elt.getChildren( new TimeSlice().getXmlTag() ).iterator();
		while(eltIt.hasNext()) {
			result.add( new TimeSlice( ((Element)eltIt.next()), false ) );
		}
		return result;
	} // end method extractListFromXml

	/**
	 * JavaDoc method comments
	 *
	 * @param   object  Add comments
	 *
	 * @return  Add comments
	 */
	public int compareTo(Object object) {
		TimeSlice other = ((TimeSlice)object);
		return this.startDate.compareTo( other.startDate );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param  map         Add comments
	 * @param  overrideId  Add comments
	 */
	public void populate(Map map, boolean overrideId) {
		super.populate( map, overrideId );
		if(map.get( TimeSliceTags.START_DATE_TAG )!=null) {
			startDate = (Date)map.get( TimeSliceTags.START_DATE_TAG );
		}
		if(map.get( TimeSliceTags.END_DATE_TAG )!=null) {
			if(map.get( TimeSliceTags.END_DATE_TAG ) instanceof Date) {
				endDate = (Date)map.get( TimeSliceTags.END_DATE_TAG );
			} else {
				endDate = null;
			}
		} // end if
		internalSynchro();
	} // end method populate

	/**
	 * JavaDoc method comments
	 *
	 * @param   object      Add comments
	 * @param   overrideId  Add comments
	 *
	 * @throws  CodingFailureException  DOCUMENT ME!
	 */
	public void populate(SerializableObject object, boolean overrideId) {
		super.populate( object, overrideId );
		if(object instanceof TimeSlice) {
			startDate = ((TimeSlice)object).startDate;
			endDate   = ((TimeSlice)object).endDate;
		} else {
			throw new CodingFailureException( "Wrong object for populate" );
		}
		internalSynchro();
	} // end method populate

	/**
	 * Generates a JDOM Element that contains all the current data of this object.
	 *
	 * @return  a new JDOM Element filled with this object's data
	 */
	public Element toXml() {
		Element root  = super.toXml();
		Element child;
		child		  = new Element( TimeSliceTags.START_DATE_TAG );
		if(this.startDate!=null) {
			child.addContent( DateFormatter.toString( this.startDate ) );
		}
		root.addContent( child );
		child = new Element( TimeSliceTags.END_DATE_TAG );
		if(this.endDate!=null) {
			child.addContent( DateFormatter.toString( this.endDate ) );
		}
		root.addContent( child );
		return root;
	} // end method toXml

	/**
	 * Just auto assign ending date if unset
	 *
	 * @param   newTimeSlice  The TimeSlice to add
	 * @param   current       An existing TimeSlice
	 * @param   firstDate     The first date of the timeslices covered period
	 *
	 * @throws  InvalidDateRangeException  If the new TimeSlice is overlapping with the current one
	 */
	private static void setEndDateToTimeSlice(TimeSlice newTimeSlice, TimeSlice current, Date firstDate)
	{
		if((current.getEndDate()==null) && current.getStartDate().before( newTimeSlice.getStartDate() )) {
			Calendar cal = Calendar.getInstance();
			cal.setLenient( true );
			cal.setTime( newTimeSlice.getStartDate() );
			cal.add( Calendar.DAY_OF_YEAR, -1 );
			current.setEndDate( cal.getTime() );
		} // end if
		if((firstDate!=null) && (newTimeSlice.getEndDate()==null) && (newTimeSlice.getStartDate().before( firstDate ) || newTimeSlice.getStartDate().equals( firstDate ))) {
			newTimeSlice.setEndDate( (Date)firstDate.clone() );
		} // end if
	} // end method checkWhenAddingTimeSlice

	/**
	 * Extract first date of the timeslices group
	 *
	 * @param   list          TimeSlices list
	 * @param   newTimeSlice  The timeslice to add
	 * @param   groupCheck  The list of methods that helps to determine the group of this timeslice
	 *
	 * @return lowest date
	 *
	 * @throws  InvalidDateRangeException
	 * @throws  CodingFailureException
	 */
	public static final Date getBoundingDates(ArrayList list, TimeSlice newTimeSlice, Method[] groupCheck)
	{
		Date	  boundDate = null;
		Object[] objList = list.toArray();
		Arrays.sort( objList );
		if (objList.length>0) {
			Calendar cal = Calendar.getInstance();
			cal.setLenient( true );
			cal.setTime( ((TimeSlice)objList[0]).getStartDate() );
			cal.add( Calendar.DAY_OF_YEAR, -1 );
			boundDate = cal.getTime();
		}
		return boundDate;
	} // end method getBoundingDates

	/**
	 * @param   timeSlices  Collection d'objets avec plage de validité
	 *
	 * @return   L 'objet valide à la date courante
	 */
	public static final TimeSlice getCurrent(Collection timeSlices) {
		return getCurrent(timeSlices, new Date( System.currentTimeMillis() ));
	} // end method getCurrent

	/**
	 * @param   timeSlices  Collection d'objets avec plage de validité
	 * @param datpAdate une date donnée
	 *
	 * @return  L 'objet valide à la date donnée
	 */
	public static final TimeSlice getCurrent(Collection timeSlices, Date datpAdate) {
		if(timeSlices==null) {
			return null;
		}
		Iterator it = timeSlices.iterator();
		while(it.hasNext()) {
			TimeSlice ts = (TimeSlice)it.next();
			if(ts.startDate.compareTo( DateUtil.roundToDay( datpAdate ) )<=0) {
				if((ts.endDate==null) || (ts.endDate.compareTo( DateUtil.roundToDay( datpAdate ) )>=0)) {
					return ts;
				}
			}
		} // end while
		return null;
	} // end method getCurrent
	
	/**
	 * JavaDoc method comments
	 *
	 * @param   timeslices  Add comments
	 *
	 * @return  Add comments
	 */
	public static final Collection getCurrentAndFuture(Collection timeslices) {
		ArrayList results = new ArrayList();
		if(timeslices!=null) {
			Iterator it = timeslices.iterator();
			while(it.hasNext()) {
				TimeSlice ts = (TimeSlice)it.next();
				if(ts.startDate.compareTo( new Date( System.currentTimeMillis() ) )<=0) {
					if((ts.endDate==null) || (ts.endDate.compareTo( DateUtil.roundToDay( new Date( System.currentTimeMillis() ) ) )>=0)) {
						results.add( ts );
					}
				} else {
					results.add( ts );
				}
			} // end while
		} // end if
		Collections.sort( results );
		return results;
	} // end method getCurrentAndFuture
	
	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public boolean isCurrentlyValid() {
		return isCurrent();
	}

	/**
	 * Gets the current value of the end date property
	 *
	 * @return  the current value of the end date property
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets a new value for the end date property
	 *
	 * @param  endDate  the new value of the end date property
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		internalSynchro();
	}

	/**
	 * Gets the current value of the start date property
	 *
	 * @return  the current value of the start date property
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets a new value for the start date property
	 *
	 * @param  startDate  the new value of the start date property
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		internalSynchro();
	}

	public boolean isCurrent() {
		return current;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = PRIME * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TimeSlice other = (TimeSlice) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
} // end class TimeSlice
