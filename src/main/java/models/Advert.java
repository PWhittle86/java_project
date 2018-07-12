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




}
