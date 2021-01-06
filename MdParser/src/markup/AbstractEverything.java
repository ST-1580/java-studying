package markup;

import java.util.List;

public abstract class AbstractEverything implements HTML {
    String s_m, s_h;
    List<? extends HTML> body;

    public AbstractEverything(String s_m, String s_h, List<? extends HTML> body) {
        this.s_m = s_m;
        this.s_h = s_h;
        this.body = body;
    }

    public void toMarkdown(StringBuilder ss) {
        ss.append(s_m);
        for (HTML m : body) {
            m.toMarkdown(ss);
        }
        ss.append(s_m);
    }

    public void toHtml(StringBuilder ss) {
        if (!s_h.isEmpty()) {
            ss.append("<");
            ss.append(s_h);
            ss.append(">");
        }
        for (HTML m : body) {
            m.toHtml(ss);
        }
        if (!s_h.isEmpty()) {
            ss.append("</");
            ss.append(s_h);
            ss.append(">");
        }
    }
}
