package twiskIG.vues.ecouteur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.outils.ThreadsManager;
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

        if(!m.getEnCoursDeSim()) {
            //Si aucune simulation n'est en cours
            System.out.println("Lancement de la simulation...");
            m.notifierObservateurs();
            SimulationIG sim = new SimulationIG(m);
        }else
        {
            //Si une simulation est en cours
            m.switchEtatSim();
            m.notifierObservateurs();
        }
    }
}
