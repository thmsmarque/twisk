package twiskIG.mondeIG;

import twiskIG.exceptions.TwiskException;
import twiskIG.outils.TailleComposants;
import twiskIG.vues.Observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe MondeIG
 */
public class MondeIG  extends SujetObserve implements Observateur, Iterable<EtapeIG>{
    private HashMap<String,EtapeIG> map;

    private ArrayList<ArcIG> arcs;

    private PointDeControleIG pointSauv;

    /**
     * Constructeur de MondeIG
     */
    public MondeIG(){
        this.map = new HashMap<>();
        this.pointSauv=null;
        arcs = new ArrayList<>();
        //this.ajouter("Activite0");
    }

    /**
     * Ajouter un arc au monde
     * @param pt1, pt2
     */
    public void ajouter(PointDeControleIG pt1,PointDeControleIG pt2) throws TwiskException {
        //Il y a déjà un arc avec les deux points
        if ((pt1 == pt2)) {
            throw new TwiskException("Nous ne pouvons pas faire un arc avec le même point");
        }

        for (ArcIG arc : arcs) {
            //Recréer le même arc
            if ((arc.getP1() == pt1 && arc.getP2() == pt2) || (arc.getP2() == pt1 && arc.getP1() == pt2)) {
                throw new TwiskException("Il y a déjà un arc qui relie ses deux activités");
            }

            // Pt de contrôle appartient à la même activité
            if ((pt1.getIdentifiantEtape() == arc.getP1().getIdentifiantEtape() && pt2.getIdentifiantEtape() == arc.getP2().getIdentifiantEtape())
                    || (pt2.getIdentifiantEtape() == arc.getP1().getIdentifiantEtape() && pt1.getIdentifiantEtape() == arc.getP2().getIdentifiantEtape())) {
                throw new TwiskException("Il y a déjà un arc reliant ses deux activités");
            }
        }
        arcs.add(new ArcIG(pt1, pt2));
        this.pointSauv = null;
    }


    public ArrayList<ArcIG> getArcs() {
        return arcs;
    }

    /**
     * Ajoute une étape définie par le type
     * @param type
     */
    public void ajouter(String type, int num){
        switch(type)
        {
            case "Activite" :
                System.out.println("AJout théorique d'une activité : " + type + num);
                EtapeIG m = new ActiviteIG(type+num, TailleComposants.getInstance().gettailleVBOXActivite()*2,TailleComposants.getInstance().gettailleVBOXActivite());
                map.put(m.getIdentifiant(),m);
                this.notifierObservateurs();
                break;
            case "Guichet" :
                System.out.println("AJout théorique d'un guichet : " + type+num);
                GuichetIG g = new GuichetIG(type+num, TailleComposants.getInstance().getTailleCoteGuichetPlace()*5,TailleComposants.getInstance().getTailleVBOXGuichet());
                map.put(g.getIdentifiant(),g);
                this.notifierObservateurs();
                break;
        }
    }

    /**
     * Renvoie le dernier point de sauvegarde cliqué
     * @return
     */
    public PointDeControleIG getPointSauv() {
        return pointSauv;
    }

    /**
     * Récupère le dernier point de sauvegarde cliqué
     * @return
     */
    public void setPointSauv(PointDeControleIG pointSauv) {
        this.pointSauv = pointSauv;
    }


    /**
     * Iterateur des étapes mémorisées
     * @return La valeur de l'activité
     */
    @Override
    public Iterator<EtapeIG> iterator() {
        return map.values().iterator();
    }

    public Iterator<ArcIG> iteratorarc() {return arcs.iterator(); }


    public void supprimerEtapeSelecMonde(EtapeIG etape){
        // Supprimer les etapes :
        HashMap<String,EtapeIG> maptemp = new HashMap<>();
        maptemp.putAll(map);
        maptemp.remove(etape.getIdentifiant());
        map = maptemp;

    }

    /**
     * Supprimer tous les arcs reliés à une activité, car cette dernière sera supprimées
     * @param etape
     */
    public void supprimerArcMonde(EtapeIG etape){
        //Supprimer les arcs :
        etape.clearAllSuccPrec();
        ArrayList<ArcIG> arctemp = new ArrayList<>();
        arctemp.addAll(arcs);
        for(ArcIG arc : arcs) {
            if (!(map.containsKey(arc.getP1().getIdentifiantEtape())) || !(map.containsKey(arc.getP2().getIdentifiantEtape()) || arc.getSelection())){
                arctemp.remove(arc);
            }
        }
        arcs=arctemp;
    }

    public void supprimerArc(){

        //Suppression des arcs :
        ArrayList<ArcIG> arctemp = new ArrayList<>();
        arctemp.addAll(arcs);
        for(ArcIG arc : arcs)
        if(arc.getSelection()){
            arctemp.remove(arc);
            //Mise à jour des predecesseurs / successeurs :
            EtapeIG e1 = arc.getP1().getEtape();
            EtapeIG e2 = arc.getP2().getEtape();
            e1.supprimerSuccesseurs(e2);
            e2.supprimerPredecesseurs(e1);
        }
        arcs = arctemp;
    }

    public void supprimerSelection(){
        for (ArcIG arc : arcs){
            if(arc.getSelection()) arc.setSelection(!arc.getSelection());
        }
        for(EtapeIG etape : map.values()){
            if(etape.getSelection()) etape.setSelection(!etape.getSelection());
        }
    }
    @Override
    public String toString() {
        return map.toString();
    }

    /**
     * RÉcupère la hashmap
     * @return
     * Pour les tests
     */
    public HashMap<String, EtapeIG> getMap() {
        return map;
    }

    @Override
    public void reagir() {
        
    }
}

/*
RAPPEL :
Guichet ne epeut pas être une sortie
On ne rentre pas dans une activité restreinte
Si on a une étape, on doit pouvoir y avoir accès depuis une sortie
Si on a une étape, il doit y avoir des successeurs menant à une sortie
 */