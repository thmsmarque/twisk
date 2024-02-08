package main.java.twisk.simulation;

import main.java.twisk.monde.Monde;
public class Simulation {

    public Simulation()
    {

    }

    public void simuler(Monde monde)
    {
        System.out.println("Notre monde possède :\n" +
                "\t\tNombre étapes   : " + monde.nbEtapes()+"\n" +
                "\t\tNombre guichets : "+monde.nbGuichets()+"\n");
    }

}
