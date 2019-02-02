package com.ajts.androidmads.sqlite2xlDemo.db;

class
DBConstants {

    static final String USER_TABLE = "customers";
    static final String CONTACT_ID = "contact_id";
    static final String CONTACT_PERSON_NAME = "contact_person_name";
    static final String CONTACT_NO = "contact_no";
    static final String USER_AREA = "area";
    static final String PIN_CODE = "pincode";
    static final String EMAIL_ID ="email_id";
    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ("
            + CONTACT_ID + " INTEGER PRIMARY KEY,"
            + CONTACT_PERSON_NAME + " TEXT,"
            + CONTACT_NO + " TEXT,"
            + USER_AREA + " TEXT, "
            + PIN_CODE + " TEXT, "
            + EMAIL_ID +" TEXT)";

    static final String SELECT_QUERY = "SELECT * FROM " + USER_TABLE;

}
