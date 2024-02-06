import java.util.Objects;

public class MyLinkedList<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> temp = getNode(index);
        return temp.value;
    }

    public int size() {
        return size;
    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        Node<T> temp = getNode(index);
        Node<T> prev = temp.previous;
        Node<T> next = temp.next;
        T result = temp.value;

        if (index == 0) {
            first = next;
        }
        if (index > 0 && index < size-1) {
            prev.next = next;
            next.previous = prev;
        }
        if (index == size-1) {
            last = prev;
        }
        size--;
        return result;
    }

    private Node<T> getNode(int index) {
        Node<T> temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void clear() {
        for (Node<T> temp = first; temp != null; ) {
            Node<T> next = temp.next;
            temp.value = null;
            temp.next = null;
            temp.previous = null;
            temp = next;
        }
        first = last = null;
        size = 0;
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        public Node(T value) {
            this.value = value;
        }
    }
}
