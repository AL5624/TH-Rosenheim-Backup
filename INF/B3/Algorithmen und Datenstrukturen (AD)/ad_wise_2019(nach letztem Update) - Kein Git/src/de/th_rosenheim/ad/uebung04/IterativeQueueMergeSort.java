package de.th_rosenheim.ad.uebung04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Iterative MergeSort using Queues
 */
public class IterativeQueueMergeSort {

    /**
     * central queue: a queue that stores queues!
     * - contains in the beginning n queues (each with 1 element),
     * - repeatedly merge until only one queue remains
     * Hint: Java Collection Interface "Queue"
     * - "Queue" is only an interface, here a "LinkedList" is always used as its implementation
     * - enqueue: method add(.)
     * - dequeue: method remove(.)
     * - peek:    method peek(.) or element(.) to examine element at head of queue without removing it.
     */
    private static Queue<Queue<Comparable>> centralQueue;


    /**
     * sorts array a. Note: The input array is overwritten with the result.
     * @param a array to be sorted
     */
    public static void sort(Comparable[] a) {
        centralQueue = new LinkedList<Queue<Comparable>>();

        // TODO

        // create and enqueue a dedicated queue for each single element
        for (Comparable c : a) {
            Queue<Comparable> tmp = new LinkedList<>();
            tmp.add(c);
            centralQueue.add(tmp);
        }

        // repeatedly apply the merging operation to the first two queues in central queue (until the latter is empty)
        while (centralQueue.size() > 1) {
            centralQueue.add(merge(centralQueue.remove(), centralQueue.remove()));
        }

        // last remaining element is the result, i.e. the queue/linked linked list in sorted order
        Queue<Comparable> b = centralQueue.remove();
        for (int i = 0; i < a.length; i++)
            a[i] = b.remove();
    }


    /**
     * Merges 2 sorted (!!!) queues. Returns 1 queue that contains all elements of the two input
     * queues in sorted (!) order.
     * @param a first queue (sorted!)
     * @param b second queue (sorted!)
     * @return queue with elements of a and b in sorted order
     */
    private static Queue<Comparable> merge(Queue<Comparable> a, Queue<Comparable> b) {
        Queue<Comparable> c = new LinkedList<Comparable>();
        // TODO
        while (!a.isEmpty() || !b.isEmpty()) {
            if (a.isEmpty()) {
                c.add(b.remove());
            }
            else if (b.isEmpty()) {
                c.add(a.remove());
            }
            // element at head of queue a is smaller than element at head of queue b
            else if (a.peek().compareTo(b.peek()) < 0) {
                c.add(a.remove());
            }
            // otherwise: element at head of queue b must be smaller than element at head of queue a
            else {
                c.add(b.remove());
            }
        }
        return c;
    }

}
