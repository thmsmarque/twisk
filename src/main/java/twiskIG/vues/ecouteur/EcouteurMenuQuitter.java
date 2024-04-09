package twiskIG.vues.ecouteur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurMenuQuitter implements EventHandler<ActionEvent> {
    public EcouteurMenuQuitter() {
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Platform.exit();
    }
}