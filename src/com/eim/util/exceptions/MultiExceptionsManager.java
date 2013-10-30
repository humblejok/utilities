/**
 * Title           : $Workfile: MultiExceptionsManager.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 3/09/09 3:12p $
 * By              : $Author: Als $
 * Version number  : $Revision: 8 $
 *
 * $History: MultiExceptionsManager.java $
 * 
 * *****************  Version 8  *****************
 * User: Als          Date: 3/09/09    Time: 3:12p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Retro-Propagation
 * 
 * *****************  Version 7  *****************
 * User: Als          Date: 10/08/07   Time: 9:31
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring for Java 5
 * 
 * *****************  Version 6  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring
 */
package com.eim.util.exceptions;

import com.eim.util.exceptions.statistic.StatisticsNotEnoughDataException;
import com.eim.util.format.DateFormatter;
import com.eim.util.messagesLoader.MessagesLoaderUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;


/**
 * MultiExceptions managing class
 */
public class MultiExceptionsManager
	implements Serializable
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 6762082994996862868L;
	//protected String				   messagesFile    = null;
	private Properties messages = new Properties();
	protected MultiExceptionsException multiExceptions;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new MultiExceptionsManager object.
	 */
	public MultiExceptionsManager() {
	}

	/**
	 * Creates a new MultiExceptionsManager object.
	 *
	 * @param  multiExceptions  DOCUMENT ME!
	 */
	public MultiExceptionsManager(MultiExceptionsException multiExceptions) {
		this.multiExceptions = multiExceptions;
	}

	/**
	 * Creates a new MultiExceptionsManager object.
	 *
	 * @param  messagesFile  DOCUMENT ME!
	 */
	public MultiExceptionsManager(String messagesFile) {
		loadMessageFile(messagesFile);
	}

	/**
	 * Creates a new MultiExceptionsManager object.
	 *
	 * @param  multiExceptions  DOCUMENT ME!
	 * @param  messagesFile     DOCUMENT ME!
	 */
	public MultiExceptionsManager(MultiExceptionsException multiExceptions, String messagesFile) {
		this.multiExceptions = multiExceptions;
		loadMessageFile(messagesFile);
	}

	//~ Methods ----------------------------------------------------------------

	private void loadMessageFile(String messageFile){
		try{
			messages = MessagesLoaderUtil.getInstance().getMessages(messageFile);
			if(messages==null){
				messages = new Properties();
			}
		}catch(Throwable t){
			messages = new Properties();
		}
	}
	
	/**
	 * @return
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append( '[' );
		for(Iterator<String> ite = this.getMessagesStack().iterator(); ite.hasNext();) {
			result.append( ite.next() );
			if(ite.hasNext()) {
				result.append( ',' );
			}
		}
		result.append( ']' );
		return result.toString();
	} // end method toString

	/**
	 * Retrieve the Exception message, if no message is associated to the exception, then a message will be searched.
	 *
	 * @param   throwable
	 *
	 * @return  The associated message
	 */
	protected String retrieveMessageForException(Throwable throwable) {
		if(throwable.getMessage()!=null) {
			return throwable.getMessage();
		}

		// Getting message from messages file
		String messageKey = throwable.getClass().getName();
		if(!messages.containsKey(messageKey)){
			//return "No associated message for " + messageKey;
			return messageKey;
		}
		
		String message = messages.getProperty(messageKey);
		// Setting parameters
		int i = getParametersNumber( message );
		if(i!=0) {
			ArrayList<String> params = getParametersFromException( throwable );
			if(params.size()==i) {
				int j = 0;
				for (String param : params) {
					message = message.replaceAll( "\\{" + j + "\\}", param );
					j++;
				}
			} // end if
		} // end if
		return message;
	} // end method retrieveMessageForException

	/**
	 * Give a collection containing all the exceptions
	 *
	 * @return  The exceptions stack
	 */
	public Collection<Throwable> getExceptionsStack() {
		ArrayList<Throwable> exceptionsList = new ArrayList<Throwable>();
		exceptionsList.add( this.multiExceptions.getCause() );
		if(this.multiExceptions.getSubExceptions()!=null) {
			exceptionsList.addAll( this.multiExceptions.getSubExceptions() );
		}
		return exceptionsList;
	} // end method getExceptionsStack

	/**
	 * Return the last message of the Exceptions stack
	 *
	 * @return  The last message
	 */
	public String getLastMessage() {
		if(this.multiExceptions==null) {
			return "No exception";
		}
		if(this.multiExceptions.getMessage()==null) {
			return retrieveMessageForException( this.multiExceptions );
		}else{
			return this.multiExceptions.getMessage();
		}
	} // end method getLastMessage

	/**
	 * Give a collection containing all the message
	 *
	 * @return  The messages stack
	 */
	public Collection<String> getMessagesStack() {
		ArrayList<String> messagesList = new ArrayList<String>();
		messagesList.add( this.getLastMessage() );
		for (Exception exception : this.multiExceptions.getSubExceptions()) {
			messagesList.add( retrieveMessageForException( exception ));
		}
		return messagesList;
	} // end method getMessagesStack

	/**
	 * @return  Returns the multiExceptions.
	 */
	public MultiExceptionsException getMultiExceptions() {
		return this.multiExceptions;
	}

	protected ArrayList<String> getParametersFromException(Throwable throwable) {
		ArrayList<String> paramsList = new ArrayList<String>();
		paramsList.add( DateFormatter.toString( ((StatisticsNotEnoughDataException)throwable).getBeforeDate() ) );
		paramsList.add( DateFormatter.toString( ((StatisticsNotEnoughDataException)throwable).getAfterDate() ) );
		return paramsList;
	} // end method getParametersFromException

	protected int getParametersNumber(String message) {
		int    result = 0;
		int    idx;
		String wrkMsg = new String( message );
		while((idx = wrkMsg.indexOf( '{' ))!=-1) {
			int end = wrkMsg.indexOf( '}' );
			if(end!=-1) {
				try {
					Integer.parseInt( wrkMsg.substring( idx + 1, end ) );
					result++;
				} catch(NumberFormatException nfe) {
					// This is not a number, skipping
				}
			} // end if
			if(idx!=(wrkMsg.length() - 1)) {
				if(end!=-1) {
					wrkMsg = wrkMsg.substring( end + 1 );
				} else {
					wrkMsg = wrkMsg.substring( idx + 1 );
				}
			} else {
				wrkMsg = "";
			}
		} // end while
		return result;
	} // end method getParametersNumber

	/**
	 * @param  messagesFile  The messagesFile to set.
	 */
	public void setMessagesFile(String messagesFile) {
		loadMessageFile(messagesFile);
	}

	/**
	 * @param  multiExceptions  The multiExceptions to set.
	 */
	public void setMultiExceptions(MultiExceptionsException multiExceptions) {
		this.multiExceptions = multiExceptions;
	}
} // end class MultiExceptionsManager
