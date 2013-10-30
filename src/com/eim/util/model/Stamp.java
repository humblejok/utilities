/**
 * Title           : $Workfile: Stamp.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:40 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 6 $
 *
 * $History: Stamp.java $
 * 
 * *****************  Version 6  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:40
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * Refactoring
 */
package com.eim.util.model;

import java.io.Serializable;


/**
 * Classe perméttant de gérer l'estampillage. Utilisée pour la mise en place du lock optimiste dans les objets DAO.
 *
 * @author  als
 */
public final class Stamp
	implements Serializable
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -2878043870306204716L;
	/** Valeur de l'estampille */
	public long stamp = 0;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Constructor for the Stamp object
	 */
	public Stamp() {
	}

	/**
	 * Constructor for the Stamp object
	 *
	 * @param  value  Description of the Parameter
	 */
	public Stamp(long value) {
		this.stamp = value;
	}

	/**
	 * Constructor for the Stamp object
	 *
	 * @param  value  Description of the Parameter
	 */
	public Stamp(String value) {
		this.stamp = Long.parseLong( value );
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param   obj  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Stamp) {
			return stamp==((Stamp)obj).stamp;
		} else if(obj instanceof Long) {
			return ((Long)obj).longValue()==stamp;
		}
		return super.equals( obj );
	} // end method equals

	/**
	 * Incrémente de un l'estampille. A utiliser juste avant une mise à jour d'un enregistrement.
	 */
	public void inc() {
		this.stamp++;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public String toString() {
		return String.valueOf( stamp );
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public Long getLongValue() {
		return new Long( stamp );
	}

	/**
	 * Gets the stamp attribute of the Stamp object
	 *
	 * @return  The stamp value
	 */
	public long getStamp() {
		return stamp;
	}
} // end class Stamp
