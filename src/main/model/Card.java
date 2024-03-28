package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

/**
 * Represents a singular Card that has both a number and a string operation associated
 * with it. The card information can be retrieved in a printable format and both of the card's
 * characteristics can also be retrieved with getters.
 * Operations supported are "AND", "OR", "NAND", and "NOR", with the note that a card could
 * never have "AND" and the number 0, or "NOR" and the number 1, as this will never result
 * in a valid pair, no matter the second card chosen.
 */
public class Card implements Writable {
    private final int num;
    private final String operation;

    // EFFECTS: Randomly assigns 0 or 1 to num, randomly assigns AND, OR, NAND, NOR to operation
    public Card() {
        // Selects 0 or 1 for number
        this.num = new Random().nextInt(2);

        // Selects one of the four operations, based on num
        int x = new Random().nextInt(3);
        if (x == 0) {
            if (this.num == 1) {
                this.operation = "AND";
            } else {
                this.operation = "NOR";
            }
        } else if (x == 1) {
            this.operation = "OR";
        } else {
            this.operation = "NAND";
        }
    }

    // EFFECTS: card constructor for testing, assigns chosen number and operation
    public Card(Integer num, String operation) {
        this.num = num;
        this.operation = operation;
    }

    public String getCardInformation() {
        return (this.num + " // " + this.operation);
    }

    public int getNum() {
        return this.num;
    }

    public String getOperation() {
        return this.operation;
    }

    // EFFECTS: Writes Card information as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("num", num);
        json.put("operation", operation);
        return json;
    }
}