package de.th_rosenheim.ad.uebung01;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {

    public static final int ARRAY_SIZE = 16000;

    public static void main(String[] args) {
        // recommendation: first check correctness with this array
        // int[] a = {35, 22, 10, 51, 48};

        // generate a array of size ARRAY_SIZE that is sorted in descending order (Worst Case)
        int[] a = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            a[ARRAY_SIZE-i-1] = i;
        }
        bubbleSort(a);
        // print out the first ten values
        System.out.print("Ausgabe:");
        for (int i=0; i<10; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }

    public static void bubbleSort (int[] a) {
        long start = System.currentTimeMillis();
        for (int i = 1; i < a.length; i++) {
            for (int j = a.length - 1; j >= i; j--) {
                if (a[j] < a[j-1]) {
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Required time (ms): " + (end-start));
    }
}
