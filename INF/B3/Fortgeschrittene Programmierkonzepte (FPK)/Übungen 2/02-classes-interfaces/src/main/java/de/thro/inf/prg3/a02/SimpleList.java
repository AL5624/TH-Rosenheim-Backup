package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleList implements SimpleListInterface, Iterable
{
    private Element head = null;
    private Element tail = null;
    private int size = 0;

    @Override
    public Iterator iterator()
    {
        return new SimpleListIterator(head);
    }

    private static class Element {
        private Object item = null;
        private Element next = null;

        public Element(Object o) {
            item = o;
        }

        public void setNext(Element e) {
            next = e;
        }

        public Element getNext() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Object getItem() {
            return item;
        }
    }

    private class SimpleListIterator implements Iterator
    {
        private Element current = null;

        public SimpleListIterator(Element head) {
            current = head;
        }

        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        @Override
        public Object next()
        {
            Element tmp = current;
            current = current.getNext();
            return tmp.getItem();
        }
    }

    @Override
    public void add(Object o)
    {
        ++this.size;
        Element element = new Element(o);

        if (head == null) {
            head = tail = element;
        }
        else {
            tail.setNext(element);
            tail = element;
        }
    }

    @Override
    public int size()
    {
        return this.size;
    }

    @Override
    public SimpleListInterface filter(SimpleFilter filter)
    {
        SimpleList newSimpleList = new SimpleList();

        for (Object e : this) {
            if (filter.include(e)) {
                newSimpleList.add(e);
            }
        }

        return newSimpleList;
    }
}
