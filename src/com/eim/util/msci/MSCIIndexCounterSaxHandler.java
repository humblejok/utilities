/**
 * Title           : $Workfile: MSCIIndexCounterSaxHandler.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 10/08/07 9:44 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 */

package com.eim.util.msci;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * JavaDoc class comment
 *
 * @author als
 */
public class MSCIIndexCounterSaxHandler
	extends DefaultHandler {
	protected Stack<String> nodePathStack = new Stack<String>(  );
	protected int indexNb = 0;

	/**
	 * Creates a new MSCIIndexCounterSaxHandler object.
	 */
	public MSCIIndexCounterSaxHandler(  ) {
	}

	/**
	 * JavaDoc method comments
	 *
	 * @exception SAXException 	Add comments
	 */
	public void startDocument(  )
	  throws SAXException {
		indexNb = 0;
		nodePathStack.push( "/" );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @exception SAXException 	Add comments
	 */
	public void endDocument(  )
	  throws SAXException {
		nodePathStack.pop(  );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param uri 	Add comments
	 * @param localName 	Add comments
	 * @param qName 	Add comments
	 *
	 * @exception SAXException 	Add comments
	 */
	public void endElement( String uri,
							String localName,
							String qName )
	  throws SAXException {
		if( nodePathStack.pop(  ).equals( MSCIIndexNodeNames.NODE_INDEX ) ) {
			indexNb++;
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param uri 	Add comments
	 * @param localName 	Add comments
	 * @param qName 	Add comments
	 * @param attributes 	Add comments
	 *
	 * @exception SAXException 	Add comments
	 */
	public void startElement( String uri,
							  String localName,
							  String qName,
							  Attributes attributes )
	  throws SAXException {
		nodePathStack.push( qName );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return 		Add comments
	 */
	public int getIndexNb(  ) {
		return indexNb;
	}
}
