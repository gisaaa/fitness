package com.ct.fitness.models;

/**
 * Created by Elia on 12/18/2017.
 */

public class Exercise {
    private int imageId;
    private String imageName;

    public Exercise() {
    }

    public Exercise(int imageId, String imageName) {
        this.imageId = imageId;
        this.imageName = imageName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
