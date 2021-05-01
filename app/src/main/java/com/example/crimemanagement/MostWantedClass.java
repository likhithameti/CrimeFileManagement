package com.example.crimemanagement;

// for adding into database
public class MostWantedClass {
    public String imageName;
    public String imageURL;
    public String crime;
    public MostWantedClass(){}

    public MostWantedClass(String name, String crime,String url) {
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
