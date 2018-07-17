package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {
    private int id;
    private String comment;
    private Advert advert;
    private User user;


    public Comment() {
    }

    public Comment(Advert advert, User user, String comment) {
        this.advert = advert;
        this.user = user;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false)
    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
