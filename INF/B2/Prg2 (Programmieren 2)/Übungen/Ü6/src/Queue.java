public class Queue
{
    int numContacts = 0;
    Contact head = null;
    Contact tail = null;

    /**
     * erzeugt eine neue Queue
     * @autor Anton Bertram
     */
    public Queue(){}

    /**
     * fuegt einen neuen Kontakt am Ende der Schlange ein
     * @autor Anton Bertram
     * @param c Kontakt, der am Ende eingefuegt werden soll
     */
    public void enqueue (Contact c)
    {
        if (head == null)
        {
            head = c;
        }
        else
        {
            tail.setNext(c);
        }

        tail = c;
        c.setNext(null);
        ++numContacts;
    }

    /**
     * Entfernt aeltesten Kontakt
     * @autor Anton Bertram
     * @return geloeschter Kontakt
     */
    public Contact dequeue()
    {
        Contact c = head;
        if(numContacts == 0) return null;
        head = head.getNext();
        c.setNext(null);
        --numContacts;
        if(numContacts == 0) tail = null;

        return c;
    }
}
