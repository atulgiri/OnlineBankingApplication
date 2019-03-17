package com.example.onlinebankingapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class BranchLocator extends AppCompatActivity
{
    private EditText vplace;
    Button vsearch;
    Button vhome;
    CheckBox vLocker;
    CheckBox vUpdater;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_locator);
        final SQLiteDatabase dbBranch = this.openOrCreateDatabase("PlacesBranch", MODE_PRIVATE, null);
        dbBranch.execSQL("DROP TABLE IF EXISTS places");
        dbBranch.execSQL("CREATE TABLE IF NOT EXISTS places (doorno INT, place VARCHAR, state VARCHAR, pin INT(6),locker VARCHAR,updation VARCHAR)");
        dbBranch.execSQL("INSERT INTO places (doorno,place,state,pin,locker,updation) VALUES (45,'Kochi','Kerala',623213,'yes','yes')");
        dbBranch.execSQL("INSERT INTO places (doorno,place,state,pin,locker,updation) VALUES (53,'Chennai','Tamil Nadu',600028,'no','yes')");
        dbBranch.execSQL("INSERT INTO places (doorno,place,state,pin,locker,updation) VALUES (13,'Bangalore','Karnataka',633213,'yes','no')");
        Toast.makeText(getApplicationContext(), "Database Generated", Toast.LENGTH_SHORT).show();
        vplace = findViewById(R.id.iplace);
        vsearch = findViewById(R.id.isearch);
        vhome = findViewById(R.id.ihome);
        vLocker = findViewById(R.id.ilocker);
        vUpdater = findViewById(R.id.iUpdater);
        vsearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String gplace = vplace.getText().toString();
                Log.i("State", "Entered Search Button");
                String checkLocker,checkUpdater;
                if(vLocker.isChecked())
                {
                    checkLocker = "yes";
                }
                else
                {
                    checkLocker = "no";
                }
                if(vUpdater.isChecked())
                {
                    checkUpdater = "yes";
                }
                else
                {
                    checkUpdater = "no";
                }
                Cursor c = dbBranch.rawQuery("SELECT * FROM places WHERE place = '" + gplace + "' AND locker = '"+checkLocker+"' AND updation = '"+checkUpdater+"'", null);
                if(c.getCount()<=0)
                {
                    AlertDialog.Builder vnullmessage = new AlertDialog.Builder(BranchLocator.this);
                    vnullmessage.setTitle("No entries found");
                    vnullmessage.setMessage("Please try again");
                    vnullmessage.setPositiveButton("OK",null);
                    vnullmessage.create().show();
                }
                else {
                    int doornoindex = c.getColumnIndex("doorno");
                    int stateindex = c.getColumnIndex("state");
                    int pinindex = c.getColumnIndex("pin");
                    int lockerindex = c.getColumnIndex("locker");
                    int updationindex = c.getColumnIndex("updation");
                    c.moveToFirst();
                    int vdoorno = c.getInt(doornoindex);
                    String vstate = c.getString(stateindex);
                    int vpin = c.getInt(pinindex);
                    String vlocker = c.getString(lockerindex);
                    String vupdater = c.getString(updationindex);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BranchLocator.this);
                    builder.setCancelable(false);
                    builder.setIcon(android.R.drawable.ic_menu_directions);
                    builder.setTitle("Address");
                    builder.setMessage("Door No : " + vdoorno + "\nPlace   : " + gplace + "\nState   : " + vstate + "\nPin     : " + vpin + "\nLocker : " + vlocker + "\nUpdation Availability : " + vupdater);
                    builder.setNegativeButton("Ok", null);
                    builder.create().show();
                }
                c.close();
            }
        });
        vhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent back2home = new Intent(BranchLocator.this,LoginActivity.class);
                startActivity(back2home);
                finish();
            }
        });

    }
}
