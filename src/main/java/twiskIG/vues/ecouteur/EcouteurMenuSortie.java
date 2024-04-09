package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

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
