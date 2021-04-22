/**
 * @author Julian Beaulieu
 * @version 2.0
 * @see JUnit tests for the Iterator class inside of the DoubleLinkedList class
 */

package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

import static org.junit.Assert.*;

public class DoubleLinkedListIteratorTest
{
    private static final String[] STRING_VALUES = {"first", "second", "third", "fourth" , "fifth"};
    private static final int[] INT_VALUES = {100, 200, 300, 400, 500};
    private static final char[] CHAR_VALUES = {'A', 'B', 'C', 'D', 'E'};
    private static final double[] DOUBLE_VALUES = {1.1, 2.2, 3.3, 4.4, 5.5};

    private static final String TO_STRING_EMPTY = "[]";
    private static final String[] TO_STRING_ADD1 = { "[first]",
            "[100]",
            "[A]",
            "[1.1]"};
    private static final String[] TO_STRING_ADD3 = { "[first, second, third]",
            "[100, 200, 300]",
            "[A, B, C]",
            "[1.1, 2.2, 3.3]"};

    private static final String STRING_INSERT_VAL = "w00t";
    private static final int INT_INSERT_VAL = 555;
    private static final char CHAR_INSERT_VAL = 'X';
    private static final double DOUBLE_INSERT_VAL = -3.14;

    private static final int STRING_INSERT_INDEX = 0, INT_INSERT_INDEX = 1, CHAR_INSERT_INDEX = 2, DOUBLE_INSERT_INDEX = 3;
    private static final String[] TO_STRING_INSERT = { "[w00t, first, second, third]",
            "[100, 555, 200, 300]",
            "[A, B, X, C]",
            "[1.1, 2.2, 3.3, -3.14]"};


    private List<String> stringList;
    private List<Integer> intList;
    private List<Character> charList;
    private List<Double> doubleList;

    private ListIterator<String> stringListIterator;
    private ListIterator<Integer> intListIterator;
    private ListIterator<Character> charListIterator;
    private ListIterator<Double> doubleListIterator;

    /* HELPER METHODS */
    public void buildLists(int num) {
        for (int i = 0; i < num; i++) {
            stringList.add(STRING_VALUES[i]);
            intList.add(INT_VALUES[i]);
            charList.add(CHAR_VALUES[i]);
            doubleList.add(DOUBLE_VALUES[i]);
        }
    }

    public void buildLists(int num, int startPosition) {
        for (int i = 0; i < num; i++) {
            stringList.add(STRING_VALUES[i]);
            intList.add(INT_VALUES[i]);
            charList.add(CHAR_VALUES[i]);
            doubleList.add(DOUBLE_VALUES[i]);
        }


        stringListIterator = stringList.listIterator(startPosition);
        intListIterator = intList.listIterator(startPosition);
        charListIterator = charList.listIterator(startPosition);
        doubleListIterator = doubleList.listIterator(startPosition);
    }



    /**
     * with the @Before tag, this method will called before EVERY SINGLE @Test method that runs
     */
    @Before
    public void setUp() {
        stringList = new DoubleLinkedList<String>();
        intList = new DoubleLinkedList<Integer>();
        charList = new DoubleLinkedList<Character>();
        doubleList = new DoubleLinkedList<Double>();

        stringListIterator = stringList.listIterator();
        intListIterator = intList.listIterator();
        charListIterator = charList.listIterator();
        doubleListIterator = doubleList.listIterator();
    }





    @Test
    public void testHasNextAssumeFalse()
    {
        //testing
        assertFalse("String list Iterator should return false for hasNext() method", stringListIterator.hasNext());
        assertFalse("Integer list Iterator should return false for hasNext() method", intListIterator.hasNext());
        assertFalse("Character list Iterator should return false for hasNext() method", charListIterator.hasNext());
        assertFalse("Double list Iterator should return false for hasNext() method", doubleListIterator.hasNext());
    }

    @Test
    public void testHasPreviousAssumeFalse()
    {
        //testing
        assertFalse("String list Iterator should return false for hasPrevious() method", stringListIterator.hasPrevious());
        assertFalse("Integer list Iterator should return false for hasPrevious() method", intListIterator.hasPrevious());
        assertFalse("Character list Iterator should return false for hasPrevious() method", charListIterator.hasPrevious());
        assertFalse("Double list Iterator should return false for hasPrevious() method", doubleListIterator.hasPrevious());
    }

    @Test
    public void testHasNextAssumeTrue()
    {
        //initialisation
        buildLists(5, 2);

        //testing
        assertTrue("String list Iterator should return true for hasNext() method", stringListIterator.hasNext());
        assertTrue("Integer list Iterator should return true for hasNext() method", intListIterator.hasNext());
        assertTrue("Character list Iterator should return true for hasNext() method", charListIterator.hasNext());
        assertTrue("Double list Iterator should return true for hasNext() method", doubleListIterator.hasNext());
    }

    @Test
    public void testHasPreviousAssumeTrue()
    {
        //initialisation
        buildLists(5, 2);

        //testing
        assertTrue("String list Iterator should return true for hasPrevious() method", stringListIterator.hasPrevious());
        assertTrue("Integer list Iterator should return true for hasPrevious() method", intListIterator.hasPrevious());
        assertTrue("Character list Iterator should return true for hasPrevious() method", charListIterator.hasPrevious());
        assertTrue("Double list Iterator should return true for hasPrevious() method", doubleListIterator.hasPrevious());
    }

