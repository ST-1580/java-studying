package markup;

import java.util.List;

public class Strong extends AbstractMarkdown  implements MrkDwn  {
    public Strong(List<MrkDwn> body) {
        super("__", "strong", body);
    }
}

