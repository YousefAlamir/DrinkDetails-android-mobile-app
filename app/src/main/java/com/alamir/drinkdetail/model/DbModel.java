package com.alamir.drinkdetail.model;



/**
 * @Class_name:  DbModel
 * @Description: this class is model to represents the database items
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */


public class DbModel {
    String ID, name, desc, prepare, type, img;

    public DbModel(String ID, String name, String desc, String prepare, String type, String img) {
        this.ID = ID;
        this.name = name;
        this.desc = desc;
        this.prepare = prepare;
        this.type = type;
        this.img = img;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public void setDate(String desc) {
        this.desc = desc;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public String getType() {
        return desc;
    }

    public void setDesc(String desc) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
