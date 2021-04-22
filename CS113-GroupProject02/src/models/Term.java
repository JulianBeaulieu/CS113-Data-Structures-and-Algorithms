/**
 * @author Julian Beaulieu
 * @version 4.0
 */
package models;

import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class Term implements Comparable<Term>
{
  private int exponent;
  private int coefficient;

  //+++++++++++++++++++++
  //+++ Constructors ++++
  //+++++++++++++++++++++

  /**
  * Default Constructor
  *
  * @see Constructor which sets the coefficient and exponent
  *
  */
  public Term()
  {
    this.exponent = 1;
    this.coefficient = 1;
  }

  /**
  * Constructor
  *
  * @param coefficient which is a int and sets the coefficient
  *
  * @param exponent which is a int and sets the exponent
  *
  * @see This will initialize both variables to the value which is passed in
  *
  */
  public Term(int newCoefficient, int newExponent)
  {
    this.exponent = newExponent;
    this.coefficient = newCoefficient;
  }

  /**
  * Constructor
  *
  * @param oneTerm which is a String that contains the new Term
  *
  * @see The Constructor will take the string and make it into a term
  *
  */
  public Term(String oneTerm)
  {
    if(oneTerm == null || oneTerm.equals("")) //incase the string has no valid caracters
    {
      this.exponent = 0;
      this.coefficient = 0;
    }
    else if(!oneTerm.toLowerCase().contains("x")) //if this does NOT contain x
    {
      //may throw error
      try
      {
        this.coefficient = Integer.parseInt(oneTerm);
      }
      catch(NumberFormatException e)
      {
        //something else got into the parse int
      }
    }
    else //if there IS a x or x^
    {
      if(oneTerm.toLowerCase().contains("x^")) //if there is a x^
      {
        StringTokenizer st = new StringTokenizer(oneTerm.toLowerCase(), "x^");

        String coefficientString = "";
        String exponentString = "";

        //this will cut the string in two
        try
        {
          coefficientString = st.nextToken();
          exponentString = st.nextToken();
        }
        catch(NoSuchElementException e)
        {
          //do nothing
        }

        //if there is only a + or - or nothing at all,
        //then these special cases will be used

        //this is if there is only a x^something
        if(exponentString.equals(""))
        {
          this.coefficient = 1;
          this.exponent = Integer.parseInt(coefficientString);
        }
        //incase there is a + or - before the x^
        else if(coefficientString.equals("+"))
        {
          coefficient = 1;
          exponent = Integer.parseInt(exponentString);
        }
        else if(coefficientString.equals("-"))
        {
          coefficient = -1;
          exponent = Integer.parseInt(exponentString);
        }
        else
        {
          coefficient = Integer.parseInt(coefficientString);
          exponent = Integer.parseInt(exponentString);
        }
      }
      else //if there is only a x
      {
        if(oneTerm.equalsIgnoreCase("-x"))
        {
          this.coefficient = -1;
          this.exponent = 1;
        }
        else if(oneTerm.equalsIgnoreCase("+x"))
        {
          this.coefficient = 1;
          this.exponent = 1;
        }
        else if(oneTerm.equalsIgnoreCase("x"))
        {
          this.coefficient = 1;
          this.exponent = 1;
        }
        else
        {
          //string tokenizer breaks the string apart
          StringTokenizer st = new StringTokenizer(oneTerm.toLowerCase(), "x");

          try
          {
            this.coefficient = Integer.parseInt(st.nextToken());
          }
          catch(NumberFormatException e)
          {
            //there was an error in the char it tried to parse
          }
          this.exponent = 1;
        }
      }
    }
  }

  /**
  * Copy constructor
  *
  * @see String#Term(Term other)
  *
  * @param other which is of the edu.miracosta.cs113.Term class
  *
  */
  public Term(Term other)
  {
    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }
    this.exponent = other.exponent;
    this.coefficient = other.coefficient;
  }

  //+++++++++++++++++++++
  //+++++ Mutators ++++++
  //+++++++++++++++++++++

  /**
  * setExponent
  *
  * @param newExponent which is an integer
  *
  * @see sets the exponent
  *
  */
  public void setExponent(int newExponent)
  {
    this.exponent = newExponent;
  }

  /**
  * setCoefficient
  *
  * @param newCoefficient which is an integer
  *
  * @see sets the coefficient
  *
  */
  public void setCoefficient(int newCoefficient)
  {
    this.coefficient = newCoefficient;
  }

  /**
  * setAll
  *
  * @param coefficient which is a int and sets the coefficient
  *
  * @param exponent which is a int and sets the exponent
  *
  * @see This method sets the coefficient and exponent
  *
  */
  public void setAll(int newCoefficient, int newExponent)
  {
    this.exponent = newExponent;
    this.coefficient = newCoefficient;
  }

  //++++++++++++++++++++++
  //+++++ Accessors ++++++
  //++++++++++++++++++++++

  /**
  * getExponent
  *
  * @see Returns the value of the exponent in an integer
  *
  * @return integer which is the exponent
  *
  */
  public int getExponent()
  {
    return exponent;
  }

  /**
  * getCoefficient
  *
  * @see Returns the value of the coefficient in an integer
  *
  * @return integer which is the coefficient
  *
  */
  public int getCoefficient()
  {
    return coefficient;
  }

  //++++++++++++++++++++++++++++
  //+++++ Default Methods ++++++
  //++++++++++++++++++++++++++++

  /**
  * equals
  *
  * @param other which is of Term class
  *
  * @see Compares two Terms and evaluates if they are equal
  *
  * @return boolean Either true or false
  *
  */
  public boolean equals(Object other)
  {
	  if(other == null || this.getClass() != other.getClass())
	  {
		  return false;
	  }

    Term otherTerm = (Term) other;

    return ((this.getExponent() == otherTerm.getExponent()) && (this.getCoefficient() == otherTerm.getCoefficient()));
  }

  /**
  * compareTo
  *
  * @param other which is of the Term class
  *
  * @see Will evaluate if the passed in Term is samller, same or bigger
  *
  * @return integer If the Term passed in is bigger, it will return a positive number
  * @return integer If the Term passed in is smaller, it will return a negative number
  * @return integer If the Term passed in is the same, it will return a 0
  *
  */
  public int compareTo(Term other)
  {
    //this checks if the exponents are the same,
    //if they are, it will go to else of outer if statement
    if(this.exponent != other.exponent)
    {
      if(this.exponent < other.exponent)
      {
        return -1;
      }
      else
      {
        return 1;
      }
    }
    //in else it compares the coefficients and then returns the coresponding int
    else
    {
      if(this.coefficient < other.coefficient)
      {
        return -1;
      }
      if(this.coefficient > other.coefficient)
      {
        return 1;
      }
      else
      {
        return 0;
      }
    }
  }

  /**
  * toString
  *
  * @return String which holds the Term i.E. -6x^7
  *
  * @see will returns all the values of the Term in a String
  *
  */
  public String toString()
  {
    String coefficientString = "" + coefficient;
    String exponentString = "" + exponent;

    //the next if statements go through various cases to make sure the
    //correct string is returned

    //if the coefficient is 0
    if(coefficient == 0)
    {
      return "";
    }

    //if the exponent is 0
    if(exponent == 0)
    {
      if(coefficient < 0)
      {
        return coefficientString;
      }
      else
      {
        return "+" + coefficientString;
      }
    }

    //if the exponent is 1
    if(exponent == 1)
    {
      if(coefficient < 0)
      {
        if(coefficient == -1)
        {
          return "-x";
        }
        else
        {
          return coefficientString + "x";
        }
      }
      else
      {
        if(coefficient == 1)
        {
          return "+x";
        }
        else
        {
          return "+" + coefficientString + "x";
        }
      }
    }

    //only if the coefficient is 1 but the exponent is something bigger
    if(coefficient == 1)
    {
      return "+x^" + exponentString;
    }
    else if(coefficient == -1)
    {
      return "-x^" + exponentString;
    }

    if(coefficient > 0)
    {
      return "+" + coefficientString + "x^" + exponentString;
    }

    //this if none of the top special cases apply
    return coefficientString + "x^" + exponentString;
  }

  /**
  * Clone
  *
  * @see returns a Term object with is equal to the generated object
  *
  * @return Term Which is the clone of the Term Object
  */
  public Term clone()
  {
    return new Term(this);
  }
}
