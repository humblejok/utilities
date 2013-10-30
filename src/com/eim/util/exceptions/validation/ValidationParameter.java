/**
 * Title           : $Workfile: ValidationParameter.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: ValidationParameter.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * Refactoring
 */
package com.eim.util.exceptions.validation;

import java.io.Serializable;


/**
 * Description of the Class
 *
 * @author  als
 */
public class ValidationParameter
	implements Serializable
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 4924065821872085638L;

	/** the user alias defined for this parameter */
	private final String alias;

	/** the value of the parameter to test */
	private final Object parameter;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Constructor for the ValidationParameter object
	 *
	 * @param  alias      the user alias defined for this parameter
	 * @param  parameter  the value of the parameter to test
	 */
	public ValidationParameter(String alias, Object parameter) {
		this.alias     = alias;
		this.parameter = parameter;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Gets the alias attribute of the ValidationParameter object
	 *
	 * @return  The alias value
	 */
	public final String getAlias() {
		return alias;
	}

	/**
	 * Gets the parameter attribute of the ValidationParameter object
	 *
	 * @return  The parameter value
	 */
	public final Object getParameter() {
		return parameter;
	}
} // end class ValidationParameter
