package persistence;

import model.CardGame;
import model.Card;
import model.Pair;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;

import org.json.*;

/**
 * Represents a reader that reads CardGame from JSON data stored in file
 */
public class JsonReader {
    private String source;

    // credit: GitHub "JsonSerializationDemo"
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // credit: GitHub "JsonSerializationDemo"
    public CardGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCardGame(jsonObject);
    }

    // credit: GitHub "JsonSerializationDemo"
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses CardGame from JSON object and returns it
    private CardGame parseCardGame(JSONObject jsonObject) {
        ArrayList<Card> cards = parseCards(jsonObject);
        ArrayList<Pair> validPairs = parseValidPairs(jsonObject);
        ArrayList<Pair> allPairs = parseAllPairs(jsonObject);
        CardGame cg = new CardGame(cards, validPairs, allPairs);
        return cg;
    }

    // EFFECTS: parses Cards from JSON object and returns them
    private ArrayList<Card> parseCards(JSONObject jsonObject) {
        ArrayList<Card> cards = new ArrayList<Card>();

        JSONArray jsonArray = jsonObject.getJSONArray("cards");
        for (Object json : jsonArray) {
            JSONObject cardObject = (JSONObject) json;
            Card card = parseCard(cardObject);
            cards.add(card);
        }
        return cards;
    }

    // EFFECTS: parses Cards from JSON object and adds it to workroom
    private Card parseCard(JSONObject jsonObject) {
        int num = jsonObject.getInt("num");
        String operation = jsonObject.getString("operation");
        Card card = new Card(num, operation);
        return card;
    }

    // EFFECTS: parses validPairs from JSON object and returns them
    private ArrayList<Pair> parseValidPairs(JSONObject jsonObject) {
        ArrayList<Pair> validPairs = new ArrayList<Pair>();

        JSONArray jsonArray = jsonObject.getJSONArray("validPairs");
        for (Object json : jsonArray) {
            JSONObject pairObject = (JSONObject) json;
            Pair pair = parsePair(pairObject);
            validPairs.add(pair);
        }
        return validPairs;
    }

    // EFFECTS: parses allPairs from JSON object and returns them
    private ArrayList<Pair> parseAllPairs(JSONObject jsonObject) {
        ArrayList<Pair> allPairs = new ArrayList<Pair>();

        JSONArray jsonArray = jsonObject.getJSONArray("allPairs");
        for (Object json : jsonArray) {
            JSONObject pairObject = (JSONObject) json;
            Pair pair = parsePair(pairObject);
            allPairs.add(pair);
        }
        return allPairs;
    }

    // EFFECTS: parses Pairs from JSON object and adds it to workroom
    private Pair parsePair(JSONObject jsonObject) {
        Card card1 = parseCard(jsonObject.getJSONObject("card1"));
        Card card2 = parseCard(jsonObject.getJSONObject("card2"));
        Pair pair = new Pair(card1, card2);
        return pair;
    }
}
