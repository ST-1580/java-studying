package queue;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue implements Queue {
    private int tail;
    private int head;
    private Object[] elements = new Object[1];
    private int iterator;

    public void enqueueElement(Object element) {
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        if (tail == head) {
            ensureCapacity();
        }
    }

    private void ensureCapacity() {
        Object[] curr = new Object[2 * elements.length];
        tail = elements.length;
        System.arraycopy(elements, head, curr, 0, elements.length - head);
        System.arraycopy(elements, 0, curr, elements.length - head, head);
        elements = curr;
        head = 0;
    }

    public Object dequeueElement() {
        Object ans = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return ans;
    }

    public Object element() {
        return elements[head];
    }

    public void clearQueue() {
        elements = new Object[1];
        tail = 0;
        head = 0;
    }

    public Queue create() {
        iterator = 0;
        return new ArrayQueue();
    }

    protected boolean hasNext() {
        return iterator < size();
    }

    protected Object next() {
        Object ans = elements[(iterator + head) % elements.length];
        iterator++;
        return ans;
    }
}
