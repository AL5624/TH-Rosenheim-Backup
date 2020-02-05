package de.th_rosenheim.ad.uebung13;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevenshteinTest {

    Levenshtein lst;

    @Before
    public void setup() {
        lst = new Levenshtein("levenshtein", "meilenstein");
    }

    @Test
    public void getDistanceForSubstrings() {
        System.out.println("Levenshtein table is");
        lst.printLevenshteinTable();

        // check some intermediate results
        assertEquals(3, lst.getDistanceForSubstrings(3,4));
        assertEquals(6, lst.getDistanceForSubstrings(6,10));

    }

    @Test
    public void getLevenshteinDistance() {
        assertEquals(4, lst.getLevenshteinDistance());
    }
}