import java.util.Comparator;

public class InterpreterTitleComparator implements Comparator<CompactDisc> {

    @Override
    public int compare(CompactDisc a, CompactDisc b)
    {
        int p = a.compareTo(b);
        if(p != 0)
        {
            return p;
        }

        String aTitel = a.getTitel().toLowerCase().trim();
        String bTitel = b.getTitel().toLowerCase().trim();

        return aTitel.compareTo(bTitel);
    }
}
