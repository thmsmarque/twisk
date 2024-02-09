package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{
    private int nbJetons;

    int indiceGuichet;

    public Guichet(String nom, FabriqueNumero fn) {
        super(nom,fn);
        indiceGuichet = fn.getNumeroSemaphore();
        this.nbJetons=0;
    }
    public Guichet(String nom, int nb,FabriqueNumero fn) {
        super(nom,fn);
        this.nbJetons = nb;
        indiceGuichet = fn.getNumeroSemaphore();
    }

    /**
     * retourne l'entier represantant l'indice sémaphore du guichet
     * @return l'indice sémaphore du guichet
     */
    public int getSemaphore()
    {
        return indiceGuichet;
    }

    /**
     * @return le nombre de jeton du guichet
     */
    public int getNbJetons() {
        return nbJetons;
    }

    public boolean estUnGuichet(){
        return true;
    }

    @Override
    public String toString() {
        return "Guichet{" +
                " nom= " + getNom() +
                " nbJetons=" + nbJetons +
                ", gestionnaire=" + gestionnaire +
                '}';
    }
}
