package com.arunj.dravidianarts.uyirCBE.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Users {

    private String userId;
    private String contactPersonName;
    private String contactNumber;
    private String date;
    private byte[] contactPhoto;
    private String area;
    private String pincode;
    private String email;

    public Users(String contactPersonName, String contactNumber, String area , String pincode, String email) {
        this.contactPersonName = contactPersonName;
        this.contactNumber = contactNumber;
        this.area = area;
        this.pincode = pincode;
        this.email = email;
    }

    public Users(String userId, String contactPersonName, String contactNumber, String area, String pincode, String email) {
        this.userId = userId;
        this.contactPersonName = contactPersonName;
        this.contactNumber = contactNumber;
        this.area = area;
        this.pincode = pincode;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getArea(){
        return area;
    }

    public String getPincode(){
        return pincode;
    }

    public String getEmail(){
        return email;
    }

    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
        String date = sdf.format(new Date());
        return date;
    }
}
