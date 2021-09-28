package cse214hw1;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T> {

    private T[] array;
    private int beginningIndex;
    private int endIndex;
    private int numOfElements;

    public ArrayDeque() {
        this.array = (T[]) new Object[3];
        this.beginningIndex = 0;
        this.numOfElements = 0;
        this.endIndex=0;
    }

    public ArrayDeque(int size) {
        this.array = (T[]) new Object[size];
        this.beginningIndex = 0;
        this.numOfElements = 0;
        this.endIndex=0;

    }

    @Override
    public void addFirst(T t) {
        if (this.numOfElements == this.array.length) {
            resize();
            this.beginningIndex = (this.beginningIndex + this.array.length - 1) % this.array.length;
            this.array[this.beginningIndex] = t;
            this.numOfElements++;
        }
        this.beginningIndex = (this.beginningIndex + this.array.length - 1) % this.array.length;
        this.array[this.beginningIndex] = t;
        this.numOfElements++;
    }

    @Override
    public void addLast (T t){
        if (this.numOfElements == this.array.length) {
            resize();
            this.endIndex = this.endIndex % this.array.length;
            this.array[this.endIndex] = t;
            this.numOfElements++;
        }
        this.endIndex = this.endIndex % this.array.length;
        this.array[this.endIndex] = t;
        this.numOfElements++;
    }

    @Override
    public T removeFirst () {
        T removedElement;
        if (numOfElements == 0) {
            throw new NoSuchElementException();
        }
        numOfElements--;
        removedElement = this.array[beginningIndex];
        this.array[beginningIndex] = null;
        beginningIndex = (beginningIndex + 1) % this.array.length;
        return removedElement;
    }

    @Override
    public T removeLast () {
        T removedElement;
        if (numOfElements == 0) {
            throw new NoSuchElementException();
        }
        numOfElements--;
        removedElement = this.array[endIndex];
        this.array[endIndex] = null;
        endIndex = (endIndex + this.array.length + 1) % this.array.length;
        return removedElement;
    }

    private void resize() {
        T[] resizedArray = (T[]) new Object[Math.max(this.array.length * 2, 1)];
        for (int i = 0; i < this.array.length; i++) {
            resizedArray[i] = this.array[(beginningIndex+i) % resizedArray.length];
        }
        this.beginningIndex = 0;
        this.endIndex = this.array.length-1;
        this.array = resizedArray;
    }

    @SafeVarargs
    public static <T> ArrayDeque<T> of(T... args) {
        ArrayDeque<T> staticArray = new ArrayDeque<>(args.length);
        for (T x : args) {
            staticArray.addFirst(x);
        }
        return staticArray;
    }

    @Override
    public String toString() {
        return "ArrayDeque{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
