package queue;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedQueue extends AbstractQueue implements Queue {

    private class Node {
        private Object element;
        private Node next;

        public Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node tail;
    private Node head;
    private Node iterator;

    public void enqueueElement(Object element) {
        Node curr = new Node(element, null);
        if (isEmpty()) {
            head = tail = curr;
            return;
        }
        tail.next = curr;
        tail = curr;
    }

    public Object dequeueElement() {
        Object ans = head.element;
        head = head.next;
        return ans;
    }

    public Object element() {
        return head.element;
    }

    public void clearQueue() {
        head = tail = null;
    }

    public Queue create() {
        iterator = head;
        return new LinkedQueue();
    }

    protected boolean hasNext() {
        return iterator != null;
    }

    protected Object next() {
        Object ans = iterator.element;
        iterator = iterator.next;
        return ans;
    }

}
