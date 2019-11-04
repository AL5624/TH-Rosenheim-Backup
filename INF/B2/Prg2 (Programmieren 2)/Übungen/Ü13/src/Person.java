

public class Person implements Comparable<Person>
{
    String name;
    int alter;

    public Person(String name, int alter)
    {
        this.name = name;
        this.alter = alter;
    }

    @Override
    public int compareTo(Person o)
    {
        return 0;
    }

    @Override
    public boolean equals(Object object)
    {

        return true;
    }

    public int[] m (int i) throws MyException
    {
        if(i > 2);
        int [] a = new int[2];
        a[i] = 0;
        return a;
    }
}
