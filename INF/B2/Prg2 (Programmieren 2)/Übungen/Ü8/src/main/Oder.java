public class Oder extends ErweiterbarerKnoten
{
    @Override
    public Boolean getWert()
    {
       for(int i = 0; Unterknoten[i] != null; i++)
       {
           if(Unterknoten[i].getWert() == true) return true;
       }
        return false;
    }
}
