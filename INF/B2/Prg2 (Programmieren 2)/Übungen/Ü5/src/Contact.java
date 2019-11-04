public class Contact {

    private int contactCounter = 0;
    private String name;
    private long phone = 0;
    private static int id;
    private Contact next = null;

    public static int getId() {
        return id;
    }

    public Contact (String name)
    {
        this.name = name;
        ++id;
    }

    public Contact (String name, long phone)
    {
        this.name = name;
        this.phone = phone;
        ++id;
    }

    public String toString()
    {
        return name + phone;
    }

    public long getPhone()
    {
        return phone;
    }

    public String getName()
    {
        return name;
    }

    public Contact getNext()
    {
        return this.next;
    }

    public void setContactCounter(int contactCounter) {
        this.contactCounter = contactCounter;
    }

    public void setNext(Contact next)
    {
        if(next.equals(this))
        {
           this.contactCounter = 1;
        }
        else
        {
            if (this.contactCounter != 0 && this.next != null && this.next.contactCounter != 0)
            {
                next.next = this.next;
            }
            else if(this.next == null && this.contactCounter == 0)
            {
                this.contactCounter = 1;
            }
            this.next = next;
            this.ContactCounterPlus(next);
        }
    }

    private void ContactCounterPlus(Contact next)
    {
        if(next != null)
        {
            int a = this.contactCounter;
            next.contactCounter = a + 1;
            next.ContactCounterPlus(next.next);
        }
    }


}
