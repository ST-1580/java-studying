import java.util.*;

public class IntList {
    private int[] list = new int[1];
    private int s = 0;

    public void in(int x) {
        if (s == list.length) {
            list = Arrays.copyOf(list, 2 * list.length);
        }
        list[s] = x;
        s++;
    }

    public int returnElem(int i) {
        return list[i];
    }
}
