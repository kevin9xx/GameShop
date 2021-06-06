package ch.bzz.bookshelf.data;

import ch.bzz.bookshelf.model.Game;
import ch.bzz.bookshelf.model.Publisher;
import ch.bzz.bookshelf.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;



public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String, Game> gameMap;
    private static Map<String, Publisher> publisherMap;


    private DataHandler() {
        gameMap = new HashMap<>();
        publisherMap = new HashMap<>();
        readJSON();
    }


    public static Game readGame(String gameUUID) {
        Game game = new Game();
        if (getGameMap().containsKey(gameUUID)) {
            game = getGameMap().get(gameUUID);
        }
        return game;
    }


    public static void saveGame(Game game) {
        getGameMap().put(game.getGameUUID(), game);
        writeJSON();
    }


    public static Publisher readPublisher(String publisherUUID) {
        Publisher publisher = new Publisher();
        if (getPublisherMap().containsKey(publisherUUID)) {
            publisher = getPublisherMap().get(publisherUUID);
        }
        return publisher;
    }

    public static void savePublisher(Publisher publisher) {
        getPublisherMap().put(publisher.getPublisherUUID(), publisher);
        writeJSON();
    }


    public static Map<String, Game> getGameMap() {
        return gameMap;
    }


    public static Map<String, Publisher> getPublisherMap() {
        return publisherMap;
    }

    public static void setPublisherMap(Map<String, Publisher> publisherMap) {
        DataHandler.publisherMap = publisherMap;
    }


    private static void readJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("gameJSON")));
            ObjectMapper objectMapper = new ObjectMapper();

            Game[] games = objectMapper.readValue(jsonData, Game[].class);
            for (Game game : games) {
                String publisherUUID = game.getPublisher().getPublisherUUID();
                Publisher publisher;

                if (getPublisherMap().containsKey(publisherUUID)) {
                    publisher = getPublisherMap().get(publisherUUID);
                }

                else {
                    publisher = game.getPublisher();
                    getPublisherMap().put(publisherUUID, publisher);
                }

                game.setPublisher(publisher);
                getGameMap().put(game.getGameUUID(), game);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        Writer writer;
        FileOutputStream fileOutputStream = null;

        String bookPath = Config.getProperty("gameJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectMapper.writeValue(writer, getGameMap().values());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

