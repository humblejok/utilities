/**
 * Title           : $Workfile: TreeNode.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 10/08/07 11:38 $
 * By              : $Author: Als $
 * Version number  : $Revision: 1 $
 */

package com.eim.util;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.eim.util.check.GeneralChecker;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
class TreeNode<E> implements Serializable, NodeWrapper<E> {
	/** Use serialVersionUID for interoperability. */
	private static final long serialVersionUID = 4335267123969904281L;

	private List<TreeNode<E>> children;
	private E content;
	private TreeNode<E> parent;

	/**
	 * Creates a new TreeNode object.
	 *
	 * @param  content  DOCUMENT ME!
	 */
	public TreeNode(E content) {
		super();
		this.content = content;
		children = new ArrayList<TreeNode<E>>();
	}

	/**
	 * Creates a new TreeNode object.
	 *
	 * @param  content  DOCUMENT ME!
	 * @param  parent   DOCUMENT ME!
	 */
	public TreeNode(E content, TreeNode<E> parent) {
		this(content);
		setParent(parent);
	}

	/**
	 * @return
	 */
	public final boolean hasChild() {
		return !GeneralChecker.isEmpty(children);
	}

	/**
	 * @return
	 */
	public String toString() {
		return content.toString();
	}

	/**
	 * @return  Returns the children.
	 */
	public final List<TreeNode<E>> getChildren() {
		return children;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public int getDepthPosition() {
		if(parent == null) {
			return 0;
		}

		return 1 + parent.getDepthPosition();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public TreeNode<E> getFirstChild() {
		if(!children.isEmpty()) {
			return children.get(0);
		} else {
			return null;
		}
	} // end method getFirstChild

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public TreeNode<E> getLastChild() {
		if(!children.isEmpty()) {
			return children.get(children.size() - 1);
		} else {
			return null;
		}
	} // end method getLastChild

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public TreeNode<E> getNextSibling() {
		if(isRoot()) {
			return null;
		}

		List<TreeNode<E>> siblings = parent.children;
		for(Iterator<TreeNode<E>> iter = siblings.iterator(); iter.hasNext();) {
			TreeNode<E> theNode = iter.next();
			if(theNode.equals(this)) {
				if(iter.hasNext()) {
					return iter.next();
				} else {
					return null;
				} // end if
			} // end if
		} // end for

		return null;
	} // end method getNextSibling

	/**
	 * @return  Returns the content.
	 */
	public final E getNodeContent() {
		return content;
	}

	/**
	 * @return  Returns the parent.
	 */
	public final TreeNode<E> getParent() {
		return parent;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public TreeNode<E> getPreviousSibling() {
		if(isRoot()) {
			return null;
		}

		List<TreeNode<E>> siblings = parent.children;
		for(ListIterator<TreeNode<E>> iter = siblings.listIterator(); iter.hasNext();) {
			TreeNode<E> theNode = iter.next();
			if(theNode.equals(this)) {
				if(iter.hasPrevious()) {
					return iter.previous();
				} else {
					return null;
				} // end if
			} // end if
		} // end for

		return null;
	} // end method getPreviousSibling

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public final boolean isRoot() {
		return parent == null;
	}

	/**
	 * @param  parent  The parent to set.
	 */
	public final void setParent(TreeNode<E> parent) {
		this.parent = parent;
		if(parent!=null){
			parent.children.add(this);
		}
	}
}
