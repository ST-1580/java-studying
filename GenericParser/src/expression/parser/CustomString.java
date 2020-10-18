package expression.parser;

public class CustomString {
    private final String data;
    private int position = -1;

    public CustomString(String data) {
        this.data = data;
    }


    public char next() {
        position++;
        return data.charAt(position);
    }

    public boolean hasNext() {
        return position < data.length() - 1;
    }

    public int getPos() {
        return position;
    }

    public int length() {
        return data.length();
    }

    public char giveElem(int i) {
        return data.charAt(i);
    }
}
