/**
 * Title           : $Workfile: AbstractDAO.java $
 * Copyright       : EIM (c) 2005
 * Updates         : $Date: 12/07/09 11:17a $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 9 $
 *
 * $History: AbstractDAO.java $
 * 
 * *****************  Version 9  *****************
 * User: Sdj          Date: 12/07/09   Time: 11:17a
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db
 * Scheduled job
 * 
 * *****************  Version 8  *****************
 * User: Als          Date: 2/20/09    Time: 11:26a
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db
 * Retro-Propagation
 * 
 * *****************  Version 7  *****************
 * User: Als          Date: 12/06/07   Time: 12:11
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db
 * Correction pour la serialization du DAO
 * 
 * *****************  Version 6  *****************
 * User: Als          Date: 4/06/07    Time: 9:46
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db
 * 
 * *****************  Version 5  *****************
 * User: Sdj          Date: 1.06.06    Time: 10:18
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db
 * Auto-generation of serial UID.
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 1.06.06    Time: 10:16
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db
 * EIM Plugins for Eclipse
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 31.05.06   Time: 15:17
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db
 * 
 * *****************  Version 2  *****************
 * User: Sdj          Date: 31.05.06   Time: 13:53
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db
 * 
 * *****************  Version 1  *****************
 * User: Jpf          Date: 3/18/05    Time: 9:19a
 * Created in $/Current/Projects/utilities/src/com/eim/util/db
 * Déplacement des classes utilitaires dans le projets util
 */
package com.eim.util.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;


/**
 * Classe abstraite dont doivent hériter tous les DAO
 *
 * @author  jpf
 */
public abstract class AbstractDAO
	implements java.io.Serializable
{

	//~ Instance fields --------------------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -246967170507810087L;
	protected transient Logger log = Logger.getLogger( this.getClass() );
	/** Description of the Field */
	private Connection connection;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Default constructor without specifying connection
	 */
	public AbstractDAO() {
		this.connection = null;
	}

	/**
	 * Constructor specifying connection
	 *
	 * @param  connection  The connection to work on.
	 */
	public AbstractDAO(Connection connection) {
		this.connection = connection;
		if(log==null){
			log = Logger.getLogger( this.getClass() );
		}
		if(log.isDebugEnabled()) {
			if(connection!=null) {
				try {
					if(log.isDebugEnabled()) {
						log.debug( "Creating " + this.getClass().getName() + " with connection (transaction isolation:"
								   + interpretsTransactionIsolation( connection.getTransactionIsolation() ) + ")" );
					}
				} catch(SQLException e) {
					if(log.isDebugEnabled()) {
						log.debug( "Creating " + this.getClass().getName() + " with connection (transaction isolation:unknown)" );
					}
				}
			} else {
				log.debug( "Creating " + this.getClass().getName() + " without connection." );
			}
		} // end if
	} // end ctor AbstractDAO

	//~ Methods ----------------------------------------------------------------

	/**
	 * Close the connection
	 */
	public void closeConnection() {
		if(log==null){
			log = Logger.getLogger( this.getClass() );
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch(SQLException e) {
				log.warn( "Could not close connection!", e );
			}
			connection = null;
		} // end if
	} // end method closeConnection
	
	/**
	 * @param   transaction
	 *
	 * @return
	 */
	private String interpretsTransactionIsolation(int transaction) {
		switch(transaction) {
			case 0:
				return "TRANSACTION_NONE";
			case 1:
				return "TRANSACTION_READ_UNCOMMITTED";
			case 2:
				return "TRANSACTION_READ_COMMITTED";
			case 4:
				return "TRANSACTION_REPEATABLE_READ";
			case 8:
				return "TRANSACTION_SERIALIZABLE";
			default:
				return "Unknown";
		}
	} // end method interpretsTransactionIsolation

	/**
	 * Gets the connection attribute of the AbstractDAO object
	 *
	 * @return  The connection value
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets the connection attribute of the AbstractDAO object
	 *
	 * @param  connection  The new connection value
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
		if(log==null){
			log = Logger.getLogger( this.getClass() );
		}
		if(log.isDebugEnabled()) {
			if(connection!=null) {
				try {
					if(log.isDebugEnabled()) {
						log.debug( "Setting connection in " + this.getClass().getName() + " (transaction isolation:"
								   + interpretsTransactionIsolation( connection.getTransactionIsolation() ) + ")" );
					}
				} catch(SQLException e) {
					if(log.isDebugEnabled()) {
						log.debug( "Setting connection in " + this.getClass().getName() + " (transaction isolation:unknown)" );
					}
				}
			} else {
				log.debug( "Removing connection in " + this.getClass().getName() );
			}
		} // end if
	} // end method setConnection
} // end class AbstractDAO