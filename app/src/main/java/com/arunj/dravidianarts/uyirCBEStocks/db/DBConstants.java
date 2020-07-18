package com.arunj.dravidianarts.uyirCBEStocks.db;

class
DBConstants {

    static final String STOCK_TABLE = "Stocks";
    static final String S_NO = "S.no";
    static final String STOCK_DATE = "Date";
    static final String STOCK_NAME = "Name";
    static final String STOCK_PRODUCT = "Product";
    static final String STOCK_CODE_NO = "Code_no";
    static final String STOCK_BATCH ="Batch";
    static final String STOCK_TOTAL = "Total";
    static final String CREATE_STOCK_TABLE = "CREATE TABLE IF NOT EXISTS " + STOCK_TABLE + " ("
            + "\"" + S_NO  + "\"" + " INTEGER PRIMARY KEY,"
            + STOCK_DATE + " TEXT,"
            + STOCK_NAME + " TEXT,"
            + STOCK_PRODUCT + " TEXT, "
            + STOCK_CODE_NO + " TEXT, "
            + STOCK_BATCH +" TEXT,"
            + STOCK_TOTAL +" TEXT)";
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
    static final String SELECT_QUERY = "SELECT * FROM " + STOCK_TABLE;

}
