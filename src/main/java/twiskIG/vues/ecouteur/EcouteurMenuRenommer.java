package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurMenuRenommer implements EventHandler<ActionEvent> {

    MondeIG monde;
    EtapeIG etape;

    public EcouteurMenuRenommer(MondeIG monde) {
        this.monde = monde;

    }
    //PLus qu'une chose sélectionné : set disable si plusieurs sélection
    @Override
    public void handle(ActionEvent actionEvent) {

        for (EtapeIG etape : monde) {
            if (etape.getSelection()) {
                this.etape = etape;
            }
        }


        TextInputDialog text = new TextInputDialog();
        text.setTitle("Renommer l'activité");
        text.setHeaderText("Veuillez rentrer le nom de l'activité :");
        text.setContentText("");
        Optional<String> result = text.showAndWait();
        result.ifPresent(e -> etape.setNom(e));

        monde.notifierObservateurs();
    }
}
