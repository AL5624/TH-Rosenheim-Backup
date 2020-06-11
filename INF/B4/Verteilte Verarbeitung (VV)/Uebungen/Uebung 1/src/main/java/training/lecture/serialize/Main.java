package training.lecture.serialize;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Customer harry = new Customer(1L, "Harry", "Hirsch",
        LocalDate.of(1950, 3, 18),
        new Address("Hochschulstr. 1", "83024", "Rosenheim"), true);
    harry.setHobbies(Arrays.asList("Laufen, Spinnen, Groelen".split(",")));

    Customer gerd = new Customer(2L, "Gerd", "Beneken",
        LocalDate.of(1971, 5, 18),
        new Address("Schütteweg 4", "26384", "Wilhelmshaven"), false);
    gerd.setHobbies(Arrays.asList("Schach, Halma, Fussball".split(",")));


    JsonSerializer<LocalDate> ser = new JsonSerializer<LocalDate>() {
      @Override
      public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        if (src != null) {
          return new JsonPrimitive(src.toString());
        }
        return null;
      }
    };

    JsonDeserializer<LocalDate> dser = new JsonDeserializer<LocalDate>() {
      @Override
      public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null) {
          return LocalDate.parse(json.getAsString());
        }
        return null;
      }
    };

    Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, ser)
        .registerTypeAdapter(LocalDate.class, dser)
        .create();


    String gerdAlsJson = gson.toJson(gerd);

    System.out.println(gerdAlsJson);

    Customer test = gson.fromJson(gerdAlsJson, Customer.class);

    System.out.println(test);

    //Ser04_JavaSerialisierung_kompakt:
/*        try (OutputStream outputStream = new FileOutputStream("customers.ser"))
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(harry);
            objectOutputStream.writeObject(gerd);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            InputStream       inputStream       = new FileInputStream("customers.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Customer eins = (Customer) objectInputStream.readObject();
            Customer zwei = (Customer) objectInputStream.readObject();

            System.out.println("Gelesen: " + eins);
            System.out.println("Gelesen: " + zwei);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/


    //Ser3_CSV_komp:
/*        try (PrintStream printStream = new PrintStream("cutomers.csv"))
        {
            harry.writeTo(printStream);
            printStream.println();
            gerd.writeTo(printStream);
            printStream.println();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            InputStream in = new FileInputStream("cutomers.csv");

            Scanner inputScanner = new Scanner(in);

            while (inputScanner.hasNext())
            {
                Customer read = new Customer();

                read.readFrom(inputScanner);
                inputScanner.nextLine();
                System.out.println("READ: " + read);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }*/
  }
}
