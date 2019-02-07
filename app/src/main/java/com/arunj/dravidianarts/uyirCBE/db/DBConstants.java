package com.arunj.dravidianarts.uyirCBE.db;

class
DBConstants {

    static final String USER_TABLE = "Customers";
    static final String CONTACT_ID = "Contact_id";
    static final String CONTACT_PERSON_NAME = "Contact_person_name";
    static final String CONTACT_NO = "Contact_no";
    static final String USER_AREA = "Area";
    static final String PIN_CODE = "Pincode";
    static final String EMAIL_ID ="Email_id";
    static final String DATE = "Date";
    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ("
            + CONTACT_ID + " INTEGER PRIMARY KEY,"
            + CONTACT_PERSON_NAME + " TEXT,"
            + CONTACT_NO + " TEXT UNIQUE,"
            + USER_AREA + " TEXT, "
            + PIN_CODE + " TEXT, "
            + EMAIL_ID +" TEXT,"
            + DATE +" TEXT)";
/*
    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE +" ("
            + CONTACT_ID + " INTEGER ,"
            + CONTACT_PERSON_NAME + " TEXT,"
            + CONTACT_NO + " TEXT,"
            + USER_AREA + " TEXT,"
            + PIN_CODE + " TEXT,"
            + EMAIL_ID +" TEXT,"
            + "PRIMARY KEY ( "+CONTACT_ID +"," +CONTACT_NO+"))";
*/
    static final String SELECT_QUERY = "SELECT * FROM " + USER_TABLE;

}
