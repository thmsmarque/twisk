package twiskIG.mondeIG;;
import twisk.vues.*;

import java.util.ArrayList;

public class SujetObserve {

    private ArrayList<Observateur> listeobs=new ArrayList<>();
    public void ajouterObservateur(Observateur v){
        this.listeobs.add(v);
    }

    public void notifierObservateurs(){
        for(Observateur o : listeobs){
            o.reagir();
        }
    }
}
