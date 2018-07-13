package controllers;

import db.DBHelper;
import models.Advert;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.route.HttpMethod.get;

public class AdController {

    public AdController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        get("/adverts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Advert> allAdverts = DBHelper.getAll(Advert.class);
            model.put("allAdverts", allAdverts);
            model.put("template", "templates/adverts/showAll.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
            }, new VelocityTemplateEngine());

        get("/adverts/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> allUsers = DBHelper.getAll(User.class);
            model.put("allUsers", allUsers);
            model.put("template", "templates/adverts/createAdvert.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/adverts/new", (req, res) -> {
            int userId = Integer.parseInt(req.queryParams("advertOwner"));
            User user = DBHelper.find(userId, User.class);

            String advertTitle = req.queryParams("advertTitle");
            String advertDescription = req.queryParams("advertDescription");
            double askingPrice = Double.parseDouble(req.queryParams("askingPrice"));

            Advert newAdvert = new Advert(advertTitle, advertDescription, askingPrice, user);
            DBHelper.save(newAdvert);

            res.redirect("/adverts");
            return null;
        }, new VelocityTemplateEngine());

        get("/adverts/:id", (req, res) -> {
            int advertId = Integer.parseInt(req.params(":id"));
            Advert advert = DBHelper.find(advertId, Advert.class);
            Map<String, Object> model = new HashMap<>();

            model.put("advert", advert);
            model.put("template", "templates/adverts/showAdvert.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



    }





}