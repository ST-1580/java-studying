package search;

import java.util.ArrayList;

public class BinarySearch {

    // for all i, j : i < j a[i] > a[j]
    private static int searchWhileFirst(ArrayList<Integer> a, int x) {
        // l < r
        int l = -1, r = a.size();
        while (l + 1 < r) {
            // l + 1 < r
            // arr[l] > x && arr[r] <= x
            int m = (l + r) / 2;
            // l < m && m < r, так как минимально l + 2 == r => l + r = 2 * l + 2 => m = l + 1
            if (a.get(m) > x) {
                // (r_pre - l_pre) / (r_post - l_post) == 2, так как размер массива уменьшается в 2 раза после выбора m
                l = m;
            } else {
                // (r_pre - l_pre) / (r_post - l_post) == 2, так как размер массива уменьшается в 2 раза после выбора m
                r = m;
            }
            // l < r
        }
        // l < r
        return r;
    }
    //res in [0; n], a[res] <= x && a[res - 1] > x

    // l < r && for all i, j : i < j a[i] > a[j] 0 <= l && r <= a.size
    private static int searchRecFirst(ArrayList<Integer> a, int x, int l, int r) {
        // l < r
        if (l + 1 == r) {
            return r;
        }
        // l < r
        // arr[l] > x && arr[r] <= x
        int m = (l + r) / 2;
        // l <= m && m <= r
        if (a.get(m) > x) {
            // (r_pre - l_pre) / (r_post - l_post) == 2, так как размер массива уменьшается в 2 раза после выбора m
            return searchRecFirst(a, x, m, r);
        } else {
            // (r_pre - l_pre) / (r_post - l_post) == 2, так как размер массива уменьшается в 2 раза после выбора m
            return searchRecFirst(a, x, l, m);
        }
    }
    // res in [0; n], a[res] <= x && a[res - 1] > x


    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        int id = Integer.parseInt(args[0]);
        for(int i = 1; i < args.length; ++i) {
            a.add(Integer.parseInt(args[i]));
        }
        System.out.println(searchWhileFirst(a, id));
    }
}
