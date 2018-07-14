package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;


public class Category {

    private int id;
    private String name;
    private Category parentCategory;
    private ArrayList<Category> childCategories;

    private Category(Category parentCategory,String name) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String categoryName) {this.name = name;}

    public Category getParentCategory() {return parentCategory;}
    public void setParentCategory(Category parentCategory) {this.parentCategory = parentCategory;}

    public ArrayList<Category> getChildCategories() {return childCategories;}
    public void setChildCategories(ArrayList<Category> childCategories) {this.childCategories = childCategories;}

    // adds a category to this category
    public Category addCategory(String name) {
        Category child = new Category(this, name);
        childCategories.add(child);
        return child;
    }

    // Creates and returns a new categories tree
    public static Category createCategories() {
        return new Category(null, "root");
    }



}



//import java.util.ArrayList;
//        import java.util.List;
//
//public class Category {
//
//    private List<Category> children = new ArrayList<Category>();
//    private Category parent;
//    private String name;
//
//    // private constructor
//    private Category(Category parent, String name) {
//        this.parent = parent;
//        this.name = name;
//    }
//
//    // adds a category to this category
//    public Category addCategory(String name) {
//        Category child = new Category(this, name);
//        children.add(child);
//        return child;
//    }
//
//    // creates and returns a new categories tree
//    public static Category createCategories() {
//        return new Category(null, "root");
//    }