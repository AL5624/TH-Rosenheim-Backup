package de.th_rosenheim.ad.uebung05;

public class CountingSort {

    /**
     * @param input integer array given as input
     * @param k     defines maximum integer value in input; all integers must in range [0..k]
     * @return input array is sorted in a stable (!!) manner
     */
    public static Integer[] sort(Integer[] input, Integer k) {

        Integer[] output = new Integer[input.length];    // array that will take sorted result.

        // count how often each value occurs
        int[] count = new int[k + 1];
        for (Integer j : input) {
            count[j]++;
        }

        // count in "count[j]" how many elements are <= j
        for (int j = 1; j <= k; j++) {
            count[j] = count[j] + count[j - 1];
        }

        // iterate over input, put each element into correct position of result array
        // using the computed cumulated frequencies in count
        for (int j = input.length - 1; j >= 0; j--) {
            int currentInt = input[j];
            output[count[currentInt] - 1] = currentInt;
            count[currentInt]--;
        }

        return output;
    }

}
