package ui;

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * Represents the main user interface for the Binary Operations Card Game.
 * This class is responsible for initializing and displaying the game's GUI,
 * including the game board, option buttons, and displays for found card pairs.
 * The UI includes functionalities such as choosing cards, adding more cards to
 * the game board, saving the current game state, loading a saved game state,
 * and viewing statistics of pairs found. These are the functionalities
 */
public class GameUI extends JFrame {

    // dimensions
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    // backend
    private Game game;
    private int c1; // index of first card chosen
    private int c2; // index of second card chosen

    // frontend
    private GameBoardUI gameBoard;
    private JPanel optionsPanel;
    private JButton chooseCardsButton;
    private JButton addCardsButton;
    private  JButton saveGameButton;
    private JButton loadGameButton;
    private JButton statisticsButton;
    private JButton instructionsButton;
    private PairsDisplayPanel pairsDisplayPanel;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        new GameUI();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new instance of Game and calls initializeGUI()
    public GameUI() {
        super("Binary Operations Card Game");
        try {
            game = new Game();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Game file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        initializeGUI();
    }

    // MODIFIES: this
    // EFFECTS: Setter for c1
    public void setC1(int i) {
        this.c1 = i;
    }

    // MODIFIES: this
    // EFFECTS: Setter for c2
    public void setC2(int i) {
        this.c2 = i;
    }

    // EFFECTS: Getter for c1
    public int getC1() {
        return this.c1;
    }

    // EFFECTS: Getter for c1
    public int getC2() {
        return this.c2;
    }

    // MODIFIES: this
    // EFFECTS: Organizes the initialization of the GUI and calls helper functions
    private void initializeGUI() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up game board
        setupGameBoard();

        // set up current cards
        c1 = -1;
        c2 = -1;

        // set up options panel
        setupOptionsPanel();

        // set up found pairs display
        setupPairsDisplay();

        // finish
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates GameBoardUI instance and adds to frame
    private void setupGameBoard() {
        gameBoard = new GameBoardUI(game, this);
        add(gameBoard, BorderLayout.CENTER);
        gameBoard.update();
    }

    // MODIFIES: this
    // EFFECTS: Creates panel of buttons at the bottom of frame
    private void setupOptionsPanel() {
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());

        chooseCardsButton = new JButton("Choose Cards");
        chooseCardsButton.addActionListener(e -> chooseCardsAction());
        optionsPanel.add(chooseCardsButton);

        addCardsButton = new JButton("Add Cards");
        addCardsButton.addActionListener(e -> addCardsAction());
        optionsPanel.add(addCardsButton);

        saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener(e -> saveGameAction());
        optionsPanel.add(saveGameButton);

        loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(e -> loadGameAction());
        optionsPanel.add(loadGameButton);

        statisticsButton = new JButton("See Game Statistics");
        statisticsButton.addActionListener(e -> statisticsAction());
        optionsPanel.add(statisticsButton);

        instructionsButton = new JButton("How to play");
        instructionsButton.addActionListener(e -> showInstructions());
        optionsPanel.add(instructionsButton);

        add(optionsPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Sets up panel with found cards on right of frame
    private void setupPairsDisplay() {
        pairsDisplayPanel = new PairsDisplayPanel(game);
        JLabel textLabel = new JLabel("VALID PAIRS FOUND");
        pairsDisplayPanel.add(textLabel, BorderLayout.NORTH);
        scrollPane = new JScrollPane(pairsDisplayPanel);
        scrollPane.setPreferredSize(new Dimension(220, getHeight()));
        add(scrollPane, BorderLayout.EAST);
        pairsDisplayPanel.update();
    }

    // MODIFIES: this
    // EFFECTS: When ChooseCards button is clicked, checks if two cards have been selected
    //          If true, it calls game to check if they are valid and changes button text
    //          accordingly.
    private void chooseCardsAction() {
        Boolean valid = false;

        if (c1 == -1 || c2 == -1) {
            // pass
        } else {
            valid = game.selectPair(c1, c2);
            c1 = -1;
            c2 = -1;
            update();
        }

        // Change chooseCardsButton color and text based on if pair is valid
        Color originalColor = chooseCardsButton.getBackground();
        if (valid) {
            chooseCardsButton.setBackground(Color.GREEN);
            chooseCardsButton.setText("Valid Pair!");
        } else {
            chooseCardsButton.setBackground(Color.RED);
            chooseCardsButton.setText("Invalid Pair!");
        }

        // Reverts back to original state after 1 second
        Timer timer = new Timer(1000, e -> {
            chooseCardsButton.setBackground(originalColor);
            chooseCardsButton.setText("Choose Cards");
        });
        timer.setRepeats(false);
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: Calls game to add more cards to the board
    private void addCardsAction() {
        game.addMoreCards();
        update();
    }

    // MODIFIES: this
    // EFFECTS: Calls game to save current game
    private void saveGameAction() {
        game.saveGame();
    }

    // MODIFIES: this
    // EFFECTS: Calls game to load saved game
    private void loadGameAction() {
        game.loadGame();
        update();
    }

    // MODIFIES: this
    // EFFECTS: Calls GameBoardUI and PairsDisplayPanel to update
    private void update() {
        gameBoard.update();
        pairsDisplayPanel.update();
    }

    // EFFECTS: Brings up a dialog with statistics for valid pairs so far
    private void statisticsAction() {
        JDialog dialog = new JDialog(this, "Statistics", true);
        dialog.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(game.getStatistics());
        textArea.setWrapStyleWord(true); // Wrap long words
        textArea.setLineWrap(true); // Enable line wrap
        textArea.setEditable(false); // Make the JTextArea non-editable
        textArea.setBackground(dialog.getBackground());

        dialog.add(textArea, BorderLayout.CENTER);
        dialog.setSize(300, 400); // Adjust size as needed
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // EFFECTS: Brings up a dialog with instructions for how to play
    private void showInstructions() {
        JDialog dialog = new JDialog(this, "How to play", true);
        dialog.setLayout(new BorderLayout());

        String instructions = "Objective: Find 'pairs' of two cards where applying the operations on "
                + "both cards to the numbers on the cards results in True for both operations."
                + "\n"
                + "\n"
                + "**Example of 'pairs':**\n"
                + "1. CARD 1: (1, AND) with CARD 2: (1, OR)\n"
                + "2. CARD 1: (0, NAND) with CARD 2: (0, NOR)\n"
                + "3. CARD 1: (1, OR) with CARD 2: (0, NAND)";

        JTextArea textArea = new JTextArea(instructions);
        textArea.setWrapStyleWord(true); // Wrap long words
        textArea.setLineWrap(true); // Enable line wrap
        textArea.setEditable(false); // Make the JTextArea non-editable
        textArea.setBackground(dialog.getBackground());

        dialog.add(textArea, BorderLayout.CENTER);
        dialog.setSize(300, 400); // Adjust size as needed
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}

