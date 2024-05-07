package twiskIG.mondeIG;

/**
 * Classe ActiviteIG
 */
public class ActiviteIG extends EtapeIG{

    /** Constructeur de la classe ActiviteIG
     * @param nom
     * @param larg
     * @param haut
     */


    public ActiviteIG(String nom, int larg, int haut) {
        super(nom, larg, haut);
    }

    @Override
    public Boolean estActivite() {
        return true;
    }

    @Override
    public Boolean estActiviteRestreinte() {
        for(EtapeIG e : this.predecesseurs.values())
        {
            if(e.estGuichet())
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public Boolean estGuichet() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
