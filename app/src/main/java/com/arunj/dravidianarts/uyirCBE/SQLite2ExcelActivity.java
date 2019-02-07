package com.arunj.dravidianarts.uyirCBE;

import android.content.DialogInterface;
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
import android.widget.Toast;

import com.arunj.dravidianarts.library.SQLiteToExcel;
import com.arunj.dravidianarts.uyirCBE.adapter.CustomAdapter;
import com.arunj.dravidianarts.uyirCBE.db.DBHelper;
import com.arunj.dravidianarts.uyirCBE.db.DBQueries;
import com.arunj.dravidianarts.uyirCBE.model.Users;
import com.arunj.dravidianarts.uyirCBE.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLite2ExcelActivity extends AppCompatActivity {

//    EditText edtUser, edtContactNo ;
    EditText edtUser, edtContactNo, pincode, area, email;
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
                if (validate(edtUser) && validate(edtContactNo) && validate(area) && validate(pincode)) {
                    dbQueries.open();

                    Drawable d = getResources().getDrawable(R.drawable.ic_action_github);
                    Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] bitmapdata = stream.toByteArray();

                    Users users = new Users(edtUser.getText().toString(), edtContactNo.getText().toString(), area.getText().toString(), pincode.getText().toString(), email.getText().toString());
                    if(dbQueries.insertUser(users)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SQLite2ExcelActivity.this);
                        builder.setTitle("Successfully Registered !");
                        builder.setMessage("We welcome your presence");
                        builder.setCancelable(true);
                        builder.show();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(SQLite2ExcelActivity.this);
                        builder.setTitle("Number already registered !");
                        builder.setMessage("Use a different number");
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

    void initViews() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(getApplicationContext());
        dbQueries = new DBQueries(getApplicationContext());

        edtUser = (EditText) findViewById(R.id.edt_user);
        edtContactNo = (EditText) findViewById(R.id.edt_c_no);
//       New
        area = (EditText) findViewById(R.id.area_name);
        pincode = (EditText) findViewById(R.id.pin_code);
        email = (EditText) findViewById(R.id.email_id);

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
