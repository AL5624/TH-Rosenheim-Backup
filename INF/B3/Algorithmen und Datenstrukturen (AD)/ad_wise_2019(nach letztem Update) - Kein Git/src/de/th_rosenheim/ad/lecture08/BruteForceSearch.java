package de.th_rosenheim.ad.lecture07;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static void main(String[] args) throws IOException {

        /*
        String pat = "rab";
        String txt = "abacadabrabracabracadabrabrabracad";
        int offset = search(pat, txt);
        // print results
        System.out.println("Found pattern at position:    " + offset);
        */

        Path path = Paths.get("pattern.txt");
        String pat = Files.readString(path);
        path = Paths.get("text.txt");
        String txt = Files.readString(path);

        long time = System.currentTimeMillis();
        int offset = search(pat, txt);
        time = System.currentTimeMillis() - time;
        System.out.println("duration= " + time);


    }
}
