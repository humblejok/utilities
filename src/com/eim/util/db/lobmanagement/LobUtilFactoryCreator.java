/**
 * Title           : $Workfile:  $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date:  $
 * By              : $Author:  $
 * Version number  : $Revision:  $
 */

package com.eim.util.db.lobmanagement;

/**
 * JavaDoc class comment
 *
 * @author als
 * @version $Revision:  $
 */
public interface LobUtilFactoryCreator {
	String ORACLE = "Oracle";

	/**
	 * JavaDoc method comments
	 *
	 * @param spDatabaseProductName Add comments
	 *
	 * @return Add comments
	 */
	LobUtil createLob( String spDatabaseProductName );
}
