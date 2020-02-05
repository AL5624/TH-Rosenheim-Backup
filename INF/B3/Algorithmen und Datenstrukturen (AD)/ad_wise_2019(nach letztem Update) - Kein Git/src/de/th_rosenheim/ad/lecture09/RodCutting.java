package de.th_rosenheim.ad.lecture09;

/**
 * Created by Wolfgang Mühlbauer on 07.01.2017.
 */
public class   RodCutting {

    public static int cutRodBottomUp(int[] prices, int length) {
        int[] revenue = new int[length + 1];
        revenue[0] = 0;

        for (int j = 1; j <= length; j++) {     // solve rod cutting problem for rod of length j
            // TODO
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {      // position of cut
                if (max < prices[i-1] + revenue[j-i]) {   // array prices is 0-indexed
                    max = prices[i-1] + revenue[j-i];
                    revenue[j] = max;
                }
            }
        }
        return revenue[length];
    }


    public static int cutRodRec(int[] prices, int length) {
        if (length <= 0)
            return 0;
        // either we will cut it or don't cut it
        // TODO
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, prices[i] + cutRodRec(prices, length -i - 1 ));
        }
        return max;
    }



    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};

        //int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 23, 25, 28, 28, 30, 32, 36, 36, 50, 50, 52, 53, 54, 55, 57, 57, 60, 64, 65, 66, 66, 70, 74, 80, 80};

        int n = prices.length;
        System.out.println("Max profit for length " + n + " is: "
                + cutRodBottomUp(prices, n));

    }


}
