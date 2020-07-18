package com.arunj.dravidianarts.uyirCBEStocks;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.arunj.dravidianarts.library.SQLiteToExcel;
import com.arunj.dravidianarts.uyirCBEStocks.adapter.CustomAdapter;
import com.arunj.dravidianarts.uyirCBEStocks.db.DBHelper;
import com.arunj.dravidianarts.uyirCBEStocks.db.DBQueries;
import com.arunj.dravidianarts.uyirCBEStocks.model.Users;
import com.arunj.dravidianarts.uyirCBEStocks.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLite2ExcelActivity extends AppCompatActivity {

//    EditText edtUser, edtContactNo ;
    EditText date, name, product, codeNo, batch, total;
    Button btnSaveUser, btnExport, btnExportExclude;
    ListView lvUsers;
    CustomAdapter lvUserAdapter;
    List<Users> usersList = new ArrayList<>();

    DBHelper dbHelper;
    DBQueries dbQueries;
    String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Backup/";
    SQLiteToExcel sqliteToExcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_2_xl);

        initViews();

        File file = new File(directory_path);
        if (!file.exists()) {
            Log.v("File Created", String.valueOf(file.mkdirs()));
        }

        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (validate(name) && validate(product) &&  validate(codeNo) && validate(batch) && validate(total) ) {
                if (validate(name)){
                dbQueries.open();

                    Drawable d = getResources().getDrawable(R.drawable.ic_action_github);
                    Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] bitmapdata = stream.toByteArray();

                    Users users = new Users(date.getText().toString(), name.getText().toString(), product.getText().toString(), codeNo.getText().toString(), batch.getText().toString(), total.getText().toString());
                    if(dbQueries.insertUser(users)){
//                        finish();
//                        new MainActivity().success();
//                         try {
                             AlertDialog.Builder builder = new AlertDialog.Builder(SQLite2ExcelActivity.this);
                             builder.setTitle("Success !");
                             builder.setMessage("Stock added successfully");
                             builder.setCancelable(true);
                             builder.show();
//                             Thread.sleep(1000);
//                             finish();
//                        }catch (Exception e){
//
//                        }

                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SQLite2ExcelActivity.this);
                        builder.setTitle("Error !");
                        builder.setMessage("something went wrong");
                        builder.setCancelable(true);
                        builder.show();
//                        Utils.showSnackBar(view, "Number already registered !");
                    }
                    usersList = dbQueries.readUsers();
                    lvUserAdapter = new CustomAdapter(getApplicationContext(), usersList);
                    lvUsers.setAdapter(lvUserAdapter);
                    dbQueries.close();

                    btnExport.performClick();
                }
            }

        });

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Export SQLite DB as EXCEL FILE
                sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DBHelper.DB_NAME, directory_path);
                sqliteToExcel.exportAllTables("users.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {
//                        try{
//                            Thread.sleep(500);
//                        }
//                        catch (Exception e){
//                            Utils.showSnackBar(view,"Error");
//                        }
//                        Utils.showSnackBar(view, "Successfully Exported");
                    }

                    @Override
                    public void onError(Exception e) {
                        Utils.showSnackBar(view, e.getMessage());
                    }
                });
            }
        });

        btnExportExclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Export SQLite DB as EXCEL FILE
                sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DBHelper.DB_NAME, directory_path);
                // Exclude Columns
                List<String> excludeColumns = new ArrayList<>();
                excludeColumns.add("contact_id");

                // Prettify or Naming Columns
                HashMap<String, String> prettyNames = new HashMap<>();
                prettyNames.put("contact_person_name", "Person Name");
                prettyNames.put("contact_no", "Mobile Number");

                sqliteToExcel.setExcludeColumns(excludeColumns);
                sqliteToExcel.setPrettyNameMapping(prettyNames);
                sqliteToExcel.setCustomFormatter(new SQLiteToExcel.ExportCustomFormatter() {
                    @Override
                    public String process(String columnName, String value) {
                        switch (columnName) {
                            case "contact_person_name":
                                value = "Sale";
                        }
                        return value;
                    }
                });
                sqliteToExcel.exportAllTables("users1.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Utils.showSnackBar(view, "Successfully Exported");
                    }

                    @Override
                    public void onError(Exception e) {
                        Utils.showSnackBar(view, e.getMessage());
                    }
                });
            }
        });
    }

    boolean validate(EditText editText) {
        if (editText.getText().toString().length() == 0) {
            editText.setError("Field Required");
            editText.requestFocus();
        }
        return editText.getText().toString().length() > 0;
    }

    boolean validatePhonenum(EditText editText){
        if (editText.getText().toString().length() == 0) {
            editText.setError("Field Required");
            editText.requestFocus();
            return false;
        }
        if(editText.getText().toString().length()<10){
            AlertDialog.Builder builder = new AlertDialog.Builder(SQLite2ExcelActivity.this);
            builder.setTitle("Mobile Number Wrong !");
            builder.setMessage("Check the number entered");
            builder.setCancelable(true);
            builder.show();
            return false;
        }
        return true;
    }

    void initViews() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(getApplicationContext());
        dbQueries = new DBQueries(getApplicationContext());

//        edtUser = (EditText) findViewById(R.id.edt_user);
//        edtContactNo = (EditText) findViewById(R.id.edt_c_no);
//       New
//        area = (EditText) findViewById(R.id.area_name);
//        pincode = (EditText) findViewById(R.id.pin_code);
//        email = (EditText) findViewById(R.id.email_id);
        date = (EditText) findViewById(R.id.date);
        name = (EditText) findViewById(R.id.name);
        product = (EditText) findViewById(R.id.product);
        codeNo = (EditText) findViewById(R.id.codeNo);
        batch = (EditText) findViewById(R.id.batch);
        total = (EditText) findViewById(R.id.total);

        btnSaveUser = (Button) findViewById(R.id.btn_save_user);
        btnExport = (Button) findViewById(R.id.btn_export);
        btnExportExclude = (Button) findViewById(R.id.btn_export_with_exclude);
        lvUsers = (ListView) findViewById(R.id.lv_users);

        dbQueries.open();
        usersList = dbQueries.readUsers();
        lvUserAdapter = new CustomAdapter(getApplicationContext(), usersList);
        lvUsers.setAdapter(lvUserAdapter);
        dbQueries.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return true;
    }

}
