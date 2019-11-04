public class Test {

    public static void main (String[] args)
    {
        Addressbook Addressbuch = new Addressbook();

        Contact Kontakt1 = new Contact("Anton", 017672307774);
        Contact Kontakt2 = new Contact("Paul");
        Contact Kontakt3 = new Contact("Moritz");

        Addressbuch.insertToFront(Kontakt1);

        Addressbuch.insertToFront(Kontakt2);

        Addressbuch.insertToFront(Kontakt3);

        Contact p = Addressbuch.search("Anton");

        if(p != null)System.out.println(p.getName());

       // Addressbuch.print();

    }


}


