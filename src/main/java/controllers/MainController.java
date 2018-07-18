package controllers;

import db.Seeds;

import static spark.Spark.staticFileLocation;
import static spark.SparkBase.externalStaticFileLocation;

public class MainController {

    public static void main(String[] args) {
        staticFileLocation("public");
        externalStaticFileLocation("/Users/user/Desktop/java_project/uploads/");

        Seeds.seedData();

        AdController adController = new AdController();
        UserController userController =new UserController();

    }



}
