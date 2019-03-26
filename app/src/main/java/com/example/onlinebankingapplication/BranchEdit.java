package com.example.onlinebankingapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class BranchEdit extends AppCompatActivity 
{
    EditText mPlace;
    CheckBox vLocker;
    CheckBox vAadhaar;
    Button mChange;
    Button mHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_edit);
            final SQLiteDatabase dbBranch = this.openOrCreateDatabase("PlacesBranch", MODE_PRIVATE, null);
            dbBranch.execSQL("DROP TABLE IF EXISTS places");
            dbBranch.execSQL("CREATE TABLE IF NOT EXISTS places (doorno INT, place VARCHAR, state VARCHAR, pin INT(6),locker VARCHAR,updation VARCHAR)");
            dbBranch.execSQL("INSERT INTO places (doorno,place,state,pin,locker,updation) VALUES (45,'Kochi','Kerala',623213,'yes','yes')");
            dbBranch.execSQL("INSERT INTO places (doorno,place,state,pin,locker,updation) VALUES (53,'Chennai','Tamil Nadu',600028,'no','yes')");
            dbBranch.execSQL("INSERT INTO places (doorno,place,state,pin,locker,updation) VALUES (13,'Bangalore','Karnataka',633213,'yes','no')");
            Toast.makeText(getApplicationContext(), "Database Generated", Toast.LENGTH_SHORT).show();
            mPlace = findViewById(R.id.itextplaceB);
            mChange = findViewById(R.id.iButtonChangeB);
            mHome = findViewById(R.id.iButtonHomeB);
            vLocker = findViewById(R.id.ilockerB);
            vAadhaar = findViewById(R.id.iAadhaarB);
            dbBranch.close();
            mChange.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    SQLiteDatabase dbBranch = openOrCreateDatabase("PlacesBranch", MODE_PRIVATE, null);
                    String checkLockerB;
                    String checkUpdaterB;
                    if(vLocker.isChecked())
                    {
                        checkLockerB = "yes";
                    }
                    else
                    {
                        checkLockerB = "no";
                    }
                    if(vAadhaar.isChecked())
                    {
                        checkUpdaterB = "yes";
                    }
                    else
                    {
                        checkUpdaterB = "no";
                    }
                    String vPlace = mPlace.getText().toString();
                    final Cursor rep = dbBranch.rawQuery("UPDATE places SET locker = '" + checkLockerB + "' , updation = '"+checkUpdaterB+"' WHERE place = '" + vPlace + "'", null);
                    Toast.makeText(getApplicationContext(), "Locker : "+checkLockerB, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Aadhaar : "+checkUpdaterB, Toast.LENGTH_SHORT).show();
                    boolean b = rep.moveToFirst();
                    if (b)
                        Toast.makeText(getApplicationContext(), "Entries updated", Toast.LENGTH_SHORT).show();
                    rep.close();
                    dbBranch.close();
                }
            });

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(BranchEdit.this, LoginActivity.class);
                startActivity(test);
                finish();
            }
        });
    }
}
