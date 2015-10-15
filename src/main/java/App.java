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
			// model.put("words", request.session().attribute("words"));
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

		//GET METHOD TO DISPLAY DEFINITIONS
		get("/definitions", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("definitions", Definition.all());
			model.put("template", "templates/definitions.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		//POST METHOD TO SAVE NEW WORDS
		post("/words", (request,response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			String term = request.queryParams("term");
			Word newWord = new Word(term);
			model.put("template", "templates/newpage.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		//POST METHOD TO SAVE NEW DEFINITIONS
		post("/definitions", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			Word word = Word.find(Integer.parseInt(request.queryParams("wordId")));
			ArrayList<Definition> definitions = word.getDefinitions();

			if (definitions == null) {
				definitions = new ArrayList<Definition>();
				request.session().attribute("definitions", definitions);
			}

			String statement = request.queryParams("statement");
			Definition newDefinition = new Definition(statement);
			definitions.add(newDefinition);
			model.put("definitions", definitions);
			model.put("word", word);
			model.put("template", "templates/word.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		//GET METHOD TO VIEW INDIVIDUAL WORDS PAGE
		get("/words/:id", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			Word word = Word.find(Integer.parseInt(request.params(":id")));
			model.put("word", word);
			model.put("template", "templates/word.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		//GET METHOD TO ADD NEW DEFINITIONS
		get("/words/:id/definitions/new", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			Word word = Word.find(Integer.parseInt(request.params(":id")));
			model.put("word", word);
			model.put("template", "templates/definition-form.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		//GET METHOD TO VIEW INDIVIDUAL DEFINITIONS PAGE
		get("/definitions/:id", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();
			Definition definition = Definition.find(Integer.parseInt(request.params(":id")));
			model.put("definition", definition);
			model.put("template", "templates/definition.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());


	}
}
