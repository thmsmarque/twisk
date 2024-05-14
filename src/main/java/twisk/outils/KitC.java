package twisk.outils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.lang.Process;

public class KitC {
    /**
     * Constructeur de KitC
     */
    public KitC(){

    }

    /**
     * Crée le répertoire twisk sous /tmp/ puis y recopie les deux fichiers programmeC.o et def.h
     */
    public void creerEnvironnement(){
        Path directory = Paths.get("/tmp/twisk");
        try {
// création du répertoire twisk sous /tmp.
// Ne déclenche pas d’erreur si le répertoire existe déjà
            Files.createDirectories(directory);
// copie des fichiers programmeC.o et def.h sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h","codeNatif.o"};
            for (String nom : liste) {
                InputStream src = getClass().getResourceAsStream("/codeC/" + nom);
                Path dest = directory.resolve(nom);
                Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée le fichier client.c directement dans le répertoire /tmp/twisk et y écrit le code C fourni en paramètre
     * @param codeC
     */
    public void creerFichier(String codeC){
        FileWriter flot;
        BufferedWriter flotfiltre;
        try{
            flot = new FileWriter("/tmp/twisk/client.c"); //remettre le bon chemin quand ^ fonctionnera
            flotfiltre = new BufferedWriter(flot);
            flotfiltre.write(codeC);
            flotfiltre.close();
        } catch(IOException e){
            System.out.println("erreur lors de la creation de fichier");
        }

    }

    /**
     * Automatise la compilation du fichier client.c
     */
    public void compiler(){
        ProcessBuilder pb = new ProcessBuilder("gcc","-Wall","-ansi","-pedantic","-fPIC","-c","/tmp/twisk/client.c","-o","/tmp/twisk/client.o");
        try {
            pb.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Automatise la construction de la bibliothèque libTwisk.so
     */
    public void construireLaBibliothese(){
        ProcessBuilder pb = new ProcessBuilder("gcc","-shared","/tmp/twisk/programmeC.o","/tmp/twisk/codeNatif.o","/tmp/twisk/client.o","-o","/tmp/twisk/libTwisk"+FabriqueNumeroLibTwisk.getInstance().getNumero()+".so");
        try {
            pb.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode détruit tous les processus (les clients, elle détruit les clients)
     * @param pid client à détruire
     */
    public void detruireLesProcessus(int pid)
    {

    }

}
