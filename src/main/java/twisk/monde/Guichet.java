package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

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
        //ids à vérifiere
        Iterator<Etape> etapes = this.gestionnaire.getListeetapes().iterator();
        Etape et = this.getGestionnaire().getListeetapes().iterator().next();
        //System.out.println("Est ici (guichet) "+ et);
        String res = "P("+ "ids"+ ","+"1"+"); \n" +
                "transfert("+this.getDefineName()+","
                + et.getDefineName()  +");\n" +
                et.toC() +
                "V("+ "ids,"+ this.getSemaphoreName()+");\n"
                +"transfert("+ et.getDefineName()
                + "," + et.iterator().next().getDefineName() + ");\n" + et.iterator().next().toC();

        StringBuilder build;
        int nb = gestionnaire.nbEtapes();
        if(nb>1) {
            build = new StringBuilder(res + "\n");
            build.append("int nb = (int) ((rand()/(float)RAND_MAX)*"+nb+"); \n" +
                    "switch(nb) { \n");
            for(int i = 0; i< nb;i++){
                build.append("case "+i+" : \n" +
                        etapes.next().toC()+"\n"+
                        "break; \n");
            }
            build.append("default : \n" +
                    "perror(\"Erreur lors de la création\"); \n" +
                    "break;" +
                    "} \n");
            res = build.toString();
        }

        return res;
    }

    @Override
    public String defineName() {
        Etape et = this.getGestionnaire().getListeetapes().iterator().next();

        return "\n#define SEM_GUICHET_"+this.getNom()+" "+this.getSemaphore() +
                "\n#define GUICHET_"+this.getNom()+" "+this.getIndiceEtape() +
                et.defineName();
    }

    @Override
    public String getDefineName() {
        return "GUICHET_"+this.getNom();
    }

    public String getSemaphoreName()
    {
        return "SEM_GUICHET_"+this.getNom();
    }
}
