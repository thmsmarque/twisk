package twiskIG.mondeIG;

import com.google.gson.annotations.Expose;

/**
 * Classe ClientIG
 */
public class ClientIG {

    @Expose
    private int posx;
    @Expose
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
