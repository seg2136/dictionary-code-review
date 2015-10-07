import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {

  @Test
  public void word_instantiatesCorrectly_true() {
    Word myWord = new Word("sunshine");
    assertEquals(true, myWord instanceof Word);
  }

  @Test
  public void word_instantiatesWithTerm_true() {
    Word myWord = new Word("happiness");
    assertEquals("happiness", myWord.getTerm());
  }
}
