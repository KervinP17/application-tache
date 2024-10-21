package cal335.lab.todolist.controleur;
import cal335.lab.todolist.TacheService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class TacheControleur implements HttpHandler {

//    private List<Tache> baseDonneesTache;
//
//    public TacheControleur() {
//        baseDonneesTache = new ArrayList<>();
//    }

    TacheService tacheService = new TacheService();


    @Override
    public void handle(HttpExchange echange) throws IOException {
        String methode = echange.getRequestMethod();
        String chemin = echange.getRequestURI().getPath();
        switch (methode) {
            case "GET":
                if (chemin.equals("/taches")) {
                    tacheService.chercherTaches(echange);
                }
                break;
            case "POST":
                tacheService.creerTache(echange);
                break;
            default:
                echange.sendResponseHeaders(405, -1);
        }
    }

//    public void creerTache(HttpExchange echange) throws IOException {
//
//        InputStream is = echange.getRequestBody();
//        String body = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonObject = objectMapper.readTree(body);
//
//        String nom = jsonObject.get("nom").asText();
//        String description = jsonObject.get("description").asText();
//        boolean indicateur = jsonObject.get("indicateur").asBoolean();
//        Tache tache = new Tache(nom, description, indicateur);
//
//
//        if (tache != null) {
//            tacheService.ajouterTaches(tache);
//            echange.sendResponseHeaders(201, -1); // Créé
//            System.out.println("added");
//        } else {
//            echange.sendResponseHeaders(400, -1);
//        }
//
//    }
//
//    public void chercherTaches(HttpExchange echange) throws IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        ArrayNode response = objectMapper.createArrayNode();
//
//        for ( Tache tache : tacheService.getListeDesTaches()) {
//            response.add(objectMapper.valueToTree(tache));
//            System.out.println(response.add(objectMapper.valueToTree(tache)));
//        }
//
//        byte[] bytes = objectMapper.writeValueAsString(response).getBytes();
//        echange.sendResponseHeaders(200, bytes.length);
//        OutputStream os = echange.getResponseBody();
//        os.write(bytes);
//        os.close();
//    }


}
