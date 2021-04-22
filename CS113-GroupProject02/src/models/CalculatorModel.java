/**
 * CalculatorModel.java : Concrete class using stack data structure to evaluate infix math expression
 *
 * @author Julian Beaulieu & Hunter Haggard
 * @version 3.0
 *
 */

package models;

import views.DisplayThe;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CalculatorModel implements CalculatorInterface {

    @Override
    public String evaluate(String expression)
    {
        System.out.println(expression);
      if(expression.contains("x") || expression.contains("^") || !ParenChecker.isBalanced(expression))
      {
          DisplayThe.rickRollError();
          return "Error";
      }
      else if(expression.length() == 1 || expression.length() == 0)
      {
          return expression;
      }

      //postfix evaluator can throw an exception
      try
      {
          return PostfixEvaluator.eval(InfixToPostfix.convert(expression));
      }
      catch (Exception e)
      {
          DisplayThe.rickRollError();
          return "Error";
      }
    }

    /*
    *####################################################################
    *####################### Derive Methods Below #######################
    *####################################################################
    */



    /**
     * takes in an infix expression and derives it using stack data structure
     *
     * @param expression unevaluated function containing +, -, x, ^
     * @return String  expression derived
     */
    @Override
    public String derive(String newExpression)
    {
        String expression = newExpression;

        //this makes sure, that if there is an illegal expression, it will return  error
        if(expression.contains("*") || expression.contains("/") ||
                expression.contains("(") || expression.contains(")") ||
                    expression.equals("") || expression.equals(" "))
        {
            DisplayThe.rickRollError();
            return "Error";
        }
        else if(expression.equals("0") || !expression.contains("x"))
        {
            return "0";
        }
        else if(true)
        {
            try
            {

                //if this does not break, expression is a normal number, in which case it will just be 0
                int testIfParseIsSuccessfull = Integer.parseInt(expression);

                return "0";
            }
            catch(Exception e)
            {
                //if exception is thrown, it should be differentiable
            }
        }

        //next three replaceAll are needed to get the Terms in the right form
        expression = expression.replaceAll(" + ", " +");
        expression = expression.replaceAll(" - ", " -");
        expression =  expression.replaceAll("^ -", "^-");

        //the next replaceAll will remove all whitespaces
        expression = expression.replaceAll(" ", "");

        //the next replaceAll will add whitespaces at the correct location
        for(int i = 0; i < expression.length(); i++)
        {
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-')
            {
                //makes sure there is no out of bounds exception
                if(i - 1 >= 0)
                {
                    if(!(expression.charAt(i) == '-' && expression.charAt(i - 1) == '^'))
                    {
                        expression = expression.substring(0, i) + " " + expression.substring(i);
                        i++;
                    }
                }
            }
        }

        //needed to cut the Terms
        StringTokenizer st = new StringTokenizer(expression);

        //needed to save the terms
        ArrayList<Term> termList = new ArrayList<Term>(st.countTokens());

        //this takes the string and cuts it up and add it to the arrayList
        while(st.hasMoreTokens())
        {
            termList.add(new Term(st.nextToken()));
        }

        //this will take the array of Terms, pass it into the method, which will do the derivative.
        //this method returns the arrayList. The toArray method is then called, which returns an array of Terms
        //which will then be passed into the Polynomial object, where it will be sorted by the Term.
        Polynomial temp = new Polynomial(doTheDerivative(termList).toString().replaceAll("", ""));

        //here it returns the resulting polynomial
        return temp.toString();
    }


    /** This does the derivative
     * @param underivedFunction is a linked list of the Terms
     * @return the string representation of the resulting derivative
     */
    private String doTheDerivative(ArrayList<Term> underivedFunction)
    {
        //makes a new ArrayList, which saves the derived function
        ArrayList<Term> derivedFunction = new ArrayList<Term>();

        //this loops through the array of the underrived function and checks what has a x and what not
        for(int i = 0; i < underivedFunction.size(); i++)
        {
            //if it has a x then it will take the derivative, if it does not have an x, the number will be 0
            if(underivedFunction.get(i).getExponent() != 0)
            {
                derivedFunction.add(new Term(underivedFunction.get(i).getCoefficient() * underivedFunction.get(i).getExponent(),
                        underivedFunction.get(i).getExponent() - 1));
            }
        }

        //this returns the string of the derived function
        return derivedFunction.toString();
    }
}
