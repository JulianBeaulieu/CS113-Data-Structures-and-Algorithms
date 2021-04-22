package models;

import java.util.*;

public class PostfixEvaluator
{

    //Nested Class
    /** Class to report a syntax error. */
    public static class SyntaxErrorException extends Exception
    {
        /**
         * Construct a SyntaxErrorException with the specified
         * message
         * @param message The message
         */
        SyntaxErrorException(String message)
        {
            super(message);
        }
    }

    //Constant
    /** A list of operators. */
    private static final String OPERATORS = "+-*/";

    //Data Field
    /** The operand stack. */
    private static Stack<Integer> operandStack;

    //Methods

    /**
     * Evaluates the current operation.
     * This function pops the two operands off the operand
     * stack and applies the operator-
     * @param op A character representing the operator
     * @return The result op applying the operator
     * @throws EmptyStackException if pop is attempted on an empty stack
     */
    private static int evalOp(char op)
    {
        // Pop the two operands off the stack
        int rhs = operandStack.pop();
        int lhs = operandStack.pop();
        int result = 0;

        //Ecaluate the operator.
        switch (op)
        {
            case '+' :  result = lhs + rhs;
                        break;

            case '-' :  result = lhs - rhs;
                        break;

            case '*' :  result = lhs * rhs;
                        break;

            case '/' :  result = lhs / rhs;
                        break;
        }

        return result;
    }

    /**
     * Determines whether a character is an operator.
     * @param ch The character is an operator
     * @return true of the character is an operator
     */
    private static boolean isOperator(char ch)
    {
        return OPERATORS.indexOf(ch) != -1;
    }

    /**
     * Evaluates a postfix expression.
     * @param expression The expression to be evaluated
     * @return The value of the expression
     * @throws SyntaxErrorException SyntaxErrorException if a syntax error is detected
     */
    public static String eval(String expression) throws SyntaxErrorException
    {
        //Create an empty stack.
        operandStack = new Stack<Integer>();

        //Process each token.
        String[] tokens = expression.split("\\s+");
        try
        {
            for(String nextToken : tokens)
            {
                char firstChar = nextToken.charAt(0);

                //Does it start with a digit?
                if(Character.isDigit(firstChar))
                {
                    //Get the integer value.
                    int value = Integer.parseInt(nextToken);

                    //Push value onto operand stack.
                    operandStack.push(value);
                }//is it an operator?
                else if(isOperator(firstChar))
                {
                    //Evaluate the operator.
                    int result = evalOp(firstChar);

                    //Push result onto the operand stack.
                    operandStack.push(result);
                }
                else
                {
                    //Invalid character - should never happen
                    return "Invalid Character: " + firstChar;
                }
            }//End for.

            //No more tokens - pop result from operand stack.
            int answer = operandStack.pop();

            //Operand stack should be empty
            if(operandStack.empty())
            {
                return answer + "";
            }
            else
            {
                //indicate Syntax Error
                System.out.println("Syntax Error: Stack should be empty");
                return "Error";
            }
        }
        catch(EmptyStackException e)
        {
            //Pop was attempted on an empty stack
            System.out.println("Syntax Error: The stack is empty");
        }

        return "Error"; //to make compiler happy
    }
}
