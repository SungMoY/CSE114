package applications.arithmetic;
import datastructures.sequential.Stack;

/**
 * This class converts an infix expression to its postfix expression equivalent for
 * evaluation of the postfix expression. The accepted infix expression should be
 * a dyck word with balanced parantheses. The two methods nextToken() and isOperand
 * serve to differentiate each element of infix expression as its own.
 * @author Sung Mo Yang
 */
public class ToPostfixConverter implements Converter {

    /**
     * Converts the given infix expression into a postfix expression
     * @param expression infix expression to be converted. This should pass
     * isDyckWord to make sure the expression is valid
     * @return the postfix expression
     */
    @Override
    public String convert(ArithmeticExpression expression) {
        Stack<Operator> operatorAndLeftParenthesis = new Stack<>();
        String originalExpression = expression.getExpression();
        String whiteSpace = " ";
        String convertedString = "";
        String currentToken;
        for (int i = 0; i < originalExpression.length(); i++) {
            currentToken = nextToken(originalExpression, i);
            if (isOperand(currentToken)) {
                convertedString += currentToken + whiteSpace;
            } else if (Operator.isOperator(currentToken)){
                if (!operatorAndLeftParenthesis.isEmpty()) {
                    while (operatorAndLeftParenthesis.peek().getRank() >= Operator.of(currentToken).getRank() && operatorAndLeftParenthesis.isEmpty()) {
                        convertedString += operatorAndLeftParenthesis.pop() + whiteSpace;
                    }
                }
                operatorAndLeftParenthesis.push(Operator.of(currentToken));
            } else if (Operator.of(currentToken).equals(Operator.LEFT_PARENTHESIS)) {
                operatorAndLeftParenthesis.push(Operator.of(currentToken));
            } else if (Operator.of(currentToken).equals(Operator.RIGHT_PARENTHESIS)) {
                while (!operatorAndLeftParenthesis.isEmpty()) {
                    if (operatorAndLeftParenthesis.peek().equals(Operator.LEFT_PARENTHESIS)) {
                        operatorAndLeftParenthesis.pop();
                    } else {
                        convertedString += operatorAndLeftParenthesis.pop().getSymbol() + whiteSpace;
                    }
                }
            }
            i += currentToken.length() - 1;
        }
        while (!operatorAndLeftParenthesis.isEmpty()) {
            convertedString += operatorAndLeftParenthesis.pop().getSymbol() + whiteSpace;
        }
        return convertedString.substring(0, convertedString.length() - 1);
    }

    /**
     * Returns the next token of a given string starting from a given index
     * @param s string that the token is derived from
     * @param start starting index of the given string
     * @return next token
     */
    @Override
    public String nextToken(String s, int start) {
        String token = "";
        if (!isOperand(String.valueOf(s.charAt(start)))) {
            return String.valueOf(s.charAt(start));
        } else {
            for (int i = start; i < s.length(); i++) {
                if (isOperand(String.valueOf(s.charAt(i)))) {
                    token += s.charAt(i);
                } else {
                    break;
                }
            }
        }
        return token;
    }

    /**
     * Checks if the string is a valid operand. Every character in the string must
     * be a digit or a decimal sign.
     * @param s string that is checked.
     * @return true if the string is a valid operand and false if it is not.
     */
    @Override
    public boolean isOperand(String s) {
        for (int j = 0; j < s.length(); j++) {
            if (!(Character.isDigit(s.charAt(j)) || s.charAt(j) == '.')) {
                return false;
            }
        }
        return true;
    }
}
