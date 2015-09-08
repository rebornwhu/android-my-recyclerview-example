package com.example.customadapter;

public class Leader {

    private String name;
    private int image;
    private String comment;

    public Leader(String name, int image, String comment) {
        this.name = name;
        this.image = image;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}