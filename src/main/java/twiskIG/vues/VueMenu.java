package twiskIG.vues;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.vues.ecouteur.*;

/**
 * Classe VueMenu
 */
public class VueMenu extends MenuBar implements Observateur {

    private MondeIG monde;
    private MenuItem quitter,supprimer,renommer,desactiver, entree, sortie, parametres;
    public VueMenu(MondeIG monde){
        super();
        this.monde=monde;

        Menu menu = new Menu("Fichier");
        Menu menu2 = new Menu("Edition");
        Menu menu3 = new Menu("Monde");
        Menu menu4 = new Menu("Parametres");

        //Quitter :
        quitter = new MenuItem("Quitter");
        quitter.setOnAction(new EcouteurMenuQuitter());

        //Supprimer
        supprimer = new MenuItem("Supprimer");
        supprimer.setOnAction(new EcouteurMenuSupprimer(monde));
        supprimer.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));


        //Renommer


        renommer = new MenuItem("Renommer la sélection");
        renommer.setOnAction(new EcouteurMenuRenommer(monde));
        renommer.setAccelerator(KeyCombination.keyCombination("Ctrl+r"));
        renommer.setDisable(true);

        // Désactiver la sélection

        desactiver = new MenuItem("Effacer la selection");
        desactiver.setOnAction(new EcouteurDesactiverSelection(monde));

        //entree sortie
        entree = new MenuItem("Entree");
        entree.setOnAction(new EcouteurMenuEntree(monde));

        sortie = new MenuItem("Sortie");
        sortie.setOnAction(new EcouteurMenuSortie(monde));

        //Parametre
        parametres = new MenuItem("Paramètres de l'activite");
        parametres.setOnAction(new EcouteurMenuParametre(monde));
        parametres.setAccelerator(KeyCombination.keyCombination("Ctrl+p"));
        parametres.setDisable(true);
        
        menu2.getItems().addAll(supprimer,renommer,desactiver);
        menu.getItems().addAll(quitter);
        menu3.getItems().addAll(entree,sortie);
        menu4.getItems().addAll(parametres);
        this.getMenus().addAll(menu,menu2,menu3,menu4);

        monde.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        boolean afficher = true;
        int nb = 0;
        for(EtapeIG etape : monde){
            if(etape.getSelection()){
                nb+=1;
            }
        }
        if(nb!=1) afficher = false;
        renommer.setDisable(!afficher);
        parametres.setDisable(!afficher);
    }
}