package de.th_rosenheim.ad.uebung13;

public class Levenshtein {

    private final String x;
    private final String y;


    // table to store the Levenshtein distances for substrings
    private int[][] ldist;

    // constructor: computes Levenshtein distances for word x and word y
    public Levenshtein(String x, String y) {
        this.x = x;
        this.y = y;

        ldist = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++)    // initialize first row
            ldist[0][i] = i;


        for (int i = 1; i <= x.length(); i++) {  // iterate over each row
            ldist[i][0] = i;                    // initialize first column
            for (int j = 1; j <= y.length(); j++) {
                ldist[i][j] = ldist[i - 1][j - 1];
                if (x.charAt(i - 1) != y.charAt(j - 1)) {
                    ldist[i][j]++;

                    if (ldist[i][j] > ldist[i - 1][j] + 1)
                        ldist[i][j] = ldist[i - 1][j] + 1;

                    if (ldist[i][j] > ldist[i][j - 1] + 1)
                        ldist[i][j] = ldist[i][j - 1] + 1;
                }
            }
        }
    }

    // returns Levenshtein distances for Substrings X_i and Y_i (nomenclature, see lecture)
    public int getDistanceForSubstrings(int i, int j) {
        return ldist[i][j];
    }

    public int getLevenshteinDistance() {
        return ldist[x.length()][y.length()];
    }

    public void printLevenshteinTable() {
        for (int i = 0; i < ldist.length; i++) {
            for (int j = 0; j < ldist[i].length; j++) {
                System.out.print(ldist[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

