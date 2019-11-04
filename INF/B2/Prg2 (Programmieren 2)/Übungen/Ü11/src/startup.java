public class startup
{
    public static void main(String[] args) {

        CompactDisc a = new CompactDisc("Die Toten Hosen", "An Tagen wie diesen", 1999);
        CompactDisc b = new CompactDisc("Eskimo Callboy", "MC Thunder", 2010);
        CompactDisc c = new CompactDisc("The Angles", "I'm an Angle with a shotgun", 3000);

        int d = 0;
        YearOfPublicationComparator e = new YearOfPublicationComparator();
        d = e.compare(a,b);

        System.out.println(d);
    }
}
