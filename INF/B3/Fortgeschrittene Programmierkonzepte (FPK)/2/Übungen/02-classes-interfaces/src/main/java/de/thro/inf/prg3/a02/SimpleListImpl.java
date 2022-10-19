package de.thro.inf.prg3.a02;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {
    private Element head = null;

    @Override
    public void add(Object o) {
        Element newElement = new Element(o);

        if (head == null) {
            head = newElement;
            return;
        }

        Element current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(newElement);
    }

    @Override
    public int size() {
        int count = 0;

        Element current = head;

        while (current != null) {
            ++count;
            current = current.getNext();
        }

        return count;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleListImpl result = new SimpleListImpl();

        for (Object o : this) {
            if (filter.include(o)) {
                result.add(o);
            }
        }

        return result;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIteratorImpl();
    }

    @Getter
    private static class Element {
        private final Object item;
        @Setter
        private Element next = null;

        public Element(Object item) {
            this.item = item;
        }
    }

    private class SimpleIteratorImpl implements Iterator<Object> {
        private Element current = SimpleListImpl.this.head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            Object item = current.getItem();
            current = current.getNext();
            return item;
        }
    }
}
