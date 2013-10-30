/**
 * Title           : $Workfile: LobUtilFactory.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 16.06.04 18:23 $
 * By              : $Author: Als $
 * Version number  : $Revision: 3 $
 */

package com.eim.util.db.lobmanagement;

import com.eim.util.db.lobmanagement.oracle.OracleLob;
import com.eim.util.exceptions.CodingFailureException;


/**
 * Description of the Class
 *
 * @author   als
 * @version  $Revision: 3 $
 */
public final class LobUtilFactory
implements LobUtilFactoryCreator {

	//~ Static fields/initializers -------------------------------------------------------------------

	private static LobUtilFactory cufPiInstance = null;

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Constructor for the LobUtilFactory object
	 */
	private LobUtilFactory() {
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param   spDatabaseProductName  Description of the Parameter
	 *
	 * @return  Une classe utilitaire spécifique à la DB pour la gestion des CLOB.
	 *
	 * @throws  CodingFailureException  DOCUMENT ME!
	 */
	public LobUtil createLob( final String spDatabaseProductName ) {
		if( spDatabaseProductName.equals( LobUtilFactoryCreator.ORACLE ) ) {
			return new OracleLob();
		} else {
			throw new CodingFailureException( "Case not treated" );
		}
	}

	/**
	 * Gets the instance attribute of the LobUtilFactory class
	 *
	 * @return  The instance value
	 */
	public static LobUtilFactory getInstance() {
		if( cufPiInstance == null ) {
			synchronized( com.eim.util.db.lobmanagement.LobUtilFactory.class ) {
				cufPiInstance = new com.eim.util.db.lobmanagement.LobUtilFactory();
			}
		}

		return cufPiInstance;
	}
}
