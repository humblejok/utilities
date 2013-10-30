/**
 * Title           : $Workfile: OracleLob.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 11.05.06 11:14 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 8 $
 *
 * $History: OracleLob.java $
 * 
 * *****************  Version 8  *****************
 * User: Sdj          Date: 11.05.06   Time: 11:14
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db/lobmanagement/oracle
 * Code clean
 * 
 * *****************  Version 7  *****************
 * User: Als          Date: 25.05.05   Time: 16:26
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db/lobmanagement/oracle
 */
package com.eim.util.db.lobmanagement.oracle;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.sql.BLOB;
import oracle.sql.CLOB;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;

import com.eim.util.db.lobmanagement.LobUtil;


/**
 * Classe utilitaire permettant de gérer de façon spécifique les LOB pour Oracle
 *
 * @author  als
 */
public class OracleLob
	implements LobUtil
{

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new OracleLob object.
	 */
	public OracleLob() {
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Met à jour un champ de type BLOB dans la DB
	 *
	 * @param      con          Connection valide à la DB
	 * @param      bapImage     Image à insérer
	 * @param      spTableName  Nom de la table
	 * @param      spFieldName  Nomdu champ de type Blob
	 * @param      spWhere      clause where pour identifier le ou les enregistrements (sans le mot clé WHERE)
	 *
	 * @exception  SQLException  Description of the Exception
	 */
	public final void updateBlobField(final Connection con, final byte[] bapImage, final String spTableName, final String spFieldName, final String spWhere)
							   throws SQLException
	{
		// MAJ du blob
		String    sSQL		   = "SELECT " + spFieldName + " FROM " + spTableName + " WHERE " + spWhere + " FOR UPDATE";
		Statement sqlStatement = con.createStatement();
		try {
			ResultSet result = sqlStatement.executeQuery( sSQL );
			try {
				if(result.next()) {
					BLOB blob = (BLOB)result.getBlob( 1 );
					try {
						if(blob!=null) {
							// Ecriture du blob
							OutputStream outputStream = blob.setBinaryStream(0L);
							outputStream.write( bapImage );
							outputStream.close();
						}
					} catch(IOException ioe) {
						throw new SQLException( ioe.getMessage() );
					}
				} // end if
			} finally {
				result.close();
			}
		} finally {
			sqlStatement.close();
		}
	} // end method updateBlobField

	/**
	 * @param   con
	 * @param   data
	 * @param   spTableName
	 * @param   spFieldName
	 * @param   spWhere
	 *
	 * @throws  SQLException
	 */
	public final void updateBlobField(final Connection con, final Object data, final String spTableName, final String spFieldName, final String spWhere)
							   throws SQLException
	{
		// MAJ du blob
		String    sSQL		   = "SELECT " + spFieldName + " FROM " + spTableName + " WHERE " + spWhere;
		Statement sqlStatement = con.createStatement();
		try {
			ResultSet result = sqlStatement.executeQuery( sSQL );
			try {
				if(result.next()) {
					BLOB blob = (BLOB)result.getBlob( 1 );
					try {
						if(blob!=null) {
							// Ecriture du blob
							ObjectOutputStream stream = new ObjectOutputStream( blob.setBinaryStream(0L) );
							stream.writeObject( data );
							stream.close();
						}
					} catch(IOException ioe) {
						throw new SQLException( ioe.getMessage() );
					}
				} // end if
			} finally {
				result.close();
			}
		} finally {
			sqlStatement.close();
		}
	} // end method updateBlobField

	/**
	 * Met à jour un champ de type CLOB dans la DB
	 *
	 * @param      con          Connection valide à la DB
	 * @param      spValue      Nouvelle valeur du champ
	 * @param      spTableName  Nom de la table
	 * @param      spFieldName  Nomdu champ de type Clob
	 * @param      spWhere      clause where pour identifier le ou les enregistrements (sans le mot clé WHERE)
	 *
	 * @exception  SQLException  Description of the Exception
	 */
	public final void updateClobField(final Connection con, final String spValue, final String spTableName, final String spFieldName, final String spWhere)
							   throws SQLException
	{
		// MAJ du CLOB
		String    sSQL		   = "SELECT " + spFieldName + " FROM " + spTableName + " WHERE " + spWhere;
		Statement sqlStatement = con.createStatement();
		try {
			ResultSet result = sqlStatement.executeQuery( sSQL );
			try {
				if(result.next()) {
					CLOB clob = (CLOB)result.getClob( 1 );
					try {
						if(clob!=null) {
							// Ecriture du Clob
							Writer writer = clob.setCharacterStream(0L);
							if(spValue==null) {
								writer.write( "" );
							} else {
								writer.write( spValue );
							}
							writer.close();
						} // end if
					} catch(IOException ioe) {
						throw new SQLException( ioe.getMessage() );
					}
				} // end if
			} finally {
				result.close();
			}
		} finally {
			sqlStatement.close();
		}
	} // end method updateClobField

	/**
	 * Description of the Method
	 *
	 * @param      con          Description of the Parameter
	 * @param      document     Description of the Parameter
	 * @param      spTableName  Description of the Parameter
	 * @param      spFieldName  Description of the Parameter
	 * @param      spWhere      Description of the Parameter
	 *
	 * @exception  SQLException  Description of the Exception
	 */
	public final void updateClobField(final Connection con, final Document document, final String spTableName, final String spFieldName, final String spWhere)
							   throws SQLException
	{
		// MAJ du CLOB
		String    sSQL		   = "SELECT " + spFieldName + " FROM " + spTableName + " WHERE " + spWhere;
		Statement sqlStatement = con.createStatement();
		try {
			ResultSet result = sqlStatement.executeQuery( sSQL );
			try {
				if(result.next()) {
					CLOB clob = (CLOB)result.getClob( 1 );
					try {
						if(clob!=null) {
							XMLOutputter output = new XMLOutputter();
							output.output( document, clob.setCharacterStream(0L) );
							clob.setCharacterStream(0L).close();
						}
					} catch(IOException ioe) {
						throw new SQLException( ioe.getMessage() );
					}
				} // end if
			} finally {
				result.close();
			}
		} finally {
			sqlStatement.close();
		}
	} // end method updateClobField

	/**
	 * DOCUMENT ME!
	 *
	 * @return     un champ clob de type empty Blob afin d'initialiser la colonne
	 *
	 * @exception  SQLException  Description of the Exception
	 */
	public final Blob getEmptyBlob()
							throws SQLException
	{
		return BLOB.getEmptyBLOB();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return     un champ clob de type empty Clob afin d'initialiser la colonne
	 *
	 * @exception  SQLException  Description of the Exception
	 */
	public final Clob getEmptyClob()
							throws SQLException
	{
		return CLOB.getEmptyCLOB();
	}
} // end class OracleLob