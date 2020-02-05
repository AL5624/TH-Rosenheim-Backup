package de.th_rosenheim.ad.lecture07;

// adapted from Sedgewick et al.
public class BruteForceSearch {

    // return offset of first match or n if no match
    public static int search(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();

        for (int i = 0; i <= n - m; i++) {  // todo: schleifenabbruch
            int j;
            for (j = 0; j < m; j++) {
                if (txt.charAt(i+j) != pat.charAt(j))
                    break;
            }
            if (j == m) return i;            // todo: found at offset i
        }
        return n;                            // not found
    }

    public static void main(String[] args) {
        String pat = "rab";
        String txt = "abacadabrabracabracadabrabrabracad";
        int offset = search(pat, txt);
        // print results
        System.out.println("Found pattern at position:    " + offset);
    }
}
