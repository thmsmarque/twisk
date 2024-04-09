package twisk.outils;

public class FabriqueNumeroLibTwisk {

    int cpt;

    private final static FabriqueNumeroLibTwisk instance = new FabriqueNumeroLibTwisk();
    private FabriqueNumeroLibTwisk()
    {
        cpt = 0;
    }

    static public FabriqueNumeroLibTwisk getInstance()
    {
        return instance;
    }

    public int getNumero()
    {
        return cpt;
    }

    public void incrementer()
    {
        cpt += 1;
    }

    public void reset()
    {
        cpt = 0;
    }
}