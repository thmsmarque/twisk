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
        //mise  à jour des clients dans les étapes concernés :
        Etape ancienneEtape = mapclient.get(numeroClient).getEtape();
        ancienneEtape.retirerClient(numeroClient);

        // ----------------------------------------------
        mapclient.get(numeroClient).allerA(etape,rang);
        //-----------------------------------------------
        //Ajout du client dans la nouvelle étape :
        etape.ajouterClient(getClient(numeroClient));
    }

    /**
     * fait le ménage dans les clients, pour traiter une nouvelle simulation
     */
    public void nettoyer(){
        mapclient.clear();
    }

    public Client getClient(int numeroClient){
        return mapclient.get(numeroClient);
    }
    @Override
    public Iterator<Client> iterator() {
        return mapclient.values().iterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Gestionnaire : \n");
        for(Client c : mapclient.values()){
            s.append(c);
            s.append("\n");
        }
        return s.toString();
    }
}
