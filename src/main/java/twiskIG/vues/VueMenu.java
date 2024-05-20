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
    private MenuItem quitter,supprimer,renommer,desactiver, entree, sortie, parametres,sauvegarder,charger,client;
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
        sauvegarder = new MenuItem("Sauvegarder");
        sauvegarder.setOnAction(new EcouteurSauvegarder(monde));

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
        entree.setAccelerator(KeyCombination.keyCombination("Ctrl+e"));
        entree.setDisable(true);

        sortie = new MenuItem("Sortie");
        sortie.setOnAction(new EcouteurMenuSortie(monde));
        sortie.setAccelerator(KeyCombination.keyCombination("Alt+e"));
        sortie.setDisable(true);

        //Parametre
        parametres = new MenuItem("Paramètres de l'activite");
        parametres.setOnAction(new EcouteurMenuParametre(monde));
        parametres.setAccelerator(KeyCombination.keyCombination("Ctrl+p"));
        parametres.setDisable(true);

        //Clients :
        client = new MenuItem("Nombre de clients");
        client.setOnAction(new EcouteurSetClients(monde));



        menu2.getItems().addAll(supprimer,renommer,desactiver);
        menu.getItems().addAll(quitter,sauvegarder);
        menu3.getItems().addAll(entree,sortie);
        menu4.getItems().addAll(parametres,client);
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
        if(nb!=0) {
            entree.setDisable(false);
            sortie.setDisable(false);
        } else {
            entree.setDisable(true);
            sortie.setDisable(true);
        }
        if(nb!=1) afficher = false;
        renommer.setDisable(!afficher);
        parametres.setDisable(!afficher);
        if(monde.getEnCoursDeSim()) {
            entree.setDisable(monde.getEnCoursDeSim());
            sortie.setDisable(monde.getEnCoursDeSim());
        }
    }
}
