package main.java.twisk.outils;

public class FabriqueNumero {

    int cptEtape;

    private final static FabriqueNumero instance = new FabriqueNumero();
    private FabriqueNumero()
    {
        cptEtape = 0;
    }

    static public FabriqueNumero getInstance()
    {
        return instance;
    }

    public int getNumeroEtape()
    {
        return cptEtape;
    }

    public void reset()
    {
        cptEtape = 0;
    }
}
