package com.example.crimemanagement;

public class ComplaintClass {
    public String imageURL;
    public String crimeloc;
    public String crimetype;
    public String crimedescription;
    public String usernaam;
    public String aadhaar;
    public String status;

    public ComplaintClass(String imageURL, String crimeloc, String crimetype, String crimedescription, String username, String aadhaar, String status) {
        this.imageURL = imageURL;
        this.crimeloc = crimeloc;
        this.crimetype = crimetype;
        this.crimedescription = crimedescription;
        this.usernaam = username;
        this.aadhaar = aadhaar;
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCrimeloc() {
        return crimeloc;
    }

    public void setCrimeloc(String crimeloc) {
        this.crimeloc = crimeloc;
    }

    public String getCrimetype() {
        return crimetype;
    }

    public void setCrimetype(String crimetype) {
        this.crimetype = crimetype;
    }

    public String getCrimedescription() {
        return crimedescription;
    }

    public void setCrimedescription(String crimedescription) {
        this.crimedescription = crimedescription;
    }

    public String getUsernaam() {
        return usernaam;
    }

    public void setUsernaam(String username) {
        this.usernaam = username;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }
}
