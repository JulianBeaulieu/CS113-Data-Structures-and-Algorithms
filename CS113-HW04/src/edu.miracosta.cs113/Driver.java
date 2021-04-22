/**
 * @author Julian Beaulieu
 * @version 4.0
 * @see This is a driver I used to debug the linked list with, before using the JUnit tests
 */

package edu.miracosta.cs113;

import java.util.LinkedList;
import java.util.List;

public class Driver
{
    public static void main(String[] args)
    {
        System.out.println("Program starting\n");

        String[] str = {"Hi, ", "you ", "must ", "be ", "using ", "this ", "Double ", "Linked ", "List"};
        String strInjection[] = {"awesome", "Hello and ", ", correct?", "Yo, "};

        int num = 5;

        String[] STRING_VALUES = {"first", "second", "third", "fourth" , "fifth"};
        int[] INT_VALUES = {100, 200, 300, 400, 500};
        char[] CHAR_VALUES = {'A', 'B', 'C', 'D', 'E'};
        double[] DOUBLE_VALUES = {1.1, 2.2, 3.3, 4.4, 5.5};

        DoubleLinkedList<String> dbl1 = new DoubleLinkedList<String>();
        DoubleLinkedList<String> dbl2 = new DoubleLinkedList<String>();

        for(int i = 0; i < str.length; i++)
        {
            dbl1.add(str[i]);
        }
        System.out.println("standard      add: " + dbl1.toString());

        dbl1.add(6, strInjection[0]);
        System.out.println("add at position 6: " + dbl1.toString());

        dbl1.addFirst(strInjection[1]);
        System.out.println("add   first   pos: " + dbl1.toString());

        dbl1.addLast(strInjection[2]);
        System.out.println("add    last   pos: " + dbl1.toString());

        dbl1.remove(1);
        System.out.println("remove  at  pos 1: " + dbl1.toString());

        dbl1.set(0, strInjection[3]);
        System.out.println("set at position 0: " + dbl1.toString());
    }
}
