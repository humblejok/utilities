/**
 * Title           : $Workfile: PimEncoder.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10.11.04 17:48 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 *
 * $History: PimEncoder.java $
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 10.11.04   Time: 17:48
 * Updated in $/Current/Projects/utilities/src/com/eim/util/pim
 */
package com.eim.util.pim;

import java.nio.charset.Charset;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
public final class PimEncoder {

	//~ Static fields/initializers ---------------------------------------------

	private static final Charset CHARSET = Charset.forName( "US-ASCII" );

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new PimEncoder object.
	 */
	private PimEncoder() {
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param   stringToEncode  Add comments
	 *
	 * @return  Add comments
	 */
	public static String encode(String stringToEncode) {
		char[]		 chars = new String( CHARSET.encode( stringToEncode ).array() ).toCharArray();
		StringBuffer sb    = new StringBuffer();
		for(int i = 0; i<chars.length; i++) {
			switch(chars[i]) {
				case '?':
					sb.append( "=" );
					sb.append( Integer.toHexString( stringToEncode.charAt( i ) ) );
					break;
				default:
					sb.append( chars[i] );
			}
		} // end for
		return sb.toString();
	} // end method encode
} // end class PimEncoder
