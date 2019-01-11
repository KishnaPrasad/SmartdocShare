package com.example.hp.smartdocshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sem56 extends AppCompatActivity {
    Button b1,b2;
    String sem=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem56);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem="5";

                Intent in=new Intent(sem56.this,detailsem56.class);
                in.putExtra("sem",sem);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem="6";
                Intent in=new Intent(sem56.this,detailsem56.class);
                in.putExtra("sem",sem);
                startActivity(in);
            }
        });
    }
}
