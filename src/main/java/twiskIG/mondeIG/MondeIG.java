package twiskIG.mondeIG;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import twiskIG.exceptions.TwiskException;
import twiskIG.outils.CustomExclusionStrategy;
import twiskIG.outils.FabriqueIdentifiant;
import twiskIG.outils.TailleComposants;
import twiskIG.vues.Observateur;

import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Classe MondeIG
 */
public class MondeIG  extends SujetObserve implements Observateur, Iterable<EtapeIG>{
    @Expose
    private HashMap<String,EtapeIG> map;
    @Expose
    private ArrayList<ArcIG> arcs;

    private PointDeControleIG pointSauv;

    private boolean enCoursDeSim;

    private int nbCLient;

    private String proba;

    private double tempsSim;

    /**
     * Constructeur de MondeIG
     */
    public MondeIG(){
        this.map = new HashMap<>();
        this.pointSauv=null;
        arcs = new ArrayList<>();
        enCoursDeSim = false;
        this.nbCLient=10;
        this.proba = "uniforme";
        this.tempsSim=-1.0;
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
        if(pt1.getEtape().estGuichet())
        {
            //Si l'entrée de cet arc est un guichet, alors définir le sens de la file
            GuichetIG guich = (GuichetIG)pt1.getEtape();
            if(pt1.equals(pt1.getEtape().getPointsdeC().get(0)))
            {
                //Si le point correspond au point de gauche
                guich.setSens(false);
            }else
            {
                //Si le point correspond au point de droite
                guich.setSens(true);
            }
        }


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
        this.notifierObservateurs();
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
                GuichetIG g = new GuichetIG(type+num, TailleComposants.getInstance().getTailleCoteGuichetPlace()*15,TailleComposants.getInstance().getTailleVBOXGuichet());
                map.put(g.getIdentifiant(),g);
                this.notifierObservateurs();
                break;
        }
    }

    /**
     * Retourne l'étape correspondant à son identifiant
     * @param id identifiant de l'étape
     * @return l'étape correspondante
     */
    public EtapeIG getEtapeByID(String id)
    {
        if(map.containsKey(id))
        {
            return (EtapeIG)map.get(id);
        }else
            return null;
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


    public void sauvegarderMonde(File file)
    {
        Gson gson = new Gson();
        try
        {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter ecrire = new BufferedWriter(fileWriter);


            ecrire.write(gson.toJson(this.map.size()));
            ecrire.newLine();
            for(EtapeIG e : this.map.values())
            {
                if(e.estGuichet())
                {
                    //Si l'étape est un guicht
                    GuichetIG guich = (GuichetIG) e;
                    ecrire.write(gson.toJson('g'));
                    ecrire.newLine();
                    ecrire.write(gson.toJson(guich.getNbJeton()));
                    ecrire.newLine();
                    ecrire.write(gson.toJson(guich.getSens()));
                    ecrire.newLine();
                }
                else
                {
                    //Si l'étape est une activité
                    ecrire.write(gson.toJson('a'));
                    ecrire.newLine();
                }

                ecrire.write(gson.toJson(e.getNom()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getIdentifiant()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getLargeur()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getHauteur()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getPosX()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getPosY()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getDelai()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getEcart()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getEstUneEntree()));
                ecrire.newLine();
                ecrire.write(gson.toJson(e.getEstUneSortie()));
                ecrire.newLine();

            }


            ecrire.write(gson.toJson(this.arcs.size()));
            ecrire.newLine();
            for(ArcIG a : this.arcs)
            {
                    ecrire.write(a.getP1().getEtape().getIdentifiant());
                    ecrire.newLine();
                    ecrire.write(a.getP1().getPositionSurEtape());
                    ecrire.newLine();
                ecrire.write(a.getP2().getEtape().getIdentifiant());
                ecrire.newLine();
                ecrire.write(a.getP2().getPositionSurEtape());
                ecrire.newLine();
                System.out.println(a.getP1().getPositionSurEtape() + ":"+a.getP1().getEtape().getNom()+ "\t\t" + a.getP2().getPositionSurEtape() + ":"+a.getP2().getEtape().getNom());

            }

            ecrire.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void resetModne()
    {
        this.map.clear();
        this.arcs.clear();

        this.supprimerSelection();

        FabriqueIdentifiant.getInstance().reset();
    }

    public void chargerMonde(File file)
    {
        resetModne();
        Gson gson = new Gson();
        try
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader lire = new BufferedReader(fileReader);


            int size = gson.fromJson(lire.readLine(), int.class);
            for(int i = 0;i < size;i++)
            {
                char typeEtape = gson.fromJson(lire.readLine(), char.class);


                if(typeEtape == 'g')
                {
                    GuichetIG temp;
                    int nombreJetons = gson.fromJson(lire.readLine(), int.class);
                    boolean sens = gson.fromJson(lire.readLine(), boolean.class);
                    String nomEtape = gson.fromJson(lire.readLine(), String.class);
                    String idEtape = gson.fromJson(lire.readLine(), String.class);
                    int largeur = gson.fromJson(lire.readLine(), int.class);
                    int hauteur = gson.fromJson(lire.readLine(), int.class);
                    int posX = gson.fromJson(lire.readLine(), int.class);
                    int posY = gson.fromJson(lire.readLine(), int.class);
                    int delai = gson.fromJson(lire.readLine(), int.class);
                    int ecart = gson.fromJson(lire.readLine(), int.class);
                    boolean estUneEntree = gson.fromJson(lire.readLine(), boolean.class);
                    boolean estUneSortie = gson.fromJson(lire.readLine(), boolean.class);


                    temp = new GuichetIG(nomEtape, largeur, hauteur);
                    try {
                        temp.changerNbJeton(nombreJetons);
                    } catch (TwiskException e) {
                        throw new RuntimeException(e);
                    }
                    temp.setSens(sens);
                    temp.setIdentifiant(idEtape);
                    temp.setPosX(posX);
                    temp.setPosY(posY);
                    temp.setPointsdeC();
                    temp.setEstUneEntree(estUneEntree);
                    temp.setEstUneSortie(estUneSortie);
                    try {
                        temp.setDelai(delai);
                        temp.setEcart(ecart);
                    } catch (TwiskException e) {
                        throw new RuntimeException(e);
                    }

                    this.map.put(idEtape, temp);


                }
                else
                {
                    ActiviteIG ac;
                    String nomEtape = gson.fromJson(lire.readLine(), String.class);
                    String idEtape = gson.fromJson(lire.readLine(), String.class);
                    int largeur = gson.fromJson(lire.readLine(), int.class);
                    int hauteur = gson.fromJson(lire.readLine(), int.class);
                    int posX = gson.fromJson(lire.readLine(), int.class);
                    int posY = gson.fromJson(lire.readLine(), int.class);
                    int delai = gson.fromJson(lire.readLine(), int.class);
                    int ecart = gson.fromJson(lire.readLine(), int.class);
                    boolean estUneEntree = gson.fromJson(lire.readLine(), boolean.class);
                    boolean estUneSortie = gson.fromJson(lire.readLine(), boolean.class);

                    ac = new ActiviteIG(nomEtape, largeur, hauteur);
                    ac.setPosX(posX);
                    ac.setPosY(posY);
                    ac.setPointsdeC();
                    ac.setEstUneEntree(estUneEntree);
                    ac.setEstUneSortie(estUneSortie);
                    ac.setIdentifiant(idEtape);
                    try {
                        ac.setDelai(delai);
                        ac.setEcart(ecart);
                    } catch (TwiskException e) {
                        throw new RuntimeException(e);
                    }
                    this.map.put(idEtape, ac);
                }


            }

            size = gson.fromJson(lire.readLine(), int.class);
            for(int i = 0;i < size;i++)
            {
                String IdEtape1 = gson.fromJson(lire.readLine(), String.class);
                String pos1 = gson.fromJson(lire.readLine(), String.class);
                String IdEtape2 = gson.fromJson(lire.readLine(), String.class);
                String pos2 = gson.fromJson(lire.readLine(), String.class);

                PointDeControleIG p1 = getEtapeByID(IdEtape1).getPointDeC(Integer.parseInt(pos1));
                PointDeControleIG p2 = getEtapeByID(IdEtape2).getPointDeC(Integer.parseInt(pos2));

                try {
                    ajouter(p1,p2);
                } catch (TwiskException e) {
                    throw new RuntimeException(e);
                }

            }

            lire.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
        this.notifierObservateurs();
    }

    public int getNbGuichets()
    {
        int res = 0;
        for(EtapeIG e : map.values())
        {
            if(e.estGuichet()) res++;
        }
        return res;
    }

    public int getNbActivite()
    {
        int res = 0;
        for(EtapeIG e : map.values())
        {
            if(e.estActivite()) res++;
        }
        return res;
    }

    @Override
    public void reagir() {
    }

    public int getNbCLient() {
        return nbCLient;
    }

    public void setNbCLient(int nbCLient) {
        this.nbCLient = nbCLient;
    }

    public void setProba(String proba) {
        this.proba = proba;
    }

    public double getTempsSim() {
        return tempsSim;
    }

    public void setTempsSim(double tempsSim) {
        this.tempsSim = tempsSim;
    }

    public String getProba() {
        return proba;
    }
}
