public abstract class ErweiterbarerKnoten extends Knoten
{
    protected Knoten [] Unterknoten = new Knoten[100];
    private int i = 0;

    public ErweiterbarerKnoten addKnoten (Knoten k)
    {
        Unterknoten [i] = k;
        ++i;
        return this;
    }

    public void ClearAllKnoten()
    {
        for(int i = 0; Unterknoten[i] != null; i++)
        {
            Unterknoten[i] = null;
        }

        i = 0;
    }
}
