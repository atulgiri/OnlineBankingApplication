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
import android.widget.EditText;
import android.widget.Toast;

public class ATMLocator extends AppCompatActivity
{
    private EditText vplace;
    Button vsearch;
    Button vhome;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atmlocator);

        final SQLiteDatabase dbATM = this.openOrCreateDatabase("Places1", MODE_PRIVATE, null);

        Toast.makeText(getApplicationContext(), "Database Generated", Toast.LENGTH_SHORT).show();
        vplace = findViewById(R.id.iplace);
        vsearch = findViewById(R.id.isearch);
        vhome = findViewById(R.id.ihome);
        vsearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String gplace = vplace.getText().toString();
                Cursor init = dbATM.rawQuery("SELECT EXISTS(SELECT * FROM placesATM WHERE place = '" + gplace + "')", null);
                while(init.getCount()<=0)
                {
                    AlertDialog.Builder vnullmessage = new AlertDialog.Builder(ATMLocator.this);
                    vnullmessage.setTitle("No entries detected");
                    vnullmessage.setMessage("Please try again");
                    vnullmessage.setPositiveButton("OK",null);
                    vnullmessage.create().show();
                }
                init.close();
                Log.i("State", "EntedbATM Search Button");
                Cursor c = dbATM.rawQuery("SELECT * FROM placesATM WHERE place = '" + gplace + "'", null);
                int doornoindex = c.getColumnIndex("doorno");
                int stateindex = c.getColumnIndex("state");
                int pinindex = c.getColumnIndex("pin");
                int circleindex = c.getColumnIndex("circle");
                c.moveToFirst();
                int vdoorno = c.getInt(doornoindex);
                String vstate = c.getString(stateindex);
                String vcircle = c.getString(circleindex);
                int vpin = c.getInt(pinindex);
                AlertDialog.Builder builder = new AlertDialog.Builder(ATMLocator.this);
                builder.setCancelable(false);
                builder.setIcon(android.R.drawable.ic_menu_directions);
                builder.setTitle("Address");
                builder.setMessage("Door No : " + vdoorno + "\nPlace   : " + gplace + "\nCircle : "+vcircle+"\nState   : " + vstate + "\nPin     : " + vpin);
                builder.setNegativeButton("Ok", null);
                builder.create().show();
                c.close();
            }
        });
        vhome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent back2home = new Intent(ATMLocator.this,LoginActivity.class);
                startActivity(back2home);
                finish();
            }
        });
    }
}
