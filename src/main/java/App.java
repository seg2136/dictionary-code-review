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
			model.put("username", request.session().attribute("username"));

			model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

		//POST METHOD TO SAVE USERNAMES ON HOME PAGE
		post("/welcome", (request, response) -> {
			HashMap<String, Object> model = new HashMap<String, Object>();

			String inputtedUserName = request.queryParams("username");
			request.session().attribute("username", inputtedUserName);
			model.put("username", inputtedUserName);

			model.put("template", "templates/home.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

	}
}
