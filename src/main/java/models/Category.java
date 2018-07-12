package models;

import java.util.ArrayList;

public class Category {

    private int id;
    private String categoryName;
    private Category parentCategory;
    private ArrayList<Category> childCategories;

    public Category(String categoryName, Category parentCategory, ArrayList<Category> childCategories) {
        this.id = id;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.childCategories = childCategories;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getCategoryName() {return categoryName;}
    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    public Category getParentCategory() {return parentCategory;}
    public void setParentCategory(Category parentCategory) {this.parentCategory = parentCategory;}

    public ArrayList<Category> getChildCategories() {return childCategories;}
    public void setChildCategories(ArrayList<Category> childCategories) {this.childCategories = childCategories;}
}