public abstract class Knoten
{
    public abstract Boolean getWert();

    @Override
    public String toString()
    {
        if (getWert())
        {
            return "1";
        }
        else
        {
            return "0";
        }
    }
}
