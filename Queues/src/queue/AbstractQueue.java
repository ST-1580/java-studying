package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue {
    protected int size;

    public void enqueue(Object element){
        enqueueElement(element);
        size++;
    }
    protected abstract void enqueueElement(Object element);

    public Object dequeue() {
        size--;
        return dequeueElement();
    }
    protected abstract Object dequeueElement();

    public int size() {
        return size;
    }
    
    public void clear() {
        size = 0;
        clearQueue();
    }
    protected abstract void clearQueue();

    public boolean isEmpty(){
        return size == 0;
    }

    public Queue map(Function<Object, Object> function) {
        Queue res = create();
        while (hasNext()) {
            res.enqueue(function.apply(next()));
        }
        return res;
    }

    public Queue filter(Predicate<Object> predicate) {
        Queue res = create();
        while(hasNext()) {
            Object element = next();
            if (predicate.test(element)) {
                res.enqueue(element);
            }
        }
        return res;
    }

    // :NOTE: write Iterator or do it in alternative way
    protected abstract boolean hasNext();

    protected abstract Object next();

    protected abstract Queue create();
}
