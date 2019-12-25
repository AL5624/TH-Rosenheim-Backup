public class CountingSort {

    /**
     * @param input integer array given as input
     * @param k     defines maximum integer value in input; all integers must in range [0..k]
     * @return input array is sorted in a stable (!!) manner
     */
    public static Integer[] sort(Integer[] input, Integer k) {
        Integer[] output = new Integer[input.length];    // array that will take sorted result.

        int[] anzahl = new int[k+1];

	   //todo

        for(int i = 0; i < input.length; i++)
        {
            anzahl[input[i]]++;
        }

        for(int i = 1; i < anzahl.length; i++)
        {
            anzahl[i] += anzahl[i-1];
        }

        for(int i = input.length-1; i >= 0; i--)
        {
            int p = input[i];
            output[anzahl[p]-1] = input[i];
            --anzahl[p];
        }





       return output;
    }

}
