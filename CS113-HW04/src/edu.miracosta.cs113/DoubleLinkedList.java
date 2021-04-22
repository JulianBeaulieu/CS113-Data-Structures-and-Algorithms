/**
 * @author Julian Beaulieu
 * @version 4.0
 *
 * @see This is a DoubleLinkedList
 * @see You can traverse this List backwards and forwards
 * @see it implements the list interface and has a Iterator which you can use to go through this Linked List
 * @see This Double LinkedList has all the methods, a normal linked list would have
 */
package edu.miracosta.cs113;

import java.util.*;

public class DoubleLinkedList<E> implements List<E>
{
    //this is a node which is used to save data
    private static class Node<E>
    {
        private E data; //saves the data
        private Node<E> next; //reference to the next node
        private Node<E> previous; //reference to the previous node

        /** Creates a new node with a null next field
         @param data  The data stored
         */
        private Node(E data)
        {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        /** Creates a new node with a null next field
         */
        private Node()
        {
            this.data = null;
            this.next = null;
            this.previous = null;
        }

        /** Creates a new node that references another node
         @param data  The data stored
         @param nodeNext  The node referenced by the next node
         @param nodePrevious  The node referenced by previous node
         */
        private Node(E data, Node<E> nodeNext, Node<E> nodePrevious)
        {
            this.data = data;
            this.next = nodeNext;
            this.previous = nodePrevious;
        }
    }

    //this is the iterator for this linked list
    private class LstItr implements ListIterator<E>
    {
        private Node<E> nextItem; //reference to the next node
        private Node<E> lastItemReturned; //reference to the last returned node
        private int index; //keeps track of the last returned node

        /** Default constructor
         * @see LstItr()
         */
        public LstItr()
        {
            nextItem = head;
            lastItemReturned = null;
            index = 0;
        }

        /** Default constructor
         * @see LstItr(int newIndex)
         * @param int the index, to which the iterator should start at
         */
        public LstItr(int newIndex)
        {
            // error check index
            if (newIndex < 0 || newIndex > size)
            {
                throw new IndexOutOfBoundsException("Invalid index " + newIndex);
            }

            lastItemReturned = null; // No item returned yet

            // Special case of last item
            if (newIndex == size)
            {
                this.index = size;
                nextItem = null;
            }
            else if(newIndex == 0)
            {
                nextItem = head;
            }
            else
            {
                // Start at the beginning
                nextItem = head;
                for (index = 0; index < newIndex; index++)
                {
                    nextItem = nextItem.next;
                }
            }
        }

        @Override
        /**
         * @see hasNext()
         * @return boolean true if there is a next node
         */
        public boolean hasNext()
        {
            return nextItem != null;
        }

        @Override
        /**
         * @see next()
         * @return E the data in the next node
         */
        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }

            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        @Override
        /**
         * @see hasPrevious()
         * @return boolean true if there is a previous node
         */
        public boolean hasPrevious()
        {
            try
            {
                return ((nextItem == null && size != 0) || (nextItem.previous != null));
            }
            catch(NullPointerException e)
            {
                return false;
            }
        }

        @Override
        /**
         * @see previous()
         * @return E the data in the previous node
         */
        public E previous()
        {
            if (!hasPrevious())
            {
                throw new NoSuchElementException();
            }

            if (nextItem == null)
            {
                // Iterator past the last element
                nextItem = tail;
            }
            else
            {
                nextItem = nextItem.previous;
            }
            lastItemReturned = nextItem; index--;
            return lastItemReturned.data;
        }

        @Override
        /**
         * @see nextIndex()
         * @return int of the next index the iterator would be at, if you call next() method
         */
        public int nextIndex()
        {
            if(index == size)
            {
                return index;
            }
            else
            {
                return index + 1;
            }
        }

        @Override
        /**
         * @see previousIndex()
         * @return int of the previous index the iterator would be at, if you call previous() method
         */
        public int previousIndex()
        {
            if(index == 0)
            {
                return 0;
            }
            else
            {
                return index - 1;
            }
        }

        @Override
        /**
         * @see remove()
         * @see removes the last node, which the iterator passed over
         */
        public void remove()
        {
            if(head.next != null)
            {
                nextItem = nextItem.next;
            }

            //if - else if - else for different removing conditions
            if(size == 1 && index == 0)
            {
                head = null;
                tail = null;
            }
            else if(nextItem == head.next)
            {
                nextItem.previous = null;
                head = nextItem;
            }
            else if(index == size - 1)
            {
                previous();
                tail = nextItem.previous;
                nextItem.previous.next = null;
            }
            else
            {
                nextItem.previous.previous.next = nextItem;
                nextItem.previous = nextItem.previous.previous;
            }

        }

