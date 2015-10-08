import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void word_instantiatesCorrectly_true() {
    Word myWord = new Word("Sunshine");
    assertEquals(true, myWord instanceof Word);
  }

  @Test
  public void word_instantiatesWithTerm_true() {
    Word myWord = new Word("Happiness");
    assertEquals("Happiness", myWord.getTerm());
  }

  @Test
  public void all_returnsAllInstancesOfWord_true() {
    Word firstWord = new Word("Sunshine");
    Word secondWord = new Word("Happiness");
    assertTrue(Word.all().contains(firstWord));
    assertTrue(Word.all().contains(secondWord));
  }

  @Test
  public void newId_wordsInstantiateWithAnID_true() {
    Word myWord = new Word("Sunshine");
    assertEquals(Word.all().size(), myWord.getId());
  }

  @Test
  public void find_returnsWordWithSameId_secondWord() {
    Word firstWord = new Word("Sunshine");
    Word secondWord = new Word("Happiness");
    assertEquals(Word.find(secondWord.getId()), secondWord);
  }

  @Test
  public void find_returnsNullWhenNoWordFound_null() {
    assertTrue(Word.find(999) == null);
  }

  @Test
  public void clear_emptiesAllWordsFromArrayList() {
    Word myWord = new Word("Sunshine");
    Word.clear();
    assertEquals(Word.all().size(), 0);
  }
}
