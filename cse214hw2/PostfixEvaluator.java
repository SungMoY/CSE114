package applications.arithmetic;
import datastructures.sequential.Stack;

/**
 * This class is used to evaluate a postfix expression. It is to be used after
 * the ToPostfixConverter class has converted an infix expression to a postfix
 * exoression. The two methods nextToken() and isOperand serve to differentiate
 * each element of postfix expression as its own.
 * @author Sung Mo Yang
 */
public class PostfixEvaluator implements Evaluator {

    /**
     * Evaluates the given postfix expression
     * @param expressionString postfix expression to be evaluated
     * @return the last item in the stack used to hold operands
     */
    @Override
    public Double evaluate(String expressionString) {
        Stack<Double> operandStack = new Stack<>();
        String currentToken = "";
        for (int i = 0;i<expressionString.length();i++) {
            if (expressionString.charAt(i) == ' ') {
                i++;
            } else {
                currentToken = nextToken(expressionString, i);
                if (isOperand(currentToken)) {
                    operandStack.push(Double.valueOf(currentToken));
                } else {
                    if (Operator.of(currentToken).equals(Operator.ADDITION)) {
                        double val2 = operandStack.pop();
                        double val1 = operandStack.pop();
                        operandStack.push(val1+val2);
                    } else if (Operator.of(currentToken).equals(Operator.SUBTRACTION)) {
                        double val2 = operandStack.pop();
                        double val1 = operandStack.pop();
                        operandStack.push(val1-val2);
                    } else if (Operator.of(currentToken).equals(Operator.MULTIPLICATION)) {
                        double val2 = operandStack.pop();
                        double val1 = operandStack.pop();
                        operandStack.push(val1*val2);
                    } else if (Operator.of(currentToken).equals(Operator.DIVISION)) {
                        double val2 = operandStack.pop();
                        double val1 = operandStack.pop();
                        operandStack.push(val1/val2);
                    }
                }
            }
            i += currentToken.length();
        }
        return operandStack.pop();
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
