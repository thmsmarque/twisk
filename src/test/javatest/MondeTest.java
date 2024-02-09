package javatest;

import main.java.twisk.monde.Activite;
import main.java.twisk.monde.Etape;
import main.java.twisk.monde.Monde;
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
        Monde m = new Monde();
        assertTrue(m.getLesEtapes().estVide());
        Etape e = new Activite("Activite");
        m.ajouter(e);
        assertEquals(m.getLesEtapes().nbEtapes(),1);

    }


    @Test
    void nbGuichets() {
    }
}