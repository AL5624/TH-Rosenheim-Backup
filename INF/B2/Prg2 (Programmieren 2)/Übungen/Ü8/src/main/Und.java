public class Und extends  ErweiterbarerKnoten
{
    @Override
    public Boolean getWert()
    {
        for(int i = 0; Unterknoten[i] != null; i++)
        {
            if(Unterknoten[i].getWert() != true) return false;
        }
        if(Unterknoten[0] == null) return false;
        return true;
    }
}
