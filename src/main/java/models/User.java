package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "users")
public class User {

    private int id;
    private String username;
    private List<Advert> userAdverts;

    public User() {
    }
    public User(String username) {
        this.username = username;
        this.userAdverts =  new ArrayList<Advert>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Column(name = "username")
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    @OneToMany(mappedBy="advertOwner", fetch = FetchType.LAZY)
    public List<Advert> getUserAdverts() {return userAdverts;}
    public void setUserAdverts(List<Advert> userAdverts) {this.userAdverts = userAdverts;}

}
