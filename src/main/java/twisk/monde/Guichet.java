package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{
    private int nbJetons;

    int indiceGuichet;

    public Guichet(String nom) {
        super(nom);
        indiceGuichet = FabriqueNumero.getInstance().getNumeroSemaphore();
        this.nbJetons=0;
    }


    public Guichet(String nom, int nb) {
        super(nom);
        indiceGuichet = FabriqueNumero.getInstance().getNumeroSemaphore();
        this.nbJetons = nb;
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

    @Override
    public String toC() {
        //ids à vérifier
        String res = "P("+ "ids"+ ","+"1"+"); \n" +
                "transfert("+this.getIndiceEtape()+","
                + this.getGestionnaire().getListeetapes().get(this.getIndiceEtape()+1).getIndiceEtape()  +");\n" +
                this.getGestionnaire().getListeetapes().get(this.getIndiceEtape()+1).toC() +
                "V("+ "ids"+ this.getSemaphore()+");" ;

                //chaque guichet est suivi d'une activité restreinte


        return res + this.gestionnaire.iterator().next();
    }
}
