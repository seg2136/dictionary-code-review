import java.util.ArrayList;

public class Word {
  private String mTerm;
  // private static ArrayList<Word> instances = new ArrayList<Word>();
  // private ArrayList<Definition> mDefinition;

  public Word(String term) {
    mTerm = term;
    // instances.add(this);
    // mDefinitions = new ArrayList<Definition>();
  }

  public String getTerm() {
    return mTerm;
  }
  //
  // public ArrayList<Definition> getDefinitions() {
  //   return mDefinition;
  // }
  //
  // public void addDefinition(Definition definition) {
  //   mDefinition.add(definition);
  // }
}
