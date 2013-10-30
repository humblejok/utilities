/**
 * Title           : $Workfile: Tree.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 11:38 $
 * By              : $Author: Als $
 * Version number  : $Revision: 12 $
 *
 * $History: Tree.java $
 * 
 * *****************  Version 12  *****************
 * User: Als          Date: 10/08/07   Time: 11:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring for Java 5
 * 
 * *****************  Version 11  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:37
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * 
 * *****************  Version 10  *****************
 * User: Als          Date: 9.01.06    Time: 14:09
 * Updated in $/Current/Projects/utilities/src/com/eim/util
 * Refactoring
 */
package com.eim.util;

import com.eim.util.check.GeneralChecker;

import java.io.Serializable;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("unchecked")
public class Tree<E> implements Serializable {

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -1985438025860473454L;
	private Hashtable<E, TreeNode<E>> data;
	private TreeNode<E>  root;

	//~ Constructors -----------------------------------------------------------

	/**
	 */
	public Tree() {
		super();
		data = new Hashtable<E, TreeNode<E>>();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param   parent
	 * @param   node
	 *
	 * @return
	 */
	public NodeWrapper<E> add(E parent, E node) {
		if(parent==null) {
			return setRoot( node );
		}
		TreeNode<E> parentNode = data.get( parent );
		if(parentNode==null) {
			return null;
		}
		TreeNode<E> treeNode = new TreeNode<E>( node, parentNode );
		data.put( node, treeNode );
		return treeNode;
	} // end method add

	/**
	 * @param   node
	 *
	 * @return
	 */
	public Iterator<E> childrenIterator(Object node) {
		if((node==null) || !data.containsKey( node )) {
			return new EmptyIterator<E>();
		}
		return new ChildIterator<E>( this, data.get( node ) );
	}

	/**
	 */
	public void clear() {
		root = null;
		data.clear();
	}

	/**
	 * @param   node
	 *
	 * @return
	 */
	public Tree<E> extractTree(E node) {
		if(data.containsKey( node )) {
			TreeNode<E> startNode  = data.get( node );
			int		 depthLimit = startNode.getDepthPosition();
			Tree<E>     newTree    = new Tree<E>();
			Iterator<TreeNode<E>> iterator   = new TreeIterator<E>( this );
			boolean  startFound = false;
			while(iterator.hasNext()) {
				TreeNode<E> aNode = iterator.next();
				if(!startFound) {
					if(aNode==startNode) {
						startFound = true;
						newTree.setRoot( node );
					}
				} else {
					if(aNode.getDepthPosition()>depthLimit) {
						newTree.add( aNode.getParent().getNodeContent(), aNode.getNodeContent() );
					}
				}
			} // end while
			return newTree;
		} // end if
		return null;
	} // end method extractTree

	/**
	 * @param   node
	 *
	 * @return
	 */
	public boolean hasChild(Object node) {
		if(data.containsKey( node )) {
			return ((TreeNode)data.get( node )).hasChild();
		}
		return false;
	}

	/**
	 * @param   node
	 *
	 * @return
	 */
	public boolean hasNextSibling(Object node) {
		if(data.containsKey( node )) {
			return ((TreeNode)data.get( node )).getNextSibling()!=null;
		}
		return false;
	}

	/**
	 * @param   node
	 *
	 * @return
	 */
	public boolean hasParent(Object node) {
		if(data.containsKey( node )) {
			return ((TreeNode)data.get( node )).getParent()!=null;
		}
		return false;
	}

	/**
	 * @param   node
	 *
	 * @return
	 */
	public boolean hasPreviousSibling(Object node) {
		if(data.containsKey( node )) {
			return ((TreeNode)data.get( node )).getPreviousSibling()!=null;
		}
		return false;
	}

	/**
	 * @return
	 */
	public Iterator<E> iterator() {
		return new PublicTreeIterator<E>( this );
	}

	/**
	 * @param   junctionNode
	 * @param   subTree
	 *
	 * @return
	 */
	public Tree<E> joinTree(E junctionNode, Tree<E> subTree) {
		if(data.containsKey( junctionNode )) {
			boolean  first    = true;
			Iterator<TreeNode<E>> iterator = new TreeIterator<E>( subTree );
			while(iterator.hasNext()) {
				TreeNode<E> node = iterator.next();
				if(first) {
					this.add( junctionNode, node.getNodeContent() );
					first = false;
				} else {
					this.add( node.getParent().getNodeContent(), node.getNodeContent() );
				}
			} // end while
		} // end if
		return this;
	} // end method joinTree

	/**
	 * @param   node
	 *
	 * @return
	 */
	public Iterator<E> siblingIterator(E node) {
		return new SiblingIterator<E>( data.get( node ) );
	}

	/**
	 * @return
	 */
	public int size() {
		int size = 0;
		for(Iterator iter = iterator(); iter.hasNext();) {
			size++;
			iter.next();
		}
		return size;
	} // end method size

	/**
	 * @return
	 */
	public String toString() {
		StringBuffer buffer   = new StringBuffer();
		Iterator<TreeNode<E>>     iterator = new TreeIterator<E>( this );
		while(iterator.hasNext()) {
			TreeNode<E> node = iterator.next();
			for(int i = 0; i<node.getDepthPosition(); i++) {
				buffer.append( "-" );
			}
			buffer.append( node.toString() );
			buffer.append( "\r\n" );
		} // end while
		return buffer.toString();
	} // end method toString

	/**
	 * @param   node
	 *
	 * @return
	 */
	public Tree<E> truncate(E node) {
		if(data.containsKey( node )) {
			TreeNode<E> theNode = data.get( node );
			if(theNode==root) {
				clear();
			} else {
				Tree<E> treeToEliminate = extractTree( node );
				theNode.getParent().getChildren().remove( theNode );
				theNode.setParent(null);
				for(Iterator<E> iter = treeToEliminate.iterator(); iter.hasNext();) {
					data.remove( iter.next() );
				}
			} // end if
		} // end if
		return this;
	} // end method truncate

	/**
	 * @param   wrapper
	 *
	 * @return
	 */
	public <V> Tree<V> wrapTree(Wrapper<E,V> wrapper) {
		TreeNode<E>  startNode		 = root;
		int		  depthLimit     = startNode.getDepthPosition();
		Hashtable<E,V> wrappedObjects = new Hashtable<E, V>();
		Tree<V>	  newTree		 = new Tree<V>();
		Iterator<TreeNode<E>>  iterator		 = new TreeIterator<E>( this );
		boolean   startFound     = false;
		while(iterator.hasNext()) {
			TreeNode<E> aNode = iterator.next();
			if(!startFound) {
				if(aNode==startNode) {
					startFound = true;
					V wrappedObject = wrapper.wrap( root.getNodeContent() );
					wrappedObjects.put( root.getNodeContent(), wrappedObject );
					newTree.setRoot( wrappedObject );
				}
			} else {
				if(aNode.getDepthPosition()>depthLimit) {
					V wrappedObject = wrapper.wrap( aNode.getNodeContent() );
					wrappedObjects.put( aNode.getNodeContent(), wrappedObject );
					newTree.add( wrappedObjects.get( aNode.getParent().getNodeContent() ), wrappedObject );
				}
			} // end if
		} // end while
		return newTree;
	} // end method wrapTree

	public Set getAllNodeValues() {
		return data.keySet();
	}

	public Collection getChildren(Object node) {
		if(data.containsKey( node )) {
			TreeNode treeNode = (TreeNode)data.get( node );
			return treeNode.getChildren();
		} else {
			return null;
		}
	} // end method getChildren

	public int getDepthPosition(Object node) {
		if(!data.containsKey( node )) {
			return -1;
		}
		return ((TreeNode)data.get( node )).getDepthPosition();
	}

	public Object getParent(Object node) {
		if(data.containsKey( node )) {
			TreeNode treeNode = (TreeNode)data.get( node );
			if(treeNode.isRoot()) {
				return null;
			} else {
				return treeNode.getParent().getNodeContent();
			}
		} else {
			return null;
		}
	} // end method getParent

	/**
	 * @return  Returns the root.
	 */
	public final E getRoot() {
		return root.getNodeContent();
	}

	public int getTotalDepth() {
		int depth = 0;
		for(Iterator iter = iterator(); iter.hasNext();) {
			TreeNode node = (TreeNode)iter.next();
			depth = Math.max( depth, node.getDepthPosition() );
		}
		return depth + 1;
	} // end method getTotalDepth

	public final boolean isEmpty() {
		return data.isEmpty();
	}

	/**
	 * @param   newRoot  The root to set.
	 *
	 * @return
	 */
	public final NodeWrapper<E> setRoot(E newRoot) {
		this.root = new TreeNode<E>( newRoot );
		data.put( newRoot, this.root );
		return this.root;
	}

	//~ Inner Classes ----------------------------------------------------------

	/**
	 */
	protected class ChildIterator<T>
		implements Iterator<T>
	{

		//~ Instance fields ----------------------------------------------------

		private TreeNode<T> cursor;
		private boolean  hasChild;
		private TreeNode<T> startNode;

		//~ Constructors -------------------------------------------------------

		/**
		 * Creates a new SiblingIterator object.
		 *
		 * @param  tree  DOCUMENT ME!
		 * @param  node  DOCUMENT ME!
		 */
		protected ChildIterator(Tree<T> tree, TreeNode<T> node) {
			hasChild  = !GeneralChecker.isEmpty( tree.getChildren( node.getNodeContent() ) );
			startNode = node;
		}

		//~ Methods ------------------------------------------------------------

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			if(!hasChild) {
				return false;
			}
			if(cursor==null) {
				return true;
			}
			return cursor.getNextSibling()!=null;
		} // end method hasNext

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#next()
		 */
		public T next() {
			if(cursor==null) {
				return (cursor = startNode.getFirstChild()).getNodeContent();
			}
			return (cursor = cursor.getNextSibling()).getNodeContent();
		}

		/**
		 * @see  java.util.Iterator#remove()
		 */
		public void remove() {
		}
	} // end class ChildIterator

