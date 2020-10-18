import java.io.*;
import java.util.*;

class Sort implements Comparator<MyStruct> {
    public int compare(MyStruct p1, MyStruct p2) {
        if (p1.id.returnElem(0) > p2.id.returnElem(0)) {
            return 1;
        }
        if (p1.id.returnElem(0) < p2.id.returnElem(0)) {
            return -1;
        }
        return 0; 
    }
}

class SortCnt implements Comparator<SNode> {
    public int compare(SNode p1, SNode p2) {
        if (p1.cnt > p2.cnt) {
            return 1;
        }
        if (p1.cnt == p2.cnt) {
            if (p1.id > p2.id) {
                return 1;
            }
            if (p1.id < p2.id) {
                return -1;
            }
        }
        if (p1.cnt < p2.cnt) {
            return -1;
        }
        return 0;
    }
}

public class WordStatIndex {
    public static void main (String[] args) throws FileNotFoundException {
        String in = args[0];
        String output = args[1];
        MyScanner input = new MyScanner(in);
        PairS par = new PairS("", 1, 1);
        Map<String, Node> hm = new HashMap<>();
        Node n = null;
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "utf8"))) ) {
            int q = 1;
            while (input.hasNextWord()) {
                while (input.hasNextWordInLine()) {
                    String word = input.getWord();
                    word = word.toLowerCase();
                    if (hm.get(word) == null) {
                        n = new Node(1, new IntList());
                        n.id.in(q);
                        hm.put(word, n);
                    } else {
                        n = new Node(hm.get(word).cnt + 1, new IntList());
                        n.id = hm.get(word).id;
                        n.id.in(q);
                        hm.put(word, n);
                    }
                    q++;
                }

            }
            List<MyStruct> a = new ArrayList<MyStruct>();
            for (Map.Entry<String, Node> i : hm.entrySet()) {
                a.add(new MyStruct(i.getKey(), i.getValue().cnt, i.getValue().id));
            }
            Collections.sort(a, new Sort());

            for (int i = 0; i < a.size(); i++) {
                out.print(a.get(i).word);
                out.print(" ");
                out.print(a.get(i).cnt);
                for (int j = 0; j < a.get(i).cnt; j++) {
                    out.print(" ");
                    out.print(a.get(i).id.returnElem(j));
                }
                out.println();
            }

            out.close();
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }
}
