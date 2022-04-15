package com.pushpa.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pushpa.news_app.Utils.DbHelper;

public class LoginActivity extends AppCompatActivity {
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DbHelper(LoginActivity.this);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                createAddDialog();
//            }
//        });
    }

    private void createAddDialog() {
        AlertDialog.Builder alertLayout = new AlertDialog.Builder(LoginActivity.this);
        View view = getLayoutInflater().inflate(R.layout.activity_login, null);
        alertLayout.setView(view);


//        EditText name = view.findViewById(R.id.name);
//        EditText website = view.findViewById(R.id.web);
//        EditText lat = view.findViewById(R.id.lat);
//        EditText lng = view.findViewById(R.id.lng);
//        Button btnsave = view.findViewById(R.id.saveBtn);

        AlertDialog alert = alertLayout.create();
        alert.show();

//        btnsave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                College college = new College();
//                college.setName(name.getText().toString());
//                college.setWebsite(website.getText().toString());
//                college.setLang(Double.parseDouble(lng.getText().toString()));
//                college.setLat(Double.parseDouble(lat.getText().toString()));
//
//                dbHelper.insertDataToDb(college);
//                rv.setAdapter(new CollegeAdapter(MainActivity.this, dbHelper.retrieveData()));
//                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
//                alert.dismiss();
//
//            }
//        });

    }
}