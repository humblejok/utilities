/**
 * Title           : $Workfile: FundNamesListBuilder.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 15/01/08 17:12 $
 * By              : $Author: Als $
 * Version number  : $Revision: 8 $
 *
 * $History: FundNamesListBuilder.java $
 * 
 * *****************  Version 8  *****************
 * User: Als          Date: 15/01/08   Time: 17:12
 * Updated in $/Current/Projects/utilities/src/com/eim/util/livelink
 * Stacking correctly new documents
 * 
 * *****************  Version 7  *****************
 * User: Ac           Date: 21/09/07   Time: 11:21
 * Updated in $/Current/Projects/utilities/src/com/eim/util/livelink
 * 
 * *****************  Version 6  *****************
 * User: Ac           Date: 14/09/07   Time: 14:44
 * Updated in $/Current/Projects/utilities/src/com/eim/util/livelink
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 10/08/07   Time: 9:35
 * Updated in $/Current/Projects/utilities/src/com/eim/util/livelink
 * Refactoring for Java 5
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 8.01.07    Time: 9:43
 * Updated in $/Current/Projects/utilities/src/com/eim/util/livelink
 * GED Integration
 * 
 * *****************  Version 3  *****************
 * User: Ac           Date: 20.11.06   Time: 16:16
 * Updated in $/Current/Projects/utilities/src/com/eim/util/livelink
 * 
 * *****************  Version 2  *****************
 * User: Sdj          Date: 13.10.06   Time: 10:44
 * Updated in $/Current/Projects/utilities/src/com/eim/util/livelink
 * GED server selection
 * 
 * *****************  Version 1  *****************
 * User: Sdj          Date: 12.10.06   Time: 16:51
 * Created in $/Current/Projects/utilities/src/com/eim/util/livelink
 * GED server selection
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 22.09.06   Time: 14:30
 * Updated in $/Current/Projects/tools/idmExporter/src/com/eim/util/intranet
 */
package com.eim.util.livelink;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

@SuppressWarnings("unchecked")
public class FundNamesListBuilder {

	//~ Static fields/initializers ---------------------------------------------

	private static FundNamesListBuilder instance = null;
	public static SimpleDateFormat MONTH_FORMATTER = new SimpleDateFormat( "MMM-yy", Locale.US );
	
	//~ Instance fields --------------------------------------------------------

	private HashMap<String, FundData> values = new HashMap<String, FundData>();

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new FundNamesListBuilder object.
	 */
	private FundNamesListBuilder() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param   file
	 *
	 * @throws  JDOMException
	 * @throws  IOException
	 */
	public void insertFromFile(File file)
						throws JDOMException, IOException
	{
		Document document = new SAXBuilder().build( file );
		for(Iterator iterator = document.getRootElement().getChildren( "DATA_RECORD" ).iterator(); iterator.hasNext();) {
			Element element = (Element)iterator.next();
			values.put( element.getChild( "FUND_CTYP_LW" ).getText().trim(), new FundData(element.getChild( "FUND_LW" ).getText().trim(),element.getChild( "FUND_ID" ).getText().trim()) );
		}
	} // end method insertFromFile

	public String getFundName(String gedAlias) {
		if (gedAlias==null) {
			return "";
		}
		return (values.get( gedAlias )==null)?gedAlias:values.get( gedAlias ).getFundName();
	}

	public String getFundID(String gedAlias) {
		if (gedAlias==null) {
			return "";
		}
		return (values.get( gedAlias )==null)?gedAlias:values.get( gedAlias ).getFundID();
	}
	
	public static FundNamesListBuilder getInstance() {
		if(instance==null) {
			instance = new FundNamesListBuilder();
		}
		return instance;
	}
	
	public static String getNewTitle(LivelinkDocumentType documentType, String fundName) {
		return documentType.getPrefix() + " " + fundName;
	} // end method getNewTitle
	
	public static String removeInvalidCharacters(String name) {
		return name.replace( ':', '-' );
	}
	public class FundData {
		private String fundName;
		private String fundID;
		
		public FundData(String fundName, String fundID) {
			this.fundID = fundID;
			this.fundName = fundName;
		}

		public String getFundID() {
			return fundID;
		}

		public void setFundID(String fundID) {
			this.fundID = fundID;
		}

		public String getFundName() {
			return fundName;
		}

		public void setFundName(String fundName) {
			this.fundName = fundName;
		}
	}
	
} // end class FundNamesListBuilder