        @Override
        /**
         * @see set(E e)
         * @param E generic object
         */
        public void set(E e)
        {
            Node<E> temp = new Node<E>(e);

            //if - else if - else for different setting conditions
            if((head.next != null) && (index != size - 1))
            {
                nextItem = nextItem.next;
            }

            if(size == 1 && index == 0)
            {
                head = temp;
                tail = temp;

            }
            else if(nextItem == head.next)
            {
                nextItem.previous = temp;
                temp.next = nextItem;
                head = temp;
            }
            else if(index == size - 1)
            {
                previous();
                temp.previous = nextItem.previous;
                nextItem.previous.next = temp;
                tail = temp;
            }
            else
            {
                temp.previous = nextItem.previous.previous;
                temp.next = nextItem;
                nextItem.previous.previous.next = temp;
                nextItem.previous = temp;
            }
        }

        @Override/**
         * @see add(E e)
         * @param E generic object
         */
        public void add(E e)
        {
            Node<E> temp = new Node(e);

            //if - else if - else for different adding conditions
            if(head == null)
            {
                temp.next = null;
                temp.previous = null;
                head = temp;
                tail = temp;

            }
            else if(nextItem == null)
            {
                temp.next = null;
                temp.previous = tail;
                tail.next = temp;
                tail = temp;

            }
            else if(index == 0)
            {
                temp.next = head;
                temp.previous = null;
                head.previous = temp;
                head = temp;
            }
            else
            {
                temp.previous = nextItem.previous;
                nextItem.previous.next = temp;
                temp.next = nextItem;
                nextItem.previous = temp;
            }

            size++;
        }
    }

    //pointer to head node
    private Node<E> head;

    //pointer to tail node
    private Node<E> tail;

    //keeps track of size of linked list
    private int size;

    //Iterator of the internal iterator
    private LstItr itr;

    /**
     * @see default constructor
     */
    public DoubleLinkedList()
    {
        head = null;
        tail = null;
        itr = new LstItr();
        size = 0;
    }

    @Override
    /**
     * @see iteraor()
     * @return Iterator returns a standard iterator
     */
    public Iterator iterator()
    {
        return new LstItr();
    }

    @Override
    /**
     * @see listIterator()
     * @return LstItr at the position 0
     */
    public LstItr listIterator()
    {
        return new LstItr();
    }

    @Override
    /**
     * @see listIterator(int index)
     * @param int index where the iterator should start at
     * @return LstItr at the position index
     */
    public LstItr listIterator(int index)
    {
        return new LstItr(index);
    }

    @Override
    /**
     * @see size()
     * @return int size of the linkedList
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * @see isEmpty()
     * @return boolean true if LinkedList is empty, false if linked list has nodes
     */
    public boolean isEmpty()
    {
        return this.size == 0;
    }

    @Override
    /**
     * @see contains(Object o)
     * @param Object object
     * @return boolean returns true if it finds this object in the array
     */
    public boolean contains(Object o)
    {
        itr = new LstItr();

        //loops until it finds the object in the linked list
        while(itr.hasNext())
        {
            if(o.equals(itr.next()))
            {
                return true;
            }
        }

      //if it never finds the object that was passed in this method,
      //and the loop ends due to the temp node == the tail node,
      //it will return false, since there is no such object in the linked list
      return false;
    }

    @Override
    /**
     * @see add(E e)
     * @param E a generic object
     * @return boolean, returns true, if e was successfully added
     */
    public boolean add(E e)
    {
        try
        {
            addLast(e);
        }
        catch(Exception ex)
        {
            return false;
        }

        return true;
    }

    @Override //good ++++++++
    /** Add an item at the specified index.
     * @param index The index at which the object is to be inserted @param obj The object to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (i < 0 || i > size())
     */
    public void add(int index, E element)
    {
        listIterator(index).add(element);
    }

    /** Adds to the end of the linkedList
     * @see addLast(E e)
     * @param Object A object
     */
    public void addLast(E e)
    {
        add(size, e);
    }

    /** adds to the beginning of the linkedList
     * @see addFirst(E e)
     * @param Object E a generic object
     */
    public void addFirst(E e)
    {
        add(0, e);
    }

