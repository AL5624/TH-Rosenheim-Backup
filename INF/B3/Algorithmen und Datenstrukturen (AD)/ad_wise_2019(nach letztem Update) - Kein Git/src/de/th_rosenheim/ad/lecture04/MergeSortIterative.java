package de.th_rosenheim.ad.lecture04;

public class MergeSortIterative {

    // This class should not be instantiated.
    private MergeSortIterative() { }

    /*
    * Merge two sorted sublists into a single list
     * @param array a list containing two sorted sublists
     * @param left start index of first list
     * @param middle end index of first list
     * @param right end index of second list
     */
    private static void merge(Comparable[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Comparable[] leftArray = new Comparable[n1];   // generate auxiliary arrays
        Comparable[] rightArray = new Comparable[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = 0;

        // sentinel values allowing to skip if one of the two arrays is empty
        for (; k <= right && i < leftArray.length && j < rightArray.length;  k++) {
             if (less(leftArray[i], rightArray[j])) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
        }

        // for one side: i or j pointer has reached the end
        // at maximum one of these will get called in the end
        for (int ii = i; ii < leftArray.length; ii++) {
            array[k] = leftArray[ii];
        }
        for (int jj = j; jj < rightArray.length; jj++) {
            array[k] = rightArray[jj];
        }
    }



    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        // TODO
        for (int len = 1; len < n; len *= 2) {
            for (int left = 0; left < n - len; left += len + len) {
                int middle  = left + len-1;
                int right = Math.min(left + len + len - 1, n - 1);
                merge(a, left, middle, right);
            }
        }
    }

    /***********************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // Test function
    public static void main(String[] args) {
        Integer[] a = {5, 2, 4, 7, 1, 3, 2, 6};
        MergeSortRecursive.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }


}
