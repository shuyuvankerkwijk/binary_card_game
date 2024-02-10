package model;

public class Pair {
    private final Card card1;
    private final Card card2;
    private final Boolean valid;

    // EFFECTS: Generates a new pair with the selected cards
    public Pair(Card c1, Card c2) {
        this.card1 = c1;
        this.card2 = c2;
        this.valid = isPairValid(c1, c2);
    }

    // EFFECTS: Tries respective operations of both cards on numbers of cards
    // If both operations are True, returns True
    private Boolean isPairValid(Card c1, Card c2) {
        // Try c1 operation
        Boolean firstOperationValid = tryOperation(
                c1.getNum(),
                c2.getNum(),
                c1.getOperation());

        // Try c2 operation
        Boolean secondOperationValid = tryOperation(
                c1.getNum(),
                c2.getNum(),
                c2.getOperation());

        return (firstOperationValid && secondOperationValid);
    }

    // REQUIRES: operation is one of "AND", "OR", "NAND", "NOR"
    // EFFECTS: returns result of bitwise operation on the two numbers
    private Boolean tryOperation(int num1, int num2, String operation) {
        int result;
        if (operation.equals("AND")) {
            result = (num1 & num2);
        } else if (operation.equals("OR")) {
            result = (num1 | num2);
        } else if (operation.equals("NAND")) {
            result = (~(num1 & num2));
        } else { // Last case is operation.equals("NOR")
            result = (~(num1 | num2));
        }

        return (result != 0); // Convert to boolean
    }

    public Boolean getValid() {
        return this.valid;
    }

//    public Card getCard1() {
//        return this.card1;
//    }
//
//    public Card getCard2() {
//        return this.card2;
//    }
}
