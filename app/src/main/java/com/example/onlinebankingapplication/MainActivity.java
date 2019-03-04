package com.example.onlinebankingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button mbutton;
    Switch mswitch1;
    //    private FirebaseAuth.AuthStateListener FirebaseAuthListener;
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        mbutton = findViewById(R.id.button1);
        mswitch1 = findViewById(R.id.switch1);
        mbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String str;
                Log.i("Switch Value : ", String.valueOf(mswitch1.isChecked()));
                if(mswitch1.isChecked())
                    str = "Branch";
                else
                    str = "ATM";
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                if(mswitch1.isChecked())
                {
                    Intent tran1 = new Intent(MainActivity.this, BranchLocator.class);
                    startActivity(tran1);
                    finish();
                }
                else
                {
                    Intent tran2 = new Intent(MainActivity.this, ATMLocator.class);
                    startActivity(tran2);
                    finish();
                }
            }
        });
//        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
//        FirebaseAuthListener =

    }
}
