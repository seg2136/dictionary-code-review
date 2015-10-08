import java.util.HashMap;
import java.util.ArrayList;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class App {
	public static void main(String[] args) {
		staticFileLocation("/public");
    String layout = "templates/layout.vtl";

		//GET METHOD FOR HOME PAGE
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("words", request.session().attribute("words"));
			model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


		//GET METHOD TO DISPLAY WORDS
		get("/words", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("words", Word.all());
			model.put("template", "templates/words.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());


		//GET METHOD TO ADD NEW WORDS
		get("/words/new", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("template", "templates/word-form.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());



		//POST METHOD TO SAVE NEW WORDS
		post("/words", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			String term = request.queryParams("term");
			Word newWord = new Word(term);
			model.put("template", "templates/newpage.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		//GET METHOD TO VIEW INDIVIDUAL WORD PAGE
		get("/words/:id", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			Word word = Word.find(Integer.parseInt(request.params(":id")));
			model.put("word", word);
			model.put("template", "templates/word.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());


	}
}
