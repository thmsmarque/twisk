package twisk.outils;

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
        int temp = cptSemaphore;
        cptSemaphore += 1;
        return temp;
    }

    public void reset()
    {
        cptEtape = 0;
        cptSemaphore = 1;
    }
}
