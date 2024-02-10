package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PairTest {

    private Card cardAND;
    private Card cardOR;
    private Card cardNAND;
    private Card cardNOR;
    private Pair pairValid;
    private Pair pairInvalid;

    @BeforeEach
    void setUp() {
        cardAND = new Card(1, "AND");
        cardOR = new Card(0, "OR");
        cardNAND = new Card(1, "NAND");
        cardNOR = new Card(0, "NOR");
        pairValid = new Pair(cardNAND, cardNOR);
        pairInvalid = new Pair(cardAND, cardOR);
    }

    @Test
    void testIsPairValid() {
        // Try all the different combinations
        assertTrue(pairValid.getValid());
        assertFalse(pairInvalid.getValid());
    }

    @Test
    void testTryOperation() {
        assertTrue(pairValid.getValid());
        assertFalse(pairInvalid.getValid());
    }
}
