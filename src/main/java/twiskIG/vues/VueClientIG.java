package twiskIG.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twiskIG.mondeIG.ClientIG;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.vues.ecouteur.EcouteurVuePointDeControleIG;

public class VueClientIG extends Circle implements Observateur{

    public ClientIG client;

    public VueClientIG(ClientIG client, MondeIG monde) {
        this.client = client;

        setCenterX(client.getPosx());
        setCenterY(client.getPosy());

        setRadius(5);
        setFill(Color.GREENYELLOW);
    }
    @Override
    public void reagir() {

    }
}
