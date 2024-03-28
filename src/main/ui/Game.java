package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import model.CardGame;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * Facilitates the gameplay of a card game through a console-based interface. This class handles
 * user input, processes commands, and displays relevant information back to the user,
 * acting as the controller of the card game experience.
 *
 * The game is initiated with 4 cards and offers the following moves to the user each 'turn':
 * - Selecting a pair of cards to evaluate for validity according to the game's rules.
 * - Adding 2 more cards to the game's current set to continue gameplay.
 * - Showing statistics including the number of valid pairs selected and the player's accuracy.
 * - Displaying a history of all pairs selected during the game session, indicating their validity.
 * - Exiting the game.
 */
public class Game {
    private CardGame game;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/game.json";

    // EFFECTS: initializes game and scanner
    public Game() throws FileNotFoundException {
        game = new CardGame();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
//        runGame();
    }

    // EFFECTS: Getter that returns Game instance
    public CardGame getGame() {
        return game;
    }

//    // MODIFIES: this
//    // EFFECTS: processes user input in loop until "exit" command is processed
//    private void runGame() {
//        String command = null;
//        while (true) {
//            System.out.println(game.getCurrentCards());
//            displayMenuOptions();
//            command = input.next();
//            command = command.toLowerCase();
//            if (command.equals("e")) {
//                break;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nThanks for playing! Hope you learned something!");
//    }

//    // EFFECTS: processes command and determines next step based on input
//    private void processCommand(String command) {
//        if (command.equals("p")) {
//            processPairSelection();
//        } else if (command.equals("m")) {
//            addMoreCards();
//        } else if (command.equals("g")) {
//            showStatistics();
//        } else if (command.equals("h")) {
//            showAllPairs();
//        } else if (command.equals("s")) {
//            saveGame();
//        } else if (command.equals("l")) {
//            loadGame();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }

    // credit: GitHub "JsonSerializationDemo"
    // EFFECTS: saves the workroom to file
    public void saveGame() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.game);
            this.jsonWriter.close();
            System.out.println("Saved game of " + this.game.getNumCurrentCards() + " cards to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // credit: GitHub "JsonSerializationDemo"
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadGame() {
        try {
            this.game = jsonReader.read();
            System.out.println("Loaded game of " + this.game.getNumCurrentCards() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

//    // EFFECTS: processes user input regarding card selection and handles errors
//    //          (non-integer or non-valid values)
//    private void processPairSelection() {
//        String firstInput = null;
//        String secondInput = null;
//        System.out.println("\nChoose first card by typing its number");
//        firstInput = input.next();
//        System.out.println("\nChoose second card by typing its number");
//        secondInput = input.next();
//        try {
//            // Checking to make sure inputs are integers
//            int c1 = Integer.parseInt(firstInput);
//            int c2 = Integer.parseInt(secondInput);
//
//            // Checking to make inputs corresponds to card indices
//            if (c1 > 0 && c1 <= game.getNumCurrentCards() && c2 > 0 && c2 <= game.getNumCurrentCards()) {
//                System.out.println("Card numbers entered: " + c1 + " and " + c2);
//                selectPair(c1 - 1, c2 - 1);
//            } else {
//                System.out.println("Error: Invalid cards selected. Please try again.");
//                processPairSelection();
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("Error: Invalid input entered. Please try again and enter integers only.");
//            processPairSelection();
//        }
//    }

    // EFFECTS: selects given cards and prints result
    public Boolean selectPair(int c1, int c2) {
        Boolean valid = game.selectPair(c1, c2);
        return valid;
//        if (valid) {
//            System.out.println((c1 + 1) + " and " + (c2 + 1) + " are a pair!");
//        } else {
//            System.out.println((c1 + 1) + " and " + (c2 + 1) + " are not a pair... Try again");
//        }
    }

    // EFFECTS: adds two more cards to game
    public void addMoreCards() {
        game.drawCards(2);
        System.out.println("Added four more cards!");
    }

    // EFFECTS: prints information on the number of valid selected pairs and accuracy
    public String getStatistics() {
        int numValidPairs = game.getNumValidPairs();
        int numTotalPairs = game.getNumPairs();
        float percentageAccuracy = 0;
        if (numTotalPairs != 0) {
            percentageAccuracy = ((float) numValidPairs / numTotalPairs) * 100;
        }
        String output = "PLAYER STATISTICS" + "\nNumber of valid pairs: " + numValidPairs
                + "\nNumber of wrong guesses: " + (numTotalPairs - numValidPairs)
                + "\nAccuracy: " + percentageAccuracy + "%";
        return output;
    }

//    // EFFECTS: prints information on all pairs selected so far and their cards/validity
//    private void showAllPairs() {
//        String allPairsInfo = game.getAllPairsString();
//        System.out.println("HISTORY OF ALL PAIRS SELECTED");
//        System.out.println(allPairsInfo);
//    }

//    // EFFECTS: prints menu of options for user
//    public void displayMenuOptions() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tp -> select pair");
//        System.out.println("\tm -> add more cards");
//        System.out.println("\tg -> show game statistics");
//        System.out.println("\th -> show history of all selected pairs");
//        System.out.println("\ts -> save game to file");
//        System.out.println("\tl -> load game from file");
//        System.out.println("\te -> exit");
//    }
}