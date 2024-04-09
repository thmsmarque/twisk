package twiskIG.vues;

import twiskIG.mondeIG.SujetObserve;

public interface Observateur {
    public SujetObserve sujet = null;

    public void reagir();
}
