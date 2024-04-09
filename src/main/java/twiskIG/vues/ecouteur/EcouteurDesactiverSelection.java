package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

public class EcouteurDesactiverSelection implements EventHandler<ActionEvent> {

    private MondeIG monde;
    public EcouteurDesactiverSelection(MondeIG monde){
        this.monde = monde;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        monde.supprimerSelection();
        monde.notifierObservateurs();

    }
}
