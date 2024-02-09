package main.java.twisk.monde;

import main.java.twisk.outils.FabriqueNumero;

public class Guichet extends Etape{
    private int nbJetons;

    int indiceGuichet;

    public Guichet(String nom, FabriqueNumero fn) {
        super(nom,fn);
        this.nbJetons=0;
        indiceGuichet = fn.getNumeroSemaphore();
    }
    public Guichet(String nom, int nb,FabriqueNumero fn) {
        super(nom,fn);
        this.nbJetons = nb;
        indiceGuichet = fn.getNumeroSemaphore();
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
