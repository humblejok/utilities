/**
 * Title           : $Workfile: MSCIIndexTrackSaxHandler.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:44 $
 * By              : $Author: Als $
 * Version number  : $Revision: 6 $
 *
 * $History: MSCIIndexTrackSaxHandler.java $
 * 
 * *****************  Version 6  *****************
 * User: Als          Date: 10/08/07   Time: 9:44
 * Updated in $/Current/Projects/utilities/src/com/eim/util/msci
 * Refactoring for Java 5
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/msci
 * Refactoring
 */
package com.eim.util.msci;

import org.apache.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
@SuppressWarnings("unchecked")
public class MSCIIndexTrackSaxHandler extends DefaultHandler {

	//~ Static fields/initializers ---------------------------------------------

	private static Logger logger = Logger.getLogger( MSCIIndexTrackSaxHandler.class );

	//~ Instance fields --------------------------------------------------------

	protected Date	    extractionDate		 = new Date();
	protected HashMap<String, MSCIImportedData>   indexes;
	protected ArrayList<String> indexIds;
	protected ArrayList listeners;
	protected boolean   loadAllIndexes		 = true;
	private Document    currentIndexDocument = null;
	private String	    currentIndexId		 = null;
	private Node	    currentIndexNode     = null;
	private Stack<String>	    nodePathStack		 = null;
	private boolean     skipCurrentIndex     = false;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new MSCIIndexTrackSaxHandler object.
	 *
	 * @param  listeners  DOCUMENT ME!
	 */
	public MSCIIndexTrackSaxHandler(ArrayList listeners) {
		loadAllIndexes = true;
		indexes		   = new HashMap<String, MSCIImportedData>();
		indexIds	   = new ArrayList<String>();
		this.listeners = listeners;
	}

	/**
	 * Creates a new MSCIIndexTrackSaxHandler object.
	 *
	 * @param  msciIndexId  DOCUMENT ME!
	 * @param  listeners    DOCUMENT ME!
	 */
	public MSCIIndexTrackSaxHandler(String msciIndexId, ArrayList listeners) {
		loadAllIndexes = false;
		indexes		   = new HashMap<String, MSCIImportedData>();
		indexIds	   = new ArrayList<String>();
		indexIds.add( msciIndexId );
		this.listeners = listeners;
	} // end ctor MSCIIndexTrackSaxHandler

	/**
	 * Creates a new MSCIIndexTrackSaxHandler object.
	 *
	 * @param  msciIndexIds  DOCUMENT ME!
	 * @param  listeners     DOCUMENT ME!
	 */
	public MSCIIndexTrackSaxHandler(ArrayList<String> msciIndexIds, ArrayList listeners) {
		super();
		loadAllIndexes = false;
		indexes		   = new HashMap<String, MSCIImportedData>();
		indexIds	   = new ArrayList<String>(msciIndexIds);
		this.listeners = listeners;
	} // end ctor MSCIIndexTrackSaxHandler

	/**
	 * Creates a new MSCIIndexTrackSaxHandler object.
	 *
	 * @param  msciIndexIds  DOCUMENT ME!
	 * @param  listeners     DOCUMENT ME!
	 */
	public MSCIIndexTrackSaxHandler(String[] msciIndexIds, ArrayList listeners) {
		loadAllIndexes = false;
		indexes		   = new HashMap<String, MSCIImportedData>();
		indexIds	   = new ArrayList<String>();
		for(int i = 0; i<msciIndexIds.length; i++) {
			indexIds.add( msciIndexIds[i] );
		}
		this.listeners = listeners;
	} // end ctor MSCIIndexTrackSaxHandler

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param      ch      Add comments
	 * @param      start   Add comments
	 * @param      length  Add comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void characters(char[] ch, int start, int length)
					throws SAXException
	{
		if(!skipCurrentIndex) {
			if(MSCIIndexNodeNames.NODE_MSCI_INDEX_CODE.equals( nodePathStack.peek() )) {
				if(currentIndexId!=null) {
					currentIndexId = currentIndexId + new String( ch, start, length );
				} else {
					currentIndexId = new String( ch, start, length );
				}
			} // end if
			if((currentIndexNode!=null)
				   && (nodePathStack.contains( MSCIIndexNodeNames.NODE_PERFORMANCE_DATA ) || nodePathStack.contains( MSCIIndexNodeNames.NODE_INDEX_NAME )
						   || nodePathStack.contains( MSCIIndexNodeNames.NODE_PERFORMANCE_DATA ))) {
				currentIndexNode.appendChild( currentIndexDocument.createTextNode( new String( ch, start, length ) ) );
			}
		} // end if
	} // end method characters