    @Test
    public void testPreviousIndexAsserEquals()
    {
        //initialisation
        buildLists(5, 2);

        //testing
        assertEquals("String list Iterator should return 1 for previousIndex() method", 1, stringListIterator.previousIndex());
        assertEquals("Integer list Iterator should return 1 for previousIndex() method", 1, intListIterator.previousIndex());
        assertEquals("Character list Iterator should return 1 for previousIndex() method", 1, charListIterator.previousIndex());
        assertEquals("Double list Iterator should return 1 for previousIndex() method", 1, doubleListIterator.previousIndex());
    }

    @Test
    public void testNextIndexAsserEquals()
    {
        //initialisation
        buildLists(5, 2);

        //testing
        assertEquals("String list Iterator should return 1 for nextIndex() method", 3, stringListIterator.nextIndex());
        assertEquals("Integer list Iterator should return 1 for nextIndex() method", 3, intListIterator.nextIndex());
        assertEquals("Character list Iterator should return 1 for nextIndex() method", 3, charListIterator.nextIndex());
        assertEquals("Double list Iterator should return 1 for nextIndex() method", 3, doubleListIterator.nextIndex());
    }

    @Test
    public void testNext()
    {
        //initialisation
        buildLists(5, 2);

        //correct values
        String testString = "third";
        Integer testInt = 300;
        Character testChar = 'C';
        Double testDouble = 3.3;

        //testing
        assertEquals("String list Iterator should return third for nextIndex() method", testString, stringListIterator.next());
        assertEquals("Integer list Iterator should return 300 for nextIndex() method", testInt, intListIterator.next());
        assertEquals("Character list Iterator should return C for nextIndex() method", testChar, charListIterator.next());
        assertEquals("Double list Iterator should return 3.3 for nextIndex() method", testDouble, doubleListIterator.next());
    }

    @Test
    public void testPrevious()
    {
        //initialisation
        buildLists(5, 2);

        //correct values
        String testString = "second";
        Integer testInt = 200;
        Character testChar = 'B';
        Double testDouble = 2.2;

        //testing
        assertEquals("String list Iterator should return second for nextIndex() method", testString, stringListIterator.previous());
        assertEquals("Integer list Iterator should return 200 for nextIndex() method", testInt, intListIterator.previous());
        assertEquals("Character list Iterator should return 'B' for nextIndex() method", testChar, charListIterator.previous());
        assertEquals("Double list Iterator should return 2.2 for nextIndex() method", testDouble, doubleListIterator.previous());
    }

    @Test
    public void testAdd()
    {
        //initialisation
        buildLists(5, 2);

        //correct values
        String testString = "thirdPointFive";
        Integer testInt = 350;
        Character testChar = 'Z';
        Double testDouble = 3.8;

        //adding
        stringListIterator.add(testString);
        intListIterator.add(testInt);
        charListIterator.add(testChar);
        doubleListIterator.add(testDouble);

        //testing
        assertEquals("String list Iterator should return thirdPointFive for nextIndex() method", testString, stringListIterator.previous());
        assertEquals("Integer list Iterator should return 350 for nextIndex() method", testInt, intListIterator.previous());
        assertEquals("Character list Iterator should return 'Z' for nextIndex() method", testChar, charListIterator.previous());
        assertEquals("Double list Iterator should return 3.8 for nextIndex() method", testDouble, doubleListIterator.previous());
    }

    @Test
    public void testRemove()
    {
        //initialisation
        buildLists(5, 2);

        //correct values
        String testString = "second";
        Integer testInt = 200;
        Character testChar = 'B';
        Double testDouble = 2.2;

        //removing
        stringListIterator.remove();
        intListIterator.remove();
        charListIterator.remove();
        doubleListIterator.remove();

        //checking
        assertEquals("String list Iterator should return second for nextIndex() method", testString, stringListIterator.previous());
        assertEquals("Integer list Iterator should return 200 for nextIndex() method", testInt, intListIterator.previous());
        assertEquals("Character list Iterator should return 'B' for nextIndex() method", testChar, charListIterator.previous());
        assertEquals("Double list Iterator should return 2.2 for nextIndex() method", testDouble, doubleListIterator.previous());
    }

    @Test //nope
    public void testSet()
    {
        //initialisation
        buildLists(5, 2);

        //correct values
        String testString = "secondButNew";
        Integer testInt = 202;
        Character testChar = 'G';
        Double testDouble = 2.3;

        //removing
        stringListIterator.set(testString);
        intListIterator.set(testInt);
        charListIterator.set(testChar);
        doubleListIterator.set(testDouble);

        //checking
        assertEquals("String list Iterator should return secondButNew for nextIndex() method", testString, stringListIterator.previous());
        assertEquals("Integer list Iterator should return 202 for nextIndex() method", testInt, intListIterator.previous());
        assertEquals("Character list Iterator should return 'G' for nextIndex() method", testChar, charListIterator.previous());
        assertEquals("Double list Iterator should return 2.3 for nextIndex() method", testDouble, doubleListIterator.previous());
    }
}
