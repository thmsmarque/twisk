package twiskIG.mondeIG;

import twiskIG.outils.FabriqueIdentifiant;

/**
 * Classe Point DE Contrôle
 */
public class PointDeControleIG {

    private String identifiant;

    private int posX;
    private int posY;
    private String identifiantEtape;
    private EtapeIG etape;

    /**
     * Constructeur de la classe PointDeControle
     */
    public PointDeControleIG(int posX, int posY, EtapeIG etape){
        this.etape = etape;
        this.identifiant= FabriqueIdentifiant.getInstance().getIdentifiantPointControle();
        this.posX=posX;
        this.posY=posY;
        this.identifiantEtape = etape.getIdentifiant();
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public EtapeIG getEtape() {
        return etape;
    }

    /**
     * @return l'icdentifiant unique du point de contrôle
     */
    public String getIdentifiant() {
        return identifiant;
    }

    public String getIdentifiantEtape() {
        return identifiantEtape;
    }

    @Override
    public String toString() {
        return "PC : " + identifiant + " ("+ posX+";"+posY+") ";
    }
}
