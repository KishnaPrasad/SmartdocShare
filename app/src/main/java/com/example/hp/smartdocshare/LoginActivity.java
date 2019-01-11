package com.example.hp.smartdocshare;

import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity{
    EditText txtUname, txtPassword;
    Button btnLogin;
    String email, password, resultstring, uname1, upass1;
    String checkvalue = "true";
    int no = 0,sus=0,sem=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUname = (EditText) findViewById(R.id.edt1);
        txtPassword = (EditText) findViewById(R.id.edt2);
        btnLogin = (Button) findViewById(R.id.student_sig);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                email = txtUname.getText().toString();
                password = txtPassword.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Both Field", Toast.LENGTH_LONG).show();
                } else {
                    ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo netw = con.getActiveNetworkInfo();
                    if (netw != null && netw.isConnected()) {
                        //new LoginAsyncTask().execute("https://smstest1.000webhostapp.com/smstest/admlogin.php");
                        new LoginActivity.LoginAsyncTask().execute("https://acc-smartdocshare.000webhostapp.com/login_check.php");
                    } else {
                        Toast.makeText(getApplicationContext(), "Internet Connection not available", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog pd;

        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(LoginActivity.this, " ", "Verify Username & Password", true);

        }

        @Override
        protected String doInBackground(String... arg0) {
            //url mate use thay,arg0=url
            return LoginCheckData(arg0[0], email.toString(), password.toString());
        }

        protected void onPostExecute(String resulet) {
            pd.dismiss();

            if(sus==1)
            {

                SharedPreferences sp = getApplicationContext().getSharedPreferences("mp", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("uname", email);
                editor.putString("pwd", password);
                editor.putInt("sem", sem);
                Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
                editor.commit();
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
            else
            {

                no = 1;
            }

       /*     if(resulet!=null)
            {
                if(username.equals(uname1) && password.equals(upass1))
                {
                    Toast.makeText(getApplicationContext(),"WELCOME", Toast.LENGTH_LONG).show();

                    Intent i1=new Intent(LoginActivity.this,.class);
                    startActivity(i1);
                }*/
            if (no == 1) {
                Toast.makeText(getApplicationContext(), "Username/Password Not match", Toast.LENGTH_LONG).show();
            }

        }
    }
    //}





    public String LoginCheckData(String url, String email, String password) {

        try {
            Log.v("response done", "data");
            //enable httpclient
            HttpClient ht = new DefaultHttpClient();

            //post method enable
            HttpPost hp = new HttpPost(url);

            //json object format
            List<NameValuePair> namevalue = new ArrayList<NameValuePair>();

            //1st "" che a php varu name,& 2nd android ma uper che a name
            namevalue.add(new BasicNameValuePair("id", email));
            namevalue.add(new BasicNameValuePair("password", password));
            //namevalue.add(new BasicNameValuePair("checkvalue", checkvalue));

            //send data
            //data trasfer with encoded form,its convert default ansi ex.A moklvu hoe to B jay (+1 krine)
            hp.setEntity(new UrlEncodedFormEntity(namevalue));

            //fetch the response
            //send thyel data no responce mate
            //execute data retriv krva mate
            //ht=http client
            //hp=http post
            HttpResponse res = ht.execute(hp);
            Log.v("response done", res.toString());

            //fetch entity
            //res=httpresponseadsg

            //htttpentity is class
            //set kreli uper entity che ane get kravva mate
            HttpEntity en = res.getEntity();
            Log.v("entity done", en.toString());

            Log.v("data",en.toString());
            if (en != null) {

                // Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
                resultstring = EntityUtils.toString(en);
                Log.v("ResultString",resultstring);
                JSONObject json = new JSONObject(resultstring);
                String mes=json.getString("message");
                sus=json.getInt("success");
                sem=json.getInt("sem");
                Log.v("Answ",mes);
                Log.v("Seme",sem+"");
                Log.v("Ans",sus+"");

                    /*if (resultstring != null) {
                        JSONObject json = new JSONObject(resultstring);
                        //json array name logindata from a_login.php
                        JSONArray jsondata = json.getJSONArray("logindata");
                        int size = jsondata.length();

                        for (int no = 0; no < size; no++) {
                            JSONObject jn = jsondata.getJSONObject(no);

                            uname1 = jn.getString("email");
                            upass1 = jn.getString("password");
                        }
                    }*/
            }

            return resultstring;
        } catch (Exception e)
        {
            Log.v("Exception",e.toString());
        }
        return resultstring;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //    getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

}








