package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;

public class EcouteurMenuEntree implements EventHandler<ActionEvent> {

    MondeIG monde;

    public EcouteurMenuEntree(MondeIG monde){
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
      for(EtapeIG etape : monde){
          if(etape.getSelection()){
              etape.setEstUneEntree(!etape.getEstUneEntree());
          }
      }
        monde.notifierObservateurs();

    }
}
