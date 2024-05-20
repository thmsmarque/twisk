package twiskIG.mondeIG;

import com.google.gson.annotations.Expose;
import twiskIG.exceptions.TwiskException;

public class GuichetIG extends EtapeIG {

    /**
     * false : gauche à droite
     * true : droite à gauche
     */
    @Expose
    private boolean sens;
    @Expose
    private int nbJeton;

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
        this.sens=true; // de droite à gauche de base
    }

    /**
     * Définit le sens de la file
     * @param sens true si Gauche->Droite false si Droite->Gauche
     */
    public void setSens(boolean sens) {
        this.sens = sens;
    }

    public boolean getSens(){
        return this.sens;
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
