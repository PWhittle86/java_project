package controllers;

import db.DBAdvert;
import db.DBComment;
import db.DBHelper;
import db.DBUser;
import models.Advert;
import models.Category;
import models.Comment;
import models.User;
import org.dom4j.rule.Mode;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.route.HttpMethod.delete;
import static spark.route.HttpMethod.get;

public class AdController {

    public AdController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        get("/adverts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Advert> allAdverts = DBHelper.getAll(Advert.class);
            List<Category> allCategories = DBHelper.getAll(Category.class);

            model.put("allCategories", allCategories);
            model.put("allAdverts", allAdverts);
            model.put("template", "templates/adverts/showAll.vtl");
            return new ModelAndView(model, "templates/homepageLayout.vtl");
            }, new VelocityTemplateEngine());

        get("/adverts/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> allUsers = DBHelper.getAll(User.class);
            List<Category> categories = DBHelper.getAll(Category.class);

            model.put("categories", categories);
            model.put("allUsers", allUsers);
            model.put("template", "templates/adverts/createAdvert.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/adverts/:id", (req, res) -> {
            int advertId = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.find(advertId, Advert.class);
            List<User> allUsers = DBHelper.getAll(User.class);
            List<Comment> comments = DBComment.findAdvertComments(advert);
            Map<String, Object> model = new HashMap<>();
            model.put("allUsers", allUsers);
            model.put("comments", comments);
            model.put("advert", advert);
            model.put("template", "templates/adverts/showAdvert.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/adverts/category/:id", (req, res) -> {
            int categoryId = Integer.parseInt(req.params(":id"));
            Category category = DBHelper.find(categoryId, Category.class);
            List<Advert> categorisedAdverts = DBAdvert.findAdvertsByCategory(category);
            Map<String, Object> model = new HashMap<>();

            model.put("category", category.getCategoryName());
            model.put("categorisedAdverts", categorisedAdverts);
            model.put("template", "templates/adverts/showCategorisedAdverts.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/adverts/update/:id", (req, res) -> {
            int advertId = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.find(advertId, Advert.class);
            List<User> allUsers = DBHelper.getAll(User.class);
            Map<String, Object> model = new HashMap<>();

            model.put("allUsers", allUsers);
            model.put("advert", advert);
            model.put("template", "templates/adverts/updateAdvert.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/adverts/search/", (req, res) -> { //Doesn't work at the moment. Query instructors on Monday.
            String searchCriteria = req.queryParams("searchCriteria");
            List<Advert> advertsList = DBAdvert.findAdvertsByName(searchCriteria);

            Map<String, Object> model = new HashMap<>();
            model.put("advertsList", advertsList);
            model.put("template", "templates/adverts/foundAdverts.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/adverts/confirmUser/:id", (req, res) -> {
            int advertId = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.find(advertId, Advert.class);
            List<User> allUsers = DBHelper.getAll(User.class);

            Map<String, Object> model = new HashMap<>();
            model.put("advert", advert);
            model.put("allUsers", allUsers);
            model.put("template", "templates/adverts/confirmUser.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/adverts/new", (req, res) -> {
            int userId = Integer.parseInt(req.queryParams("advertOwner"));
            User user = DBHelper.find(userId, User.class);

            int categoryId = Integer.parseInt(req.queryParams("advertCategory"));
            Category category = DBHelper.find(categoryId, Category.class);

            String advertTitle = req.queryParams("advertTitle");
            String advertDescription = req.queryParams("advertDescription");
            double askingPrice = Double.parseDouble(req.queryParams("askingPrice"));

            Advert newAdvert = new Advert(advertTitle, advertDescription, askingPrice, user);
            newAdvert.addCategory(category);

            List<Advert> newAdvertCategory = new ArrayList<Advert>(); //Manually creating a new adverts arraylist in order to be able to add a category. Workaround.
            newAdvertCategory.add(newAdvert);

            category.setAdverts(newAdvertCategory);
            DBHelper.save(newAdvert);

            res.redirect("/adverts");
            return null;
        }, new VelocityTemplateEngine());

        post("adverts/update/:id", (req, res) -> {
            int advertId = Integer.parseInt(req.params(":id"));
            Advert updatedAdvert = DBHelper.find(advertId, Advert.class);

            int userId = Integer.parseInt(req.queryParams("advertOwner"));

            String advertTitle = req.queryParams("advertTitle");
            String advertDescription = req.queryParams("advertDescription");
            Double askingPrice = Double.parseDouble(req.queryParams("askingPrice"));
            User advertOwner = DBHelper.find(userId, User.class);

            updatedAdvert.setAdvertTitle(advertTitle);
            updatedAdvert.setAdvertDescription(advertDescription);
            updatedAdvert.setAskingPrice(askingPrice);
            updatedAdvert.setAdvertOwner(advertOwner);

            DBHelper.save(updatedAdvert);
            res.redirect("/adverts");
            return null;
        }, new VelocityTemplateEngine());

        post("adverts/delete/:id",(req, res) -> {
            int advertId = Integer.parseInt(req.params(":id"));
            Advert deletedAdvert = DBHelper.find(advertId, Advert.class);

            DBHelper.delete(deletedAdvert);
            res.redirect("/adverts");
            return null;
        }, new VelocityTemplateEngine());

        post("/adverts/confirmFavourite/:id", (req, res) -> {
            int advertId = Integer.parseInt(req.params(":id"));
            Advert favAdvert = DBHelper.find(advertId, Advert.class);

            int userId = Integer.parseInt(req.queryParams("selectedUser"));
            User selectedUser = DBHelper.find(userId, User.class);

            int debugpoint = 0;

            DBUser.addToUserFavourites(selectedUser, favAdvert);


            res.redirect("/users"); //Might change this later so that it points to the user's favourite adverts, once it has been implemented.
            return null;
        }, new VelocityTemplateEngine());

        post("/adverts/comment/:id", (request, response) ->{
            int advertId = Integer.parseInt(request.params(":id"));
            Advert advert = DBHelper.find(advertId, Advert.class);
            int userId = Integer.parseInt(request.queryParams("selectedUser"));
            User selectedUser = DBHelper.find(userId, User.class);
            String comment = request.queryParams("comment");

            Comment newComment = new Comment(advert, selectedUser, comment);
            DBHelper.save(newComment);
            DBComment.advertComment(advert, selectedUser, newComment);

            response.redirect("/adverts");
            return null;
        }, new VelocityTemplateEngine() );

    }

}