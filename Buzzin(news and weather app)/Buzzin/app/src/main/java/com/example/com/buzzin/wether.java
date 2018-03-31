package com.example.com.buzzin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class wether extends AppCompatActivity {
public TextView t1,tmin,tmax,cname,ws,humid,tv,tp,ttime,tday;
    public Button b1,b2;
    public ImageView i12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wether);
        SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
        String s= sharedPref.getString("jsonw","");
        t1=(TextView)findViewById(R.id.t1);
        tv=(TextView)findViewById(R.id.tv);
        tp=(TextView)findViewById(R.id.tp);
        tday=(TextView)findViewById(R.id.day);
        ttime=(TextView)findViewById(R.id.ttime);
        tmin=(TextView)findViewById(R.id.tmin);
        tmax=(TextView)findViewById(R.id.tmax);
        cname=(TextView)findViewById(R.id.Tcity);
      ws=(TextView)findViewById(R.id.tda);
        humid=(TextView)findViewById(R.id.td);


        i12=(ImageView)findViewById(R.id.i1);

        JSONObject dfwed= null;
        try {
            dfwed = new JSONObject(s);


        JSONObject main=dfwed.getJSONObject("main");
            double f=main.getDouble("temp");
          double min=main.getDouble("temp_min");
            double max=main.getDouble("temp_max");

cname.setText(dfwed.getString("name"));
        t1.setText(GetT(f)+" °C");
            tmin.setText(GetT(min-2) );
            tmax.setText(GetT(max+5));
        JSONObject wind=dfwed.getJSONObject("wind");
        ws.setText(wind.getString("speed")+" km/hr");
   humid.setText(main.getString("humidity")+" %");
            tv.setText(dfwed.getString("visibility")+ " m");
            tp.setText(main.getString("pressure")+" mb");
        JSONObject cloud=dfwed.getJSONObject("clouds");
        java.util.Date currentTime = Calendar.getInstance().getTime();
        Log.e("time",currentTime+"");
        int iny=currentTime.toString().indexOf("GMT+");
        iny=iny-9;

int gny=0;
            String mint=currentTime.toString().substring((iny+2),(iny+5));
        iny=Integer.parseInt(currentTime.toString().substring(iny,(iny+2)));
            tday.setText(currentTime.toString().substring(0,8));
            String am="AM";
            gny=iny;
            if(gny>12){
                gny=gny-12;
            am="PM";}
            ttime.setText(gny+mint+" "+am);
            Log.e("abhi",iny+"");
        char d='N';
        int vol=0;
        try {
            JSONObject rain = dfwed.getJSONObject("rain");
            vol=rain.getInt("3h");
        }catch(Exception e){

        }
        if(iny>=6&&iny<20)
            d='D';
        if(d=='N')
        {
            if (cloud.getInt("all")<25)
             i12.setImageResource(R.drawable.a11);
            else if (cloud.getInt("all")<50)
             i12.setImageResource(R.drawable.a12);
            else if (cloud.getInt("all")<100)
             i12.setImageResource(R.drawable.a13);

        }
        else
        {
            if (cloud.getInt("all")<25){
                if (iny<=7)
                   i12.setImageResource(R.drawable.asr);
                else if(iny>=17)
                i12.setImageResource(R.drawable.ass);
                else
                 i12.setImageResource(R.drawable.a6);}
            else if (cloud.getInt("all")<50)
             i12.setImageResource(R.drawable.a7);
            else if (cloud.getInt("all")<100)
                i12.setImageResource(R.drawable.a8);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        } b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent inw=new Intent(wether.this,second.class);
                startActivity(inw);
            }
        });
    }
    public   String GetT(double d){

        double gh=d;
        gh=gh-273;

        int g=(int ) gh;
        gh=gh-g;
        int jg=(int) gh*10;
        String h=g+"."+jg+"";
        return h;
    }
}
