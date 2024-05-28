package twiskIG.vues.ecouteur;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.*;
import twiskIG.vues.VueEtapeIG;

public class EcouteurSource implements EventHandler<MouseEvent> {


    private VueEtapeIG vue;
   // public final Dragboard db;

    public ClipboardContent clip;
    public EcouteurSource( VueEtapeIG vue) {
        this.vue = vue;


    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        Dragboard db = vue.startDragAndDrop(TransferMode.MOVE);
        clip = new ClipboardContent();
        clip.putString(this.vue.getId());
        clip.putImage(vue.snapshot(new SnapshotParameters(),null));
        db.setContent(clip);
        mouseEvent.consume();
    }
}
