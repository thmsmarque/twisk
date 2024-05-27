package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twiskIG.mondeIG.MondeIG;

import java.io.File;

public class EcouteurChargerMonde implements EventHandler<ActionEvent> {

    MondeIG monde;
    public EcouteurChargerMonde(MondeIG monde)
    {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        File file = new File("sauv.json");
        monde.chargerMonde(file);
    }
}
