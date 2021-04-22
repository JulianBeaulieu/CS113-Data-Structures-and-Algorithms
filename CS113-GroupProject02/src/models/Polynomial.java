/**
 * @author Julian Beaulieu
 * @version 4.0
 */
package models;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class Polynomial
{
  LinkedList<Term> polynomial;

  //+++++++++++++++++++++
  //+++ Constructors ++++
  //+++++++++++++++++++++

  /**
   * Default Constructor
   *
   * @see String#Polynomial()
   *
   */
  public Polynomial()
  {
      polynomial = new LinkedList<Term>();
  }

  /**
   * Constructor
   *
   * @see String#Polynomial(Term[] newTerms)
   *
   * @param Term An array of the Term class
   *
   */
  public Polynomial(Term[] newTerms)
  {
    polynomial = new LinkedList<Term>();

    addToPolynomial(newTerms);
  }

  /**
   * Constructor
   *
   * @see String#Polynomial(String newTerms)
   *
   * @param Term A String with terms
   *
   */
  public Polynomial(String newTerms)
  {
    polynomial = new LinkedList<Term>();

    //makes sure that the negative powers don't get deleted
    if(newTerms.contains("^-"))
    {
      newTerms.replaceAll("^-", "%");
    }

    //puts back the negative exponents if there were any
    if(newTerms.contains("%"))
    {
      newTerms.replaceAll("%", "^-");
    }

    //trims in case it added a space to the front or back
    newTerms.trim();

    //string tokenizer, which cuts at spaces
    StringTokenizer st = new StringTokenizer(newTerms, " ");

    //adds the terms to the polynomial
    while(st.hasMoreTokens())
    {
      String temp = st.nextToken();
      temp = temp.substring(1, temp.length() - 1);
      addTerm(new Term(temp));
    }
  }

  /**
   * Copy constructor
   *
   * @see String#Polynomial(Polynomial other)
   *
   * @param other which is of the Polynomial class
   *
   */
  public Polynomial(Polynomial other)
  {
    this();

    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }

    //loops through the other polynomial and saves the terms in the new polynomial
    for(int i = 0; i < other.getNumTerms(); i++)
    {
      polynomial.addFirst(new Term(other.getTerm(i).getCoefficient(), other.getTerm(i).getExponent()));
    }

    sort();
  }

  //+++++++++++++++++++++
  //+++++ Mutators ++++++
  //+++++++++++++++++++++

  /**
   * addTerm
   *
   * @see This will add a new Term to the Polynomial
   *
   * @param newTerm Which is of Term class
   *
   */
  public void addTerm(Term newTerm)
  {
    Boolean addWholeNewTerm = true;

    //loops through this array to see if this exponent already exists
    for(int i = 0; i < polynomial.size(); i++)
    {
      if(newTerm.getExponent() == polynomial.get(i).getExponent())
      {
        int firstCoefficiant = polynomial.get(i).getCoefficient();
        int secondCoefficiant = newTerm.getCoefficient();

        polynomial.get(i).setCoefficient(firstCoefficiant + secondCoefficiant);
        addWholeNewTerm = false;
      }
    }

    //if it can't find the exponent in the loop, then it will just add it to the polynomial
    if(addWholeNewTerm)
    {
      polynomial.add(newTerm);
      sort();
    }
  }

  /**
   * addAllTerms
   *
   * @see This will add a whole array of Terms to the Polynomial
   *
   * @param newTerms Which needs to be an array
   *
   */
  public Term[] addAllTerms(Term[] newTerms)
  {
    addToPolynomial(newTerms);

    sort();

    return newTerms;
  }

  /**
   * deleteTerm
   *
   * @see This method will take the position of a Term in the Polynomial and delete it
   *
   * @param position Which is an integer
   *
   */
  public Term deleteTerm(int position)
  {
    return polynomial.remove(position);
  }

  /**
   * clear
   *
   * @see This method deletes all Terms of the Polynomial
   *
   */
  public void clear()
  {
    polynomial = new LinkedList<Term>();
  }

  //++++++++++++++++++++++
  //+++++ Accessors ++++++
  //++++++++++++++++++++++

  /**
   * getTerm
   *
   * @see This method will return the Term of a certain position
   *
   * @param position Which is an integer
   *
   * @return Term which is the wanted Term
   */
  public Term getTerm(int position)
  {
    return polynomial.get(position);
  }

  /**
   * getAllTerms
   *
   * @see This method will return a Array of Terms
   *
   * @return Term[] Array of Terms
   *
   */
  public Term[] getAllTerms()
  {
    Term[] newTerms = new Term[polynomial.size()];

    for(int i = 0; i < newTerms.length; i++)
    {
      newTerms[i] = polynomial.get(i);
    }

    return newTerms;
  }

  /**
   * getNumTerms
   *
   * @see This will return the number of Terms in a Polynomial
   *
   * @return integer Number of Terms in the Polynomial
   *
   */
  public int getNumTerms()
  {
    return polynomial.size();
  }

  //++++++++++++++++++++++++++++
  //+++++ Helper Methods +++++++
  //++++++++++++++++++++++++++++

  /**
  Precondition: numberUsed <= a.length;
                The first numberUsed indexed variables have values.
  Action: Sorts so that a[0], a[1], ... , a[numberUsed - 1] are in decreasing
          order by the compareTo method
  */
  private void sort()
  {
    int numberUsed = polynomial.size();
    Comparable[] a = getAllTerms();

    int index, indexOfNextBiggest;
    for (index = 0; index < numberUsed - 1; index++)
    {
      indexOfNextBiggest = indexOfBiggest(index, a, numberUsed);
      interchange(index, indexOfNextBiggest, a);
    }
  }

  /**
  Returns the index of the biggest value among a[startIndex],
  a[startIndex - 1], ... a[numberUsed - 1]
  */
  private int indexOfBiggest(int startIndex, Comparable[] a, int numberUsed)
  {
    Comparable max = a[startIndex];
    int indexOfMax = startIndex;
    int index;
    for(index = startIndex + 1; index < numberUsed; index++)
    {
      //if a[index] is bigger then min
      if(a[index].compareTo(max) > 0)
      {
        max = a[index];
        //max is biggest of a[startIndex] through a[index]
        indexOfMax = index;
      }
    }
    return indexOfMax;
  }

  /**
  Precondition: i and j are legal indices for the array a.
  Postcondition: Values of a[i] and a[j] have been interchanged
  */
  private void interchange(int i, int j, Comparable[] a)
  {
    //this does the swithing in both data structures
    Comparable temp;
    temp = a[i];
    a[i] = a[j];
    a[j] = temp; //original value of a[i]

	  Term tempTerm;
    tempTerm = polynomial.get(i);
    polynomial.set(i, polynomial.get(j));
    polynomial.set(j, tempTerm);
  }

  /**
  Precondition: Term is an array that is not null
  Postcondition: the Array has been added to the Polynomial
  */
  private void addToPolynomial(Term[] newTerms)
  {
    //this loops through the array of terms and adds them
    if(newTerms != null)
    {
      for(int i = 0; i < newTerms.length; i++)
      {
        addTerm(newTerms[i]);
      }

      sort();
    }
  }

  //++++++++++++++++++++++++++++
  //+++++ Default Methods ++++++
  //++++++++++++++++++++++++++++

  /**
  * toString
  *
  * @return String which returns the complete Polynomial
  *
  * @see will returns all the values of the Polynomial in a String
  *
  */
  public String toString()
  {
    sort();

    String theToStringString = "";

    //incase the polynomial is 0 it will return just that
    if(polynomial.size() == 0)
    {
      return "0";
    }

    //else it will loop through the array and add all polynomials together
    for(int i = 0; i < polynomial.size(); i++)
    {
      theToStringString += polynomial.get(i);
    }

    if(theToStringString.charAt(0) == '+' && !theToStringString.equals(""))
    {
      theToStringString = theToStringString.substring(1);
    }

    return theToStringString;
  }

  /**
  * equals
  *
  * @param other which is of Polynomial class
  *
  * @see Compares two Polynomials and evaluates if they are equal
  *
  * @return boolean Either true or false
  *
  */
  public boolean equals(Polynomial theOtherPolynomial)
  {
    //if the other class is null, it will return false
    if(theOtherPolynomial == null)
    {
      return false;
    }

    if(this.polynomial.size() != theOtherPolynomial.getNumTerms())
    {
      return false;
    }

    for(int i = 0; i < this.polynomial.size(); i++)
    {
      if(!this.polynomial.get(i).equals(theOtherPolynomial.polynomial.get(i)))
      {
        return false;
      }
    }

    return true;
  }

  /**
  * Clone
  *
  * @see returns a Polynomial object with is equal to the generated object
  *
  * @return Polynomial Which is the clone of the Polynomial Object
  */
  public Polynomial clone()
  {
    return new Polynomial(this);
  }
}
