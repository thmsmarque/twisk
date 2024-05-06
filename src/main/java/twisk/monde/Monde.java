package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {
    public GestionnaireEtapes lesEtapes;
    SasEntree entree;
    SasSortie sortie;

    /**
     * Constructeur de la classe Monde
     */
    public Monde()
    {

        lesEtapes = new GestionnaireEtapes();
        this.entree = new SasEntree();
        this.sortie = new SasSortie();
        lesEtapes.ajouter(entree,sortie);

    }

    /**
     * Méthode qui définit les entrées
     * @param etapes etapes à définir comme entrées
     *
     */
    public void aCommeEntree(Etape... etapes)
    {
        this.entree.ajouterSuccesseur(etapes);
    }

    /**
     * Méthode qui définit les sorties
     *
     * @param etapes étapes à définir comme sorties
     */
    public void aCommeSortie(Etape... etapes)
    {
        for(Etape et : etapes)
        {
            et.ajouterSuccesseur(this.sortie);
        }
    }

    /**
     * Méthode qui donne la ou les entrée(s)
     *
     * @return entrée(s) du monde
     */
    public SasEntree getEntree() {
        return entree;
    }

    /**
     * Méthode qui donne la ou les sortie(s)
     *
     * @return sortie(s) du monde
     */
    public SasSortie getSortie() {
        return sortie;
    }

    /**
     * Méthode qui donne les étapes du monde
     *
     * @return les étapes du monde
     */
    public GestionnaireEtapes getLesEtapes() {
        return lesEtapes;
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
        int nbGuichet = 0;
        Iterator<Etape> it = lesEtapes.iterator();
        do
        {
            if(it.next().estUnGuichet())
            {
                nbGuichet++;
            }
        }while(it.hasNext());
        return nbGuichet;
    }

    /**
     * Méthode qui permet de générer le fichier client.c de ce Monde
     * @return contenu du fichier client.c
     */
    public String toC()
    {
        Etape et = this.getLesEtapes().getListeetapes().iterator().next().iterator().next();
        Etape etDef = this.getLesEtapes().getListeetapes().iterator().next();
        //System.out.println(et);
        String res = "#include \"def.h\"\n" +
            etDef.defineName() +
            "\n\nvoid simulation(int ids)\n{\n" +
                "int nb; \n" +
                "entrer(0);\n" +
                "delai("+this.entree.getTemps()+","+this.entree.getEcartTemps()+");\n" +
                "transfert(0,"+et.getIndiceEtape() +");\n" + et.toC();
        StringBuilder build = new StringBuilder();
        //build.append(res).append(et.toC());
        return res;
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
                "\n\t\tlesEtapes=" + lesEtapes +
                "\n\t\t, entree=" + entree +
                '}';
    }
}
