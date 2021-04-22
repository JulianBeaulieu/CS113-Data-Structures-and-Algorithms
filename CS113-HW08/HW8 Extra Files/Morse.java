package edu.miracosta.cs113;

public class Morse implements Comparable
{
  /*########### Fields ###########*/
  private String code;
  private String letter;

  /*########### Constructors  ###########*/

  public Morse()
  {
    code = "";
    letter = "";
  }

  public Morse(String letter, String code)
  {
    this.code = code;
    this.letter = letter;
  }

  /**
  * Copy constructor
  *
  * @see String#Morse(Morse other)
  *
  * @param other which is of the Morse class
  *
  */
  public Morse(Morse other)
  {
    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }
    this.letter = other.letter;
    this.code = other.code;
  }

  /*########### Mutator Methods ###########*/

  public void setLetter(String letter)
  {
    this.letter = letter;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  /*########### Accessor Methods ###########*/

  public String getLetter()
  {
    return letter;
  }

  public String getCode()
  {
    return code;
  }

  /*########### Default Methods ###########*/

  //clones this Morse
  public Morse clone()
  {
    return new Morse(this);
  }

  public boolean equals(Object other)
  {
    if(other.getClass() != this.getClass() || other == null)
    {
      return false;
    }
    else
    {
        Morse otherObject = (Morse) other;
      return otherObject.code == this.code
                        &&
              otherObject.letter == this.letter;
    }
  }

  public String toString()
  {
    return letter + " " + code;
  }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     * <p>
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o)
    {

        int numOfIter = 0;
        boolean otherIsBigger = false;

        Morse other = (Morse) o;

        if(this.code.equals(other.code))
        {
          return 0;
        }

        if(this.code.length() <= other.code.length())
        {
            numOfIter = this.code.length();
            otherIsBigger = true;
        }
        else
        {
            numOfIter = other.code.length();
        }

        for(int i = 0; i < numOfIter; i++)
        {

            if(this.code.charAt(i) < other.code.charAt(i))
            {
                return -1;
            }
            else if(this.code.charAt(i) > other.code.charAt(i))
            {
                return 1;
            }
        }

        if(otherIsBigger && other.code.charAt(other.code.length() - 1) == '*')
        {
            return -1;
        }
        else if(otherIsBigger && other.code.charAt(other.code.length() - 1) == '-')
        {
            return 1;
        }
        else if(!otherIsBigger && this.code.charAt(this.code.length() - 1) == '*')
        {
            return -1;
        }
        else if(!otherIsBigger && this.code.charAt(this.code.length() - 1) == '-')
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
