/**
 * Title           : $Workfile: ShaEncription.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 16.06.04 18:23 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 */

package com.eim.util.security.encription;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class ShaEncription {

	//~ Static fields/initializers -------------------------------------------------------------------

	/** Description of the Field */
	private static final String ALGORITHM = "SHA-1";

	/** Description of the Field */
	private static ShaEncription instance = null;

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Constructor for the ShaEncription object
	 */
	protected ShaEncription() {
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * Description of the Method
	 *
	 * @param   string  Description of the Parameter
	 *
	 * @return  Description of the Return Value
	 */
	public final String encrypt( String string ) {
		byte[]        buf       = string.getBytes();
		MessageDigest algorithm = null;

		try {
			algorithm = MessageDigest.getInstance( ALGORITHM );
		} catch( NoSuchAlgorithmException e ) {
			System.out.println( e );
		}
		algorithm.reset();
		algorithm.update( buf );

		byte[] digest1        = algorithm.digest();
		String sCryptedResult = new String( Base64.getInstance().encode( digest1 ) );

		return sCryptedResult;
	}

	/**
	 * Gets the instance attribute of the ShaEncription class
	 *
	 * @return  The instance value
	 */
	public static ShaEncription getInstance() {
		synchronized( com.eim.util.security.encription.ShaEncription.class ) {
			if( instance == null ) {
				instance = new com.eim.util.security.encription.ShaEncription();
			}
		}

		return instance;
	}
}
