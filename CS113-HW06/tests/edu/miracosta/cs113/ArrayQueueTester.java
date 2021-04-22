/**
 * @author Julian Beaulieu
 * @version 2.0
 *
 * Basic ArrayQueue JUnit tester class
 */
package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayQueueTester {

  private static final String[] STRING_VALUES = {"first", "second", "third", "fourth" , "fifth", "sixth",
                                                    "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};
  private static final char[] CHAR_VALUES = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
  private static final int[] INT_VALUES = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200};
  private static final double[] DOUBLE_VALUES = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.00, 11.11, 12.12};

  private static final String TO_STRING_EMPTY = "";
  private static final String[] TO_STRING_ADD1 = { "first", "100", "A","1.1"};

  private ArrayQueue<String> stringArrayQueue;
  private ArrayQueue<Character> characterArrayQueue;
  private ArrayQueue<Integer> integerArrayQueue;
  private ArrayQueue<Double> doubleArrayQueue;

  @Before
  public void setUp() {
    stringArrayQueue = new ArrayQueue<String>();
    characterArrayQueue = new ArrayQueue<Character>();
    integerArrayQueue = new ArrayQueue<Integer>();
    doubleArrayQueue = new ArrayQueue<Double>();
  }

  /* HELPER METHODS */
  private void buildLists() {
    for (int i = 0; i < 5; i++) {
      stringArrayQueue.offer(STRING_VALUES[i]);
      integerArrayQueue.offer(INT_VALUES[i]);
      characterArrayQueue.offer(CHAR_VALUES[i]);
      doubleArrayQueue.offer(DOUBLE_VALUES[i]);
    }
  }

  /* HELPER METHODS */
  private void expandLists() {
    for (int i = 5; i < 12; i++) {
      stringArrayQueue.offer(STRING_VALUES[i]);
      integerArrayQueue.offer(INT_VALUES[i]);
      characterArrayQueue.offer(CHAR_VALUES[i]);
      doubleArrayQueue.offer(DOUBLE_VALUES[i]);
    }
  }

  @Test
  public void testOfferSimple() {
    buildLists();

    assertEquals("Calling the method peek() should return first", STRING_VALUES[0], stringArrayQueue.peek());
    assertEquals("Calling the method peek() should return 'A'", (Object)CHAR_VALUES[0], characterArrayQueue.peek());
    assertEquals("Calling the method peek() should return 100", (Object)INT_VALUES[0], (Object)integerArrayQueue.peek());
    assertEquals("Calling the method peek() should return 1.1", (Object)DOUBLE_VALUES[0], (Object)doubleArrayQueue.peek());
  }

  @Test
  public void testOfferComplex() {
    buildLists();
    expandLists();

    for(int i = 0; i < 12; i++)
    {
      assertEquals("Calling the method peek() should return first", STRING_VALUES[i], stringArrayQueue.poll());
      assertEquals("Calling the method peek() should return 'A'", (Object)CHAR_VALUES[i], characterArrayQueue.poll());
      assertEquals("Calling the method peek() should return 100", (Object)INT_VALUES[i], (Object)integerArrayQueue.poll());
      assertEquals("Calling the method peek() should return 1.1", (Object)DOUBLE_VALUES[i], (Object)doubleArrayQueue.poll());
    }
  }

  @Test
  public void specialOfferHardCoreTest()
  {
    for(int i = 0; i < 10000; i++)
    {
      integerArrayQueue.offer(new Integer(i));
    }

    for(int i = 0; i < 10000; i++)
    {
      assertEquals("Calling the method peek() should return first", (Object) new Integer(i), (Object)integerArrayQueue.poll());
    }
  }


  @Test
  public void testRemove() {
    buildLists();

    assertEquals("Calling the method remove() should return first", STRING_VALUES[0], stringArrayQueue.remove());
    assertEquals("Calling the method remove() should return 'A'", (Object)CHAR_VALUES[0], characterArrayQueue.remove());
    assertEquals("Calling the method remove() should return 100", (Object)INT_VALUES[0], (Object)integerArrayQueue.remove());
    assertEquals("Calling the method remove() should return 1.1", (Object)DOUBLE_VALUES[0], (Object)doubleArrayQueue.remove());
  }

  @Test
  public void testRemoveEmpty()
  {
    boolean stringArrayQueueBoolean = false;
    boolean characterArrayQueueBoolean =false;
    boolean  integerArrayQueueBoolean = false;
    boolean doubleArrayQueueBoolean = false;

    //try catch for stringArrayQueue.element() method, which will throw a NoSuchElementException
    try
    {
      stringArrayQueue.remove();
    }
    catch(NoSuchElementException e)
    {
      stringArrayQueueBoolean = true;
    }

    //try catch for charArrayQueue.element() method, which will throw a NoSuchElementException
    try
    {
      characterArrayQueue.remove();
    }
    catch(NoSuchElementException e)
    {
      characterArrayQueueBoolean = true;
    }

    //try catch for intArrayQueue.element() method, which will throw a NoSuchElementException
    try
    {
      integerArrayQueue.remove();
    }
    catch(NoSuchElementException e)
    {
      integerArrayQueueBoolean = true;
    }

    //try catch for doubleArrayQueue.element() method, which will throw a NoSuchElementException
    try
    {
      doubleArrayQueue.remove();
    }
    catch(NoSuchElementException e)
    {
      doubleArrayQueueBoolean = true;
    }

    assertEquals("Calling the method remove() should throw a NoSuchElementException, which will turn the boolean to true", true, stringArrayQueueBoolean);
    assertEquals("Calling the method remove() should throw a NoSuchElementException, which will turn the boolean to true", true, characterArrayQueueBoolean);
    assertEquals("Calling the method remove() should throw a NoSuchElementException, which will turn the boolean to true", true, integerArrayQueueBoolean);
    assertEquals("Calling the method remove() should throw a NoSuchElementException, which will turn the boolean to true", true, doubleArrayQueueBoolean);
  }

  @Test
  public void testPoll() {
      buildLists();

      assertEquals("Calling the method poll() should return first", STRING_VALUES[0], stringArrayQueue.poll());
      assertEquals("Calling the method poll() should return 'A'", (Object)CHAR_VALUES[0], characterArrayQueue.poll());
      assertEquals("Calling the method poll() should return 100", (Object)INT_VALUES[0], (Object)integerArrayQueue.poll());
      assertEquals("Calling the method poll() should return 1.1", (Object)DOUBLE_VALUES[0], (Object)doubleArrayQueue.poll());
  }

  @Test
  public void testPollEmpty()
  {
    assertNull("Calling the method poll() on an empty ArrayQueue should return null", stringArrayQueue.poll());
    assertNull("Calling the method poll() on an empty ArrayQueue should return null", characterArrayQueue.poll());
    assertNull("Calling the method poll() on an empty ArrayQueue should return null", integerArrayQueue.poll());
    assertNull("Calling the method poll() on an empty ArrayQueue should return null", doubleArrayQueue.poll());
  }

  @Test
  public void testElement() {
    buildLists();

    assertEquals("Calling the method element() should return first", STRING_VALUES[0], stringArrayQueue.element());
    assertEquals("Calling the method element() should return 'A'", (Object)CHAR_VALUES[0], characterArrayQueue.element());
    assertEquals("Calling the method element() should return 100", (Object)INT_VALUES[0], (Object)integerArrayQueue.element());
    assertEquals("Calling the method element() should return 1.1", (Object)DOUBLE_VALUES[0], (Object)doubleArrayQueue.element());
  }

  @Test
  public void testElementEmpty()
  {
    boolean stringArrayQueueBoolean = false;
    boolean characterArrayQueueBoolean =false;
    boolean  integerArrayQueueBoolean = false;
    boolean doubleArrayQueueBoolean = false;

    //try catch for stringArrayQueue.element() method, which will throw a NoSuchElementException
    try
    {
      stringArrayQueue.element();
    }
    catch(NoSuchElementException e)
    {
      stringArrayQueueBoolean = true;
    }

    //try catch for charArrayQueue.element() method, which will throw a NoSuchElementException
    try
    {
      characterArrayQueue.element();
    }
    catch(NoSuchElementException e)
    {
      characterArrayQueueBoolean = true;
    }

    //try catch for intArrayQueue.element() method, which will throw a NoSuchElementException
    try
    {
      integerArrayQueue.element();
    }
    catch(NoSuchElementException e)
    {
      integerArrayQueueBoolean = true;
    }

    //try catch for doubleArrayQueue.element() method, which will throw a NoSuchElementException
    try
    {
      doubleArrayQueue.element();
    }
    catch(NoSuchElementException e)
    {
      doubleArrayQueueBoolean = true;
    }

    assertEquals("Calling the method element() should throw a NoSuchElementException, which will turn the boolean to true", true, stringArrayQueueBoolean);
    assertEquals("Calling the method element() should throw a NoSuchElementException, which will turn the boolean to true", true, characterArrayQueueBoolean);
    assertEquals("Calling the method element() should throw a NoSuchElementException, which will turn the boolean to true", true, integerArrayQueueBoolean);
    assertEquals("Calling the method element() should throw a NoSuchElementException, which will turn the boolean to true", true, doubleArrayQueueBoolean);
  }

  @Test
  public void testPeek() {
    buildLists();

    assertEquals("Calling the method peek() should return first", STRING_VALUES[0], stringArrayQueue.peek());
    assertEquals("Calling the method peek() should return 'A'", (Object)CHAR_VALUES[0], characterArrayQueue.peek());
    assertEquals("Calling the method peek() should return 100", (Object)INT_VALUES[0], (Object)integerArrayQueue.peek());
    assertEquals("Calling the method peek() should return 1.1", (Object)DOUBLE_VALUES[0], (Object)doubleArrayQueue.peek());
  }

  @Test
  public void testPeekEmpty()
  {
    assertNull("Calling the method peek() on an empty ArrayQueue should return null", stringArrayQueue.peek());
    assertNull("Calling the method peek() on an empty ArrayQueue should return null", characterArrayQueue.peek());
    assertNull("Calling the method peek() on an empty ArrayQueue should return null", integerArrayQueue.peek());
    assertNull("Calling the method peek() on an empty ArrayQueue should return null", doubleArrayQueue.peek());
  }
}
