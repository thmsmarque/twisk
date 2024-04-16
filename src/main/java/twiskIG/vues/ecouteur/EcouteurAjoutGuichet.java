package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twiskIG.mondeIG.MondeIG;

public class EcouteurAjoutGuichet implements EventHandler<ActionEvent> {

    private MondeIG monde;
    private int num;

    /**
     * Constructeur EcouteurOutils
     */
    public EcouteurAjoutGuichet(MondeIG monde, int num){
        this.monde=monde;
        this.num=num;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajouter("Guichet",num);
        this.monde.notifierObservateurs();
    }
}
