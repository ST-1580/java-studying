import java.io.*;

public class MyScanner {
    String fileName;
    Reader in;

    public MyScanner(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        try {
            if (fileName == null) {
                in = new BufferedReader(new InputStreamReader(System.in));
            } else {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "utf8"));
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("utf8 error" + e);
        }

    }

    public MyScanner(InputStream is, String fileName) throws IOException {
        in = new InputStreamReader(is);
    }

    private int lastAnswInt;
    private String lastAnswWord;
    private int lastSep = 0, currSep = 0;
    private boolean first = true;

    private boolean isTokensSeparatorInt(int z) {
        if ((z < 48 || z > 57) && z != 45) {
            return true;
        } else {
            return false;
        }
    }

    public int getInt() {
        return lastAnswInt;
    }

    public boolean hasNextIntInLine() throws IOException {
        return hasNextInLine(0);
    }

    public boolean hasNextInt()  throws IOException {
        return hasNext(0);
    }

    ///////////////////////////////WORD/////////////////////////////////

    private boolean isTokensSeparatorWord(int z) {
        char c = (char) z;
        if (!(Character.isLetter(c)) && c != '\'' && !(Character.getType(c) == Character.DASH_PUNCTUATION)) {
            return true;
        } else {
            return false;
        }
    }

    public String getWord() {
        return lastAnswWord;
    }

    public boolean hasNextWordInLine() throws IOException {
        return hasNextInLine(1);
    }

    public boolean hasNextWord()  throws IOException {
        return hasNext(1);
    }

    /////////////////////////////ALL////////////////////////////////////

    private boolean hasNext(int p) throws IOException {
        if (lastSep == 3) {
            in.close();
            return false;
        }
        if (first) {
            first = false;
            return true;
        }
        StringBuilder s = new StringBuilder();
        int z, pr = 0;
        String a = "";
        int b = 1;
        while (true) {
            z = in.read();
            char c = (char) z;
            if (p == 0 &&  z != -1 && isTokensSeparatorInt(z) && !(Character.isWhitespace(c))) {
                return false;
            }
            if ((p == 0 && isTokensSeparatorInt(z)) || (p == 1 && isTokensSeparatorWord(z))) {
                if (!(s.toString().isEmpty())) {
                    if (p == 1) {
                        a = s.toString();
                    } else {
                        b = Integer.parseInt(s.toString());
                    }
                    pr = 1;
                }
                if (z == -1) {
                    lastSep = currSep;
                    currSep = 3;
                    return false;
                } else if  (c == '\n' || c == '\r') {
                    lastSep = currSep;
                    currSep = 2;
                } else if (c == ' ' || isTokensSeparatorWord(z)) {
                    lastSep = currSep;
                    currSep = 1;
                }
                if (!(s.toString().isEmpty()) || currSep != 1) {
                    break;
                }
            } else {
                s.append(Character.toString(c));
            }
        }
        if (pr == 1) {
            if (p == 1) {
                lastAnswWord = a;
            } else {
                lastAnswInt = b;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean hasNextInLine(int p) throws IOException {
        boolean t = false;
        if (currSep <= 1) {
            if (p == 1) {
                t = hasNextWord();
            } else {
                t = hasNextInt();
            }
        }
        if (!t && !first) {
            skipLine();
            lastSep = currSep;
            currSep = 0;
            first = true;
            return false;
        } else {
            return true;
        }
    }

    private void skipLine() throws IOException {
        int z = in.read();
        while (!isTokensSeparatorWord(z)) {
            z = in.read();
        }
    }
}
