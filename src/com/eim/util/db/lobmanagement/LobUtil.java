/**
 * Title           : $Workfile: LobUtil.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 25.05.05 16:26 $
 * By              : $Author: Als $
 * Version number  : $Revision: 5 $
 *
 * $History: LobUtil.java $
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 25.05.05   Time: 16:26
 * Updated in $/Current/Projects/utilities/src/com/eim/util/db/lobmanagement
 */
package com.eim.util.db.lobmanagement;

import org.jdom.Document;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * JavaDoc class comment
 *
 * @author   als
 * @version  $Revision: 5 $
 */
public interface LobUtil {

	//~ Methods ----------------------------------------------------------------

	/**
	 * Met à jour un champ de type BLOB dans la DB
	 *
	 * @param   con          Connection valide à la DB
	 * @param   bapImage     Image à insérer
	 * @param   spTableName  Nom de la table
	 * @param   spFieldName  Nom du champ de type Blob
	 * @param   spWhere      clause where pour identifier le ou les enregistrements (sans le mot clé WHERE)
	 *
	 * @throws  SQLException  DOCUMENT ME!
	 */
	void updateBlobField(Connection con, byte[] bapImage, String spTableName, String spFieldName, String spWhere)
				  throws SQLException;

	/**
	 * Met à jour un champ de type BLOB dans la DB
	 *
	 * @param   con          Connection valide à la DB
	 * @param   data         les données à inserer
	 * @param   spTableName  Nom de la table
	 * @param   spFieldName  Nom du champ de type Blob
	 * @param   spWhere      clause where pour identifier le ou les enregistrements (sans le mot clé WHERE)
	 *
	 * @throws  SQLException  DOCUMENT ME!
	 */
	void updateBlobField(Connection con, Object data, String spTableName, String spFieldName, String spWhere)
				  throws SQLException;

	/**
	 * Met à jour un champ de type CLOB dans la DB
	 *
	 * @param   con          Connection valide à la DB
	 * @param   spValue      Nouvelle valeur du champ
	 * @param   spTableName  Nom de la table
	 * @param   spFieldName  Nom du champ de type Clob
	 * @param   spWhere      clause where pour identifier le ou les enregistrements (sans le mot clé WHERE)
	 *
	 * @throws  SQLException  DOCUMENT ME!
	 */
	void updateClobField(Connection con, String spValue, String spTableName, String spFieldName, String spWhere)
				  throws SQLException;

	/**
	 * Met à jour un champ de type CLOB dans la DB
	 *
	 * @param   con          Connection valide à la DB
	 * @param   document     Nouvelle valeur du champ
	 * @param   spTableName  Nom de la table
	 * @param   spFieldName  Nom du champ de type Clob
	 * @param   spWhere      clause where pour identifier le ou les enregistrements (sans le mot clé WHERE)
	 *
	 * @throws  SQLException  DOCUMENT ME!
	 */
	void updateClobField(Connection con, Document document, String spTableName, String spFieldName, String spWhere)
				  throws SQLException;

	/**
	 * DOCUMENT ME!
	 *
	 * @return  un champ clob de type empty Blob afin d'initialiser la colonne
	 *
	 * @throws  SQLException  DOCUMENT ME!
	 */
	Blob getEmptyBlob()
			   throws SQLException;

	/**
	 * DOCUMENT ME!
	 *
	 * @return  un champ clob de type empty Clob afin d'initialiser la colonne
	 *
	 * @throws  SQLException  DOCUMENT ME!
	 */
	Clob getEmptyClob()
			   throws SQLException;
} // end interface LobUtil