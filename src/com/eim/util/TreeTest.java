/**
 * Title           : $Workfile: TreeTest.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 11:38 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 *
 * $History: TreeTest.java $
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 10/08/07   Time: 11:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring for Java 5
 * 
 * *****************  Version 1  *****************
 * User: Als          Date: 22.02.05   Time: 10:07
 * Created in $/Current/Projects/utilities/src/com/eim/util
 * Gestion d'arbre
 */
package com.eim.util;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


/**
 * 
 */
public final class TreeTest {

	//~ Static fields/initializers ---------------------------------------------

	/** Logger for this class */
	private static final Logger logger = Logger.getLogger( TreeTest.class );

	static {
		BasicConfigurator.resetConfiguration();
		Logger rootLogger = Logger.getLogger( "com.eim" );
		rootLogger.setLevel( Level.DEBUG );
		rootLogger.addAppender( new ConsoleAppender( new PatternLayout( "%-20d %-5p [%t]: %m%n" ) ) );
	}

	//~ Constructors -----------------------------------------------------------

	/**
	 * 
	 */
	protected TreeTest() {
		super();
		Tree<String> tree = new Tree<String>();
		tree.add( null, "Book" );
		tree.add( "Book", "Chapter 1" );
		tree.add( "Book", "Chapter 2" );
		tree.add( "Chapter 2", "Para 1" );
		tree.add( "Chapter 2", "Para 2" );
		tree.add( "Chapter 2", "Para 3" );
		tree.add( "Para 3", "Part 1" );
		tree.add( "Para 3", "Part 2" );
		tree.add( "Para 3", "Part 3" );
		tree.add( "Para 3", "Part 4" );
		tree.add( "Book", "Chapter 3" );
		tree.add( "Book", "Chapter 4" );
		tree.add( "Book", "Chapter 5" );
		logger.info( tree.toString() );
		logger.info( tree.extractTree( "Chapter 2" ).toString() );
		logger.info( tree.extractTree( "Book" ).truncate("Chapter 2").toString() );
	} // end ctor TreeTest

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param  args
	 */
	public static void main(String[] args) {
		new TreeTest();
	}
} // end class TreeTest
