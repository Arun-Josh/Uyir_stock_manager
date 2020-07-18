package com.arunj.dravidianarts.uyirCBEStocks.model;

public class Users {

//    private String userId;
//    private String contactPersonName;
//    private String contactNumber;
//    private String date;
//    private byte[] contactPhoto;
//    private String area;
//    private String pincode;
//    private String email;

    public Users(String date, String name, String product, String codeNo, String batch, String total) {
        this.date = date;
        this.name = name;
        this.product = product;
        this.codeNo = codeNo;
        this.batch = batch;
        this.total = total;
    }

    private String date;
    private String name;
    private String product;
    private String codeNo;
    private String batch;

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public String getBatch() {
        return batch;
    }

    public String getTotal() {
        return total;
    }

    private String total;

//    public Users(String contactPersonName, String contactNumber, String area , String pincode, String email) {
//        this.contactPersonName = contactPersonName;
//        this.contactNumber = contactNumber;
//        this.area = area;
//        this.pincode = pincode;
//        this.email = email;
//    }
//
//    public Users(String userId, String textcolor2PersonName, String textcolor2Number, String area, String pincode, String email) {
//        this.userId = userId;
//        this.textcolor2PersonName = textcolor2PersonName;
//        this.textcolor2Number = textcolor2Number;
//        this.area = area;
//        this.pincode = pincode;
//        this.email = email;
//    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public String getContactPersonName() {
//        return contactPersonName;
//    }
//
//    public String getContactNumber() {
//        return contactNumber;
//    }
//
//    public String getArea(){
//        return area;
//    }
//
//    public String getPincode(){
//        return pincode;
//    }
//
//    public String getEmail(){
//        return email;
//    }
//
//    public String getDate(){
//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
//        String date = sdf.format(new Date());
//        return date;
//    }
}
