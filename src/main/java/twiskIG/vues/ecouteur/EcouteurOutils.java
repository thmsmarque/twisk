package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twiskIG.mondeIG.MondeIG;

/**
 * Classe de l'écouteur outils
 */
public class EcouteurOutils implements EventHandler<ActionEvent> {

    private MondeIG monde;
    private int num;

    /**
     * Constructeur EcouteurOutils
     */
    public EcouteurOutils(MondeIG monde, int num){
    this.monde=monde;
    this.num=num;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajouter("Activite" + num);
        this.monde.notifierObservateurs();
    }
}
