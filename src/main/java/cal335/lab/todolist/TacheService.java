package cal335.lab.todolist;

import cal335.lab.todolist.modele.Tache;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TacheService {
    private List<Tache> listeDesTaches = new ArrayList<>();

    public void creerTache(HttpExchange echange) throws IOException {

        InputStream is = echange.getRequestBody();
        String body = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonObject = objectMapper.readTree(body);

        String nom = jsonObject.get("nom").asText();
        String description = jsonObject.get("description").asText();
        boolean indicateur = jsonObject.get("indicateur").asBoolean();
        Tache tache = new Tache(nom, description, indicateur);

        if (tache != null) {
            listeDesTaches.add(tache);
            echange.sendResponseHeaders(201, -1); // Créé
            System.out.println("added");
        } else {
            echange.sendResponseHeaders(400, -1);
        }

        String tacheSerialisee = objectMapper.writeValueAsString(tache);
        System.out.println("Tâche en JSON : " + tacheSerialisee);

        Tache tacheDeserialisee = objectMapper.readValue(tacheSerialisee, Tache.class);
        System.out.println("Description de la tâche : " + tacheDeserialisee.getDescription());

    }

    public void chercherTaches(HttpExchange echange) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode response = objectMapper.createArrayNode();

        for ( Tache tache : listeDesTaches) {
            response.add(objectMapper.valueToTree(tache));
            System.out.println(response.add(objectMapper.valueToTree(tache)));
        }

        byte[] bytes = objectMapper.writeValueAsString(response).getBytes();
        echange.sendResponseHeaders(200, bytes.length);
        OutputStream os = echange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
