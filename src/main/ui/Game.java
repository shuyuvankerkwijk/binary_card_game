package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.CardGame;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * Facilitates the gameplay of a card game through a console-based interface. This class handles
 * user input, processes commands, and displays relevant information back to the user,
 * acting as the controller of the card game experience.
 * The game is initiated with 4 cards and offers the following moves to the user each 'turn':
 * - Selecting a pair of cards to evaluate for validity according to the game's rules.
 * - Adding 2 more cards to the game's current set to continue gameplay.
 * - Showing statistics including the number of valid pairs selected and the player's accuracy.
 * - Displaying a history of all pairs selected during the game session, indicating their validity.
 * - Exiting the game.
 */
public class Game {
    private CardGame cardGame;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/game.json";

    // EFFECTS: initializes game and scanner
    public Game() throws FileNotFoundException {
        cardGame = new CardGame();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Getter that returns Game instance
    public CardGame getCardGame() {
        return cardGame;
    }

    // credit: GitHub "JsonSerializationDemo"
    // EFFECTS: saves the workroom to file
    public void saveGame() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.cardGame);
            this.jsonWriter.close();
            System.out.println("Saved game of " + this.cardGame.getNumCurrentCards() + " cards to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // credit: GitHub "JsonSerializationDemo"
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadGame() {
        try {
            this.cardGame = jsonReader.read();
            System.out.println("Loaded game of " + this.cardGame.getNumCurrentCards() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: selects given cards and prints result
    public Boolean selectPair(int c1, int c2) {
        return cardGame.selectPair(c1, c2);
    }

    // EFFECTS: adds two more cards to game
    public void addMoreCards() {
        cardGame.drawCards(2);
    }

    // EFFECTS: prints information on the number of valid selected pairs and accuracy
    public String getStatistics() {
        int numValidPairs = cardGame.getNumValidPairs();
        int numTotalPairs = cardGame.getNumPairs();
        float percentageAccuracy = 0;
        if (numTotalPairs != 0) {
            percentageAccuracy = ((float) numValidPairs / numTotalPairs) * 100;
        }
        String output = "PLAYER STATISTICS" + "\nNumber of valid pairs: " + numValidPairs
                + "\nNumber of wrong guesses: " + (numTotalPairs - numValidPairs)
                + "\nAccuracy: " + percentageAccuracy + "%";
        return output;
    }
}