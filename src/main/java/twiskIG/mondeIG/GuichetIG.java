package twiskIG.mondeIG;

public class GuichetIG extends EtapeIG {

    int nbJeton;

    /**
     * Constructeur de la classe abstraite EtapeIG
     *
     * @param nom
     * @param larg
     * @param haut
     */
    public GuichetIG(String nom, int larg, int haut) {
        super(nom, larg, haut);
        nbJeton = 5;
    }

    /**
     * Renvoi le nombre de jetons de l'activité
     * @return nbJeton
     */
    public int getNbJeton() {
        return nbJeton;
    }

    /**
     * Cette méthode permet de chager le nombre de jetons de l'activité
     * @param nbJeton nouveau nombre de jetons
     */
    public void changerNbJeton(int nbJeton) {
        this.nbJeton = nbJeton;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
