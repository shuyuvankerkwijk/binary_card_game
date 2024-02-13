package model;

import java.util.ArrayList;
import java.util.List;

public class CardGame {

    private List<Card> currentCards;
    private List<Pair> validPairs;
    private List<Pair> allPairs;

    // EFFECTS: initializes currentCards with four cards, validPairsFound as empty
    public CardGame() {
        this.currentCards = new ArrayList<Card>();
        this.validPairs = new ArrayList<Pair>();
        this.allPairs = new ArrayList<Pair>();
        drawCards(4);
    }

    // EFFECTS: initializes CardGame with specified cards, for testing purpose
    public CardGame(Card c1, Card c2, Card c3, Card c4) {
        this.currentCards = new ArrayList<Card>();
        this.validPairs = new ArrayList<Pair>();
        this.allPairs = new ArrayList<Pair>();
        this.currentCards.add(c1);
        this.currentCards.add(c2);
        this.currentCards.add(c3);
        this.currentCards.add(c4);
    }

    // REQUIRES: c >= 0
    // MODIFIES: this
    // EFFECTS: adds specified number of Card items to currentCards
    public void drawCards(int c) {
        for (int i = 0; i < c; i++) {
            this.currentCards.add(new Card());
        }
    }

    // REQUIRES: 0 <= c1, c2 <= currentCards.size()-1
    // MODIFIES: this
    // EFFECTS: creates a new pair with cards at given indices, adds to allPairs
    //          If pair is valid, adds pair to validPairs and removes cards from currentCards
    public Boolean selectPair(int c1, int c2) {
        Card card1 = currentCards.get(c1);
        Card card2 = currentCards.get(c2);
        Pair pair = new Pair(card1, card2);
        this.allPairs.add(pair);
        if (pair.getValid()) {
            this.validPairs.add(pair);
            removeCards(c1, c2);
            return true;
        }
        return false;
    }

    // REQUIRES: 0 <= c <= currentCards.size()-1
    // MODIFIES: this
    // EFFECTS: removes two cards at given index from currentCards
    //          first removes the card at larger index, as to not change indexing
    //          second card.
    public void removeCards(int c1, int c2) {
        if (c2 > c1) {
            this.currentCards.remove(c2);
            this.currentCards.remove(c1);
        } else {
            this.currentCards.remove(c1);
            this.currentCards.remove(c2);
        }
    }

    // EFFECTS: returns current card information in string
    public String getCurrentCards() {
        String result = "";
        for (int i = 0; i < currentCards.size(); i++) {
            Card card = currentCards.get(i);
            result = result + "\n" + (i + 1) + ". " + card.getCardInformation();
        }
        return result;
    }

    // EFFECTS: returns a list of all pairs selected, with information on both
    //          cards and whether the pair was valid
    public String getAllPairs() {
        String result = "";
        for (int i = 0; i < allPairs.size(); i++) {
            Pair pair = allPairs.get(i);
            boolean pairValid = pair.getValid();
            String pairValidString = "invalid";
            if (pairValid == true) {
                pairValidString = "valid";
            }
            Card card1 = pair.getCard1();
            String card1Info = card1.getCardInformation();
            Card card2 = pair.getCard2();
            String card2Info = card2.getCardInformation();

            String pairResult = "Pair " + (i+1) + ":"
                    + "(" + card1Info + ")"
                    + " & "
                    + "(" + card2Info + ")"
                    + " was " + pairValidString;
            result = result + "\n" + pairResult;
        }
        return result;
    }

    public Card getCard(int i) {
        return currentCards.get(i);
    }

    public int getNumCurrentCards() {

        return this.currentCards.size();
    }

    public int getNumPairs() {

        return this.allPairs.size();
    }

    public int getNumValidPairs() {

        return this.validPairs.size();
    }
}
