import java.util.Iterator;

public class SimpleListImpl implements SimpleList, Iterable
{


    @Override
    public Iterator<Element> iterator()
    {
        return new MySimpleIterator();
    }

    private static class Element<E>
    {
       private E item;

       private Element next = null;

       public Element (E item)
        {
            this.item = item;
        }

       @Override
       public String toString()
        {
            return this.item.toString();
        }

       @Override
       public boolean equals(Object o)
       {
           if (this == o) return true;
           if (this == null) return false;

           if(this.item.getClass() != o.getClass())return false;

           return this.item.equals(o);
       }
    }

    private class MySimpleIterator implements Iterator<Element>
    {
        private Element e = null;

        @Override
        public boolean hasNext()
        {
            if(e == null && head != null) return true;
            else if (head == null) return false;
            return e.next != null;
        }

        @Override
        public Element next()
        {
            if (e == null) e = head;
            else e = e.next;
            return e;
        }
    }

    private Element head = null;

    @Override
    public void add(Object item)
    {
        if (head == null)
        {
            head = new Element(item);
        }
        else
        {
            Element e = head;
            while(e.next != null)
            {
                e = e.next;
            }
            e.next = new Element(item);

            /*
            Element e = new Element(item);
            e.next = head;
            head = e;
            */
        }

    }

    @Override
    public int size()
    {
        int i = 0;
        Element e = head;
        while(e != null)
        {
            ++i;
            e = e.next;
        }

        return i;
    }

    public void printList()
    {
        for (Object e: this)
        {
            System.out.println(e.toString());
        }
    }

    public Element get(int z)
    {
        if(z > this.size()) return null;

        int i = 1;
        Element e = head;
        while (i != z)
        {
            e = e.next;
            ++i;
        }
        return e;
    }


}
