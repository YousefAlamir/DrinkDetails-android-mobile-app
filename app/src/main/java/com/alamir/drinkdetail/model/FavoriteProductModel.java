package com.alamir.drinkdetail.model;


/**
 * @Class_name:  FavoriteProductModel
 * @Description: this class is model to represents the favorite table items
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */



public class FavoriteProductModel {
    int img;
    String name, desc;
    boolean favorite;

    public FavoriteProductModel(int img, String name, String desc, boolean favorite) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.favorite = favorite;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
