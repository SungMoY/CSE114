package applications.arithmetic;
import datastructures.sequential.Stack;

/**
 * This class checks and wraps a string as a dyck word. It makes sure the
 * incoming infix expression has balanced parenthesis and can be succuessfully
 * converted into a postfix expression for evaluating.
 * @author Ritwik Banerjee
 */
public class DyckWord {

    private final String word;

    public DyckWord(String word) {
        if (isDyckWord(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a valid Dyck word.", word));
    }
    /**
     * Checks if a string is a valid dyck word or not.
     * @param word the string that is to be checked.
     * @return true if the string is a dyck word and false if it is not.
     */
    private static boolean isDyckWord(String word) {
        Stack<Brackets> dyckStack = new Stack<>();
        Brackets currentChar = null;
        for (int i = 0; i < word.length();i++) {
            String item = Character.toString(word.charAt(i));
            switch(word.charAt(i)) {
                case'(':
                    currentChar = Brackets.LEFT_PARENTHESIS;
                    dyckStack.push(currentChar);
                    break;
                case')':
                    if (dyckStack.isEmpty()) {
                        return false;
                    }
                    if (dyckStack.peek() == Brackets.LEFT_PARENTHESIS) {
                        dyckStack.pop();
                    }
                    break;
                case'{':
                    currentChar = Brackets.LEFT_BRACE;
                    dyckStack.push(currentChar);
                    break;
                case'}':
                    if (dyckStack.isEmpty()) {
                        return false;
                    }
                    if (dyckStack.peek() == Brackets.LEFT_BRACE) {
                        dyckStack.pop();
                    }
                    break;
                case'[':
                    currentChar = Brackets.LEFT_SQUARE_BRACKET;
                    dyckStack.push(currentChar);
                    break;
                case']':
                    if (dyckStack.isEmpty()) {
                        return false;
                    }
                    if (dyckStack.peek() == Brackets.LEFT_SQUARE_BRACKET) {
                        dyckStack.pop();
                    }
                    break;
            }
        }
        return dyckStack.isEmpty();
    }

    public String getWord() {
        return word;
    }

}