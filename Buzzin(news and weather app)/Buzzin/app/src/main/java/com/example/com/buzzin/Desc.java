package com.example.com.buzzin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Desc extends AppCompatActivity {
public TextView t1,t2,td,tda,tta,ttd;
    public ImageView i1;
    public Button b1,b2;
    public static  String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        url="";
        SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
        String s= sharedPref.getString("jdf","");
        Intent t=getIntent();
        int i=4;
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        td=(TextView)findViewById(R.id.td);
        tda=(TextView)findViewById(R.id.tda);
        tta=(TextView)findViewById(R.id.tta);
        ttd=(TextView)findViewById(R.id.ttp);
        i1=(ImageView)findViewById(R.id.i1);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
     if(t.hasExtra(Intent.EXTRA_TEXT)) {
         String fgh="";
         fgh=t.getStringExtra(Intent.EXTRA_TEXT).toString();
         i=Integer.parseInt(fgh);
         try {
             Log.e("asdf", fgh.toString());
         }
         catch (Exception e)
         {
             Log.e("asdf",e.getMessage());
         }
      }
        String a="",s1="",d="",f="",de="",Date="";

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent inw=new Intent(Desc.this,second.class);
                startActivity(inw);
            }
        });
        try {
            JSONArray jd=new JSONArray(s);
            JSONObject js =jd.getJSONObject(i);
            a=js.getString("url");
            url=a;
            String auth=js.getString("author");
            s1=js.getString("title");
            d=js.getString("publishedAt");
            f=js.getString("urlToImage");
            de=js.getString("description");
            Date=js.getString("publishedAt");
            if(Date=="null") {
                td.setVisibility(View.INVISIBLE);
            ttd.setVisibility(View.INVISIBLE);
            }
            else{

            String fg="Date:"+findTime(Date);
            fg=fg+"\n"+"Time:"+findDate(Date);
                td.setText(fg);}
            if(auth.equals("null")==false)
                tda.setText(auth);
            else
            {
              tda.setVisibility(View.INVISIBLE);
                tta.setVisibility(View.INVISIBLE);

            }

            t1.setText(s1);
            t2.setText(de);

            URL urli = null;


            urli = new URL(f);



            new taskimage(i1).execute(urli);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                // Intent inw=new Intent(Desc.this,webv.class);
                //inw.putExtra(Intent.EXTRA_TEXT,url);
                //startActivity(inw);
            }
        });
    }
    public class taskimage extends AsyncTask<URL, Void, Bitmap> {
        ImageView t34;
        int index=0;
        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        public taskimage(ImageView t134){
            t34=t134;


        }
        @Override

        protected void onPreExecute() {
  /*          try
            {
                t34.setImageBitmap(ims[index]);
            }
            catch (Exception e)
            {

            }
*/
        }

        @Override
        protected Bitmap doInBackground(URL... params) {
            Bitmap st=null;

            URL url = params[0];

            String githubSearchResults = null;
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {

                    InputStream in = urlConnection.getInputStream();

                    st= BitmapFactory.decodeStream(in);

                    String inputStr;

                    int i=0;

                    // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("asdf",e.getMessage().toString());
            }

            return st;
        }

        @Override
        protected void onPostExecute(Bitmap Re) {
            //  TextView t2=(TextView)findViewById(R.id.t2);
            // t34.setText(Re[index]);
            //ims[index]=Re;
            t34.setImageBitmap(Re);
        }
    }
    public String findTime(String f){
        String hg;
        int g=0,g1=0;
        g=f.indexOf('T');
        hg=f.substring(0,g);
        g++;
        String hg2;
        g1=f.indexOf('Z');



        return hg;
    }
    public String findDate(String f){
        String hg;
        int g=0,g1=0;
        g=f.indexOf('T');
        hg=f.substring(0,g);
        g++;
        String hg2;

        g1=f.indexOf('Z');

        hg2=f.substring(g,g1);

        return hg2;
    }



}

