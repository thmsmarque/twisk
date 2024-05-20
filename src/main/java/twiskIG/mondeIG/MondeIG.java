package twiskIG.mondeIG;

import twiskIG.exceptions.TwiskException;
import twiskIG.outils.TailleComposants;
import twiskIG.vues.Observateur;

import java.util.*;

/**
 * Classe MondeIG
 */
public class MondeIG  extends SujetObserve implements Observateur, Iterable<EtapeIG>{
    private HashMap<String,EtapeIG> map;

    private ArrayList<ArcIG> arcs;

    private PointDeControleIG pointSauv;

    private boolean enCoursDeSim;

    /**
     * Constructeur de MondeIG
     */
    public MondeIG(){
        this.map = new HashMap<>();
        this.pointSauv=null;
        arcs = new ArrayList<>();
        enCoursDeSim = false;
        //this.ajouter("Activite0");
    }

    /**
     * Ajouter un arc au monde
     * @param pt1, pt2
     */
    public void ajouter(PointDeControleIG pt1,PointDeControleIG pt2) throws TwiskException {
        //Il y a déjà un arc avec les deux points
        if (estMemePdc(pt1, pt2)) throw new TwiskException("Nous ne pouvons pas faire un arc avec le même point");

        if (arcExisteDeja(pt1, pt2)) throw new TwiskException("Il y a déjà un arc qui relie ses deux activités");

        if (arcMemeActivite(pt1, pt2)) throw new TwiskException("Il y a déjà un arc reliant ses deux activités");

        if (estAccessibleDepuis(pt1.getEtape(), pt2.getEtape()))
            throw new TwiskException("Il y a un cycle entre " + pt1.getEtape().getNom() + " et " + pt2.getEtape().getNom());
        arcs.add(new ArcIG(pt1, pt2));
        this.pointSauv = null;
    }

    public boolean estMemePdc(PointDeControleIG pt1, PointDeControleIG pt2) {
        if ((pt1 == pt2)) {
            return true;
        }
        return false;
    }

    /**
     * On vérifie qu'on n'est pas en train de recrééer le même arc
     * @param pt1
     * @param pt2
     * @return
     */
    public boolean arcExisteDeja(PointDeControleIG pt1, PointDeControleIG pt2) {
        for (ArcIG arc : arcs) {
            if ((arc.getP1() == pt1 && arc.getP2() == pt2) || (arc.getP2() == pt1 && arc.getP1() == pt2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * On vérifie que le Point de contrôle n'appartient pas à la même activité
     * @param pt1
     * @param pt2
     * @return
     */
    public boolean arcMemeActivite(PointDeControleIG pt1, PointDeControleIG pt2) {
        for (ArcIG arc : arcs) {
            if ((pt1.getIdentifiantEtape() == arc.getP1().getIdentifiantEtape() && pt2.getIdentifiantEtape() == arc.getP2().getIdentifiantEtape())
                    || (pt2.getIdentifiantEtape() == arc.getP1().getIdentifiantEtape() && pt1.getIdentifiantEtape() == arc.getP2().getIdentifiantEtape())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Renvoi l'état de la simulation
     * @return état de la simulation
     */
    public boolean getEnCoursDeSim()
    {
        return enCoursDeSim;
    }

    /**
     * Inverse l'état de la simulation
     * @return le nouvel état de la simulation
     */
    public boolean switchEtatSim()
    {
        enCoursDeSim = !enCoursDeSim;
        System.out.println("L'état de la sim est passée à"+enCoursDeSim);
        //this.notifierObservateurs();
        return enCoursDeSim;
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
        for(EtapeIG et : etape.successeurs.values())
        {
            et.supprimerPredecesseurs(etape);
        }
        for(EtapeIG et : etape.predecesseurs.values())
        {
            et.supprimerSuccesseurs(etape);
        }

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
     * On vérifie si il existe un chemin en partant des successeurs de la racine, menant vers le candidat
     * @param candidat
     * @param racine
     * @return true si il y a un cycle entre le candidat et la racine
     */
    public boolean estAccessibleDepuis(EtapeIG candidat, EtapeIG racine){
        Stack<EtapeIG> stack = new Stack<>();
        HashSet<EtapeIG> visited = new HashSet<>();
        stack.push(racine);

        while (!stack.isEmpty()) { //on cherche jusqu'à trouver la racine
            EtapeIG etapeEnCours = stack.pop();
            if (visited.contains(etapeEnCours)) {
                continue;
            }
            visited.add(etapeEnCours);
            if (etapeEnCours==candidat) {
                return true;
            }
            for (EtapeIG succ : etapeEnCours.getSuccesseurs().values()) {
                if (!visited.contains(succ)) {
                    stack.push(succ);
                }
            }
        }
        return false;
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