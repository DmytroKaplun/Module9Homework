import java.util.Arrays;

public class MyQueue<T> {
    //public class ArrayDeque<E> extends AbstractCollection<E> implements Deque<E>, Cloneable, Serializable
    public static final int DEFAULT_CAPACITY = 16;
    private T[] elements;
    private int size;

    public MyQueue() {
        elements = (T[])new Object[DEFAULT_CAPACITY];
    }

    public void add(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
        elements[size] = value;
        size++;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    public T peek() {
        return elements[0];
    }

    public T poll() {
        if (size == 0) {
            return null;
        }
        T res = elements[0];
        System.arraycopy(elements, 1, elements, 0, size-1);
        size--;
        return res;
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "elements=" + Arrays.toString(Arrays.copyOf(elements, size)) +
                ", size=" + size +
                '}';
    }
}
