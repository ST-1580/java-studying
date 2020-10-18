package markup;

public class Text implements MrkDwn {
    String s;

    public Text(String s) {
        this.s = s;
    }

    public void toHtml(StringBuilder ss) {
        ss.append(s);
    }

    public void toMarkdown(StringBuilder ss) {
        ss.append(s);
    }
}
