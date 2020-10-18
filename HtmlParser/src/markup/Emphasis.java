package markup;

import java.util.List;

public class Emphasis extends AbstractMarkdown implements MrkDwn  {

    public Emphasis(List<MrkDwn> body) {
        super("*","em", body);
    }

}
