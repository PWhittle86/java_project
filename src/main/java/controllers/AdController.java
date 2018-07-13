package controllers;

import db.DBHelper;
import models.Advert;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
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
