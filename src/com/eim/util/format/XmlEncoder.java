/**
 * Title           : $Workfile: XmlEncoder.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10.06.05 11:19 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 10 $
 *
 * $History: XmlEncoder.java $
 * 
 * *****************  Version 10  *****************
 * User: Sdj          Date: 10.06.05   Time: 11:19
 * Updated in $/Current/Projects/utilities/src/com/eim/util/format
 * Special characters in the Alias wordings
 * 
 * *****************  Version 9  *****************
 * User: Sdj          Date: 17.05.05   Time: 10:10
 * Updated in $/Current/Projects/utilities/src/com/eim/util/format
 * Empty string to encode
 */
package com.eim.util.format;

import com.thoughtworks.xstream.io.xml.xppdom.Xpp3DomBuilder;

import java.io.StringReader;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
public final class XmlEncoder {

	//~ Static fields/initializers ---------------------------------------------

	private static final String[] TOKENS		   = {
		"&quot;",
		"&amp;",
		"<",
		"&lt;",
		">",
		"&gt;",
		"&nbsp;",
		"&iexcl;",
		"&cent;",
		"&pound;",
		"&curren;",
		"&yen;",
		"&brvbar;",
		"&sect;",
		"&uml;",
		"&copy;",
		"&ordf;",
		"&laquo;",
		"&not;",
		"&shy;",
		"&reg;",
		"&macr;",
		"&deg;",
		"&plusmn;",
		"&sup2;",
		"&sup3;",
		"&acute;",
		"&micro;",
		"&para;",
		"&middot;",
		"&cedil;",
		"&sup1;",
		"&ordm;",
		"&raquo;",
		"&frac14;",
		"&frac12;",
		"&frac34;",
		"&iquest;",
		"&Agrave;",
		"&Aacute;",
		"&Acirc;",
		"&Atilde;",
		"&Auml;",
		"&Aring;",
		"&AElig;",
		"&Ccedil;",
		"&Egrave;",
		"&Eacute;",
		"&Ecirc;",
		"&Euml;",
		"&Igrave;",
		"&Iacute;",
		"&Icirc;",
		"&Iuml;",
		"&ETH;",
		"&Ntilde;",
		"&Ograve;",
		"&Oacute;",
		"&Ocirc;",
		"&Otilde;",
		"&Ouml;",
		"&times;",
		"&Oslash;",
		"&Ugrave;",
		"&Uacute;",
		"&Ucirc;",
		"&Uuml;",
		"&Yacute;",
		"&THORN;",
		"&szlig;",
		"&agrave;",
		"&aacute;",
		"&acirc;",
		"&atilde;",
		"&auml;",
		"&aring;",
		"&aelig;",
		"&ccedil;",
		"&egrave;",
		"&eacute;",
		"&ecirc;",
		"&euml;",
		"&igrave;",
		"&iacute;",
		"&icirc;",
		"&iuml;",
		"&eth;",
		"&ntilde;",
		"&ograve;",
		"&oacute;",
		"&ocirc;",
		"&otilde;",
		"&ouml;",
		"&divide;",
		"&oslash;",
		"&ugrave;",
		"&uacute;",
		"&ucirc;",
		"&uuml;",
		"&yacute;",
		"&thorn;",
		"&yuml;",
		"&#10;"
	};
	private static final String[] TOKENS_TRANSFORM = {
		"&#34;",
		"&#38;",
		"&#60;",
		"&#60;",
		"&#62;",
		"&#62;",
		"&#160;",
		"&#161;",
		"&#162;",
		"&#163;",
		"&#164;",
		"&#165;",
		"&#166;",
		"&#167;",
		"&#168;",
		"&#169;",
		"&#170;",
		"&#171;",
		"&#172;",
		"&#173;",
		"&#174;",
		"&#175;",
		"&#176;",
		"&#177;",
		"&#178;",
		"&#179;",
		"&#180;",
		"&#181;",
		"&#182;",
		"&#183;",
		"&#184;",
		"&#185;",
		"&#186;",
		"&#187;",
		"&#188;",
		"&#189;",
		"&#190;",
		"&#191;",
		"&#192;",
		"&#193;",
		"&#194;",
		"&#195;",
		"&#196;",
		"&#197;",
		"&#198;",
		"&#199;",
		"&#200;",
		"&#201;",
		"&#202;",
		"&#203;",
		"&#204;",
		"&#205;",
		"&#206;",
		"&#207;",
		"&#208;",
		"&#209;",
		"&#210;",
		"&#211;",
		"&#212;",
		"&#213;",
		"&#214;",
		"&#215;",
		"&#216;",
		"&#217;",
		"&#218;",
		"&#219;",
		"&#220;",
		"&#221;",
		"&#222;",
		"&#223;",
		"&#224;",
		"&#225;",
		"&#226;",
		"&#227;",
		"&#228;",
		"&#229;",
		"&#230;",
		"&#231;",
		"&#232;",
		"&#233;",
		"&#234;",
		"&#235;",
		"&#236;",
		"&#237;",
		"&#238;",
		"&#239;",
		"&#240;",
		"&#241;",
		"&#242;",
		"&#243;",
		"&#244;",
		"&#245;",
		"&#246;",
		"&#247;",
		"&#248;",
		"&#249;",
		"&#250;",
		"&#251;",
		"&#252;",
		"&#253;",
		"&#254;",
		"&#255;",
		"<BR/>"
	};

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new XmlEncoder object.
	 */
	private XmlEncoder() {
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param   str  Add comments
	 *
	 * @return  Add comments
	 */
	public static String encode(final String str) {
		if ((str!=null) && !str.equals("")) {
			if(str.charAt( 0 )=='<') {
				try {
					Xpp3DomBuilder.build( new StringReader( str ) );
					return str;
				} catch(Exception e) {
					return internalEncode( str );
				}
			} else {
				return internalEncode( str );
			}
		} else {
			return str;
		}
	} // end method encode

	/**
	 * @param   str
	 *
	 * @return
	 */
	private static String internalEncode(final String str) {
		StringBuffer result = new StringBuffer( str );
		for(int i = 0; i<TOKENS.length; i++) {
			int position = 0;
			while((position = result.indexOf( TOKENS[i] ))>=0) {
				result.delete( position, position + TOKENS[i].length() );
				result.insert( position, TOKENS_TRANSFORM[i] );
			}
		} // end for
		int ampPosition = -1;
		while((ampPosition = result.indexOf( "&", ampPosition + 1 ))>=0) {
			if((ampPosition==(result.length() - 1)) || (result.charAt( ampPosition + 1 )!='#')) {
				result.delete( ampPosition, ampPosition + 1 );
				result.insert( ampPosition, "&#38;" );
			}
		}
		return result.toString();
	} // end method internalEncode
} // end class XmlEncoder
