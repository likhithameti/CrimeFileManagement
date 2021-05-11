package com.example.crimemanagement;

public class AdminComplaintStatusModel {
    public String status;
    public String usernaam;
    public String crimeloc;
    public String crimetype;
    public String imageURL;
    public String aadhaar;
    public String crimedescription;
    public String date;
    public String id;

    public AdminComplaintStatusModel() {
    }

    public AdminComplaintStatusModel(String status, String usernaam, String crimeloc, String crimetype, String imageURL, String aadhaar, String crimedescription,String date,String id) {
        this.status = status;
        this.usernaam = usernaam;
        this.crimeloc = crimeloc;
        this.crimetype = crimetype;
        this.imageURL = imageURL;
        this.aadhaar = aadhaar;
        this.crimedescription = crimedescription;
        this.date = date;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsernaam() {
        return usernaam;
    }

    public void setUsernaam(String usernaam) {
        this.usernaam = usernaam;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getCrimedescription() {
        return crimedescription;
    }

    public void setCrimedescription(String crimedescription) {
        this.crimedescription = crimedescription;
    }

}
