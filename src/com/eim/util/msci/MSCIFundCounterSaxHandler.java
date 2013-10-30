/**
 * Title           : $Workfile: MSCIFundCounterSaxHandler.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 10/08/07 9:44 $
 * By              : $Author: Als $
 * Version number  : $Revision: 3 $
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
public class MSCIFundCounterSaxHandler
	extends DefaultHandler {
	protected Stack<String> nodePathStack = new Stack<String>(  );
	protected int fundNb = 0;

	/**
	 * Creates a new MSCIFundCounterSaxHandler object.
	 */
	public MSCIFundCounterSaxHandler(  ) {
	}

	/**
	 * JavaDoc method comments
	 *
	 * @exception SAXException Add comments
	 */
	public void startDocument(  )
	  throws SAXException {
		fundNb = 0;
		nodePathStack.push( "/" );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @exception SAXException Add comments
	 */
	public void endDocument(  )
	  throws SAXException {
		nodePathStack.pop(  );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param uri Add comments
	 * @param localName Add comments
	 * @param qName Add comments
	 *
	 * @exception SAXException Add comments
	 */
	public void endElement( String uri,
							String localName,
							String qName )
	  throws SAXException {
		if( nodePathStack.pop(  ).equals( MSCIFundNodeNames.NODE_FUND ) ) {
			fundNb++;
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param uri Add comments
	 * @param localName Add comments
	 * @param qName Add comments
	 * @param attributes Add comments
	 *
	 * @exception SAXException Add comments
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
	public int getFundNb(  ) {
		return fundNb;
	}
}
