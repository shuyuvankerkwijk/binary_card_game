package model;

import org.json.JSONObject;
import persistence.Writable;

/**
 * Represents a pair of cards in a card game and determines the validity of the pair based on
 * logical (bitwise) operations associated with each card. A pair is considered valid
 * if both cards' operations, when applied to the numbers represented by the cards, result in a
 * "true" outcome.The validity of a pair is determined at the time of creation
 * Operations supported are "AND", "OR", "NAND", and "NOR", which are applied in a bitwise context
 * to the numbers associated with each card.
 */
public class Pair implements Writable {

    private final Card card1;
    private final Card card2;
    private final boolean valid;

    // EFFECTS: Generates a new pair with the selected cards
    public Pair(Card c1, Card c2) {
        this.card1 = c1;
        this.card2 = c2;
        this.valid = isPairValid(c1, c2);
    }

    // EFFECTS: Tries respective operations of both cards on numbers of cards
    // If both operations are True, returns True
    private boolean isPairValid(Card c1, Card c2) {
        // Try c1 operation
        boolean firstOperationValid = tryOperation(
                c1.getNum(),
                c2.getNum(),
                c1.getOperation());

        // Try c2 operation
        boolean secondOperationValid = tryOperation(
                c1.getNum(),
                c2.getNum(),
                c2.getOperation());

        return (firstOperationValid && secondOperationValid);
    }

    // REQUIRES: operation is one of "AND", "OR", "NAND", "NOR"
    // EFFECTS: returns result of bitwise operation on the two numbers
    private boolean tryOperation(int num1, int num2, String operation) {
        boolean result;
        if (operation.equals("AND")) {
            result = ((num1 & num2) != 0);
        } else if (operation.equals("OR")) {
            result = ((num1 | num2) != 0);
        } else if (operation.equals("NAND")) {
            result = ((num1 & num2) == 0);
        } else {       // before was else if (operation.equals("NOR")) {
            result = ((num1 | num2) == 0);
        }
        return result; // Convert to boolean
    }

    // EFFECTS: Getter operation
    public boolean getValid() {
        return this.valid;
    }

    // EFFECTS: Getter operation
    public Card getCard1() {
        return this.card1;
    }

    // EFFECTS: Getter operation
    public Card getCard2() {
        return this.card2;
    }

    // EFFECTS: Writes Pair information as JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("card1", this.card1.toJson());
        json.put("card2", this.card2.toJson());
        return json;
    }
}

