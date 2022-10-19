package de.thro.inf.prg3.a04.collections;


import java.util.function.Consumer;

public abstract class CollectionsUtility {

    public static <T extends Comparable<T>> void sort(SimpleList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                T tmp = list.get(j);
                if (tmp.compareTo(list.get(j + 1)) > 0) {
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                }
            }
        }
    }

}
