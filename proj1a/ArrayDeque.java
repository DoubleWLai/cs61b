public class ArrayDeque<T> {
    private T[] Array;
    private int size;
    private  int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        Array = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private void resize() {
        if (size == Array.length) {
            resizeHelper(Array.length * 2);
        }
        if (size < Array.length / 2) {
            reduce(Array.length / 2);
        }
    }

    private void resizeHelper(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(Array, 0, a, 0, size);
        Array = a;
    }

    private void reduce(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(Array, 0, a, 0, size);
        Array = a;
    }

    public void addFirst(T item) {
        resize();
        T[] a = (T[]) new Object[Array.length];
        System.arraycopy(Array, 0, a, 1, size);
        a[0] = item;
        Array = a;
        size++;
    }

    public T removeFitst() {
        T first = Array[0];
        T[] a = (T[]) new Object[Array.length -1];
        System.arraycopy(Array, 1, a, 0, size-1);
        Array = a;
        resize();
        size--;
        return first;
    }

    public T getFirst() {
        return Array[0];
    }

    public void addLast(T item) {
        resize();
        Array[size] = item;
        size++;
    }

    public T removeLast() {
        T last = Array[size -1];
        T[] a = (T[]) new Object[Array.length -1];
        System.arraycopy(Array, 0, a, 0, size-1);
        Array = a;
        resize();
        size--;
        return last;
    }

    public T getLast() {
        return Array[size-1];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(Array[i]);
            System.out.print(" ");
        }
        System.out.println(" ");
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            return Array[index];
        }
    }























}
