package twiskIG.vues;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import twiskIG.mondeIG.*;
import twiskIG.vues.ecouteur.EcouteurPanneauDragOver;
import twiskIG.vues.ecouteur.EcouteurPanneauDropped;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Classe VueMondeIG
 */
public class VueMondeIG extends Pane implements Observateur  {

    private ArrayList<VueArcIG> arcs;
    private ArrayList<VueEtapeIG> etapes;
    private boolean simEnCours = false;


    private final MondeIG monde;
    public VueMondeIG(MondeIG monde){
        super();
        this.monde=monde;
        this.arcs = new ArrayList<>();
        this.etapes = new ArrayList<>();

        monde.ajouterObservateur(this);
        for(EtapeIG etape : monde){
            VueEtapeIG vueetape = new VueActiviteIG(monde,etape,getSimEnCours());
            etapes.add(vueetape);
            getChildren().add(vueetape);
        }
        this.reagir();
        monde.notifierObservateurs();
        setOnDragOver(new EcouteurPanneauDragOver());
        setOnDragDropped(new EcouteurPanneauDropped(monde,this));
    }

    public MondeIG getMonde() {
        return monde;
    }

    @Override
    public void reagir() {
        this.simEnCours=monde.getEnCoursDeSim(); //true = en cours en simulation
        Pane pane = this;
        Runnable command = new Runnable() {
            @Override
            public void run() {
                etapes.clear();
                pane.getChildren().clear();


                Iterator<ArcIG> arcs = monde.iteratorarc();
                while(arcs.hasNext()){
                    VueArcIG vuearc = new VueArcIG(monde,arcs.next(),getSimEnCours());
                    vuearc.setSimEnCours(getSimEnCours());
                    pane.getChildren().add(vuearc);
                }


                int tmp = 10;
                for (EtapeIG etape : monde) {
                    if(etape.estActivite())
                    {
                        VueEtapeIG vueetape = new VueActiviteIG(monde, etape,getSimEnCours());
                        etapes.add(vueetape);
                        pane.getChildren().add(vueetape );
                    }
                    if(etape.estGuichet())
                    {
                        VueEtapeIG vueetape = new VueGuichetIG(monde, (GuichetIG)etape,getSimEnCours());
                        etapes.add(vueetape);
                        getChildren().add(vueetape );
                    }

                    for(PointDeControleIG pc : etape){
                        VuePointDeControle vuepdc = new VuePointDeControle(monde,pc,getSimEnCours());
                        getChildren().add(vuepdc);
                    }
//AJOUTER LES CLIENTS :------------------------------
                    System.out.println("les étapes :" + etapes);
                    for(VueEtapeIG vueEtapeIG : etapes){
                        //System.out.println("les étapes youpiiii");

                        if(vueEtapeIG.getEtape().estActivite() || vueEtapeIG.getEtape().estActiviteRestreinte()) {
                            System.out.println("les activité youpiiii");

                            VueActiviteIG ac = (VueActiviteIG)vueEtapeIG;
                            for (ClientIG client : vueEtapeIG.getEtape().getClientsDansEtape()) {
                                HBox box = ac.getBox();
                                box.getChildren().add(new VueClientIG());


                            }
                        }
                        if(vueEtapeIG.getEtape().estGuichet())
                        {
                            System.out.println("les guichets youpiiii");

                            VueGuichetIG guich = (VueGuichetIG)vueEtapeIG;
                            ArrayList<HBox> listBox = guich.getBoxList();
                            Iterator<HBox> it = listBox.iterator();
                            for (ClientIG client : vueEtapeIG.getEtape().getClientsDansEtape()) {
                                HBox pane = it.next();
                                System.out.println(pane);
                                pane.getChildren().add(new VueClientIG());
                            }
                        }

                    }

                }
            }
        };
        if(Platform.isFxApplicationThread())
        {
            command.run();
        }else
        {
            Platform.runLater(command);
        }


    }

    public void setSimEnCours(boolean estSelectionne) {
        this.simEnCours = estSelectionne;
    }

    public boolean getSimEnCours(){
        return this.simEnCours;
    }
}
