/**
 * Title           : $Workfile: ChartsProperties.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 12/21/07 4:58p $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 3 $
 *
 * $History: ChartsProperties.java $
 * 
 * *****************  Version 3  *****************
 * User: Jpf          Date: 12/21/07   Time: 4:58p
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * 
 * *****************  Version 2  *****************
 * User: Sdj          Date: 2.02.06    Time: 10:42
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * V2.9.1: Migration to CeWolf 1.0
 * 
 * *****************  Version 1  *****************
 * User: Sdj          Date: 8.04.05    Time: 9:46
 * Created in $/Current/Projects/utilities/src/com/eim/util
 * 
 * *****************  Version 1  *****************
 * User: Sdj          Date: 6.04.05    Time: 11:51
 * Created in $/Current/Projects/application/src/com/eim/application/common/util
 * Define the charts common properties
 */
package com.eim.util;

import java.awt.Color;


/**
 * This class defines the common properties for displaying charts.
 *
 * @author  sdj
 */
public class ChartsProperties {

	//~ Static fields/initializers ---------------------------------------------

	/** Colors for each curves of a chart. */
	public static final Color[] CHART_COLORS = {
		Color.blue,
		Color.red,
		Color.green,
		Color.orange,
		Color.cyan,
		Color.magenta,
		Color.gray,
		Color.pink,
		Color.yellow
	};
	
	public static final Color BACKGROUND = new Color(222,227,238);
	public static final Color EIM_BLUE = new Color(1,43,127);
} // end class ChartsProperties