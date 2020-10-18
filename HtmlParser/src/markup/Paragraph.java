package markup;

import java.net.http.HttpClient;
import java.security.PublicKey;
import java.util.*;

public class Paragraph extends AbstractMarkdown implements Item {
    public Paragraph(List<MrkDwn> body) {
        super("", "", body);
    }
}
