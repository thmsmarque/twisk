package twisk.simulation;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Monde;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    public void testConstructeur(){
        Etape e = new Activite("Activite",5,5);
        Client c = new Client(5);
        c.allerA(e,0);
        assertSame(c.getEtape(),e);
        assertSame(c.getNumeroClient(),5);
        assertSame(c.getRang(),0);
    }

}