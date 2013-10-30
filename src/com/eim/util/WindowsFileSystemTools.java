/**
 * Title           : $Workfile: WindowsFileSystemTools.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 22.09.06 16:30 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 2 $
 *
 * $History: WindowsFileSystemTools.java $
 * 
 * *****************  Version 2  *****************
 * User: Sdj          Date: 22.09.06   Time: 16:30
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Adaptation to distribution factory
 * 
 * *****************  Version 1  *****************
 * User: Als          Date: 22.09.06   Time: 14:31
 * Created in $/Current/Projects/utilities/src/com/eim/util
 */
package com.eim.util;

/**
 */
public abstract class WindowsFileSystemTools {

	//~ Static fields/initializers ---------------------------------------------

	/**
	 */
	public static final char[] INVALID_CHARACTERS = {
		'\\',
		'/',
		':',
		'*',
		'*',
		'<',
		'>',
		'|',
		'"'
	};

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new WindowsFileSystemTools object.
	 */
	private WindowsFileSystemTools() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param   fileName
	 * @param   replacementChar
	 *
	 * @return
	 */
	public static String purgeInvalidCharacters(String fileName, char replacementChar) {
		if(fileName==null) {
			return null;
		}
		StringBuffer builder = new StringBuffer();
		for(int i = 0; i<fileName.length(); i++) {
			char c = fileName.charAt( i );
			if(((int)c)<32) {
				builder.append( replacementChar );
			} else {
				boolean found = false;
				for(int j = 0; j<INVALID_CHARACTERS.length; j++) {
					if(c==INVALID_CHARACTERS[j]) {
						found = true;
						builder.append( replacementChar );
						break;
					}
				} // end for
				if(!found) {
					builder.append( c );
				}
			} // end if
		} // end for
		return builder.toString();
	} // end method purgeInvalidCharacters
} // end class WindowsFileSystemTools
