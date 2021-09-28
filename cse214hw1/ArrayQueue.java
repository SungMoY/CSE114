package cse214hw1;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {

    private T[] array;
    private int beginningIndex;
    private int endIndex;
    private int numOfElements;

    public ArrayQueue() {
        this.array = (T[]) new Object[3];
        this.beginningIndex = 0;
        this.endIndex = 0;
        this.numOfElements = 0;
    }

    @Override
    public void add(T t) {
        if (this.numOfElements == this.array.length) {
            resize();
            this.array[(this.beginningIndex + this.numOfElements) % this.array.length] = t;
            this.numOfElements++;
        }
        this.array[(this.beginningIndex + this.numOfElements) % this.array.length] = t;
        this.numOfElements++;
    }

    @Override
    public T remove() {
        T removedElement;
        if (numOfElements == 0) {
            throw new NoSuchElementException();
        }
        numOfElements--;
        removedElement = this.array[beginningIndex];
        this.array[beginningIndex] = null;
        beginningIndex = (beginningIndex+this.array.length+1) % this.array.length;
        return removedElement;
    }

    @Override
    public T peek() {
        T peekElement;
        if (numOfElements == 0) {
            throw new NoSuchElementException();
        }
        peekElement = this.array[beginningIndex];
        return peekElement;
    }

    private void resize() {
        T[] resizedArray = (T[]) new Object[Math.max(this.array.length * 2, 1)];
        for (int i = 0; i < this.array.length; i++) {
            resizedArray[i] = this.array[(beginningIndex+i) % this.array.length];
        }
        this.beginningIndex = 0;
        this.array = resizedArray;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
