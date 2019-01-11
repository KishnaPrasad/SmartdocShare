package com.example.hp.smartdocshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sem34 extends AppCompatActivity {
    Button b1,b2;
    String sem=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem34);

        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(sem34.this,detailsem34.class);
                sem="3";
                in.putExtra("sem",sem);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(sem34.this,detailsem34.class);
                sem="4";
                in.putExtra("sem",sem);
                startActivity(in);
            }
        });
    }
}
