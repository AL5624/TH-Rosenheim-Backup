package de.th_rosenheim.ad.lecture04;

// Code adapted from Sedgewick et al.

import java.util.BitSet;

public class MergeSortRecursive {

    // This class should not be instantiated.
    private MergeSortRecursive() {
    }

    // helper function: is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    /**
     * Merge two sorted sublists into a single list
     * @param array a list containing two sorted sublists
     * @param left start index of first list
     * @param middle end index of first list
     * @param right end index of second list
     */
    private static void merge(Comparable[] array, int left, int middle, int right) {

        int n1 = middle - left + 1;  // num elements in left part
        int n2 = right - middle;     // num elements in right part

        Comparable[] leftArray = new Comparable[n1];
        Comparable[] rightArray = new Comparable[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        // füge Element an Position k (im Ergebnisarray!) ein
        for (; k <= right && i < leftArray.length && j < rightArray.length;  k++) {

            if (less(leftArray[i], rightArray[j])){
                array[k] = leftArray[i];
                i++;
            }
            else {
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
     * call of mergesort on array limited by indices
     * @param array input array, actually a subarray
     * @param left start index on the left
     * @param right end index on the right
     */
    private static void mergeSort(Comparable[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle+1, right);
            merge(array, left, middle, right);
        }
    }

    // wrapper
    public static void sort(Comparable[] a) {
        int left = 0;
        int right = a.length - 1;
        mergeSort(a, left, right);
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





