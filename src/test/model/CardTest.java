package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card card;

    @BeforeEach
    public void setup() {
        card = new Card();
    }

    @Test
    public void testCard() {
        // Check that number of card is either 0 or 1
        int number = card.getNum();
        assertTrue(number == 1 | number == 0);
        // Check that operation of card is one of 'AND', 'NOR', 'OR', 'NAND'
        String result = card.getOperation();
        assertTrue(result.equals("AND") |
                result.equals("OR") |
                result.equals("NAND") |
                result.equals("NOR"));
    }
}