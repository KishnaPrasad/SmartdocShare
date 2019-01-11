package com.example.hp.smartdocshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class detailsem34 extends AppCompatActivity {

    Button b1,b2,b3,b4;
    String sem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsem34);

        b1=(Button)findViewById(R.id.button4);
        b2=(Button)findViewById(R.id.button5);
        b3=(Button)findViewById(R.id.button6);
        b4=(Button)findViewById(R.id.button7);
        sem=null;

        Intent data = getIntent();
        sem=data.getStringExtra("sem");

        Toast.makeText(getApplicationContext(),sem,Toast.LENGTH_SHORT).show();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(detailsem34.this,Assignments.class);
                in.putExtra("s",sem);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(detailsem34.this,books.class);
                in.putExtra("s",sem);
                startActivity(in);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(detailsem34.this,questions.class);
                in.putExtra("s",sem);
                startActivity(in);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(detailsem34.this,detailsem12.class);
                in.putExtra("s",sem);
                startActivity(in);
            }
        });
    }
}
