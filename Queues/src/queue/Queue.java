package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Queue {
    //element != null
    void enqueue(Object element);
    // q_post = q_pre[element_0, ... , element_(size_pre), element] && size_pre + 1 = size_post

    // size > 0
    Object dequeue();
    // q_post = q_pre[element_1, ... , element_(size_pre)] && size_pre - 1 = size_post

    // size > 0
    Object element();
    // size > 0 && q_pre == q_post

    // size >= 0
    int size();
    // size >= 0 && q_pre == q_post

    // size >= 0
    boolean isEmpty();
    // size >= 0 && q_pre == q_post

    // size >= 0
    void clear();
    // size == 0 && q_post = []

    //size >= 0 && function != null && f(q) != null
    Queue map(Function<Object, Object> function);
    // q_post = q_pre[f(element_0, ... , f(element_(size_pre))] && size_pre == size_post

    //size >= 0 && predicate != null
    Queue filter(Predicate<Object> predicate);
    // all elements from q_post exists in q_pre
}
