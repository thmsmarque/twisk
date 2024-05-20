package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twiskIG.mondeIG.MondeIG;

public class EcouteurSauvegarder implements EventHandler<ActionEvent> {

    MondeIG monde;
    public EcouteurSauvegarder(MondeIG monde)
    {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        monde.sauvegarderMonde();
    }
}
