package javatest;

import main.java.twisk.monde.*;
import main.java.twisk.outils.FabriqueNumero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {


    @Test
    void nbGuichets() {
        FabriqueNumero fn = FabriqueNumero.getInstance();
        Monde m = new Monde();
        Etape e = new Guichet("Guichet",fn);
        Etape e1 = new Guichet("Guichet",fn);
        Etape e2 = new Guichet("Guichet",fn);
        Etape e3 = new Activite("Activite",fn);

        m.ajouter(e,e1,e2,e3);

        assertEquals(m.nbGuichets(),3);
    }


    @Test
    void nbEtapes() {
        FabriqueNumero fn = FabriqueNumero.getInstance();
        Monde m = new Monde();
        assertTrue(m.getLesEtapes().estVide());
        Etape e = new Activite("Activite",fn);
        m.ajouter(e);
        assertEquals(m.getLesEtapes().nbEtapes(),1);

    }

    @Test
    void aCommeEntree() {
        FabriqueNumero fn = FabriqueNumero.getInstance();
        Monde m = new Monde();
        Etape e = new Guichet("Guichet",fn);
        Etape e1 = new Guichet("Guichet",fn);
        Etape e2 = new Guichet("Guichet",fn);
        m.aCommeEntree(e);
        GestionnaireEtapes g = m.getEntree().getGestionnaire();
        assertEquals(g.nbEtapes(),1);
        m.ajouter(e1,e2);
        assertEquals(g.nbEtapes(),1);
    }

    @Test
    void aCommeSortie() {
        FabriqueNumero fn = FabriqueNumero.getInstance();
        Monde m = new Monde();
        Etape e = new Guichet("Guichet",fn);
        Etape e1 = new Guichet("Guichet",fn);
        Etape e2 = new Guichet("Guichet",fn);
        m.aCommeSortie(e);
        GestionnaireEtapes g = m.getSortie().getGestionnaire();
        assertEquals(g.nbEtapes(),1);
        m.ajouter(e1,e2);
        assertEquals(g.nbEtapes(),1);

    }


}