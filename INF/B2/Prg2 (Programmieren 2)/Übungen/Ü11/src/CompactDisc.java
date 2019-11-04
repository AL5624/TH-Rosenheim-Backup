import java.lang.Comparable;
import java.util.Arrays;

public class CompactDisc implements Comparable<CompactDisc>
{
   private String titel;
   private String interpret;
   private int erscheinungsjahr;
   private String plattenlabel;

   private final static String[] TO_REMOVE = {"der", "die", "das", "ein", "eine", "einer", "eines", "the", "a"};

    public String getTitel() {
        return titel;
    }

    public String getInterpret() {
        return interpret;
    }

    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public String getPlattenlabel() {
        return plattenlabel;
    }

    public CompactDisc (String interpret, String titel)
    {
        this.interpret = interpret;
        this.titel = titel;
    }

    public CompactDisc(String interpret, String titel, int erscheinungsjahr)
    {
        this(interpret, titel);
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public CompactDisc(String interpret, String titel, String plattenlabel)
    {
        this(interpret, titel);
        this.plattenlabel = plattenlabel;
    }

    public CompactDisc(String interpret, String titel, int erscheinungsjahr, String plattenlabel)
    {
        this(interpret, titel, erscheinungsjahr);
        this.plattenlabel = plattenlabel;
    }

    @Override
    public int compareTo(CompactDisc a1)
    {
        String a = this.interpret;
        String b = a1.interpret;
        a = a.toLowerCase().trim();
        b = b.toLowerCase().trim();

        String [] aArray = a.split(" +");
        String [] bArray = b.split(" +");

        int aVariable = 0;
        int bVariable = 0;

        for(int i = 0; i < TO_REMOVE.length; i++)
        {
            if(aArray[aVariable].equals(TO_REMOVE[i]) && aVariable != 1)
            {
                ++aVariable;
            }

            if(bArray[bVariable].equals(TO_REMOVE[i]) && bVariable != 1)
            {
                ++bVariable;
            }

            if(aVariable == 1 && bVariable == 1)
            {
                break;
            }
        }

        return aArray[aVariable].compareTo(bArray[bVariable]);
    }

    @Override
    public boolean equals(Object a1)
    {
        if(a1 == null) return false;
        if(a1 == this) return true;
        if(!a1.getClass().getName().equals(this.getClass().getName())) return false;

        CompactDisc b1 = (CompactDisc) a1;
        String b2 = this.interpret.toLowerCase();
        String b3 = b1.interpret.toLowerCase();
        String b4 = this.titel.toLowerCase();
        String b5 = b1.titel.toLowerCase();
        if(b2.equals(b3) && b4.equals(b5)) return true;

        return false;
    }

    @Override
    public String toString()
    {
        return interpret + " - " + titel;
    }

    @Override
    public int hashCode()
    {
        String s = this.interpret.toLowerCase();
        String a = this.titel.toLowerCase();
        int result = 31 * s.hashCode();
        result = 31 * result + a.hashCode();

        return result;
    }

    public static void main(String[] args) {
        CompactDisc a = new CompactDisc("S", "s", 1);
        CompactDisc b = new CompactDisc("s", "s", 1);

        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));

    }
}
