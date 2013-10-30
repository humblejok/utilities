/**
 * Title           : $Workfile: ExceptionSerializer.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 22.06.04 18:26 $
 * By              : $Author: Als $
 * Version number  : $Revision: 4 $
 */

package com.eim.util.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;

import com.eim.util.exceptions.validation.CheckDataFailureException;

import org.jdom.Element;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
public final class ExceptionSerializer {

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new ExceptionSerializer object.
	 */
	private ExceptionSerializer() {
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param   throwable  Add comments
	 *
	 * @return  Add comments
	 */
	public static Element printToElement( Throwable throwable ) {
		if( throwable instanceof CheckDataFailureException ) {
			return ((CheckDataFailureException)throwable).printToElement();
		} else {
			Element elt = new Element( "exception" );
			elt.setAttribute( "type", throwable.getClass().getName() );
			if( throwable.getMessage() != null ) {
				elt.setAttribute( "message", throwable.getMessage() );
			}

			StringWriter sw = new StringWriter();
			throwable.printStackTrace( new PrintWriter( sw, true ) );
			elt.addContent( new Element( "stacktrace" ).addContent( sw.getBuffer().toString() ) );
			if( throwable.getCause() != null ) {
				elt.addContent( printToElement( throwable.getCause() ) );
			} else if( throwable instanceof ServletException ) {
				if( ((ServletException)throwable).getRootCause() != null ) {
					elt.addContent( printToElement( ((ServletException)throwable).getRootCause() ) );
				}
			}

			return elt;
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   throwable  Add comments
	 *
	 * @return  Add comments
	 */
	public static String printToMessage( Throwable throwable ) {
		if( throwable == null ) {
			return "An Unknown exception has occured";
		}
		if( throwable instanceof CheckDataFailureException ) {
			return ((CheckDataFailureException)throwable).printToMessage();
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append( "Exception class: " ).append( throwable.getClass().getName() ).append( "\n" );
			sb.append( "Message: " ).append( throwable.getMessage() ).append( "\n" );

			StringWriter sw = new StringWriter();
			throwable.printStackTrace( new PrintWriter( sw, true ) );
			sb.append( sw.getBuffer() ).append( "\n\n\n" );
			if( throwable.getCause() != null ) {
				sb.append( printToMessage( throwable.getCause() ) );
			} else if( throwable instanceof ServletException ) {
				sb.append( printToMessage( ((ServletException)throwable).getRootCause() ) );
			}

			return sb.toString();
		}
	}
}
