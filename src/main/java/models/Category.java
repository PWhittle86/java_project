package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    private int id;
    private String categoryName;
    private List<Advert> adverts;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.adverts = new ArrayList<Advert>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Column(name = "name")
    public String getCategoryName() {return categoryName;}
    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "advert_category",
            joinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "advert_id", nullable = false, updatable = false)})

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public void addAdvert(Advert advert) {
        this.adverts.add(advert);
    }

}
