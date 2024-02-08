package main.java.twisk;

import main.java.twisk.monde.*;
import main.java.twisk.simulation.Simulation;
public class ClientTwisk {

    public static void main(String[] args) {
        Monde monde = new Monde();
        Simulation sim = new Simulation();
        sim.simuler(monde);
    }
}
