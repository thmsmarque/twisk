package twiskIG.mondeIG;

/**
 * Classe ClientIG
 */
public class ClientIG {

    private int posx;
    private int posy;

    /**
     * Constructeur de la classe ClientIG
     * @param posx
     * @param posy
     */
    public ClientIG(int posx, int posy){
        this.posx = posx;
        this.posy = posy;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }
}
