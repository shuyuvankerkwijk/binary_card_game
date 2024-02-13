package ui;

import java.util.Scanner;
import model.CardGame;

import javax.swing.*;

public class Game {
    private CardGame game;
    private Scanner input;

    // EFFECTS: Runs the game
    public Game() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input in loop until "exit" command is processed
    private void runGame() {
        String command = null;
        init();
        while (true) {
            System.out.println(game.getCurrentCards());
            displayMenuOptions();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("e")) {
                break;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for playing! Hope you learned something!");
    }

    // EFFECTS: processes command and determines next step based on input
    private void processCommand(String command) {
        if (command.equals("p")) {
            processPairSelection();
        } else if (command.equals("m")) {
            addMoreCards();
        } else if (command.equals("s")) {
            showStatistics();
        } else if (command.equals("h")) {
            showAllPairs();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: processes user input regarding card selection and handles errors
    //          (non-integer or non-valid values)
    private void processPairSelection() {
        String firstInput = null;
        String secondInput = null;
        System.out.println("\nChoose first card by typing its number");
        firstInput = input.next();
        System.out.println("\nChoose second card by typing its number");
        secondInput = input.next();
        try {
            // Checking to make sure inputs are integers
            int c1 = Integer.parseInt(firstInput);
            int c2 = Integer.parseInt(secondInput);

            // Checking to make inputs corresponds to card indices
            if (c1 > 0 && c1 <= game.getNumCurrentCards() && c2 > 0 && c2 <= game.getNumCurrentCards()) {
                System.out.println("Card numbers entered: " + c1 + " and " + c2);
                selectPair(c1 - 1, c2 - 1);
            } else {
                System.out.println("Error: Invalid cards selected. Please try again.");
                processPairSelection();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input entered. Please try again and enter integers only.");
            processPairSelection();
        }
    }

    // EFFECTS: selects given cards and prints result
    private void selectPair(int c1, int c2) {
        Boolean valid = game.selectPair(c1, c2);
        if (valid) {
            System.out.println(c1 + " and " + c2 + " are a pair!");
        } else {
            System.out.println(c1 + " and " + c2 + " are not a pair... Try again");
        }
    }

    // EFFECTS: adds two more cards to game
    private void addMoreCards() {
        game.drawCards(2);
        System.out.println("Added two more cards!");
    }

    // EFFECTS: prints information on the number of valid selected pairs and accuracy
    private void showStatistics() {
        int numValidPairs = game.getNumValidPairs();
        int numTotalPairs = game.getNumPairs();
        float percentageAccuracy = 0;
        if (numTotalPairs != 0) {
            percentageAccuracy = (numValidPairs / numTotalPairs) * 100;
        }
        System.out.println("PLAYER STATISTICS");
        System.out.println("Number of valid pairs: " + numValidPairs);
        System.out.println("Number of wrong guesses: " + (numTotalPairs - numValidPairs));
        System.out.println("Accuracy: " + percentageAccuracy);
    }

    // EFFECTS: prints information on all pairs selected so far and their cards/validity
    private void showAllPairs() {
        String allPairsInfo = game.getAllPairs();
        System.out.println("HISTORY OF ALL PAIRS SELECTED");
        System.out.println(allPairsInfo);
    }

    // MODIFIES: this
    // EFFECTS: initializes game and scanner
    private void init() {
        game = new CardGame();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: prints menu of options for user
    public void displayMenuOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> select pair");
        System.out.println("\tm -> add more cards");
        System.out.println("\ts -> show game statistics");
        System.out.println("\th -> show history of all selected pairs");
        System.out.println("\te -> exit");
    }
}