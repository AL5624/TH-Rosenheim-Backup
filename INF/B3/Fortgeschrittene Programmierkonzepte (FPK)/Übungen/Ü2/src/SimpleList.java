public interface SimpleList<T> extends Iterable<T>
{
    void add(T item);

    int size();

    default SimpleList<T> filter (SimpleFilter<T> filter)
    {
        SimpleList<T> result = new SimpleListImpl();
        for(T o: this)
        {
            if(filter.include(o))
            {
                result.add(o);
            }
        }
        return result;
    }

}
