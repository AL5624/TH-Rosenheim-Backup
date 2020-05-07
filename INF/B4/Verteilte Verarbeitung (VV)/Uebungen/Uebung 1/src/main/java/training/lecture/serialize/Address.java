package training.lecture.serialize;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Address implements Serializable
{
    public static final String DELIMITER = ";";
    private String street;
    private String postalCode;
    private String city;



    public Address()
    {
    }

    public Address(String street, String postalCode, String city)
    {
        this.street     = street;
        this.postalCode = postalCode;
        this.city       = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
               Objects.equals(postalCode, address.postalCode) &&
               Objects.equals(city, address.city);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(street, postalCode, city);
    }

    @Override
    public String toString()
    {
        return "Address{" +
               "street='" + street + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", city='" + city + '\'' +
               '}';
    }

    //Ser3_CSV_komp:


    public void readFrom(Scanner in)
    {
        in.useDelimiter(DELIMITER);
        street = in.next();
        postalCode = in.next();
        city = in.next();
    }


    public void writeTo(PrintStream out)
    {
        out.print(street);
        out.print(DELIMITER);

        out.print(postalCode);
        out.print(DELIMITER);

        out.print(city);
        out.print(DELIMITER);
    }
}
