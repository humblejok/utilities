/**
 * Title           : $Workfile: IdentifiableObject.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 5 $
 *
 * $History: IdentifiableObject.java $
 * 
 * *****************  Version 5  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * Refactoring
 */
package com.eim.util.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.jdom.Element;

import com.eim.util.exceptions.CodingFailureException;
import com.eim.util.exceptions.SessionFailureException;
import com.eim.util.xml.IdentifiableObjectTags;


/**
 * Classe abstraite dont doivent h�riter toutes les classes repr�sentant des objets pass�s par valeur et qui ne sont pas estampill�s.
 *
 * @author 
 * @version
 */
@SuppressWarnings("unchecked")
public class IdentifiableObject extends SerializableObject {

	//~ Instance fields --------------------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 2541323409879897950L;
	
	/** Attribut ID de l'enregistrement. */
	public Long id;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Constructeur.
	 *
	 * @param  id  Description of the Parameter
	 */
	public IdentifiableObject(Long id) {
		this.id = id;
	}

	/**
	 * Clone constructor
	 *
	 * @param  identifiableObject  the object to clone
	 */
	public IdentifiableObject(IdentifiableObject identifiableObject) {
		this.id = new Long( identifiableObject.id.longValue() );
	}

	/**
	 * Constructor that extracts all the data from the provided JDom Element.
	 *
	 * @param   elt     the JDOM Element containing the object data
	 * @param   father  true if the provided element is the parent node of the node containing the desired data. false if the provided node is the node
	 *                  containing the Data
	 *
	 * @throws  IllegalArgumentException  DOCUMENT ME!
	 */
	public IdentifiableObject(Element elt, boolean father) {
		if(elt==null) {
			throw new IllegalArgumentException( "The XML node cannot be null!" );
		}
		if(father) {
			elt = elt.getChild( getXmlTag() );
			if(elt==null) {
				throw new IllegalArgumentException( "The given XML node does not contain " + getXmlTag() + " child!" );
			}
		}
		try {
			this.id = new Long( elt.getChildText( com.eim.util.xml.IdentifiableObjectTags.ID_TAG ) );
		} catch(NullPointerException npe) {
			this.id = null;
		} catch(NumberFormatException nfe) {
			// Wrong id will be generated by DAO
			this.id = null;
		}
	} // end ctor IdentifiableObject

	/**
	 * Constructeur.
	 */
	protected IdentifiableObject() {
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * shallow copy
	 *
	 * @param   other  IdentifiableObject that we want to copy
	 *
	 * @throws  SessionFailureException  DOCUMENT ME!
	 */
	public void copy(IdentifiableObject other) {
		if(other!=null) {
			Class theClass = other.getClass();
			while(theClass!=null) {
				Field[] modelFields = theClass.getDeclaredFields();
				for(int i = 0; i<modelFields.length; i++) {
					String fieldName = modelFields[i].getName();
					try {
						Field modelLocalField = theClass.getDeclaredField( fieldName );
						if(!Modifier.isFinal( modelLocalField.getModifiers() )) {
							modelLocalField.setAccessible( true );
							modelLocalField.set( this, modelLocalField.get( other ) );
						}
					} catch(Exception e) {
						throw new SessionFailureException( "unable to copy model", e );
					}
				} // end for
				theClass = theClass.getSuperclass();
			} // end while
		} // end if
	} // end method copy

	/**
	 * DOCUMENT ME!
	 *
	 * @param   obj  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof IdentifiableObject)) {
			return false;
		}
		IdentifiableObject other = (IdentifiableObject)obj;
		return ((id==null)?(other.id==null):id.equals( other.id ));
	} // end method equals

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public int hashCode() {
		int result = 17;
		result = (37 * result) + id.hashCode();
		return result;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @param   object      Add comments
	 * @param   overrideId  Add comments
	 *
	 * @throws  CodingFailureException  DOCUMENT ME!
	 */
	public void populate(SerializableObject object, boolean overrideId) {
		if(object instanceof IdentifiableObject) {
			if(overrideId) {
				id = ((IdentifiableObject)object).id;
			}
		} else {
			throw new CodingFailureException( "Wrong object for populate" );
		}
	} // end method populate

	/**
	 * JavaDoc method comments
	 *
	 * @param  map         Add comments
	 * @param  overrideId  Add comments
	 */
	public void populate(Map map, boolean overrideId) {
		if(overrideId) {
			if(map.get( IdentifiableObjectTags.ID_TAG )!=null) {
				id = (Long)map.get( IdentifiableObjectTags.ID_TAG );
			}
		}
	} // end method populate

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append( '{' );
		buffer.append( "id=" ).append( id );
		buffer.append( '}' );
		return buffer.toString();
	} // end method toString

	/**
	 * Generates a JDOM Element that contains all the current data of this object.
	 *
	 * @return  a new JDOM Element filled with this object's data
	 */
	public Element toXml() {
		Element eRoot  = new Element( getXmlTag() );
		Element eChild = new Element( IdentifiableObjectTags.ID_TAG );
		if(id!=null) {
			eChild.addContent( id.toString() );
		}
		eRoot.addContent( eChild );
		return eRoot;
	} // end method toXml

	/**
	 * Gets the id attribute of the IdentifiableObject object
	 *
	 * @return  The id value
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the ObjectKey of the IdentifiableObject object
	 *
	 * @return  The ObjectKey of this object
	 */
	public ObjectKey getObjectKey() {
		if(this.getId()!=null) {
			return new ObjectKey( new GenericKey( IdentifiableObjectTags.ID_TAG, this.getId() ) );
		} else {
			return new ObjectKey();
		}
	} // end method getObjectKey

	/**
	 * Sets the id attribute of the IdentifiableObject object
	 *
	 * @param  id  The new id value
	 */
	public void setId(Long id) {
		this.id = id;
	}
} // end class IdentifiableObject
