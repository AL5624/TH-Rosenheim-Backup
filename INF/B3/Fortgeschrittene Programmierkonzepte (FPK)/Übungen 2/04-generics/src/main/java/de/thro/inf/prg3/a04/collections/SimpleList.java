package de.thro.inf.prg3.a04.collections;


import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleList<T> implements SimpleListInterface<T>
{
    private ListElement head;

    private int size;

    public SimpleList() {
        head = null;
    }

    private class SimpleIterator implements Iterator {

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

        private ListElement next;

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

        public ListElement getNext() {
            return next;
        }

        public void setNext(ListElement next) {
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
            head = new ListElement(item);
        } else {
            ListElement current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new ListElement(item));
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

        return current.getItem();
    }

    @Override
    public void set(int index, T item) throws IndexOutOfBoundsException
    {
        ListElement<T> current = head;
        int i = 0;
        while (i != index -1 && current != null)
        {
            current = current.getNext();
            ++i;
        }

        if (current != null)
        {
            ListElement<T> next = current.getNext().getNext();
            current.setNext(new ListElement(item));
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

