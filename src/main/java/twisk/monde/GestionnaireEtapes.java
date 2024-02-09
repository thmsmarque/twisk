package main.java.twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireEtapes implements Iterable<Etape>{

    ArrayList<Etape> listeetapes;

    /**
     * Constructeur de la classe GestionnaireEtapes
     *
     */
    public GestionnaireEtapes()
    {
        listeetapes = new ArrayList<>();
    }

    /**
     * @return la liste des étapes
     */
    public ArrayList<Etape> getListeetapes() {
        return listeetapes;
    }

    /**
     * Méthode pour ajouter des étapes au gestionnaire
     *
     * @param etapes les étapes à ajouter
     */
    public void ajouter(Etape... etapes)
    {
        listeetapes.addAll(Arrays.asList(etapes));
    }

    /**
     * Renvoi un entier correspondant au nombre d'étapes du gestionnaire
     * @return int nombre d'étapes du gestionnaire
     */
    public int nbEtapes()
    {
        return listeetapes.size();
    }

    /**
     * Crée l'iterator de la classe
     * @return iterator
     */
    public Iterator<Etape> iterator()
    {
        return listeetapes.iterator();
    }

    @Override
    public String toString() {
        return "GestionnaireEtapes{" +
                "listeetapes=" + listeetapes +
                '}';
    }
}
