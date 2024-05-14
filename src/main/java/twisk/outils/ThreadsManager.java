package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager {

    final static ThreadsManager instance = new ThreadsManager();

    ArrayList<Thread> threads;

    private ThreadsManager()
    {
        threads = new ArrayList<>();
    }

    public static ThreadsManager getInstance()
    {
        return instance;
    }

    /**
     * Lance l'execution d'une tâche via un nouveau thread
     * @param task tâche à éxecuter
     */
    public void lancer(Task task)
    {
        Thread thread = new Thread(task);
        this.threads.add(thread);
        thread.start();
    }

    /**
     * Détruit tous les threads existants
     */
    public void detruireTout()
    {
        //!! Se contente d'interronpre sans tuer les threads !!
        for(Thread thread : threads)
        {
            thread.interrupt();
        }

    }



}
