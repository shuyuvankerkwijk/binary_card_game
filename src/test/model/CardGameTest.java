package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    CardGame game;
    CardGame gameSpecific;
    Card cardAND1;
    Card cardOR0;
    Card cardNAND1;
    Card cardNOR0;

    @BeforeEach
    public void setup() {
        game = new CardGame();
        cardAND1 = new Card(1, "AND");
        cardOR0 = new Card(0, "OR");
        cardNAND1 = new Card(1, "NAND");
        cardNOR0 = new Card(0, "NOR");
        gameSpecific = new CardGame(cardAND1, cardOR0, cardNAND1, cardNOR0);
    }

    @Test
    public void testCardGame() {
        assertEquals(4, game.getNumCurrentCards());
        assertEquals(0, game.getNumPairs());
        assertEquals(0, game.getNumValidPairs());
    }

    @Test
    public void testCardGameConstructorWithParameters() {
        assertEquals(4, gameSpecific.getNumCurrentCards());
        assertEquals(0, gameSpecific.getNumPairs());
        assertEquals(0, gameSpecific.getNumValidPairs());
        assertEquals(cardAND1, gameSpecific.getCard(0));
        assertEquals(cardOR0, gameSpecific.getCard(1));
        assertEquals(cardNAND1, gameSpecific.getCard(2));
        assertEquals(cardNOR0, gameSpecific.getCard(3));
    }

    @Test
    public void testDrawCards() {
        game.drawCards(1);
        assertEquals(5, game.getNumCurrentCards());
        game.drawCards(1);
        assertEquals(6, game.getNumCurrentCards());
        game.drawCards(2);
        assertEquals(8, game.getNumCurrentCards());
    }

    @Test
    public void testSelectPair() {
        // valid pair
        assertTrue(gameSpecific.selectPair(1, 2)); // (OR, 0) and (NAND, 1) is valid pair
        assertEquals(1, gameSpecific.getNumPairs());
        assertEquals(1, gameSpecific.getNumValidPairs());
        assertEquals(2, gameSpecific.getNumCurrentCards());

        // invalid pair
        assertFalse(gameSpecific.selectPair(0, 1)); // (AND, 1) and (NOR, 0) is invalid pair
        assertEquals(2, gameSpecific.getNumPairs());
        assertEquals(1, gameSpecific.getNumValidPairs());
        assertEquals(2, gameSpecific.getNumCurrentCards());
    }

    @Test
    public void testRemoveCards1() {
        // remove two cards, c2 > c1 index
        gameSpecific.removeCards(0, 2);
        assertEquals(2, gameSpecific.getNumCurrentCards());
        assertEquals(cardOR0, gameSpecific.getCard(0));
        assertEquals(cardNOR0, gameSpecific.getCard(1));
    }

    @Test
    public void testRemoveCards2() {
        // remove two cards, c1 > c2 index
        gameSpecific.removeCards(2, 1);
        assertEquals(2, gameSpecific.getNumCurrentCards());
        assertEquals(cardAND1, gameSpecific.getCard(0));
        assertEquals(cardNOR0, gameSpecific.getCard(1));
    }

    @Test
    public void testGetCurrentCards() {
        String correctResult = "\n" +
                "1. 1 // AND\n" +
                "2. 0 // OR\n" +
                "3. 1 // NAND\n" +
                "4. 0 // NOR";
        assertEquals(correctResult, gameSpecific.getCurrentCards());
    }

    @Test
    public void testGetAllPairs() {
        String correctResult1 = "";
        assertEquals(correctResult1, gameSpecific.getAllPairs());
        gameSpecific.selectPair(1, 2); // (OR, 0) and (NAND, 1) is valid pair
        gameSpecific.selectPair(0, 1); // (AND, 1) and (NOR, 0) is invalid pair
        String correctResult2 = "\n" +
                "Pair 1:(0 // OR) & (1 // NAND) was valid\n" +
                "Pair 2:(1 // AND) & (0 // NOR) was invalid";
        assertEquals(correctResult2, gameSpecific.getAllPairs());
    }
}
