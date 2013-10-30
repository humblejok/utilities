/**
 * Title           : $Workfile: MSCIFundInfoSaxHandler.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:44 $
 * By              : $Author: Als $
 * Version number  : $Revision: 11 $
 */

package com.eim.util.msci;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import com.eim.util.msci.event.AnalyzingFundEvent;
import com.eim.util.msci.event.MsciImporterEvent;
import com.eim.util.msci.event.MsciImporterEventListener;

import org.apache.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * SAX handler specific for the MSCI data files.
 *
 * @author  als
 */
@SuppressWarnings("unchecked")
public class MSCIFundInfoSaxHandler extends DefaultHandler {

	//~ Static fields/initializers -------------------------------------------------------------------

	private static Logger logger = Logger.getLogger( MSCIFundInfoSaxHandler.class );

	//~ Instance fields ------------------------------------------------------------------------------

	protected Date      extractionDate = new Date();
	protected ArrayList<String> fundIds;
	protected HashMap<String, MSCIImportedData>   funds;
	protected ArrayList listeners;
	protected boolean   loadAllFunds   = true;
	private Document    currentFundDocument = null;
	private String      currentFundId       = null;
	private Node        currentFundNode     = null;
	private Stack<String>       nodePathStack       = null;
	private boolean     skipCurrentFund = false;

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new MSCISaxHanlder object.
	 *
	 * @param  listeners  DOCUMENT ME!
	 */
	public MSCIFundInfoSaxHandler( ArrayList listeners ) {
		super();
		loadAllFunds   = true;
		funds          = new HashMap<String, MSCIImportedData>();
		this.listeners = listeners;
		fundIds        = new ArrayList<String>();
	}

	/**
	 * Creates a new MSCIFundInfoSaxHandler object.
	 *
	 * @param  msciFundId  DOCUMENT ME!
	 * @param  listeners   DOCUMENT ME!
	 */
	public MSCIFundInfoSaxHandler(
	  String    msciFundId,
	  ArrayList listeners ) {
		super();
		loadAllFunds = false;
		funds        = new HashMap<String, MSCIImportedData>();
		fundIds = new ArrayList<String>();
		fundIds.add( msciFundId );
		this.listeners = listeners;
	}

	/**
	 * Creates a new MSCIFundInfoSaxHandler object.
	 *
	 * @param  msciFundIds  DOCUMENT ME!
	 * @param  listeners    DOCUMENT ME!
	 */
	public MSCIFundInfoSaxHandler(
	  ArrayList<String> msciFundIds,
	  ArrayList listeners ) {
		super();
		loadAllFunds   = false;
		funds          = new HashMap<String, MSCIImportedData>();
		fundIds        = new ArrayList<String>(msciFundIds);
		this.listeners = listeners;
	}

