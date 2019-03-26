package com.example.onlinebankingapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ATMEdit extends AppCompatActivity
{
    EditText mPlace;
    EditText mCircle;
    Button mChange;
    Button mHome;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atmedit);
        final SQLiteDatabase dbATM = this.openOrCreateDatabase("Places1", MODE_PRIVATE, null);
        mPlace = findViewById(R.id.itextplace);
        mCircle = findViewById(R.id.itextcircle);
        mChange = findViewById(R.id.iButtonChange);
        mHome = findViewById(R.id.iButtonHome);
        dbATM.execSQL("DROP TABLE IF EXISTS placesATM");
        dbATM.execSQL("CREATE TABLE IF NOT EXISTS placesATM (doorno INT,circle VARCHAR, place VARCHAR, state VARCHAR, pin INT(6))");
        dbATM.execSQL("INSERT INTO placesATM (doorno,circle,place,state,pin) VALUES (45,'Kaloor','Kochi','Kerala',623213)");
        dbATM.execSQL("INSERT INTO placesATM (doorno,circle,place,state,pin) VALUES (53,'Anna Nagar','Chennai','Tamil Nadu',600028)");
        dbATM.execSQL("INSERT INTO placesATM (doorno,circle,place,state,pin) VALUES (13,'White Feild','Bangalore','Karnataka',633213)");
        dbATM.close();
        mChange.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbATM = openOrCreateDatabase("Places1", MODE_PRIVATE, null);
                String vPlace = mPlace.getText().toString();
                String vCircle = mCircle.getText().toString();
                Cursor rep = dbATM.rawQuery("UPDATE placesATM SET circle = '" + vCircle + "' WHERE place = '" + vPlace + "'", null);
                boolean b = rep.moveToFirst();
                if (b)
                    Toast.makeText(getApplicationContext(), "Entries updated", Toast.LENGTH_SHORT).show();
                rep.close();
                dbATM.close();
            }
        });
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(ATMEdit.this, LoginActivity.class);
                startActivity(test);
                finish();
            }
        });
    }
}
