package main.java.twisk.monde;

public class SasEntree extends Activite {

    Monde m;
    public SasEntree(){
        super("Entree");
    }

    @Override
    public String toString() {
        return "SasEntree{" +
                "m=" + m +
                ", temps=" + temps +
                ", ecartTemps=" + ecartTemps +
                ", gestionnaire=" + gestionnaire +
                '}';
    }
}
