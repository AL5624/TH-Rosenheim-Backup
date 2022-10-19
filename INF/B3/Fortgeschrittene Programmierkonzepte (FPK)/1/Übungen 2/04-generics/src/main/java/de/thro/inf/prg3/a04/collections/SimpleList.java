package de.thro.inf.prg3.a04.collections;


import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleList<T> implements SimpleListInterface<T>
{
    private ListElement<T> head;

    private int size;

    public SimpleList() {
        head = null;
    }

    private class SimpleIterator implements Iterator<T> {

        private ListElement<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T tmp = current.getItem();
            current = current.getNext();
            return tmp;
        }
    }

    private static class ListElement<T> {
        T item;

        private ListElement<T> next;

        public ListElement(T item) {
            this.item = item;
            this.next = null;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public ListElement<T> getNext() {
            return next;
        }

        public void setNext(ListElement<T> next) {
            this.next = next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleListInterface<T> filter(Predicate<T> predicate)
    {
        SimpleListInterface<T> newList = new SimpleList<T>();

        for (T item: this)
        {
            if (predicate.test(item))
            {
                newList.add(item);
            }
        }

        return newList;
    }

    @Override
    public void add(T item) {
        if (head == null) {
            head = new ListElement<T>(item);
        } else {
            ListElement<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new ListElement<T>(item));
        }
        ++size;
    }

    @Override
    public T get(int index)
    {
        ListElement<T> current = head;
        int i = 0;
        while (i != index && current != null)
        {
            current = current.getNext();
            ++i;
        }

        if (current == null) return null;

        return current.getItem();
    }

    @Override
    public void set(int index, T item) throws IndexOutOfBoundsException
    {
        ListElement<T> current = head;
        int i = 0;

        if (index == 0)
        {
            ListElement<T> next = head.getNext();
            head = new ListElement<T>(item);
            head.setNext(next);
            return;
        }
        else while (i != index - 1 && current != null)
        {
            current = current.getNext();
            ++i;
        }

        if (current != null)
        {
            ListElement<T> next = current.getNext().getNext();
            current.setNext(new ListElement<T>(item));
            current.getNext().setNext(next);
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator();
    }
}

