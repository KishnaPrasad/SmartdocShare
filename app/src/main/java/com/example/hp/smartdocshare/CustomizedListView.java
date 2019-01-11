package com.example.hp.smartdocshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomizedListView extends Activity {
	// All static variables
	static final String URL = "https://dipentrading.000webhostapp.com/asssignment.xml";

	HashMap<String, String> map1;
	// XML node keys
	static final String KEY_SONG = "as_view"; // parent node
	static final String KEY_ID = "emp";
	static final String KEY_TITLE = "div";
	static final String KEY_ARTIST = "sub";
	static final String KEY_DURATION = "attach";
	static final String KEY_THUMB_URL = "std";
	String name,a,b,c,d;
	ListView list;
    LazyAdapter adapter;
	XMLParser parser;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (android.os.Build.VERSION.SDK_INT > 9) {
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
			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));


			// adding HashList to ArrayList
			songsList.add(map);
		}

		list=(ListView)findViewById(R.id.list);

		// Getting adapter by passing xml data ArrayList
		adapter=new LazyAdapter(this, songsList);
		list.setAdapter(adapter);

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

				HashMap<String, String> song = new HashMap<String, String>();
				song = songsList.get(position);
				name=song.get(CustomizedListView.KEY_TITLE);
                a=song.get(CustomizedListView.KEY_ARTIST);
                b=song.get(CustomizedListView.KEY_DURATION);
                c=song.get(CustomizedListView.KEY_ID);
                d=song.get(CustomizedListView.KEY_THUMB_URL);
				Toast.makeText(getApplicationContext(),name+"",Toast.LENGTH_LONG).show();
				Intent in = new Intent();//CustomizedListView.this,.class);
				//name=songsList.get(CustomizedListView.KEY_TITLE);
				in.putExtra("name",name);
				in.putExtra("a",a);
				in.putExtra("b",b);
                in.putExtra("c",c);
                in.putExtra("d",d);
				startActivity(in);
			}
		});
	}
}