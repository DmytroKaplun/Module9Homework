import java.util.NoSuchElementException;
import java.util.Objects;

public class MyStack<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        public Node(T value) {
            this.value = value;
        }
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);
        Node<T> prev = tail;
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            prev.next = node;
            node.previous = prev;
            tail = node;
        }
        size++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        Node<T> temp = getNode(index);
        Node<T> next = temp.next;
        Node<T> prev = temp.previous;

        if (index == 0) {
            head = next;
        }

        if (index > 0 && index < size-1) {
            prev.next = next;
            next.previous = prev;
        }

        if (index == size-1) {
            tail = prev;
        }
        size--;
    }

    private Node<T> getNode(int index) {
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void clear() {
        for (Node<T> temp = head; temp != null; ) {
            Node<T> next = temp.next;
            temp.value = null;
            temp.next = null;
            temp.previous = null;
            temp = next;
        }
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (tail == null) {
            return null;
        }
        return tail.value;
    }

    public T pop() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = tail;
        T res = node.value;;
        if (tail.previous == null) {
            tail = null;
        } else {
            tail = tail.previous;
        }
        size--;
        return res;
    }
}
