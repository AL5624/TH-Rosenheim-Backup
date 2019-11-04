import java.util.Comparator;

public class YearOfPublicationComparator implements Comparator<CompactDisc>
{
    @Override
    public int compare(CompactDisc a, CompactDisc b)
    {
        if (a.getErscheinungsjahr() < b.getErscheinungsjahr())
        {
            return -1;
        }
        else if (b.getErscheinungsjahr() > a.getErscheinungsjahr())
        {
            return 1;
        }

        return 0;
    }
}
