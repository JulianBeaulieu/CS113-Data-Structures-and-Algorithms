/**
 * @author Julian Beaulieu
 * @version 2.0
 *
 * Basic Queue data structure using an array
 */
package edu.miracosta.cs113;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
import java.util.Queue;
import java.lang.reflect.ParameterizedType;

public class ArrayQueue<E> implements Queue<E>{

    //inner iterator for the queue
    private class QueueIterator implements Iterator<E> {

        //keeps track of the index and the count
        private int index;
        private int count = 0;

        //default constructor
        public QueueIterator() {
            index = front;
        }

        /**
         *
         * @return true if there is a next object in the array, false if there isn't
         */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {

            if (!hasNext())
            {
                throw new NoSuchElementException();
            }

            //saves the next object
            E returnValue = theData[index];
            index = (index + 1) % capacity;
            count++; //increments the counter
            return returnValue; //returns the object
        }

        /**
         * @throws UnsupportedOperationException since this is not a permitted action
         */
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    //declares the needed variables
    private E[] theData;
    private int capacity;
    private int size;
    private int front;
    private int rear;

    /**
     * Default constructor
     * @see inititalizes the size of the inner array to 10
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        capacity = 10;
        theData = (E[])new Object[capacity]; //array of generics
        front = 0;
        rear = capacity - 1; //sets the rear to the last position in the array
        size = 0;
    }

    /**
     * 2nd Constructor which lets user set the size of the inner array
     * @param initCapacity capacity of the inner array
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initCapacity) {
        capacity = initCapacity;
        theData = (E[])new Object[capacity]; //array of generics
        front = 0;
        rear = capacity - 1; //sets the rear to the last position in the array
        size = 0;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param o the element to add
     * @return {@code true} if the element was added to this queue, else
     * {@code false}
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    @Override
    public boolean offer(E o) {

        //these two lines check if the entered object is legal
        //if not it will throw an exception
        if(o == null)
        {
            throw new NullPointerException();
        }

        //tries to add a element to the queue, if it fails it will return false
        try
        {
            //incase the array has reached capacity, then it will call rellocate method, which grows and unwinds array
            if (size == capacity)
            {
                reallocate();
            }
            //increments the size of the array, adds the new element to the end of the array, and returns true
            //since the element was successfully added
            size++;
            rear = (rear + 1) % capacity;
            theData[rear] = o;
            return true;
        }
        catch(IllegalArgumentException e)
        {
            return false;
        }
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public E remove() {

        //throws exception if the queue is empty
        if(size == 0)
        {
            throw new NoSuchElementException();
        }
        else
        {
            //decrements the size, increments the front which "deletes" the object and then returns the data
            front = (front + 1) % capacity;
            size--;

            return theData[front - 1];
        }
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public E poll() {

        //returns null if the queue is empty
        if (size == 0)
        {
            return null;
        }
        else
        {
            //decrements the size, increments the front which "deletes" the object and then returns the data
            front = (front + 1) % capacity;
            size--;

            return theData[front - 1];
        }
    }

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public E element() {

        //throws exception if the queue is empty
        if(size == 0)
        {
            throw new NoSuchElementException();
        }
        else //else returns the first object, without deleting it
        {
            return theData[front];
        }
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public E peek() {

        //returns null if the queue is empty
        if(size == 0)
        {
            return null;
        }
        else //else returns the first object, without deleting it
        {
            return theData[front];
        }
    }

    /**
     ***************************************************************************************
     ******************************* Helper Methods ****************************************
     ***************************************************************************************
     **/

    /**
     * This helper method unwinds and grows the array
     */
    @SuppressWarnings("unchecked")
    private void reallocate() {

        //this makes the new array capacity double the size of the old one
        int newCapacity = 2 * capacity;

        //then it makes the new array
        E[] newData = (E[])new Object[newCapacity]; int j = front;

        //this copies the old array into the new array
        for (int i = 0; i < size; i++)
        {
            newData[i] = theData[j];
            j = (j + 1) % capacity;
        }

        //resets the front / size / capacity
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
        theData = newData; //makes the newly made array the new array which deletes the refference to the old array
    }


    /**
    ***************************************************************************************
    ******************************* Not Needed ********************************************
    ***************************************************************************************
    **/




    @Override
    public int size() {
        return 0;
        //return size; //uncomment for debugging
    }

    public int capacity() {
        return 0;
        //return capacity; //uncomment for debugging
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        return (E[])new Object[0];
        //return theData; //uncomment for debugging
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(Object[] a) {
        return (E[]) new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

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
    public boolean containsAll(Collection c) {
        return false;
    }
}
