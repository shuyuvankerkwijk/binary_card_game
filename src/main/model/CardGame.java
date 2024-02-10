package model;

import java.util.ArrayList;
import java.util.List;

public class CardGame {

    List<Card> currentCards;
    List<Pair> validPairs;
    List<Pair> allPairs;

    // EFFECTS: initializes currentCards with four cards, validPairsFound as empty
    public CardGame() {
        this.currentCards = new ArrayList<Card>();
        this.validPairs = new ArrayList<Pair>();
        this.allPairs = new ArrayList<Pair>();
        drawCards(4);
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
