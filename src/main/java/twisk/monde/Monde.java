package twisk.monde;

import twisk.simulation.GestionnaireClients;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {
    private GestionnaireEtapes lesEtapes;
    private SasEntree entree;
    private SasSortie sortie;
    private GestionnaireClients g;
    private String probaEntree;
    private String nomFonctionProbaEntree;
    /**
     * Constructeur de la classe Monde
     */
    public Monde()
    {

        lesEtapes = new GestionnaireEtapes();
        this.entree = new SasEntree();
        this.sortie = new SasSortie();
        lesEtapes.ajouter(entree,sortie);
        this.g = new GestionnaireClients();

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
                "#include <math.h> \n"+
            etDef.defineName() + "\n" +
                this.probaEntree +
            "\n\nvoid simulation(int ids)\n{\n" +
                "int nb; \n" +
                "entrer("+getEntree().getDefineName()+");\n" +
                /*"delai("+this.entree.getTemps()+","+this.entree.getEcartTemps()+");\n" +*/

                "usleep("+this.nomFonctionProbaEntree +"*1000000); \n" +

                "transfert("+getEntree().getDefineName()+","+et.getDefineName() +");\n" + et.toC() + "\n}" ;
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

    public GestionnaireClients getG() {
        return g;
    }

    @Override
    public String toString() {
        return "Monde{" +
                "\n\t\tlesEtapes=" + lesEtapes +
                "\n\t\t, entree=" + entree +
                '}';
    }

    public void probaType(String proba){
        switch(proba){
            case "uniforme":
                System.out.println("uniforme");
               this.nomFonctionProbaEntree = "delaiUniforme("+getEntree().getTemps()+","+getEntree().getEcartTemps()+")";
                this.probaEntree =
                        "int delaiUniforme(int temps, int delta)\n" +
                                "{\n" +
                                "    int bi, bs;\n" +
                                "    int n, nbSec;\n" +
                                "    bi = temps - delta;\n" +
                                "    if (bi < 0)\n" +
                                "        bi = 0;\n" +
                                "    bs = temps + delta;\n" +
                                "    n = bs - bi + 1;\n" +
                                "    nbSec = (rand() / (float)RAND_MAX) * n;\n" +
                                "    nbSec += bi;\n" +
                                "return nbSec; \n"+
                                "}\n" ;
                break;
            case "exponentielle":
                System.out.println("exponentielle");
                this.nomFonctionProbaEntree = "delaiExponentiel("+getEntree().getTemps()+")";

                this.probaEntree =
                        "int delaiExponentiel(double lambda)\n" +
                                "{\n" +
                                " double lambdabis = 1/lambda; \n"+
                                "    double U = rand() / (double)RAND_MAX;\n" +
                                "    double X = -log(U) / lambdabis;\n" +
                                "   return X; \n"+
                                "}\n";
                break;
            case "gaussienne" :
                System.out.println("gaussienne");
                this.nomFonctionProbaEntree = "delaiGauss("+getEntree().getTemps()+","+getEntree().getEcartTemps()+")";

                this.probaEntree =
                        "int delaiGauss(double moyenne, double ecartype)\n" +
                                "{\n" +
                                "    double U1 = rand() / (double)RAND_MAX;\n" +
                                "    double U2 = rand() / (double)RAND_MAX;\n" +
                                "    double Z = sqrt(-2.0 * log(U1)) * cos(2.0 * 3.14159265358979323846 * U2);\n" +
                                "    double X = moyenne + Z * ecartype;\n" +
                                "return X; \n" +
                                "}\n";

                break;
        }
    }
}


