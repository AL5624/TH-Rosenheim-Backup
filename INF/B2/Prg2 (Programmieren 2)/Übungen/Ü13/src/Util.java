import java.util.ArrayList;
import java.util.List;

public class Util
{

    public static <E> void reverse1(List<E> list)
    {
        for (int i = 0, p = list.size() - 1; i < list.size()/2; i++, p--)
        {
            E t = list.get(i);

            list.set(i, list.get(p));

            list.set(p, t);
        }
    }


    public static <E> List<E> reverse2(List<E> list) throws IllegalAccessException, InstantiationException
    {


        List<E> neueList = null;

        try
        {
            neueList = list.getClass().newInstance();
        }catch (Exception e)
        {
            neueList = new ArrayList<E>();
        }

        for (int p = list.size() - 1; p >= 0; p--)
        {
            neueList.add(list.get(p));
        }

        return neueList;
    }




}
