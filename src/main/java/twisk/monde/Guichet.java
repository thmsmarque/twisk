package main.java.twisk.monde;

public class Guichet extends Etape{
    private int nbJetons;

    public Guichet(String nom) {
        super(nom);
        this.nbJetons=0;
    }
    public Guichet(String nom, int nb) {
        super(nom);
        this.nbJetons = nb;
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
                "nbJetons=" + nbJetons +
                ", gestionnaire=" + gestionnaire +
                '}';
    }
}
