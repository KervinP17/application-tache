package cal335.lab.todolist;

import cal335.lab.todolist.controleur.TacheControleur;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApplicationTacheServer {

    private static final int PORT = 9001;

    public static void main(String[] args) throws IOException {

        HttpServer serveur = HttpServer.create(new InetSocketAddress(PORT), 0);

//        TacheService tacheService = new TacheService();

        serveur.createContext("/taches", new TacheControleur());
        serveur.setExecutor(null);

        serveur.start();

        System.out.println("Serveur démarré sur le port " + PORT);


    }
}
