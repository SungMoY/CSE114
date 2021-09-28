package applications.arithmetic;

/**
 * This interface defines the structure used to implement an evaluator of a
 * mathematical expression. The expression is accepted as string and the two
 * methods, nextToken and isOperand, is used to differentiate the operands and
 * operators to complete the mathematics.
 * @author Sung Mo Yang
 */
public interface Evaluator {

    /**
     * Evaluates a given expression.
     * @param expressionString a expression
     * @return the solution of the expression
     */
    Double evaluate(String expressionString);

    /**
     * Identifies the next token of a given string given the index.
     * @param s string that the token is derived from.
     * @param start the starting index of the token.
     * @return next token.
     */
    String nextToken(String s, int start);

    /**
     * Determines whether or not a string is an operand.
     * @param s string to be determined whether or not it is an operand.
     * @return true if the given string is an operand and false if it is not.
     */
    boolean isOperand(String s);
}
