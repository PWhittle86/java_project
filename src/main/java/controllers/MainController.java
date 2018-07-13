package controllers;

import db.Seeds;

public class MainController {

    public static void main(String[] args) {
        Seeds.seedData();

        AdController adController = new AdController();

    }



}
