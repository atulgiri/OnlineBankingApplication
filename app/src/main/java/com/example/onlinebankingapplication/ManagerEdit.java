package com.example.onlinebankingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class ManagerEdit extends AppCompatActivity
{
    Button mEditButton;
    Switch mSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_edit);
        mEditButton = findViewById(R.id.iEdit);
        mSwitch = findViewById(R.id.iSwitch);
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(mSwitch.isChecked())
                {
                    Intent toBranchEdit = new Intent(ManagerEdit.this,BranchEdit.class);
                    startActivity(toBranchEdit);
                    finish();
                }
                else
                {
                    Intent toATMEdit = new Intent(ManagerEdit.this,ATMEdit.class);
                    startActivity(toATMEdit);
                    finish();
                }
            }
        });

    }
}
