package datastructures.sequential;
import java.util.EmptyStackException;

/**
 * This class creates the Stack, last in first out, data structure using nodes.
 * Each node in the stack retains a reference to the next node in the stack.
 * Stacks are used to hold and present data values as infix expressions are
 * converted to postfix expressions then evaluated.
 * @author Sung Mo Yang
 */
public class Stack<E> implements LIFOQueue<E> {

    SNode<E> topOfStack;

    /**
     * No-args constructor for the stack.
     */
    public Stack() {
        this.topOfStack = null;
    }

    /**
     * Main constructor for the stack.
     * @param node the first node that is pushed onto the stack.
     */
    public Stack(SNode<E> node) {
        this.topOfStack = node;
    }

    /**
     * Removes and returns the value of the top item in the stack.
     * @return the value of the top item in the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        SNode<E> returnNode = this.topOfStack;
        this.topOfStack = this.topOfStack.getNext();
        return returnNode.getData();
    }

    /**
     * Pushes a node on top of the stack
     * @param element the data value of the node being pushed onto the stack
     */
    @Override
    public void push(E element) {
        this.topOfStack = new SNode<>(element, this.topOfStack);
    }

    /**
     * Returns the value of the top item in the stack without removing it.
     * @return the value of the top item in the stack
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.topOfStack.getData();
    }

    /**
     * Returns the number of items in the stack
     * @return the number of items in the stack
     */
    @Override
    public int size() {
        SNode<E> temp = this.topOfStack;
        int counter = 0;
        while (temp !=null) {
            counter++;
            temp = temp.getNext();
        }
        if (counter > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return counter;
        }
    }

    /**
     * Determines whether or not the stack is empty
     * @return true if the stack is not empty and false if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        if (this.topOfStack == null) {
            return true;
        } else {
            return false;
        }
    }
}