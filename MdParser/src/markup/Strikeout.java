package markup;

import java.util.List;

public class Strikeout extends AbstractMarkdown implements MrkDwn {
    public Strikeout(List<MrkDwn> body) {
        super("~", "s", body);
    }

}
