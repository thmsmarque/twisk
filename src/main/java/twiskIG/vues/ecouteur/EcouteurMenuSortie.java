package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;

public class EcouteurMenuSortie implements EventHandler<ActionEvent> {

    MondeIG monde;

    public EcouteurMenuSortie(MondeIG monde){
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        for(EtapeIG etape : monde){
            if(etape.getSelection()){
                etape.setEstUneSortie(!etape.getEstUneSortie());
            }
            monde.notifierObservateurs();
        }
    }
}
