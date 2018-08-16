package controllers;

import db.Seeds;

import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {

        port(getHerokuAssignedPort());

        staticFileLocation("public");

        Seeds.seedData();

        AdController adController = new AdController();
        UserController userController =new UserController();
    }


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }


}
