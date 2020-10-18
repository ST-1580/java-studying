package markup;

import java.util.List;

public class UnorderedList extends AbstractItem {
    public UnorderedList(List<X> body) {
        super("ul", "ul", body);
    }
}
