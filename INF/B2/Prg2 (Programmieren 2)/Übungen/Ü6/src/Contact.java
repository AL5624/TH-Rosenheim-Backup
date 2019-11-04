public class Contact
{
    private static int contactCounter;
    private String name;
    private long phone = 0;
    private int id;
    private Contact next;

    public static int getContactCounter() {
        return contactCounter;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public Contact getNext() {
        return next;
    }

    public Contact(String name)
    {
        this.name = name;
        ++contactCounter;
        id = contactCounter;
    }

    public Contact(String name, long phone)
    {
        this(name);
        this.phone = phone;
    }

    public String toString ()
    {
        return name + " - " + phone;
    }

    public void setNext(Contact next)
    {
        this.next = next;
    }
}
