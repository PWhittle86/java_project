package models;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adverts")
public class Advert {

    private int id;
    private String advertTitle;
    private String advertDescription;
    private List<Category> categories;
    private Double askingPrice;
    private User advertOwner;
    private List<User> favouritedBy;
    private String imageLocation;
    private List<Comment> comments;

    public Advert() {
    }

    public Advert(String advertTitle, String advertDescription, Double askingPrice, User advertOwner, String imageLocation) {
        this.advertTitle = advertTitle;
        this.advertDescription = advertDescription;
        this.categories = new ArrayList<Category>();
        this.askingPrice = askingPrice;
        this.advertOwner = advertOwner;
        this.favouritedBy = new ArrayList<User>();
        this.imageLocation = imageLocation;
        this.comments = new ArrayList<Comment>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Column(name = "advertTitle")
    public String getAdvertTitle() {
        return advertTitle;
    }
    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle;
    }

    @Column(name = "advertDescription")
    public String getAdvertDescription() {
        return advertDescription;
    }
    public void setAdvertDescription(String advertDescription) {
        this.advertDescription = advertDescription;
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "advert_category",
            joinColumns = {@JoinColumn(name = "advert_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)})
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Column(name = "askingPrice")
    public Double getAskingPrice() {
        return askingPrice;
    }
    public void setAskingPrice(Double askingPrice) {
        this.askingPrice = askingPrice;
    }

    @Column(name= "imageLocation")
    public String getImageLocation() {return imageLocation;}
    public void setImageLocation(String imageLocation) {this.imageLocation = imageLocation;}

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getAdvertOwner() {
        return advertOwner;
    }
    public void setAdvertOwner(User advertOwner) {
        this.advertOwner = advertOwner;
    }

    @ManyToMany
    @JoinTable(name="user_advert",
            joinColumns = {@JoinColumn(name = "advert_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)}
    )
    public List<User> getFavouritedBy() {return favouritedBy;}
    public void setFavouritedBy(List<User> favouritedBy) {this.favouritedBy = favouritedBy;}

    @OneToMany(mappedBy = "advert", fetch = FetchType.LAZY)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void addUserFavourite(User user){
        favouritedBy.add(user);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
}