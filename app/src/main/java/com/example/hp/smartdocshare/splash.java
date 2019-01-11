package com.example.hp.smartdocshare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int s=0,n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = getSharedPreferences("mp", 0);
        editor = sp.edit();

        SharedPreferences sp = getSharedPreferences("mp", 0);
        String email=sp.getString("email",null);
        //String emial=sp.getString("password",null);
        if(email!=null)
        {
            s=1;
        }
        else
        {
            n=1;
        }
        Thread d=new Thread()
        {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(2000);
                }
                catch(Exception e){

                }
                finally {

                    if(n==1)
                    {
                        Intent in = new Intent(splash.this, LoginActivity.class);
                        startActivity(in);
                    }
                    else
                    {
                        Intent in = new Intent(splash.this,MainActivity.class);
                        startActivity(in);
                    }
                }

            }
        };
        d.start();
    }
}
