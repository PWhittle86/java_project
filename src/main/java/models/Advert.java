package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name= "adverts")
public class Advert {

    private int id;
    private String advertTitle;
    private String advertDescription;
//    private ArrayList<Categories> categories;
    private Double askingPrice;
    private User advertOwner;

    public Advert() {
    }

    public Advert(String advertTitle, String advertDescription, Double askingPrice, User advertOwner) {
        this.advertTitle = advertTitle;
        this.advertDescription = advertDescription;
//        this.categories = new ArrayList<Categories>();
        this.askingPrice = askingPrice;
        this.advertOwner = advertOwner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Column(name = "advertTitle") //Jack, should we be including DB entries for this and description? Seems like it might be a really big table entry... Added them for now, not sure they should be in the table?
    public String getAdvertTitle() {return advertTitle;}
    public void setAdvertTitle(String advertTitle) {this.advertTitle = advertTitle;}

    @Column(name = "advertDescription")
    public String getAdvertDescription() {return advertDescription;}
    public void setAdvertDescription(String advertDescription) {this.advertDescription = advertDescription;}

    //Leaving the category stuff commented out until everything else is working.

//    public ArrayList<Categories> getCategories() {return categories;}
//    public void setCategories(ArrayList<Categories> categories) {this.categories = categories;}

    @Column(name="askingPrice")
    public Double getAskingPrice() {return askingPrice;}
    public void setAskingPrice(Double askingPrice) {this.askingPrice = askingPrice;}

    @ManyToOne
    @JoinColumn(name="user_id", nullable= false)
    public User getAdvertOwner() {return advertOwner;}
    public void setAdvertOwner(User advertOwner) {this.advertOwner = advertOwner;}
}
