package javatest;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.GestionnaireEtapes;
import twisk.monde.Guichet;
import twisk.outils.FabriqueNumero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireEtapesTest {

    @Test
    void constructor(){
        GestionnaireEtapes g = new GestionnaireEtapes();
        assertTrue(g.getListeetapes().isEmpty());
    }

    @Test
    void ajouterTest(){

        GestionnaireEtapes g = new GestionnaireEtapes();
        Etape e = new Activite("Activite");
        g.ajouter(e);

        assertEquals(g.getListeetapes().get(0),e);
    }
    @Test
    void nbEtapes() {

        GestionnaireEtapes g = new GestionnaireEtapes();
        Etape e = new Activite("Activite");
        Etape e1 = new Activite("Activite");
        Etape e2 = new Activite("Activite");

        Etape e3 = new Guichet("Guichet");
        Etape e4 = new Guichet("Guichet");

        assertEquals(g.nbEtapes(),0);


        g.ajouter(e,e1,e2,e3,e4);
        assertEquals(g.nbEtapes(),5);
    }

}