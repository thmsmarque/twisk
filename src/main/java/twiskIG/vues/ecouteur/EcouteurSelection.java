package twiskIG.vues.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * Classe de l'écouteur de sélection
 */
public class EcouteurSelection  implements EventHandler<MouseEvent> {

    private MondeIG monde;
    private EtapeIG etape;

    private ArcIG arc;

    /**
     * Constructeur de la classe EcouteurSelection
     * @param monde
     */
   public EcouteurSelection(MondeIG monde, EtapeIG etape){
       this.monde=monde;
       this.etape=etape;
       this.arc=null;
   }

   public EcouteurSelection(MondeIG monde, ArcIG arc){
        this.monde = monde;
        this.arc = arc;
        this.etape=null;
   }


    @Override
    public void handle(MouseEvent mouseEvent) {
       if(arc==null) {
           this.etape.setSelection(!etape.getSelection());
       } else if (etape==null){
           this.arc.setSelection(!arc.getSelection());
       }
        monde.notifierObservateurs();
    }
}
