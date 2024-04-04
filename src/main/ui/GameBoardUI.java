package ui;

import model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Represents the game board UI component of the Binary Operations Card Game.
 * This class is responsible for displaying the game cards on the board, allowing
 * players to interact with the cards. It adjusts its layout based on the number
 * of cards currently in play.
 * Each card is represented as a button with an image depicting the card's face.
 * Players interact with the cards by clicking these buttons, which then communicates
 * the player's actions back to the game logic for processing.
 */
public class GameBoardUI extends JPanel {

    private Game game;
    private GameUI gameUI;

    // EFFECTS: Initializes the game board UI with a specified layout and background color. It sets
    //          up references to the game logic and the main game UI.
    public GameBoardUI(Game game, GameUI gameUI) {
        super();
        this.setLayout(new GridLayout(0, 4)); // Adjust grid layout rows/columns based on your game needs
        setBackground(Color.white);
        this.game = game;
        this.gameUI = gameUI;
    }

    // Returns an ImageIcon containing the image for the specified card
    private ImageIcon getCardImage(Card c) {
        String imagePath = "./data/images/";
        String fileName = imagePath + c.getNum() + c.getOperation() + ".png";
        return new ImageIcon(fileName);
    }

    // MODIFIES: this
    // EFFECTS: Updates the panel with the game's current cards
    public void update() {
        removeAll();

        int numCards = game.getCardGame().getNumCurrentCards();

        if (numCards > 8) {
            setLayout(new GridLayout(4, (int) Math.ceil((float) numCards / 4.0)));
        } else {
            setLayout(new GridLayout(2, (numCards / 2)));
        }

        List<Card> currentCards = game.getCardGame().getCurrentCards();
        int index = 0;
        for (Card card : currentCards) {
            ImageIcon cardImage = getCardImage(card);
            JButton cardButton = new JButton();
            cardButton.setIcon(new ImageIcon(cardImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH)));

            // Add a border to the button; initially invisible
            cardButton.setBorder(BorderFactory.createEmptyBorder());

            // Create new action listener for each cardButton
            cardButton.addActionListener(new CardButtonActionListener(gameUI, index));
            add(cardButton);
            index = index + 1;
        }
        revalidate();
        repaint();
    }
}

