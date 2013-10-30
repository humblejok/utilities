/**
 * Title           : $Workfile: ISimpleValueToken.java $
 * Copyright       : EIM (c) 2006
 * Updates         : $Date: 3/27/06 2:49p $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 1 $
 *
 * $History: ISimpleValueToken.java $
 * 
 * *****************  Version 1  *****************
 * User: Jpf          Date: 3/27/06    Time: 2:49p
 * Created in $/Current/Projects/utilities/src/com/eim/util/model
 * Séparation moteur de calcul du moteur de reporting pour la Ranking
 * Table
 */
package com.eim.util.model;

import java.util.Date;


/**
 * Token simple au format date valeur
 */
public interface ISimpleValueToken {

	//~ Methods ----------------------------------------------------------------

	/**
	 * @return  Date de la valeur
	 */
	public abstract Date getDate();

	/**
	 * @return  La valeur
	 */
	public abstract Double getValue();
	
	/**
	 * 
	 * @param status le status final ou estimé ou null
	 */
	public abstract void setStatus(String status);
	
	/**
	 * 
	 * @return le status final ou estimé ou null
	 */
	public abstract String getStatus();

	/**
	 * @param  date  Date de la valeur
	 */
	public abstract void setDate(Date date);

	/**
	 * @param  double1  valeur
	 */
	public abstract void setValue(Double double1);
} // end interface ISimpleValueToken