    @Override
    /**
     * @see remove(Object o)
     * @return boolean if object was successfully removed it will return true
     * @param Object A object that should be removed
     */
    public boolean remove(Object o)
    {

        try
        {
            listIterator(indexOf(o)).remove();
        }
        catch(Exception e)
        {
            return false;
        }

        return true;
    }

    @Override
    /**
     * @see remove(int index)
     * @return E returns the removed element
     * @param int the index of the element which should be removed
     */
    public E remove(int index)
    {
        //incase the user mistakenly tries to add to an invalid position
        if(index < 0 || index >= this.size)
        {
            throw new IndexOutOfBoundsException();
        }

        E newData = listIterator(index).next();
        listIterator(index).remove();

        return newData;
    }

    @Override
    /**
     * @see Clears the linked list
     * @see clear()
     */
    public void clear()
    {
        head = null;
        tail = null;

        size = 0;
    }

    @Override
    /**
    * @see Get the element at position index.
    * @param index Position of item to be retrieved @return The item at index
    */
    public E get(int index)
    {
        if(index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        return listIterator(index).next();
    }

    /**
     * @see getFirst()
     * @return E returns the first element of the linked list
     */
    public E getFirst()
    {
        return head.data;
    }

    /**
     * @see getLast()
     * @return E returns the first element of the linked list
     */
    public E getLast()
    {
        return tail.data;
    }

    @Override
    /**
     * @see set(int index, E element)
     * @return E returns the element of the linked list which is in index i
     * @param int index where the object should be placed
     * @param E A generic object
     */
    public E set(int index, E element)
    {
        //incase the user mistakenly tries to add to an invalid position
        if(index < 0 || index >= this.size)
        {
            throw new IndexOutOfBoundsException();
        }

        listIterator(index).set(element);

        return element;
    }

    @Override
    /**
     * @see index(Object o)
     * @return int the index of the object searched for
     * @param Object A object
     */
    public int indexOf(Object o)
    {
        itr = new LstItr();

        //loops until it finds the object in the linked list
        for(int position = 0; itr.hasNext(); position++)
        {
            if(o.equals(itr.next()))
            {
                return position;
            }
        }

        //if it does not find the object it automatically returns -1;
        return -1;
    }

    @Override
    /**
     * @see lastIndexOf(Object o)
     * @return int the index of the last occurrence of the object searched for
     * @param Object A object
     */
    public int lastIndexOf(Object o)
    {
        int lastOccurance = -1;

        itr = new LstItr();

        //loops until it finds the object in the linked list
        for(int position = 0; itr.hasNext(); position++)
        {
            if(itr.next().equals(o))
            {
                lastOccurance = position;
            }
        }

        //if it does not find the object it automatically returns -1;
        return lastOccurance;
    }

    @Override
    /**
     * @see toString()
     * @return String representation of the whole linked list
     */
    public String toString()
    {
        if(size == 0)
        {
            return "[]";
        }

        //string that will save the toStrings of the data objects in the nodes
        String theToStringString = "[";

        //makes a new node object and points it to head
        LstItr newItr = this.listIterator();

        while(newItr.hasNext())
        {
            theToStringString += newItr.next().toString() + ", ";
        }

        return theToStringString.substring(0, theToStringString.length() - 2) + "]";
    }

    @Override
    /**
     * @see equals(Object o)
     * @return boolean returns true if both objects are equal, false if they are not
     * @param Object A object
     */
    public boolean equals(Object o)
    {

        System.out.println("Yo " + (o instanceof DoubleLinkedList));

        if (o == null || !(o instanceof List)) //use instanceof operator
        {
            return false;
        }

        List<E> other = (LinkedList<E>) o;

        if(this.size != other.size())
        {
            return false;
        }

        Node<E> temp = head.next;
        LstItr newItr = (LstItr) other.listIterator();

        while(temp != tail)
        {
            E newData = newItr.next();

            System.out.println(temp.data + " " + newData);

            if(!temp.data.equals(newData))
            {
                return false;
            }

            temp = temp.next;
        }

        return true;
    }





















    /*
    ######################################
    ######################################
            +++++ Not Needed ++++++
    ######################################
    ######################################
     */

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    /**
     * @see toArray()
     * @return Object[] A array which has all of the elements in the node
     */
    public Object[] toArray()
    {
        Object[] tempArr = new Object[size];
        Node<E> temp = head.next;

        for(int i = 0; temp.next != tail; i++)
        {
            tempArr[i] = temp.data;
            temp = temp.next;
        }
        return tempArr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) new Object[0];
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
