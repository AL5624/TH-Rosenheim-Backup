package training.lecture.serialize;

import java.io.PrintStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Customer implements Serializable
{
    public static final String       DELIMITER = ";";
    private             Long         id;
    private             String       firstName;
    private             String       lastName;
    private             LocalDate    birthday;
    private             Address      postalAddress;
    private             boolean      isPremium;
    private             List<String> hobbies;

    public Customer()
    {
    }

    public Customer(Long id, String firstName, String lastName, LocalDate birthday, Address postalAddress, boolean isPremium)
    {
        this.id            = id;
        this.firstName     = firstName;
        this.lastName      = lastName;
        this.birthday      = birthday;
        this.postalAddress = postalAddress;
        this.isPremium     = isPremium;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        return "Customer{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", birthday=" + birthday +
               ", postalAddress=" + postalAddress +
               ", isPremium=" + isPremium +
               ", hobbies=" + hobbies +
               '}';
    }

    //getter und setter:

    public boolean isPremium()
    {
        return isPremium;
    }

    public void setPremium(boolean premium)
    {
        isPremium = premium;
    }

    public List<String> getHobbies()
    {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies)
    {
        this.hobbies = hobbies;
    }

    public Address getPostalAddress()
    {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress)
    {
        this.postalAddress = postalAddress;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }


    //Ser3_CSV_komp:


    public void readFrom(Scanner in)
    {
        in.useDelimiter(DELIMITER);
        id = Long.parseLong(in.next());
        firstName = in.next();
        lastName = in.next();
        birthday = LocalDate.parse(in.next());
        postalAddress = new Address();
        postalAddress.readFrom(in);
    }


    public void writeTo(PrintStream out)
    {
        out.print(id);
        out.print(DELIMITER);

        out.print(firstName);
        out.print(DELIMITER);

        out.print(lastName);
        out.print(DELIMITER);

        out.print(birthday);
        out.print(DELIMITER);

        postalAddress.writeTo(out);
        out.print(DELIMITER);
    }
}
