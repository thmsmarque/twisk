package main.java.twisk.monde;

public class Guichet extends Etape{
    private int nbJetons;

    public Guichet(String nom) {
        super(nom);
    }
    public Guichet(String nom, int nb) {
        super(nom);
        this.nbJetons = nb;
    }

    boolean estUnGuichet(){
        return true;
    }
}
