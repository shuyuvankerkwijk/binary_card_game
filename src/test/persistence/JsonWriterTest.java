package persistence;
import model.Card;
import model.CardGame;
import model.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// credit: GitHub "JsonSerializationDemo"
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            List<Card> currentCards = new ArrayList<Card>();
            List<Pair> validPairs = new ArrayList<Pair>();
            List<Pair> allPairs = new ArrayList<Pair>();
            CardGame cgEmpty = new CardGame(currentCards, validPairs, allPairs);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(cgEmpty);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            cgEmpty = reader.read();
            assertEquals(0, cgEmpty.getNumCurrentCards());
            assertEquals(0, cgEmpty.getNumPairs());
            assertEquals(0, cgEmpty.getNumValidPairs());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Card c1 = new Card(1, "OR");
            Card c2 = new Card(1, "NAND");
            List<Card> currentCards = new ArrayList<Card>();
            currentCards.add(c1);
            currentCards.add(c2);
            Pair p1 = new Pair(new Card(1, "NAND"), new Card(0, "OR"));
            Pair p2 = new Pair(new Card(1, "OR"), new Card(1, "NAND"));
            Pair p3 = new Pair(new Card(1, "NAND"), new Card(0, "NAND"));
            List<Pair> validPairs = new ArrayList<Pair>();
            validPairs.add(p1);
            validPairs.add(p3);
            List<Pair> allPairs = new ArrayList<Pair>();
            allPairs.add(p1);
            allPairs.add(p2);
            allPairs.add(p3);
            CardGame cg = new CardGame(currentCards, validPairs, allPairs);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(cg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            cg = reader.read();

            assertEquals(2, cg.getNumCurrentCards());
            assertEquals(3, cg.getNumPairs());
            assertEquals(2, cg.getNumValidPairs());

            currentCards = cg.getCards();
            assertEquals(2, currentCards.size());
            checkCard(1, "OR", currentCards.get(0));
            checkCard(1, "NAND", currentCards.get(1));

            validPairs = cg.getValidPairs();
            assertEquals(2, validPairs.size());
            checkPair(1, "NAND", 0, "OR", validPairs.get(0));
            checkPair(1, "NAND", 0, "NAND", validPairs.get(1));

            allPairs = cg.getAllPairs();
            assertEquals(3, allPairs.size());
            checkPair(1, "NAND", 0, "OR", allPairs.get(0));
            checkPair(1, "OR", 1, "NAND", allPairs.get(1));
            checkPair(1, "NAND", 0, "NAND", allPairs.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}