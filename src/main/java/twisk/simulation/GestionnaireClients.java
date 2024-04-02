package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe GestionnaireClients
 */
public class GestionnaireClients implements Iterable<Client> {
    private HashMap<Integer,Client> mapclient;

    /**
     * Constructeur du GestionnaireClients
     */
    public GestionnaireClients(){
        mapclient = new HashMap<>();
    }

    /**
     * instancie les clients identifiés par leur numéro de processus (numéro de client)
     * @param tabClients
     */
    public void setClients(int...tabClients){
      for(int i : tabClients){
          mapclient.put(i,new Client(i));
      }

    }

    /**
     *  met à jour les attributs etape et rang d’un client
     * @param numeroClient numero unique du client
     * @param etape  est l’étape du Monde dans laquelle se trouve le client,
     * @param rang  visualise un ordre dans une file d’attente
     */
    public void allerA(int numeroClient, Etape etape, int rang){
    mapclient.get(numeroClient).allerA(etape,rang);
    }

    /**
     * fait le ménage dans les clients, pour traiter une nouvelle simulation
     */
    public void nettoyer(){
        mapclient.clear();
    }
    @Override
    public Iterator<Client> iterator() {
        return mapclient.values().iterator();
    }
}