	/**
	 * JavaDoc method comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void endDocument()
					 throws SAXException
	{
		nodePathStack.pop();
		if(!nodePathStack.isEmpty()) {
			throw new SAXException( "Stack is not empty at the end of the document processing" );
		}
		nodePathStack = null;
		if(logger.isDebugEnabled()) {
			logger.debug( "Ending document analysis" );
		}
	} // end method endDocument

	/**
	 * JavaDoc method comments
	 *
	 * @param      uri        Add comments
	 * @param      localName  Add comments
	 * @param      qName      Add comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void endElement(String uri, String localName, String qName)
					throws SAXException
	{
		if(MSCIIndexNodeNames.NODE_MSCI_HEDGE_FUND_INDEX_INFO.equals( qName )) {
			// it's ok we continue
		} else if(MSCIIndexNodeNames.NODE_INDEX.equals( qName )) {
			if(!skipCurrentIndex) {
				indexes.put( currentIndexId, new MSCIImportedData( currentIndexDocument, extractionDate ) );
			}
			currentIndexDocument = null;
			currentIndexNode     = null;
			currentIndexId		 = null;
		} else if(MSCIIndexNodeNames.NODE_MSCI_INDEX_CODE.equals( qName )) {
			skipCurrentIndex = !(loadAllIndexes || indexIds.contains( currentIndexId ));
			currentIndexNode = currentIndexNode.getParentNode();
			if(logger.isDebugEnabled()) {
				if(skipCurrentIndex) {
					logger.debug( "Skipping current index information analysis: " + currentIndexId + "is not interesting" );
				} else {
					logger.debug( "Analyzing fund information : " + currentIndexId + "." );
				}
			} // end if
		} else if(!skipCurrentIndex) {
			if(MSCIIndexNodeNames.NODE_PERFORMANCE_DATA.equals( qName ) || MSCIIndexNodeNames.NODE_MSCI_INDEX_CODE.equals( qName )
				   || MSCIIndexNodeNames.NODE_INDEX_NAME.equals( qName ) || nodePathStack.contains( MSCIIndexNodeNames.NODE_PERFORMANCE_DATA )) {
				currentIndexNode = currentIndexNode.getParentNode();
			} else {
				// nothing to do
			}
		} // end if-else
		nodePathStack.pop();
	} // end method endElement

	/**
	 * JavaDoc method comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void startDocument()
					   throws SAXException
	{
		nodePathStack = new Stack<String>();
		nodePathStack.push( "/" );
		if(logger.isDebugEnabled()) {
			logger.debug( "Starting document analysis" );
		}
	} // end method startDocument

	/**
	 * JavaDoc method comments
	 *
	 * @param      uri         Add comments
	 * @param      localName   Add comments
	 * @param      qName       Add comments
	 * @param      attributes  Add comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes)
					  throws SAXException
	{
		nodePathStack.push( qName );
		if(MSCIIndexNodeNames.NODE_MSCI_HEDGE_FUND_INDEX_INFO.equals( qName )) {
			try {
				extractionDate = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" ).parse( attributes.getValue( MSCIIndexNodeNames.ATTR_EXTRACT_DATE ) + " "
																					  + attributes.getValue( MSCIIndexNodeNames.ATTR_EXTRACT_TIME ) );
			} catch(ParseException e) {
				throw new SAXException( "Invalid Extraction date format", e );
			}
		} else if(MSCIIndexNodeNames.NODE_INDEX.equals( qName )) {
			try {
				currentIndexDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element indexRootNode = currentIndexDocument.createElement( qName );
				currentIndexDocument.appendChild( indexRootNode );
				currentIndexNode = indexRootNode;
				currentIndexId   = null;
				skipCurrentIndex = false;
				// fireEvent( new AnalyzingFundEvent(  ) );
			} catch(ParserConfigurationException e) {
				// @FIXME treat that exception
			} catch(FactoryConfigurationError e) {
				// @FIXME treat that exception
			}
		} else if(!skipCurrentIndex) {
			if(MSCIIndexNodeNames.NODE_MSCI_INDEX_CODE.equals( qName )) {
				currentIndexId = null;
			}
			if(MSCIIndexNodeNames.NODE_PERFORMANCE_DATA.equals( qName ) || MSCIIndexNodeNames.NODE_MSCI_INDEX_CODE.equals( qName )
				   || MSCIIndexNodeNames.NODE_INDEX_NAME.equals( qName ) || nodePathStack.contains( MSCIIndexNodeNames.NODE_PERFORMANCE_DATA )) {
				Element elt = currentIndexDocument.createElement( qName );
				currentIndexNode.appendChild( elt );
				currentIndexNode = elt;
			} else {
				// it's ok we continue
			}
		} // end if-else
	} // end method startElement

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public Date getExtractionDate() {
		return extractionDate;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public HashMap<String, MSCIImportedData> getIndexes() {
		return indexes;
	}
} // end class MSCIIndexTrackSaxHandler
