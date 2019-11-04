public class Addressbook {

    private int numContacts;
    private Contact first = null;

    public Addressbook()
    {
        numContacts = 0;
    }

    public int getNumContacts() {
        return numContacts;
    }

    public void setNumContacts(int numContacts) {
        this.numContacts = numContacts;
    }

    public void insertToFront (Contact first)
    {
        if(this.first != null)
        {
            first.setNext(this.first);
        }
        else
        {
            first.setNext(first);
        }

        this.first = first;
        ++numContacts;
    }

    public void insertToBack (Contact last)
    {
        Contact p = first;

        while(p.getNext() != null)
        {
            p = p.getNext();
        }

        p.setNext(last);
    }

    public void insertBehind (Contact vorhanden, Contact New)
    {
        Contact p = first;

        while(p != vorhanden && p != null)
        {
            p = p.getNext();
        }

        if(p == null)
        {
            System.out.println("Kontakt nicht vorhanden");
        }
        else
        {
            p.setNext(New);
        }
    }

    public Contact getFirst() {
        return first;
    }

    public void delete(Contact delete)
    {
        Contact p = first;

        while(p.getNext() != delete)
        {
            p = p.getNext();
        }

        delete.setContactCounter(0);

        p.setNext(delete.getNext());

        --numContacts;
    }

    public Contact search (String search)
    {
        Contact p = first;

        while (p != null)
        {
            if(search.equals(p.getName()))
            {
                return p;
            }
            p = p.getNext();
        }

        System.out.println("Fehler");

        return null;
    }

    public void print()
    {
        Contact p = first;

        while (p != null)
        {
            System.out.println(p.getName());
            p = p.getNext();
        }
    }

}
