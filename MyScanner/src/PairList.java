import java.util.*;

public class PairList {
    Pair[] list = new Pair[1];
    int s = 0;

    public void in(int x, int y, int z) {
        if (s == list.length) {
            list = Arrays.copyOf(list, 2 * list.length);
        }
        list[s] = new Pair(x, y, z);
        s++;
    }

    public Pair returnElem(int i) {
        return list[i];
    }

    public int returnSize() {
        return s;
    }
    public void reload(int id, Pair x) {
        list[id] = x;
    }
}