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

        return res;
    }

    @Override
    public String defineName() {
        Etape et = this.getGestionnaire().getListeetapes().iterator().next();

        return "\n#define " +getSemaphoreName() + this.getSemaphore() +
                "\n#define "+ getDefineName() + getIndiceEtape() +
                et.defineName();
    }

    @Override
    public String getDefineName() {
        String res = this.getNom();
        res = res.replace(" ","_");
        res = res.replace("é","e");
        res = res.replace("è","e");
        res = res.replace("à","a");
        res = res.replace("ê","e");
        res = res.replace("ë","e");
        res = res.replace("-","_");
        return "GUICHET_" + res + " ";
    }

    public String getSemaphoreName()
    {
        String res = this.getNom();
        res = res.replace(" ","_");
        res = res.replace("é","e");
        res = res.replace("è","e");
        res = res.replace("à","a");
        res = res.replace("ê","e");
        res = res.replace("ë","e");
        res = res.replace("-","_");
        return "GUICHET_SEMAPHORE_"+res + " ";
    }
}
