#include "../ressource/codeC/def.h"

/* main du client1
int main(int argc, char** argv) {

    //Préparation du monde
    int nbEtapes = 3;
    int nbGuichets = 0;
    int nbClients = 5;
    int* tabJetonsGuichet = {0};

    //Lancement de la simulation
    int* resSim;
    resSim = start_simulation(nbEtapes,nbGuichets,nbClients,tabJetonsGuichet);

    //Affichage des numéros de processus
    printf("Les clients :");
    for(int i=0; i<nbClients; i++)
    {
        printf("\t%d",resSim[i]);
    }
    printf("\n");

        int* posClients = ou_sont_les_clients(nbEtapes,nbClients);
    //On affiche où sont les clients
    do
    {
        posClients = ou_sont_les_clients(nbEtapes,nbClients);
        sleep(2.3);
        int etapeActuel = 0;
        for(int i = 0; i<nbEtapes;i++)
        {
            if(etapeActuel != 1)
            {
                printf("étape %d %d client(s) : ",i,posClients[i+nbClients*i]);
                int nbClientDansAct = posClients[i+nbClients*i];
                for(int j = i+nbClients*i + 1;j<i+nbClients*i + 1 + nbClientDansAct;j++)
                {
                    //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                    //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                    printf("\t%d",posClients[j]);
                }
                printf("\n");
            }
                etapeActuel++;
        }
        printf("étape %d %d client(s) : ",1,posClients[nbClients + 1]);
                        int nbClientDansAct = posClients[nbClients + 1];
                        for(int j = nbClients + 2 ;j< nbClients + 2 + nbClientDansAct;j++)
                        {
                            //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                            //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                            printf("\t%d",posClients[j]);
                        }
                        printf("\n");
                        etapeActuel++;


    }while(posClients[nbClients+1] != nbClients);//while tous les clients ne sont pas dans le sasSortie donc posClient[nbAct-1 + nbClient * nbAct-1] == nbClient


    //On fait le nettoyage
    nettoyage();

    return 0 ;
    }
    */

    //main du client2
    int main(int argc, char** argv) {

        //Préparation du monde
        int nbEtapes = 4;
        int nbGuichets = 1;
        int nbClients = 10;
        int tabJetonsGuichet[nbGuichets];
        tabJetonsGuichet[0] = 2;

        //Lancement de la simulation
        int* resSim;
        resSim = start_simulation(nbEtapes,nbGuichets,nbClients,tabJetonsGuichet);

        //Affichage des numéros de processus
        printf("Les clients :");
        for(int i=0; i<nbClients; i++)
        {
            printf("\t%d",resSim[i]);
        }
        printf("\n");

            int* posClients = ou_sont_les_clients(nbEtapes,nbClients);
        //On affiche où sont les clients
        do
        {
            posClients = ou_sont_les_clients(nbEtapes,nbClients);
            sleep(1);
            int etapeActuel = 0;
            for(int i = 0; i<nbEtapes;i++)
            {
                if(etapeActuel != 1)
                {
                    printf("étape %d %d client(s) : ",i,posClients[i+nbClients*i]);
                    int nbClientDansAct = posClients[i+nbClients*i];
                    for(int j = i+nbClients*i + 1;j<i+nbClients*i + 1 + nbClientDansAct;j++)
                    {
                        //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                        //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                        printf("\t%d",posClients[j]);
                    }
                    printf("\n");
                }
                    etapeActuel++;
            }
            printf("étape %d %d client(s) : ",1,posClients[nbClients + 1]);
                            int nbClientDansAct = posClients[nbClients + 1];
                            for(int j = nbClients + 2 ;j< nbClients + 2 + nbClientDansAct;j++)
                            {
                                //j est initié à la position dans le tableau posClients où se trouve le premier client de l'étape
                                //S'il n'y a aucun client dans l'étape alors j >= à la condition qui sort de la boucle
                                printf("\t%d",posClients[j]);
                            }
                            printf("\n");
                            etapeActuel++;

            printf("\n\n\n");
        }while(posClients[nbClients+1] != nbClients);//while tous les clients ne sont pas dans le sasSortie donc posClient[nbAct-1 + nbClient * nbAct-1] == nbClient


        //On fait le nettoyage
        nettoyage();

        return 0 ;
        }