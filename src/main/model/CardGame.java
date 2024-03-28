package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a card game that manages a collection of cards. The game is initialized with 4 cards
 * and allows the user to add more cards as they run out. Pairs of cards can be selected from the
 * current 'board' and evaluated for validity (see 'Pairs'). Valid pairs are tracked separately
 * from all selected pairs, and cards forming valid pairs are removed from the board.
 * Statistics and history of the game can be returned.
 */
public class CardGame implements Writable {

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

    // EFFECTS: initializes CardGame with specified currentCards, validPairs, allPairs
    public CardGame(List<Card> cards, List<Pair> validPairs, List<Pair> allPairs) {
        this.currentCards = cards;
        this.validPairs = validPairs;
        this.allPairs = allPairs;
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
    public String getCurrentCardsString() {
        String result = "";
        for (int i = 0; i < currentCards.size(); i++) {
            Card card = currentCards.get(i);
            result = result + "\n" + (i + 1) + ". " + card.getCardInformation();
        }
        return result;
    }

    // EFFECTS: returns current card information
    public List<Card> getCurrentCards() {
        return this.currentCards;
    }

    // EFFECTS: returns a list of all pairs selected, with information on both
    //          cards and whether the pair was valid
    public String getAllPairsString() {
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

            String pairResult = "Pair " + (i + 1) + ":"
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

    public List<Card> getCards() {
        return this.currentCards;
    }

    public List<Pair> getValidPairs() {
        return this.validPairs;
    }

    public List<Pair> getAllPairs() {
        return this.allPairs;
    }

    // EFFECTS: returns Game as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cards", cardsToJson());
        json.put("validPairs", validPairsToJson());
        json.put("allPairs", allPairsToJson());
        return json;
    }

    // EFFECTS: returns currentCards as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : this.currentCards) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns validPairs as a JSON array
    private JSONArray validPairsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pair p : this.validPairs) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns allPairs as a JSON array
    private JSONArray allPairsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pair p : this.allPairs) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
