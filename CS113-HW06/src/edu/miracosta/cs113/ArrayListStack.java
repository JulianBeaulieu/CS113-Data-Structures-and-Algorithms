/**
 * @author Julian Beaulieu
 * @version 2.0
 *
 * Basic ArrayListStack data structure using a ArrayList
 */
package edu.miracosta.cs113;

import java.util.EmptyStackException;
import java.util.ArrayList;

public class ArrayListStack<E> implements StackInterface<E>
{
  //this will hold the data
  private ArrayList<E> theData;

  //default constructor
  public ArrayListStack()
  {
    theData = new ArrayList<E>();
  }

  /** Returns the item at the top of the stack but does not remove it
   *
   * @return E
   *            the object at the top of the stack
   * @throws EmptyStackException
   *                              if the stack is empty
   */
  public E peek()
  {
    if(theData.size() == 0)
    {
      throw new EmptyStackException();
    }

    return theData.get(theData.size() - 1);
  }

  /** Returns the item at the top of the stack and removes it
   *
   * @return E
   *            the object at the top of the stack
   * @throws EmptyStackException
   *                              if the stack is empty
   */
  public E pop()
  {
    if(theData.size() == 0)
    {
      throw new EmptyStackException();
    }

    return theData.remove(theData.size() - 1);
  }

  /** Adds a object on the top of the stack
   *
   * @param obj
   *            of type E. This will add the object to the top of the stack
   */
  public E push(E obj)
  {
    theData.add(obj);
    return obj;
  }

  /**
   *
   * @return boolean
   *                  true if stack is empty, false if not empty
   */
  public boolean empty()
  {
    return theData.size() == 0;
  }
}
