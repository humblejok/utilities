/**
 * Title           : $Workfile: ComparisonStorage.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 12/12/07 14:55 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 8 $
 *
 * $History: ComparisonStorage.java $
 * 
 * *****************  Version 8  *****************
 * User: Sdj          Date: 12/12/07   Time: 14:55
 * Updated in $/Current/Projects/utilities/src/com/eim/util/xml
 * Mass validation for fund
 * 
 * *****************  Version 7  *****************
 * User: Sdj          Date: 5/11/07    Time: 10:22
 * Updated in $/Current/Projects/utilities/src/com/eim/util/xml
 * Optim comparison
 * 
 * *****************  Version 6  *****************
 * User: Als          Date: 10/08/07   Time: 9:46
 * Updated in $/Current/Projects/utilities/src/com/eim/util/xml
 * Refactoring for Java 5
 * 
 * *****************  Version 5  *****************
 * User: Sdj          Date: 11/07/07   Time: 14:54
 * Updated in $/Current/Projects/utilities/src/com/eim/util/xml
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/xml
 * Refactoring
 */
/**
 * Title           : $Workfile: ComparisonStorage.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 12/12/07 14:55 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 8 $
 */
package com.eim.util.xml;

import org.jdom.Attribute;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * JavaDoc class comment
 *
 * @author  vj
 */
@SuppressWarnings("unchecked")
public class ComparisonStorage {

	//~ Instance fields --------------------------------------------------------

	private ArrayList<ComparisonStorage>		    childResults;
	private XmlComparisonResult result;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new ComparisonStorage object.
	 */
	public ComparisonStorage() {
		this.result		  = null;
		this.childResults = null;
	}

	/**
	 * Creates a new ComparisonStorage object.
	 *
	 * @param  original  DOCUMENT ME!
	 * @param  modified  DOCUMENT ME!
	 */
	public ComparisonStorage(Element original, Element modified) {
		this.result		  = null;
		this.childResults = null;
		this.compare( original, modified );
	}

