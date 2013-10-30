/**
 * Title           : $Workfile: GenericKey.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: GenericKey.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * Refactoring
 */
package com.eim.util.model;

import java.io.Serializable;


/**
 * Description of the Class
 *
 * @author  als
 */
public class GenericKey
	implements Serializable
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -1131727021823541500L;

	/** Description of the Field */
	private boolean functionalKey;

	/** Description of the Field */
	private String name;

	/** Description of the Field */
	private Object value;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Constructor for the GenericKey object
	 *
	 * @param  name   Description of the Parameter
	 * @param  value  Description of the Parameter
	 */
	public GenericKey(String name, Long value) {
		this.name		   = name;
		this.value		   = value;
		this.functionalKey = false;
	}

	/**
	 * Constructor for the FunctionnalKey object
	 *
	 * @param  name           Description of the Parameter
	 * @param  value          Description of the Parameter
	 * @param  functionalKey  Description of the Parameter
	 */
	public GenericKey(String name, Object value, boolean functionalKey) {
		this.name		   = name;
		this.value		   = value;
		this.functionalKey = functionalKey;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Description of the Method
	 *
	 * @param   object  Description of the Parameter
	 *
	 * @return  Description of the Return Value
	 */
	public boolean equals(Object object) {
		if(!(object instanceof GenericKey)) {
			return false;
		}
		GenericKey otherKey = (GenericKey)object;
		return this.name.equals( otherKey.name ) && this.value.equals( otherKey.value ) && (this.functionalKey==otherKey.functionalKey);
	} // end method equals

	/**
	 * Description of the Method
	 *
	 * @return  Description of the Return Value
	 */
	public int hashCode() {
		int hash = 0;
		if(name!=null) {
			hash = name.hashCode();
		}
		if(value!=null) {
			if(hash==0) {
				hash = value.hashCode();
			} else {
				hash ^= value.hashCode();
			}
		} // end if
		return hash;
	} // end method hashCode

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public String toString() {
		return "{" + name + "," + value + "," + functionalKey + "}";
	}

	/**
	 * Gets the name attribute of the FunctionnalKey object
	 *
	 * @return  The name value
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the value attribute of the FunctionnalKey object
	 *
	 * @return  The value value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Gets the functionalKey attribute of the GenericKey object
	 *
	 * @return  The functionalKey value
	 */
	public boolean isFunctionalKey() {
		return functionalKey;
	}
} // end class GenericKey
