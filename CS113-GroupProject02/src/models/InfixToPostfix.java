package models;

import java.util.*;

public class InfixToPostfix
{
    //Data Fields

    /** The operator Stack. */
    private static Stack<Character> operatorStack;

    /** The operators. */
    private static final String OPERATORS = "+-*/()";

    /** The precedence of the operators matches order in OPERATORS. */
    private static final int[] PRECEDENCE = {1, 1, 2, 2, -1, -1};

    /** The postfix string. */
    private static StringBuilder postfix;

    //Methods

    /**
     * Convert a string from infix to postfix
     * @param infix The infix expression
     * @return the postfix expression
     */
    public static String convert(String infix)
    {
        operatorStack = new Stack<Character>();
        postfix = new StringBuilder();

        try
        {
            //Process each token in the infix string.
            String nextToken;
            Scanner scan = new Scanner(infix);

            while((nextToken = scan.findInLine("[\\p{L}\\p{N}]+|[-+/\\*()]")) != null)
            {
                char firstChar = nextToken.charAt(0);

                // Is it an operand?
                if(Character.isJavaIdentifierStart(firstChar) || Character.isDigit(firstChar))
                {
                    postfix.append(nextToken);
                    postfix.append(' ');
                } // Is it an operator?
                else if(isOperator(firstChar))
                {
                    processOperator(firstChar);
                }
                else
                {
                    return "Error";
                }
            }// end loop

            //Pop any remaining operators and
            //append them to postfix.
            while(!operatorStack.empty())
            {
                char op = operatorStack.pop();

                postfix.append(op);
                postfix.append(' ');
            }
            System.out.println();

            //assert: Stack is empty return result.
            return postfix.toString();
        }
        catch(EmptyStackException e)
        {
            //Pop was attempted on an empty stack
            System.out.println("Syntax Error: The stack is empty");
        }

        return "Error"; //to make compiler (and me) happy
    }

    /**
     * Method to process operators
     * @param op The operator
     * @throws EmptyStackException
     */
    private static void processOperator(char op)
    {
        if(operatorStack.empty() || op == '(')
        {
            operatorStack.push(op);
        }
        else
        {
            //Peek the operator stack and
            //let topOp be a top operator.
            char topOp = operatorStack.peek();

            if(precedence(op) > precedence(topOp))
            {
                operatorStack.push(op);
            }
            else
            {
                //Pop all stacked operators with equal
                //or higher precedence than op.
                while(!operatorStack.empty() && precedence(op) <= precedence(topOp))
                {
                    operatorStack.pop();

                    if(topOp == '(')
                    {
                        // Matching '(' pooped - exit loop.
                        break;
                    }

                    postfix.append(topOp);
                    postfix.append(' ');
                    if(!operatorStack.empty())
                    {
                        //Reset topOp.
                        topOp = operatorStack.peek();
                    }
                }

                //assert: Operator stack is empty or
                //        current operator precedence >
                //        top of stack operator precedence.
                if(op != ')')
                {
                    operatorStack.push(op);
                }
            }
        }
    }

    /**
     * Determine whether a character is an operator
     * @param ch The character to be tested
     * @return true if ch is an operator
     */
    private static boolean isOperator(char ch)
    {
        return OPERATORS.indexOf(ch) != -1;
    }

    /**
     * Determine the precedence of an operator.
     * @param op The operator
     * @return the precedence
     */
    public static int precedence(char op) {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }
}
