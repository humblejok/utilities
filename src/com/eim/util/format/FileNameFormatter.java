/**
 * Title           : $Workfile: FileNameFormatter.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 26.03.07 13:40 $
 * By              : $Author: Ac $
 * Version number  : $Revision: 3 $
 */
package com.eim.util.format;

/**
 * Classe utilitaire permettant de manipuler les noms de fichiers
 *
 * @author  jpf
 */
public final class FileNameFormatter {

	//~ Constructors ---------------------------------------------------------------------------------

	/**
	 * Creates a new FileNameFormatter object.
	 */
	private FileNameFormatter() {
	}

	//~ Methods --------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param   spFileName  Nom de fichier
	 *
	 * @return  Nom de fichier sans les caractères interdits par Windows
	 */
	public static String cleanFileName( String spFileName ) {
		StringBuffer stringBuffer = new StringBuffer();
		char[]       chars        = spFileName.toCharArray();

		for( int i = 0; i < chars.length; i++ ) {
			String symbol;
			switch( chars[i] ) {
				case '\\':
				case '/':
				case ':':
				case '*':
				case '?':
				case '>':
				case '<':
				case '|':
				case '"':
					symbol = "_";

					break;

				case '.':
				case ';':
					symbol = " ";

					break;

				case '\n':
				case '\t':
					symbol = "";

					break;

				default:
					symbol = String.valueOf( chars[i] );

					break;
			}
			stringBuffer.append( symbol );
		}

		// Suppression des doubles espaces
		String sResult = stringBuffer.toString();
		while( sResult.indexOf( "  " ) != -1 ) {
			sResult = sResult.replaceAll( "  ", " " );
		}

		return sResult;
	}
}
