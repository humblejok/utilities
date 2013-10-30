/**
 * Title           : $Workfile: SystemUtil.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 8/01/08 18:18 $
 * By              : $Author: Als $
 * Version number  : $Revision: 4 $
 *
 * $History: SystemUtil.java $
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 8/01/08    Time: 18:18
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 12.05.05   Time: 10:51
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Cache problems
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 22.04.05   Time: 11:10
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * 
 * *****************  Version 1  *****************
 * User: Als          Date: 21.04.05   Time: 18:08
 * Created in $/Current/Projects/utilities/src/com/eim/util
 */
package com.eim.util;

import com.eim.util.exceptions.CodingFailureException;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.io.File;
import java.io.IOException;

import java.rmi.MarshalledObject;

@SuppressWarnings("unchecked")
public final class SystemUtil {

	//~ Static fields/initializers ---------------------------------------------

	/**
	 * 
	 */
	public static final String NEW_LINE = System.getProperty( "line.separator" );

	//~ Constructors -----------------------------------------------------------

	/**
	 * 
	 */
	private SystemUtil() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param   o
	 *
	 * @return
	 *
	 * @throws  CodingFailureException
	 */
	public static Object deepClone(Object o) {
		try {
			return new MarshalledObject( o ).get();
		} catch(IOException e) {
			throw new CodingFailureException( "Cloning problem!", e );
		} catch(ClassNotFoundException e) {
			throw new CodingFailureException( "Cloning problem!", e );
		}
	} // end method deepClone

	/**
	 * @param  message
	 * @param  logger
	 * @param  priority
	 */
	public static void printStackTrace(String message, Logger logger, Priority priority) {
		StackTraceElement[] fulltrace = new Throwable().fillInStackTrace().getStackTrace();
		StackTraceElement[] trace     = new StackTraceElement[fulltrace.length - 1];
		System.arraycopy( fulltrace, 1, trace, 0, trace.length );
		StringBuffer buffer = new StringBuffer( message );
		if(trace.length>0) {
			buffer.append( NEW_LINE );
		}
		for(int i = 0; i<trace.length; i++) {
			StackTraceElement stackTraceElement = trace[i];
			buffer.append( stackTraceElement.toString() ).append( NEW_LINE );
		}
		logger.log( priority, buffer );
	} // end method printStackTrace
	
	/**
	 * Delete safely a file.
	 * 
	 * @param filePath the file to delete
	 * @return true if the document could be deleted, false otherwise.
	 */
	public static boolean deleteFileSafely(String filePath){
		if(filePath!=null){
			return new File(filePath).delete();
		}else{
			return true;
		}
	}
	
} // end class SystemUtil
