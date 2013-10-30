/**
 * Title           : $Workfile: XmlComparisonResult.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 26.02.04 15:50 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 */

package com.eim.util.xml;

import org.jdom.Element;


/**
 * JavaDoc class comment
 *
 * @author vj
 */
@SuppressWarnings("unchecked")
class XmlComparisonResult implements Comparable, Cloneable {
	public static final int NO_RESULT = -1;
	protected Element original;
	protected Element modified;
	protected int errors;

	/**
	 * Creates a new XmlComparisonResult object.
	 *
	 * @param original DOCUMENT ME!
	 * @param modified DOCUMENT ME!
	 * @param errors DOCUMENT ME!
	 */
	public XmlComparisonResult( final Element original, final Element modified, final int errors ) {
		this.original = original;
		this.modified = modified;
		this.errors = errors;
	}

	/**
	 * Creates a new XmlComparisonResult object.
	 *
	 * @param elt1 DOCUMENT ME!
	 * @param elt2 DOCUMENT ME!
	 * @param errors DOCUMENT ME!
	 * @param firstIsOriginal DOCUMENT ME!
	 */
	public XmlComparisonResult( final Element elt1, final Element elt2, final int errors, final boolean firstIsOriginal ) {
		if( firstIsOriginal ) {
			this.original = elt1;
			this.modified = elt2;
			this.errors = errors;
		} else {
			this.original = elt2;
			this.modified = elt1;
			this.errors = errors;
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param elt Add comments
	 *
	 * @return Add comments
	 */
	public final boolean isOriginal( final Element elt ) {
		if( this.original == elt ) {
			return true;
		} else if( this.modified == elt ) {
			return false;
		} else {
			throw new IllegalArgumentException( "The element is not part of this comparison result!" );
		}
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param o Add comments
	 *
	 * @return Add comments
	 */
	public final int compareTo( final Object o ) {
		return this.errors - ( ( XmlComparisonResult ) o ).errors;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return Add comments
	 */
	public final Object clone(  ) {
		return new XmlComparisonResult( original, modified, errors );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param elt Add comments
	 *
	 * @return Add comments
	 */
	public final Element getOpponent( final Element elt ) {
		if( elt == original ) {
			return modified;
		} else if( elt == modified ) {
			return original;
		} else {
			return null;
		}
	}
}
