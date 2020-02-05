package de.th_rosenheim.ad.lecture03;

import java.util.Stack;

public class MyArrayStack<Item> {
    private Item[] items;  // stack entries;
    private int n;         // stack size;

    public MyArrayStack(int capacity) {
        items = (Item[]) new Object[capacity];
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public void push(Item item) {
        items[n++] = item;
    }
    public Item pop() {
        if (n > 0) {
            return items[--n];
        }
        else {
            return null;
        }
    }
    public Item peek() {
          return items[n-1];
    }
}

