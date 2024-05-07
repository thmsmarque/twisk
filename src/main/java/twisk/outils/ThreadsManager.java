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

    static ThreadsManager getInstance()
    {
        return instance;
    }

    public void lancer(Task task)
    {

    }

    public void detruireTout()
    {

    }



}
