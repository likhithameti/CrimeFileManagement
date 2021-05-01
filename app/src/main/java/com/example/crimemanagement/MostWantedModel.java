package com.example.crimemanagement;

public class MostWantedModel {
    public String imageName;
    public String imageURL;
    public String crime;
    public MostWantedModel(){}

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public MostWantedModel(String name, String crime, String url) {
        this.imageName = name;
        this.imageURL = url;
        this.crime = crime;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }
    public String getCrime(){
        return crime;
    }
}
