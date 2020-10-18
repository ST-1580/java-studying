import java.io.*;
import java.util.*;

class SortLast implements Comparator<MyStructFirst> {
    public int compare(MyStructFirst p1, MyStructFirst p2) {
        return p1.word.compareTo(p2.word);
    }
}

public class WordStatSortedLastIndex {
    public static void main(String[] args) throws FileNotFoundException {
        String in = args[0];
        String output = args[1];
        MyScanner input = new MyScanner(in);
        PairS par = new PairS("", 1, 1);
        Map<String, NodeFirst> hm = new TreeMap<>();
        NodeFirst n = null;
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "utf8")))) {
            int stringNumber = 1, allWords = 0;
            while (input.hasNextWord()) {
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
                        } else {
                            n.id.reload(n.id.returnSize() - 1, new Pair(q, stringNumber, allWords));
                        }
                        hm.put(word, n);
                    }
                    q++;
                    allWords++;
                }
                stringNumber++;
            }
            for (Map.Entry<String, NodeFirst> i : hm.entrySet()) {
                out.print(i.getKey());
                out.print(" ");
                out.print(i.getValue().cnt);
                for (int j = 0; j < i.getValue().id.returnSize(); j++) {
                    out.print(" ");
                    out.print(i.getValue().id.returnElem(j).id);
                }
                out.println();
            }
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }
}
