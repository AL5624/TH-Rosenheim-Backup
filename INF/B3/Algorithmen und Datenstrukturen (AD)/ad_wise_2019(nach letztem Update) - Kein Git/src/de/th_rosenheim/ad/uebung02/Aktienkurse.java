package de.th_rosenheim.ad.uebung02;

/**
 * Created by muwo522 on 14.10.2016.
 */
public class Aktienkurse {

    // absoluter Kurs
    public static final int[] changes = {1, -5, 4, 2, -7, 3, 6, -1, 2, -4, 7, -10, 2, 6, 1, -3};


    public static void main(String[] args) {

        Integer maxSum = Integer.MIN_VALUE; // keeps sum of maximum subarray (that is already known at current position)
        int maxLow = 0;                        // - left index
        int maxHigh = 0;                       // - right index

        Integer curSum = Integer.MIN_VALUE; // keeps maximum sum of all subarrays that end at current position
        int curLow = 0;                     // - left index

        for (int pos = 0; pos < changes.length; pos++) {   // scanline: read from left to right

            if (curSum > 0) {
                // if curSum was positive at last position, max. subarray that ends at this position also contains previous element
                curSum = curSum + changes[pos];
            }
            else {
                // if curSum was negative at last position: max. subarray that ends at this position is just current element               curLow = i;
                curLow = pos;
                curSum = changes[pos];
            }

            if (curSum > maxSum) {
                // update sum of maximum subarray and keep track where it starts and ends.
                maxSum = curSum;
                maxLow = curLow;
                maxHigh = pos;
            }
        }


        // Ausgabe
        System.out.println("Ideales Szenario:");
        System.out.println("- Einkaufstag:\t" + (maxLow + 1));
        System.out.println("- Verkaufstag:\t" + (maxHigh + 1));
        int sum = 0;
        for (int i=maxLow; i<=maxHigh; i++) {
            sum += changes[i];
        }
        System.out.println("- Verdienst:\t" + sum);

    }

}
