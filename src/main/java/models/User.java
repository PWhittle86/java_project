package models;

import java.util.ArrayList;

public class User {

    private int id;
    private String username;
    private ArrayList<Advert> userAdverts;

    public User(String username) {
        this.username = username;
        this.userAdverts = new ArrayList<Advert>();
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public ArrayList<Advert> getUserAdverts() {return userAdverts;}
    public void setUserAdverts(ArrayList<Advert> userAdverts) {this.userAdverts = userAdverts;}
}
