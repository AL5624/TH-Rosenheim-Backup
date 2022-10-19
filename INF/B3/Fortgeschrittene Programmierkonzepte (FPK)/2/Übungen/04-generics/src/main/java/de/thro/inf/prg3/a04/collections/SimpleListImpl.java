package de.thro.inf.prg3.a04.collections;


import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleListImpl<T> implements SimpleList<T> {

    private ListElement<T> head;

    private int size;

    public SimpleListImpl() {
        head = null;
    }

    @Override
    public int size() {
        return size;
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
        size++;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index > this.size() - 1) {
            throw new IndexOutOfBoundsException();
        }

        ListElement<T> item = head;
        int counter = 0;

        while (counter != index) {
            item = item.getNext();
            ++counter;
        }

        return item.getItem();
    }

    @Override
    public void set(int index, T item) throws IndexOutOfBoundsException {
        if (index > this.size() - 1) {
            throw new IndexOutOfBoundsException();
        }

        ListElement<T> current = head;
        int counter = 0;

        while (counter != index) {
            current = current.getNext();
            ++counter;
        }

        current.setItem(item);
    }

    @Override
    public void addDefault(Class<T> clazz) {
        try {
            this.add(clazz.getConstructor().newInstance());
        } catch (Exception ignored) {
        }
    }

    @Override
    public SimpleList<T> makeInstance() {
        return new SimpleListImpl<T>();
    }

    @Override
    public SimpleList<T> filter(Predicate<T> predicate) {
        SimpleList<T> filteredList = new SimpleListImpl<T>();

        ListElement<T> current = head;

        while (current != null) {
            if (predicate.test(current.getItem())) {
                filteredList.add(current.getItem());
            }
            current = current.getNext();
        }

        return filteredList;
    }

    @Override
    public void forEach(Consumer<? super T> consumer) {
        for (T item : this) {
            consumer.accept(item);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator();
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
}

