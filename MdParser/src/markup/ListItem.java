package markup;

import java.util.List;

public class ListItem extends AbstractEverything implements X {
    public ListItem(List<Item> body) {
        super("li", "li", body);
    }
}
