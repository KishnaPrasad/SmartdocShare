package com.example.hp.smartdocshare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class books extends AppCompatActivity {
    Button b1,b2;
    int no=0;
    String checkvalue = "true";
    String resultstring;
    String sem;
    ListView list;
    La_books adapter;
    XMLParser parser;
    static final String KEY_SONG = "as_view"; // parent node
    static final String KEY_ID = "emp";
    static final String KEY_TITLE = "sub";
    static final String KEY_ARTIST = "div";
    static final String KEY_DURATION = "deadline";
    //static final String KEY_THUMB_URL = "div";
    String n,a,b,c,d;
    static final String URL = "https://acc-smartdocshare.000webhostapp.com/book.xml";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Intent data = getIntent();
        sem=data.getStringExtra("s");




        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Toast.makeText(getApplicationContext(),sem,Toast.LENGTH_LONG).show();
        NetworkInfo netw = con.getActiveNetworkInfo();
        if (netw != null && netw.isConnected()) {

            sem=data.getStringExtra("s");
            //new LoginAsyncTask().execute("https://smstest1.000webhostapp.com/smstest/admlogin.php");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                new LoginAsyncTask().execute("https://acc-smartdocshare.000webhostapp.com/insert_book.php");
            }
        } else {
            Toast.makeText(getApplicationContext(), "Internet Connection not available", Toast.LENGTH_LONG).show();

        }

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML from URL
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_SONG);
        // looping through all song nodes &lt;song&gt;
        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key =&gt; value
            map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
            map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
            map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
            //map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));


            // adding HashList to ArrayList
            songsList.add(map);
        }

        list=(ListView)findViewById(R.id.list);
        String number[]={"data","show"};
        // Getting adapter by passing xml data ArrayList
        adapter=new La_books(this, songsList,number);
        list.setAdapter(adapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> song = new HashMap<String, String>();
                song = songsList.get(position);

                String nam=song.get(books.KEY_TITLE);

                //Toast.makeText(getApplicationContext(),nam.toString(),Toast.LENGTH_LONG).show();
                Intent in = new Intent(books.this,viewdownload.class);

                //in.putExtra("a",a);
                in.putExtra("nm",nam.toString());
                /*in.putExtra("b",b);
                in.putExtra("c",c);
                in.putExtra("d",d);*/
                startActivity(in);
            }
        });
        //listview over




    }

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog pd;

        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(books.this, " ", "loading", true);

        }

        @Override
        protected String doInBackground(String... arg0) {
            //url mate use thay,arg0=url
            return LoginCheckData(arg0[0], sem);
        }

        protected void onPostExecute(String resulet) {
            pd.dismiss();


       /*     if(resulet!=null)
            {
                if(username.equals(uname1) && password.equals(upass1))
                {
                    Toast.makeText(getApplicationContext(),"WELCOME", Toast.LENGTH_LONG).show();

                    Intent i1=new Intent(LoginActivity.this,.class);
                    startActivity(i1);
                }*/
            if (no == 1) {
                Toast.makeText(getApplicationContext(), "failed to load", Toast.LENGTH_LONG).show();
            }

        }
    }
    //}





    public String LoginCheckData(String url, String sem) {

        try {
            Log.v("response done", "data");
            //enable httpclient
            HttpClient ht = new DefaultHttpClient();

            //post method enable
            HttpPost hp = new HttpPost(url);

            //json object format
            List<NameValuePair> namevalue = new ArrayList<NameValuePair>();

            //1st "" che a php varu name,& 2nd android ma uper che a name
            namevalue.add(new BasicNameValuePair("sem", sem));
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

                //  Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
                resultstring = EntityUtils.toString(en);
                Log.v("ResultString",resultstring);
                JSONObject json = new JSONObject(resultstring);
                String mes=json.getString("message");
                int sus=json.getInt("success");
                Log.v("Answ",mes);
                Log.v("Ans",sus+"");
                Log.v("sem",sem);
                if(sus==1)
                {
                    /*Intent i = new Intent(getApplicationContext(), CustomizedListView.class);
                    i.putExtra("sem", sem);
                    startActivity(i);*/
                }
                else
                {
                    no = 1;
                }
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

}

