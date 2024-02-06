import java.util.Arrays;

public class MyHashMap<K, V> {
    public static final int DEFAULT_CAPACITY = 8;
    private Node<K, V> [] table = new Node[DEFAULT_CAPACITY];
    private int size;

    public V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> node = new Node<>(key, value);
        V oldValue = null;                              //return old value
        if (table[index] == null) {                //empty array element
            table[index] = node;
            size++;
        } else {
            Node<K, V> temp = table[index];
            while (temp.next != null) {
                if (temp.key.equals(key)) {       //equal keys
                    oldValue = temp.value;
                    temp.value = value;
                    break;
                }
                temp = temp.next;
            }
            if (temp.key.equals(key)) {
                temp.value = value;
                oldValue = temp.value;
            } else {
                temp.next = node;
                size++;
            }
        }
        return oldValue;
    }

    public V get(K key) {
        Node<K, V> temp = table[getIndex(key)];
        V res = null;
        while (temp != null) {
            if (temp.key.equals(key)) {
                res = temp.value;
                break;
            } else {
                temp = temp.next;
            }
        }
        return res;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public void remove(K key) {
        Node<K, V> next = table[getIndex(key)];                //table[getIndex(key)] first element linked list
        Node<K, V> prev = null;
        if (table[getIndex(key)].key.equals(key) && next != null) {
            table[getIndex(key)] = null;                                // check first element in linked list
        } else {
            while (next.next != null) {
                if (next.key.equals(key)) {
                    prev = next;
                    prev.next = next.next;
                    break;
                } else {
                    next = next.next;
                }
            }
        }
        size--;
    }
    private int getIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
