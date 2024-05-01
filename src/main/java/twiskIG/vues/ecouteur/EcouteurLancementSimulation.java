package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twiskIG.mondeIG.MondeIG;
import twiskIG.mondeIG.SimulationIG;

public class EcouteurLancementSimulation implements EventHandler<ActionEvent> {

    MondeIG m;
    public EcouteurLancementSimulation(MondeIG m)
    {
        this.m = m;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        SimulationIG sim = new SimulationIG(m);
    }
}
