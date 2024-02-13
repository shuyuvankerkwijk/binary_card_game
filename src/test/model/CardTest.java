package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card card;
    Card cardAND1;
    Card cardOR0;
    Card cardOR1;
    Card cardNAND0;
    Card cardNAND1;
    Card cardNOR0;

    @BeforeEach
    public void setup() {
        card = new Card();
        cardAND1 = new Card(1, "AND");
        cardOR0 = new Card(0, "OR");
        cardOR1 = new Card(1, "OR");
        cardNAND0 = new Card(0, "NAND");
        cardNAND1 = new Card(1, "NAND");
        cardNOR0 = new Card(0, "NOR");
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

    @Test
    public void testCardConstructorWithParameters() {
        assertEquals(1, cardAND1.getNum());
        assertEquals("AND", cardAND1.getOperation());
        assertEquals(0, cardOR0.getNum());
        assertEquals("OR", cardOR0.getOperation());
        assertEquals(1, cardOR1.getNum());
        assertEquals("OR", cardOR1.getOperation());
        assertEquals(0, cardNAND0.getNum());
        assertEquals("NAND", cardNAND0.getOperation());
        assertEquals(1, cardNAND1.getNum());
        assertEquals("NAND", cardNAND1.getOperation());
        assertEquals(0, cardNOR0.getNum());
        assertEquals("NOR", cardNOR0.getOperation());
    }

    @Test
    public void testGetCardInformation() {
        assertEquals("1 // AND", cardAND1.getCardInformation());
        assertEquals("0 // OR", cardOR0.getCardInformation());
        assertEquals("1 // OR", cardOR1.getCardInformation());
        assertEquals("0 // NAND", cardNAND0.getCardInformation());
        assertEquals("1 // NAND", cardNAND1.getCardInformation());
        assertEquals("0 // NOR", cardNOR0.getCardInformation());
    }
}


// if need to test randomness
//@Test
//public void testCardDefaultConstructorRandomness() {
//    int tries = 10000; // A large number to ensure statistical significance.
//    Set<String> seenOperations = new HashSet<>();
//    boolean seenZero = false;
//    boolean seenOne = false;
//
//    for (int i = 0; i < tries; i++) {
//        Card card = new Card();
//        seenOperations.add(card.getOperation());
//        if (card.getNum() == 0) seenZero = true;
//        if (card.getNum() == 1) seenOne = true;
//    }
//
//    assertTrue(seenZero && seenOne, "Both 0 and 1 should be generated for num.");
//    assertTrue(seenOperations.containsAll(Arrays.asList("AND", "OR", "NAND", "NOR")),
//            "All operations should be generated.");
//}