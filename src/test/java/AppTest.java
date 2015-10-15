import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Create Your Own Dictionary!");
  }

  @Test
  public void wordIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Word"));
    fill("#term").with("sunshine");
    submit(".btn");
    assertThat(pageSource()).contains("Your word has been saved.");
  }

  @Test
  public void wordIsDisplayedTest() {
    goTo("http://localhost:4567/words/new");
    fill("#term").with("sunshine");
    submit(".btn");
    click("a", withText("View All Words"));
    assertThat(pageSource()).contains("sunshine");
  }

  @Test
  public void wordDefinitionsFormIsDisplayed() {
    goTo("http://localhost:4567/words/new");
    fill("#term").with("Sunshine");
    submit(".btn");
    click("a", withText("View All Words"));
    click("a", withText("Sunshine"));
    click("a", withText("Add a new definition"));
    assertThat(pageSource()).contains("Enter a New Definition:");
  }

  @Test
  public void definitionsIsAddedAndDisplayed() {
    goTo("http://localhost:4567/words/new");
    fill("#term").with("Happiness");
    submit(".btn");
    click("a", withText("View All Words"));
    click("a", withText("Happiness"));
    click("a", withText("Add a new definition"));
    fill("#statement").with("Cupcakes");
    submit(".btn");
    assertThat(pageSource()).contains("Cupcakes");
  }

  // @Test
  // public void multipleWordsAreDisplayedTest() {
  //   goTo("http://localhost:4567/words/new");
  //   fill("#term").with("sunshine");
  //   submit(".btn");
  //   goTo("http://localhost:4567/words/new");
  //   fill("#term").with("happiness");
  //   submit(".btn");
  //   click("a", withText("View Words"));
  //   assertThat(pageSource()).contains("sunshine");
  //   assertThat(pageSource()).contains("happiness");
  // }
  //
  // @Test
  // public void wordShowPageDisplaysWord() {
  //   goTo("http://localhost:4567/words/new");
  //   fill("#term").with("sunshine");
  //   submit(".btn");
  //   goTo("http://localhost:4567/words/1");
  //   assertThat(pageSource()).contains("sunshine");
  // }
  //
  // @Test
  // public void wordNotFoundMessageShown() {
  //   goTo("http://localhost:4567/words/999");
  //   assertThat(pageSource()).contains("Sorry, word not found. Please insert a word.");
  // }

}
