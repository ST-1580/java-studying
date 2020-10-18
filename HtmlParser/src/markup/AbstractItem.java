package markup;

import java.util.List;

public abstract class AbstractItem extends AbstractEverything implements Item {

    public AbstractItem(String s_m, String s_h, List<X> body) {
        super(s_m, s_h, body);
    }
}
