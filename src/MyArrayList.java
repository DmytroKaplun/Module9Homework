import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> {
        public static final int DEFAULT_CAPACITY = 10;
        private T[] data;
        private int size;


    public MyArrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public T get(int index) {
            Objects.checkIndex(index, size);
            return data[index];
    }

    public void add(T value) {
            if (size == data.length) {
                int newCapacity = data.length * 2;
                data = Arrays.copyOf(data, newCapacity);
            }
            data[size] = value;
            size++;
    }

    public int size() {
        return size;
    }

    public T remove(int index) {
            Objects.checkIndex(index, size);
            T res = data[index];
            T[] temp = (T[])new Object[data.length];
            for (int j = 0, k = 0; j < data.length; j++) {
                if (j == index) {
                    continue;
                }
                temp[k++] = data[j];
            }
            data = Arrays.copyOf(temp, temp.length);
            size--;
            return res;
        }
        public void clear() {
            Arrays.fill(data, null);
            size = 0;
        }

        @Override
        public String toString() {
            return "MyArrayList{" +
                    "data=" + Arrays.toString(Arrays.copyOf(data, size)) +
                    ", size=" + size +
                    '}';
        }
    }

