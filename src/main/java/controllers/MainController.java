package controllers;

import db.Seeds;

import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {
        staticFileLocation("public");

        Seeds.seedData();

        AdController adController = new AdController();
        UserController userController =new UserController();

    }



}
