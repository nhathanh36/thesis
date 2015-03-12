package com.english_for_kid.thanhnha.englishforkid.model;

/**
 * Created by Thanh Nha on 3/2/2015.
 */
public class Vocabulary {
    private int id;
    private String name;
    private int image_url;
    private int group;

    public Vocabulary(int id, String name, int image_url, int group) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.group = group;
    }

    public Vocabulary(String name, int group) {
        this.name = name;
        this.group = group;
    }

    public Vocabulary() {
        this.id = 0;
        this.name = "";
        this.group = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    public int getImage() {
        return image_url;
    }

    public void setImage(int image_url) {
        this.image_url = image_url;
    }
}
