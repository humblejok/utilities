/**
 * Title           : $Workfile: TimeSliceTags.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 3/18/05 9:51a $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 3 $
 */
package com.eim.util.xml;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 3 $, $Date: 3/18/05 9:51a $
 */
public interface TimeSliceTags
extends IdentifiableObjectTags {

	//~ Instance fields ------------------------------------------------------------------------------

	/** the xml tag for the end date property */
	String END_DATE_TAG = "end-date";

	/** the xml tag for the start date property */
	String START_DATE_TAG = "start-date";

}
