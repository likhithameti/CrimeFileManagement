package com.example.crimemanagement;

public class UserComplaintStatusModel {
    public String status;
    public String date;
    public String type;
    public String zone;
    public String complaintid;
    public String imageURL;

    public UserComplaintStatusModel(){}

    public UserComplaintStatusModel(String status, String date, String type, String zone, String complaintid, String imageURL) {
        this.status = status;
        this.date = date;
        this.type = type;
        this.zone = zone;
        this.complaintid = complaintid;
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getComplaintid() {
        return complaintid;
    }

    public void setComplaintid(String complaintid) {
        this.complaintid = complaintid;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
