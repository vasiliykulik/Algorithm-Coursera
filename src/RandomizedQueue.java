import edu.princeton.cs.algs4.StdRandom;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Vasiliy Kylik on 15.10.2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int N;


    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        autoEnlarge();
        array[N++] = item;
    }

    private void autoEnlarge() {
        if (N == array.length) resize(2 * array.length);
    }

    public Item dequeue() {
        assertNotEmpty();
        int index = randomIndex();
        Item item = array[index];
        array[index] = array[N - 1];
        array[N - 1] = null;
        N--;
        autoShrink();
        return item;
    }

    private void assertNotEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private void autoShrink() {
        if (N > 0 && N == array.length / 4) resize(array.length / 2);
    }

    private int randomIndex() {
        return StdRandom.uniform(0, N);
    }

    public Item sample() {
        assertNotEmpty();
        return array[randomIndex()];
    }

    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }


    private class RandomArrayIterator implements Iterator<Item> {
        private Item[] r;
        private int i;

        public RandomArrayIterator() {
            copyQueue();
            StdRandom.shuffle(r);
        }

        private void copyQueue() {
            r = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                r[i] = array[i];
            }
        }

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return r[i++];
        }
    }
}
