package markup;

import java.util.List;

public class OrderedList extends AbstractItem{
    public OrderedList(List<X> body) {
        super("ol", "ol", body);
    }
}
