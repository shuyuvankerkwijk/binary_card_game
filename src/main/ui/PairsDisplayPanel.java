package ui;

import model.Card;
import model.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a display panel for showing all valid card pairs found in the Binary Operations Card Game.
 * This panel is part of the game's GUI, designed to visually present the pairs of cards that have been
 * successfully matched according to the game's rules.
 * The class manages a list of valid pairs and updates its display whenever the game state changes. It
 * dynamically adjusts its size based on the number of pairs to be displayed and draws the card images
 * on the panel.
 */
public class PairsDisplayPanel extends JPanel {

    private Game game;
    private List<Pair> validPairs;

    // EFFECTS: Constructor. Initializes the panel with a specified size and background color. Sets up
    //          reference to the game instance and initializes a list of valid pairs.
    public PairsDisplayPanel(Game game) {
        super();
        setPreferredSize(new Dimension(200, 500));
        setBackground(Color.white);
        this.game = game;
        validPairs = new ArrayList<Pair>();
    }

    // EFFECTS: Returns an ImageIcon object that contains the image for the specified card (all in data/images)
    private ImageIcon getCardImage(Card c) {
        String imagePath = "./data/images/";
        String fileName = imagePath + c.getNum() + c.getOperation() + ".png";
        return new ImageIcon(fileName);
    }

    // MODIFIES: this
    // EFFECTS: Updates the panel with the game's current valid pairs
    public void update() {
        validPairs = game.getCardGame().getValidPairs();
        repaint();
    }

    // EFFECTS: Overrides generic paint function to create a long image with the found valid pairs
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int imageWidth = Math.min(100, panelWidth / 2);
        int imageHeight = (int) (imageWidth * 1.5);

        int xdirOffset = panelWidth / 5; // Offset for the overlay effect between pairs
        int ydirOffset = panelHeight / 10; // Vertical space between pairs
        int yposImage = ydirOffset; // Vertical position to place
        int xposImage = xdirOffset; // Horizontal position to place

        for (Pair pair : validPairs) {
            ImageIcon icon1 = getCardImage(pair.getCard1());
            ImageIcon icon2 = getCardImage(pair.getCard2());

            ImageIcon scaledIcon1 = new ImageIcon(icon1.getImage().getScaledInstance(
                    imageWidth, imageHeight, Image.SCALE_SMOOTH));
            ImageIcon scaledIcon2 = new ImageIcon(icon2.getImage().getScaledInstance(
                    imageWidth, imageHeight, Image.SCALE_SMOOTH));

            g.drawImage(scaledIcon1.getImage(), xposImage, yposImage, this);
            g.drawImage(scaledIcon2.getImage(), xposImage + xdirOffset, yposImage, this);

            yposImage = yposImage + imageHeight + ydirOffset;
        }
        setPreferredSize(new Dimension(200, yposImage));
        revalidate();
    }
}
