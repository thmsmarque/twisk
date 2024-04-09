package twiskIG.vues.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import twiskIG.mondeIG.MondeIG;
import twiskIG.vues.VueEtapeIG;
import twiskIG.vues.VueMondeIG;

public class EcouteurPanneauDropped implements EventHandler<DragEvent> {

    private VueMondeIG vue;
    private  MondeIG monde;

    public EcouteurPanneauDropped(MondeIG monde, VueMondeIG vue){
        this.vue=vue;
        this.monde = monde;
    }
    @Override
    public void handle(DragEvent dragEvent) {
        if(dragEvent.getDragboard().hasString()){
            String id = dragEvent.getDragboard().getString();
            VueEtapeIG vue = (VueEtapeIG) this.vue.lookup("#"+id);
            if(vue!=null){
                int x = (int) dragEvent.getX();
                int y = (int) dragEvent.getY();
                vue.getEtape().setPosX(x);
                vue.getEtape().setPosY(y);
                vue.relocate(x,y);
                vue.getEtape().setPointsdeC();
            }
            dragEvent.consume();
            this.monde.notifierObservateurs();
        }
    }
}
