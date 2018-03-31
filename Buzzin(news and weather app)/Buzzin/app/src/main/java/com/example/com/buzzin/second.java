package com.example.com.buzzin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class second extends AppCompatActivity implements GreenAdapter.ListItemClickListener,SharedPreferences.OnSharedPreferenceChangeListener{
    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;
    public String js;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(this);
        doit();
    }
    @Override
protected void onDestroy(){
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
    public void doit(){
        Intent in=getIntent();
        String h="";
        int k=0,l=0;
        SharedPreferences sharedPrefe = getSharedPreferences( "appData", Context.MODE_PRIVATE );
        String se= sharedPrefe.getString("jsonw","");
        if(se==null)
            Log.e("wed","null hai");
        else
            Log.e("wed ",se+"");
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        while(k<5){
            if(sp.getBoolean("p"+k,false))
                l++;
            k++;
        }

     /* SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
    String s= sharedPref.getString("json","");
js=s;*/
        js=tryit();

        mNumbersList = (RecyclerView) findViewById(R.id.rv_num);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        int j=10*l+1;

        mNumbersList.setHasFixedSize(true);
        mAdapter = new GreenAdapter(j, this,this,js,se);
        mNumbersList.setAdapter(mAdapter);
    }
public String tryit(){
    int k=0,l=0;
    SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
    String jbf="";
    while(k<5){
        if(sp.getBoolean("p"+k,false))
            l++;
        k++;
        SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
        String s= sharedPref.getString("json"+k,"");
        Log.e("qwer",s);
        JSONObject js = null;
        try {
            js = new JSONObject(s);
            JSONArray jd=js.getJSONArray("articles");
        } catch (JSONException e) {
         Log.e("json",e.getMessage());
        }

    }
    k=0;int l1=0;
    JSONArray jdf=new JSONArray();
    while (k<5){


        if(sp.getBoolean("p"+k,false)){

        SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
        String s= sharedPref.getString("json"+k,"");
        JSONObject js = null;
        try {
            js = new JSONObject(s);
            JSONArray jd=js.getJSONArray("articles");
            int m=l*l1;
            for(int g=0;g<10;g++) {
               // jdf[((l * g) + l1)] = jd.getJSONObject(g);
                jdf.put(((l*g)+l1),jd.getJSONObject(g));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
            l1++;

    }
    k++;
    }
    jbf=jdf.toString();
    SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
    SharedPreferences.Editor prefEditor = getSharedPreferences( "appData", Context.MODE_PRIVATE ).edit();
    prefEditor.putString( "jdf", jbf);
    prefEditor.apply();
    return jbf;


}
    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (clickedItemIndex == 0) {
            Intent in = new Intent(second.this, wether.class);
            startActivity(in);
        } else {
            Intent in = new Intent(second.this, Desc.class);
            String ghd = "" + --clickedItemIndex;
            in.putExtra(Intent.EXTRA_TEXT, ghd);
            startActivity(in);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.m1) {
            Intent in = new Intent(this, setting.class);
            startActivity(in);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("jdf")){

        }
        else
        {
           doit();
        }
    }
}
