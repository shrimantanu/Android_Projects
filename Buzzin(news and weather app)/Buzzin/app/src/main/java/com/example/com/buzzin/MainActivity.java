package com.example.com.buzzin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        java.util.Date currentTime = Calendar.getInstance().getTime();
        Toast.makeText(MainActivity.this,currentTime.toString(),Toast.LENGTH_LONG).show();
        ProgressBar p1;
        p1=(ProgressBar) findViewById(R.id.p1);
        File file = new File(this.getFilesDir(), "json.txt");
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        Log.e("asdfMAin","yanha tak111");
        String sort="top";
//noti cg=new noti(MainActivity.this);
  //      cg.notifyuser();
   // sort=  sp.getString("Sort_P","top");
        URL urlw=null;
        try{
            urlw=new URL("https://api.openweathermap.org/data/2.5/weather?id=1273294&APPID=d7ee051a0a293755dd3bef236370d39b ");
            Log.e("URLS",""+urlw);
        }
        catch (Exception e){
            Log.e("wedreport",e.getMessage().toString());
        }
        new task(p1, MainActivity.this,"jsonw").execute(urlw);
        if(sp.getBoolean("p0",true)){

            URL url=null;
            try{
                url=new URL("https://newsapi.org/v1/articles?source=BBC-news&sortBy="+sort+"&apiKey=963d8ab282ac4435825cc034a1b0e62b");
            Log.e("URLS",""+urlw);
            }
            catch (Exception e){
                Log.e("asdf",e.getMessage().toString());
            }
            new task(p1, MainActivity.this,"json0").execute(url);}
       // Log.e("asdfMAin","yanha tak2222");
        if(sp.getBoolean("p1",true)){
`
            URL url=null;
            try{
                url=new URL(" https://newsapi.org/v1/articles?source=cnn&sortBy="+sort+"&apiKey=963d8ab282ac4435825cc034a1b0e62b");
            }
            catch (Exception e){
         //       Log.e("asdf1",e.getMessage().toString());
            }
            new task(p1, MainActivity.this,"json1").execute(url);}
        if(sp.getBoolean("p2",true)){

            URL url=null;
            try{
                url=new URL("https://newsapi.org/v1/articles?source=espn&sortBy="+sort+"&apiKey=963d8ab282ac4435825cc034a1b0e62b");
            }
            catch (Exception e){
           //     Log.e("asd2f",e.getMessage().toString());
            }
            new task(p1, MainActivity.this,"json2").execute(url);}
        if(sp.getBoolean("p3",true)){

            URL url=null;
            try{
                url=new URL("https://newsapi.org/v1/articles?source=google-news&sortBy="+sort+"&apiKey=963d8ab282ac4435825cc034a1b0e62b");
            }
            catch (Exception e){
             //   Log.e("asdf3",e.getMessage().toString());
            }
            new task(p1,MainActivity.this,"json3").execute(url);}
        if(sp.getBoolean("p4",true)){

            URL url=null;
            try{
                url=new URL("https://newsapi.org/v1/articles?source=the-times-of-india&sortBy="+sort+"&apiKey=963d8ab282ac4435825cc034a1b0e62b");
            }
            catch (Exception e){
                Log.e("asdf4",e.getMessage().toString());
            }
            new task(p1, MainActivity.this,"json4").execute(url);}

    }
    public void doit(JSONObject json, String key){

        try{ String str = json.toString();
            Log.e("asdfMAin","yanha takdoit");
            SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
            Log.e("asdfMAin","yanha tak");

            SharedPreferences.Editor prefEditor = getSharedPreferences( "appData", Context.MODE_PRIVATE ).edit();
            Log.e("asdfMAin","yanha tak");

            prefEditor.putString( key, str );
            Log.e("asdfMAin","yanha tak");

            prefEditor.apply();
            Log.e(key,str);

            Intent in=new Intent(MainActivity.this,second.class);

            startActivity(in);

        }
        catch(Exception e){
            Log.e("asdfMAin",e.getMessage());
            e.printToBackStackTrace();
        }
    }
    public class task extends AsyncTask<URL, Void, JSONObject> {
        ProgressBar p1;
        Context con;
        String key;
        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        public task (ProgressBar p2,Context cont,String k){
            p1=p2;
            key=k;

        }
        @Override

        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(URL... params) {
            String[] st;
            st =new String[50];
            String d="";
            URL url = params[0];
            JSONObject js=null;
            String githubSearchResults = null;
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = urlConnection.getInputStream();

                    BufferedReader streamReader = new BufferedReader( new InputStreamReader(in, "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();

                    String inputStr;
                    Log.e("asdfMAin","yanha tak");

                    int i=0;
                    while ((inputStr = streamReader.readLine()) != null) {
                        responseStrBuilder.append(inputStr);
                        st[i]=inputStr;
                        i++;
                    }
                    js = new JSONObject(responseStrBuilder.toString());
                    d=responseStrBuilder.toString();
                    Log.e("asdfMAin",d);

                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("asdf",e.getMessage().toString());
            }

            return js;
        }

        @Override
        protected void onPostExecute(JSONObject df) {
            doit(df,key);
            //Toast.makeText(con,df,Toast.LENGTH_LONG).show();

        }



    }

}
