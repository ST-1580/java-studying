import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.List;
import markup.*;

public class Test {
    public static void main(String[] args) {
        Paragraph paragraph = new Paragraph(List.of(/*new Paragraph(List.of())*/));
        StringBuilder ss = new StringBuilder();
        paragraph.toHtml(ss);
        System.out.println(ss);
    }
}
