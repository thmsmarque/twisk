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
        Etape et = this.getGestionnaire().getListeetapes().iterator().next();
        //System.out.println("Est ici (guichet) "+ et);
        String res = "P("+ "ids"+ ","+"1"+"); \n" +
                "transfert("+this.getIndiceEtape()+","
                + et.getIndiceEtape()  +");\n" +
                et.toC() +
                "V("+ "ids,"+ this.getSemaphore()+");\n"
                +"transfert("+ et.getIndiceEtape()
                + "," + et.iterator().next().getIndiceEtape() + ");\n" + et.iterator().next().toC();

                //chaque guichet est suivi d'une activité restreinte
        //StringBuilder build = new StringBuilder();

        //build.append(res).append(et.iterator().next().toC());


        return res;
    }
}
