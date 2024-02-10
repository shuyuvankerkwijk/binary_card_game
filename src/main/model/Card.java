package model;

import java.util.Random;
import java.lang.Math;

public class Card {
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

}
