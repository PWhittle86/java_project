package models;

import java.util.ArrayList;

public class ShopFront {

    private ArrayList<User> allUsers;
    private ArrayList<Advert> allAdverts;



    public ShopFront() {

        this.allUsers = new ArrayList<User>();
        this.allAdverts = new ArrayList<Advert>();

    }

    public ArrayList<User> getAllUsers() {return allUsers;}
    public void setAllUsers(ArrayList<User> allUsers) {this.allUsers = allUsers;}

    public ArrayList<Advert> getAllAdverts() {return allAdverts;}
    public void setAllAdverts(ArrayList<Advert> allAdverts) {this.allAdverts = allAdverts;}
}