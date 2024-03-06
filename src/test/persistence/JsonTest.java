package persistence;

import model.Card;
import model.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCard(int num, String operation, Card card) {
        assertEquals(num, card.getNum());
        assertEquals(operation, card.getOperation());
    }

    protected void checkPair(int num1, String operation1, int num2, String operation2, Pair pair) {
        checkCard(num1, operation1, pair.getCard1());
        checkCard(num2, operation2, pair.getCard2());
    }
}
