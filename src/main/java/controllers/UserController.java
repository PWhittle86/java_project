package controllers;

import db.DBHelper;
import db.DBUser;
import models.Advert;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

        get("/users/:id", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String strId = req.params(":id");
            Integer id = Integer.parseInt(strId);
            User foundUser = DBHelper.find(id, User.class);

            List<Advert> userAdverts = DBUser.getUsersAdverts(foundUser);
            model.put("foundUser", foundUser);
            model.put("userAdverts", userAdverts);
            model.put("template", "templates/users/view.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}
