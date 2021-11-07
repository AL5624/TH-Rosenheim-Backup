package de.thro.inf.prg3.a04.collections;

public abstract class CollectionsUtility {
    public static <T extends Comparable<T>> void sort(SimpleListInterface<T> simpleList) {
        boolean isSorted = false;

        while (!isSorted)
        {
            isSorted = true;
            for (int i = 0; i < simpleList.size() - 1; i++)
            {
                T next = simpleList.get(i + 1);
                int result = simpleList.get(i).compareTo(next);
                if (result < 0)
                {
                    isSorted = false;
                    simpleList.set(i + 1, simpleList.get(i));
                    simpleList.set(i, next);
                }
            }
        }
    }
}
