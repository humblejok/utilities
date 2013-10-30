/**
 * Title           : $Workfile: SerializableObject.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:40 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: SerializableObject.java $
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:40
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * 
 * *****************  Version 2  *****************
 * User: Sdj          Date: 12.05.05   Time: 10:51
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * Cache problems
 * 
 * *****************  Version 1  *****************
 * User: Jpf          Date: 3/18/05    Time: 9:19a
 * Created in $/Current/Projects/utilities/src/com/eim/util/model
 * Déplacement des classes utilitaires dans le projets util
 * 
 * *****************  Version 6  *****************
 * User: Als          Date: 24.02.05   Time: 15:05
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 27.01.05   Time: 11:36
 * Updated in $/Current/Projects/core/src/com/eim/business/common/model
 */
package com.eim.util.model;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.eim.util.SystemUtil;


@SuppressWarnings("unchecked")
public abstract class SerializableObject implements Serializable {

	//~ Static fields/initializers ---------------------------------------------

	protected static final SimpleDateFormat jsonDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	
	/* Auto generated serial UID version */
	private static final long serialVersionUID = -1950296236497065207L;

	/**
	 * set all properties of the current model according to the properties of the specified object. All properties are "cloned" even the null properties.
	 *
	 * @param  object      Add comments
	 * @param  overrideId  Add comments
	 */
	public abstract void populate(SerializableObject object, boolean overrideId);

	/**
	 * set all properties of the current model according to the properties specified in the map. All properties are "cloned" but not the null properties.
	 *
	 * @param  map         Add comments
	 * @param  overrideId  Add comments
	 */
	public abstract void populate(Map map, boolean overrideId);

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public SerializableObject deepClone() {
		return (SerializableObject)SystemUtil.deepClone( this );
	} // end method deepClone

	/**
	 * Méthode permettant de convertir les données contenues dans un
	 *
	 * <p>objet dans une chaine de caractères.</p>
	 *
	 * @return  String Les données formatées.
	 */
	public String toString() {
		StringBuffer stringRepresentation = new StringBuffer();
		Field[]		 aTheFields			  = null;
		Class		 theClass			  = this.getClass();
		stringRepresentation.append( '{' );
		while(theClass!=null) {
			aTheFields = theClass.getDeclaredFields();
			// Pour voir les attributs privés des classes filles
			AccessibleObject.setAccessible( aTheFields, true );
			boolean first = true;
			for(int i = 0; i<aTheFields.length; i++) {
				if(!Modifier.isStatic( aTheFields[i].getModifiers() )) {
					if(first) {
						first = false;
					} else {
						stringRepresentation.append( ", " );
					}
					stringRepresentation.append( aTheFields[i].getName() );
					stringRepresentation.append( '=' );
					try {
						stringRepresentation.append( aTheFields[i].get( this ).toString() );
					} catch(NullPointerException npe) {
						if(getLogger().isDebugEnabled()) {
							getLogger().debug( "", npe );
						}
					} catch(IllegalAccessException iae) {
						if(getLogger().isDebugEnabled()) {
							getLogger().debug( "", iae );
						}
					} catch(IllegalArgumentException eaee) {
						if(getLogger().isDebugEnabled()) {
							getLogger().debug( "", eaee );
						}
					}
				} // end if
			} // end for
			AccessibleObject.setAccessible( aTheFields, false );
			theClass = theClass.getSuperclass();
		} // end while
		return stringRepresentation.toString();
	} // end method toString

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public ObjectKey getObjectKey() {
		return new ObjectKey();
	}

	/**
	 * Logger for this class
	 *
	 * @return
	 */
	protected final Logger getLogger() {
		return Logger.getLogger( this.getClass() );
	}

	/**
	 * returns an xml tag that will be used to encapsulated the current object data serialization
	 *
	 * @return  the xml tag that (it is the class name of the current object)
	 */
	protected final String getXmlTag() {
		return this.getClass().getName();
	}
	
	public void fromJSON(JSONObject object) throws ParseException {
		Iterator keys = object.keys();
		while (keys.hasNext()) {
			String key = keys.next().toString();
			try {
				Field field = this.getClass().getField(key);
				if (field.getType().equals(String.class)) {
					field.set(this, object.getString(key));
				} else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
					field.set(this, object.getDouble(key));
				} else if (field.getType().equals(long.class) || field.getType().equals(Long.class)) {
					field.set(this, object.getLong(key));
				} else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
					field.set(this, object.getInt(key));
				} else if (field.getType().equals(float.class) || field.getType().equals(Float.class)) {
					field.set(this, new Double(object.getDouble(key)).floatValue());
				} else if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
					field.set(this, object.getBoolean(key));
				} else if (field.getType().equals(Date.class)) {
					String string = object.getString(key);
					field.set(this, jsonDateFormat.parse(string));
				} else {
					SerializableObject subObject = (SerializableObject)field.getType().getConstructor().newInstance();
					subObject.fromJSON(object.getJSONObject(key));
					field.set(this, subObject);
				}
			} catch (SecurityException e) {
				throw new IllegalArgumentException( "The given JSON data does not match the target model!", e );
			} catch (NoSuchFieldException e) {
				throw new IllegalArgumentException( "The given JSON data does not match the target model!", e );
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException( "The given JSON data does not match the target model!", e );
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException( "The given JSON data does not match the target model!", e );
			} catch (JSONException e) {
				throw new IllegalArgumentException( "The given JSON data does not match the target model!", e );
			} catch (InstantiationException e) {
				throw new IllegalArgumentException( "The given JSON data does not match the target model!", e );
			} catch (InvocationTargetException e) {
				throw new IllegalArgumentException( "The given JSON data does not match the target model!", e );
			} catch (NoSuchMethodException e) {
				throw new IllegalArgumentException( "The given JSON data does not match the target model!", e );
			}
		}
	}
	
} // end class SerializableObject
