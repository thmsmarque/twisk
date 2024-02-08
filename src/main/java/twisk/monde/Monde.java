package main.java.twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {
    GestionnaireEtapes lesEtapes;
    SasEntree entree;
    //SasSortie sortie;

    /**
     * Constructeur de la classe Monde
     */
    public Monde()
    {

    }

    /**
     * Méthode qui définit les entrées
     * @param etapes etapes à définir comme entrées
     */
    public void aCommeEntree(Etape... etapes)
    {

    }

    /**
     * Méthode qui définit les sorties
     *
     * @param etapes étapes à définir comme sorties
     */
    public void aCommeSortie(Etape... etapes)
    {

    }

    /**
     * Méthode pour ajouter des étapes au Monde
     * @param etapes étapes à ajouter au Monde
     */
    public void ajouter(Etape... etapes)
    {
        lesEtapes.ajouter(etapes);
    }

    /**
     * Méthode qui renvoi le nombre d'étapes du monde
     * @return nombre d'étapes du monde
     */
    public int nbEtapes()
    {
        return lesEtapes.nbEtapes();
    }

    /**
     * Méthode qui renvoi le nombre de guichets du monde
     * @return nombre de guichets du monde
     */
    public int nbGuichets()
    {
        return 0;
    }


    /**
     * Crée l'iterator de la classe monde
     * @return l'iterator de la classe monde
     */
    public Iterator<Etape> iterator()
    {
        return lesEtapes.iterator();
    }

    @Override
    public String toString() {
        return "Monde{" +
                "lesEtapes=" + lesEtapes +
                ", entree=" + entree +
                '}';
    }
}