	/**
	 * Creates a new MSCIFundInfoSaxHandler object.
	 *
	 * @param  msciFundIds  DOCUMENT ME!
	 * @param  listeners    DOCUMENT ME!
	 */
	public MSCIFundInfoSaxHandler(
	  String[]  msciFundIds,
	  ArrayList listeners ) {
		super();
		loadAllFunds = false;
		funds        = new HashMap<String, MSCIImportedData>();
		fundIds      = new ArrayList<String>();
		for( int i = 0; i < msciFundIds.length; i++ ) {
			fundIds.add( msciFundIds[i] );
		}
		this.listeners = listeners;
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param      ch      Add comments
	 * @param      start   Add comments
	 * @param      length  Add comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void characters(
	  char[] ch,
	  int    start,
	  int    length )
	        throws SAXException {
		if( !skipCurrentFund ) {
			if( MSCIFundNodeNames.NODE_MSCI_FUND_CODE.equals( nodePathStack.peek() ) ) {
				if( currentFundId != null ) {
					currentFundId = currentFundId + new String( ch, start, length );
				} else {
					currentFundId = new String( ch, start, length );
				}
			}
			if( (currentFundNode != null) && !(nodePathStack.contains( MSCIFundNodeNames.NODE_PERFORMANCE_DATA )) ) {
				currentFundNode.appendChild( currentFundDocument.createTextNode( new String( ch, start, length ) ) );
			}
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void endDocument()
	        throws SAXException {
		nodePathStack.pop();
		if( !nodePathStack.isEmpty() ) {
			throw new SAXException( "Stack is not empty at the end of the document processing" );
		}
		nodePathStack = null;
		if( logger.isDebugEnabled() ) {
			logger.debug( "Ending document analysis" );
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param      uri        Add comments
	 * @param      localName  Add comments
	 * @param      qName      Add comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void endElement(
	  String uri,
	  String localName,
	  String qName )
	        throws SAXException {
		if( MSCIFundNodeNames.NODE_MSCI_HEDGE_FUND_INFO.equals( qName ) ) {
			// it's ok, we continue.
		} else if( MSCIFundNodeNames.NODE_FUND.equals( qName ) ) {
			if( !skipCurrentFund ) {
				funds.put( currentFundId, new MSCIImportedData( currentFundDocument, extractionDate ) );
			}
			currentFundDocument = null;
			currentFundNode     = null;
			currentFundId       = null;
		} else if( MSCIFundNodeNames.NODE_MSCI_FUND_CODE.equals( qName ) ) {
			skipCurrentFund = !(loadAllFunds || fundIds.contains( currentFundId ));
			currentFundNode = currentFundNode.getParentNode();
			if( logger.isDebugEnabled() ) {
				if( skipCurrentFund ) {
					logger.debug( "Skipping current fund information analysis: " + currentFundId + " is not interesting" );
				} else {
					logger.debug( "Analyzing fund information : " + currentFundId + "." );
				}
			}
		} else if( !skipCurrentFund ) {
			if( nodePathStack.contains( MSCIFundNodeNames.NODE_PERFORMANCE_DATA ) ) {
				// it's ok we continue
			} else {
				currentFundNode = currentFundNode.getParentNode();
			}
		}
		nodePathStack.pop();
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param  event  Add comments
	 */
	private void fireEvent( MsciImporterEvent event ) {
		if( listeners != null ) {
			for( Iterator iter = listeners.iterator(); iter.hasNext(); ) {
				((MsciImporterEventListener)iter.next()).onMsciImporterEvent( event );
			}
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @exception  SAXException  Add comments
	 */
	public void startDocument()
	        throws SAXException {
		nodePathStack = new Stack<String>();
		nodePathStack.push( "/" );
		if( logger.isDebugEnabled() ) {
			logger.debug( "Starting document analysis" );
		}
	}

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
	public void startElement(
	  String     uri,
	  String     localName,
	  String     qName,
	  Attributes attributes )
	        throws SAXException {
		nodePathStack.push( qName );
		if( MSCIFundNodeNames.NODE_MSCI_HEDGE_FUND_INFO.equals( qName ) ) {
			try {
				extractionDate = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" ).parse( attributes.getValue( MSCIFundNodeNames.ATTR_EXTRACT_DATE ) + " " + attributes.getValue( MSCIFundNodeNames.ATTR_EXTRACT_TIME ) );
			} catch( ParseException e ) {
				throw new SAXException( "Invalid Extraction date format", e );
			}
		} else if( MSCIFundNodeNames.NODE_FUND.equals( qName ) ) {
			try {
				currentFundDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

				Element fundRootNode = currentFundDocument.createElement( qName );
				currentFundDocument.appendChild( fundRootNode );
				currentFundNode = fundRootNode;
				currentFundId   = null;
				skipCurrentFund = false;
				fireEvent( new AnalyzingFundEvent() );
			} catch( ParserConfigurationException e ) {
				// @FIXME treat that exception
			} catch( FactoryConfigurationError e ) {
				// @FIXME treat that exception
			}
		} else if( !skipCurrentFund ) {
			if( nodePathStack.contains( MSCIFundNodeNames.NODE_PERFORMANCE_DATA ) ) {
				// it's ok we continue
			} else {
				Element elt = currentFundDocument.createElement( qName );
				currentFundNode.appendChild( elt );
				currentFundNode = elt;
				if( MSCIFundNodeNames.NODE_MSCI_FUND_CODE.equals( qName ) ) {
					currentFundId = null;
				}
			}
		}
	}

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
	public HashMap<String, MSCIImportedData> getFunds() {
		return funds;
	}
}
