package com.example.hp.smartdocshare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class edu_not extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_not);
        SharedPreferences sp = getSharedPreferences("mp", 0);
        int sem=sp.getInt("sem",0);


        Toast.makeText(getApplicationContext(),sem+"", Toast.LENGTH_SHORT).show();

        Button fy = (Button) findViewById(R.id.b1);
        Button sy = (Button) findViewById(R.id.b2);
        Button ty = (Button) findViewById(R.id.b3);

        if(sem==1 || sem==2)
        {
            fy.setVisibility(View.VISIBLE);
        }
        else if(sem==3 || sem==4)
        {
            sy.setVisibility(View.VISIBLE);
        }
        else if(sem==5 || sem==6)
        {
            ty.setVisibility(View.VISIBLE);
        }

        fy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(edu_not.this,sem12.class);
                startActivity(in);
            }
        });

        sy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(edu_not.this,sem34.class);
                startActivity(in);
            }
        });


        ty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(edu_not.this,sem56.class);
                startActivity(in);
            }
        });

    }
}
