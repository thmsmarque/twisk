package main.java.twisk.outils;

public class FabriqueNumero {

    int cptEtape, cptSemaphore;

    private final static FabriqueNumero instance = new FabriqueNumero();
    private FabriqueNumero()
    {
        cptEtape = 0;
        cptSemaphore = 1;
    }

    static public FabriqueNumero getInstance()
    {
        return instance;
    }

    public int getNumeroEtape()
    {
        cptEtape++;
        return cptEtape-1;
    }
    public int getNumeroSemaphore()
    {
        cptSemaphore++;
        return cptSemaphore-1;
    }

    public void reset()
    {
        cptEtape = 0;
    }
}
