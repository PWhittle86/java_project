package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "users")
public class User {

    private int id;
    private String username;
    private List<Advert> userAdverts;
    private List<Advert> favouriteAdverts;
    private String userImage;
    private List<Comment> comments;


    public User() {
    }

    public User(String username, String userImage) {
        this.username = username;
        this.userImage = userImage;
        this.userAdverts =  new ArrayList<Advert>();
        this.favouriteAdverts =  new ArrayList<Advert>();
        this.comments = new ArrayList<Comment>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Column(name = "username")
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    @OneToMany(mappedBy = "advertOwner", fetch = FetchType.LAZY)
    public List<Advert> getUserAdverts() {return userAdverts;}
    public void setUserAdverts(List<Advert> userAdverts) {this.userAdverts = userAdverts;}

    @ManyToMany
    @JoinTable(name="user_advert",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "advert_id", nullable = false, updatable = false)}
    )
    public List<Advert> getFavouriteAdverts() {return favouriteAdverts;}
    public void setFavouriteAdverts(List<Advert> favouriteAdverts) {this.favouriteAdverts = favouriteAdverts;}

    @Column(name="user_image")
    public String getUserImage() {return userImage;}
    public void setUserImage(String userImage) {this.userImage = userImage;}

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addFavouriteAdvert(Advert advert){
        favouriteAdverts.add(advert);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
}
