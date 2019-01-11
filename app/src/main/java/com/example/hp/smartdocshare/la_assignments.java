package com.example.hp.smartdocshare;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 15-Nov-17.
 */

public class la_assignments extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    int a[];
    public la_assignments(Activity a, ArrayList<HashMap<String, String>> d,String number[]) {
        activity = a;
        data=d;
        this.a=new int[number.length];
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.menuitem,parent,false);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        // Setting all values in listview
        title.setText(song.get(Assignments.KEY_ARTIST));
        artist.setText(song.get(Assignments.KEY_TITLE));
        duration.setText(song.get(Assignments.KEY_DURATION));
        //imageLoader.DisplayImage(song.get(Assignments.KEY_THUMB_URL), thumb_image);


        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                a[position]=a[position]+1;
                display.setText(String.valueOf(a[position]));
            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {display.setText("minus");
            }
        });*/
        return vi;
    }


}
