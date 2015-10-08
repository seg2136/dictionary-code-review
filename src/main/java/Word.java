import java.util.ArrayList;

public class Word {
  private static ArrayList<Word> instances = new ArrayList<Word>();

  private String mTerm;
  private int mId;
  // private String mDefinition;


  public Word(String term) {
    mTerm = term;
    instances.add(this);
    mId = instances.size();
    // mDefinitions = new ArrayList<Definition>();
  }

  public String getTerm() {
    return mTerm;
  }

  public int getId() {
    return mId;
  }

  public static ArrayList<Word> all() {
    return instances;
  }

  public static Word find(int id) {
    try {
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  public static void clear() {
    instances.clear();
  }

}


  //
  // public ArrayList<Definition> getDefinitions() {
  //   return mDefinition;
  // }
  //
  // public void addDefinition(Definition definition) {
  //   mDefinition.add(definition);
  // }
