import java.math.BigInteger;

final public class BigRational
{
    private BigInteger Zaehler;
    private BigInteger Nenner;

    public static final String DELIMITER = "/";

    private static BigInteger ggT(BigInteger zaehler, BigInteger nenner)
    {
        zaehler = zaehler.abs();
        nenner = nenner.abs();

        while (zaehler.compareTo(nenner) != 0)
        {
            if (zaehler.compareTo(nenner) == 1)
            {
                zaehler = zaehler.subtract(nenner);
            } else if (zaehler.compareTo(nenner) == -1)
            {
                nenner = nenner.subtract(zaehler);
            }
        }

        return nenner;
    }

    private void norm()
    {
        BigInteger ggT = ggT(Zaehler, Nenner);



        Zaehler = Zaehler.divide(ggT);
        Nenner = Nenner.divide(ggT);

        if(Nenner.compareTo(BigInteger.ZERO) < 0)
        {
            Zaehler = Zaehler.negate();
            Nenner = Nenner.negate();
        }
    }

    public BigRational(BigInteger zaehler, BigInteger nenner)
    {
        Zaehler = zaehler;
        Nenner = nenner;
        norm();
    }

    public BigRational(BigInteger zaehler)
    {
        Zaehler = zaehler;
        Nenner = BigInteger.ONE;
    }

    public BigRational(String bruch)
    {
        String a = bruch.trim();

        String [] b = a.split(DELIMITER);
        b[0] = b[0].trim();
        b[1] = b[1].trim();
        int zaehler = Integer.parseInt(b[0]);
        int nenner = Integer.parseInt(b[1]);
        Zaehler = BigInteger.valueOf(zaehler);
        Nenner = BigInteger.valueOf(nenner);
    }



}
