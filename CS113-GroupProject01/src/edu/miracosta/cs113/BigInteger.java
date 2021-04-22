package edu.miracosta.cs113;

/**
 * BigInteger.java : Used to store, add, and subtract integers larger than
 * the limit of long data types
 *
 * @author William Pickering and Julian Beaulieu
 * @version 2.0
 *
 */
import javax.print.attribute.standard.MediaSize;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class BigInteger implements BigNumber
{
    private LinkedList<Integer> bigInt;
    private boolean negative;

    /*#### Constructors #######*/

    /**
     *
     * Default constructor
     */
    public BigInteger()
    {
        bigInt = new LinkedList<Integer>();
        negative = false;
    }

    /**
     *
     * String Constructor will convert the String of a BigInteger
     * will check if negative and will remove any spaces
     *
     * @param bigIntegerIn
     *          string representing the entire number
     */
    public BigInteger(String bigIntegerIn)
    {
        negative = false;

        String integerString = bigIntegerIn;

        //checks for negative sign
        if( integerString.contains("-") )
        {
            negative = true;
            integerString = integerString.replace("-", "");
        }
        else
        {
            negative = false;
        }

        bigInt = stringToBigInt(integerString);
    }


    /*#### Add Methods #######*/


    /**
     * add an int value to the BigInteger value
     * @param n
     *             int value to be added to BigInteger
     */
    @Override
    public void add(int n)
    {
        add("" + n);
    }

    /**
     * to convert a String value to a BigInteger value and adds it to the BigInteger value
     * @param n
     *             String value of a BigInteger value to be added to BigInteger
     */
    @Override
    public void add(String n)
    {
        add(new BigInteger(n));
    }

    /**
     * adds a BigInteger value to the current BigInteger value
     * @param bigIntIn
     *             BigInteger value to be added to BigInteger
     */
    public void add(BigInteger bigIntIn)
    {
        //the string is needed in the if statement and incase we are adding a negative and a positive integer
        String otherBigIntAsString = bigIntIn.toString();
        boolean isOtherBigIntNegative = false; //this is needed to control if statements


        //this checks if the other bigInteger is negative. if so, it will set the boolean
        //designated for the other bigInt to true and replaces the negative
        if(otherBigIntAsString.contains("-") )
        {
            //sets the boolean to negative
            isOtherBigIntNegative = true;
            //strips the negative of the toString
            otherBigIntAsString = otherBigIntAsString.replace("-", "");
        }

        int tempInt, smallNumberSize, largeNumberSize;
        String returnValueStr="";
        Iterator<Integer> number1;
        Iterator<Integer> number2;

        //Compares if this BigInteger is larger than the onw passed through
        //sets the longer number as number1
        if(bigInt.size() >= bigIntIn.bigInt.size())
        {
            number1 = bigInt.descendingIterator();
            number2 = bigIntIn.bigInt.descendingIterator();

            smallNumberSize = bigIntIn.bigInt.size();
            largeNumberSize = bigInt.size();
        }
        else
        {
            number1 = bigIntIn.bigInt.descendingIterator();
            number2 = bigInt.descendingIterator();

            smallNumberSize = bigInt.size();
            largeNumberSize = bigIntIn.bigInt.size();
        }

        if(isOtherBigIntNegative && this.negative || isOtherBigIntNegative == false && this.negative == false)
        //Checks for like terms
        {

            boolean carryTheOne = false;
            int i;
            //Adds the smaller number to the larger number
            for(i = 0; i < smallNumberSize; i++)
            {
                tempInt = number1.next()  +number2.next();
                if(carryTheOne)
                {
                    tempInt++;
                    carryTheOne = false;
                }
                if(tempInt >= 1000)
                {
                    tempInt -= 1000;
                    carryTheOne = true;
                }

                //To fill the other spots with zeros
                if(tempInt < 100 && tempInt > 9)
                {
                    returnValueStr = "0" + tempInt + returnValueStr;
                }
                else if(tempInt < 10 && tempInt > 0)
                {
                    returnValueStr = "00" + tempInt + returnValueStr;
                }
                else if(tempInt == 0)
                {
                    returnValueStr = "000" + returnValueStr;
                }
                else
                {
                    returnValueStr = tempInt + returnValueStr;
                }
            }
            for(i = i; i < largeNumberSize; i++)
            {
                tempInt = number1.next(); //number1 will always be the larger number if it is larger
                if(carryTheOne) //only effects the first loop
                {
                    tempInt++;
                    carryTheOne = false;
                }
                if(tempInt >= 1000)
                {
                    tempInt -= 1000;
                    carryTheOne = true;
                }

                if(tempInt < 100 && tempInt  >9)
                {
                    returnValueStr = "0" + tempInt + returnValueStr;
                }
                else if(tempInt < 10 && tempInt > 0)
                {
                    returnValueStr = "00" + tempInt + returnValueStr;
                }
                else if(tempInt == 0)
                {
                    returnValueStr = "000" + returnValueStr;
                }
                else
                {
                    returnValueStr = tempInt + returnValueStr;
                }
            }
            bigInt = stringToBigInt(returnValueStr);
        }
        else
        {
            //if this bigInt or the other bigInt is negative then you are just subtracting two numbers
            if(this.negative)
            {
                //if this is negative, it makes it positive and subtracts them
                this.negative = false;
                subtract(bigIntIn);
            }
            else if(isOtherBigIntNegative)
            {
                //else it will take the toString of the other bigInt, take out the - which it does
                //at the beginning of the method, and then passes this string into the subtract method
                subtract(otherBigIntAsString);
            }
        }
        //this makes sure that there are no empty nodes because the number shrunk
        checkForEmptyNodes();
    }

    /*#### Subtract Methods #######*/

    /**
     * subtract an int value to the BigInteger value
     * @param n
     *             int value to be subtracted from BigInteger
     */
    @Override
    public void subtract(int n)
    {
        subtract("" + n);
    }

    /**
     * to convert a String value to a BigInteger value and subtracts it to the BigInteger value
     * @param n
     *             String value of a BigInteger value to be subtracted from BigInteger
     */
    @Override
    public void subtract(String n)
    {
        subtract(new BigInteger(n));
    }

    /**
     * to convert a String value to a BigInteger value and subtracts it to the BigInteger value
     * @param n
     *             BigInteger to be subtracted from BigInteger
     */
    public void subtract(BigInteger n)
    {

        String otherBigIntAsString = n.toString();
        boolean isOtherBigIntNegative = false;

        //this checks if the other bigInteger is negative. if so, it will set the boolean
        //designated for the other bigInt to true and replaces the negative
        if(otherBigIntAsString.contains("-") )
        {
            isOtherBigIntNegative = true;
            otherBigIntAsString = otherBigIntAsString.replace("-", "");
        }

        LinkedList<Integer> otherBigInt = stringToBigInt(otherBigIntAsString);

        //logic -> { -i - (-i) } == { -i + i } == { - ( i - i ) }
        if((this.negative == true) && (isOtherBigIntNegative == true))
        {
            //when subtracting a negative from a negative
            //we can pull out a negative and it ends up being
            //a subtraction of two positive numbers
            //then making the result negative again
            this.negative = false;

            //this calls the in-house subtract method.
            //we strip the negative of the other integer,then subtract them
            standardSubtraction(otherBigInt);

            //after performing the calculations, we put the negative back
            this.negative = true;
        }
        //logic -> { -i - i } == { - (i + i) }
        else if((this.negative == true) && (isOtherBigIntNegative == false))
        {
            //subtracting a positive from a negative is like adding
            //so we strip both negatives, add them together
            //and put a negative on the resulting number
            this.negative = false;

            //this calls the in-house add method.
            //we strip the negative of the other integer,then add them
            add(otherBigIntAsString);

            //after performing the calculations, we put the negative back
            this.negative = true;
        }
        //logic -> { i - (-i) } == { i + i }
        else if((this.negative == false) && (isOtherBigIntNegative == true))
        {
            //in this case it's just straight up adding
            add(otherBigIntAsString);
        }
        //this subtraction is, when the other Big Int is bigger than this one
        else if(!isThisBigger(otherBigInt))
        {
            standardSubtraction(otherBigInt);
            negative = true;
        }
        //this is just a standard subtraction
        else
        {
            standardSubtraction(otherBigInt);
        }

        //this makes sure that there are no empty nodes because the number shrunk
        checkForEmptyNodes();
    }


    /*#### Helper Methods #######*/

    /**
     * Loops through the linkedList using an iterator and removes unnecessary nodes
     */
    private void checkForEmptyNodes()
    {
        //makes an iterator
        ListIterator<Integer> lstItr = bigInt.listIterator(0);

        //and a boolean so we don't have to break out of the loop
        boolean stopLoop = false;

        //this loops through the linked lists if any of the links at the beginning are 0
        //if a node is, it will delete it. If the iterator hits the last node it will stop,
        //because the int can be 0
        for(int position = 1; position < bigInt.size() && !stopLoop; position++)
        {
            if(lstItr.next() == 0)
            {
                lstItr.remove();
            }
            else
            {
                stopLoop = true;
            }
        }
    }

    /**
     * Tells user if this BigInteger is bigger than the other one
     * @param otherBigInt LinkedList<Integer> of the linked list to compare with
     * @returns boolean
     *             true if this bigInteger is bigger then the other one, false if not
     */
    private boolean isThisBigger(LinkedList<Integer> otherBigInt)
    {
        //if both linkedLists are of the same length, it will compare the ints of the head node
        if(bigInt.size() == otherBigInt.size())
        {
            return bigInt.getFirst() > otherBigInt.getFirst();
        }
        else
        {
            //else it will just return if this bigInt is bigger or smaller than the other one
            return bigInt.size() > otherBigInt.size();
        }
    }

    private void standardSubtraction(LinkedList<Integer> otherBigInt)
    {
        /*
         * Doing the subtraction this way saves a second method which does exactly this
         * Just the other way around, that's why this subtraction method works dynamically
         */

        //makes two pointers
        LinkedList<Integer> theBiggerList;
        LinkedList<Integer> theSmallerList;

        //allows dynamic switching of the bigger list
        if(isThisBigger(otherBigInt))
        {
            theBiggerList = bigInt;
            theSmallerList = otherBigInt;
        }
        else
        {
            theBiggerList = otherBigInt;
            theSmallerList = bigInt;
        }

        //sets both iterators to the end of the linked lists
        ListIterator<Integer> theBiggerListIterator = theBiggerList.listIterator(theBiggerList.size());
        ListIterator<Integer> theSmallerListIterator = theSmallerList.listIterator(theSmallerList.size());

        //this first loop does the first round of subtraction
        while(theSmallerListIterator.hasPrevious())
        {
            //this subtracts the integer in the other bigInt Linked list from this one
            Integer temp = theBiggerListIterator.previous() - theSmallerListIterator.previous();
            theBiggerListIterator.next(); //then the iterator needs to go back to the beginning
            //since the last node returned was the one we just used to subtract we now set the new value to is
            theBiggerListIterator.set(temp);
            theBiggerListIterator.previous(); //this iterates to the next node
        }

        //resets the iterator to the end of the LinkedList
        theBiggerListIterator = theBiggerList.listIterator(theBiggerList.size());

        //this second loop makes sure no node is negative
        while(theBiggerListIterator.hasPrevious())
        {
            //if the next node has a negative integer
            if(theBiggerListIterator.previous().toString().contains("-") && theBiggerListIterator.hasPrevious() == true)
            {
                Integer temp = theBiggerListIterator.previous();
                temp -= 1;
                theBiggerListIterator.next();
                theBiggerListIterator.set(temp);
                temp = theBiggerListIterator.next();
                temp += 1000;
                theBiggerListIterator.set(temp);
                theBiggerListIterator.previous();
            }
        }

        bigInt = theBiggerList;
    }

    /**
     * Transforms a string to a LinkedList<Integer>
     * @param bigIntegerIn
     *              which holds the integer
     * @returns LinkedList<Integer>
     *             LinkedList representation of the string
     */
    private LinkedList<Integer> stringToBigInt(String bigIntegerIn)
    {
        LinkedList<Integer> newLinkedList = new LinkedList<Integer>();

        String integerString = bigIntegerIn;
        //removes all commas
        if(integerString.contains(","))
        {
            integerString = integerString.replace(",", "");
        }
        //removes all spaces
        integerString = integerString.replace(" ", "");

        int i = integerString.length();
        int j = integerString.length() - 3;

        //parses the number into chunks of three digits
        while(j>0)
        {

            Integer tempInt = new Integer(integerString.substring(j,i));
            newLinkedList.addFirst(tempInt);
            i -= 3;
            j -=3 ;
        }
        //when there is less than 3 digits left
        if(j != -3)
        {
            Integer tempInt = new Integer(integerString.substring(0,i));
            newLinkedList.addFirst(tempInt);
        }
        return newLinkedList;
    }

    /*#### Default Methods #######*/

    @Override
    /**
     * @see toString()
     * @returns a String representation of this BigInteger.
     */
    public String toString()
    {
        //creates a String that will be used to contain the bigInteger
        String bigIntString = "";

        //iterator is used to go through the array
        ListIterator<Integer> itr = bigInt.listIterator(0);

        //this is for the special case, that the big int is 0
        if(bigInt.size() == 1)
        {
            if(bigInt.getFirst() == 0)
            {
                return "0";
            }
        }

        //loops through the linked list and adds the integers to the string,
        //but with "," between the decimals
        while(itr.hasNext())
        {
            String temp = itr.next().toString();

            //this adds "0" to the front of the number if the number is to short
            if(temp.length() == 1)
            {
                temp = "00" + temp;
            }
            else if(temp.length() == 2)
            {
                temp = "0" + temp;
            }

            bigIntString += "," + temp;
        }

        //needed to control the loop
        boolean loop = true;

        //loops to delete leading "0"
        while(loop)
        {
            //checks if there is a leading 0, if yes, it deletes it
            if(bigIntString.charAt(1) == '0')
            {
                //deletes the "0" which is at the front of the string
                bigIntString = bigIntString.substring(1, bigIntString.length());
            }
            else //if not it stops the loop
            {
                loop = false;
            }
        }

        //deletes the "," which is at the front of the string
        bigIntString = bigIntString.substring(1, bigIntString.length());

        //depending if the boolean negative is true or false it will add a "-" before the string
        //to indicate that the bigInt is a negative number
        if(negative)
        {
            return "-" + bigIntString;
        }
        else
        {
            return bigIntString;
        }
    }


    /**
     *
     * @returns boolean true or false, depending if the two BigIntegers are the equal or not
     */
    public boolean equals(Object other)
    {
        //if this the to comparing class is null or from a different object
        if(other.getClass() != this.getClass() || other == null)
        {
            return false;
        }

        //else it will just compare the toStrings
        return toString().equals(other.toString());
    }
}
