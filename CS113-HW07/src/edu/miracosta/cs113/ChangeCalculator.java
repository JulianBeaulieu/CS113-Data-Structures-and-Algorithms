package edu.miracosta.cs113;

import java.util.ArrayList;

/**
 * @author Julian Beaulieu
 * @version 6.0
 *
 * Question:
 * Write a recursive method that will dispense change for a given amount of money.
 * The method will display the total number of combinations of quarters, dimes, nickels,
 * and pennies that equal the desired amount and all of the combinations as well.
 */

public class ChangeCalculator
{
  /**
   * Wrapper method for the recursive method
   *
   * @param change which the user wants to know all combinations
   * @return arr which is the array list with all combinations
   */
  public static ArrayList<String> changeCalculator(int change)
  {
    //creates the array which stores the combinations
    ArrayList<String> arr = new ArrayList<String>();

    if(change <= 0)
    {
      return arr;
    }

    //calls the recursive method
    cC(arr, change, change, 0, 0, 0);

    //returns the array after recursive method is done
    return arr;
  }

  /**
   * Actual recursive method
   *
   * @param arr ArrayList with saves all of the combinations
   * @param change which the user wants to know all combinations
   * @param p number of pennies
   * @param n number of nickels
   * @param d number of dimes
   * @param q number of quarters
   */
  private static void cC(ArrayList<String> arr, int change, int p, int n, int d, int q)
  {
    String temp = "P: " + p + " N: " + n + " D: " + d + " Q: " + q;

    //checks if combination is already taken
    if(!arr.contains(temp))
    {
      arr.add(temp); //if it's not it will add it
    }

    //this checks if we have reached the base case
    if (q * 25 + d * 10 + n * 5 + p > change)
    {
      return;
    }

    //each one of the following if statements calls the method again
    //this will break up the problem in different trees and then solve each one of them until it reaches the base case
    if (p >= 5)
    {
      cC(arr, change, p - 5, n + 1, d, q);
    }
    if (p >= 10)
    {
      cC(arr, change, p - 10, n, d + 1, q);
    }
    if (p >= 25)
    {
      cC(arr, change, p - 25, n, d, q + 1);
    }
  }
}
