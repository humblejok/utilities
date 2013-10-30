/**
 * Title           : $Workfile: XmlComparator.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 11/07/07 14:54 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: XmlComparator.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 11/07/07   Time: 14:54
 * Updated in $/Current/Projects/utilities/src/com/eim/util/xml
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 10.11.04   Time: 17:54
 * Updated in $/Current/Projects/utilities/src/com/eim/util/xml
 */
package com.eim.util.xml;

import org.jdom.Element;
import org.jdom.JDOMException;

import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;


/**
 * JavaDoc class comment
 *
 * @author  vj
 */
public class XmlComparator {

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new XmlComparator object.
	 */
	public XmlComparator() {
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param      original  Add comments
	 * @param      modified  Add comments
	 *
	 * @return     Add comments
	 *
	 * @exception  JDOMException  Add comments
	 * @throws     IOException
	 */
	public Element merge(String original, String modified)
				  throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		return merge( builder.build( new StringReader( original ) ).getRootElement(), builder.build( new StringReader( modified ) ).getRootElement() );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   original  Add comments
	 * @param   modified  Add comments
	 *
	 * @return  Add comments
	 */
	public Element merge(Element original, Element modified) {
		ComparisonStorage cs = new ComparisonStorage( original, modified );
		return cs.toXml();
	}
	
	public Element differences(Element original, Element modified) {
		ComparisonStorage cs = new ComparisonStorage( original, modified );
		return cs.differencesToXml();
	}
} // end class XmlComparator