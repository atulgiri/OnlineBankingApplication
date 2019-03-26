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
public class ManagerLogin extends AppCompatActivity
{
    EditText mUsername;
    EditText mPassword;
    Button mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);
        final SQLiteDatabase dManager = this.openOrCreateDatabase("Managers",MODE_PRIVATE,null);
        dManager.execSQL("CREATE TABLE IF NOT EXISTS  managers (username VARCHAR,password VARCHAR)");
        dManager.execSQL("INSERT INTO managers (username, password) VALUES ('Justin','mylo')");
        dManager.execSQL("INSERT INTO managers (username, password) VALUES ('Robert','harry')");
        dManager.execSQL("INSERT INTO managers (username, password) VALUES ('Atul Giri','123456')");
        Toast.makeText(getApplicationContext(),"Database for Managers generated",Toast.LENGTH_SHORT).show();
        mUsername = findViewById(R.id.iuserEditText);
        mPassword = findViewById(R.id.ipassEditText);
        mLogin = findViewById(R.id.iLogin);
        dManager.close();
        mLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SQLiteDatabase dManager = openOrCreateDatabase("Managers",MODE_PRIVATE,null);
                String vuser =  mUsername.getText().toString();
                String vpass = mPassword.getText().toString();
                Cursor logdata = dManager.rawQuery("SELECT * FROM managers WHERE username = '"+vuser+"' AND password = '"+vpass+"'",null);
                if(logdata.getCount()<=0)
                {
                    Toast.makeText(getApplicationContext(),"No such users",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Welcome "+vuser,Toast.LENGTH_SHORT).show();
                    Intent manage = new Intent(ManagerLogin.this,ManagerEdit.class);
                    startActivity(manage);
                    finish();
                }
                logdata.close();
                dManager.close();
            }
        });

    }
}
