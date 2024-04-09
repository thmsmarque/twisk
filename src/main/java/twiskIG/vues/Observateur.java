package twiskIG.vues;

import twisk.mondeIG.SujetObserve;

public interface Observateur {
    public SujetObserve sujet = null;

    public void reagir();
}
