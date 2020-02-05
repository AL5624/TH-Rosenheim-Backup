package de.th_rosenheim.ad.lecture07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

// adapted from Sedgewick et al.
public class BoyerMooreSearch {
    private static final int R = 256;     // the radix, i.e. numbers of characters -> 256 for ASCII
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string


    public BoyerMooreSearch(String pat) {
        this.pat = pat;

        // compute position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;     // initialize: character does not appear in pattern
        for (int j = 0; j < pat.length(); j++)   // TODO
            right[pat.charAt(j)] = j;
    }

    // check if txt occurs within pattern
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {  // TODO
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return n;                       // not found
    }


    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  text the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; n if no such match
     */
    public int search(char[] text) {
        int m = pattern.length;
        int n = text.length;
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return n;                       // not found
    }


    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException {
        /* String pat = "NEEDLE";
        String txt = "FINDINGAHAYSTACKNEEDLEINA";

        BoyerMooreSearch boyermoore1 = new BoyerMooreSearch(pat);
        int offset = boyermoore1.search(txt);


        // print results
        System.out.println("Found pattern at offset" + offset);
*/

        Path path = Paths.get("pattern.txt");
        String pat = Files.readString(path);
        path = Paths.get("text.txt");
        String txt = Files.readString(path);
        //long time = System.currentTimeMillis();

        BoyerMooreSearch boyermoore1 = new BoyerMooreSearch(pat);
        long time = System.currentTimeMillis();
        int offset = boyermoore1.search(txt);
        time = System.currentTimeMillis() - time;
        System.out.println("duration= " + time);



     }


}
