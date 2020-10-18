import java.io.*;
import java.util.*;

class SortFirst implements Comparator<MyStructFirst> {
    public int compare(MyStructFirst p1, MyStructFirst p2) {
        if (p1.id.returnElem(0).all > p2.id.returnElem(0).all) {
            return 1;
        }
        if (p1.id.returnElem(0).all < p2.id.returnElem(0).all) {
            return -1;
        }
        return 0;
    }
}

public class WordStatFirstIndex {
    public static void main (String[] args) throws FileNotFoundException {
        String in = args[0];
        String output = args[1];
        MyScanner input = new MyScanner(in);
        PairS par = new PairS("", 1, 1);
        Map<String, NodeFirst> hm = new HashMap<>();
        NodeFirst n = null;
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "utf8"))) ){
            int stringNumber = 1, allWords = 0;
            while(input.hasNextWord()) {
                int q = 1;
                while (input.hasNextWordInLine()) {
                    String word = input.getWord();
                    word = word.toLowerCase();
                    if (hm.get(word) == null) {
                        n = new NodeFirst(1, new PairList());
                        n.id.in(q, stringNumber, allWords);
                        hm.put(word, n);
                    } else {
                        n = new NodeFirst(hm.get(word).cnt + 1, new PairList());
                        n.id = hm.get(word).id;
                        if (n.id.returnElem(n.id.returnSize() - 1).str != stringNumber) {
                            n.id.in(q, stringNumber, allWords);
                        }
                        hm.put(word, n);
                    }
                    q++; allWords++;
                }
                stringNumber++;
            }
            List<MyStructFirst> a = new ArrayList<MyStructFirst>();
            for (Map.Entry<String, NodeFirst> i : hm.entrySet()) {
                a.add(new MyStructFirst(i.getKey(), i.getValue().cnt, i.getValue().id));
            }
            Collections.sort(a, new SortFirst());
            for (int i = 0; i < a.size(); i++) {
                out.print(a.get(i).word);
                out.print(" ");
                out.print(a.get(i).cnt);
                for (int j = 0; j < a.get(i).id.returnSize(); j++) {
                    out.print(" ");
                    out.print(a.get(i).id.returnElem(j).id);
                }
                out.println();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }
}
