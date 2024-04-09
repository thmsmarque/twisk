package twiskIG.vues;

import javafx.scene.layout.Pane;
import twisk.mondeIG.*;
import twisk.vues.ecouteur.EcouteurPanneauDragOver;
import twisk.vues.ecouteur.EcouteurPanneauDropped;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Classe VueMondeIG
 */
public class VueMondeIG extends Pane implements Observateur  {

    private ArrayList<VueArcIG> arcs;

private final MondeIG monde;
    public VueMondeIG(MondeIG monde){
        super();
        this.monde=monde;
        this.arcs = new ArrayList<>();

        monde.ajouterObservateur(this);
        for(EtapeIG etape : monde){
            VueEtapeIG vueetape = new VueActiviteIG(monde,etape);
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
            VueEtapeIG vueetape = new VueActiviteIG(this.monde, etape);
            getChildren().add(vueetape );
            for(PointDeControleIG pc : etape){
                //TEMP :
                getChildren().add(new VuePointDeControle(this.monde,pc));
            }


        }

    }
}