	/**
	 */
	protected class EmptyIterator<T>
		implements Iterator<T>
	{

		//~ Constructors -------------------------------------------------------

		/**
		 * Creates a new EmptyIterator object.
		 */
		protected EmptyIterator() {
			super();
		}

		//~ Methods ------------------------------------------------------------

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return false;
		}

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#next()
		 */
		public T next() {
			return null;
		}

		/**
		 * @see  java.util.Iterator#remove()
		 */
		public void remove() {
		}
	} // end class EmptyIterator

	/**
	 */
	protected class PublicTreeIterator<T>
		implements Iterator<T>
	{
		//~ Instance fields ----------------------------------------------------

		private TreeNode<T> cursor;
		private Tree<T>     tree;

		//~ Constructors -------------------------------------------------------

		/**
		 * Creates a new TreeIterator object.
		 *
		 * @param  tree  DOCUMENT ME!
		 */
		protected PublicTreeIterator(Tree<T> tree) {
			super();
			this.tree = tree;
			cursor    = null;
		}

		//~ Methods ------------------------------------------------------------

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			if(cursor==null) {
				return !tree.isEmpty();
			}
			if(!cursor.getChildren().isEmpty()) {
				return true;
			}
			if(cursor.getNextSibling()!=null) {
				return true;
			}
			if(cursor.isRoot()) {
				return false;
			}
			TreeNode<T> aParentNode = cursor;
			while(!aParentNode.isRoot()) {
				if(aParentNode.getNextSibling()!=null) {
					return true;
				} else {
					aParentNode = aParentNode.getParent();
				}
			} // end while
			return false;
		} // end method hasNext

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#next()
		 */
		public T next() {
			if(cursor==null) {
				cursor = this.tree.root;
				return cursor.getNodeContent();
			}
			if(!cursor.getChildren().isEmpty()) {
				cursor = cursor.getFirstChild();
				return cursor.getNodeContent();
			}
			if(cursor.getNextSibling()!=null) {
				cursor = cursor.getNextSibling();
				return cursor.getNodeContent();
			}
			TreeNode<T> aParentNode = cursor;
			while(!aParentNode.isRoot()) {
				if(aParentNode.getNextSibling()!=null) {
					cursor = aParentNode.getNextSibling();
					return cursor.getNodeContent();
				} else {
					aParentNode = aParentNode.getParent();
				}
			} // end while
			return null;
		} // end method next

		/**
		 * @see  java.util.Iterator#remove()
		 */
		public void remove() {
		}	} // end class PublicTreeIterator

	/**
	 */
	protected class SiblingIterator<T>
		implements Iterator<T>
	{

		//~ Instance fields ----------------------------------------------------

		private TreeNode<T> cursor;
		private TreeNode<T> startNode;

		//~ Constructors -------------------------------------------------------

		/**
		 * Creates a new SiblingIterator object.
		 *
		 * @param  node  DOCUMENT ME!
		 */
		protected SiblingIterator(TreeNode<T> node) {
			startNode = node;
		}

		//~ Methods ------------------------------------------------------------

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			if(cursor==null) {
				return true;
			}
			return cursor.getNextSibling()!=null;
		}

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#next()
		 */
		public T next() {
			if(cursor==null) {
				cursor = startNode;
			}
			return (cursor = cursor.getNextSibling()).getNodeContent();
		}

		/**
		 * @see  java.util.Iterator#remove()
		 */
		public void remove() {
		}
	} // end class SiblingIterator

	/**
	 */
	protected class TreeIterator<T>
		implements Iterator<TreeNode<T>>
	{

		//~ Instance fields ----------------------------------------------------

		private TreeNode<T> cursor;
		private Tree<T>     tree;

		//~ Constructors -------------------------------------------------------

		/**
		 * Creates a new TreeIterator object.
		 *
		 * @param  tree  DOCUMENT ME!
		 */
		protected TreeIterator(Tree<T> tree) {
			super();
			this.tree = tree;
			cursor    = null;
		}

		//~ Methods ------------------------------------------------------------

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			if(cursor==null) {
				return !tree.isEmpty();
			}
			if(!cursor.getChildren().isEmpty()) {
				return true;
			}
			if(cursor.getNextSibling()!=null) {
				return true;
			}
			if(cursor.isRoot()) {
				return false;
			}
			TreeNode<T> aParentNode = cursor;
			while(!aParentNode.isRoot()) {
				if(aParentNode.getNextSibling()!=null) {
					return true;
				} else {
					aParentNode = aParentNode.getParent();
				}
			} // end while
			return false;
		} // end method hasNext

		/**
		 * @return
		 *
		 * @see     java.util.Iterator#next()
		 */
		public TreeNode<T> next() {
			if(cursor==null) {
				return cursor = this.tree.root;
			}
			if(!cursor.getChildren().isEmpty()) {
				cursor = cursor.getFirstChild();
				return cursor;
			}
			if(cursor.getNextSibling()!=null) {
				cursor = cursor.getNextSibling();
				return cursor;
			}
			TreeNode<T> aParentNode = cursor;
			while(!aParentNode.isRoot()) {
				if(aParentNode.getNextSibling()!=null) {
					cursor = aParentNode.getNextSibling();
					return cursor;
				} else {
					aParentNode = aParentNode.getParent();
				}
			} // end while
			return null;
		} // end method next

		/**
		 * @see  java.util.Iterator#remove()
		 */
		public void remove() {
		}
	} // end class TreeIterator

} // end class Tree
