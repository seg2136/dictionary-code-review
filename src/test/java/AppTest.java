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
    click("a", withText("Add a new word"));
    fill("#term").with("sunshine");
    submit(".btn");
    assertThat(pageSource()).contains("Your word has been saved.");
  }

  @Test
  public void wordIsDisplayedTest() {
    goTo("http://localhost:4567/words/new");
    fill("#term").with("sunshine");
    submit(".btn");
    click("a", withText("View Words"));
    assertThat(pageSource()).contains("sunshine");
  }

  @Test
  public void multipleWordsAreDisplayedTest() {
    goTo("http://localhost:4567/words/new");
    fill("#term").with("sunshine");
    submit(".btn");
    goTo("http://localhost:4567/words/new");
    fill("#term").with("happiness");
    submit(".btn");
    click("a", withText("View Words"));
    assertThat(pageSource()).contains("sunshine");
    assertThat(pageSource()).contains("happiness");
  }

  @Test
  public void wordShowPageDisplaysWord() {
    goTo("http://localhost:4567/words/new");
    fill("#term").with("sunshine");
    submit(".btn");
    click("a", withText("View Words"));
    click("a", withText("sunshine"));
    assertThat(pageSource()).contains("sunshine");
  }

  @Test
  public void wordNotFoundMessageShown() {
    goTo("http://localhost:4567/words/999");
    assertThat(pageSource()).contains("Word not found");
  }

}
