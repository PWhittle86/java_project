package controllers;

import db.DBHelper;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class UserController {

    public UserController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        get("/users", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            List<User> allUsers = DBHelper.getAll(User.class);
            model.put("allUsers", allUsers);
            model.put("template", "templates/users/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}
