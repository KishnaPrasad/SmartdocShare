package com.example.hp.smartdocshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sem12 extends AppCompatActivity {
    Button b1,b2;
    String sem=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem12);

        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(sem12.this,detailsem12.class);
                sem="1";
                in.putExtra("sem",sem);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(sem12.this,detailsem12.class);
                sem="2";
                in.putExtra("sem",sem);
                startActivity(in);
            }
        });
    }
}
