package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Acts as an action listener for card buttons. Handles player interactions with
 * the card buttons on the game board, managing card selection logic for gameplay.
 * It determines whether a card is being selected or deselected as part of a move
 * and updates the game state accordingly.
 */
public class CardButtonActionListener implements ActionListener {
    private final GameUI gameUI;
    private int cardIndex;

    // EFFECTS: Constructor for ActionListener, creates references to
    //          GameUI and the index of the card of which this listener
    //          is for.
    public CardButtonActionListener(GameUI gameUI, int cardIndex) {
        super();
        this.gameUI = gameUI;
        this.cardIndex = cardIndex;
    }

    // MODIFIES: this
    // EFFECTS: Handles the selection or deselection of a card in the game UI based
    //          on the current game state
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();

        // Check if the button is already selected, if so, deselect
        if (gameUI.getC1() == this.cardIndex) {
            sourceButton.setBorder(BorderFactory.createEmptyBorder());
            gameUI.setC1(gameUI.getC2());
            gameUI.setC2(-1);
        // Otherwise, select as first card or second card
        } else {
            if (gameUI.getC1() == -1) {
                gameUI.setC1(this.cardIndex);
                sourceButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            } else if (gameUI.getC2() == -1) {
                gameUI.setC2(this.cardIndex);
                sourceButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            }
        }
    }
}
