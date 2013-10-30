/**
 * Title           : $Workfile: HtmlEncoder.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31/08/07 16:27 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 7 $
 *
 * $History: HtmlEncoder.java $
 * 
 * *****************  Version 7  *****************
 * User: Sdj          Date: 31/08/07   Time: 16:27
 * Updated in $/Current/Projects/intranet-web/src/com/eim/application/intranet/web/util
 * Liquidities Status implementation
 * 
 * *****************  Version 6  *****************
 * User: Sdj          Date: 13.05.05   Time: 8:37
 * Updated in $/Current/Projects/intranet-web/src/com/eim/application/intranet/web/util
 * Wizard labels not printed correctly
 */
/**
 * Title           : $Workfile: HtmlEncoder.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 31/08/07 16:27 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 7 $
 */
package com.eim.util.format;

/**
 * a usefull Java class for managing the encoding of a String into HTML language.
 *
 * @author  Folco Banfi
 * @author  Alban Soupper
 * @see     javax.servlet.http.HttpServletResponse.encodeURL(String)
 */
public class HtmlEncoder {

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param   str  Add comments
	 *
	 * @return  Add comments
	 */
	public static String encode(String str) {
		return HtmlEncoder.encode( str, true );
	}

	/**
	 * encodes a String into the HTML Language. Note: this method does not traduce every character into it's HTML code, but encodes only those which may makes
	 * troubles.
	 *
	 * <pre>
	   Characters encoded:
	   &quot;
	   &amp;
	   &#39;
	   &lt;
	   &gt;
	   &nbsp;
	   &iexcl;
	   &cent;
	   &pound;
	   &curren;
	   &yen;
	   &brvbar;
	   &sect;
	   &uml;
	   &copy;
	   &ordf;
	   &laquo;
	   &not;
	   &shy;
	   &reg;
	   &macr;
	   &deg;
	   &plusmn;
	   &sup2;
	   &sup3;
	   &acute;
	   &micro;
	   &para;
	   &middot;
	   &cedil;
	   &sup1;
	   &ordm;
	   &raquo;
	   &frac14;
	   &frac12;
	   &frac34;
	   &iquest;
	   &Agrave;
	   &Aacute;
	   &Acirc;
	   &Atilde;
	   &Auml;
	   &Aring;
	   &AElig;
	   &Ccedil;
	   &Egrave;
	   &Eacute;
	   &Ecirc;
	   &Euml;
	   &Igrave;
	   &Iacute;
	   &Icirc;
	   &Iuml;
	   &ETH;
	   &Ntilde;
	   &Ograve;
	   &Oacute;
	   &Ocirc;
	   &Otilde;
	   &Ouml;
	   &times;
	   &Oslash;
	   &Ugrave;
	   &Uacute;
	   &Ucirc;
	   &Uuml;
	   &Yacute;
	   &THORN;
	   &szlig;
	   &agrave;
	   &aacute;
	   &acirc;
	   &atilde;
	   &auml;
	   &aring;
	   &aelig;
	   &ccedil;
	   &egrave;
	   &eacute;
	   &ecirc;
	   &euml;
	   &igrave;
	   &iacute;
	   &icirc;
	   &iuml;
	   &eth;
	   &ntilde;
	   &ograve;
	   &oacute;
	   &ocirc;
	   &otilde;
	   &ouml;
	   &divide;
	   &oslash;
	   &ugrave;
	   &uacute;
	   &ucirc;
	   &uuml;
	   &yacute;
	   &thorn;
	   &yuml;
	 * </pre>
	 *
	 * @param   str              the String to encode.
	 * @param   encodeAmpersand  DOCUMENT ME!
	 *
	 * @return  a new String representing the encoding of the String parameter
	 */
	public static String encode(String str, boolean encodeAmpersand) {
		StringBuffer stringBuffer = new StringBuffer();
		if((str==null) || str.equals( "" ) || str.equals( "null" )) {
			return "&#160;";
			// return "&nbsp;";
		}
		char[] chars = str.toCharArray();
		for(int i = 0; i<chars.length; i++) {
			String symbol;
			switch(chars[i]) {
				case 34:
					symbol = "&quot;";
					break;
				case 38:
					if(encodeAmpersand) {
						symbol = "&amp;";
					} else {
						symbol = "&";
					}
					break;
				case 39:
					symbol = "&#39;";
					break;
				case 60:
					symbol = "&lt;";
					break;
				case 62:
					symbol = "&gt;";
					break;
				case 160:
					symbol = "&nbsp;";
					break;
				case 161:
					symbol = "&iexcl;";
					break;
				case 162:
					symbol = "&cent;";
					break;
				case 163:
					symbol = "&pound;";
					break;
				case 164:
					symbol = "&curren;";
					break;
				case 165:
					symbol = "&yen;";
					break;
				case 166:
					symbol = "&brvbar;";
					break;
				case 167:
					symbol = "&sect;";
					break;
				case 168:
					symbol = "&uml;";
					break;
				case 169:
					symbol = "&copy;";
					break;
				case 170:
					symbol = "&ordf;";
					break;
				case 171:
					symbol = "&laquo;";
					break;
				case 172:
					symbol = "&not;";
					break;
				case 173:
					symbol = "&shy;";
					break;
				case 174:
					symbol = "&reg;";
					break;
				case 175:
					symbol = "&macr;";
					break;
				case 176:
					symbol = "&deg;";
					break;
				case 177:
					symbol = "&plusmn;";
					break;
				case 178:
					symbol = "&sup2;";
					break;
				case 179:
					symbol = "&sup3;";
					break;
				case 180:
					symbol = "&acute;";
					break;
				case 181:
					symbol = "&micro;";
					break;
				case 182:
					symbol = "&para;";
					break;
				case 183:
					symbol = "&middot;";
					break;
				case 184:
					symbol = "&cedil;";
					break;
				case 185:
					symbol = "&sup1;";
					break;
				case 186:
					symbol = "&ordm;";
					break;
				case 187:
					symbol = "&raquo;";
					break;
				case 188:
					symbol = "&frac14;";
					break;
				case 189:
					symbol = "&frac12;";
					break;
				case 190:
					symbol = "&frac34;";
					break;
				case 191:
					symbol = "&iquest;";
					break;
				case 192:
					symbol = "&Agrave;";
					break;
				case 193:
					symbol = "&Aacute;";
					break;
				case 194:
					symbol = "&Acirc;";
					break;
				case 195:
					symbol = "&Atilde;";
					break;
				case 196:
					symbol = "&Auml;";
					break;
				case 197:
					symbol = "&Aring;";
					break;
				case 198:
					symbol = "&AElig;";
					break;
				case 199:
					symbol = "&Ccedil;";
					break;
				case 200:
					symbol = "&Egrave;";
					break;
				case 201:
					symbol = "&Eacute;";
					break;
				case 202:
					symbol = "&Ecirc;";
					break;
				case 203:
					symbol = "&Euml;";
					break;
				case 204:
					symbol = "&Igrave;";
					break;
				case 205:
					symbol = "&Iacute;";
					break;
				case 206:
					symbol = "&Icirc;";
					break;
				case 207:
					symbol = "&Iuml;";
					break;
				case 208:
					symbol = "&ETH;";
					break;
				case 209:
					symbol = "&Ntilde;";
					break;
				case 210:
					symbol = "&Ograve;";
					break;
				case 211:
					symbol = "&Oacute;";
					break;
				case 212:
					symbol = "&Ocirc;";
					break;
				case 213:
					symbol = "&Otilde;";
					break;
				case 214:
					symbol = "&Ouml;";
					break;
				case 215:
					symbol = "&times;";
					break;
				case 216:
					symbol = "&Oslash;";
					break;
				case 217:
					symbol = "&Ugrave;";
					break;
				case 218:
					symbol = "&Uacute;";
					break;
				case 219:
					symbol = "&Ucirc;";
					break;
				case 220:
					symbol = "&Uuml;";
					break;
				case 221:
					symbol = "&Yacute;";
					break;
				case 222:
					symbol = "&THORN;";
					break;
				case 223:
					symbol = "&szlig;";
					break;
				case 224:
					symbol = "&agrave;";
					break;
				case 225:
					symbol = "&aacute;";
					break;
				case 226:
					symbol = "&acirc;";
					break;
				case 227:
					symbol = "&atilde;";
					break;
				case 228:
					symbol = "&auml;";
					break;
				case 229:
					symbol = "&aring;";
					break;
				case 230:
					symbol = "&aelig;";
					break;
				case 231:
					symbol = "&ccedil;";
					break;
				case 232:
					symbol = "&egrave;";
					break;
				case 233:
					symbol = "&eacute;";
					break;
				case 234:
					symbol = "&ecirc;";
					break;
				case 235:
					symbol = "&euml;";
					break;
				case 236:
					symbol = "&igrave;";
					break;
				case 237:
					symbol = "&iacute;";
					break;
				case 238:
					symbol = "&icirc;";
					break;
				case 239:
					symbol = "&iuml;";
					break;
				case 240:
					symbol = "&eth;";
					break;
				case 241:
					symbol = "&ntilde;";
					break;
				case 242:
					symbol = "&ograve;";
					break;
				case 243:
					symbol = "&oacute;";
					break;
				case 244:
					symbol = "&ocirc;";
					break;
				case 245:
					symbol = "&otilde;";
					break;
				case 246:
					symbol = "&ouml;";
					break;
				case 247:
					symbol = "&divide;";
					break;
				case 248:
					symbol = "&oslash;";
					break;
				case 249:
					symbol = "&ugrave;";
					break;
				case 250:
					symbol = "&uacute;";
					break;
				case 251:
					symbol = "&ucirc;";
					break;
				case 252:
					symbol = "&uuml;";
					break;
				case 253:
					symbol = "&yacute;";
					break;
				case 254:
					symbol = "&thorn;";
					break;
				case 255:
					symbol = "&yuml;";
					break;
				case '\r':
					symbol = "";
				case '\n':
					symbol = "&#10;";
					break;
				default:
					symbol = String.valueOf( chars[i] );
					break;
			}
			stringBuffer.append( symbol );
		} // end for
		return stringBuffer.toString();
	} // end method encode

	/**
	 * JavaDoc method comments
	 *
	 * @param   str  Add comments
	 *
	 * @return  Add comments
	 */
	public static String encodeForUrl(String str) {
		StringBuffer result   = new StringBuffer( str );
		int			 position = 0;
		while((position = result.indexOf( "&" ))>0) {
			result.delete( position, position + 1 );
			result.insert( position, "%26" );
		}
		return result.toString();
	} // end method encodeForUrl
} // end class HtmlEncoder