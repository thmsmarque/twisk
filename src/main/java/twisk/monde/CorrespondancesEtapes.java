package twisk.monde;

import twiskIG.mondeIG.EtapeIG;

import java.util.ArrayList;
import java.util.HashMap;

public class CorrespondancesEtapes {

    HashMap<EtapeIG, Etape> etapes;

    public CorrespondancesEtapes()
    {
        etapes = new HashMap<EtapeIG,Etape>();
    }

    /**
     * Cette méthode ajoute à l'hashmap l'etapeIG et son Etape correspondante
     * @param etapeIG etape de l'interface graphique
     * @param etape etape logique
     */
    public void ajouter(EtapeIG etapeIG, Etape etape)
    {
        etapes.put(etapeIG, etape);
    }

    /**
     * Renvoi l'Etape associée à l'EtapeIG
     * @param etapeIG etape de l'interface graphique
     * @return l'étape logique correpondant à etapeIG
     */
    public Etape get(EtapeIG etapeIG)
    {
        return etapes.get(etapeIG);
    }

}
