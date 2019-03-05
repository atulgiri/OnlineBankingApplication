package com.example.onlinebankingapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BranchLocator extends AppCompatActivity
{
    private EditText vplace;
    Button vsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_locator);
        final SQLiteDatabase mdb = this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        mdb.execSQL("CREATE TABLE IF NOT EXISTS places (doorno INT, place VARCHAR, state VARCHAR, pin INT(6))");
        mdb.execSQL("INSERT INTO places (doorno,place,state,pin) VALUES (45,'Kochi','Kerala',623213)");
        mdb.execSQL("INSERT INTO places (doorno,place,state,pin) VALUES (53,'Chennai','Tamil Nadu',600028)");
        mdb.execSQL("INSERT INTO places (doorno,place,state,pin) VALUES (13,'Bangalore','Karnataka',633213)");
        Toast.makeText(getApplicationContext(), "Database Generated", Toast.LENGTH_SHORT).show();
        Log.i("State", "Database Generated");

        vplace = findViewById(R.id.iplace);
        vsearch = findViewById(R.id.isearch);
        vsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gplace = vplace.getText().toString();
                Log.i("State", "Entered Search Button");
                Cursor c = mdb.rawQuery("SELECT * FROM places WHERE place = '" + gplace + "'", null);
                int doornoindex = c.getColumnIndex("doorno");
                int placeindex = c.getColumnIndex("place");
                int stateindex = c.getColumnIndex("state");
                int pinindex = c.getColumnIndex("pin");
                c.moveToFirst();
                String str = c.getString(stateindex);
                int vdoorno = c.getInt(doornoindex);
                String vstate = c.getString(stateindex);
                int vpin = c.getInt(pinindex);
                Log.i("State", str);
                AlertDialog.Builder builder = new AlertDialog.Builder(BranchLocator.this);
                builder.setCancelable(false);
                builder.setIcon(android.R.drawable.ic_menu_directions);
                builder.setTitle("Address");
                builder.setMessage("Door No : " + vdoorno + "\nPlace   : " + gplace + "\nState   : " + vstate + "\nPin     : " + vpin);
                builder.setNegativeButton("Ok", null);
                builder.create().show();
                c.close();
            }
        });

    }
}
