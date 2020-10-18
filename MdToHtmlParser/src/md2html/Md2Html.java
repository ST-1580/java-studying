package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Md2Html {
    private static int whereEnd(String parseMe, String mark, int pos) {
        int len = parseMe.length();
        boolean bs = false;
        while (pos < len) {
            char ch = parseMe.charAt(pos);
            if (bs) {
                bs = false;
                pos++;
            } else if (ch == '\\') {
                bs = true;
                pos++;
            } else if (ch == mark.charAt(0)) {
                if (isMark(parseMe, mark, pos)) {
                    return pos;
                }
            }
            pos++;
        }
        return -1;
    }

    private static String makeHtml(String s, String mark, int pos) {
        if (mark == "-") {
            return mark;
        }
        int posEnd = whereEnd(s, mark, pos + mark.length());
        if (posEnd == -1) {
            return mark;
        } else {
            String htmlMark = new String();
            if (mark.equals("*")  || mark.equals("_")) {
                htmlMark = "<em>";
            } else if (mark.equals("**") || mark.equals("__")) {
                htmlMark = "<strong>";
            } else if (mark.equals("--")) {
                htmlMark = "<s>";
            } else if (mark.equals("`")) {
                htmlMark = "<code>";
            } else if (mark.equals("++")) {
                htmlMark = "<u>";
            }
            return parsing(s.substring(pos + mark.length(), posEnd), htmlMark);
        }
    }

    private static int changePos(String s, String mark, int pos) {
        int posEnd = whereEnd(s, mark, pos + mark.length());
        if (posEnd == -1) {
            return pos + mark.length();
        } else{
            return posEnd + mark.length();
        }
    }

    private static boolean isMark(String parseMe, String mark, int pos) {
        if (parseMe.charAt(pos) == mark.charAt(0)) {
            if (mark.length() == 1) {
                return true;
            } else {
                if (pos + 1 < parseMe.length() && parseMe.charAt(pos + 1) == mark.charAt(1)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private static int skipLinkName(String s, int pos) {
        int len = s.length();
        while (pos < len) {
            char ch = s.charAt(pos);
            if (ch == '\\') {
                pos += 2;
            } else if (ch == ']') {
                pos++;
                break;
            }
            pos++;
        }
        return pos;
    }

    private static boolean isLink(String s, int pos) {
        int len = s.length();
        pos = skipLinkName(s, pos);
        if (pos == len || s.charAt(pos) != '(') {
            return false;
        }
        pos++;
        while (pos < len) {
            if (s.charAt(pos) == ')') {
                return true;
            }
            pos++;
        }
        return false;
    }

    private static String makeLink(String parseMe, int pos) {
        StringBuilder s = new StringBuilder();
        pos++;
        int len = parseMe.length();
        while (pos < len) {
            char ch = parseMe.charAt(pos);
            if (ch == '\\') {
                pos += 2;
            } else if  (ch == ']') {
                pos++;
                break;
            }
            s.append(ch);
            pos++;
        }
        StringBuilder link = new StringBuilder();
        pos++;
        while (pos < len) {
            char ch = parseMe.charAt(pos);
            if (ch == ')') {
                break;
            }
            link.append(ch);
            pos++;
        }
        StringBuilder res = new StringBuilder();
        res.append("<img alt=\'");
        res.append(s);
        res.append("\' src=\'");
        res.append(link);
        res.append("\'>");
        return res.toString();
    }

    private static int getPos(String parseMe, int pos) {
        int len = parseMe.length();
        pos = skipLinkName(parseMe, pos);
        while (pos < len) {
            char ch = parseMe.charAt(pos);
            if (ch == ')') {
                return pos + 1;
            }
            pos++;
        }
        return -1;
    }

    private static Map<Character, String> take = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    private static String parsing(String parseMe, String markHtml) {
        StringBuilder s = new StringBuilder();
        s.append(markHtml);
        int pos = 0, len = parseMe.length();
        boolean bs = false;
        while (pos < len) {
            char ch = parseMe.charAt(pos);
            if (bs) {
                s.append(ch);
                bs = false;
                pos++;
            } else if (ch == '\\') {
                bs = true;
                pos++;
            } else if (take.containsKey(ch)) {
                s.append(take.get(ch));
                pos++;
            } else if (ch == '-') {
                if (isMark(parseMe, "--", pos)) {
                    s.append(makeHtml(parseMe, "--", pos));
                    pos = changePos(parseMe, "--", pos);
                } else {
                    s.append(makeHtml(parseMe, "-", pos));
                    pos++;
                }
            } else if (ch == '`') {
                s.append(makeHtml(parseMe, "`", pos));
                pos = changePos(parseMe, "`", pos);
            } else if (ch == '_' || ch == '*' || ch == '+') {
                String isMark = String.valueOf(ch);
                if (isMark(parseMe, isMark + isMark, pos)) {
                    s.append(makeHtml(parseMe, isMark + isMark, pos));
                    pos = changePos(parseMe, isMark + isMark, pos);
                } else {
                    s.append(makeHtml(parseMe, isMark, pos));
                    pos = changePos(parseMe, isMark, pos);
                }
            } else if (ch == '!') {
                if (pos < len - 1) {
                    if (parseMe.charAt(pos + 1) == '[') {
                        pos++;
                        if (isLink(parseMe, pos)) {
                            s.append(makeLink(parseMe, pos));
                            pos = getPos(parseMe, pos);
                        } else {
                            s.append("![");
                            pos++;
                        }
                    }
                } else {
                    s.append("!");
                    pos++;
                }
            } else {
                s.append(ch);
                pos++;
            }
        }
        if (!markHtml.equals("")) {
            s.append("</");
            s.append(markHtml.substring(1));
        }
        return s.toString();
    }

    private static String toHtml(String pg) {
        StringBuilder s = new StringBuilder();
        int isH = 0, pos = 0;
        while (pg.charAt(pos) == '#') {
            isH++;
            pos++;
        }
        if (!Character.isWhitespace(pg.charAt(pos))) {
            isH = 0;
            pos = 0;
        }
        if (isH == 0) {
            s.append("<p>");
        } else {
            s.append("<h");
            s.append(isH);
            s.append(">");
        }
        if (pos == 0) {
            s.append(parsing(pg, ""));
        } else {
            s.append(parsing(pg.substring(pos + 1), ""));
        }
        if (isH == 0) {
            s.append("</p>");
        } else {
            s.append("</h");
            s.append(isH);
            s.append(">");
        }
        return s.toString();
    }

    public static void main(String[] args) throws IOException, NullPointerException, AssertionError {
        String fileIn = args[0];
        String fileOut = args[1];
        List<String> body = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        String curr;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileIn)), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut), StandardCharsets.UTF_8)) ) {
            while ((curr = in.readLine()) != null) {
                if (curr.isEmpty()) {
                    if (s.length() != 0) {
                        s.deleteCharAt(s.length() - 1);
                        body.add(s.toString());
                        s = new StringBuilder();
                    }
                } else {
                    s.append(curr);
                    s.append('\n');
                }
            }
            if (s.length() != 0) {
                s.deleteCharAt(s.length() - 1);
                body.add(s.toString());
            }
            for (String output : body) {
                out.write(toHtml(output));
                out.write('\n');
            }
        }
    }
}
