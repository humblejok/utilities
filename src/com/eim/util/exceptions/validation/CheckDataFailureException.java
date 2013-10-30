/**
 * Title           : $Workfile: CheckDataFailureException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:32 $
 * By              : $Author: Als $
 * Version number  : $Revision: 6 $
 *
 * $History: CheckDataFailureException.java $
 * 
 * *****************  Version 6  *****************
 * User: Als          Date: 10/08/07   Time: 9:32
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * Refactoring for Java 5
 * 
 * *****************  Version 5  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * Refactoring
 */
package com.eim.util.exceptions.validation;

import com.eim.util.exceptions.EIMException;
import com.eim.util.exceptions.ExceptionSerializer;

import org.jdom.Element;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.HashMap;
import java.util.Iterator;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision: 6 $, $Date: 10/08/07 9:32 $
 */
public class CheckDataFailureException
	extends EIMException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -9121072519012978691L;
	private final HashMap<ValidationParameter, InvalidDataException> failures;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Constructor for the CheckDataFailureException object
	 */
	public CheckDataFailureException() {
		super();
		this.failures = new HashMap<ValidationParameter, InvalidDataException>();
	}

	/**
	 * Constructor for the CheckDataFailureException object
	 *
	 * @param  msg  Description of the Parameter
	 */
	public CheckDataFailureException(final String msg) {
		super( msg );
		this.failures = new HashMap<ValidationParameter, InvalidDataException>();
	}

	/**
	 * Constructor for the CheckDataFailureException object
	 *
	 * @param  msg                  Description of the Parameter
	 * @param  validationParameter  Description of the Parameter
	 * @param  exception            Description of the Parameter
	 */
	public CheckDataFailureException(final String msg, final ValidationParameter validationParameter, final InvalidDataException exception) {
		super( msg );
		this.failures = new HashMap<ValidationParameter, InvalidDataException>();
		this.addFailure( validationParameter, exception );
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Adds a feature to the Failure attribute of the CheckDataFailureException object
	 *
	 * @param  cdfe  The feature to be added to the Failure attribute
	 */
	public final void addFailure(final CheckDataFailureException cdfe) {
		if(cdfe!=null) {
			this.failures.putAll( cdfe.failures );
		}
	}

	/**
	 * Adds a feature to the Failure attribute of the CheckDataFailureException object
	 *
	 * @param  validationParameter  The feature to be added to the Failure attribute
	 * @param  exception            The feature to be added to the Failure attribute
	 */
	public final void addFailure(final ValidationParameter validationParameter, final InvalidDataException exception) {
		this.failures.put( validationParameter, exception );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param  fieldAlias            DOCUMENT ME!
	 * @param  fieldValue            DOCUMENT ME!
	 * @param  invalidDataException  DOCUMENT ME!
	 */
	public final void addFailure(final String fieldAlias, final Object fieldValue, final InvalidDataException invalidDataException) {
		this.failures.put( new ValidationParameter( fieldAlias, fieldValue ), invalidDataException );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public final boolean hasFailures() {
		return !failures.isEmpty();
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final Element printToElement() {
		Element elt = new Element( "exception" );
		elt.setAttribute( "type", this.getClass().getName() );
		if(this.getMessage()!=null) {
			elt.setAttribute( "message", this.getMessage() );
		}
		Element  failuresElt = new Element( "failures" );
		Iterator<ValidationParameter> it			 = failures.keySet().iterator();
		while(it.hasNext()) {
			ValidationParameter  vp		 = it.next();
			InvalidDataException ide     = this.failures.get( vp );
			Element				 failure = new Element( "failure" );
			failure.setAttribute( "alias", vp.getAlias() );
			if(vp.getParameter()!=null) {
				failure.setAttribute( "parameter", vp.getParameter().toString() );
			}
			failure.addContent( ExceptionSerializer.printToElement( ide ) );
			failuresElt.addContent( failure );
		} // end while
		elt.addContent( failuresElt );
		return elt;
	} // end method printToElement

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final String printToMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append( "Exception class: " ).append( this.getClass().getName() ).append( "\n" );
		sb.append( "Message: " ).append( this.getMessage() ).append( "\n" );
		StringWriter sw = new StringWriter();
		this.printStackTrace( new PrintWriter( sw, true ) );
		sb.append( sw.getBuffer() ).append( "\n\n\n" );
		Iterator<ValidationParameter> it = failures.keySet().iterator();
		while(it.hasNext()) {
			ValidationParameter  vp  = it.next();
			InvalidDataException ide = this.failures.get( vp );
			sb.append( "Failure: \n" );
			sb.append( "Alias:\t" ).append( vp.getAlias() );
			if(vp.getParameter()!=null) {
				sb.append( "Parameter:\t" ).append( vp.getParameter().toString() );
			}
			sb.append( "Exception:\t" ).append( ExceptionSerializer.printToMessage( ide ) );
			sb.append( "\n\n\n" );
		} // end while
		return sb.toString();
	} // end method printToMessage

	/**
	 * Gets the failures attribute of the CheckDataFailureException object
	 *
	 * @return  The failures value
	 */
	public final HashMap<ValidationParameter, InvalidDataException> getFailures() {
		return failures;
	}
} // end class CheckDataFailureException