	/**
	 * Creates a new ComparisonStorage object.
	 *
	 * @param  original  DOCUMENT ME!
	 * @param  modified  DOCUMENT ME!
	 * @param  errors    DOCUMENT ME!
	 */
	private ComparisonStorage(Element original, Element modified, int errors) {
		this.result		  = new XmlComparisonResult( original, modified, errors );
		this.childResults = null;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param   elt  Add comments
	 *
	 * @return  Add comments
	 */
	public static int countChildren(Element elt) {
		if(elt==null) {
			return 0;
		}
		if(elt.getChildren().size()>0) {
			int		 total = 0;
			Iterator it    = elt.getChildren().iterator();
			while(it.hasNext()) {
				total += countChildren( (Element)it.next() );
			}
			return total + elt.getChildren().size();
		} else {
			return 0;
		}
	} // end method countChildren

	/**
	 * JavaDoc method comments
	 *
	 * @param   eltList  Add comments
	 *
	 * @return  Add comments
	 */
	public static int countChildren(List eltList) {
		int		 errors = 0;
		Iterator it     = eltList.iterator();
		while(it.hasNext()) {
			errors += countChildren( (Element)it.next() );
		}
		return errors;
	} // end method countChildren

	/**
	 * JavaDoc method comments
	 *
	 * @param  original  Add comments
	 * @param  modified  Add comments
	 */
	public void compare(Element original, Element modified) {
		// we don't need this check because we have made enough test
		// if( !original.getName().equals(modified.getName()) ){
		// throw new IllegalArgumentException();
		// }
		int errors = compareAttributes( original, modified );
		if(original.getChildren().size()>0) {
			if(modified.getChildren().size()>0) {
				this.result		  = new XmlComparisonResult( original, modified, 0 );
				this.childResults = new ArrayList<ComparisonStorage>();
				ListIterator originalIt = original.getChildren().listIterator();
				while(originalIt.hasNext()) {
					Element originalChild = (Element)originalIt.next();
					if(modified.getChildren( originalChild.getName() ).size()==0) {
						this.addChild( new ComparisonStorage( originalChild, null, countChildren( originalChild ) ) );
					} else {
						Iterator modifiedIt = modified.getChildren( originalChild.getName() ).iterator();
						while(modifiedIt.hasNext()) {
							Element modifiedChild = (Element)modifiedIt.next();
							this.addChild( new ComparisonStorage( originalChild, modifiedChild ) );
							if (originalChild.getChild("ID")!=null && modifiedChild.getChild("ID")!=null) {
								if (originalChild.getChildText("ID").equals(modifiedChild.getChildText("ID"))) {
									break;
								}
							}
						}
					} // end if
				} // end while
				Iterator modifiedIt = modified.getChildren().iterator();
				while(modifiedIt.hasNext()) {
					Element modifiedChild = (Element)modifiedIt.next();
					if(original.getChildren( modifiedChild.getName() ).size()==0) {
						this.addChild( new ComparisonStorage( null, modifiedChild, countChildren( modifiedChild ) ) );
					} else {
						ListIterator originalChildrenIt = original.getChildren( modifiedChild.getName() ).listIterator();
						boolean newItem = modifiedChild.getChild("ID")!=null;
						while (originalChildrenIt.hasNext() && newItem) {
							Element originalChild = (Element)originalChildrenIt.next();
							if (modifiedChild.getChild("ID")!=null && originalChild.getChild("ID")!=null
								&& originalChild.getChildText("ID").equals(modifiedChild.getChildText("ID"))) {
								newItem = false;
							}
						}
						if (newItem) {
							this.addChild( new ComparisonStorage( null, modifiedChild, countChildren( modifiedChild ) ) );
						}
					}
				}
			} else {
				this.result = new XmlComparisonResult( original, modified, countChildren( original ) );
			}
		} else {
			if(modified.getChildren().size()>0) {
				this.result = new XmlComparisonResult( original, modified, countChildren( modified ) );
			} else {
				if(original.getText().equals( modified.getText() )) {
					this.result = new XmlComparisonResult( original, modified, 0 );
				} else {
					this.result = new XmlComparisonResult( original, modified, 1 );
				}
			} // end if
		} // end if
		// as we want the differencies between attributes be dominent
		// we multiply the number of errors by the number of sub-elements
		this.result.errors += (errors * (countChildren( original ) + countChildren( modified )));
	} // end method compare

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public Element toXml() {
		if(this.result.errors>0) {
			if(this.result.original==null) {
				Element elt = (Element)this.result.modified.clone();
				elt.setAttribute( "comparison-flag", "created" );
				return elt;
			} else if(this.result.modified==null) {
				Element elt = (Element)this.result.original.clone();
				elt.setAttribute( "comparison-flag", "deleted" );
				return elt;
			} else {
				if((this.childResults==null) || (this.childResults.size()==0)) {
					Element elt = new Element( this.result.original.getName() );
					Element ori = (Element)this.result.original.clone();
					ori.setName( "original" );
					Element mod = (Element)this.result.modified.clone();
					mod.setName( "modified" );
					elt.addContent( ori );
					elt.addContent( mod );
					return elt;
				} else {
					Element elt = new Element( this.result.original.getName() );
					// Clonage des attributs
					Iterator itAttributes = this.result.original.getAttributes().iterator();
					while(itAttributes.hasNext()) {
						elt.setAttribute( (Attribute)((Attribute)itAttributes.next()).clone() );
					}
					Iterator<ComparisonStorage> it = this.childResults.iterator();
					while(it.hasNext()) {
						ComparisonStorage cs = it.next();
						elt.addContent( cs.toXml() );
					}
					return elt;
				} // end if
			} // end if-else
		} else {
			if(this.result.original==null) {
				Element elt = (Element)this.result.modified.clone();
				elt.setAttribute( "comparison-flag", "created" );
				return elt;
			} else {
				return (Element)this.result.original.clone();
			}
		} // end if
	} // end method toXml

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public Element differencesToXml() {
		if(this.result.errors>0) {
			if(this.result.original==null) {
				Element elt = (Element)this.result.modified.clone();
				elt.setAttribute( "comparison-flag", "created" );
				return elt;
			} else if(this.result.modified==null) {
				Element elt = (Element)this.result.original.clone();
				elt.setAttribute( "comparison-flag", "deleted" );
				return elt;
			} else {
				if((this.childResults==null) || (this.childResults.size()==0)) {
					Element elt = new Element( this.result.original.getName() );
					Element ori = (Element)this.result.original.clone();
					ori.setName( "original" );
					Element mod = (Element)this.result.modified.clone();
					mod.setName( "modified" );
					elt.addContent( ori );
					elt.addContent( mod );
					return elt;
				} else {
					Element elt = new Element( this.result.original.getName() );
					// Clonage des attributs
					Iterator itAttributes = this.result.original.getAttributes().iterator();
					while(itAttributes.hasNext()) {
						elt.setAttribute( (Attribute)((Attribute)itAttributes.next()).clone() );
					}
					Iterator<ComparisonStorage> it = this.childResults.iterator();
					while(it.hasNext()) {
						ComparisonStorage cs = it.next();
						Element append = cs.differencesToXml();
						if (append!=null) {
							elt.addContent( append );
						}
					}
					return elt;
				} // end if
			} // end if-else
		} else {
			return null;
		} // end if
	} // end method toXml
	
	/**
	 * JavaDoc method comments
	 *
	 * @param  cs  Add comments
	 */
	private void addChild(ComparisonStorage cs) {
		if((cs==null) || ((cs.result.original==null) && (cs.result.modified==null))) {
			// nothing to do
			return;
		}
		if(this.childResults==null) {
			// there is no child so we add it
			this.childResults = new ArrayList<ComparisonStorage>();
			this.childResults.add( cs );
		} else if(this.childResults.size()==0) {
			// there is no child so we add it
			this.childResults.add( cs );
		} else {
			if(cs.result.original==null) {
				ComparisonStorage previousResultForModified = this.getChildFor( cs.result.modified, false );
				if(previousResultForModified==null) {
					// we do have previous comparison with the modified element
					this.childResults.add( cs );
				} else {
					if(previousResultForModified.result.compareTo( cs.result )>0) {
						// we have a better comparison
						this.childResults.remove( previousResultForModified );
						Element oldOriginal = previousResultForModified.result.original;
						if(oldOriginal!=null) {
							this.childResults.add( new ComparisonStorage( oldOriginal, null, ComparisonStorage.countChildren( oldOriginal ) ) );
						}
					} // end if
				} // end if
			} else if(cs.result.modified==null) {
				ComparisonStorage previousResultForOriginal = this.getChildFor( cs.result.original, true );
				if(previousResultForOriginal==null) {
					// we do have previous comparison with the original element
					this.childResults.add( cs );
				} else {
					if(previousResultForOriginal.result.compareTo( cs.result )>0) {
						// we have a better comparison
						this.childResults.remove( previousResultForOriginal );
						Element oldModified = previousResultForOriginal.result.modified;
						if(oldModified!=null) {
							this.childResults.add( new ComparisonStorage( oldModified, null, ComparisonStorage.countChildren( oldModified ) ) );
						}
					} // end if
				} // end if
			} else {
				// both items of the current comparison are not null
				ComparisonStorage previousResultForOriginal = this.getChildFor( cs.result.original, true );
				ComparisonStorage previousResultForModified = this.getChildFor( cs.result.modified, false );
				if((previousResultForOriginal==null) && (previousResultForModified==null)) {
					// we do have previous comparison with any element
					this.childResults.add( cs );
				} else if(previousResultForOriginal==null) {
					// we do have previous comparison with the original element
					// we have to add the new comparison for the original element
					// and may be to remove the previousResultForModified if necessary
					if(previousResultForModified.result.compareTo( cs.result )>0) {
						// we have a better result
						Element oldOriginal = previousResultForModified.result.original;
						if((oldOriginal!=null) && (oldOriginal!=cs.result.original)) {
							this.childResults.add( new ComparisonStorage( oldOriginal, null, ComparisonStorage.countChildren( oldOriginal ) ) );
						}
						this.childResults.remove( previousResultForModified );
						this.childResults.add( cs );
					} else {
						// we do not have a better result
						// so we add the original element of the new comparison as a deleted element
						this.childResults.add( new ComparisonStorage( cs.result.original, null, ComparisonStorage.countChildren( cs.result.original ) ) );
					}
				} else if(previousResultForModified==null) {
					// we do have previous comparison with the modified element
					// we have to add the new comparison for the modified element
					// and may be to remove the previousResultForOriginal if necessary
					if(previousResultForOriginal.result.compareTo( cs.result )>0) {
						// we have a better result
						Element oldModified = previousResultForOriginal.result.modified;
						if((oldModified!=null) && (oldModified!=cs.result.modified)) {
							this.childResults.add( new ComparisonStorage( null, oldModified, ComparisonStorage.countChildren( oldModified ) ) );
						}
						this.childResults.remove( previousResultForOriginal );
						this.childResults.add( cs );
					} else {
						// we do not have a better result
						// so we add the modified element of the new comparison as a created element
						this.childResults.add( new ComparisonStorage( null, cs.result.modified, ComparisonStorage.countChildren( cs.result.modified ) ) );
					}
				} else {
					// we have a result for both items
					if(previousResultForOriginal==previousResultForModified) {
						// we have a previous result of the same comparison
						if(previousResultForOriginal.result.compareTo( cs.result )>0) {
							// we have a better result
							this.childResults.remove( previousResultForOriginal );
							this.childResults.add( cs );
						} else {
							// nothing to do
						}
					} else {
						// we have valid results
						if((previousResultForOriginal.result.compareTo( cs.result )>0) && (previousResultForModified.result.compareTo( cs.result )>0)) {
							// both old comparison are weaker than the current one
							Element oldModified = previousResultForOriginal.result.modified;
							if((oldModified!=null) && (oldModified!=cs.result.modified)) {
								this.childResults.add( new ComparisonStorage( null, oldModified, ComparisonStorage.countChildren( oldModified ) ) );
							}
							this.childResults.remove( previousResultForOriginal );
							Element oldOriginal = previousResultForModified.result.original;
							if((oldOriginal!=null) && (oldOriginal!=cs.result.original)) {
								this.childResults.add( new ComparisonStorage( oldOriginal, null, ComparisonStorage.countChildren( oldOriginal ) ) );
							}
							this.childResults.remove( previousResultForModified );
							this.childResults.add( cs );
							// }else if( previousResultForOriginal.result.compareTo(
							// cs.result )>0 ){                            //we have a best comparison only for
							// the original element                            Element oldModified =
							// previousResultForOriginal.result.modified;                            if(
							// oldModified!=null && oldModified!=cs.result.modified ){
							// this.childResults.add( new ComparisonStorage( null, oldModified,
							// this.countChildren(oldModified) ) );                            }
							// this.childResults.remove( previousResultForOriginal );
							// this.childResults.add( cs );                        }else if(
							// previousResultForModified.result.compareTo( cs.result )>0 ){
							// //we have a best comparison only for the modified element
							// Element oldOriginal = previousResultForModified.result.original;
							// if( oldOriginal!=null && oldOriginal!=cs.result.original ){
							// this.childResults.add( new ComparisonStorage( oldOriginal,
							// null, this.countChildren(oldOriginal) ) );                            }
							// this.childResults.remove( previousResultForModified );
							// this.childResults.add( cs );
						} else {
							// nothing to do
						}
					} // end if
				} // end if-else
			} // end if-else
		} // end if-else
		// re-calculate the total number of errors
		this.result.errors = 0;
		Iterator<ComparisonStorage> it = this.childResults.iterator();
		while(it.hasNext()) {
			ComparisonStorage csTemp = it.next();
			this.result.errors += csTemp.result.errors;
		}
	} // end method addChild

	/**
	 * DOCUMENT ME!
	 *
	 * @param   original  DOCUMENT ME!
	 * @param   modified  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	private int compareAttributes(Element original, Element modified) {
		int errors = 0;
		if(original.getAttributes().size()>0) {
			Iterator origAttrIt = original.getAttributes().iterator();
			while(origAttrIt.hasNext()) {
				Attribute attr = (Attribute)origAttrIt.next();
				if(!((modified.getAttribute( attr.getName() )!=null) && modified.getAttributeValue( attr.getName() ).equals( attr.getValue() ))) {
					errors++;
				}
			}
			if(modified.getAttributes().size()>original.getAttributes().size()) {
				errors += (modified.getAttributes().size() - original.getAttributes().size());
			}
		} else {
			errors = modified.getAttributes().size();
		}
		return errors;
	} // end method compareAttributes

	/**
	 * JavaDoc method comments
	 *
	 * @param   elt       Add comments
	 * @param   original  Add comments
	 *
	 * @return  Add comments
	 */
	private ComparisonStorage getChildFor(Element elt, boolean original) {
		if((this.childResults==null) || (this.childResults.size()==0)) {
			return null;
		} else {
			Iterator<ComparisonStorage> it = this.childResults.iterator();
			while(it.hasNext()) {
				ComparisonStorage cs = it.next();
				if((cs.result.original==elt) && original) {
					return cs;
				} else if((cs.result.modified==elt) && !original) {
					return cs;
				}
			} // end while
			return null;
		} // end if
	} // end method getChildFor
} // end class ComparisonStorage
