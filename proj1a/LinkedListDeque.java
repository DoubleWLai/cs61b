public class LinkedListDeque<T> {
    private class ListNode {
        private T item;
        private ListNode prev;
        private ListNode next;

        private ListNode(ListNode p, T i, ListNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    // Declare variables.
    private ListNode sentinel;
    private int size;


    // Creates an empty linked list deque.
    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new ListNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new ListNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T firstItem = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return firstItem;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T lastItem = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return lastItem;
        }

    }

    public T get(int index) {
        int count = 0;
        ListNode ptr = sentinel;
        while(ptr.next != sentinel) {
            ptr = ptr.next;
            if (count == index) {
                return ptr.item;
            }
            count ++;
        }
        return null;
    }

    public T getRecursive(int index) {
        int length = size;
        if (index > length -1) {
            return null;
        } else {
            return traverse(sentinel.next, index);
        }
    }

    public T traverse(ListNode n, int i) {
        if (i == 0) {
            return n.item;
        } else {
            return traverse(n.next, i-1);
        }
    }
}
