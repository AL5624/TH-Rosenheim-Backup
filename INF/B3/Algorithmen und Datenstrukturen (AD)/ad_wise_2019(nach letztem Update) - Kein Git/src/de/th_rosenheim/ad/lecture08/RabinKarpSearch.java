package de.th_rosenheim.ad.lecture07;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class RabinKarpSearch {

    private static final int R = 256;     // the radix, i.e. numbers of characters -> 256 for ASCII

    private String pat;      // the pattern  // needed only for Las Vegas
    private long patHash;    // pattern hash value
    private int m;           // pattern length
    private long q;          // a large prime, small enough to avoid long overflow
    private long rm;         // R^(M-1) % Q



    public RabinKarpSearch(String pat) {
        this.pat = pat;      // save pattern (needed only for Las Vegas)
        m = pat.length();

        // compute a prime number
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        q = prime.longValue();

        // precompute R^(m-1) % q for use in removing leading digit
        rm = 1;
        for (int i = 1; i <= m-1; i++)
            rm = (R * rm) % q;
        patHash = hash(pat, m);
    }

    // Compute hash for key[0..m-1].
    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++)
            h = (R * h + key.charAt(j)) % q;
        return h;
    }

    // Las Vegas version: does pat[] match txt[i..i-m+1] ?
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++)
            if (pat.charAt(j) != txt.charAt(i + j))
                return false;
        return true;
    }

    // Monte Carlo version: always return true
    // private boolean check(int i) {
    //    return true;
    //}


    public int search(String txt) {
        int n = txt.length();
        if (n < m) return n;
        long txtHash = hash(txt, m);

        // check for match at offset 0
        if ((patHash == txtHash) && check(txt, 0))
            return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + q - rm *txt.charAt(i-m) % q) % q;
            txtHash = (txtHash*R + txt.charAt(i)) % q;

            // match
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(txt, offset))
                return offset;
        }

        // no match
        return n;
    }


    public static void main(String[] args) throws IOException {
        /*
        String txt = "FINDINAHAYSTACKNEEDLEINA";
        String pat = "NEEDLE";

        RabinKarpSearch searcher = new RabinKarpSearch(pat);
        int offset = searcher.search(txt);

        // print results
        System.out.println("Found match at position: " + offset);
        */


        Path path = Paths.get("pattern.txt");
        String pat = Files.readString(path);
        path = Paths.get("text.txt");
        String txt = Files.readString(path);

        RabinKarpSearch search = new RabinKarpSearch(pat);
        long time = System.currentTimeMillis();
        int offset = search.search(txt);
        time = System.currentTimeMillis() - time;
        System.out.println("duration= " + time);


    }
}
