package persistence;

import model.Card;
import model.CardGame;
import model.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// credit: GitHub "JsonSerializationDemo"
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CardGame cg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCardGame() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCardGame.json");
        try {
            CardGame cg = reader.read();
            assertEquals(0, cg.getNumCurrentCards());
            assertEquals(0, cg.getNumPairs());
            assertEquals(0, cg.getNumValidPairs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCardGame.json");
        try {
            CardGame cg = reader.read();
            assertEquals(2, cg.getNumCurrentCards());
            assertEquals(3, cg.getNumPairs());
            assertEquals(2, cg.getNumValidPairs());

            List<Card> cards = cg.getCards();
            assertEquals(2, cards.size());
            checkCard(1, "OR", cards.get(0));
            checkCard(1, "NAND", cards.get(1));

            List<Pair> validPairs = cg.getValidPairs();
            assertEquals(2, validPairs.size());
            checkPair(1, "NAND", 0, "OR", validPairs.get(0));
            checkPair(1, "NAND", 0, "NAND", validPairs.get(1));

            List<Pair> allPairs = cg.getAllPairs();
            assertEquals(3, allPairs.size());
            checkPair(1, "NAND", 0, "OR", allPairs.get(0));
            checkPair(1, "OR", 1, "NAND", allPairs.get(1));
            checkPair(1, "NAND", 0, "NAND", allPairs.get(2));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}