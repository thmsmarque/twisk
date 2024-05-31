package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public class ActiviteRestreinte extends Activite {
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    public ActiviteRestreinte(String nom, int temps, int ecartTemps) {
        super(nom, temps, ecartTemps);
    }

    public String toC(int semaphore) {
        int nbSuccesseur = this.gestionnaire.nbEtapes();

        System.out.println("Nombre d'étape de " + getNom() + " " + nbSuccesseur);


        Iterator<Etape> it = this.getGestionnaire().iterator();
        Etape et = it.next();
        String res = "";
        if (nbSuccesseur == 1) {
            //Dans le cas où l'étape actuelle n'a qu'un seul successeur
            res = "delai(" + this.temps + "," + this.ecartTemps + ");\n" +
                    "V(" + "ids," + semaphore + ");\n" +
                    "transfert(" + this.getDefineName() + "," + et.getDefineName() + ");\n" + et.toC();
        } else if (nbSuccesseur > 1) {
            //Dans le cas où l'étape actuelle a plusieurs successeurs
            res = "nb = (int) ((rand() / (float) RAND_MAX)*" + this.getGestionnaire().nbEtapes() + ");\n";
            StringBuilder build = new StringBuilder(res);

            int i = 0;
            Iterator<Etape> it2 = this.getGestionnaire().iterator();

            while (it2.hasNext()) {
                System.out.println("N'avait pas de suivant");
                et = it2.next();
                String suivant = et.toC();
                String actuel = "delai(" + this.temps + "," + this.ecartTemps + ");\n" +
                        "V(" + "ids," + semaphore + ");\n" +
                        "transfert(" + this.getDefineName() + "," + et.getDefineName() + ");\n";
                if (i == 0) {
                    build.append("\n if (nb==").append(i).append(") { \n").append(actuel).append(" ").append(suivant).append("\n}");
                } else {
                    build.append(" \n else if (nb ==").append(i).append(") { \n").append(actuel).append(" ").append(suivant).append("\n  \n}");
                }
                i++;
                res = build.toString();
            }
        }
     return res;
    }
}



