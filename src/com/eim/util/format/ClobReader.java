/**
 * Title           : $Workfile: ClobReader.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/17/07 5:17p $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 4 $
 */

package com.eim.util.format;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.Clob;
import java.sql.SQLException;

import org.jdom.Document;
import org.jdom.JDOMException;

import org.jdom.input.SAXBuilder;


/**
 * Description of the Class
 *
 * @author  als
 */
public final class ClobReader {

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new ClobReader object.
	 */
	private ClobReader() {
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * Description of the Method
	 *
	 * @param      clob  Description of the Parameter
	 *
	 * @return     Description of the Return Value
	 *
	 * @exception  SQLException  Description of the Exception
	 */
	public static String readClob( Clob clob )
	        throws SQLException {
		BufferedReader bur = new BufferedReader( clob.getCharacterStream() );
		StringBuffer   sb  = new StringBuffer( "" );
		String         s;
		try {
			s=bur.readLine();
			while(s!=null){
				sb.append(s);
				s=bur.readLine();
				if(s!=null && s.length()>0){
					sb.append(System.getProperty( "line.separator" ));
				}
			}
			/*
			while( (s = bur.readLine()) != null ) {
				sb.append( s + System.getProperty( "line.separator" ) );
			}
			*/
			bur.close();
		} catch( IOException ioe ) {
			ioe.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * Description of the Method
	 *
	 * @param      clob  Description of the Parameter
	 *
	 * @return     Description of the Return Value
	 *
	 * @exception  JDOMException  Description of the Exception
	 * @exception  SQLException   Description of the Exception
	 */
	public static Document readClobAsDocument( Clob clob )
	        throws JDOMException, SQLException {
		SAXBuilder builder = new SAXBuilder();

		try {
			return builder.build( clob.getCharacterStream() );
		} catch( IOException e ) {
			return null;
		}
	}
}
