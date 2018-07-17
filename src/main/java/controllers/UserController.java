package controllers;

import db.DBComment;
import db.DBHelper;
import db.DBUser;
import models.Advert;
import models.Category;
import models.Comment;
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
import static spark.Spark.post;

public class UserController {

    public UserController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        get("/users/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            List<Category> allCategories = DBHelper.getAll(Category.class);

            model.put("allCategories", allCategories);
            model.put("template", "templates/users/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/users", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            List<User> allUsers = DBHelper.getAll(User.class);
            List<Category> allCategories = DBHelper.getAll(Category.class);

            model.put("allCategories", allCategories);
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
            List<Comment> comments = DBComment.findUserComments(foundUser);
            List<Category> allCategories = DBHelper.getAll(Category.class);

            model.put("allCategories", allCategories);
            model.put("comments", comments);
            model.put("foundUser", foundUser);
            model.put("userAdverts", userAdverts);
            model.put("template", "templates/users/view.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/users/:id/edit", (req, res)-> {
            String strId = req.params(":id");
            Integer id = Integer.parseInt(strId);
            User foundUser = DBHelper.find(id, User.class);
            Map<String, Object> model = new HashMap<>();
            List<Category> allCategories = DBHelper.getAll(Category.class);

            model.put("allCategories", allCategories);
            model.put("foundUser", foundUser);
            model.put("template", "templates/users/update.vtl");
            return  new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/users/:id/favouriteads", (req, res)-> {
            int userId = Integer.parseInt(req.params(":id"));
            User foundUser = DBHelper.find(userId, User.class);
            List<Advert> favAdverts = DBUser.getUsersFavAdverts(foundUser);

            Map<String, Object> model = new HashMap<>();
            List<Category> allCategories = DBHelper.getAll(Category.class);

            model.put("allCategories", allCategories);
            model.put("favAdverts", favAdverts);
            model.put("foundUser", foundUser);
            model.put("template", "templates/users/viewFavourites.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
                }, new VelocityTemplateEngine());

        post("/users",(req, res)->{
            String username = req.queryParams("username");
            String userImage = "/seedImages/" + req.queryParams("imageLocation");

            User newUser = new User(username, userImage);
            DBHelper.save(newUser);
            res.redirect("/users");
            return null;
        }, new VelocityTemplateEngine());

        post("users/:id", (req, res)->{
            String strId = req.params(":id");
            Integer id = Integer.parseInt(strId);
            User user = DBHelper.find(id, User.class);
            String username = req.queryParams("username");
            String imageLocation = req.queryParams("imageLocation");

            user.setUsername(username);
            user.setUserImage("/seedImages/" + imageLocation);
            DBHelper.save(user);
            res.redirect("/users");
            return null;
        }, new VelocityTemplateEngine());

        post("/users/delete/comment/:id", (req, res)->{
            String strId = req.params(":id");
            Integer id = Integer.parseInt(strId);
            Comment comment = DBHelper.find(id, Comment.class);
            DBHelper.delete(comment);
            res.redirect("/users");
            return null;

        }, new VelocityTemplateEngine());
    }

}
