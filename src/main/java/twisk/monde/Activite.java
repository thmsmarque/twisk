package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public class Activite extends Etape {

    int temps;
    int ecartTemps;

    public Activite(String nom){
        super(nom);
        this.temps=3;
        this.ecartTemps=1;
    }

    /**
     * Constructeur de la classe Activite
     * @param nom
     * @param temps
     * @param ecartTemps
     */

    public Activite(String nom, int temps, int ecartTemps){
        super(nom);

        assert(temps>=0);
        assert(ecartTemps>=0);

        this.temps = temps;
        this.ecartTemps = ecartTemps;
    }


    /**
     * @return l'écart temp de l'activité
     */
    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * @return le temps de l'activité
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Vérifie si l'objet est une activité
     * @return true si l'objet est une activite
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public String toString() {
        return "Activite{" +
                " nom= " + getNom() +
                " temps=" + temps +
                ", ecartTemps=" + ecartTemps +
                ", gestionnaire=" + gestionnaire +
                '}';
    }

    public String toC() {
        Iterator<Etape> it = this.getGestionnaire().iterator();
        Etape et = it.next();
        String res;
        if(!it.hasNext())
        {
            //Dans le cas où l'étape actuelle n'a qu'un seul successeur
            res = "delai("+this.temps+","+this.ecartTemps+");\n" +
                    "transfert("+this.getDefineName()+","+et.getDefineName()+");\n" + et.toC();
        }else
        {
            //Dans le cas où l'étape actuelle a plusieurs successeurs
            res = "int nb = (int) ((rand() / (float) RAND_MAX)*" + this.getGestionnaire().nbEtapes() + ");\n" +
                    "switch(nb){\n";
            StringBuilder build = new StringBuilder(res);

            int i = 0;
            while(it.hasNext())
            {
                build.append("\tcase " + i +":" + et.toC() + "break;\n");
                i++;
                et = it.next();
            }
            build.append("perror(\"Erreur lors de la création\"); \n" +
                    "break;" +
                    "} \n");
            res = build.toString();

        }

        return res;
    }


    @Override
    public String defineName() {
        Etape et = this.getGestionnaire().getListeetapes().iterator().next();
        return "\n#define "+this.getDefineName() + this.getIndiceEtape()
                +et.defineName();
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
        return "ACTIVITE_"+res + " ";
    }


}
