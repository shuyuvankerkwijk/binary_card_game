package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PairTest {

    private Card cardAND1;
    private Card cardOR0;
    private Card cardOR1;
    private Card cardNAND0;
    private Card cardNAND1;
    private Card cardNOR0;
    private Pair pairValid1;
    private Pair pairValid2;
    private Pair pairValid3;
    private Pair pairValid4;
    private Pair pairValid5;
    private Pair pairValid6;
    private Pair pairInvalid1;
    private Pair pairInvalid2;
    private Pair pairInvalid3;
    private Pair pairInvalid4;
    private Pair pairInvalid5;

    @BeforeEach
    void setUp() {
        cardAND1 = new Card(1, "AND");
        cardOR0 = new Card(0, "OR");
        cardOR1 = new Card(1, "OR");
        cardNAND0 = new Card(0, "NAND");
        cardNAND1 = new Card(1, "NAND");
        cardNOR0 = new Card(0, "NOR");
        pairValid1 = new Pair(cardNAND1, cardOR0); // (1, NAND) and (0, OR)
        pairValid2 = new Pair(cardNAND0, cardNOR0); // (0, NAND) and (0, NOR)
        pairValid3 = new Pair(cardAND1, cardAND1); // (1, AND) and (1, AND)
        pairValid4 = new Pair(cardNOR0, cardNOR0); // (0, NOR) and (0, NOR)
        pairValid5 = new Pair(cardAND1, cardOR1); // (1, AND) and (1, OR)
        pairValid6 = new Pair(cardOR1, cardOR0); // (1, OR) and (0, OR)
        pairInvalid1 = new Pair(cardAND1, cardOR0); // (1, AND) and (0, OR)
        pairInvalid2 = new Pair(cardAND1, cardNOR0); // (1, AND) and (0, NOR)
        pairInvalid3 = new Pair(cardNAND1, cardAND1); // (1, NAND) and (1, AND)
        pairInvalid4 = new Pair(cardOR0, cardOR0); // (0, OR) and (0, OR)
        pairInvalid5 = new Pair(cardNOR0, cardOR1); // (0, NOR) and (1, OR)
    }

    @Test
    void testPair() {
        assertEquals(cardNAND1, pairValid1.getCard1());
        assertEquals(cardOR0, pairValid1.getCard2());
        assertEquals(cardNAND1, pairInvalid3.getCard1());
        assertEquals(cardAND1, pairInvalid3.getCard2());
        assertTrue(pairValid1.getValid());
        assertFalse(pairInvalid3.getValid());
    }

    @Test
    void testIsPairValid() {
        assertTrue(pairValid1.getValid());
        assertTrue(pairValid2.getValid());
        assertTrue(pairValid3.getValid());
        assertTrue(pairValid4.getValid());
        assertTrue(pairValid5.getValid());
        assertTrue(pairValid6.getValid());
        assertFalse(pairInvalid1.getValid());
        assertFalse(pairInvalid2.getValid());
        assertFalse(pairInvalid3.getValid());
        assertFalse(pairInvalid4.getValid());
        assertFalse(pairInvalid5.getValid());
    }

    @Test
    void testTryOperation() {
        assertTrue(pairValid1.getValid());
        assertTrue(pairValid2.getValid());
        assertTrue(pairValid3.getValid());
        assertTrue(pairValid4.getValid());
        assertTrue(pairValid5.getValid());
        assertTrue(pairValid6.getValid());
        assertFalse(pairInvalid1.getValid());
        assertFalse(pairInvalid2.getValid());
        assertFalse(pairInvalid3.getValid());
        assertFalse(pairInvalid4.getValid());
        assertFalse(pairInvalid5.getValid());
    }
}
