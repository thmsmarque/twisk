package twiskIG.mondeIG;

import twiskIG.exceptions.TwiskException;

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

    @Override
    public Boolean estActivite() {
        return false;
    }

    @Override
    public Boolean estActiviteRestreinte() {
        return false;
    }


    @Override
    public Boolean estGuichet() {
        return true;
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
    public void changerNbJeton(int nbJeton) throws TwiskException {
        if(nbJeton <=0)
        {
            throw new TwiskException("Le nombre de jeton ne peut pas être inférieur ou égal à 0");
        }
        this.nbJeton = nbJeton;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public static class ClientIG {


        public ClientIG(){

        }
    }
}
