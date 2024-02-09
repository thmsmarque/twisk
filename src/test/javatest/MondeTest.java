package javatest;

import main.java.twisk.monde.Activite;
import main.java.twisk.monde.Etape;
import main.java.twisk.monde.Monde;
import main.java.twisk.outils.FabriqueNumero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {

    @Test
    void aCommeEntree() {
    }

    @Test
    void aCommeSortie() {
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
    void nbGuichets() {
    }
}