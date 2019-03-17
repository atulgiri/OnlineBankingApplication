package com.example.onlinebankingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity
{
    Button mCustomer;
    Button mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCustomer = findViewById(R.id.iLoginCustomer);
        mManager = findViewById(R.id.iLoginManager);
        mCustomer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent toCustomer = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(toCustomer);
                finish();
            }
        });
        mManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent toManager = new Intent(LoginActivity.this,ManagerLogin.class);
                startActivity(toManager);
                finish();
            }
        });
    }
}
