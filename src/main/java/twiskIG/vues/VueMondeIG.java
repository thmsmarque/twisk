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

private final MondeIG monde;
    public VueMondeIG(MondeIG monde){
        super();
        this.monde=monde;
        this.arcs = new ArrayList<>();
        this.etapes = new ArrayList<>();

        monde.ajouterObservateur(this);
        for(EtapeIG etape : monde){
            VueEtapeIG vueetape = new VueActiviteIG(monde,etape);
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
        this.getChildren().clear();


        Iterator<ArcIG> arcs = monde.iteratorarc();
        while(arcs.hasNext()){
            getChildren().add(new VueArcIG(this.monde,arcs.next()));
        }


        int tmp = 10;
        for (EtapeIG etape : this.monde) {
            if(etape.estActivite())
            {
                VueEtapeIG vueetape = new VueActiviteIG(this.monde, etape);
                getChildren().add(vueetape );
            }
            if(etape.estGuichet())
            {
                VueEtapeIG vueetape = new VueGuichetIG(this.monde, (GuichetIG)etape);
                getChildren().add(vueetape );
            }

            for(PointDeControleIG pc : etape){
                getChildren().add(new VuePointDeControle(this.monde,pc));
            }
//AJOUTER LES CLIENTS :------------------------------
            for(VueEtapeIG vueEtapeIG : etapes){
                if(vueEtapeIG.getEtape().estActivite() || vueEtapeIG.getEtape().estActiviteRestreinte()) {
                    VueActiviteIG ac = (VueActiviteIG)vueEtapeIG;
                    for (ClientIG client : vueEtapeIG.getEtape().getClientsDansEtape()) {
                        HBox box = ac.getBox();

                        Runnable command = new Runnable()
                        {
                            public void run()
                            {
                                box.getChildren().add(new VueClientIG(vueEtapeIG.getEtape()));
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
                }else if(vueEtapeIG.getEtape().estGuichet())
                {
                    VueGuichetIG guich = (VueGuichetIG)vueEtapeIG;
                    for (ClientIG client : vueEtapeIG.getEtape().getClientsDansEtape()) {
                        guich.getChildren().add(new VueClientIG(vueEtapeIG.getEtape()));
                    }
                }

            }

        }

    }
}
