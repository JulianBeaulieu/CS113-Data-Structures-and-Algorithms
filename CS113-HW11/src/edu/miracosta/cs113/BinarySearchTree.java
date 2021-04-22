/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @filename BinarySearchTree
 * @HW 11
 * @Professor Nery Chapeton-Lamas
 */
package edu.miracosta.cs113;

public class BinarySearchTree<E extends Comparable> extends BinaryTree implements SearchTree
{
  public BinarySearchTree()
  {
    root = null;
    addReturn = false;
  }

  /*##################################################*/
  /*################## Fields  #######################*/
  /*##################################################*/

  protected boolean addReturn;
  protected E deleteReturn;

  /*##################################################*/
  /*################## Methods  ######################*/
  /*##################################################*/

  /*################## Add Methods  ##################*/

  /** Starter method add.
    pre: The object to insert must implement the
    Comparable interface.
    @param item The object being inserted
    @return true if the object is inserted, false
           if the object already exists in the tree
    */
  public boolean add(Object item)
  {
    root = add(root, (E) item);
    return addReturn;
  }

  /** Recursive add method.
        post: The data field addReturn is set true if the item is added to
          the tree, false if the item is already in the tree.
        @param localRoot The local root of the subtree
        @param item The object to be inserted
        @return The new local root that now contains the inserted item
    */
  private Node<E> add(Node<E> localRoot, E item)
  {
    if (localRoot == null)
    {
      // item is not in the tree — insert it. addReturn = true;
      return new Node<E>(item);
    }
    else if (item.compareTo(localRoot.data) == 0)
    {
      // item is equal to localRoot.data
      addReturn = false;
      return localRoot;
    }
    else if (item.compareTo(localRoot.data) < 0)
    {
      // item is less than localRoot.data
      localRoot.left = add(localRoot.left, item);
      return localRoot;
    }
    else
    {
      // item is greater than localRoot.data
      localRoot.right = add(localRoot.right, item);
      return localRoot;
    }
  }

  /*################## Find Methods  #################*/

  /** Contains method, which searches for the desired item
   * @param item which should be searched for
   * @return true if item was found, false if it was not found
   */
  public boolean contains(Object item)
  {
    if(find((E) item) == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  /** Starter method find.
      pre: The target object must implement
              the Comparable interface.
      @return The object, if found, otherwise null
      @param target The Comparable object being sought
    */
  public E find(Object target)
  {
    return (E) find(root, (E) target);
  }

  /** Recursive find method
        @param localRoot The local subtree's root
        @param target The object being sought
        @return The object, if found, otherwise null
    */
  private E find(Node<E> localRoot, E target)
  {
    if(localRoot == null)
    {
      return null;
    }

    //Compare the target with the data field at the root
    int compResult = target.compareTo(localRoot.data);
    if(compResult == 0)
    {
      return localRoot.data;
    }
    else if(compResult < 0)
    {
      return find(localRoot.left, target);
    }
    else
    {
      return find(localRoot.right, target);
    }
  }

  /*################## Delete Methods  ###############*/

  /** Starter method remove, uses delete
        post: The object is not in the tree.
        @param target The objecz to be deleted
        @return true if target was removed, false if it was not removed
        @throws ClassCastException if target does not implement Comparable
    */
  public boolean remove(Object target)
  {
    root = delete(root, (E) target);

    if(deleteReturn == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }


  /** Starter method delete
        post: The object is not in the tree.
        @param target The objecz to be deleted
        @return The object deleted from the tree
                or null of the object was not in the tree
        @throws ClassCastException if target does not implement Comparable
    */
  public E delete(Object target)
  {
    root = delete(root, (E) target);
    return deleteReturn;
  }

  /** Recursive delete method.
        post: The item is not in the tree;
              delete Return is equal to the deleted item
              as it was stored in the tree or null
              if the item was not found.
        @param localRoot The root of the current subtree
        @param item The item to be deleted
        @return The modified local root that does not contain the item
    */
  private Node<E> delete(Node<E> localRoot, E item)
  {
    if(localRoot == null)
    {
      //item is not in the tree.
      deleteReturn = null;
      return localRoot;
    }

    // Search for item to delete.
    int compResult = item.compareTo(localRoot.data);
    if(compResult < 0)
    {
      //item is smaller than localRoot.data
      localRoot.left = delete(localRoot.left, item);
      return localRoot;
    }
    else if(compResult > 0)
    {
      //item is larger than localRoot.data
      localRoot.right = delete(localRoot.right, item);
      return localRoot;
    }
    else
    {
      //item is at local root
      deleteReturn = localRoot.data;
      if(localRoot.left == null)
      {
        //If there is no left child, return right child
        //which can also be null
        return localRoot.right;
      }
      else if(localRoot.right == null)
      {
        //there is no right child, return left child
        return localRoot.left;
      }
      else
      {
        //Node being deleted has 2 children, replace the data
        //with inorder predecessor
        if(localRoot.left.right == null)
        {
          //The left child has no right child.
          //Replace the data with the data in the left child
          localRoot.data = localRoot.left.data;
          //Replace the left child with its left child
          localRoot.left = localRoot.left.left;
          return localRoot;
        }
        else
        {
          //Search for the onorder predecessor (ip) and
          //replace deleted node's data with ip
          localRoot.data = findLargestChild(localRoot.left);
          return localRoot;
        }
      }
    }
  }

  /** Find the node that is the inorder and predecessor
        and replace with its left child (if any).
        post: its left child (if any)
        @param parent The parent of possible inorder predecessor(ip)
        @return The data in the ip
    */
  private E findLargestChild(Node<E> parent)
  {
    //If the right child has no right child, it is
    //the inorder predecessor(ip)
    if(parent.right.right == null)
    {
      E returnValue = parent.right.data;
      parent.right = parent.right.left;
      return returnValue;
    }
    else
    {
      return findLargestChild(parent.right);
    }
  }
}
