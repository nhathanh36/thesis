package com.english_for_kid.thanhnha.englishforkid.model;

/**
 * Created by Thanh Nha on 3/2/2015.
 */
public class Group_VB {
    private int id_group;
    private int image_group;
    private String name_group;

    public Group_VB(int id, int image, String name) {
        this.id_group = id;
        this.image_group = image;
        this.name_group = name;
    }

    public Group_VB() {
        this.id_group = 0;
        this.image_group = 0;
        this.name_group = "";
    }

    public int getId_group() {
        return id_group;
    }

    public String getNameGroup() {
        return name_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public void setNameGroup(String name) {
        this.name_group = name;
    }

    public int getImage_group() {
        return image_group;
    }

    public String getName_group() {
        return name_group;
    }

    public void setImage_group(int image) {
        this.image_group = image;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }
}
