package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleList implements SimpleListInterface
{
    private Element head = null;
    private Element last = null;
    private int size = 0;

    private static class Element {
        private Object element = null;
        private Element next = null;

        public Element(Object o) {
            this.element = o;
        }

        public void setNext(Element e) {
            this.next = e;
        }

        public Element getNext() {
            return this.next;
        }

        public boolean hasNext() {
            return this.next != null;
        }
    }

    private class Iterable implements java.lang.Iterable {
        @Override
        public Iterator iterator()
        {
            return null;
        }
    }

    @Override
    public void add(Object o)
    {
        ++this.size;
        Element element = new Element(o);

        if (head == null) {
            this.head = element;
            this.last = element;
        }
        else {
            this.last.setNext(element);
            this.last = element;
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
        return null;
    }
}
