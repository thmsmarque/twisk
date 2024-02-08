package main.java.twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireEtapes {

    ArrayList<Etape> etapes;

    /**
     * Constructeur de la classe GestionnaireEtapes
     *
     */
    public GestionnaireEtapes()
    {

    }


    /**
     * Méthode pour ajouter des étapes au gestionnaire
     *
     * @param etapes les étapes à ajouter
     */
    public void ajouter(Etape... etapes)
    {

    }

    /**
     * Renvoi un entier correspondant au nombre d'étapes du gestionnaire
     * @return int nombre d'étapes du gestionnaire
     */
    public int nbEtapes()
    {
        return etapes.size();
    }

    /**
     * Crée l'iterator de la classe
     * @return iterator
     */
    Iterator<Etape> iterator()
    {
        return etapes.iterator();
    }

}
