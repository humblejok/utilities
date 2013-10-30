/**
 * Title           : $Workfile: ObjectKey.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:44 $
 * By              : $Author: Als $
 * Version number  : $Revision: 6 $
 *
 * $History: ObjectKey.java $
 * 
 * *****************  Version 6  *****************
 * User: Als          Date: 10/08/07   Time: 9:44
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * Refactoring for Java 5
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

import java.io.Serializable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
public final class ObjectKey
	implements Serializable
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -5884078887607103300L;

	private Map<String, GenericKey> functionalKeys = new HashMap<String, GenericKey>();

	/** Description of the Field */
	private GenericKey technicalKey = null;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Constructor for the ObjectKey object
	 */
	public ObjectKey() {
	}

	/**
	 * Constructeur
	 *
	 * @param  technicalKey  Description of the Parameter
	 */
	public ObjectKey(GenericKey technicalKey) {
		this.technicalKey = technicalKey;
	}

	/**
	 * Constructor for the ObjectKey object
	 *
	 * @param  technicalKeyName   Description of the Parameter
	 * @param  technicalKeyValue  Description of the Parameter
	 */
	public ObjectKey(String technicalKeyName, Long technicalKeyValue) {
		this.technicalKey = new GenericKey( technicalKeyName, technicalKeyValue );
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Adds a feature to the FunctionnalKey attribute of the ObjectKey object
	 *
	 * @param   functionalKey  The feature to be added to the FunctionnalKey attribute
	 *
	 * @throws  IllegalArgumentException
	 */
	public void addFunctionalKey(GenericKey functionalKey) {
		if(!functionalKey.isFunctionalKey()) {
			throw new IllegalArgumentException( "A Functional Key is required!" );
		}
		this.functionalKeys.put( functionalKey.getName(), functionalKey );
	}

	/**
	 * Ajoute un champ à la clé fonctionnelle
	 *
	 * @param  spFieldName  Nom du champ
	 * @param  opValue      Valeur du champ
	 */
	public void addFunctionalKey(String spFieldName, Object opValue) {
		this.functionalKeys.put( spFieldName, new GenericKey( spFieldName, opValue, true ) );
	}

	/**
	 * Méthode permettant de vérifier si deux objets <code>ObjectKey</code>
	 *
	 * <P>sont équivalent.</p>
	 *
	 * @param   object  Description of the Parameter
	 *
	 * @return  boolean True si les deux objets sont égaux.
	 */
	public boolean equals(Object object) {
		if(!(object instanceof ObjectKey)) {
			return false;
		}
		ObjectKey other = (ObjectKey)object;
		if(this.hasTechnicalKey()!=other.hasTechnicalKey()) {
			return false;
		}
		if(this.hasFunctionalKey()!=other.hasFunctionalKey()) {
			return false;
		}
		if(this.hasTechnicalKey() && !(this.technicalKey.equals( other.technicalKey ))) {
			return false;
		}
		if(this.hasFunctionalKey()) {
			if(!this.functionalKeys.equals( other.functionalKeys )) {
				return false;
			}
			Iterator<String> it = this.functionalKeys.keySet().iterator();
			while(it.hasNext()) {
				Object key = it.next();
				if(!this.functionalKeys.get( key ).equals( other.functionalKeys.get( key ) )) {
					return false;
				}
			}
			return true;
		} // end if
		return false;
	} // end method equals

	/**
	 * Description of the Method
	 *
	 * @return  Description of the Return Value
	 */
	public boolean hasFunctionalKey() {
		return this.functionalKeys.size()>0;
	}

	/**
	 * Description of the Method
	 *
	 * @return  Description of the Return Value
	 */
	public int hashCode() {
		// On détermine le hascode à partir des clés, valeurs pour les clés
		// fonctionnelles. On ajoute aussi la clé technique.
		Map<String, GenericKey>		   m		 = this.getFunctionalKeys();
		Iterator<String>   itKey     = m.keySet().iterator();
		String     sKeyName  = null;
		GenericKey sKeyValue = null;
		int		   hash		 = 0;
		while(itKey.hasNext()) {
			sKeyName  = itKey.next();
			sKeyValue = m.get( sKeyName );
			if(hash==0) {
				hash = sKeyName.hashCode()^sKeyValue.hashCode();
			} else {
				hash = hash^sKeyName.hashCode()^sKeyValue.hashCode();
			}
		} // end while
		if(this.getTechnicalKey()!=null) {
			hash ^= this.getTechnicalKey().hashCode();
		}
		return hash;
	} // end method hashCode

	/**
	 * Description of the Method
	 *
	 * @return  Description of the Return Value
	 */
	public boolean hasTechnicalKey() {
		return this.technicalKey!=null;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append( "{" );
		if(technicalKey!=null) {
			s.append( "Technical key:" ).append( technicalKey.toString() ).append( "," );
		}
		s.append( functionalKeys.toString() );
		s.append( "}" );
		return s.toString();
	} // end method toString

	/**
	 * Gets the functionalKeys attribute of the ObjectKey object
	 *
	 * @return  The functionalKeys value
	 */
	public Map<String, GenericKey> getFunctionalKeys() {
		return functionalKeys;
	}

	/**
	 * Gets the technicalKey attribute of the ObjectKey object
	 *
	 * @return  The technicalKey value
	 */
	public GenericKey getTechnicalKey() {
		return technicalKey;
	}

	/**
	 * Gets the value registered to the specified key name
	 *
	 * @param   keyName  the key name desired
	 *
	 * @return  The value specified by the key name, or null if the key is not present
	 */
	public Object getValue(String keyName) {
		if((this.technicalKey!=null) && this.technicalKey.getName().equals( keyName )) {
			return keyName;
		} else if(this.getFunctionalKeys().containsKey( keyName )) {
			return this.getFunctionalKeys().get( keyName );
		} else {
			return null;
		}
	} // end method getValue
} // end class ObjectKey
