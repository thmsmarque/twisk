package javatest;

import twisk.monde.*;
import twisk.outils.FabriqueNumero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {


    @Test
    void nbGuichets() {

        Monde m = new Monde();
        Etape e = new Guichet("Guichet");
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Guichet("Guichet");
        Etape e3 = new Activite("Activite");

        m.ajouter(e,e1,e2,e3);

        assertEquals(m.nbGuichets(),3);
    }


    @Test
    void nbEtapes() {

        Monde m = new Monde();
        assertTrue(m.getLesEtapes().estVide());
        Etape e = new Activite("Activite");
        m.ajouter(e);
        assertEquals(m.getLesEtapes().nbEtapes(),1);

    }

    @Test
    void aCommeEntree() {

        Monde m = new Monde();
        Etape e = new Guichet("Guichet");
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Guichet("Guichet");
        m.aCommeEntree(e);
        GestionnaireEtapes g = m.getEntree().getGestionnaire();
        assertEquals(g.nbEtapes(),1);
        m.ajouter(e1,e2);
        assertEquals(g.nbEtapes(),1);
    }

    @Test
    void aCommeSortie() {

        Monde m = new Monde();
        Etape e = new Guichet("Guichet");
        Etape e1 = new Guichet("Guichet");
        Etape e2 = new Guichet("Guichet");
        m.aCommeSortie(e);
        GestionnaireEtapes g = m.getSortie().getGestionnaire();
        assertEquals(g.nbEtapes(),1);
        m.ajouter(e1,e2);
        assertEquals(g.nbEtapes(),1);

    }


}