package de.th_rosenheim.ad.lecture01;

/**
 * Created by Wolfgang Mühlbauer on 23.09.2018.
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {4, 1, 8, -3, 5, 7};
        InsertionSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }

    }

    public static void sort(int[] array) {
        int key;
        for (int j = 1; j <= array.length - 1; j++) {
            key = array[j];
            int i = j - 1;
            while (i >= 0 && key < array[i]) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;

        }
    }
}
