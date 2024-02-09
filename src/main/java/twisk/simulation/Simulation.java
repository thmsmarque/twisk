package twisk.simulation;

import twisk.monde.Monde;
import twisk.monde.Etape;

import java.util.Iterator;

public class Simulation {

    public Simulation()
    {

    }

    public void simuler(Monde monde)
    {
        System.out.println("Notre monde possède :\n" +
                "\t\tNombre étapes   : " + monde.nbEtapes()+"\n" +
                "\t\tNombre guichets : "+monde.nbGuichets()+"\n");
        Iterator<Etape> it = monde.iterator();
        while(it.hasNext())
        {
            Etape etape = it.next();
            System.out.println(etape.toString());
        }
    }

}
