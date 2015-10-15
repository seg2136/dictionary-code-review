import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {

  @Test
  public void getTerm_returnsTerm_true() {
    Word testWord = new Word("Sunshine");
    assertEquals("Sunshine", testWord.getTerm());
  }

  @Test
  public void getId_returnsWordId() {
    Word testWord = new Word("Sunshine");
    assertTrue(Word.all().size() == testWord.getId());
  }

  @Test
  public void getWords_initiallyReturnsEmptyArrayList() {
    Word testWord = new Word("Sunshine");
    assertTrue(testWord.getDefinitions() instanceof ArrayList);
  }

  @Test
  public void all_returnsAllInstancesOfDefinition_true() {
    Word firstWord = new Word("Sunshine");
    Word secondWord = new Word("Sunshine");
    assertTrue(Word.all().contains(firstWord));
    assertTrue(Word.all().contains(secondWord));
  }

  @Test
  public void clear_removesAllWordInstancesFromMemory() {
    Word testWord = new Word("Sunshine");
    Word.clear();
    assertEquals(Word.all().size(), 0);
  }

  @Test
  public void find_returnsWordWithSameId() {
    Word testWord = new Word("Sunshine");
    assertEquals(Word.find(testWord.getId()), testWord);
  }

  @Test
  public void addDefinition_addsDefinitionToList() {
    Word testWord = new Word("Sunshine");
    Definition testDefinition = new Definition("Defintion");
    testWord.addDefinition(testDefinition);
    assertTrue(testWord.getDefinitions().contains(testDefinition));
  }
}
