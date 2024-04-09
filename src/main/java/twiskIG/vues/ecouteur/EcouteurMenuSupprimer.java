package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;

public class EcouteurMenuSupprimer implements EventHandler<ActionEvent> {

    MondeIG monde;
    public EcouteurMenuSupprimer(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        monde.supprimerArc();
        for(EtapeIG etape : monde) {
            if(etape.getSelection() ) {
                this.monde.supprimerEtapeSelecMonde(etape);
                this.monde.supprimerArcMonde(etape);
            }
            this.monde.notifierObservateurs();
        }
    }
}
