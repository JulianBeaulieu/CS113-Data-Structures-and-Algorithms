/**
 * @author Julian Beaulieu
 * @version 4.0
 *
 * @description The method will check if string is null, if so it will throw an IllegaArgumentExeption
 *              After that, it will take the string, remove the white spaces and make all of the characters lowerCase
 *              Then it takes the length of the string and makes sure that that is devisible by 2
 *              Following that it takes the string, and adds it to a stack.
 *              After adding them to the left stack, half of that stack, will be put into a different stack, the right stack
 *              then it takes one char off of the left stack if the string was odd numbered long
 *              finially it loops through both stacks and compares every char. if one pair does not match it returns false
 *              if the loop finishes without the if statement in it returning false, the string is a palindrome and the method
 *              will return true
 */
package edu.miracosta.cs113;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PalindromeTester {

    /**
     * Checks if parameter is a palindrome using stacks and
     * ignores whitespace + case sensitivity; doesn't ignore digits/symbols
     *
     * @param s any chars allowed,
     * @return returns true if a palindrome (ignoring whitespace and case sensitivity), false otherwise
     */
    private boolean isPalindrome(String s) {

        //checks if the string is null
        if(s == null)
        {
            throw new IllegalArgumentException(); //if string is null, it throws an exception
        }

        //makes sure that the string has no whit spaces and is in lowercase
        String inputString = s.replaceAll(" ", "").toLowerCase();

        //this is needed to controll loops and some if statements
        int lengthOfInputString = 0;

        //to keep track if string is odd numbered
        boolean isOddNumbered = false;

        //two stacks are needed to compare the chars of the string for palindromanimity
        ArrayListStack<Character> leftStack = new ArrayListStack<Character>();
        ArrayListStack<Character> rightStack = new ArrayListStack<Character>();

        //if the string is 0 or 1 long it is theoretically a palindrome
        if(inputString.length() == 0 || inputString.length() == 1)
        {
            return true;
        }

        //this makes sure, that if the string is odd numbered, it will neglect the char in the middle
        if(inputString.length() % 2 == 0)
        {
            lengthOfInputString = inputString.length() / 2;
        }
        else
        {
            lengthOfInputString = (inputString.length() - 1) / 2;
            isOddNumbered = true;
        }

        //first for loop puts the string into one stack
        for(int i = 0; inputString.length() != 0; i++)
        {
            leftStack.push(inputString.charAt(0));
            inputString = inputString.substring(1, inputString.length());
        }

        //this loop takes out half of the left stack and puts it into the right stack
        for(int i = 0; i < lengthOfInputString; i++)
        {
            rightStack.push(leftStack.pop());
        }

        //in case the string is odd numbered, the if statement will remove the middle char by popping the left stack
        if(isOddNumbered)
        {
            leftStack.pop();
        }

        //second for loop pops the stacks and compares the two chars
        for(int i = 0; i < lengthOfInputString; i++)
        {
            //if the chars are not equal it will return false
            if(leftStack.pop() != rightStack.pop())
            {
                return false;
            }
        }

        return true; //if the if statement in the previous for loop is not invoked than the string is a palindrome
    }

    @Test
    public void testErrors() {
        try {
            isPalindrome(null);
            fail("Checking null to see if it's a palindrome should throw IllegalArgumentException!");
        } catch (IllegalArgumentException iae) { /* Test Passed! */ }
    }

    @Test
    public void testSimpleTrueCases() {
        assertTrue("This test is a palindrome", isPalindrome(""));
        assertTrue("This test is a palindrome", isPalindrome(" "));
        assertTrue("This test is a palindrome", isPalindrome("A"));
        assertTrue("This test is a palindrome", isPalindrome("7"));
        assertTrue("This test is a palindrome", isPalindrome("%"));

        assertTrue("This test is a palindrome", isPalindrome("  "));
        assertTrue("This test is a palindrome", isPalindrome("BB"));
        assertTrue("This test is a palindrome", isPalindrome("33"));
        assertTrue("This test is a palindrome", isPalindrome("**"));
    }

    @Test
    public void testSimpleFalseCases() {
        assertFalse("This test is NOT a palindrome", isPalindrome("AC"));
        assertFalse("This test is NOT a palindrome", isPalindrome("71"));
        assertFalse("This test is NOT a palindrome", isPalindrome("@+"));
    }

    @Test
    public void testWhitespaceCases() {
        assertTrue("This test is a palindrome", isPalindrome(" x "));
        assertTrue("This test is a palindrome", isPalindrome(" t   t  "));
        assertTrue("This test is a palindrome", isPalindrome(" 5 5"));
        assertTrue("This test is a palindrome", isPalindrome(" #      # "));

        assertFalse("This test is NOT a palindrome", isPalindrome("m   n  "));
        assertFalse("This test is NOT a palindrome", isPalindrome("   8  7 "));
        assertFalse("This test is NOT a palindrome", isPalindrome("  ^      &  "));
    }

    @Test
    public void testCaseSensitivityCases() {
        assertTrue("This test is a palindrome", isPalindrome("ABba"));
        assertTrue("This test is a palindrome", isPalindrome("roTOR"));
        assertTrue("This test is a palindrome", isPalindrome("rAceCaR"));
    }

    @Test
    public void testComplexCases() {
        assertTrue("This test is a palindrome", isPalindrome("fOO race CAR ooF"));
        assertTrue("This test is a palindrome", isPalindrome("AbBa ZaBba"));
        assertTrue("This test is a palindrome", isPalindrome("1 3 3 7  331"));
        assertTrue("This test is a palindrome", isPalindrome("sT RJKLEeE R#@ $A$ @# REeEL K  JRT s"));

    }

}
