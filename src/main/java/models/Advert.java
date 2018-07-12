package models;

import java.util.ArrayList;

public class Advert {

    private int id;
    private String advertTitle;
    private String advertDescription;
//    private ArrayList<Categories> categories;
    private Double askingPrice;
    private User advertOwner;

    public Advert(String advertTitle, String advertDescription, Double askingPrice, User advertOwner) {
        this.advertTitle = advertTitle;
        this.advertDescription = advertDescription;
//        this.categories = new ArrayList<Categories>();
        this.askingPrice = askingPrice;
        this.advertOwner = advertOwner;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getAdvertTitle() {return advertTitle;}
    public void setAdvertTitle(String advertTitle) {this.advertTitle = advertTitle;}

    public String getAdvertDescription() {return advertDescription;}
    public void setAdvertDescription(String advertDescription) {this.advertDescription = advertDescription;}

//    public ArrayList<Categories> getCategories() {return categories;}
//    public void setCategories(ArrayList<Categories> categories) {this.categories = categories;}

    public Double getAskingPrice() {return askingPrice;}
    public void setAskingPrice(Double askingPrice) {this.askingPrice = askingPrice;}

    public User getAdvertOwner() {return advertOwner;}
    public void setAdvertOwner(User advertOwner) {this.advertOwner = advertOwner;}
}
